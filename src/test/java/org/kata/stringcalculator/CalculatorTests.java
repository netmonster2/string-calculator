package org.kata.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
