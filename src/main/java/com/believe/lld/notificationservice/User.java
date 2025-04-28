package com.believe.lld.notificationservice;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class User {
	private String name;
	private String email;
	private String phoneNumber;
	private String deviceToken;

	public User(String name, String email, String phoneNumber, String deviceToken) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.deviceToken = deviceToken;
	}

	private Set<NotificationType> subscriptions = new HashSet<>();

	public void subscribe(NotificationType type) {
		subscriptions.add(type);
	}

	public void unsubscribe(NotificationType type) {
		subscriptions.remove(type);
	}
}
