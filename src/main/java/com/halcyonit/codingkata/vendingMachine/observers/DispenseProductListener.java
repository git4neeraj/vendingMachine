package com.halcyonit.codingkata.vendingMachine.observers;

import com.halcyonit.codingkata.vendingMachine.MachineState;
import com.halcyonit.codingkata.vendingMachine.VendingMachine;

/**
 * The listener interface for receiving dispenseProduct events.
 * The class that is interested in processing a dispenseProduct
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addDispenseProductListener<code> method. When
 * the dispenseProduct event occurs, that object's appropriate
 * method is invoked.
 *
 * @see DispenseProductEvent
 */
public class DispenseProductListener implements Listener {

    /* (non-Javadoc)
     * @see com.halcyonit.codingkata.vendingMachine.observers.Listener#listen(com.halcyonit.codingkata.vendingMachine.MachineState, com.halcyonit.codingkata.vendingMachine.VendingMachine)
     */
    public void listen(MachineState machineState, VendingMachine vendingMachine) {
        if (machineState.getSelectedValidProduct() != null) {
            vendingMachine.dispenseProduct(machineState.getSelectedValidProduct());
        }

    }

}
