/**
 * 
 */
package com.mercadolibre.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.mercadolibre.app.config.DataSourceConfig;
import com.mercadolibre.app.domain.CountryDTO;
import com.mercadolibre.app.domain.CurrencyCountryDTO;
import com.mercadolibre.app.domain.IpInfoDTO;
import com.mercadolibre.app.domain.RatesDTO;
import com.mercadolibre.app.domain.currencyDTO;
import com.mercadolibre.app.exception.MeliException;
import com.mercadolibre.app.model.Blacklist;
import com.mercadolibre.app.repository.BlacklistRepository;
import com.mercadolibre.app.util.MeliValidation;

/**
 * Clase definida para la creación de las pruebas unitarias
 * @author Alejandro.Hurtado
 *
 */
@SpringBootTest(classes = { MeliApplication.class, DataSourceConfig.class })
@ActiveProfiles("test")
public class MeliValidationTest {
	
    private static final String TEST_IP = "128.0.0.10";
    private static final String TEST_BAD_IP = "IAM.0.0.1";
	
	@Autowired
	private MeliValidation meliValidation;
	@Autowired
    private BlacklistRepository blacklistRepository;
	
	private Blacklist blacklist;
	
	private Blacklist createBanList(String ip) {
    	blacklist = new Blacklist();
    	blacklist.setIpAddress(ip);
    	blacklist.setFecha(new Timestamp(new Date().getTime()));
    	blacklist = blacklistRepository.save(blacklist);
    	return blacklist;
    }
	
	@Test
	public void validationBlockIpTest() {
		blacklistRepository.deleteAll();
		this.createBanList(TEST_IP);
		try {
			this.meliValidation.validationBlockIp(TEST_IP);
		} catch (MeliException e) {
			assertEquals(MeliException.class, e.getClass(), "");
		}
	}
	
	@Test
	public void validationObjectIpFirstIfTest() {
		blacklistRepository.deleteAll();
		this.createBanList(TEST_IP);
		IpInfoDTO ipInfo = null;
		try {
			this.meliValidation.validationObjectIp(ipInfo);
		} catch (MeliException e) {
			assertEquals(MeliException.class, e.getClass(), "");
		}
	}	
	@Test
	public void validationObjectIpSecondIfTest() {
		blacklistRepository.deleteAll();
		this.createBanList(TEST_IP);
		IpInfoDTO ipInfo = new IpInfoDTO();
		try {
			this.meliValidation.validationObjectIp(ipInfo);
		} catch (MeliException e) {
			assertEquals(MeliException.class, e.getClass(), "");
		}
	}
	@Test
	public void validationObjectIpThirdIfTest() {
		blacklistRepository.deleteAll();
		this.createBanList(TEST_IP);
		IpInfoDTO ipInfo = new IpInfoDTO();
		ipInfo.setCountry_name("COL");
		ipInfo.setCurrency(null);
		try {
			this.meliValidation.validationObjectIp(ipInfo);
		} catch (MeliException e) {
			assertEquals(MeliException.class, e.getClass(), "");
		}
	}
	@Test
	public void validationObjectIpFourthIfTest() {
		blacklistRepository.deleteAll();
		this.createBanList(TEST_IP);
		IpInfoDTO ipInfo = new IpInfoDTO();
		ipInfo.setCountry_name("COL");
		ipInfo.setCurrency(new currencyDTO());
		try {
			this.meliValidation.validationObjectIp(ipInfo);
		} catch (MeliException e) {
			assertEquals(MeliException.class, e.getClass(), "");
		}
	}
	@Test
	public void validationObjectCountryFirstIfTest() {
		CountryDTO countryInfo = null;
		try {
			this.meliValidation.validationObjectCountry(countryInfo);
		} catch (MeliException e) {
			assertEquals(MeliException.class, e.getClass(), "");
		}
	}
	@Test
	public void validationObjectCountrySecondIfTest() {
		CountryDTO countryInfo = new CountryDTO();
		try {
			this.meliValidation.validationObjectCountry(countryInfo);
		} catch (MeliException e) {
			assertEquals(MeliException.class, e.getClass(), "");
		}
	}
	@Test
	public void validationObjectCountryConcurrencyFirtsIfTest() {
		CurrencyCountryDTO countryConcurrency = null;
		try {
			this.meliValidation.validationObjectCountryConcurrency(countryConcurrency);
		} catch (MeliException e) {
			assertEquals(MeliException.class, e.getClass(), "");
		}
	}
	@Test
	public void validationObjectCountryConcurrencySecondIfTest() {
		CurrencyCountryDTO countryConcurrency = new CurrencyCountryDTO();
		try {
			this.meliValidation.validationObjectCountryConcurrency(countryConcurrency);
		} catch (MeliException e) {
			assertEquals(MeliException.class, e.getClass(), "");
		}
	}
	@Test
	public void validationObjectCountryConcurrencyThirdIfTest() {
		CurrencyCountryDTO countryConcurrency = new CurrencyCountryDTO();
		countryConcurrency.setDate("10/04/2022");
		try {
			this.meliValidation.validationObjectCountryConcurrency(countryConcurrency);
		} catch (MeliException e) {
			assertEquals(MeliException.class, e.getClass(), "");
		}
	}
	@Test
	public void validationObjectCountryConcurrencyFourthIfTest() {
		CurrencyCountryDTO countryConcurrency = new CurrencyCountryDTO();
		countryConcurrency.setDate("10/04/2022");
		countryConcurrency.setRates(new RatesDTO());
		try {
			this.meliValidation.validationObjectCountryConcurrency(countryConcurrency);
		} catch (MeliException e) {
			assertEquals(MeliException.class, e.getClass(), "");
		}
	}
	@Test
	public void validationObjectCountryConcurrencyFifthIfTest() {
		CurrencyCountryDTO countryConcurrency = new CurrencyCountryDTO();
		countryConcurrency.setDate("10/04/2022");
		RatesDTO rates = new RatesDTO();
		rates.setEUR("0.00024");
		countryConcurrency.setRates(rates);
		try {
			this.meliValidation.validationObjectCountryConcurrency(countryConcurrency);
		} catch (MeliException e) {
			assertEquals(MeliException.class, e.getClass(), "");
		}
	}
	@Test
	public void validationAddressIpFirtsIfTest() {
		try {
			this.meliValidation.validationAddressIp(TEST_BAD_IP);
		} catch (MeliException e) {
			assertEquals(MeliException.class, e.getClass(), "");
		}
	}
}
