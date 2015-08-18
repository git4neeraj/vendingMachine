package com.halcyonit.codingkata.vendingMachine.observers;

import com.halcyonit.codingkata.vendingMachine.MachineState;
import com.halcyonit.codingkata.vendingMachine.VendingMachine;
import com.halcyonit.codingkata.vendingMachine.exception.UnableToMakeChange;
import com.halcyonit.codingkata.vendingMachine.processor.CoinProcessor;

/**
 * The listener interface for receiving returnCoin events.
 * The class that is interested in processing a returnCoin
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addReturnCoinListener<code> method. When
 * the returnCoin event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ReturnCoinEvent
 */
public class ReturnCoinListener implements Listener {

    /* (non-Javadoc)
     * @see com.halcyonit.codingkata.vendingMachine.observers.Listener#listen(com.halcyonit.codingkata.vendingMachine.MachineState, com.halcyonit.codingkata.vendingMachine.VendingMachine)
     */
    public void listen(MachineState machineState, VendingMachine vendingMachine) {
        if (machineState.getOccuredException() != null) {
            vendingMachine.returnCoins(machineState.getInputCoin(), machineState.getNumberOfInputCoins());
        } else if (machineState.getReturnAmount() > 0.0 && machineState.getAmount() == 0.0) {
            double returnAmount = machineState.getReturnAmount();
            try {
                machineState.setArrayReturnCoins(new CoinProcessor().getReturningCoins(returnAmount));
                vendingMachine.returnArrayOfCoins();
            } catch (UnableToMakeChange occuredException) {
                machineState.setSelectedValidProduct(null);
                machineState.setOccuredException(occuredException);
            }

        }

    }

}
