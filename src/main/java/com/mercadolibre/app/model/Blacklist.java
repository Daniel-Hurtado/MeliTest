/**
 * 
 */
package com.mercadolibre.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Entidad que representa la tabla blacklist de BD
 * @author Alejandro.Hurtado
 *
 */
@Entity
@Table(name = "blacklist")
public class Blacklist implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8083488421876252168L;
	/** Identificador primario */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blacklist_id")
    private Long blacklistId;
    /** Ip bloqueada */
    @Column(name = "ip_address", nullable = false, length = 45)
    private String ipAddress;
    /** Fecha del bloqueo */
    @Column(name = "date_block", nullable = false, columnDefinition = "TIMESTAMP")
    private Date fecha;
    
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
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
