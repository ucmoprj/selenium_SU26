package junitpractice;

/**
 * Represents a library book that can be borrowed and returned.
 *
 * Rules:
 *   - A book can only be borrowed by one person at a time.
 *   - Borrowing an already-borrowed book throws IllegalStateException.
 *   - Returning a book that is not borrowed throws IllegalStateException.
 *   - Borrower name must not be null or blank.
 */
public class LibraryBook {

    private final String title;
    private final String author;
    private final String isbn;
    private boolean borrowed;
    private String borrowedBy;

    public LibraryBook(String title, String author, String isbn) {
        if (title == null || title.isBlank())  throw new IllegalArgumentException("Title cannot be blank");
        if (author == null || author.isBlank()) throw new IllegalArgumentException("Author cannot be blank");
        if (isbn == null || isbn.isBlank())    throw new IllegalArgumentException("ISBN cannot be blank");
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.borrowed = false;
        this.borrowedBy = null;
    }

    public void borrow(String borrowerName) {
        if (borrowerName == null || borrowerName.isBlank()) {
            throw new IllegalArgumentException("Borrower name cannot be blank");
        }
        if (borrowed) {
            throw new IllegalStateException("Book is already borrowed by: " + borrowedBy);
        }
        this.borrowed = true;
        this.borrowedBy = borrowerName;
    }

    public void returnBook() {
        if (!borrowed) {
            throw new IllegalStateException("Book is not currently borrowed");
        }
        this.borrowed = false;
        this.borrowedBy = null;
    }

    public boolean isAvailable() {
        return !borrowed;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public String getBorrowedBy() { return borrowedBy; }
}
