package com.believe.lld.strategypattern;

public class PaymentProcessorFactory {

	public static PaymentStrategy getPaymentProcessor(PaymentMethods paymentMethods) {

		switch (paymentMethods) {
		case PaymentMethods.CREDIT_CARD:
			return new CreditCardPaymentStrategy();
		case PaymentMethods.DEBIT_CARD:
			return	new DebitCardPaymentStrategy();
		case PaymentMethods.NET_BANKING:
			return new NetBankingPaymentStrategy();
		default:
			throw new RuntimeException("Unknown Payment Processor Found");

		}

	}

}
