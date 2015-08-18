package com.halcyonit.codingkata.vendingMachine;

import com.halcyonit.codingkata.vendingMachine.domain.Coin;
import com.halcyonit.codingkata.vendingMachine.domain.Product;
import com.halcyonit.codingkata.vendingMachine.observers.DispenseProductListener;
import com.halcyonit.codingkata.vendingMachine.observers.DisplayUnitListener;
import com.halcyonit.codingkata.vendingMachine.observers.Listener;
import com.halcyonit.codingkata.vendingMachine.observers.ReturnCoinListener;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


/**
 * The Class MachineState is implementation of state pattern.
 */
public class MachineState {


    /** The listeners. */
    List<Listener> listeners = new ArrayList<Listener>();

    /** The amount. */
    private Double amount = 0.0;

    /** The return amount. */
    private Double returnAmount = 0.0;

    /** The array input coins. */
    private int[] arrayInputCoins = {0, 0, 0};

    /** The array return coins. */
    private int[] arrayReturnCoins = {0, 0, 0};

    /** The occured exception. */
    private Exception occuredException = null;

    /** The input coin. */
    private Coin inputCoin;

    /** The number of input coins. */
    private int numberOfInputCoins;

    /** The available products. */
    private List<Product> availableProducts = new ArrayList();


    /** The selected valid product. */
    private Product selectedValidProduct;

    /**
     * Gets the available products.
     *
     * @return the available products
     */
    public List<Product> getAvailableProducts() {
        return availableProducts;
    }

    /**
     * Instantiates a new machine state.
     */
    public MachineState() {
        listeners.add(new DisplayUnitListener());
        listeners.add(new ReturnCoinListener());
        listeners.add(new DispenseProductListener());
    }

    /**
     * This method changes the state of the given Context parameter.
     *
     * @param vendingMachine the vending machine
     * @param state the state
     */
    protected void changeState(VendingMachine vendingMachine, MachineState state) {
        vendingMachine.changeState(state);
        for (Listener listener : listeners) {
            listener.listen(state, vendingMachine);
        }
    }

    /**
     * This method handles a request from a Context instance.
     *
     * @param vendingMachine the vending machine
     */
    public void handle(VendingMachine vendingMachine) {
        changeState(vendingMachine, vendingMachine.getMachineState());
    }

    /**
     * Gets the amount.
     *
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     *
     * @param amount the new amount
     */
    public void setAmount(Double amount) {
        this.amount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Gets the occured exception.
     *
     * @return the occured exception
     */
    public Exception getOccuredException() {
        return occuredException;
    }

    /**
     * Sets the occured exception.
     *
     * @param occuredException the new occured exception
     */
    public void setOccuredException(Exception occuredException) {
        this.occuredException = occuredException;
    }

    /**
     * Gets the input coin.
     *
     * @return the input coin
     */
    public Coin getInputCoin() {
        return inputCoin;
    }

    /**
     * Sets the input coin.
     *
     * @param inputCoin the new input coin
     */
    public void setInputCoin(Coin inputCoin) {
        this.inputCoin = inputCoin;
    }

    /**
     * Gets the number of input coins.
     *
     * @return the number of input coins
     */
    public int getNumberOfInputCoins() {
        return numberOfInputCoins;
    }

    /**
     * Sets the number of input coins.
     *
     * @param numberOfInputCoins the new number of input coins
     */
    public void setNumberOfInputCoins(int numberOfInputCoins) {
        this.numberOfInputCoins = numberOfInputCoins;
    }

    /**
     * Gets the selected valid product.
     *
     * @return the selected valid product
     */
    public Product getSelectedValidProduct() {
        return selectedValidProduct;
    }

    /**
     * Sets the selected valid product.
     *
     * @param selectedValidProduct the new selected valid product
     */
    public void setSelectedValidProduct(Product selectedValidProduct) {
        this.selectedValidProduct = selectedValidProduct;
    }

    /**
     * Gets the return amount.
     *
     * @return the return amount
     */
    public Double getReturnAmount() {
        return returnAmount;
    }

    /**
     * Sets the return amount.
     *
     * @param returnAmount the new return amount
     */
    public void setReturnAmount(Double returnAmount) {
        this.returnAmount = returnAmount;
        this.amount = 0.0;
    }

    /**
     * Gets the array return coins.
     *
     * @return the array return coins
     */
    public int[] getArrayReturnCoins() {
        return arrayReturnCoins;
    }

    /**
     * Sets the array return coins.
     *
     * @param arrayReturnCoins the new array return coins
     */
    public void setArrayReturnCoins(int[] arrayReturnCoins) {
        this.arrayReturnCoins = arrayReturnCoins;
    }

    /**
     * Gets the array input coins.
     *
     * @return the array input coins
     */
    public int[] getArrayInputCoins() {
        return arrayInputCoins;
    }

    /**
     * Sets the array input coins.
     *
     * @param arrayInputCoins the new array input coins
     */
    public void setArrayInputCoins(int[] arrayInputCoins) {
        this.arrayInputCoins = arrayInputCoins;
    }


}
