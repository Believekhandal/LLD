package com.believe.lld.notificationservice;

// Factory Pattern for Notification Sender
public class NotificationFactory {
	public static NotificationSender getNotificationSender(NotificationType type) {
		switch (type) {
		case EMAIL:
			return new EmailNotificationSender();
		case SMS:
			return new SMSNotificationSender();
		case PUSH:
			return new PushNotificationSender();
		default:
			throw new IllegalArgumentException("Invalid Notification Type");
		}
	}
}
