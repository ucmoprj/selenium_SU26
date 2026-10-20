package hamcrestpractice;

import java.util.Objects;

/**
 * A simple student record used as a test target in the Hamcrest labs.
 *
 * Follows JavaBean conventions (getX / isX getters) so that bean matchers
 * such as hasProperty() and samePropertyValuesAs() can read its properties.
 *
 * Two students are equal when they have the same id.
 */
public class Student {

    public static final double HONORS_GPA = 3.5;

    private final int id;
    private final String name;
    private final String email;
    private final String major;
    private final double gpa;

    public Student(int id, String name, String email, String major, double gpa) {
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0");
        }
        this.id = id;
        this.name = name;
        this.email = email;
        this.major = major;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMajor() {
        return major;
    }

    public double getGpa() {
        return gpa;
    }

    /**
     * A student is on the honors list when GPA >= 3.5.
     */
    public boolean isHonors() {
        return gpa >= HONORS_GPA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        return id == ((Student) o).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', major='" + major + "', gpa=" + gpa + "}";
    }
}
