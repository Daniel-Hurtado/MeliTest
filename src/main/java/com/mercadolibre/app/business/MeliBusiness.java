/**
 * 
 */
package com.mercadolibre.app.business;

import java.util.List;

import com.mercadolibre.app.domain.InfoCompleteDTO;
import com.mercadolibre.app.model.Blacklist;
import com.mercadolibre.app.util.Response;

/**
 * 
 * Interface donde se definen las operaciones a implementar.
 * @author Alejandro.Hurtado
 *
 */
public interface MeliBusiness {

	/**
     * Permite obtener la información asociada a una IP
     * @param ip
     *            donde se realiza la solicitud
	 */
	Response<InfoCompleteDTO> validateIp(String ip);
	
	/**
	 * Permite bloquear/ban una dirección IP especifica
	 * @param ip
	 *            donde se realiza la solicitud
	 */
	Response<Void> banIp(String ip);
	
	/**
	 * Permite obtener la lista de Ip bloqueadas
	 */
	Response<List<Blacklist>> listBanIp();
	
	/**
	 * Permite modificar una dirección IP especifica
	 * @param ip
	 *            donde se realiza la solicitud
	 */
	Response<Void> modifyBanIp(Long blacklistId, String newIp);
	
	/**
	 * Permite eliminar el bloqueo a una dirección IP especifica
	 * @param ip
	 *            donde se realiza la solicitud
	 */
	Response<Void> deleteBanIp(Long blacklistId);
}
