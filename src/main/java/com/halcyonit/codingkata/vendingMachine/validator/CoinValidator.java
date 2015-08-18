package com.halcyonit.codingkata.vendingMachine.validator;

import java.util.Arrays;

import com.halcyonit.codingkata.vendingMachine.domain.Coin;
import com.halcyonit.codingkata.vendingMachine.enums.CoinType;

/**
 * The Class CoinValidator.
 */
public class CoinValidator {

	/**
	 * Validate coin.
	 *
	 * @param coin the coin
	 * @return true, if successful
	 */
	public boolean validate(Coin coin) {
		CoinType[] validCoinType = { CoinType.DIME, CoinType.NICKEL,
				CoinType.QUARTER };
		return Arrays.asList(validCoinType).contains(coin.getType());
	}

}
