package com.halcyonit.codingkata.vendingMachine;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.halcyonit.codingkata.vendingMachine.domain.Coin;
import com.halcyonit.codingkata.vendingMachine.domain.Product;
import com.halcyonit.codingkata.vendingMachine.enums.CoinType;
import com.halcyonit.codingkata.vendingMachine.exception.InvalidCoinException;
import com.halcyonit.codingkata.vendingMachine.exception.NotSufficientBalance;
import com.halcyonit.codingkata.vendingMachine.exception.ProductOutOfStockException;
import com.halcyonit.codingkata.vendingMachine.exception.UnableToMakeChange;
import com.halcyonit.codingkata.vendingMachine.processor.CoinProcessor;
import com.halcyonit.codingkata.vendingMachine.processor.ProductProcessor;
import com.halcyonit.codingkata.vendingMachine.validator.CoinValidator;

public class TestSelectProduct {
	
	private VendingMachine machine;
	private CoinProcessor coinProcessor;
	private ProductProcessor productProcessor;
	private CoinValidator coinValidator;
	private MachineState machineState;
	List<Product> products;

	@Before
	public void setUp() {
		coinValidator = new CoinValidator();
		coinProcessor = new CoinProcessor(coinValidator);
		productProcessor = new ProductProcessor();
		machineState = new MachineState();
		machine = new VendingMachine(coinProcessor,productProcessor, machineState);
		products=new ArrayList();
		products.add(new Product(111, "Cola", 1.00));
		products.add(new Product(222, "Chips", 0.50));
		products.add(new Product(222, "Candy", 0.65));
		products.add(new Product(111, "Cola", 1.00));
		products.add(new Product(222, "Chips", 0.50));
		products.add(new Product(555, "INVALID", 0.58));
		machine.loadProducts(products);
	}

	@Test
	public void testSelectColaProduct() throws InvalidCoinException, NotSufficientBalance, ProductOutOfStockException, UnableToMakeChange {
		final Coin coin = new Coin(CoinType.NICKEL);
		machine.acceptCoin(coin, 3);
		machine.selectProduct(products.get(0));
		assertNull(machine.getMachineState().getSelectedValidProduct());
	}
	
	@Test
	public void testSelectCandyProduct() throws InvalidCoinException, NotSufficientBalance, ProductOutOfStockException, UnableToMakeChange {
		Coin coin = new Coin(CoinType.NICKEL);
		machine.acceptCoin(coin, 1);
		coin = new Coin(CoinType.QUARTER);
		machine.acceptCoin(coin, 1);
		machine.selectProduct(products.get(2));
		assertNull(machine.getMachineState().getSelectedValidProduct());
	}
	
	@Test(expected=NotSufficientBalance.class)
	public void testSelectProductWithNotEnoughAmount() throws InvalidCoinException, NotSufficientBalance, ProductOutOfStockException, UnableToMakeChange {
		Coin coin = new Coin(CoinType.QUARTER);
		machine.acceptCoin(coin, 1);
		machine.selectProduct(products.get(0));		
	}
	
	@Test
	public void testSelectCandyProductForCorrectChange() throws InvalidCoinException, NotSufficientBalance, ProductOutOfStockException, UnableToMakeChange {
		Coin coin = new Coin(CoinType.NICKEL);
		machine.acceptCoin(coin, 1);
		coin = new Coin(CoinType.QUARTER);
		machine.acceptCoin(coin, 3);
		machine.selectProduct(products.get(2));
		assertNull(machine.getMachineState().getSelectedValidProduct());
		//dime
		assertSame(1,machine.getMachineState().getArrayReturnCoins()[2]);
		//nickel
		assertSame(1,machine.getMachineState().getArrayReturnCoins()[0]);
	}
	
	@Test(expected=ProductOutOfStockException.class)
	public void testSelectProductOutOfStock() throws InvalidCoinException, NotSufficientBalance, ProductOutOfStockException, UnableToMakeChange {
		final Coin coin = new Coin(CoinType.NICKEL);
		machine.acceptCoin(coin, 3);
		machine.selectProduct(new Product(444, "NOPE", 0.50));
		assertNull(machine.getMachineState().getSelectedValidProduct());
	}
	
	@Test(expected=UnableToMakeChange.class)
	public void testSelectProductExactChangeProblem() throws InvalidCoinException, NotSufficientBalance, ProductOutOfStockException, UnableToMakeChange {
		final Coin coin = new Coin(CoinType.NICKEL);
		machine.acceptCoin(coin, 3);
		machine.selectProduct(products.get(5));
		assertNull(machine.getMachineState().getSelectedValidProduct());
	}

}
