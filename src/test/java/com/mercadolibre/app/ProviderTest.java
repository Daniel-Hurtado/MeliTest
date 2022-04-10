/**
 * 
 */
package com.mercadolibre.app;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import com.mercadolibre.app.config.DataSourceConfig;
import com.mercadolibre.app.provider.ObtainInfoCountryCurrencyProvider;
import com.mercadolibre.app.provider.ObtainInfoCountryProvider;
import com.mercadolibre.app.provider.ObtainInfoIpProvider;
import com.mercadolibre.app.util.Response;

/**
 * Clase definida para la creación de las pruebas unitarias
 * @author Alejandro.Hurtado
 *
 */
@SpringBootTest(classes = { MeliApplication.class, DataSourceConfig.class })
@ActiveProfiles("test")
public class ProviderTest {
	
	private static final String TEST_IP = "127.0.0.1";
	private static final String TEST_COUNTRY = "127.0.0.1";
	private static final String TEST_COUNTRY_CURRENCY = "127.0.0.1";
	
	@Autowired
	private ObtainInfoIpProvider obtainInfoIpProvider;
	@Autowired
	private ObtainInfoCountryProvider obtainInfoCountryProvider;
	@Autowired
	private ObtainInfoCountryCurrencyProvider obtainInfoCountryCurrencyProvider;
	
	@MockBean
    private RestTemplate restTemplate;
	
	@SuppressWarnings("rawtypes")
	@Test
    public void obtainInfoIpTest() {
		Response<Void> response = new Response<>();
		ResponseEntity<Response> response1 = new ResponseEntity<>(response, HttpStatus.OK);
        when(this.restTemplate.exchange(Mockito.any(), Mockito.eq(Response.class))).thenReturn(response1);
        this.obtainInfoIpProvider.consumeApiGetInfoIp(TEST_IP);
    }
	
	@SuppressWarnings("rawtypes")
	@Test
    public void obtainInfoCountryTest() {
		Response<Void> response = new Response<>();
		ResponseEntity<Response> response1 = new ResponseEntity<>(response, HttpStatus.OK);
        when(this.restTemplate.exchange(Mockito.any(), Mockito.eq(Response.class))).thenReturn(response1);
        this.obtainInfoCountryProvider.consumeApiGetInfoCountry(TEST_COUNTRY);
    }
	
	@SuppressWarnings("rawtypes")
	@Test
    public void obtainInfoCountryCurrencyTest() {
		Response<Void> response = new Response<>();
		ResponseEntity<Response> response1 = new ResponseEntity<>(response, HttpStatus.OK);
        when(this.restTemplate.exchange(Mockito.any(), Mockito.eq(Response.class))).thenReturn(response1);
        this.obtainInfoCountryCurrencyProvider.consumeApiGetInfoCountryConcurrency(TEST_COUNTRY_CURRENCY);
    }
}
