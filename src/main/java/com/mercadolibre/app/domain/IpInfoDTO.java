/**
 * 
 */
package com.mercadolibre.app.domain;

import java.io.Serializable;

/**
 * 
 * Clase que define un DTO para el mapeo de la informacion de la IP
 * @author Alejandro.Hurtado
 *
 */
public class IpInfoDTO implements Serializable {

	/**
	 * Serializer
	 */
	private static final long serialVersionUID = -2420593166525993881L;
	/** Nombre del país*/
	private String country_name;
	/** Código del país*/
	private String country_code;
	/** Nombre del continente donde está ubicado el país*/
	private String continent_name;
	/** Código del continente donde está ubicado el país*/
	private String continent_code;
	/** Ciudad principal*/
	private String city;
	/** Latitud*/
	private String latitude;
	/** Longitud*/
	private String longitude;
	/** Objeto con información de la moneda*/
	private currencyDTO currency;
	
	/**
	 * @return the country_name
	 */
	public String getCountry_name() {
		return country_name;
	}
	/**
	 * @param country_name the country_name to set
	 */
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	/**
	 * @return the country_code
	 */
	public String getCountry_code() {
		return country_code;
	}
	/**
	 * @param country_code the country_code to set
	 */
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	/**
	 * @return the continent_name
	 */
	public String getContinent_name() {
		return continent_name;
	}
	/**
	 * @param continent_name the continent_name to set
	 */
	public void setContinent_name(String continent_name) {
		this.continent_name = continent_name;
	}
	/**
	 * @return the continent_code
	 */
	public String getContinent_code() {
		return continent_code;
	}
	/**
	 * @param continent_code the continent_code to set
	 */
	public void setContinent_code(String continent_code) {
		this.continent_code = continent_code;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the currency
	 */
	public currencyDTO getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(currencyDTO currency) {
		this.currency = currency;
	}
}
