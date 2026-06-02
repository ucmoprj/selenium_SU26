package mockitopractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Lab 02 - Stubbing
 *
 * Learning Objectives:
 *   1. Use when().thenReturn() -- make a Mock return a specific value
 *   2. Use when().thenThrow()  -- make a Mock throw an exception
 *   3. Understand that stubbing only applies to the exact arguments specified
 *   4. Stub the same method multiple times with different arguments
 *
 * Key Concept:
 *   Stubbing means programming a Mock to return a specific value
 *   when a specific method is called.
 *   Without stubbing, Mocks return default values (null, false, 0).
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab02_Stubbing
 *
 * Target class: UserRepository
 */
@ExtendWith(MockitoExtension.class)
public class Lab02_Stubbing {

    @Mock
    UserRepository userRepository;

    User alice;
    User bob;

    @BeforeEach
    void setUp() {
        alice = new User(1, "Alice", "alice@example.com");
        bob   = new User(2, "Bob",   "bob@example.com");
    }

    // ============================================================
    // STEP 1 -- when().thenReturn()
    //
    // when(mock.method(argument)).thenReturn(value)
    //   Programs the mock to return 'value' when method() is called with 'argument'.
    //
    // Without this stub, findById(1) would return null.
    // After this stub,  findById(1) returns the alice User object.
    // ============================================================
    @Test
    void stubbingWithThenReturn() {
        // when(userRepository.findById(1)).thenReturn(alice);

        // User result = userRepository.findById(1);
        // assertNotNull(result);
        // assertEquals("Alice", result.getName());
        // System.out.println("Found: " + result);
    }

    // ============================================================
    // STEP 2 -- Stubbing only applies to the specified argument
    //
    // Stubbing findById(1) does NOT affect findById(2).
    // findById(2) still returns the default value (null).
    // ============================================================
    @Test
    void stubbingIsArgumentSpecific() {
        // when(userRepository.findById(1)).thenReturn(alice);

        // assertEquals("Alice", userRepository.findById(1).getName());  // stubbed
        // assertNull(userRepository.findById(2));                        // not stubbed -- returns null
    }

    // ============================================================
    // STEP 3 -- Stub the same method with different arguments
    //
    // You can stub the same method multiple times with different arguments.
    // Each call gets its own configured return value.
    // ============================================================
    @Test
    void stubbingMultipleArguments() {
        // when(userRepository.findById(1)).thenReturn(alice);
        // when(userRepository.findById(2)).thenReturn(bob);

        // assertEquals("Alice", userRepository.findById(1).getName());
        // assertEquals("Bob",   userRepository.findById(2).getName());
    }

    // ============================================================
    // STEP 4 -- when().thenThrow()
    //
    // when(mock.method(argument)).thenThrow(ExceptionClass.class)
    //   Programs the mock to throw an exception when method() is called.
    //
    // Use this to simulate error conditions like DB connection failure.
    // ============================================================
    @Test
    void stubbingWithThenThrow() {
        // when(userRepository.findById(99))
        //     .thenThrow(new RuntimeException("DB connection failed"));

        // assertThrows(RuntimeException.class,
        //     () -> userRepository.findById(99));
    }

    // ============================================================
    // STEP 5 -- Stubbing boolean methods
    //
    // Stub methods that return boolean to control flow in business logic.
    // ============================================================
    @Test
    void stubbingBooleanMethod() {
        // when(userRepository.existsByEmail("alice@example.com")).thenReturn(true);
        // when(userRepository.existsByEmail("new@example.com")).thenReturn(false);

        // assertTrue(userRepository.existsByEmail("alice@example.com"));
        // assertFalse(userRepository.existsByEmail("new@example.com"));
    }
}
