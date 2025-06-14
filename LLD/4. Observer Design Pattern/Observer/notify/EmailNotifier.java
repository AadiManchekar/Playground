package notify;

public class EmailNotifier implements EmailNotify {
    @Override
    public void sendNotification(String msg, String recipient) {
        System.out.println("Sending email to " + recipient + " with message: " + msg);
    }
}
