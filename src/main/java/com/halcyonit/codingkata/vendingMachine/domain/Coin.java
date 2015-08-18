package com.halcyonit.codingkata.vendingMachine.domain;

import com.halcyonit.codingkata.vendingMachine.enums.CoinType;

/**
 * The Class Coin.
 */
public class Coin {

	/** The type. */
	private CoinType type;

	/**
	 * Instantiates a new coin.
	 *
	 * @param coinType the coin type
	 */
	public Coin(final CoinType coinType) {
		this.type = coinType;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public CoinType getType() {
		return type;
	}
}
