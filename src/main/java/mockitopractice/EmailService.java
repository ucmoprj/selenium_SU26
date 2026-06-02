package mockitopractice;

/**
 * Simulates an external email sending service.
 *
 * In real applications this would connect to an SMTP server.
 * In tests, this class is replaced with a Mock object so no real email is sent.
 */
public class EmailService {

    public void sendWelcomeEmail(String to, String name) {
        // real implementation would send an email via SMTP
        throw new UnsupportedOperationException("Real SMTP not available in tests -- use a Mock");
    }

    public void sendNotification(String to, String message) {
        // real implementation would send an email via SMTP
        throw new UnsupportedOperationException("Real SMTP not available in tests -- use a Mock");
    }
}
