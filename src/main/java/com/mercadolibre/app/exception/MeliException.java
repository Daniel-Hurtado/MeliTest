package com.mercadolibre.app.exception;

/**
 * Clase que define una excepción personalizada para el control de errores en la aplicación
 * 
 * @author Alejandro.Hurtado
 *
 */
public class MeliException extends Exception {

    /** Serialize */
    private static final long serialVersionUID = 6365652257268547172L;
    /** Mensaje informativo para el usuario */
    private final String userMessage;
    /** Codigo que define el estado de la transaccion */
    private final int status;
    /** Codigo de error dentro de la aplicacion */
    private final String errorCode;

    /**
     * Metodo constructor
     */
    public MeliException(String developerMessage, String userMessage, int status, String errorCode, Throwable e) {
        super(developerMessage, e);
        this.userMessage = userMessage;
        this.status = status;
        this.errorCode = errorCode;
    }

    /**
     * Método constructor
     */
    public MeliException(String developerMessage, String userMessage, int status, String errorCode) {
        super(developerMessage);
        this.userMessage = userMessage;
        this.status = status;
        this.errorCode = errorCode;
    }

    /**
     * @return the userMessage
     */
    public String getUserMessage() {
        return userMessage;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }
}
