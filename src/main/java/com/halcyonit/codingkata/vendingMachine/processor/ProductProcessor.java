package com.halcyonit.codingkata.vendingMachine.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.halcyonit.codingkata.vendingMachine.domain.Product;
import com.halcyonit.codingkata.vendingMachine.enums.ErrorCodes;
import com.halcyonit.codingkata.vendingMachine.exception.NotSufficientBalance;
import com.halcyonit.codingkata.vendingMachine.exception.ProductOutOfStockException;
import com.halcyonit.codingkata.vendingMachine.exception.UnableToMakeChange;

public class ProductProcessor {

	public ProductProcessor() {

	}

	public Product selectProductAndDeductAmount(Product selectedProduct,
			Double currentAmount, List<Product> availableProducts)
			throws NotSufficientBalance, ProductOutOfStockException,
			UnableToMakeChange {
		if (selectedProduct.getPrice() > currentAmount) {
			throw new NotSufficientBalance(
					ErrorCodes.NOT_SUFFICIENT_BALANCE.getErrorMsg());
		} else if (!availableProducts.contains(selectedProduct)) {
			throw new ProductOutOfStockException(
					ErrorCodes.PRODUCT_OUT_OF_STOCK.getErrorMsg());
		}
		int[] returnAbleChange = new CoinProcessor()
				.getReturningCoins(new BigDecimal(currentAmount
						- selectedProduct.getPrice()).setScale(2,
						RoundingMode.HALF_UP).doubleValue());
		// for valid coin changes
		if (returnAbleChange[0] >= 0 && returnAbleChange[1] >= 0
				&& returnAbleChange[2] >= 0) {
			availableProducts.remove(selectedProduct);

			return selectedProduct;
		}
		return null;
	}

}
