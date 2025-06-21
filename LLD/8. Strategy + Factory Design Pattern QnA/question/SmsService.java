public class SmsService {
    void send(String phoneNumber, String body) {
        System.out.println(String.format("Sending sms to %s with subject %s and body %s", phoneNumber, body));
    }
}
