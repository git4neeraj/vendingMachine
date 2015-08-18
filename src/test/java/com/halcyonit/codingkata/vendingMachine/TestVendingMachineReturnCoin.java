package com.halcyonit.codingkata.vendingMachine;

import com.halcyonit.codingkata.vendingMachine.domain.Coin;
import com.halcyonit.codingkata.vendingMachine.enums.CoinType;
import com.halcyonit.codingkata.vendingMachine.exception.InvalidCoinException;
import com.halcyonit.codingkata.vendingMachine.processor.CoinProcessor;
import com.halcyonit.codingkata.vendingMachine.processor.ProductProcessor;
import com.halcyonit.codingkata.vendingMachine.validator.CoinValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestVendingMachineReturnCoin {

    private VendingMachine machine;
    private CoinProcessor coinProcessor;
    private ProductProcessor productProcessor;
    private CoinValidator coinValidator;
    private MachineState machineState;

    @Before
    public void setUp() {
        coinValidator = new CoinValidator();
        coinProcessor = new CoinProcessor(coinValidator);
        productProcessor = new ProductProcessor();
        machineState = new MachineState();
        machine = new VendingMachine(coinProcessor, productProcessor,
                machineState);
    }

    @Test(expected = InvalidCoinException.class)
    public void testRejectPenniesInAcceptCoins() throws InvalidCoinException {
        final Coin coin = new Coin(CoinType.PENNY);
        machine.acceptCoin(coin, 7);
    }

    @Test
    public void testUserAskToReturnCoins() throws InvalidCoinException {
        Coin coin = new Coin(CoinType.NICKEL);
        machine.acceptCoin(coin, 7);
        coin = new Coin(CoinType.DIME);
        machine.acceptCoin(coin, 3);
        machine.askToReturnCoin();
    }
}
