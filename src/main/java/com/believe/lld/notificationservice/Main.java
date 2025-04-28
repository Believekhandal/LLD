package com.believe.lld.notificationservice;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Alice", "alice@gmail.com", "9999999999", "deviceToken1");
        user1.subscribe(NotificationType.EMAIL);
        user1.subscribe(NotificationType.SMS);

        User user2 = new User("Bob", "bob@gmail.com", "8888888888", "deviceToken2");
        user2.subscribe(NotificationType.PUSH);

        List<User> users = List.of(user1, user2);

        NotificationService service = NotificationService.getInstance();
        service.notifyUsers(users, "System Maintenance Tonight!");
    }
}
