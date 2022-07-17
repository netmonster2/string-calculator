package org.kata.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTests {

    private Calculator calculator;

    @BeforeEach
    public void init() {
        this.calculator = new Calculator();
    }

    @DisplayName("When the input is an an empty or null string, the calculator returns 0")
    @Test
    public void emptyStringSum() {
        int emptySum = calculator.add("");
        assertEquals(0, emptySum, "The sum of an empty string should be 0");
    }
}
