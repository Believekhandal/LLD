package com.believe.lld.notificationservice;

public class SMSNotificationSender implements NotificationSender {
	@Override
	public void sendNotification(User user, String message) {
		System.out.println("Sending SMS to " + user.getPhoneNumber() + ": " + message);
	}
}