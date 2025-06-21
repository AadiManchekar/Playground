package order.notifier;

import order.Order;

import user.User;

public interface OrderNotificationStrategy {
    public void sendNotification(User user, Order order, String subject, String body);
}
