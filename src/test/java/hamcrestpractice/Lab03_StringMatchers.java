package hamcrestpractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Lab 03 - String Matchers
 *
 * Learning Objectives:
 *   1. Check parts of a String with containsString / startsWith / endsWith
 *   2. Ignore case with equalToIgnoringCase / containsStringIgnoringCase
 *   3. Ignore extra whitespace with equalToCompressingWhiteSpace
 *   4. Check for empty or blank Strings
 *   5. Match a regular expression with matchesPattern
 *   6. Check that substrings appear in order with stringContainsInOrder
 *
 * Key Concept:
 *   Real-world Strings (page titles, messages, summaries) often contain
 *   details that change -- counts, timestamps, spacing.
 *   String matchers let you assert only the part that matters,
 *   so your tests do not break for unrelated reasons.
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab03_StringMatchers
 *
 * Target classes: Course, Student
 */
public class Lab03_StringMatchers {

    Course course;
    Student alice;

    @BeforeEach
    void setUp() {
        course = new Course("SE4930", "Software Testing", 30);
        alice = new Student(1, "Alice", "alice@example.com", "Computer Science", 3.8);
        course.enroll(alice);
    }

    // ============================================================
    // STEP 1 -- containsString / startsWith / endsWith
    //
    // course.getSummary() returns "SE4930 - Software Testing (1/30 enrolled)"
    // ============================================================
    @Test
    void partialStringMatching() {
        // System.out.println("Summary: " + course.getSummary());

        // assertThat(course.getSummary(), containsString("Software Testing"));
        // assertThat(course.getSummary(), startsWith("SE4930"));
        // assertThat(course.getSummary(), endsWith("enrolled)"));
    }

    // ============================================================
    // STEP 2 -- Ignoring case
    //
    // equalToIgnoringCase(s)        -- whole String, case does not matter
    // containsStringIgnoringCase(s) -- substring, case does not matter
    // ============================================================
    @Test
    void ignoringCase() {
        // assertThat(course.getTitle(), equalToIgnoringCase("SOFTWARE TESTING"));
        // assertThat(course.getSummary(), containsStringIgnoringCase("software testing"));
    }

    // ============================================================
    // STEP 3 -- equalToCompressingWhiteSpace
    //
    // Trims leading/trailing whitespace and collapses runs of
    // whitespace into a single space before comparing.
    // Handy for text copied from a web page or a file.
    // ============================================================
    @Test
    void ignoringExtraWhitespace() {
        // String messy = "   Software     Testing   ";

        // assertThat(messy, is(not(equalTo("Software Testing"))));
        // assertThat(messy, equalToCompressingWhiteSpace("Software Testing"));
    }

    // ============================================================
    // STEP 4 -- emptyString / blankString / emptyOrNullString
    //
    // emptyString()       -- ""
    // blankString()       -- "" or only whitespace, e.g. "   "
    // emptyOrNullString() -- null or ""
    // ============================================================
    @Test
    void emptyAndBlankStrings() {
        // assertThat("", is(emptyString()));
        // assertThat("   ", is(blankString()));
        // assertThat((String) null, is(emptyOrNullString()));

        // assertThat(course.getTitle(), is(not(emptyOrNullString())));
    }

    // ============================================================
    // STEP 5 -- matchesPattern (regular expressions)
    //
    // matchesPattern(regex) -- the WHOLE String must match the regex.
    //
    //   [A-Z]{2}\d{4}       -- two capital letters followed by four digits
    //   [a-z]+@example\.com -- lowercase letters, then @example.com
    //
    // In a Java String literal, a backslash must be written as \\
    // ============================================================
    @Test
    void regularExpressions() {
        // assertThat(course.getCode(), matchesPattern("[A-Z]{2}\\d{4}"));
        // assertThat(alice.getEmail(), matchesPattern("[a-z]+@example\\.com"));
    }

    // ============================================================
    // STEP 6 -- stringContainsInOrder
    //
    // Passes when ALL substrings appear, in the given order.
    // Other text may appear between them.
    // ============================================================
    @Test
    void substringsInOrder() {
        // assertThat(course.getSummary(), stringContainsInOrder("SE4930", "Software", "enrolled"));
        // assertThat(course.getSummary(), not(stringContainsInOrder("enrolled", "SE4930")));
    }
}
