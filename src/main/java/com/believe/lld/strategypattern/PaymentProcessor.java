package com.believe.lld.strategypattern;

public class PaymentProcessor {

	PaymentStrategy paymentStrategy;

	public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
		this.paymentStrategy = paymentStrategy;
	}

	public void processPayment(double amt) {
		this.paymentStrategy.pay(amt);
	}
	
}
