/**
 * 
 */
package com.mercadolibre.app.provider;

import static com.mercadolibre.app.util.MeliConstants.ACCESS_KEY;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.mercadolibre.app.domain.CountryDTO;
import com.mercadolibre.app.util.MeliProperties;

/**
 * @author Alejandro.Hurtado
 *
 */
@Component
public class ObtainInfoCountryProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(ObtainInfoCountryProvider.class);
    /** Objeto para la carga de propiedades */
    private final MeliProperties properties;
    /** Objeto para el consumo de Api Rest */
    private final RestTemplate restTemplate;
    
	/**
	 * @param properties
	 * @param restTemplate
	 */
	public ObtainInfoCountryProvider(MeliProperties properties, RestTemplate restTemplate) {
		this.properties = properties;
		this.restTemplate = restTemplate;
	}
    
	/**
     * Permite hacer llamado a la api de obtener información de un país
     * 
     * @param countryName
     * @return
     */
    public CountryDTO consumeApiGetInfoCountry(String countryCode) {
    	LOGGER.info("Init consumeApiGetInfoCountry with countryCode: {}", countryCode);
		ResponseEntity <String> response = null;
		CountryDTO countryInfo = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", "application/json");
			response = restTemplate.exchange(this.properties.getUrlApiInfoCountry().concat(countryCode).concat("?").concat(ACCESS_KEY).concat(this.properties.getAccessKeyInfoCountry()).concat("&fullText=true"), 
					HttpMethod.GET, null, new ParameterizedTypeReference<String>() {});
			Gson g = new Gson();  
			countryInfo = g.fromJson(response.getBody(), CountryDTO.class);
		} catch (Exception e) {
			LOGGER.error("Error in consumeApiGetInfoCountry: {}", e);
		}
		LOGGER.info("Finish consumeApiGetInfoCountry with response: {}", countryInfo);
		
		return countryInfo;
    }
}
