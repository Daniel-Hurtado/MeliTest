/**
 * 
 */
package com.mercadolibre.app.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Alejandro.Hurtado
 *
 */
@Component
@ConfigurationProperties(prefix = "meli")
public class MeliProperties {
	
	/** Propiedad que contiene la URL del API de obtener información mediante IP*/
    private String urlApiIp;
    /** Propiedad que contiene el access key del API de obtener información mediante IP*/
    private String accessKeyIp;
    
    /** Propiedad que contiene la URL del API de obtener información de un país*/
    private String urlApiInfoCountry;
    /** Propiedad que contiene el access key del API de obtener información de un país*/
    private String accessKeyInfoCountry;
    
    /** Propiedad que contiene la URL del API de obtener información mediante IP*/
    private String urlApiInfoCurrencyCountry;
    /** Propiedad que contiene el access key del API de obtener información mediante IP*/
    private String accessKeyInfoCurrencyCountry;
    
	/**
	 * @return the urlApiIp
	 */
	public String getUrlApiIp() {
		return urlApiIp;
	}
	/**
	 * @param urlApiIp the urlApiIp to set
	 */
	public void setUrlApiIp(String urlApiIp) {
		this.urlApiIp = urlApiIp;
	}
	/**
	 * @return the accessKeyIp
	 */
	public String getAccessKeyIp() {
		return accessKeyIp;
	}
	/**
	 * @param accessKeyIp the accessKeyIp to set
	 */
	public void setAccessKeyIp(String accessKeyIp) {
		this.accessKeyIp = accessKeyIp;
	}
	/**
	 * @return the urlApiInfoCountry
	 */
	public String getUrlApiInfoCountry() {
		return urlApiInfoCountry;
	}
	/**
	 * @param urlApiInfoCountry the urlApiInfoCountry to set
	 */
	public void setUrlApiInfoCountry(String urlApiInfoCountry) {
		this.urlApiInfoCountry = urlApiInfoCountry;
	}
	/**
	 * @return the accessKeyInfoCountry
	 */
	public String getAccessKeyInfoCountry() {
		return accessKeyInfoCountry;
	}
	/**
	 * @param accessKeyInfoCountry the accessKeyInfoCountry to set
	 */
	public void setAccessKeyInfoCountry(String accessKeyInfoCountry) {
		this.accessKeyInfoCountry = accessKeyInfoCountry;
	}
	/**
	 * @return the urlApiInfoCurrencyCountry
	 */
	public String getUrlApiInfoCurrencyCountry() {
		return urlApiInfoCurrencyCountry;
	}
	/**
	 * @param urlApiInfoCurrencyCountry the urlApiInfoCurrencyCountry to set
	 */
	public void setUrlApiInfoCurrencyCountry(String urlApiInfoCurrencyCountry) {
		this.urlApiInfoCurrencyCountry = urlApiInfoCurrencyCountry;
	}
	/**
	 * @return the accessKeyInfoCurrencyCountry
	 */
	public String getAccessKeyInfoCurrencyCountry() {
		return accessKeyInfoCurrencyCountry;
	}
	/**
	 * @param accessKeyInfoCurrencyCountry the accessKeyInfoCurrencyCountry to set
	 */
	public void setAccessKeyInfoCurrencyCountry(String accessKeyInfoCurrencyCountry) {
		this.accessKeyInfoCurrencyCountry = accessKeyInfoCurrencyCountry;
	}
}
