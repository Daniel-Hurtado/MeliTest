/**
 * 
 */
package com.mercadolibre.app.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.app.business.MeliBusiness;
import com.mercadolibre.app.domain.InfoCompleteDTO;
import com.mercadolibre.app.model.Blacklist;
import com.mercadolibre.app.util.Response;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

/**
 * Rest Controller que permite exponer las Apis.
 * 
 * @author Alejandro.Hurtado
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET })
public class MeliRest {
	
	private static final String GET_INFO_IP_SERVICE = "getInfoIp";
	private static final String BAN_IP_SERVICE = "banIp";
	private static final String LIST_BAN_IP_SERVICE = "listBanIp";
	private static final String DESBAN_IP_SERVICE = "desbanIp";
	private static final String DELETE_BAN_IP_SERVICE = "deleteBanIp";
	/** Objeto para la ejecucion de las operaciones de negocio */
    private final MeliBusiness business;

    /**
     * Metodo constructor
     */
    public MeliRest(MeliBusiness business) {
        this.business = business;
    }
    
    @GetMapping(value = "/info-ip")
    @CircuitBreaker(name=GET_INFO_IP_SERVICE, fallbackMethod = "fallbackService")
    public Response<InfoCompleteDTO> getInfoIp(
            @RequestParam(value = "Ip-Address", required = true) String ip) {
        return this.business.validateIp(ip);
    }
    
    @PostMapping(value = "/ban-ip", produces = MediaType.APPLICATION_JSON_VALUE)
    @CircuitBreaker(name=BAN_IP_SERVICE, fallbackMethod = "fallbackService")
    public Response<Void> banIp(
            @RequestParam(value = "Ip-Address", required = true) String ip) {
        return this.business.banIp(ip);
    }
    
    @GetMapping(value = "/list-ban-ip")
    @CircuitBreaker(name=LIST_BAN_IP_SERVICE, fallbackMethod = "fallbackService")
    public Response<List<Blacklist>> listBanIp() {
        return this.business.listBanIp();
    }
    
    @PutMapping(value = "/modify-ban-ip")
    @CircuitBreaker(name=DESBAN_IP_SERVICE, fallbackMethod = "fallbackService")
    public Response<Void> desbanIp(
    		@RequestParam(value = "Blacklist-Id", required = true) Long blacklistId,
            @RequestParam(value = "New-Ip", required = true) String newIp) {
        return this.business.modifyBanIp(blacklistId, newIp);
    }
    
    @DeleteMapping(value = "/delete-ip")
    @CircuitBreaker(name=DELETE_BAN_IP_SERVICE, fallbackMethod = "fallbackService")
    public Response<Void> deleteBanIp(
    		@RequestParam(value = "Blacklist-Id", required = true) Long blacklistId) {
        return this.business.deleteBanIp(blacklistId);
    }
    
    public Response<List<Blacklist>> fallbackService(Exception e) {
        return null;
    }
}
