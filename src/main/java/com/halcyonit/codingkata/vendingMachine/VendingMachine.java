package com.halcyonit.codingkata.vendingMachine;

import com.halcyonit.codingkata.vendingMachine.domain.Coin;
import com.halcyonit.codingkata.vendingMachine.domain.Product;
import com.halcyonit.codingkata.vendingMachine.enums.CoinType;
import com.halcyonit.codingkata.vendingMachine.exception.InvalidCoinException;
import com.halcyonit.codingkata.vendingMachine.exception.NotSufficientBalance;
import com.halcyonit.codingkata.vendingMachine.exception.ProductOutOfStockException;
import com.halcyonit.codingkata.vendingMachine.exception.UnableToMakeChange;
import com.halcyonit.codingkata.vendingMachine.processor.CoinProcessor;
import com.halcyonit.codingkata.vendingMachine.processor.ProductProcessor;

import java.util.List;

/**
 * The Class VendingMachine is implementation of vending machine in real.
 */
public class VendingMachine {

    /** The coin processor. */
    final CoinProcessor coinProcessor;

    /** The machine state. */
    MachineState machineState;

    /** The product processor. */
    final ProductProcessor productProcessor;

    /**
     * Instantiates a new vending machine.
     *
     * @param coinProcessor the coin processor
     * @param productProcessor the product processor
     * @param machineState the machine state
     */
    public VendingMachine(CoinProcessor coinProcessor,
                          ProductProcessor productProcessor, MachineState machineState) {
        this.coinProcessor = coinProcessor;
        this.machineState = machineState;
        this.productProcessor = productProcessor;
    }

    /**
     * Accept coin.
     *
     * @param coin the coin
     * @param numberOfCoins the number of coins
     * @throws InvalidCoinException the invalid coin exception
     */
    public void acceptCoin(Coin coin, int numberOfCoins)
            throws InvalidCoinException {
        double amount = 0.0;
        try {
            amount = coinProcessor.evaluateAmount(coin, numberOfCoins);
            machineState.setAmount(amount + machineState.getAmount());
            switch (coin.getType()) {
                case NICKEL:
                    machineState.getArrayInputCoins()[0] = numberOfCoins;
                    break;
                case QUARTER:
                    machineState.getArrayInputCoins()[1] = numberOfCoins;
                    break;
                case DIME:
                    machineState.getArrayInputCoins()[2] = numberOfCoins;
                    break;
            }
        } catch (InvalidCoinException occuredException) {
            machineState.setOccuredException(occuredException);
            machineState.setInputCoin(coin);
            machineState.setNumberOfInputCoins(numberOfCoins);
            throw occuredException;
        } finally {
            machineState.handle(this);
        }

    }

    /**
     * This method changes the state of the Context instance.
     *
     * @param machineState the machine state
     */
    public void changeState(MachineState machineState) {
        this.machineState = machineState;
    }

    /**
     * Gets the machine state.
     *
     * @return the machine state
     */
    public MachineState getMachineState() {
        return machineState;
    }

    /**
     * Return coins.
     *
     * @param inputCoin the input coin
     * @param numberOfInputCoins the number of input coins
     */
    public void returnCoins(Coin inputCoin, int numberOfInputCoins) {
        if (numberOfInputCoins > 0) {
            display("Returning : coin of coinType: " + inputCoin.getType()
                    + " Number of coins: " + numberOfInputCoins);
        }
    }

    /**
     * Display.
     *
     * @param message the message
     */
    public void display(String message) {
        System.out.println(message);
    }

    /**
     * Load products.
     *
     * @param products the products
     */
    public void loadProducts(List<Product> products) {
        machineState.getAvailableProducts().addAll(products);
        machineState.handle(this);
    }

    /**
     * Select product.
     *
     * @param selectedProduct the selected product
     * @throws NotSufficientBalance the not sufficient balance
     * @throws ProductOutOfStockException the product out of stock exception
     * @throws UnableToMakeChange the unable to make change
     */
    public void selectProduct(Product selectedProduct)
            throws NotSufficientBalance, ProductOutOfStockException, UnableToMakeChange {
        try {
            Product selectedValidProduct = productProcessor
                    .selectProductAndDeductAmount(selectedProduct,
                            machineState.getAmount(),
                            machineState.getAvailableProducts());
            machineState.setSelectedValidProduct(selectedValidProduct);
            machineState.setAmount(machineState.getAmount()
                    - selectedValidProduct.getPrice());
        } catch (NotSufficientBalance exception) {
            display("Price of product is " + selectedProduct.getPrice());
            display(exception.toString());
            throw exception;
        } catch (ProductOutOfStockException exception) {
            display("SOLD OUT");
            display(exception.toString());
            throw exception;
        } catch (UnableToMakeChange exception) {
            display(exception.toString());
            throw exception;
        } finally {

            machineState.handle(this);
        }
    }

    /**
     * Dispense product.
     *
     * @param selectedValidProduct the selected valid product
     */
    public void dispenseProduct(Product selectedValidProduct) {
        display("Dispensing product:" + selectedValidProduct);
        machineState.setReturnAmount(machineState.getAmount());
        machineState.setSelectedValidProduct(null);
        machineState.handle(this);
        machineState.setSelectedValidProduct(null);
        display("Thank You!");
    }

    /**
     * Return array of coins.
     */
    public void returnArrayOfCoins() {
        int[] arrayReturnCoins = machineState.getArrayReturnCoins();
        returnCoins(new Coin(CoinType.NICKEL), arrayReturnCoins[0]);
        returnCoins(new Coin(CoinType.QUARTER), arrayReturnCoins[1]);
        returnCoins(new Coin(CoinType.DIME), arrayReturnCoins[2]);
        machineState.setAmount(0.0);
    }

    /**
     * Ask to return coin.
     */
    public void askToReturnCoin() {
        int[] arrayInputCoins = machineState.getArrayInputCoins();
        returnCoins(new Coin(CoinType.NICKEL), arrayInputCoins[0]);
        returnCoins(new Coin(CoinType.QUARTER), arrayInputCoins[1]);
        returnCoins(new Coin(CoinType.DIME), arrayInputCoins[2]);
        int[] newInputCoinArray = {0, 0, 0};
        machineState.setArrayInputCoins(newInputCoinArray);
        machineState.setAmount(0.0);
        machineState.handle(this);
    }

}
