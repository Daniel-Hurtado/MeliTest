/**
 * 
 */
package com.mercadolibre.app.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mercadolibre.app.util.MeliProperties;

/**
 * @author Alejandro.Hurtado
 *
 */
@Component
public class ObtainInfoCountryCurrencyProvider {
	 /** Logger */
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
     * Permite hacer llamado a la api de notificación para el envío de mensajes de texto
     * 
     * @param smsDto
     * @param ip
     * @return
     
    public boolean sendInvitationToSms(SmsDTO smsDto, String ip) {
        LOGGER.debug("Init sendOtpToSms with smsDto: {}", smsDto);

        boolean isSended = true;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode request = mapper.createObjectNode();
            String phone = smsDto.getPhone() != null ? smsDto.getPhone().replace("+", "") : null;
            request.put("name", smsDto.getName());
            request.put("lastName", smsDto.getLastName());
            request.put("email", smsDto.getEmail());
            request.put("code", smsDto.getCode());
            request.put("phone", phone);
            request.put("transaction", smsDto.getTransaction());
            request.put("userId", smsDto.getUserId());
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add(IP_ADDRESS, ip);

            HttpEntity<ObjectNode> requestEntity = new HttpEntity<>(request, headers);

            Response<?> response = this.restTemplate.postForObject(
                    this.properties.getNotification().getEndpointUrl().concat(this.properties.getNotification().getSmsPath()),
                    requestEntity, Response.class);
            isSended = (Boolean) response.getData();
        } catch (Exception e) {
            LOGGER.error("Error in sendOtpToSms", e);
            isSended = false;
        }

        LOGGER.debug("Finish sendOtpToSms with result: {}", isSended);
        return isSended;
    }*/
}
