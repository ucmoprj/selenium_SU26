package hamcrestpractice;

import org.hamcrest.Description;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Lab 07 - Custom Matchers
 *
 * Learning Objectives:
 *   1. Write a custom matcher by extending TypeSafeMatcher
 *   2. Write describeTo / describeMismatchSafely for clear failure messages
 *   3. Write a matcher for one feature of an object with FeatureMatcher
 *   4. Combine custom matchers with built-in matchers
 *   5. Report detailed mismatches with TypeSafeDiagnosingMatcher
 *
 * Key Concept:
 *   When the same check appears in many tests, wrap it in a matcher with a
 *   domain name. Tests then read like the requirements:
 *
 *     assertThat(alice, isHonorsStudent());
 *     assertThat(course, hasOpenSeats());
 *
 *   By convention, a custom matcher is created by a static factory method
 *   (isHonorsStudent(), hasGpa(...)) -- just like the built-in matchers.
 *
 *   The four custom matchers are already written at the BOTTOM of this file.
 *   Read them first, then uncomment the STEP blocks that use them.
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab07_CustomMatchers
 *
 * Target classes: Course, Student
 */
public class Lab07_CustomMatchers {

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
    // STEP 1 -- Using a TypeSafeMatcher
    //
    // isHonorsStudent() is defined at the bottom of this file.
    // TypeSafeMatcher<Student> checks for null and the type for us,
    // so matchesSafely() only ever receives a non-null Student.
    // ============================================================
    @Test
    void typeSafeMatcher() {
        // assertThat(alice, isHonorsStudent());
        // assertThat(bob, is(not(isHonorsStudent())));
    }

    // ============================================================
    // STEP 2 -- The failure message of a custom matcher
    //
    // describeTo()            -> fills in the "Expected: ..." line
    // describeMismatchSafely() -> fills in the "but: ..." line
    //
    // Run this step and compare the output with the code at the bottom.
    // ============================================================
    @Test
    void customFailureMessage() {
        // AssertionError error = assertThrows(AssertionError.class,
        //     () -> assertThat(bob, isHonorsStudent()));

        // System.out.println(error.getMessage());
    }

    // ============================================================
    // STEP 3 -- FeatureMatcher
    //
    // A FeatureMatcher extracts ONE value (a "feature") from an object
    // and checks it with another matcher.
    //   hasGpa(greaterThan(3.0))     -- reads getGpa(), then applies greaterThan
    //   hasMajor("Computer Science") -- reads getMajor(), then applies equalTo
    // ============================================================
    @Test
    void featureMatcher() {
        // assertThat(alice, hasGpa(greaterThan(3.0)));
        // assertThat(alice, hasGpa(closeTo(3.8, 0.001)));
        // assertThat(bob, hasMajor("Software Engineering"));
    }

    // ============================================================
    // STEP 4 -- Combining custom and built-in matchers
    //
    // Custom matchers are ordinary Matcher objects,
    // so they work everywhere a built-in matcher works.
    // ============================================================
    @Test
    void combiningCustomMatchers() {
        // assertThat(course.getStudents(), hasItem(isHonorsStudent()));
        // assertThat(course.getHonorsStudents(), everyItem(isHonorsStudent()));
        // assertThat(alice, allOf(isHonorsStudent(), hasMajor("Computer Science")));
        // assertThat(course.getStudents(), not(everyItem(hasGpa(greaterThan(3.5)))));
    }

    // ============================================================
    // STEP 5 -- TypeSafeDiagnosingMatcher
    //
    // Instead of a separate describeMismatchSafely(), a diagnosing matcher
    // writes the mismatch description WHILE it checks the value.
    // This is useful when the explanation depends on what was found.
    // ============================================================
    @Test
    void diagnosingMatcher() {
        // assertThat(course, hasOpenSeats());

        // Course seminar = new Course("SE4990", "Testing Seminar", 1);
        // seminar.enroll(alice);
        // assertThat(seminar, is(not(hasOpenSeats())));

        // AssertionError error = assertThrows(AssertionError.class,
        //     () -> assertThat(seminar, hasOpenSeats()));
        // System.out.println(error.getMessage());
    }

    // ============================================================
    // Custom matcher definitions
    // ============================================================

    /**
     * Matches a student whose GPA is on the honors list (GPA >= 3.5).
     */
    static Matcher<Student> isHonorsStudent() {
        return new TypeSafeMatcher<Student>() {
            @Override
            protected boolean matchesSafely(Student student) {
                return student.isHonors();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("an honors student (GPA >= ")
                           .appendValue(Student.HONORS_GPA)
                           .appendText(")");
            }

            @Override
            protected void describeMismatchSafely(Student student, Description mismatch) {
                mismatch.appendText("was ")
                        .appendValue(student.getName())
                        .appendText(" with GPA ")
                        .appendValue(student.getGpa());
            }
        };
    }

    /**
     * Matches a student whose GPA satisfies the given matcher.
     */
    static Matcher<Student> hasGpa(Matcher<? super Double> gpaMatcher) {
        return new FeatureMatcher<Student, Double>(gpaMatcher, "a student with GPA", "GPA") {
            @Override
            protected Double featureValueOf(Student student) {
                return student.getGpa();
            }
        };
    }

    /**
     * Matches a student with exactly the given major.
     */
    static Matcher<Student> hasMajor(String major) {
        return new FeatureMatcher<Student, String>(equalTo(major), "a student majoring in", "major") {
            @Override
            protected String featureValueOf(Student student) {
                return student.getMajor();
            }
        };
    }

    /**
     * Matches a course that still has at least one open seat.
     */
    static Matcher<Course> hasOpenSeats() {
        return new TypeSafeDiagnosingMatcher<Course>() {
            @Override
            protected boolean matchesSafely(Course course, Description mismatch) {
                if (course.isFull()) {
                    mismatch.appendText("course ")
                            .appendValue(course.getCode())
                            .appendText(" is full with ")
                            .appendValue(course.getStudents().size())
                            .appendText(" of ")
                            .appendValue(course.getCapacity())
                            .appendText(" seats taken");
                    return false;
                }
                return true;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("a course with at least one open seat");
            }
        };
    }
}
