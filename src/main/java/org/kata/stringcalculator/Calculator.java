package org.kata.stringcalculator;

import org.apache.commons.lang3.math.NumberUtils;

public class Calculator {

    public int add(String numbers) {
        if (numbers == null || numbers.isBlank())
            return 0;
        else if (NumberUtils.isDigits(numbers)) {
            return NumberUtils.toInt(numbers);
        } else {
            numbers = numbers.replace("\n", ",");
            String firstPart = numbers.substring(0, numbers.indexOf(",") + 1).replace(",", "");
            int firstNumber = NumberUtils.isDigits(firstPart) ? NumberUtils.toInt(firstPart) : 0;

            String remainder = numbers.substring(numbers.indexOf(",") + 1);
            return firstNumber + add(remainder);
        }
    }
}
