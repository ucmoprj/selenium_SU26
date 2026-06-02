package junitpractice;

/**
 * A simple calculator used as a test target in JUnit labs.
 *
 * Covers: add, subtract, multiply, divide
 * divide() throws ArithmeticException when divisor is zero.
 */
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }
}

