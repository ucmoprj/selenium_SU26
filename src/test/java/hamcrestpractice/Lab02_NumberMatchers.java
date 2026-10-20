package hamcrestpractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Lab 02 - Number Matchers
 *
 * Learning Objectives:
 *   1. Compare numbers with greaterThan / lessThan
 *   2. Use the inclusive versions greaterThanOrEqualTo / lessThanOrEqualTo
 *   3. Compare floating-point values safely with closeTo
 *   4. Use comparesEqualTo for values where equals() is too strict (BigDecimal)
 *   5. Understand that ordering matchers work for ANY Comparable, not just numbers
 *
 * Key Concept:
 *   With plain JUnit you often write assertTrue(gpa > 3.5).
 *   When that fails, the message is only "expected: <true> but was: <false>".
 *
 *   With Hamcrest you write assertThat(gpa, greaterThan(3.5)).
 *   When that fails, the message says:
 *     Expected: a value greater than <3.5>
 *          but: <3.2> was less than <3.5>
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab02_NumberMatchers
 *
 * Target classes: Course, Student
 */
public class Lab02_NumberMatchers {

    Course course;
    Student alice;
    Student bob;
    Student carol;

    @BeforeEach
    void setUp() {
        course = new Course("SE4930", "Software Testing", 30);
        alice = new Student(1, "Alice", "alice@example.com", "Computer Science", 3.8);
        bob = new Student(2, "Bob", "bob@example.com", "Software Engineering", 3.2);
        carol = new Student(3, "Carol", "carol@example.com", "Computer Science", 3.6);
        course.enroll(alice);
        course.enroll(bob);
        course.enroll(carol);
    }

    // ============================================================
    // STEP 1 -- greaterThan / lessThan
    //
    // greaterThan(x) -- passes when actual > x
    // lessThan(x)    -- passes when actual < x
    // ============================================================
    @Test
    void greaterThanAndLessThan() {
        // assertThat(alice.getGpa(), greaterThan(3.5));
        // assertThat(bob.getGpa(), lessThan(3.5));
        // assertThat(course.getStudents().size(), greaterThan(0));
    }

    // ============================================================
    // STEP 2 -- greaterThanOrEqualTo / lessThanOrEqualTo
    //
    // The inclusive versions: >= and <=
    // Useful for boundaries such as "number enrolled <= capacity".
    // ============================================================
    @Test
    void inclusiveComparisons() {
        // assertThat(course.getStudents().size(), lessThanOrEqualTo(course.getCapacity()));
        // assertThat(carol.getGpa(), greaterThanOrEqualTo(Student.HONORS_GPA));
        // assertThat(bob.getGpa(), greaterThanOrEqualTo(0.0));
    }

    // ============================================================
    // STEP 3 -- closeTo for floating-point values
    //
    // Floating-point arithmetic is not exact:
    //   0.1 + 0.2 == 0.30000000000000004   (not 0.3)
    //
    // closeTo(expected, error) passes when |actual - expected| <= error.
    //
    // The average GPA here is (3.8 + 3.2 + 3.6) / 3 = 3.5333...
    // equalTo(3.53) would FAIL, but closeTo(3.53, 0.01) passes.
    // ============================================================
    @Test
    void closeToForDoubles() {
        // assertThat(0.1 + 0.2, is(not(equalTo(0.3))));
        // assertThat(0.1 + 0.2, is(closeTo(0.3, 0.000001)));

        // assertThat(course.getAverageGpa(), is(closeTo(3.53, 0.01)));
    }

    // ============================================================
    // STEP 4 -- comparesEqualTo
    //
    // equalTo uses equals(); comparesEqualTo uses compareTo() == 0.
    //
    // For BigDecimal these are different:
    //   new BigDecimal("3.50").equals(new BigDecimal("3.5"))     -> false (different scale)
    //   new BigDecimal("3.50").compareTo(new BigDecimal("3.5"))  -> 0     (same value)
    // ============================================================
    @Test
    void comparesEqualToForBigDecimal() {
        // BigDecimal a = new BigDecimal("3.50");
        // BigDecimal b = new BigDecimal("3.5");

        // assertThat(a, is(not(equalTo(b))));
        // assertThat(a, comparesEqualTo(b));
    }

    // ============================================================
    // STEP 5 -- Ordering matchers work on any Comparable
    //
    // greaterThan / lessThan / comparesEqualTo accept any type that
    // implements Comparable -- for example String (alphabetical order).
    // ============================================================
    @Test
    void orderingMatchersOnStrings() {
        // assertThat("Bob", greaterThan("Alice"));
        // assertThat(alice.getName(), lessThan(carol.getName()));
    }
}
