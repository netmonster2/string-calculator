package org.kata.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        int expectedSum = 25;

        assertEquals(expectedSum, calculator.add(numbers), "The sum of comma-separated numbers is incorrect");
    }

    private int getRandomInt(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }
}
