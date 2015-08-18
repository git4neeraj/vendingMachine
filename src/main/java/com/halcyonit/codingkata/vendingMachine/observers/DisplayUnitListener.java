package com.halcyonit.codingkata.vendingMachine.observers;

import com.halcyonit.codingkata.vendingMachine.MachineState;
import com.halcyonit.codingkata.vendingMachine.VendingMachine;

/**
 * The listener interface for receiving displayUnit events.
 * The class that is interested in processing a displayUnit
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addDisplayUnitListener<code> method. When
 * the displayUnit event occurs, that object's appropriate
 * method is invoked.
 *
 * @see DisplayUnitEvent
 */
public class DisplayUnitListener implements Listener {

    /* (non-Javadoc)
     * @see com.halcyonit.codingkata.vendingMachine.observers.Listener#listen(com.halcyonit.codingkata.vendingMachine.MachineState, com.halcyonit.codingkata.vendingMachine.VendingMachine)
     */
    public void listen(MachineState machineState, VendingMachine vendingMachine) {
        if (machineState.getAmount() != 0.0) {
            vendingMachine.display("Your Current amount is "
                    + machineState.getAmount());
        }
        if (machineState.getOccuredException() != null) {
            vendingMachine.display(machineState.getOccuredException()
                    .toString());
            if (machineState.getAmount() == 0.0) {
                vendingMachine.display("Please Insert Coin!");
            }
        } else {
            if (machineState.getAmount() == 0.0) {
                vendingMachine.display("Please Insert Coin!");
            }
        }

    }

}
