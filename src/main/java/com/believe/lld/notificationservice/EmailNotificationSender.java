package com.believe.lld.notificationservice;

public class EmailNotificationSender implements NotificationSender {

	@Override
	public void sendNotification(User user, String message) {
		System.out.println("sending email to :" + user.getEmail() + "message: " + message);
	}

}
