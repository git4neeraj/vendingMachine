package com.halcyonit.codingkata.vendingMachine.processor;

import com.halcyonit.codingkata.vendingMachine.domain.Coin;
import com.halcyonit.codingkata.vendingMachine.enums.CoinType;
import com.halcyonit.codingkata.vendingMachine.enums.ErrorCodes;
import com.halcyonit.codingkata.vendingMachine.exception.InvalidCoinException;
import com.halcyonit.codingkata.vendingMachine.exception.UnableToMakeChange;
import com.halcyonit.codingkata.vendingMachine.validator.CoinValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CoinProcessor {

    CoinValidator coinValidator;

    public CoinProcessor(CoinValidator coinValidator) {
        this.coinValidator = coinValidator;
    }

    public CoinProcessor() {
        super();
    }

    public double evaluateAmount(Coin coin, int numberOfCoins)
            throws InvalidCoinException {
        if (numberOfCoins < 0) {
            throw new InvalidCoinException(
                    ErrorCodes.INVALID_NUMBER_OF_COIN.getErrorMsg());
        } else if (!coinValidator.validate(coin)) {
            throw new InvalidCoinException(
                    ErrorCodes.INVALID_COIN_TYPE.getErrorMsg());
        }

        return new BigDecimal(String.valueOf((double) numberOfCoins
                * coin.getType().value())).setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public int[] getReturningCoins(double returnAmount) throws UnableToMakeChange {

        //Since we are working on double to 2 decimal places converting it to int
        //will help in calculating changes.
        returnAmount = returnAmount * 100;
        int[] arrayReturnCoins = {0, 0, 0};

        int nickleAmount = (int) (CoinType.NICKEL.value().doubleValue() * 100);
        int dimeAmount = (int) (CoinType.DIME.value().doubleValue() * 100);
        int quarterAmount = (int) (CoinType.QUARTER.value().doubleValue() * 100);

        int nickels = (int) (returnAmount / nickleAmount);
        returnAmount = returnAmount % nickleAmount;

        int quarters = (int) (returnAmount / quarterAmount);
        returnAmount = returnAmount % quarterAmount;

        int dimes = (int) (returnAmount / dimeAmount);
        returnAmount = returnAmount % dimeAmount;

        if (returnAmount > 0) {
            throw new UnableToMakeChange(ErrorCodes.EXACT_CHANGE_ONLY.getErrorMsg());
        }

        arrayReturnCoins[0] = nickels;
        arrayReturnCoins[1] = quarters;
        arrayReturnCoins[2] = dimes;

        return arrayReturnCoins;

    }

}
