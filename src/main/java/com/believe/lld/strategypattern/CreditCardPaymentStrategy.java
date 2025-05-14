package com.believe.lld.strategypattern;

public class CreditCardPaymentStrategy implements PaymentStrategy {

	@Override
	public void pay(double paymentAmount) {
		// TODO Auto-generated method stub
		System.out.println("processing payment via creditcard amount: " + paymentAmount);

	}

}
