/**
 * 
 */
package com.mercadolibre.app.business;

import com.mercadolibre.app.domain.InfoCompleteDTO;
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
}
