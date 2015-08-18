package com.halcyonit.codingkata.vendingMachine.enums;


public enum CoinType {

	PENNY(0.01), NICKEL(0.5), DIME(0.10), QUARTER(0.25);

	private Double coinValue;

	public Double value() {
		return coinValue;
	}

	CoinType(final Double value) {
		coinValue = value;
	}

	
	/*private static List<CoinType> allowedCoinTypes = new ArrayList<CoinType>();

	static {
		allowedCoinTypes.add(NICKEL);
		allowedCoinTypes.add(DIME);
		allowedCoinTypes.add(QUARTER);
	}

	public static List<CoinType> getAllowedCoinTypes() {
		return allowedCoinTypes;
	}*/
}
