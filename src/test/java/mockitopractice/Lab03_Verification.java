package mockitopractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

/**
 * Lab 03 - Verification
 *
 * Learning Objectives:
 *   1. Use verify() -- confirm a method was called on a Mock
 *   2. Use verify(times(n)) -- confirm a method was called exactly n times
 *   3. Use verify(never()) -- confirm a method was never called
 *   4. Use verifyNoInteractions() -- confirm no methods were called at all
 *
 * Key Concept:
 *   Stubbing answers "what does the Mock return?"
 *   Verification answers "was the Mock actually called?"
 *
 *   Example: after registering a user, verify that save() was called.
 *   If save() was never called, the registration logic is broken.
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab03_Verification
 *
 * Target classes: UserRepository, EmailService
 */
@ExtendWith(MockitoExtension.class)
public class Lab03_Verification {

    @Mock
    UserRepository userRepository;

    @Mock
    EmailService emailService;

    User alice;

    @BeforeEach
    void setUp() {
        alice = new User(1, "Alice", "alice@example.com");
    }

    // ============================================================
    // STEP 1 -- verify() basic usage
    //
    // verify(mock).method(argument)
    //   Passes if method() was called on the mock with that argument.
    //   Fails if method() was NOT called.
    // ============================================================
    @Test
    void verifyMethodWasCalled() {
        // userRepository.save(alice);

        // verify(userRepository).save(alice);
        // System.out.println("save(alice) was called -- verified");
    }

    // ============================================================
    // STEP 2 -- verify(times(n))
    //
    // verify(mock, times(n)).method()
    //   Passes if method() was called exactly n times.
    //
    // Other options:
    //   times(1)    -- exactly once (default if omitted)
    //   times(0)    -- never called (same as never())
    //   atLeast(n)  -- at least n times
    //   atMost(n)   -- at most n times
    // ============================================================
    @Test
    void verifyCallCount() {
        // userRepository.findById(1);
        // userRepository.findById(1);
        // userRepository.findById(1);

        // verify(userRepository, times(3)).findById(1);
        // System.out.println("findById(1) was called exactly 3 times -- verified");
    }

    // ============================================================
    // STEP 3 -- verify(never())
    //
    // Confirms that a method was NEVER called on the mock.
    // Useful for verifying that an action was intentionally skipped.
    //
    // Example: if email already exists, save() should NOT be called.
    // ============================================================
    @Test
    void verifyMethodWasNeverCalled() {
        // -- do NOT call save()

        // verify(userRepository, never()).save(alice);
        // System.out.println("save() was never called -- verified");
    }

    // ============================================================
    // STEP 4 -- verifyNoInteractions()
    //
    // verifyNoInteractions(mock)
    //   Passes if NO methods were called on the mock at all.
    //   Useful for confirming a dependency was completely unused.
    // ============================================================
    @Test
    void verifyNoMethodsCalledAtAll() {
        // -- do NOT interact with emailService

        // verifyNoInteractions(emailService);
        // System.out.println("emailService was not used at all -- verified");
    }

    // ============================================================
    // STEP 5 -- Combining stubbing and verification
    //
    // Real tests typically:
    //   1. Stub the mock to return a value
    //   2. Call the method under test
    //   3. Verify the mock was called with correct arguments
    // ============================================================
    @Test
    void stubbingAndVerification() {
        // -- Arrange
        // when(userRepository.existsByEmail("alice@example.com")).thenReturn(false);

        // -- Act
        // userRepository.existsByEmail("alice@example.com");

        // -- Assert
        // verify(userRepository, times(1)).existsByEmail("alice@example.com");
    }
}
