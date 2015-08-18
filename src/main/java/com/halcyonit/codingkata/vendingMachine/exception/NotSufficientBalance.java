package com.halcyonit.codingkata.vendingMachine.exception;

/**
 * The Class NotSufficientBalance.
 */
public class NotSufficientBalance extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The message. */
    private String message;

    /**
     * Instantiates a new not sufficient balance.
     *
     * @param message the message
     */
    public NotSufficientBalance(final String message) {
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
