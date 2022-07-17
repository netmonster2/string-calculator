package org.kata.stringcalculator;

public class Calculator {

    public int add(String numbers) {
        if (numbers == null || numbers.isBlank())
            return 0;
        else
            return -1;
    }
}
