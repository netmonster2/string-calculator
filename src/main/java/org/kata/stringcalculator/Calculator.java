package org.kata.stringcalculator;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import org.apache.commons.lang3.math.NumberUtils;

public class Calculator {

    private static final String[] DEFAULT_SEPARATORS = {"\n", ","};

    public int add(String numbers) {
        if (numbers == null || numbers.isBlank())
            return 0;
        else if (NumberUtils.isDigits(numbers)) {
            return NumberUtils.toInt(numbers);
        } else if (numbers.startsWith("//") && numbers.indexOf('\n') == 3) {
            char customSeparator = numbers.charAt(2);
            String numbersToSum = numbers.substring(4);
            numbersToSum = numbersToSum.replace(customSeparator, '+');
            return add(numbersToSum);
        } else {
            for (String separator : DEFAULT_SEPARATORS) {
                numbers = numbers.replace(separator, "+");
            }

            DoubleEvaluator evaluator = new DoubleEvaluator();
            return evaluator.evaluate(numbers).intValue();
        }
    }

}
