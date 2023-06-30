package com.instantsystem.mobilityapi.parking.exception;

/**
 * Exception class when the request is not valid
 * 
 * @author jearfi
 */
public class MobilityNotValidException extends MobilityException {

    private static final long serialVersionUID = 7590602765293582948L;

    /**
     * Constructor
     * 
     * @param message the exception message
     */
    public MobilityNotValidException(String message) {
        super(message);
    }

    /**
     * Constructor
     * 
     * @param message the exception message
     * @param throwable the exception
     */
    public MobilityNotValidException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
