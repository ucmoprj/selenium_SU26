package mockitopractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

/**
 * Lab 05 - Argument Matchers
 *
 * Learning Objectives:
 *   1. Use any()       -- match any object argument
 *   2. Use anyInt()    -- match any int argument
 *   3. Use anyString() -- match any String argument
 *   4. Use eq()        -- match an exact value (explicit)
 *   5. Understand when to use matchers vs exact values
 *
 * Key Concept:
 *   Instead of stubbing for one specific argument,
 *   Argument Matchers let you stub for a whole category of arguments.
 *
 *   when(repo.findById(1)).thenReturn(alice)    -- only matches findById(1)
 *   when(repo.findById(anyInt())).thenReturn(alice) -- matches any int argument
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab05_ArgumentMatchers
 *
 * Target class: UserService, UserRepository, EmailService
 */
@ExtendWith(MockitoExtension.class)
public class Lab05_ArgumentMatchers {

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

    // ============================================================
    // STEP 1 -- anyInt()
    //
    // anyInt() matches ANY integer passed to findById().
    // This is useful when you do not care about the specific ID value.
    // ============================================================
    @Test
    void anyInt_matchesAnyId() {
        // when(userRepository.findById(anyInt())).thenReturn(alice);

        // assertEquals("Alice", userService.findUser(1).getName());
        // assertEquals("Alice", userService.findUser(99).getName());
        // assertEquals("Alice", userService.findUser(0).getName());
        // System.out.println("anyInt() matched all IDs");
    }

    // ============================================================
    // STEP 2 -- anyString()
    //
    // anyString() matches any non-null String argument.
    // ============================================================
    @Test
    void anyString_matchesAnyEmail() {
        // when(userRepository.existsByEmail(anyString())).thenReturn(false);

        // assertFalse(userRepository.existsByEmail("alice@example.com"));
        // assertFalse(userRepository.existsByEmail("anyone@anywhere.com"));
    }

    // ============================================================
    // STEP 3 -- any()
    //
    // any() matches any object of any type (including null).
    // any(ClassName.class) matches any object of that specific type.
    // ============================================================
    @Test
    void any_matchesAnyObject() {
        // when(userRepository.existsByEmail(anyString())).thenReturn(false);

        // userService.registerUser(alice);

        // verify(userRepository).save(any(User.class));
        // verify(emailService).sendWelcomeEmail(anyString(), anyString());
    }

    // ============================================================
    // STEP 4 -- eq() for exact matching inside a matcher context
    //
    // When mixing matchers and exact values, you MUST wrap exact values with eq().
    // You cannot mix raw values and matchers in the same method call.
    //
    // WRONG: verify(emailService).sendWelcomeEmail(anyString(), "Alice");
    // RIGHT: verify(emailService).sendWelcomeEmail(anyString(), eq("Alice"));
    // ============================================================
    @Test
    void eq_forExactValueWithinMatchers() {
        // when(userRepository.existsByEmail(anyString())).thenReturn(false);

        // userService.registerUser(alice);

        // verify(emailService).sendWelcomeEmail(anyString(), eq("Alice"));
    }

    // ============================================================
    // STEP 5 -- Using matchers in verify() for never()
    //
    // Matchers are especially useful with never() to confirm
    // a method was not called with ANY argument.
    // ============================================================
    @Test
    void neverCalledWithAnyArgument() {
        // when(userRepository.existsByEmail(anyString())).thenReturn(true);

        // assertThrows(IllegalArgumentException.class,
        //     () -> userService.registerUser(alice));

        // verify(emailService, never()).sendWelcomeEmail(anyString(), anyString());
    }
}
