package junitpractice;

/**
 * String utility methods used as a test target in JUnit labs.
 *
 * Covers: reverse, palindrome check, word count, capitalize
 * Good for Assumptions and boundary-value tests.
 */
public class StringUtils {

    /**
     * Returns the reversed string. Returns null if input is null.
     */
    public String reverse(String input) {
        if (input == null) return null;
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * Returns true if the string reads the same forwards and backwards (case-insensitive).
     * Returns false for null or blank input.
     */
    public boolean isPalindrome(String input) {
        if (input == null || input.isBlank()) return false;
        String cleaned = input.replaceAll("\\s+", "").toLowerCase();
        return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
    }

    /**
     * Returns the number of words separated by whitespace.
     * Returns 0 for null or blank input.
     */
    public int countWords(String input) {
        if (input == null || input.isBlank()) return 0;
        return input.trim().split("\\s+").length;
    }

    /**
     * Capitalizes the first letter of each word.
     * Returns null if input is null.
     */
    public String capitalizeWords(String input) {
        if (input == null) return null;
        if (input.isBlank()) return input;
        String[] words = input.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(Character.toUpperCase(word.charAt(0)))
              .append(word.substring(1).toLowerCase())
              .append(" ");
        }
        return sb.toString().trim();
    }
}

