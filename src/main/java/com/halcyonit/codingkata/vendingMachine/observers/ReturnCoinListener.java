package com.halcyonit.codingkata.vendingMachine.observers;

import com.halcyonit.codingkata.vendingMachine.MachineState;
import com.halcyonit.codingkata.vendingMachine.VendingMachine;
import com.halcyonit.codingkata.vendingMachine.exception.InvalidCoinException;
import com.halcyonit.codingkata.vendingMachine.exception.UnableToMakeChange;
import com.halcyonit.codingkata.vendingMachine.processor.CoinProcessor;

public class ReturnCoinListener implements Listener {

	public void listen(MachineState machineState, VendingMachine vendingMachine)  {
		if (machineState.getOccuredException() != null) {
			vendingMachine.returnCoins(machineState.getInputCoin(),machineState.getNumberOfInputCoins());
		} else if(machineState.getReturnAmount()>0.0 && machineState.getAmount()==0.0){
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
