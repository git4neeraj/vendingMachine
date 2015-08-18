package com.halcyonit.codingkata.vendingMachine;

import java.util.List;

import com.halcyonit.codingkata.vendingMachine.domain.Coin;
import com.halcyonit.codingkata.vendingMachine.domain.Product;
import com.halcyonit.codingkata.vendingMachine.enums.CoinType;
import com.halcyonit.codingkata.vendingMachine.exception.InvalidCoinException;
import com.halcyonit.codingkata.vendingMachine.exception.NotSufficientBalance;
import com.halcyonit.codingkata.vendingMachine.exception.ProductOutOfStockException;
import com.halcyonit.codingkata.vendingMachine.exception.UnableToMakeChange;
import com.halcyonit.codingkata.vendingMachine.processor.CoinProcessor;
import com.halcyonit.codingkata.vendingMachine.processor.ProductProcessor;

public class VendingMachine {

	final CoinProcessor coinProcessor;

	MachineState machineState;

	final ProductProcessor productProcessor;

	public VendingMachine(CoinProcessor coinProcessor,
			ProductProcessor productProcessor, MachineState machineState) {
		this.coinProcessor = coinProcessor;
		this.machineState = machineState;
		this.productProcessor = productProcessor;
	}

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
	 */
	public void changeState(MachineState machineState) {
		this.machineState = machineState;
	}

	public MachineState getMachineState() {
		return machineState;
	}

	public void returnCoins(Coin inputCoin, int numberOfInputCoins) {
		if (numberOfInputCoins > 0) {
			display("Returning : coin of coinType: " + inputCoin.getType()
					+ " Number of coins: " + numberOfInputCoins);
		}
	}

	public void display(String message) {
		System.out.println(message);
	}

	public void loadProducts(List<Product> products) {
		machineState.getAvailableProducts().addAll(products);
		machineState.handle(this);
	}

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

	public void dispenseProduct(Product selectedValidProduct) {
		display("Dispensing product:" + selectedValidProduct);
		machineState.setReturnAmount(machineState.getAmount());
		machineState.setSelectedValidProduct(null);
		machineState.handle(this);
		machineState.setSelectedValidProduct(null);
		display("Thank You!");
	}

	public void returnArrayOfCoins() {
		int[] arrayReturnCoins = machineState.getArrayReturnCoins();
		returnCoins(new Coin(CoinType.NICKEL), arrayReturnCoins[0]);
		returnCoins(new Coin(CoinType.QUARTER), arrayReturnCoins[1]);
		returnCoins(new Coin(CoinType.DIME), arrayReturnCoins[2]);
		machineState.setAmount(0.0);
	}

	public void askToReturnCoin() {
		int[] arrayInputCoins = machineState.getArrayInputCoins();
		returnCoins(new Coin(CoinType.NICKEL), arrayInputCoins[0]);
		returnCoins(new Coin(CoinType.QUARTER), arrayInputCoins[1]);
		returnCoins(new Coin(CoinType.DIME), arrayInputCoins[2]);
		int [] newInputCoinArray ={0,0,0};
		machineState.setArrayInputCoins(newInputCoinArray);
		machineState.setAmount(0.0);
		machineState.handle(this);
	}

}
