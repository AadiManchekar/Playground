package order.notifier.impl;

import order.Order;
import order.notifier.OrderNotificationStrategy;
import user.User;

public class SpeedPostService implements OrderNotificationStrategy {

    @Override
    public void sendNotification(User user, Order order, String subject, String body) {
        System.out.println(String.format("Sending SpeedPost to %s with subject: %s and body: %s", user.getAddress(), subject, body));
    
    }
    
}
