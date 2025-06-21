import java.time.LocalDate;

import order.OrderStatus;

public class Order {
    OrderStatus status;
    LocalDate shippedAt;
    String customerEmail;
    String customerPhoneNumber;
}
