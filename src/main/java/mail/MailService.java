package mail;

public interface MailService {
    void sendMail(String name, String email, String body) throws MailServiceException;
}
