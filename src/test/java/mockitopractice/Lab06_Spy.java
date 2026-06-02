package mockitopractice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Lab 06 - Spy
 *
 * Learning Objectives:
 *   1. Understand the difference between Mock and Spy
 *   2. Create a Spy using @Spy annotation
 *   3. Use a Spy to call real methods while stubbing only specific ones
 *   4. Verify method calls on a Spy
 *
 * Key Concept:
 *   Mock -- ALL methods are fake (return default values unless stubbed)
 *   Spy  -- wraps a REAL object; real methods run UNLESS specifically stubbed
 *
 *   Use a Spy when you want to test a real object but override just one method.
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab06_Spy
 *
 * Target class: UserService (partial real behavior with spied dependency)
 */
@ExtendWith(MockitoExtension.class)
public class Lab06_Spy {

    // ============================================================
    // STEP 1 -- @Spy vs @Mock
    //
    // @Mock UserService  -- all methods return null/false/0 by default
    // @Spy  UserService  -- real methods run by default
    //
    // Note: @Spy requires the class to have a no-arg constructor,
    //       OR you can pass an instance: @Spy UserService service = new UserService(...)
    //
    // We spy on a simple list to demonstrate the difference clearly.
    // ============================================================
    @Spy
    java.util.ArrayList<String> spyList;

    @Test
    void spy_callsRealMethods() {
        // spyList.add("Alice");
        // spyList.add("Bob");

        // Real method runs -- size() returns 2
        // assertEquals(2, spyList.size());

        // verify works on Spy just like Mock
        // verify(spyList, times(2)).add(anyString());
        // System.out.println("Spy list: " + spyList);
    }

    // ============================================================
    // STEP 2 -- Stubbing a specific method on a Spy
    //
    // You can stub one method while keeping all other methods real.
    // Use doReturn().when() syntax for Spy (not when().thenReturn()).
    //
    // when().thenReturn() calls the real method during setup -- can cause issues.
    // doReturn().when() skips the real method during setup -- safer for Spy.
    // ============================================================
    @Test
    void spy_stubbingOneMethod() {
        // spyList.add("Alice");
        // spyList.add("Bob");

        // Override size() to return 99 -- real data still has 2 elements
        // doReturn(99).when(spyList).size();

        // assertEquals(99, spyList.size());    // stubbed -- returns 99
        // assertEquals("Alice", spyList.get(0)); // real method -- returns "Alice"
    }

    // ============================================================
    // STEP 3 -- Mock vs Spy comparison
    //
    // See the difference side by side.
    // ============================================================
    @Test
    void mockVsSpyComparison() {
        // java.util.List<String> mockList = mock(java.util.ArrayList.class);
        // java.util.List<String> spyList2 = spy(new java.util.ArrayList<String>());

        // mockList.add("Alice");
        // spyList2.add("Alice");

        // Mock -- add() does nothing (fake), size() returns 0
        // assertEquals(0, mockList.size());

        // Spy -- add() really adds, size() returns 1
        // assertEquals(1, spyList2.size());

        // System.out.println("Mock size: " + mockList.size());
        // System.out.println("Spy size:  " + spyList2.size());
    }

    // ============================================================
    // STEP 4 -- When to use Spy
    //
    // Spy is useful when:
    //   1. The class has complex setup you want to preserve
    //   2. You want to test MOST of the real behavior but stub one method
    //   3. You need to verify calls on a real object
    //
    // In most cases, prefer @Mock over @Spy.
    // If you find yourself using @Spy often, consider refactoring the design.
    // ============================================================
    @Test
    void verify_worksSameOnSpy() {
        // spyList.add("Alice");
        // spyList.add("Bob");
        // spyList.contains("Alice");

        // verify(spyList).add("Alice");
        // verify(spyList).add("Bob");
        // verify(spyList).contains("Alice");
        // System.out.println("All interactions verified on Spy");
    }
}
