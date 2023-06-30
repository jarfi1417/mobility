package com.instantsystem.mobilityapi.parking.exception;

/**
 * Exception class for Mobility
 * 
 * @author jearfi
 */
public class MobilityException extends Exception {

    private static final long serialVersionUID = 7153334308461532636L;

    /**
     * Constructor
     * 
     * @param message the exception message
     */
    public MobilityException(String message) {
        super(message);
    }

    /**
     * Constructor
     * 
     * @param message the exception message
     * @param throwable the exception
     */
    public MobilityException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
