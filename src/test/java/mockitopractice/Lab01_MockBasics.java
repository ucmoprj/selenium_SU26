package mockitopractice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Lab 01 - Mock Basics
 *
 * Learning Objectives:
 *   1. Understand what a Mock object is and why we need it
 *   2. Create a Mock using @Mock annotation with @ExtendWith
 *   3. Create a Mock using mock() method (programmatic style)
 *   4. Understand that Mock methods return default values by default
 *
 * Key Concept:
 *   A Mock is a fake object that replaces a real dependency in tests.
 *   Instead of connecting to a real database or sending real emails,
 *   we use Mocks to simulate those behaviors in a controlled way.
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab01_MockBasics
 *
 * Target classes: UserRepository, EmailService
 */
@ExtendWith(MockitoExtension.class)
public class Lab01_MockBasics {

    // ============================================================
    // STEP 1 -- @Mock annotation
    //
    // @Mock creates a fake (mock) instance of UserRepository.
    // The mock does NOT connect to a real database.
    // All methods return default values: null for objects, 0 for int, false for boolean.
    //
    // @ExtendWith(MockitoExtension.class) on the class enables @Mock processing.
    // ============================================================
    @Mock
    UserRepository userRepository;

    @Mock
    EmailService emailService;

    @Test
    void mockCreatedWithAnnotation() {
        // userRepository is a Mock -- not a real UserRepository
        // assertNotNull(userRepository);
        // System.out.println("Mock class: " + userRepository.getClass().getName());
    }

    // ============================================================
    // STEP 2 -- Default return values of a Mock
    //
    // Without any setup (stubbing), Mock methods return:
    //   - null     for object return types
    //   - 0        for int/long/double
    //   - false    for boolean
    //   - empty    for collections
    //
    // This is different from the real UserRepository which throws UnsupportedOperationException.
    // ============================================================
    @Test
    void mockReturnsDefaultValues() {
        // User result = userRepository.findById(1);
        // assertNull(result);      // returns null by default

        // boolean exists = userRepository.existsByEmail("test@test.com");
        // assertFalse(exists);     // returns false by default

        // System.out.println("findById(1)  = " + userRepository.findById(1));
        // System.out.println("existsByEmail = " + userRepository.existsByEmail("a@b.com"));
    }

    // ============================================================
    // STEP 3 -- mock() method (programmatic style)
    //
    // Instead of @Mock annotation, you can create a mock manually.
    // Both approaches produce the same result.
    // Use @Mock in test classes, use mock() when you need it inline.
    // ============================================================
    @Test
    void mockCreatedProgrammatically() {
        // UserRepository repo = mock(UserRepository.class);
        // EmailService email  = mock(EmailService.class);

        // assertNotNull(repo);
        // assertNotNull(email);
        // assertNull(repo.findById(99));
    }

    // ============================================================
    // STEP 4 -- Real object vs Mock object
    //
    // Calling a method on the REAL UserRepository throws UnsupportedOperationException.
    // Calling a method on a MOCK returns the default value safely.
    // ============================================================
    @Test
    void realObjectThrows_mockDoesNot() {
        // Real object -- throws UnsupportedOperationException
        // UserRepository realRepo = new UserRepository();
        // assertThrows(UnsupportedOperationException.class,
        //     () -> realRepo.findById(1));

        // Mock object -- returns null safely
        // UserRepository mockRepo = mock(UserRepository.class);
        // assertNull(mockRepo.findById(1));
        // System.out.println("Mock is safe to call without a real DB");
    }
}
