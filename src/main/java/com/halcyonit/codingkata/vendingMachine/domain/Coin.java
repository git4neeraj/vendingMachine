package com.halcyonit.codingkata.vendingMachine.domain;

import com.halcyonit.codingkata.vendingMachine.enums.CoinType;

public class Coin {

	private CoinType type;

	public Coin(final CoinType coinType) {
		this.type = coinType;
	}

	public CoinType getType() {
		return type;
	}
}
