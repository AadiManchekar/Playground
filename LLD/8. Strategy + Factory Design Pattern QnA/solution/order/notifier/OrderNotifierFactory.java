package order.notifier;

import order.notifier.impl.EmailService;
import order.notifier.impl.SmsService;
import order.notifier.impl.SpeedPostService;
import order.notifier.impl.WhatsAppService;

public class OrderNotifierFactory {
    public static OrderNotificationStrategy getNotifier(NotificationType type) {
        switch (type) {
            case EMAIL:
                return new EmailService();
            case SMS:
                return new SmsService();
            case WHATSAPP:
                return new WhatsAppService();
            case SPEEDPOST:
                return new SpeedPostService();
            default:
                throw new IllegalArgumentException("Unsupported NotificationType: " + type);
        }
    }
}
