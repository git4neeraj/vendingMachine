package com.halcyonit.codingkata.vendingMachine;

import com.halcyonit.codingkata.vendingMachine.domain.Coin;
import com.halcyonit.codingkata.vendingMachine.enums.CoinType;
import com.halcyonit.codingkata.vendingMachine.exception.InvalidCoinException;
import com.halcyonit.codingkata.vendingMachine.processor.CoinProcessor;
import com.halcyonit.codingkata.vendingMachine.processor.ProductProcessor;
import com.halcyonit.codingkata.vendingMachine.validator.CoinValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestVendingMachineCollectCoin {

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
        machine = new VendingMachine(coinProcessor, productProcessor, machineState);
    }

    @Test
    public void testAcceptCoins() throws InvalidCoinException {
        final Coin coin = new Coin(CoinType.NICKEL);
        machine.acceptCoin(coin, 3);
        assertTrue(machine.getMachineState().getAmount() == 1.5);
    }

    @Test
    public void testAcceptDimeCoins() throws InvalidCoinException {
        final Coin coin = new Coin(CoinType.DIME);
        machine.acceptCoin(coin, 3);
        assertTrue(machine.getMachineState().getAmount() == 0.30);
    }

    @Test
    public void testAcceptQuarterCoins() throws InvalidCoinException {
        final Coin coin = new Coin(CoinType.QUARTER);
        machine.acceptCoin(coin, 3);
        assertTrue(machine.getMachineState().getAmount() == 0.75);
    }

    @Test
    public void testBalanaceIncrement() throws InvalidCoinException {
        final Coin nickel = new Coin(CoinType.NICKEL);
        final Coin quarter = new Coin(CoinType.QUARTER);
        final Coin dime = new Coin(CoinType.DIME);
        machine.acceptCoin(nickel, 3);
        machine.acceptCoin(quarter, 3);
        machine.acceptCoin(dime, 3);
        assertTrue(machine.getMachineState().getAmount() == 2.55);
    }

    @Test(expected = InvalidCoinException.class)
    public void testInvalidCoinNumberAcceptCoins() throws InvalidCoinException {
        final Coin coin = new Coin(CoinType.NICKEL);
        machine.acceptCoin(coin, -3);
    }

    @Test(expected = InvalidCoinException.class)
    public void testRejectPenniesInAcceptCoins() throws InvalidCoinException {
        final Coin coin = new Coin(CoinType.PENNY);
        machine.acceptCoin(coin, 3);
    }

    @Test(expected = InvalidCoinException.class)
    public void testBalanaceIncrementWithInvalidCoins() throws InvalidCoinException {
        final Coin nickel = new Coin(CoinType.NICKEL);
        final Coin quarter = new Coin(CoinType.QUARTER);
        final Coin penny = new Coin(CoinType.PENNY);
        machine.acceptCoin(nickel, 3);
        machine.acceptCoin(quarter, 3);
        machine.acceptCoin(penny, 3);
    }

}
