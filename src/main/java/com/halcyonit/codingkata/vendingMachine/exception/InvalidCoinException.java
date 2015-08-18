package com.halcyonit.codingkata.vendingMachine.exception;

public class InvalidCoinException extends Exception {

    private static final long serialVersionUID = 1L;
    private String message;

    public InvalidCoinException(final String message) {
        super(message);
        this.message = message;
    }

    public InvalidCoinException() {
        this("Invalid Coin!, Insert nickels, dimes or quarters");
    }

    @Override
    public String toString() {
        return message;
    }

}