/**
 * 
 */
package com.mercadolibre.app.business;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mercadolibre.app.exception.MeliException;
import com.mercadolibre.app.util.Response;

/**
 * Clase encargada de implementar la lógica de negocio.
 * 
 * @author Alejandro.Hurtado
 *
 */
@Service
public class MeliBusinessImpl implements MeliBusiness{

	/** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(MeliBusinessImpl.class);
    
    /**
     * @see MeliBusiness#validateIp(String, UserDTO, String)
     */
    //@Override
    public Response<Void> validateIp(String ip) {
        LOGGER.debug("Init validateIp with request: {}", ip);
        Response<Void> response = null;
        try {
            
            /*response = new Response<>(HttpStatus.OK.value(), this.message.getMessage(UIM089), this.message.getMessage(DIM086), EMPTY_STRING,
                    EMPTY_STRING, null);
        } catch (MeliException e) {
            //response = new Response<>(e.getStatus(), e.getUserMessage(), e.getMessage(), e.getErrorCode(), EMPTY_STRING);*/
        } catch (Exception e) {
            /*response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), this.message.getMessage(UEM131),
                    this.message.getMessage(DEM136), DEM136, EMPTY_STRING);*/
        } 
        LOGGER.debug("Finish validateIp with response: {}", response);

        return response;
    }
}
