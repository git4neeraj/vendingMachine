package com.halcyonit.codingkata.vendingMachine.observers;

import com.halcyonit.codingkata.vendingMachine.MachineState;
import com.halcyonit.codingkata.vendingMachine.VendingMachine;

public class DisplayUnitListener implements Listener {

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
