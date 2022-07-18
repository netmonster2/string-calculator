package org.kata.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorTests {

    private Calculator calculator;

    @BeforeEach
    public void init() {
        this.calculator = new Calculator();
    }

    @DisplayName("When the input is an a null/empty/whitespace or null string, the calculator returns 0")
    @Test
    public void emptyStringSum() {
        assertAll("The sum of a a null/empty/whitespace string should be 0",
                () -> assertEquals(0, calculator.add(""), "The sum of an empty string should be 0"),
                () -> assertEquals(0, calculator.add("       "), "The sum of a whitespace string should be 0"),
                () -> assertEquals(0, calculator.add(null), "The sum of a null string should be 0"));
    }

    @DisplayName("When only one number is provided as input, it should be returned as it is")
    @Test
    public void oneNumberSum() {
        String numbers = "34";
        assertEquals(34, calculator.add(numbers), "The number provided as input is not returned");
    }

    @DisplayName("When the input string has comma-separated numbers, their sum should be returned")
    @Test
    public void commaSeparatedNumbers() {
        int numberOfParts = getRandomInt(3, 100);

        StringBuilder generatedNumbersBuilder = new StringBuilder();
        int expectedSum = 0;

        for (int i = 0; i < numberOfParts; i++) {
            int random = getRandomInt(0, 500);
            expectedSum += random;
            generatedNumbersBuilder
                    .append(random)
                    .append(",");
        }
        generatedNumbersBuilder.deleteCharAt(generatedNumbersBuilder.length() - 1);

        int actualSum = calculator.add(generatedNumbersBuilder.toString());

        assertEquals(expectedSum, actualSum, "The sum of comma-separated numbers is incorrect");
    }

    @DisplayName("When the input string has comma-separated and newline-separated values, their sum should be returned")
    @Test
    public void newLineWithCommaSeparatedNumbers() {
        String numbers = "1\n2,3\n4\n6,9";

        assertEquals(25, calculator.add(numbers), "The sum of comma-separated numbers is incorrect");
    }

    @DisplayName("When the input string has consecutively more than one delimiter between two numbers, " +
            "the input is considered invalid and an exception is thrown")
    @Test
    public void multipleConsecutiveDelimitersInvalidInput() {
        String numbers = "1,\n2,3\n,4\n6,9";

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> calculator.add(numbers),
                "The Illegal Argument Exception was not raised for an invalid input");

        assertEquals("Only one delimiter is allowed between two numbers", ex.getMessage(),
                "Exception message is incorrect");
    }

    @DisplayName("When the input has a first line specifying a custom number delimiter," +
            " it should be used to calculate the sum")
    @Test
    public void customNumberDelimiter() {
        String numbers = "//a\n1a2a3a4a6a9";

        assertEquals(25, calculator.add(numbers), "The sum of custom delimiter numbers is incorrect");
    }

    @DisplayName("When negative numbers are present, an exceptions should be raised mentioning them")
    @Test
    public void negativeNumbersNotAccepted() {
        String numbers = "//;\n1;-2;3;-4;6;9;-11";

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> calculator.add(numbers),
                "Negative numbers are not allowed");

        assertAll("All negative numbers should be mentioned in the exception message",
                () -> assertTrue(StringUtils.isNotBlank(ex.getMessage())
                        && ex.getMessage().contains("-2"), "The -2 was not mentioned in the exception"),
                () -> assertTrue(StringUtils.isNotBlank(ex.getMessage())
                        && ex.getMessage().contains("-4"), "The -4 was not mentioned in the exception"),
                () -> assertTrue(StringUtils.isNotBlank(ex.getMessage())
                        && ex.getMessage().contains("-11"), "The -11 was not mentioned in the exception"));
    }

    @DisplayName("When an invalid input is given, an exception should be raised")
    @Test
    public void cornerCases() {
        String alphabeticCharsPresent = "//;\n1;2;3;4;6;b";
        String startingWithDelimiter = "//;\n;1;2;3;4;6";
        String endingWithDelimiter = "//;\n1;2;3;4;6;";

        assertAll("Invalid input should raise the respective exception",
                () -> {
                    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                            () -> calculator.add(alphabeticCharsPresent),
                            "Exception was not raised for an input with alphabetic characters");
                    assertEquals("Only numbers and delimiters are allowed", ex.getMessage(),
                            "The exception doesn't have the correct message");
                },
                () -> {
                    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                            () -> calculator.add(startingWithDelimiter),
                            "Exception was not raised for an input starting with delimiter");
                    assertEquals("Starting or ending with delimiter is not allowed", ex.getMessage(),
                            "The exception doesn't have the correct message");
                },
                () -> {
                    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                            () -> calculator.add(endingWithDelimiter),
                            "Exception was not raised for an input ending with delimiter");
                    assertEquals("Starting or ending with delimiter is not allowed", ex.getMessage(),
                            "The exception doesn't have the correct message");
                });
    }


    private int getRandomInt(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }
}
