package com.halcyonit.codingkata.vendingMachine.enums;

public enum ErrorCodes {

    INVALID_NUMBER_OF_COIN("Please insert valid number of coins!"),
    INVALID_COIN_TYPE("Invalid Coin!, Insert nickels, dimes or quarters"),
    NOT_SUFFICIENT_BALANCE("The current amount as appropriate,Insert more coins!"),
    PRODUCT_OUT_OF_STOCK("Product seems to be out of stock!"),
    EXACT_CHANGE_ONLY("EXACT CHANGE ONLY");
    ;

    String errorMsg;

    ErrorCodes(final String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }


}
