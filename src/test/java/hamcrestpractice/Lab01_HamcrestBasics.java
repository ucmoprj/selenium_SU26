package hamcrestpractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Lab 01 - Hamcrest Basics
 *
 * Learning Objectives:
 *   1. Write assertions with assertThat(actual, matcher)
 *   2. Use the core matchers: equalTo, is, not
 *   3. Check for null with nullValue / notNullValue
 *   4. Understand the difference between equalTo and sameInstance
 *   5. Check types with instanceOf / isA
 *   6. Read Hamcrest failure messages
 *
 * Key Concept:
 *   A Matcher is an object that answers one question: "does this value match?"
 *   Hamcrest assertions read like an English sentence:
 *
 *     JUnit:    assertEquals("SE4930", course.getCode());
 *     Hamcrest: assertThat(course.getCode(), is(equalTo("SE4930")));
 *
 *   Note the argument order: assertThat puts the ACTUAL value first,
 *   then the matcher that describes what you EXPECT.
 *
 * Imports used in every Hamcrest lab:
 *   import static org.hamcrest.MatcherAssert.assertThat;
 *   import static org.hamcrest.Matchers.*;
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab01_HamcrestBasics
 *
 * Target classes: Course, Student
 * 
 * Install : Extension Pack for Java 
 */
public class Lab01_HamcrestBasics {

    Course course;
    Student alice;
    Student bob;

    @BeforeEach
    void setUp() {
        course = new Course("SE4930", "Software Testing", 30);
        alice = new Student(1, "Alice", "alice@example.com", "Computer Science", 3.8);
        bob = new Student(2, "Bob", "bob@example.com", "Software Engineering", 3.2);
        course.enroll(alice);
        course.enroll(bob);
    }

    // ============================================================
    // STEP 1 -- assertThat + equalTo
    //
    // assertThat(actual, equalTo(expected))
    //   Passes when actual.equals(expected).
    //   It does the same job as assertEquals, but reads left-to-right:
    //   "assert that the course code is equal to SE4930".
    // ============================================================
    @Test
    void assertThatWithEqualTo() {
        // assertEquals("SE4930", course.getCode());           // JUnit style
        // assertThat(course.getCode(), equalTo("SE4930"));    // Hamcrest style

        // assertThat(course.getTitle(), equalTo("Software Testing"));
        // assertThat(course.getCapacity(), equalTo(30));
    }

    // ============================================================
    // STEP 2 -- is()
    //
    // is() is "syntactic sugar": it does not change the result,
    // it only makes the sentence easier to read.
    //
    //   is(30)             is the same as  is(equalTo(30))
    //   is(equalTo(30))    is the same as  equalTo(30)
    // ============================================================
    @Test
    void isForReadability() {
        // assertThat(course.getCapacity(), is(30));
        // assertThat(course.getCode(), is(equalTo("SE4930")));
        // assertThat(course.isFull(), is(false));
    }

    // ============================================================
    // STEP 3 -- not()
    //
    // not(matcher) inverts any matcher.
    // not(value) is a shortcut for not(equalTo(value)).
    // ============================================================
    @Test
    void notInvertsAMatcher() {
        // assertThat(course.getCode(), not(equalTo("CS1110")));
        // assertThat(course.getCode(), is(not("CS1110")));
        // assertThat(alice, is(not(bob)));
    }

    // ============================================================
    // STEP 4 -- nullValue / notNullValue
    //
    // nullValue()    -- passes when the value IS null
    // notNullValue() -- passes when the value is NOT null
    //
    // course.findByName() returns null when the student is not enrolled.
    // ============================================================
    @Test
    void nullChecks() {
        // assertThat(course.findByName("Alice"), is(notNullValue()));
        // assertThat(course.findByName("Zoe"), is(nullValue()));
    }

    // ============================================================
    // STEP 5 -- equalTo vs sameInstance
    //
    // equalTo(x)      -- uses equals()  (Student.equals compares id only)
    // sameInstance(x) -- uses ==        (must be the exact same object in memory)
    //
    // aliceCopy has the same id as alice, so they are EQUAL,
    // but they are two different objects, so they are NOT the same instance.
    // ============================================================
    @Test
    void equalToVsSameInstance() {
        // Student aliceCopy = new Student(1, "Alice", "alice@example.com", "Computer Science", 3.8);

        // assertThat(aliceCopy, is(equalTo(alice)));          // same id -> equal
        // assertThat(aliceCopy, is(not(sameInstance(alice)))); // different objects

        // assertThat(course.findByName("Alice"), is(sameInstance(alice)));
    }

    // ============================================================
    // STEP 6 -- instanceOf / isA and a reason message
    //
    // instanceOf(Type.class) -- passes when the value is an instance of Type
    // isA(Type.class)        -- shortcut for is(instanceOf(Type.class))
    //
    // assertThat(reason, actual, matcher)
    //   The optional first String is printed when the assertion fails.
    // ============================================================
    @Test
    void typeChecksAndReason() {
        // assertThat(course.getStudents(), instanceOf(List.class));
        // assertThat(alice, isA(Student.class));

        // assertThat("A new course should not be full", course.isFull(), is(false));
    }

    // ============================================================
    // STEP 7 -- Reading a failure message
    //
    // One big advantage of Hamcrest is the failure message.
    // Each matcher describes itself, so a failure looks like:
    //
    //   Expected: is <25>
    //        but: was <30>
    //
    // Here we deliberately make an assertion fail, catch the AssertionError
    // with assertThrows, and print its message so you can read it.
    // ============================================================
    @Test
    void readFailureMessage() {
        // AssertionError error = assertThrows(AssertionError.class,
        //     () -> assertThat(course.getCapacity(), is(25)));

        // System.out.println(error.getMessage());
    }
}
