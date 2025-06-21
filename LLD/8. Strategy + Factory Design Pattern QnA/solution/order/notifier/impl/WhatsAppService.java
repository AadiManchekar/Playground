package order.notifier.impl;

import order.Order;
import order.notifier.OrderNotificationStrategy;
import user.User;

public class WhatsAppService implements OrderNotificationStrategy {

    @Override
    public void sendNotification(User user, Order order, String title, String message) {
        System.out.println(String.format("Sending WhatsApp message to %s with title: %s and message: %s", user.getPhoneNumber(), title, message));
    }
    
}
