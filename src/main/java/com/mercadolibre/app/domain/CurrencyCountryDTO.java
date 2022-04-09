/**
 * 
 */
package com.mercadolibre.app.domain;

import java.io.Serializable;

/**
 * 
 * Clase que define un DTO para el mapeo de la informacion de la moneda de un país
 * @author Alejandro.Hurtado
 *
 */
public class CurrencyCountryDTO implements Serializable{

	/**
	 * Serializer
	 */
	private static final long serialVersionUID = -7092449724379764404L;
	/** Fecha de la consulta de la capitalización */
	private String date; 
	/** Clase que contiene la información de la capitalización en USD y EUR*/
	private RatesDTO rates;
	
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the rates
	 */
	public RatesDTO getRates() {
		return rates;
	}
	/**
	 * @param rates the rates to set
	 */
	public void setRates(RatesDTO rates) {
		this.rates = rates;
	} 
}
