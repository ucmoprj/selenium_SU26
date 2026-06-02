package mockitopractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Lab 04 - @InjectMocks
 *
 * Learning Objectives:
 *   1. Understand the difference between @Mock and @InjectMocks
 *   2. Use @InjectMocks to inject Mocks into the class under test
 *   3. Test business logic in UserService with mocked dependencies
 *   4. Verify that UserService calls its dependencies correctly
 *
 * Key Concept:
 *   @Mock    -- creates a fake dependency
 *   @InjectMocks -- creates the REAL object under test,
 *                   and automatically injects all @Mock fields into it
 *
 *   UserService depends on UserRepository and EmailService.
 *   We mock the dependencies and inject them into UserService,
 *   so we can test UserService's logic without a real DB or email server.
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab04_InjectMocks
 *
 * Target class: UserService
 */
@ExtendWith(MockitoExtension.class)
public class Lab04_InjectMocks {

    // ============================================================
    // STEP 1 -- @Mock + @InjectMocks setup
    //
    // @Mock creates fake versions of the dependencies.
    // @InjectMocks creates a REAL UserService and injects the mocks into it.
    //
    // Mockito matches by type -- userRepository (UserRepository) goes into
    // UserService's UserRepository field, emailService goes into EmailService field.
    // ============================================================
    @Mock
    UserRepository userRepository;

    @Mock
    EmailService emailService;

    @InjectMocks
    UserService userService;

    User alice;

    @BeforeEach
    void setUp() {
        alice = new User(1, "Alice", "alice@example.com");
    }

    @Test
    void userServiceIsCreated() {
        // assertNotNull(userService);
        // System.out.println("UserService created with mocked dependencies");
    }

    // ============================================================
    // STEP 2 -- Test findUser() through UserService
    //
    // userService.findUser(1) internally calls userRepository.findById(1).
    // We stub userRepository to return alice, then verify userService returns alice.
    // ============================================================
    @Test
    void findUser_returnsUserFromRepository() {
        // when(userRepository.findById(1)).thenReturn(alice);

        // User result = userService.findUser(1);

        // assertEquals("Alice", result.getName());
        // verify(userRepository).findById(1);
    }

    // ============================================================
    // STEP 3 -- Test getUserOrThrow() -- user not found case
    //
    // When userRepository returns null, getUserOrThrow() should throw.
    // ============================================================
    @Test
    void getUserOrThrow_throwsWhenUserNotFound() {
        // when(userRepository.findById(99)).thenReturn(null);

        // assertThrows(IllegalArgumentException.class,
        //     () -> userService.getUserOrThrow(99));
    }

    // ============================================================
    // STEP 4 -- Test registerUser() -- happy path
    //
    // registerUser() should:
    //   1. Check if email exists (existsByEmail)
    //   2. Save the user (save)
    //   3. Send a welcome email (sendWelcomeEmail)
    //
    // We verify all three steps happened in the correct order.
    // ============================================================
    @Test
    void registerUser_savesAndSendsEmail() {
        // when(userRepository.existsByEmail("alice@example.com")).thenReturn(false);

        // userService.registerUser(alice);

        // verify(userRepository).save(alice);
        // verify(emailService).sendWelcomeEmail("alice@example.com", "Alice");
    }

    // ============================================================
    // STEP 5 -- Test registerUser() -- duplicate email case
    //
    // If email already exists, registerUser() should throw
    // and NOT call save() or sendWelcomeEmail().
    // ============================================================
    @Test
    void registerUser_throwsWhenEmailAlreadyExists() {
        // when(userRepository.existsByEmail("alice@example.com")).thenReturn(true);

        // assertThrows(IllegalArgumentException.class,
        //     () -> userService.registerUser(alice));

        // verify(userRepository, never()).save(any());
        // verify(emailService, never()).sendWelcomeEmail(any(), any());
    }
}
