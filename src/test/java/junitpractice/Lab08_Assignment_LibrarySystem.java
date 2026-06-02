package junitpractice;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lab 08 - Assignment: Library Book System
 *
 * Instructions:
 *   Implement all test methods marked with TODO.
 *   Do NOT modify method signatures or @DisplayName values.
 *   Run: mvn test -Dtest=Lab08_Assignment_LibrarySystem
 *
 * Requirements:
 *   - All TODO methods must contain at least one assertion
 *   - Use @Nested groups as provided
 *   - Use appropriate assertions from Labs 02 and 03
 *
 * Target class: LibraryBook (src/main/java/junitpractice/LibraryBook.java)
 *
 *   LibraryBook(String title, String author, String isbn)
 *     - throws IllegalArgumentException if any argument is null or blank
 *
 *   void borrow(String borrowerName)
 *     - throws IllegalArgumentException if borrowerName is null or blank
 *     - throws IllegalStateException if book is already borrowed
 *
 *   void returnBook()
 *     - throws IllegalStateException if book is not currently borrowed
 *
 *   boolean isAvailable()
 *   String getTitle()
 *   String getAuthor()
 *   String getIsbn()
 *   String getBorrowedBy()  -- returns null if not borrowed
 */
@DisplayName("LibraryBook")
public class Lab08_Assignment_LibrarySystem {

    LibraryBook book;

    @BeforeEach
    void setUp() {
        book = new LibraryBook("Clean Code", "Robert Martin", "978-0132350884");
    }

    // ============================================================
    // Part 1 -- Basic object creation
    // ============================================================

    @Test
    @DisplayName("Book is created with correct title, author, and isbn")
    void bookCreatedWithCorrectInfo() {
        // TODO: verify title, author, and isbn using assertAll
    }

    @Test
    @DisplayName("New book is available by default")
    void newBookIsAvailable() {
        // TODO: verify isAvailable() returns true
    }

    @Test
    @DisplayName("New book has no borrower")
    void newBookHasNoBorrower() {
        // TODO: verify getBorrowedBy() returns null
    }

    // ============================================================
    // Part 2 -- Invalid construction
    // ============================================================

    @ParameterizedTest
    @DisplayName("Creating book with blank title throws IllegalArgumentException")
    @ValueSource(strings = {"", " ", "   "})
    void blankTitleThrowsException(String blankTitle) {
        // TODO: verify IllegalArgumentException is thrown
    }

    @Test
    @DisplayName("Creating book with null author throws IllegalArgumentException")
    void nullAuthorThrowsException() {
        // TODO: verify IllegalArgumentException is thrown
    }

    // ============================================================
    // Part 3 -- Borrow and return (use @Nested)
    // ============================================================

    @Nested
    @DisplayName("when book is available")
    class WhenAvailable {

        @Test
        @DisplayName("borrow() makes book unavailable")
        void borrowMakesBookUnavailable() {
            // TODO: borrow the book, then verify isAvailable() returns false
        }

        @Test
        @DisplayName("borrow() records the borrower name")
        void borrowRecordsBorrowerName() {
            // TODO: borrow the book, then verify getBorrowedBy() equals the borrower name
        }

        @Test
        @DisplayName("borrow() with blank name throws IllegalArgumentException")
        void borrowWithBlankNameThrows() {
            // TODO: verify IllegalArgumentException is thrown when borrowing with blank name
        }

        @Test
        @DisplayName("returnBook() on available book throws IllegalStateException")
        void returnAvailableBookThrows() {
            // TODO: verify IllegalStateException is thrown when returning a book that is not borrowed
        }
    }

    @Nested
    @DisplayName("when book is borrowed")
    class WhenBorrowed {

        @BeforeEach
        void borrowBook() {
            book.borrow("Alice");
        }

        @Test
        @DisplayName("isAvailable() returns false")
        void bookIsNotAvailable() {
            // TODO: verify isAvailable() returns false
        }

        @Test
        @DisplayName("borrow() again throws IllegalStateException")
        void borrowAgainThrows() {
            // TODO: verify IllegalStateException is thrown when borrowing an already-borrowed book
        }

        @Test
        @DisplayName("exception message contains current borrower name")
        void exceptionMessageContainsBorrowerName() {
            // TODO: verify the exception message contains "Alice"
        }

        @Test
        @DisplayName("returnBook() makes book available again")
        void returnMakesBookAvailable() {
            // TODO: return the book, then verify isAvailable() returns true
        }

        @Test
        @DisplayName("returnBook() clears the borrower name")
        void returnClearsBorrowerName() {
            // TODO: return the book, then verify getBorrowedBy() returns null
        }

        @Test
        @DisplayName("book can be borrowed again after return")
        void canBorrowAfterReturn() {
            // TODO: return the book, then borrow again with a different name, verify new borrower
        }
    }
}
