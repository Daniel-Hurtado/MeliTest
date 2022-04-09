/**
 * 
 */
package com.mercadolibre.app.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mercadolibre.app.domain.CountryDTO;
import com.mercadolibre.app.domain.CurrencyCountryDTO;
import com.mercadolibre.app.domain.InfoCompleteDTO;
import com.mercadolibre.app.domain.IpInfoDTO;

/**
 * 
 * 
 * @author Alejandro.Hurtado
 *
 */
@Component
public class MeliMapper {
	private static final Logger LOGGER = LoggerFactory.getLogger(MeliMapper.class);
	
	/**
     * Permite mapear la informacion de las entidades a la respuesta necesaria
     * 
     * @param user
     *            Entidad con la informacion del usuario
     * @return DTO con la informacion del usuario
     */
    public InfoCompleteDTO fromEntityToDto(IpInfoDTO ipInfo, CountryDTO country, CurrencyCountryDTO countryConcurrency) {
    	LOGGER.info("Init fromEntityToDto");
    	InfoCompleteDTO info = new InfoCompleteDTO();
    	info.setCountryName(ipInfo.getCountry_name());
    	info.setCountryIso(country.getAlpha3Code());
    	info.setCountryCurrency(ipInfo.getCurrency().getCode());
    	info.setCountryCurrencyEUR("La cotización actual a la fecha ".concat(countryConcurrency.getDate()).concat(" de la moneda en Euros es: ").concat(countryConcurrency.getRates().getEUR()));
    	info.setCountryCurrencyUSD("La cotización actual a la fecha ".concat(countryConcurrency.getDate()).concat(" de la moneda en USD es: ").concat(countryConcurrency.getRates().getUSD()));
    	LOGGER.info("Finish fromEntityToDto with Info: {}", info);
    	return info;
    }
}
