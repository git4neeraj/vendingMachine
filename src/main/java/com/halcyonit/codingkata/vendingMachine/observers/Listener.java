package com.halcyonit.codingkata.vendingMachine.observers;

import com.halcyonit.codingkata.vendingMachine.MachineState;
import com.halcyonit.codingkata.vendingMachine.VendingMachine;


/**
 * The Interface Listener is implementation Observer pattern.
 */
public interface Listener {

    /**
     * Listen to the machine state change event.
     *
     * @param machineState the machine state
     * @param vendingMachine the vending machine
     */
    public void listen(final MachineState machineState, VendingMachine vendingMachine);

}
