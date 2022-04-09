/**
 * 
 */
package com.mercadolibre.app.domain;

import java.io.Serializable;

/**
 * 
 * Clase que define un DTO para el mapeo de la informacion de la capitalización
 * @author Alejandro.Hurtado
 *
 */
public class RatesDTO implements Serializable{

	/**
	 * Serializer
	 */
	private static final long serialVersionUID = -5284504528317529840L;
	/** Capitalización en EUR*/
	private String EUR;
	/** Capitalización en USD*/
	private String USD;
	
	/**
	 * @return the eUR
	 */
	public String getEUR() {
		return EUR;
	}
	/**
	 * @param eUR the eUR to set
	 */
	public void setEUR(String eUR) {
		EUR = eUR;
	}
	/**
	 * @return the uSD
	 */
	public String getUSD() {
		return USD;
	}
	/**
	 * @param uSD the uSD to set
	 */
	public void setUSD(String uSD) {
		USD = uSD;
	}
	
}
