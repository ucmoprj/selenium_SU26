package mockitopractice;

/**
 * Business logic layer for user management.
 *
 * Depends on UserRepository (DB access) and EmailService (email sending).
 * Both dependencies are injected via constructor -- this makes the class testable
 * because tests can pass in Mock objects instead of real implementations.
 *
 * This is the class under test in the Mockito labs.
 */
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    /**
     * Finds a user by ID.
     * Returns null if the user does not exist.
     */
    public User findUser(int id) {
        return userRepository.findById(id);
    }

    /**
     * Finds a user by ID or throws if not found.
     */
    public User getUserOrThrow(int id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + id);
        }
        return user;
    }

    /**
     * Registers a new user.
     * Saves to the repository and sends a welcome email.
     * Throws IllegalArgumentException if the email is already registered.
     */
    public void registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already registered: " + user.getEmail());
        }
        userRepository.save(user);
        emailService.sendWelcomeEmail(user.getEmail(), user.getName());
    }

    /**
     * Sends a notification to a user.
     * Throws IllegalArgumentException if the user does not exist.
     */
    public void notifyUser(int userId, String message) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + userId);
        }
        emailService.sendNotification(user.getEmail(), message);
    }
}
