package hamcrestpractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Lab 06 - Object (Bean) Matchers
 *
 * Learning Objectives:
 *   1. Check that an object has a property with hasProperty(name)
 *   2. Check a property's value with hasProperty(name, matcher)
 *   3. Use bean matchers on collections of objects
 *   4. Compare all properties at once with samePropertyValuesAs
 *   5. Check toString() output with hasToString
 *   6. Understand equalTo (equals) vs samePropertyValuesAs (all properties)
 *
 * Key Concept:
 *   A "property" is a value exposed by a JavaBean getter:
 *     getName()  -> property "name"
 *     getGpa()   -> property "gpa"
 *     isHonors() -> property "honors"   (boolean getters start with "is")
 *
 *   Bean matchers read properties BY NAME at runtime, so you can describe
 *   an object without writing a separate assertion for every getter.
 *   A typo in the property name is only caught when the test runs.
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab06_ObjectMatchers
 *
 * Target classes: Course, Student
 */
public class Lab06_ObjectMatchers {

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
    // STEP 1 -- hasProperty(name)
    //
    // Passes when the object has a getter for that property.
    // Student has getName(), getGpa(), isHonors() ... but no getPassword().
    // ============================================================
    @Test
    void hasPropertyByName() {
        // assertThat(alice, hasProperty("name"));
        // assertThat(alice, hasProperty("gpa"));
        // assertThat(alice, hasProperty("honors"));
        // assertThat(alice, not(hasProperty("password")));
    }

    // ============================================================
    // STEP 2 -- hasProperty(name, matcher)
    //
    // Reads the property and checks its value with another matcher.
    // ============================================================
    @Test
    void hasPropertyWithValue() {
        // assertThat(alice, hasProperty("name", equalTo("Alice")));
        // assertThat(alice, hasProperty("gpa", greaterThan(3.5)));
        // assertThat(alice, hasProperty("honors", is(true)));
        // assertThat(bob, hasProperty("email", endsWith("@example.com")));
    }

    // ============================================================
    // STEP 3 -- Bean matchers on collections
    //
    // Combine hasProperty with the collection matchers from Lab 04:
    //   hasItem(hasProperty(...))   -- at least one object matches
    //   everyItem(hasProperty(...)) -- every object matches
    //   contains(hasProperty(...), ...) -- objects in an exact order
    // ============================================================
    @Test
    void beanMatchersOnCollections() {
        // assertThat(course.getStudents(), hasItem(hasProperty("name", equalTo("Carol"))));
        // assertThat(course.getHonorsStudents(), everyItem(hasProperty("honors", is(true))));

        // assertThat(course.getHonorsStudents(), contains(
        //     hasProperty("name", is("Alice")),
        //     hasProperty("name", is("Carol"))));
    }

    // ============================================================
    // STEP 4 -- samePropertyValuesAs
    //
    // samePropertyValuesAs(expected)
    //   Passes when EVERY property of actual equals the same property of expected.
    //
    // samePropertyValuesAs(expected, "prop1", "prop2", ...)
    //   Same, but ignores the listed properties.
    // ============================================================
    @Test
    void comparingAllProperties() {
        // Student expected = new Student(1, "Alice", "alice@example.com", "Computer Science", 3.8);
        // assertThat(course.findByName("Alice"), samePropertyValuesAs(expected));

        // Student renamed = new Student(1, "Alice Smith", "alice@example.com", "Computer Science", 3.8);
        // assertThat(alice, samePropertyValuesAs(renamed, "name"));
    }

    // ============================================================
    // STEP 5 -- hasToString
    //
    // hasToString(matcher) -- applies a matcher to actual.toString()
    //
    // alice.toString() ->
    //   Student{id=1, name='Alice', major='Computer Science', gpa=3.8}
    // ============================================================
    @Test
    void toStringMatching() {
        // assertThat(alice, hasToString(containsString("name='Alice'")));
        // assertThat(alice, hasToString(startsWith("Student{")));
    }

    // ============================================================
    // STEP 6 -- equalTo vs samePropertyValuesAs
    //
    // Student.equals() compares ONLY the id.
    // So a student with id 1 but a different name is "equal" to alice,
    // even though most of its data is different.
    //
    // samePropertyValuesAs catches the difference and reports
    // exactly which property did not match.
    // ============================================================
    @Test
    void equalToVsSamePropertyValues() {
        // Student impostor = new Student(1, "Mallory", "mallory@example.com", "Mathematics", 2.0);

        // assertThat(impostor, is(equalTo(alice)));

        // AssertionError error = assertThrows(AssertionError.class,
        //     () -> assertThat(impostor, samePropertyValuesAs(alice)));
        // System.out.println(error.getMessage());
    }
}
