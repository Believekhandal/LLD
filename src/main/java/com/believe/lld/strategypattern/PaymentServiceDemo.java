package com.believe.lld.strategypattern;

public class PaymentServiceDemo {
	
	
	public static void main(String[] args) {
		PaymentProcessor processor = new PaymentProcessor();
		try {
			PaymentStrategy creditCard =  PaymentProcessorFactory.getPaymentProcessor(PaymentMethods.CREDIT_CARD);
			PaymentStrategy debitCard =  PaymentProcessorFactory.getPaymentProcessor(PaymentMethods.DEBIT_CARD);
			PaymentStrategy netbankingCard =  PaymentProcessorFactory.getPaymentProcessor(PaymentMethods.NET_BANKING);
			
			processor.setPaymentStrategy(creditCard);
			processor.processPayment(12000.0);
			
			processor.setPaymentStrategy(debitCard);
			processor.processPayment(2000.0);
			
			processor.setPaymentStrategy(netbankingCard);
			processor.processPayment(8000.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
