/**
 * 
 */
package com.mercadolibre.app;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mercadolibre.app.business.MeliBusiness;
import com.mercadolibre.app.config.DataSourceConfig;
import com.mercadolibre.app.domain.CountryDTO;
import com.mercadolibre.app.domain.CurrencyCountryDTO;
import com.mercadolibre.app.domain.IpInfoDTO;
import com.mercadolibre.app.domain.RatesDTO;
import com.mercadolibre.app.domain.currencyDTO;
import com.mercadolibre.app.model.Blacklist;
import com.mercadolibre.app.provider.ObtainInfoCountryCurrencyProvider;
import com.mercadolibre.app.provider.ObtainInfoCountryProvider;
import com.mercadolibre.app.provider.ObtainInfoIpProvider;
import com.mercadolibre.app.repository.BlacklistRepository;
import com.mercadolibre.app.rest.MeliRest;

/**
 * Clase definida para la creación de las pruebas unitarias
 * @author Alejandro.Hurtado
 *
 */
@SpringBootTest(classes = { MeliApplication.class, DataSourceConfig.class })
@ActiveProfiles("test")
public class MeliApplicationRestTest {
	
	private static final String GENERAL_PATH = "/api/v1/";
	private static final String GENERAL_PATH_IP_ADDRESS = "?Ip-Address=";
    private static final String PATH_LIST_BAN_IP = "list-ban-ip";
    private static final String PATH_BAN_IP = "ban-ip";
    private static final String PATH_MODIFY_BAN_IP = "modify-ban-ip";
    private static final String PATH_DELETE_BAN_IP = "delete-ip";
    private static final String PATH_INFO_IP = "info-ip";
    private static final String RESPONSE_STATE = "$.status";
    private static final String TEST_IP = "128.0.0.10";
    private static final String TEST_SECOND_IP = "129.0.0.10";
    private static final String TEST_BAD_IP = "IAM.0.0.1";
    
    private MockMvc mvc;
    
    @Autowired
    private MeliBusiness business;
    @Autowired
    private BlacklistRepository blacklistRepository;
    @MockBean
    private ObtainInfoCountryCurrencyProvider obtainInfoCountryCurrencyProvider;
    @MockBean
    private ObtainInfoCountryProvider obtainInfoCountryProvider;
    @MockBean
    private ObtainInfoIpProvider obtainInfoIpProvider;
    
    private Blacklist blacklist;
    
    @BeforeEach
    public void setUp() {
    	this.mvc = MockMvcBuilders.standaloneSetup(new MeliRest(business)).build();
    }
    
    private Blacklist createBanList() {
    	blacklist = new Blacklist();
    	blacklist.setIpAddress(TEST_IP);
    	blacklist.setFecha(new Timestamp(new Date().getTime()));
    	blacklist = blacklistRepository.save(blacklist);
    	return blacklist;
    }
    
    private IpInfoDTO consumeFirstApi() {
    	currencyDTO currencyInfo = new currencyDTO();
    	currencyInfo.setCode("COP");
    	currencyInfo.setName("Colombian Peso");
    	currencyInfo.setPlural("Colombians Pesos");
    	currencyInfo.setSymbol("CO$");
    	currencyInfo.setSymbol_native("$");
    	
    	IpInfoDTO ipInfo = new IpInfoDTO();
    	ipInfo.setCity("Bogotá");
    	ipInfo.setContinent_code("AME");
    	ipInfo.setContinent_name("América del Sur");
    	ipInfo.setCountry_code("COL");
    	ipInfo.setCountry_name("Colombia");
    	ipInfo.setCurrency(currencyInfo);
    	ipInfo.setLatitude("4.570868");
    	ipInfo.setLongitude("-74.297333");
    	return ipInfo;
    }
    private CountryDTO consumeSecondApi() {
    	CountryDTO countryInfo = new CountryDTO();
    	countryInfo.setAlpha3Code("COL");
    	countryInfo.setCapital("Bogotá");
    	countryInfo.setName("Colombia");
    	return countryInfo;
    }
    private CurrencyCountryDTO consumeThirdApi() {
    	RatesDTO rates = new RatesDTO();
    	rates.setEUR("0,00024");
    	rates.setUSD("0,00027");
    	
    	CurrencyCountryDTO currencyCountryInfo = new CurrencyCountryDTO();
    	currencyCountryInfo.setDate("10/04/2022");
    	currencyCountryInfo.setRates(rates);
    	return currencyCountryInfo;
    }
    
	@Test
    public void testGetListBanIpBadRequest() throws Exception {
		blacklistRepository.deleteAll();
        mvc.perform(get(URI.create(GENERAL_PATH + PATH_LIST_BAN_IP))).andDo(print()).andExpect(status().isOk())
        		.andExpect(jsonPath(RESPONSE_STATE).value(HttpStatus.BAD_REQUEST.value()));
    }
	
	@Test
    public void testGetListBanIpOkRequest() throws Exception {
		this.createBanList();
		List<Blacklist> listBlack = new ArrayList<>();
		listBlack.add(blacklist);
        mvc.perform(get(URI.create(GENERAL_PATH + PATH_LIST_BAN_IP))).andDo(print()).andExpect(status().isOk())
        		.andExpect(jsonPath(RESPONSE_STATE).value(HttpStatus.OK.value()));
    }
	
	@Test
    public void testCreateBanIpBadRequestForErrorInFormatIp() throws Exception {
        mvc.perform(post(URI.create(GENERAL_PATH + PATH_BAN_IP + GENERAL_PATH_IP_ADDRESS + TEST_BAD_IP))).andDo(print()).andExpect(status().isOk())
        		.andExpect(jsonPath(RESPONSE_STATE).value(HttpStatus.BAD_REQUEST.value()));
    }
    
    @Test
    public void testCreateBanIpBadRequestForIpBlockActually() throws Exception {
    	blacklistRepository.deleteAll();
    	this.createBanList();
        mvc.perform(post(URI.create(GENERAL_PATH + PATH_BAN_IP + GENERAL_PATH_IP_ADDRESS + TEST_IP))).andDo(print()).andExpect(status().isOk())
        		.andExpect(jsonPath(RESPONSE_STATE).value(HttpStatus.BAD_REQUEST.value()));
    }
	
	@Test
    public void testCreateBanIpOkRequest() throws Exception {
		blacklistRepository.deleteAll();
        mvc.perform(post(URI.create(GENERAL_PATH + PATH_BAN_IP + GENERAL_PATH_IP_ADDRESS + TEST_IP))).andDo(print()).andExpect(status().isOk())
        		.andExpect(jsonPath(RESPONSE_STATE).value(HttpStatus.OK.value()));
    }
	
	@Test
    public void testModifyBanIpBadRequestForErrorInFormatIp() throws Exception {
        mvc.perform(put(URI.create(GENERAL_PATH + PATH_MODIFY_BAN_IP + "?Blacklist-Id=10" + "&New-Ip=" + TEST_BAD_IP))).andDo(print()).andExpect(status().isOk())
        		.andExpect(jsonPath(RESPONSE_STATE).value(HttpStatus.BAD_REQUEST.value()));
    }
	
	@Test
    public void testModifyBanIpBadRequestForIpBlockActually() throws Exception {
		blacklistRepository.deleteAll();
		this.createBanList();
        mvc.perform(put(URI.create(GENERAL_PATH + PATH_MODIFY_BAN_IP + "?Blacklist-Id=10" + "&New-Ip=" + TEST_IP))).andDo(print()).andExpect(status().isOk())
        		.andExpect(jsonPath(RESPONSE_STATE).value(HttpStatus.BAD_REQUEST.value()));
    }
	
	@Test
    public void testModifyBanIpBadRequestForIdNotEncountered() throws Exception {
		blacklistRepository.deleteAll();
        mvc.perform(put(URI.create(GENERAL_PATH + PATH_MODIFY_BAN_IP + "?Blacklist-Id=1" + "&New-Ip=" + TEST_IP))).andDo(print()).andExpect(status().isOk())
        		.andExpect(jsonPath(RESPONSE_STATE).value(HttpStatus.BAD_REQUEST.value()));
    }
	
	@Test
    public void testModifyBanIpOkRequest() throws Exception {
		blacklistRepository.deleteAll();
		Blacklist banIp = this.createBanList();
        mvc.perform(put(URI.create(GENERAL_PATH + PATH_MODIFY_BAN_IP + "?Blacklist-Id="+ banIp.getBlacklistId() + "&New-Ip=" + TEST_SECOND_IP))).andDo(print()).andExpect(status().isOk())
        		.andExpect(jsonPath(RESPONSE_STATE).value(HttpStatus.OK.value()));
    }
    
    @Test
    public void testDeleteBanIpBadRequestForIdNotEncountered() throws Exception {
		blacklistRepository.deleteAll();
        mvc.perform(delete(URI.create(GENERAL_PATH + PATH_DELETE_BAN_IP + "?Blacklist-Id=1"))).andDo(print()).andExpect(status().isOk())
        		.andExpect(jsonPath(RESPONSE_STATE).value(HttpStatus.BAD_REQUEST.value()));
    }
    
    @Test
    public void testDeleteBanIpOkRequest() throws Exception {
		blacklistRepository.deleteAll();
		Blacklist banIp = this.createBanList();
        mvc.perform(delete(URI.create(GENERAL_PATH + PATH_DELETE_BAN_IP + "?Blacklist-Id="+ banIp.getBlacklistId()))).andDo(print()).andExpect(status().isOk())
        		.andExpect(jsonPath(RESPONSE_STATE).value(HttpStatus.OK.value()));
    }
    
    @Test
    public void testInfoIpBadRequestForErrorInFormatIp() throws Exception {
        mvc.perform(get(URI.create(GENERAL_PATH + PATH_INFO_IP + GENERAL_PATH_IP_ADDRESS + TEST_BAD_IP))).andDo(print()).andExpect(status().isOk())
        		.andExpect(jsonPath(RESPONSE_STATE).value(HttpStatus.BAD_REQUEST.value()));
    }
    
    @Test
    public void testInfoIpBadRequestForIpBlockActually() throws Exception {
    	blacklistRepository.deleteAll();
		this.createBanList();
        mvc.perform(get(URI.create(GENERAL_PATH + PATH_INFO_IP + GENERAL_PATH_IP_ADDRESS + TEST_IP))).andDo(print()).andExpect(status().isOk())
        		.andExpect(jsonPath(RESPONSE_STATE).value(HttpStatus.BAD_REQUEST.value()));
    }
    
    @Test
    public void testInfoIpOkRequest() throws Exception {
    	blacklistRepository.deleteAll();
    	IpInfoDTO ipInfo = this.consumeFirstApi();
        CountryDTO countryInfo = this.consumeSecondApi();
        CurrencyCountryDTO currencyCountryInfo = this.consumeThirdApi();
        when(this.obtainInfoIpProvider.consumeApiGetInfoIp(TEST_IP)).thenReturn(ipInfo);
        when(this.obtainInfoCountryProvider.consumeApiGetInfoCountry(ipInfo.getCountry_code())).thenReturn(countryInfo);
        when(this.obtainInfoCountryCurrencyProvider.consumeApiGetInfoCountryConcurrency(ipInfo.getCurrency().getCode())).thenReturn(currencyCountryInfo);
        mvc.perform(get(URI.create(GENERAL_PATH + PATH_INFO_IP + GENERAL_PATH_IP_ADDRESS + TEST_IP))).andDo(print()).andExpect(status().isOk())
        		.andExpect(jsonPath(RESPONSE_STATE).value(HttpStatus.OK.value()));
    }
}

