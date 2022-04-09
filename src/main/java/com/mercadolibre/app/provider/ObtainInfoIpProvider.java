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
import com.mercadolibre.app.domain.IpInfoDTO;
import com.mercadolibre.app.util.MeliProperties;

/**
 * @author Alejandro.Hurtado
 *
 */
@Component
public class ObtainInfoIpProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(ObtainInfoIpProvider.class);
    /** Objeto para la carga de propiedades */
    private final MeliProperties properties;
    /** Objeto para el consumo de Api Rest */
    private final RestTemplate restTemplate;
    
	/**
	 * @param properties
	 * @param restTemplate
	 */
	public ObtainInfoIpProvider(MeliProperties properties, RestTemplate restTemplate) {
		this.properties = properties;
		this.restTemplate = restTemplate;
	}
    
	/**
     * Permite hacer llamado a la api de obtener información mediante la IP
     * 
     * @param ip
     * @return
     */
    public IpInfoDTO consumeApiGetInfoIp(String ip) {
    	LOGGER.info("Init consumeApiGetInfoIp with Ip: {}", ip);
		ResponseEntity <String> response = null;
		IpInfoDTO ipInfo = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", "application/json");
			response = restTemplate.exchange(this.properties.getUrlApiIp().concat(ip).concat("?").concat(ACCESS_KEY).concat(this.properties.getAccessKeyIp()), 
					HttpMethod.GET, null, new ParameterizedTypeReference<String>() {});
			Gson g = new Gson();  
			ipInfo = g.fromJson(response.getBody(), IpInfoDTO.class);  
		} catch (Exception e) {
			LOGGER.error("Error in consumeApiGetInfoIp: {}", e);
		}
		LOGGER.info("Finish consumeApiGetInfoIp with response: {}", ipInfo);
		
		return ipInfo;
    }
}
