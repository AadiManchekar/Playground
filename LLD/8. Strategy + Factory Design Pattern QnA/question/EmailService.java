public class EmailService {
    void send(String email, String subject, String body) {
        System.out.println(String.format("Sending email to %s with subject %s and body %s", email, subject, body));
    }
}
