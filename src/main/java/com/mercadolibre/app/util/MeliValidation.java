/**
 * 
 */
package com.mercadolibre.app.util;

import static com.mercadolibre.app.util.MeliConstants.ERROR_IP;
import static com.mercadolibre.app.util.MeliConstants.PATTERN_IP;
import static java.util.Objects.isNull;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.mercadolibre.app.domain.CountryDTO;
import com.mercadolibre.app.domain.CurrencyCountryDTO;
import com.mercadolibre.app.domain.IpInfoDTO;
import com.mercadolibre.app.exception.MeliException;
import com.mercadolibre.app.model.Blacklist;
import com.mercadolibre.app.repository.BlacklistRepository;

/**
 * 
 * Clase donde se definen las validaciones utilitarias del proyecto
 * @author Alejandro.Hurtado
 *
 */
@Component
public class MeliValidation {
	
	@Autowired
    private BlacklistRepository blacklistRepository;
	
	public void validationBlockIp(String ip) throws MeliException {
		Optional<Blacklist> ipBlock = this.blacklistRepository.findByIpAddress(ip);
		if(ipBlock.isPresent()) {
			throw new MeliException(HttpStatus.BAD_REQUEST.value(), ERROR_IP, "La dirección IP del usuario se encuentra bloqueada", 
    				"DEM001");
    	}
	}
	
	public void validationObjectIp(IpInfoDTO ipInfo) throws MeliException {
		if(isNull(ipInfo)) {
			throw new MeliException(HttpStatus.BAD_REQUEST.value(), ERROR_IP, "Validar el llamado del api de IP ya que el objeto de respuesta llego vacío.", 
					"DEM013");
		}
		
		if(isNull(ipInfo.getCountry_name())) {
			throw new MeliException(HttpStatus.BAD_REQUEST.value(), ERROR_IP, "Validar el llamado del api de IP ya que el objeto de respuesta llego vacío.", 
					"DEM002");
		}
		if(isNull(ipInfo.getCurrency())) {
			throw new MeliException(HttpStatus.BAD_REQUEST.value(), ERROR_IP, "Validar el status del plan de Ipapi ya que no retornó la info completa.", 
					"DEM003");
		}
		if(isNull(ipInfo.getCurrency().getCode())) {
			throw new MeliException(HttpStatus.BAD_REQUEST.value(), ERROR_IP, "Validar el status del plan de Ipapi ya que no retornó la info completa del código de moneda.", 
					"DEM011");
		}
	}
	
	public void validationObjectCountry(CountryDTO country) throws MeliException {
		if(isNull(country)) {
			throw new MeliException(HttpStatus.BAD_REQUEST.value(), ERROR_IP, "Validar el llamado del api de información de país ya que el objeto de respuesta llego vacío.", 
					"DEM005");
		}
		if(isNull(country.getAlpha3Code())) {
			throw new MeliException(HttpStatus.BAD_REQUEST.value(), ERROR_IP, "Validar la información de país ya que el código ISO de respuesta llego vacío.", 
					"DEM006");
		}
		
	}
	
	public void validationObjectCountryConcurrency(CurrencyCountryDTO countryConcurrency) throws MeliException {
		if(isNull(countryConcurrency)) {
			throw new MeliException(HttpStatus.BAD_REQUEST.value(), ERROR_IP, "Validar el llamado del api de información de moneda del país ya que el objeto de respuesta llego vacío.", 
					"DEM0014");
		}
		if(isNull(countryConcurrency.getDate())) {
			throw new MeliException(HttpStatus.BAD_REQUEST.value(), ERROR_IP, "Validar el llamado del api de información de moneda del país ya que el objeto de respuesta llego vacío.", 
					"DEM007");
		}
		if(isNull(countryConcurrency.getRates())) {
			throw new MeliException(HttpStatus.BAD_REQUEST.value(), ERROR_IP, "Validar la información de moneda del país ya que la cotización en EUR llego vacío.", 
					"DEM008");
		}
		if(isNull(countryConcurrency.getRates().getEUR())) {
			throw new MeliException(HttpStatus.BAD_REQUEST.value(), ERROR_IP, "Validar la información de moneda del país ya que la cotización en EUR llego vacío.", 
					"DEM008");
		}
		if(isNull(countryConcurrency.getRates().getUSD())) {
			throw new MeliException(HttpStatus.BAD_REQUEST.value(), ERROR_IP, "Validar la información de moneda del país ya que la cotización en USD llego vacío.", 
					"DEM009");
		}
	}
	
	public void validationAddressIp(String ip) throws MeliException {
        if(!ip.matches(PATTERN_IP))
        	throw new MeliException(HttpStatus.BAD_REQUEST.value(), ERROR_IP, "La IP no cumple con el formato estandar de IPv4", "DEM012");
	}
}
