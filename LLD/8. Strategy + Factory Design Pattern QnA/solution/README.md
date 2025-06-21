- Lets first fix Single Responsibility Principle by 
    - Adding a User class that will hold email and phoneNumber.
    - Because Order class should not care about customerDetails
- Lets add two more NotificationType those are WHATSAPP, SPEEDPOST
- Update `User.java` to hold Notification preference
- Now lets solve the Dependency Inversion Principle, we can define a common interface `OrderNotifier` with a function `void sendNotification(User user, Order order, String subject, String body)`. Which can have multiple concrete classes like `EmailService`, `SmsService`, `WhatsAppService` and `SpeedPostService`
- We can used strategy pattern to define a strategy (interface) and have its associated concrete classes, which enables us to inject the strategy and on runtime choose any concrete implementation.
- We can go a step ahead and instead of just injecting OrderNotificationStrategy and then in runtime injecting actual concrete Implementation what we can do is generate the object at run time and only inject `NotifierFactory`
- So in this way we used **SOLID, Strategy + Factory design pattern** together. 

---

- https://youtu.be/aBOrVRKK3fA?si=RksQ68oS6LcoCNB0