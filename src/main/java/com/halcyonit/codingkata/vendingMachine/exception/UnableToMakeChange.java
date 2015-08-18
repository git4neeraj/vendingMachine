package com.halcyonit.codingkata.vendingMachine.exception;

/**
 * The Class UnableToMakeChange.
 */
public class UnableToMakeChange extends Exception {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The message. */
    private String message;

    /**
     * Instantiates a new unable to make change.
     *
     * @param message the message
     */
    public UnableToMakeChange(final String message) {
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
