package user;

import order.notifier.NotificationType;

public class User {
    String email;
    String address;
    String phoneNumber;
    NotificationType notificationPreference;

    // Getters
    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public NotificationType getNotificationPreference() {
        return notificationPreference;
    }
}
