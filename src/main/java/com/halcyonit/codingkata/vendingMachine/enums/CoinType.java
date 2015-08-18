package com.halcyonit.codingkata.vendingMachine.enums;


public enum CoinType {

	PENNY(0.01), NICKEL(0.5), DIME(0.10), QUARTER(0.25);

	/** The coin value. */
	private Double coinValue;

	/**
	 * Value.
	 *
	 * @return the double
	 */
	public Double value() {
		return coinValue;
	}

	/**
	 * Instantiates a new coin type.
	 *
	 * @param value the value
	 */
	CoinType(final Double value) {
		coinValue = value;
	}


}
