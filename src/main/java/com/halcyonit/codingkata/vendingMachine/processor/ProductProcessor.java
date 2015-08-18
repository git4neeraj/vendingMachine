package com.halcyonit.codingkata.vendingMachine.processor;

import com.halcyonit.codingkata.vendingMachine.domain.Product;
import com.halcyonit.codingkata.vendingMachine.enums.ErrorCodes;
import com.halcyonit.codingkata.vendingMachine.exception.NotSufficientBalance;
import com.halcyonit.codingkata.vendingMachine.exception.ProductOutOfStockException;
import com.halcyonit.codingkata.vendingMachine.exception.UnableToMakeChange;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


/**
 * The Class ProductProcessor processes product related functionalities
 */
public class ProductProcessor {

    /**
     * Instantiates a new product processor.
     */
    public ProductProcessor() {

    }

    /**
     * Select product and deduct amount.
     *
     * @param selectedProduct the selected product
     * @param currentAmount the current amount
     * @param availableProducts the available products
     * @return the product
     * @throws NotSufficientBalance the not sufficient balance
     * @throws ProductOutOfStockException the product out of stock exception
     * @throws UnableToMakeChange the unable to make change
     */
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
        // for all valid coin changes
        if (returnAbleChange[0] >= 0 && returnAbleChange[1] >= 0
                && returnAbleChange[2] >= 0) {
            availableProducts.remove(selectedProduct);

            return selectedProduct;
        }
        return null;
    }

}
