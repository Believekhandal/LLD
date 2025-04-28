package com.believe.lld.notificationservice;

public class PushNotificationSender implements NotificationSender {
	@Override
	public void sendNotification(User user, String message) {
		System.out.println("Sending PUSH notification to " + user.getDeviceToken() + ": " + message);
	}
}