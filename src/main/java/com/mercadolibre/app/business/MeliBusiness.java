/**
 * 
 */
package com.mercadolibre.app.business;

/**
 * 
 * Interface donde se definen las operaciones a implementar.
 * @author Alejandro.Hurtado
 *
 */
public interface MeliBusiness {
	
	/**
     * Permite actualizar la información del perfil de un usuario
     *
     * @param token
     *            Token de autorizacion que contiene la informacion del usuario en la sesion actual
     * @param userInfo
     *            Objeto con la informacion del usuario
     * @param ip
     *            donde se realiza la solicitud
     
    Response<Void> updateUserInfo(String token, UpdateUserDTO userInfo, String ip);*/
}
