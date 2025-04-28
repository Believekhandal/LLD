package com.believe.lld.notificationservice;

import java.util.List;

public class NotificationService {
	private static volatile NotificationService instance;

	private NotificationService() {
		// private constructor
	}

	public static NotificationService getInstance() {
		if (instance == null) {
			synchronized (NotificationService.class) {
				if (instance == null) {
					instance = new NotificationService();
				}
			}
		}
		return instance;
	}

	public void notifyUsers(List<User> users, String message) {
		for (User user : users) {
			for (NotificationType type : user.getSubscriptions()) {
				NotificationSender sender = NotificationFactory.getNotificationSender(type);
				sender.sendNotification(user, message);
			}
		}
	}
}