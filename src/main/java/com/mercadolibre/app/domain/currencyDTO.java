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
public class currencyDTO implements Serializable{

	/**
	 * Serializer
	 */
	private static final long serialVersionUID = -5137337102833350806L;
	/** Código de la moneda del país*/
	private String code;
	/** Nombre de la moneda*/
	private String name;
	/** Nombre en plural*/
	private String plural;
	/** Simbolo*/
	private String symbol;
	/** Simbolo nativo*/
	private String symbol_native;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the plural
	 */
	public String getPlural() {
		return plural;
	}
	/**
	 * @param plural the plural to set
	 */
	public void setPlural(String plural) {
		this.plural = plural;
	}
	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * @return the symbol_native
	 */
	public String getSymbol_native() {
		return symbol_native;
	}
	/**
	 * @param symbol_native the symbol_native to set
	 */
	public void setSymbol_native(String symbol_native) {
		this.symbol_native = symbol_native;
	}
}
