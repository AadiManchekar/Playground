package order.notifier.impl;

import order.Order;
import order.notifier.OrderNotificationStrategy;
import user.User;

public class EmailService implements OrderNotificationStrategy {

    @Override
    public void sendNotification(User user, Order order, String subject, String body) {
        System.out.println(String.format("Sending email to %s with subject: %s and body: %s", user.getEmail(), subject, body));
    }
    
}
