/**
 * 
 */
package com.mercadolibre.app.business;

import static com.mercadolibre.app.util.MeliConstants.EMPTY_STRING;
import static com.mercadolibre.app.util.MeliConstants.ERROR_IP;
import static com.mercadolibre.app.util.MeliConstants.UNEXPECTED_ERROR;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mercadolibre.app.domain.CountryDTO;
import com.mercadolibre.app.domain.CurrencyCountryDTO;
import com.mercadolibre.app.domain.InfoCompleteDTO;
import com.mercadolibre.app.domain.IpInfoDTO;
import com.mercadolibre.app.exception.MeliException;
import com.mercadolibre.app.mapper.MeliMapper;
import com.mercadolibre.app.model.Blacklist;
import com.mercadolibre.app.provider.ObtainInfoCountryCurrencyProvider;
import com.mercadolibre.app.provider.ObtainInfoCountryProvider;
import com.mercadolibre.app.provider.ObtainInfoIpProvider;
import com.mercadolibre.app.repository.BlacklistRepository;
import com.mercadolibre.app.util.MeliValidation;
import com.mercadolibre.app.util.Response;

/**
 * Clase encargada de implementar la lógica de negocio.
 * 
 * @author Alejandro.Hurtado
 *
 */
@Service
public class MeliBusinessImpl implements MeliBusiness{
	private static final Logger LOGGER = LoggerFactory.getLogger(ObtainInfoIpProvider.class);
    @Autowired
    private ObtainInfoIpProvider obtainInfoIpProvider;
    @Autowired
    private ObtainInfoCountryProvider obtainInfoCountryProvider;
    @Autowired
    private ObtainInfoCountryCurrencyProvider obtainInfoCountryCurrencyProvider;
    @Autowired
    private MeliValidation meliValidation;
    @Autowired
    private MeliMapper meliMapper;
    @Autowired
    private BlacklistRepository blacklistRepository;
    
    /**
     * @see MeliBusiness#validateIp(String, UserDTO, String)
     */
    @Override
    public Response<InfoCompleteDTO> validateIp(String ip) {
    	LOGGER.info("Init validateIp with request: {}", ip);
        Response<InfoCompleteDTO> response = null;
        try {
        	this.meliValidation.validationAddressIp(ip);
        	this.meliValidation.validationBlockIp(ip);        	
            IpInfoDTO ipInfo = this.obtainInfoIpProvider.consumeApiGetInfoIp(ip);
            this.meliValidation.validationObjectIp(ipInfo);
        	CountryDTO country = this.obtainInfoCountryProvider.consumeApiGetInfoCountry(ipInfo.getCountry_code());
        	this.meliValidation.validationObjectCountry(country);
        	CurrencyCountryDTO countryConcurrency = this.obtainInfoCountryCurrencyProvider.consumeApiGetInfoCountryConcurrency(ipInfo.getCurrency().getCode());
            this.meliValidation.validationObjectCountryConcurrency(countryConcurrency);
            
            response = new Response<>(HttpStatus.OK.value(), "La información de tu Ip fue procesada correctamente", "Se realizó la validación exitosa de la IP", "DIM001",
                    null, meliMapper.fromEntityToDto(ipInfo, country, countryConcurrency));
        } catch (MeliException e) {
            response = new Response<>(e.getStatus(), e.getUserMessage(), e.getMessage(), e.getErrorCode(), EMPTY_STRING);
        } catch (Exception e) {
            response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_IP,
            		UNEXPECTED_ERROR, "DEM010", EMPTY_STRING);
        } 
        LOGGER.info("Finish validateIp with response: {}", response);

        return response;
    }

	@Override
	public Response<Void> banIp(String ip) {
		LOGGER.info("Init banIp with request: {}", ip);
		Response<Void> response = null;
		try {
			this.meliValidation.validationBlockIp(ip);
			this.meliValidation.validationAddressIp(ip);
			this.createBanIp(ip);
			response = new Response<>(HttpStatus.OK.value(), "La dirección IP fue bloqueada correctamente", "Se realizó el bloqueo exitoso de la IP", "DIM001",
                    null);
        } catch (MeliException e) {
            response = new Response<>(e.getStatus(), e.getUserMessage(), e.getMessage(), e.getErrorCode(), EMPTY_STRING);
        } catch (Exception e) {
            response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_IP,
            		UNEXPECTED_ERROR, "DEM013", EMPTY_STRING);
        } 
		LOGGER.info("Finish banIp with response: {}", response);
		return response;
	}

	@Override
	public Response<List<Blacklist>> listBanIp() {
		LOGGER.info("Init listBanIp");
		Response<List<Blacklist>> response = null;
		try {
			List<Blacklist> listIp = this.getBanIp();
			if(listIp.isEmpty()) {
				response = new Response<>(HttpStatus.BAD_REQUEST.value(), "La lista de bloqueo de IP fue consultada correctamente pero no arrojó resultados", "Se realizó la consulta de las IP bloqueadas correctamente, pero no arrojó resultados", "DIM002",
	                    null);
			} else {
				response = new Response<>(HttpStatus.OK.value(), "La lista de bloqueo de IP fue consultada correctamente", "Se realizó la consulta de las IP bloqueadas correctamente", "DIM002",
	                    null, listIp);
			}
        } catch (Exception e) {
            response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_IP,
            		UNEXPECTED_ERROR, "DEM014", EMPTY_STRING);
        } 
		LOGGER.info("Finish listBanIp with response: {}", response);
		return response;
	}

	@Override
	public Response<Void> modifyBanIp(Long blacklistId, String newIp) {
		LOGGER.info("Init modifyBanIp with request: {}", newIp);
		Response<Void> response = null;
		try {
			this.meliValidation.validationAddressIp(newIp);
			this.meliValidation.validationBlockIp(newIp);
			Optional<Blacklist> ipInfo = this.blacklistRepository.findByBlacklistId(blacklistId);
			if(ipInfo.isPresent()) {
				Blacklist ipBefore = ipInfo.get();
				ipBefore.setIpAddress(newIp);
				this.blacklistRepository.save(ipBefore);
				response = new Response<>(HttpStatus.OK.value(), "La modificación de tu IP fue realizada correctamente", "Se realizó la modificación de la IP correctamente", "DIM002",
	                    null);
			} else {
				response = new Response<>(HttpStatus.BAD_REQUEST.value(), "La IP a modificar no existe en base de datos", "El ID de la IP no arrojó ningún resultado", "DIM002",
	                    null);
			}
        } catch (MeliException e) {
            response = new Response<>(e.getStatus(), e.getUserMessage(), e.getMessage(), e.getErrorCode(), EMPTY_STRING);
        } catch (Exception e) {
            response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_IP,
            		UNEXPECTED_ERROR, "DEM015", EMPTY_STRING);
        } 
		LOGGER.info("Finish modifyBanIp with response: {}", response);
		return response;
	}

	@Override
	public Response<Void> deleteBanIp(Long blacklistId) {
		LOGGER.info("Init deleteBanIp with request: {}", blacklistId);
		Response<Void> response = null;
		try {
			Optional<Blacklist> ipInfo = this.blacklistRepository.findByBlacklistId(blacklistId);
			if(ipInfo.isPresent()) {
				Blacklist ipBefore = ipInfo.get();
				this.blacklistRepository.delete(ipBefore);
				response = new Response<>(HttpStatus.OK.value(), "La eliminación de tu IP fue realizada correctamente", "Se realizó la eliminación de la IP correctamente", "DIM002",
	                    null);
			} else {
				response = new Response<>(HttpStatus.BAD_REQUEST.value(), "La IP a eliminar no existe en base de datos", "El ID de la IP no arrojó ningún resultado", "DIM002",
	                    null);
			}
        } catch (Exception e) {
            response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_IP,
            		UNEXPECTED_ERROR, "DEM016", EMPTY_STRING);
        } 
		LOGGER.info("Finish deleteBanIp with response: {}", response);
		return response;
	}
	
	public List<Blacklist> getBanIp(){
		return this.blacklistRepository.findAll();
	}
	
	public void createBanIp(String ip) throws MeliException{
		Blacklist banIp = new Blacklist();
		banIp.setIpAddress(ip);
		banIp.setFecha(new Timestamp(new Date().getTime()));
		this.blacklistRepository.save(banIp);
	}
}
