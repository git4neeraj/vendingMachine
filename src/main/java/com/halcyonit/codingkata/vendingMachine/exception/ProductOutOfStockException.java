package com.halcyonit.codingkata.vendingMachine.exception;

/**
 * The Class ProductOutOfStockException.
 */
public class ProductOutOfStockException extends Exception {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The message. */
    private String message;

    /**
     * Instantiates a new product out of stock exception.
     *
     * @param message the message
     */
    public ProductOutOfStockException(final String message) {
        super(message);
        this.message = message;
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#toString()
     */
    @Override
    public String toString() {
        return message;
    }
}
