/**
 * 
 */
package com.mercadolibre.app.domain;

import java.io.Serializable;

/**
 * 
 * Clase que define un DTO para el mapeo de la informacion de un país
 * @author Alejandro.Hurtado
 *
 */
public class CountryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7662378189303680757L;
	/** nombre del país */
	private String name;
	/** Código ISO del país */
	private String alpha3Code;
	/** Capital del país */
	private String capital;
	
	/**
	 * 
	 */
	public CountryDTO() {
		super();
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the alpha3Code
	 */
	public String getAlpha3Code() {
		return alpha3Code;
	}
	/**
	 * @param alpha3Code the alpha3Code to set
	 */
	public void setAlpha3Code(String alpha3Code) {
		this.alpha3Code = alpha3Code;
	}
	/**
	 * @return the capital
	 */
	public String getCapital() {
		return capital;
	}
	/**
	 * @param capital the capital to set
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}
	
}
