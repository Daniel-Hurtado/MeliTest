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
import com.mercadolibre.app.domain.CurrencyCountryDTO;
import com.mercadolibre.app.domain.IpInfoDTO;
import com.mercadolibre.app.util.MeliProperties;

/**
 * @author Alejandro.Hurtado
 *
 */
@Component
public class ObtainInfoCountryCurrencyProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(ObtainInfoCountryCurrencyProvider.class);
    /** Objeto para la carga de propiedades */
    private final MeliProperties properties;
    /** Objeto para el consumo de Api Rest */
    private final RestTemplate restTemplate;
    
	/**
	 * @param properties
	 * @param restTemplate
	 */
	public ObtainInfoCountryCurrencyProvider(MeliProperties properties, RestTemplate restTemplate) {
		this.properties = properties;
		this.restTemplate = restTemplate;
	}
    
	/**
     * Permite hacer llamado a la api de obtener información de la moneda actual de un país
     * 
     * @param localCurrency
     * @return
     */
    public CurrencyCountryDTO consumeApiGetInfoCountryConcurrency(String localCurrency) {
    	LOGGER.info("Init consumeApiGetInfoCountryConcurrency with localCurrency: {}", localCurrency);
		ResponseEntity <String> response = null;
		CurrencyCountryDTO currencyInfo = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", "application/json");
			response = restTemplate.exchange(this.properties.getUrlApiInfoCurrencyCountry().concat("?").concat(ACCESS_KEY)
					.concat(this.properties.getAccessKeyInfoCurrencyCountry()).concat("&base=").concat(localCurrency).concat("&symbols=EUR,USD"), HttpMethod.GET, null, new ParameterizedTypeReference<String>() {});
			Gson g = new Gson();  
			currencyInfo = g.fromJson(response.getBody(), CurrencyCountryDTO.class);  
		} catch (Exception e) {
			LOGGER.error("Error in consumeApiGetInfoCountryConcurrency: {}", e);
		}
		LOGGER.info("Finish consumeApiGetInfoCountryConcurrency with response: {}", currencyInfo);
		
		return currencyInfo;
    }
}
