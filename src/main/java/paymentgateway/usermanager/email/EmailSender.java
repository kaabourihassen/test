package paymentgateway.usermanager.email;

public interface EmailSender {
    void send(String to, String email);
}