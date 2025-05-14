package com.believe.lld.strategypattern;

public class DebitCardPaymentStrategy implements PaymentStrategy {

	@Override
	public void pay(double paymentAmount) {
		System.out.println("processing payment via debitcard amount: " + paymentAmount);

	}
}
