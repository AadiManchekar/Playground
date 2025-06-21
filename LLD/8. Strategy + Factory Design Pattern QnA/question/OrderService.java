import java.time.LocalDate;

import order.OrderStatus;
import order.notifier.NotificationType;

public class OrderService {

    AppConfig config;
    EmailService emailService;
    SmsService smsService;
    OrderService(AppConfig config, EmailService emailService, SmsService smsService) {
        this.config = config;
        this.emailService = emailService;
        this.smsService = smsService;
    }


    // Refactor this
    void shipOrder(Order order) {
        order.status = OrderStatus.SHIPPED;
        order.shippedAt = LocalDate.now();

        // Send notification to user
        if(config.notificationType == NotificationType.EMAIL) {
            var subject = "Your order is shipped!!!";
            var message = "Your order is shipped and will be delivered soon";
            emailService.send(order.customerEmail, subject, message);
        }
        else if(config.notificationType == NotificationType.SMS) {
            var message = "Your order is shipped and will be delivered soon";
            smsService.send(order.customerEmail, message);
        }
    }
}
