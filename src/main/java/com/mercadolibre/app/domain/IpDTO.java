/**
 * 
 */
package com.mercadolibre.app.domain;

import java.io.Serializable;

/**
 * @author Alejandro.Hurtado
 *
 */
public class IpDTO implements Serializable{

	/**
	 * Serializer
	 */
	private static final long serialVersionUID = -3955077922179895368L;
	/** Id Blacklist*/
	private Long blacklistId;
	/** Dirección Ip*/
	private String ipAddress;
	
	/**
	 * @param blacklistId
	 * @param ipAddress
	 */
	public IpDTO(Long blacklistId, String ipAddress) {
		super();
		this.blacklistId = blacklistId;
		this.ipAddress = ipAddress;
	}
	/**
	 * @return the blacklistId
	 */
	public Long getBlacklistId() {
		return blacklistId;
	}
	/**
	 * @param blacklistId the blacklistId to set
	 */
	public void setBlacklistId(Long blacklistId) {
		this.blacklistId = blacklistId;
	}
	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
