package com.halcyonit.codingkata.vendingMachine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.halcyonit.codingkata.vendingMachine.domain.Coin;
import com.halcyonit.codingkata.vendingMachine.domain.Product;
import com.halcyonit.codingkata.vendingMachine.observers.DispenseProductListener;
import com.halcyonit.codingkata.vendingMachine.observers.DisplayUnitListener;
import com.halcyonit.codingkata.vendingMachine.observers.Listener;
import com.halcyonit.codingkata.vendingMachine.observers.ReturnCoinListener;

public class MachineState {
	
	
    List<Listener> listeners = new ArrayList<Listener>();
    
    private Double amount = 0.0;
    
    private Double returnAmount = 0.0;
    
    private int [] arrayInputCoins ={0,0,0};
    
    private int [] arrayReturnCoins ={0,0,0};
    
    private Exception occuredException = null ;
    
    private Coin inputCoin;
    
    private int numberOfInputCoins;
    
    private List<Product> availableProducts = new ArrayList();
	
    
    private Product selectedValidProduct;
	
	public List<Product> getAvailableProducts() {
		return availableProducts;
	}

	public MachineState() {
		listeners.add(new DisplayUnitListener());
		listeners.add(new ReturnCoinListener());
		listeners.add(new DispenseProductListener());
	}

	/**
	 * This method changes the state of the given Context parameter.
	 */
	protected void changeState(VendingMachine vendingMachine, MachineState state) {
		vendingMachine.changeState(state);
		for (Listener listener : listeners) {
			listener.listen(state,vendingMachine);
		}
	}
    
	/** 
	 * This method handles a request from a Context instance.
	 */
	public void handle(VendingMachine vendingMachine) {
		changeState(vendingMachine, vendingMachine.getMachineState());
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	public Exception getOccuredException() {
		return occuredException;
	}

	public void setOccuredException(Exception occuredException) {
		this.occuredException = occuredException;
	}

	public Coin getInputCoin() {
		return inputCoin;
	}

	public void setInputCoin(Coin inputCoin) {
		this.inputCoin = inputCoin;
	}

	public int getNumberOfInputCoins() {
		return numberOfInputCoins;
	}

	public void setNumberOfInputCoins(int numberOfInputCoins) {
		this.numberOfInputCoins = numberOfInputCoins;
	}

	public Product getSelectedValidProduct() {
		return selectedValidProduct;
	}

	public void setSelectedValidProduct(Product selectedValidProduct) {
		this.selectedValidProduct = selectedValidProduct;		
	}

	public Double getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
		this.amount = 0.0;
	}

	public int [] getArrayReturnCoins() {
		return arrayReturnCoins;
	}

	public void setArrayReturnCoins(int [] arrayReturnCoins) {
		this.arrayReturnCoins = arrayReturnCoins;
	}

	public int [] getArrayInputCoins() {
		return arrayInputCoins;
	}

	public void setArrayInputCoins(int [] arrayInputCoins) {
		this.arrayInputCoins = arrayInputCoins;
	}

	
}
