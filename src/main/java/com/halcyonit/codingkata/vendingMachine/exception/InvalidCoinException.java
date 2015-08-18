package com.halcyonit.codingkata.vendingMachine.exception;

/**
 * The Class InvalidCoinException.
 */
public class InvalidCoinException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The message. */
    private String message;

    /**
     * Instantiates a new invalid coin exception.
     *
     * @param message the message
     */
    public InvalidCoinException(final String message) {
        super(message);
        this.message = message;
    }

    /**
     * Instantiates a new invalid coin exception.
     */
    public InvalidCoinException() {
        this("Invalid Coin!, Insert nickels, dimes or quarters");
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#toString()
     */
    @Override
    public String toString() {
        return message;
    }

}