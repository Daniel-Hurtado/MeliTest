/**
 * 
 */
package com.mercadolibre.app.business;

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
import com.mercadolibre.app.provider.ObtainInfoCountryCurrencyProvider;
import com.mercadolibre.app.provider.ObtainInfoCountryProvider;
import com.mercadolibre.app.provider.ObtainInfoIpProvider;
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
    
    /**
     * @see MeliBusiness#validateIp(String, UserDTO, String)
     */
    @Override
    public Response<InfoCompleteDTO> validateIp(String ip) {
    	LOGGER.info("Init validateIp with request: {}", ip);
        Response<InfoCompleteDTO> response = null;
        try {
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
            response = new Response<>(e.getStatus(), e.getUserMessage(), e.getMessage(), e.getErrorCode(), "");
        } catch (Exception e) {
            response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocurrió un error en la validación de tu dirección IP",
                    "Ocurrió un error inesperado", "DEM010", "");
        } 
        LOGGER.info("Finish validateIp with response: {}", response);

        return response;
    }
}
