package com.halcyonit.codingkata.vendingMachine.observers;

import com.halcyonit.codingkata.vendingMachine.MachineState;
import com.halcyonit.codingkata.vendingMachine.VendingMachine;

public interface Listener {

    public void listen(final MachineState machineState, VendingMachine vendingMachine);

}
