package com.halcyonit.codingkata.vendingMachine.exception;

public class NotSufficientBalance extends Exception {

    private static final long serialVersionUID = 1L;
    private String message;

    public NotSufficientBalance(final String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
