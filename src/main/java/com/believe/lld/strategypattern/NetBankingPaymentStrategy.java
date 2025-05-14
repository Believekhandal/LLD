package com.believe.lld.strategypattern;

public class NetBankingPaymentStrategy implements PaymentStrategy {

	@Override
	public void pay(double paymentAmount) {
		// TODO Auto-generated method stub
		System.out.println("processing payment via netbank amount: " + paymentAmount);

	}

}
