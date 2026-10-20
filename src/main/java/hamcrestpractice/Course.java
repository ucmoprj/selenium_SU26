package hamcrestpractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * A course with a limited number of seats, used as a test target in the Hamcrest labs.
 *
 * Covers: enrollment, roster queries, GPA statistics, grouping by major.
 * Returns plenty of Strings, numbers, Lists and Maps -- good material for matchers.
 */
public class Course {

    private final String code;
    private final String title;
    private final int capacity;
    private final List<Student> students = new ArrayList<>();

    public Course(String code, String title, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.code = code;
        this.title = title;
        this.capacity = capacity;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * Enrolls a student.
     * Throws IllegalStateException if the course is full.
     * Throws IllegalArgumentException if the student is already enrolled.
     */
    public void enroll(Student student) {
        if (isFull()) {
            throw new IllegalStateException("Course is full: " + code);
        }
        if (students.contains(student)) {
            throw new IllegalArgumentException("Already enrolled: " + student.getName());
        }
        students.add(student);
    }

    public void drop(Student student) {
        if (!students.remove(student)) {
            throw new IllegalArgumentException("Not enrolled: " + student.getName());
        }
    }

    public boolean isFull() {
        return students.size() >= capacity;
    }

    /**
     * Returns the enrolled students in enrollment order (read-only).
     */
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    /**
     * Returns student names in enrollment order.
     */
    public List<String> getStudentNames() {
        return students.stream().map(Student::getName).collect(Collectors.toList());
    }

    /**
     * Returns student names sorted alphabetically.
     */
    public List<String> getSortedStudentNames() {
        return students.stream().map(Student::getName).sorted().collect(Collectors.toList());
    }

    /**
     * Returns the students whose GPA is on the honors list.
     */
    public List<Student> getHonorsStudents() {
        return students.stream().filter(Student::isHonors).collect(Collectors.toList());
    }

    /**
     * Returns the student with the given name, or null if not enrolled.
     */
    public Student findByName(String name) {
        return students.stream()
                .filter(s -> s.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the average GPA, or 0.0 if no students are enrolled.
     */
    public double getAverageGpa() {
        return students.stream().mapToDouble(Student::getGpa).average().orElse(0.0);
    }

    /**
     * Returns the number of students per major, sorted by major name.
     */
    public Map<String, Integer> getMajorCounts() {
        Map<String, Integer> counts = new TreeMap<>();
        for (Student s : students) {
            counts.merge(s.getMajor(), 1, Integer::sum);
        }
        return counts;
    }

    /**
     * Returns the email addresses of all enrolled students as an array.
     */
    public String[] getEmailList() {
        return students.stream().map(Student::getEmail).toArray(String[]::new);
    }

    /**
     * Returns a one-line summary, e.g. "SE4930 - Software Testing (2/30 enrolled)".
     */
    public String getSummary() {
        return code + " - " + title + " (" + students.size() + "/" + capacity + " enrolled)";
    }
}
