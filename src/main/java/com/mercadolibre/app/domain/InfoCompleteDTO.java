/**
 * 
 */
package com.mercadolibre.app.domain;

import java.io.Serializable;

/**
 * 
 * Clase que define un DTO para el mapeo de la informacion de respuesta del api
 * @author Alejandro.Hurtado
 *
 */
public class InfoCompleteDTO implements Serializable{

	/**
	 * Serializer
	 */
	private static final long serialVersionUID = -2401863338177222111L;
	/** Nombre del país */
	private String countryName;
	/** código ISO del país */
	private String countryIso;
	/** Moneda del país*/
	private String countryCurrency;
	/** Capitalización en EUR*/
	private String countryCurrencyEUR;
	/** Capitalización en USD*/
	private String countryCurrencyUSD;
	
	/**
	 * 
	 */
	public InfoCompleteDTO() {
		super();
	}
	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return the countryIso
	 */
	public String getCountryIso() {
		return countryIso;
	}
	/**
	 * @param countryIso the countryIso to set
	 */
	public void setCountryIso(String countryIso) {
		this.countryIso = countryIso;
	}
	/**
	 * @return the countryCurrency
	 */
	public String getCountryCurrency() {
		return countryCurrency;
	}
	/**
	 * @param countryCurrency the countryCurrency to set
	 */
	public void setCountryCurrency(String countryCurrency) {
		this.countryCurrency = countryCurrency;
	}
	/**
	 * @return the countryCurrencyEUR
	 */
	public String getCountryCurrencyEUR() {
		return countryCurrencyEUR;
	}
	/**
	 * @param countryCurrencyEUR the countryCurrencyEUR to set
	 */
	public void setCountryCurrencyEUR(String countryCurrencyEUR) {
		this.countryCurrencyEUR = countryCurrencyEUR;
	}
	/**
	 * @return the countryCurrencyUSD
	 */
	public String getCountryCurrencyUSD() {
		return countryCurrencyUSD;
	}
	/**
	 * @param countryCurrencyUSD the countryCurrencyUSD to set
	 */
	public void setCountryCurrencyUSD(String countryCurrencyUSD) {
		this.countryCurrencyUSD = countryCurrencyUSD;
	}
}
