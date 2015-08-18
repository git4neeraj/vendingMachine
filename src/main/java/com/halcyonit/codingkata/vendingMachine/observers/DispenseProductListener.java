package com.halcyonit.codingkata.vendingMachine.observers;

import com.halcyonit.codingkata.vendingMachine.MachineState;
import com.halcyonit.codingkata.vendingMachine.VendingMachine;

public class DispenseProductListener implements Listener {

    public void listen(MachineState machineState, VendingMachine vendingMachine) {
        if (machineState.getSelectedValidProduct() != null) {
            vendingMachine.dispenseProduct(machineState.getSelectedValidProduct());
        }

    }

}
