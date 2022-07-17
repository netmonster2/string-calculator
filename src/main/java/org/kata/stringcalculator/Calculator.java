package org.kata.stringcalculator;

import java.util.Arrays;

public class Calculator {

    public int add(String numbers) {
        if (numbers == null || numbers.isBlank())
            return 0;
        else if (numbers.contains(",")) {
            String[] numberArray = numbers.split(",");
            return Arrays.stream(numberArray).map(Integer::parseInt).reduce(0, Integer::sum);
        } else
            return -1;
    }
}
