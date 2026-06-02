package junitpractice;

/**
 * Validates user input â€” used as a test target in JUnit labs.
 *
 * Covers: email format, password strength, username rules
 * Good for parameterized tests with many input/expected pairs.
 */
public class UserValidator {

    /**
     * Returns true if the email contains exactly one '@' and at least one '.' after it.
     */
    public boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) return false;
        int atIndex = email.indexOf('@');
        if (atIndex <= 0) return false;
        String domain = email.substring(atIndex + 1);
        return domain.contains(".") && !domain.startsWith(".") && !domain.endsWith(".");
    }

    /**
     * Returns true if the password is at least 8 characters and contains
     * at least one digit and one uppercase letter.
     */
    public boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) return false;
        boolean hasDigit = false;
        boolean hasUpper = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c))     hasDigit = true;
            if (Character.isUpperCase(c)) hasUpper = true;
        }
        return hasDigit && hasUpper;
    }

    /**
     * Returns true if the username is 3â€“20 characters, alphanumeric or underscore only,
     * and does not start with a digit.
     */
    public boolean isValidUsername(String username) {
        if (username == null) return false;
        if (username.length() < 3 || username.length() > 20) return false;
        if (Character.isDigit(username.charAt(0))) return false;
        return username.matches("[a-zA-Z0-9_]+");
    }
}

