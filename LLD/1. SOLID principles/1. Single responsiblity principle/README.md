## Single Responsibility Principle
- A class should have a single responsibility/role.
- A class should have one and only one reason to change.
- If a Class has many responsibilities, it increases the possibility of bugs because making changes to one of its responsibilities, could affect the other ones without you knowing.
- If a class handles multiple responsibilities, changing one part of the class may unintentionally affect other unrelated parts, increasing the chances of bugs and making maintenance harder.
- SRP is not about the number of methods or actions in a class, but about having a single, well-defined responsibility.
- For example, in a hotel system, you might have:
    - Waiter class (responsible for taking orders)
    - Cashier class (responsible for handling payments)
    - Chef class (responsible for preparing food)
    - Dishwasher class (responsible for cleaning dishes)
- Each class has one role and a single reason to change.
- Single Responsibility Principle (SRP) is about why a class changes, not how many actions it performs.
- A class can have multiple methods or actions as long as they all relate to the same responsibility.
- The reason to change is the key idea: if a class has more than one reason to change, it's violating SRP.
- It's perfectly fine for the Cashier class to have multiple payment methods like:
```Java
class PaymentProcessor {
    void acceptCashPayment() { ... }
    void acceptCardPayment() { ... }
    void acceptUPIPayment() { ... }
}
```
- All these actions are related to handling payments, which is the single responsibility of the PaymentProcessor.
- The class would only need to change if the payment handling process changes — this is one reason to change.
- https://www.javaguides.net/2018/02/single-responsibility-principle.html