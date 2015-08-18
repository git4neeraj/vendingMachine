package com.halcyonit.codingkata.vendingMachine.exception;

public class ProductOutOfStockException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;

	public ProductOutOfStockException(final String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
}
