import notify.EmailNotify;

public class Person implements Observer {

    EmailNotify notifier;
    String emailId;
    private Observable observable; // Add reference to observable

    Person(EmailNotify notifier, String emailId, Observable observable){
        this.notifier = notifier;
        this.emailId = emailId;
        this.observable = observable;
        this.observable.add(this); // Auto-subscribe on creation
    }

    @Override
    public void update() {
        this.update("Default notification");
    }

    @Override
    public void update(String msg) {
        notifier.sendNotification(msg, emailId);
    }
    
    public void unsubscribe() {
        if (observable != null) {
            observable.remove(this);
            observable = null; // Clear reference
        }
    }
    
}
