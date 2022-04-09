/**
 * 
 */
package com.mercadolibre.app.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.app.business.MeliBusiness;
import com.mercadolibre.app.domain.InfoCompleteDTO;
import com.mercadolibre.app.util.Response;

/**
 * Rest Controller que permite exponer las Apis.
 * 
 * @author Alejandro.Hurtado
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET })
public class MeliRest {
	
	/** Objeto para la ejecucion de las operaciones de negocio */
    private final MeliBusiness business;

    /**
     * Metodo constructor
     */
    public MeliRest(MeliBusiness business) {
        this.business = business;
    }
    
    @GetMapping(value = "/info-ip")
    public Response<InfoCompleteDTO> getInfoIp(
            @RequestParam(value = "Ip-Address", required = true) String ip) {
        return this.business.validateIp(ip);
    }
}
