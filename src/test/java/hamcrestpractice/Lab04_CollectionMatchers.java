package hamcrestpractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Lab 04 - Collection, Map and Array Matchers
 *
 * Learning Objectives:
 *   1. Check size and emptiness with hasSize / empty
 *   2. Check membership with hasItem / hasItems
 *   3. Know the difference between contains and containsInAnyOrder
 *   4. Apply a matcher to every element with everyItem
 *   5. Inspect Maps with hasKey / hasValue / hasEntry / aMapWithSize
 *   6. Inspect arrays with arrayWithSize / arrayContaining / hasItemInArray
 *   7. Check that a value is one of several options with in / oneOf
 *
 * Key Concept:
 *   Checking a List with plain JUnit usually means several assertions:
 *     assertEquals(3, names.size());
 *     assertTrue(names.contains("Alice"));
 *
 *   Hamcrest collection matchers do it in one readable line, and when a
 *   check fails they print the WHOLE collection so you can see what was wrong.
 *
 * WATCH OUT -- contains() is not the same as List.contains():
 *   hasItem("Alice")          -- "Alice" is somewhere in the list
 *   contains("Bob", "Alice")  -- the list is EXACTLY [Bob, Alice], in that order
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab04_CollectionMatchers
 *
 * Target classes: Course, Student
 */
public class Lab04_CollectionMatchers {

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

        // Enrollment order: Bob, Alice, Carol
        course.enroll(bob);
        course.enroll(alice);
        course.enroll(carol);
    }

    // ============================================================
    // STEP 1 -- hasSize / empty
    //
    // hasSize(n) -- the collection has exactly n elements
    // empty()    -- the collection has no elements
    // ============================================================
    @Test
    void sizeAndEmptiness() {
        // assertThat(course.getStudents(), hasSize(3));
        // assertThat(course.getStudents(), is(not(empty())));

        // Course newCourse = new Course("SE4940", "Software Quality", 20);
        // assertThat(newCourse.getStudents(), is(empty()));
    }

    // ============================================================
    // STEP 2 -- hasItem / hasItems
    //
    // hasItem(x)       -- x appears at least once
    // hasItems(x, y)   -- x AND y both appear (order does not matter)
    // ============================================================
    @Test
    void membership() {
        // assertThat(course.getStudentNames(), hasItem("Alice"));
        // assertThat(course.getStudentNames(), hasItems("Carol", "Alice"));
        // assertThat(course.getStudentNames(), not(hasItem("Zoe")));
    }

    // ============================================================
    // STEP 3 -- contains vs containsInAnyOrder
    //
    // contains(a, b, c)           -- exactly these elements, in this order
    // containsInAnyOrder(a, b, c) -- exactly these elements, any order
    //
    // getStudentNames()       -> [Bob, Alice, Carol]   (enrollment order)
    // getSortedStudentNames() -> [Alice, Bob, Carol]   (alphabetical)
    // ============================================================
    @Test
    void containsVsContainsInAnyOrder() {
        // assertThat(course.getStudentNames(), contains("Bob", "Alice", "Carol"));
        // assertThat(course.getSortedStudentNames(), contains("Alice", "Bob", "Carol"));

        // assertThat(course.getStudentNames(), containsInAnyOrder("Alice", "Bob", "Carol"));
        // assertThat(course.getStudentNames(), not(contains("Alice", "Bob", "Carol")));
    }

    // ============================================================
    // STEP 4 -- everyItem
    //
    // everyItem(matcher) -- EVERY element must match the given matcher.
    // You can pass any matcher you have learned so far.
    // ============================================================
    @Test
    void everyItemMatches() {
        // assertThat(Arrays.asList(course.getEmailList()), everyItem(endsWith("@example.com")));
        // assertThat(course.getStudentNames(), everyItem(not(emptyString())));
    }

    // ============================================================
    // STEP 5 -- Map matchers
    //
    // getMajorCounts() -> {Computer Science=2, Software Engineering=1}
    //
    // hasKey(k)          -- the map contains key k
    // hasValue(v)        -- the map contains value v
    // hasEntry(k, v)     -- the map contains the pair k -> v
    // aMapWithSize(n)    -- the map has exactly n entries
    // ============================================================
    @Test
    void mapMatchers() {
        // System.out.println("Major counts: " + course.getMajorCounts());

        // assertThat(course.getMajorCounts(), hasKey("Computer Science"));
        // assertThat(course.getMajorCounts(), hasValue(1));
        // assertThat(course.getMajorCounts(), hasEntry("Computer Science", 2));
        // assertThat(course.getMajorCounts(), not(hasKey("Mathematics")));
        // assertThat(course.getMajorCounts(), aMapWithSize(2));
    }

    // ============================================================
    // STEP 6 -- Array matchers
    //
    // getEmailList() returns a String[] in enrollment order.
    //
    // arrayWithSize(n)              -- array has n elements
    // hasItemInArray(x)             -- x appears in the array
    // arrayContaining(a, b, c)      -- exactly these elements, in order
    // arrayContainingInAnyOrder(..) -- exactly these elements, any order
    // ============================================================
    @Test
    void arrayMatchers() {
        // String[] emails = course.getEmailList();

        // assertThat(emails, arrayWithSize(3));
        // assertThat(emails, hasItemInArray("alice@example.com"));
        // assertThat(emails, arrayContaining("bob@example.com", "alice@example.com", "carol@example.com"));
        // assertThat(emails, arrayContainingInAnyOrder("carol@example.com", "bob@example.com", "alice@example.com"));
    }

    // ============================================================
    // STEP 7 -- in / oneOf
    //
    // The reverse question: is the ACTUAL value one of the allowed options?
    //
    // is(in(collection)) -- actual is an element of the collection
    // is(oneOf(a, b, c)) -- actual equals one of the listed values
    // ============================================================
    @Test
    void valueIsOneOfOptions() {
        // assertThat(bob.getMajor(), is(in(Arrays.asList("Computer Science", "Software Engineering"))));
        // assertThat(course.getCode(), is(oneOf("SE4930", "SE4940")));
    }
}
