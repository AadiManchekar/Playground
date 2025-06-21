package order;

import order.notifier.OrderNotificationStrategy;
import order.notifier.OrderNotifierFactory;

import user.User;

import java.time.LocalDate;

public class OrderService {
    User user;
    Order order;
    OrderNotifierFactory notifierFactory;

    OrderService(User user, Order order, OrderNotifierFactory notifierFactory) {
        this.user = user;
        this.order = order;
        this.notifierFactory = notifierFactory;
    }

    void shipOrder(Order order, User user) {
        order.status = OrderStatus.SHIPPED;
        order.shippedAt = LocalDate.now();

        var subject = "Your order is shipped!!!";
        var message = "Your order is shipped and will be delivered soon";

        // Send notification to user
        OrderNotificationStrategy notifier =
                OrderNotifierFactory.getNotifier(user.getNotificationPreference());
        notifier.sendNotification(user, order, subject, message);
    }
}
