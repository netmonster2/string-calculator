package org.kata.stringcalculator;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import org.apache.commons.lang3.math.NumberUtils;

public class Calculator {

    private static final String[] SEPARATORS = {"\n", ","};

    public int add(String numbers) {
        if (numbers == null || numbers.isBlank())
            return 0;
        else if (NumberUtils.isDigits(numbers)) {
            return NumberUtils.toInt(numbers);
        } else {
            for (String separator : SEPARATORS) {
                numbers = numbers.replace(separator, "+");
            }

            DoubleEvaluator evaluator = new DoubleEvaluator();
            return evaluator.evaluate(numbers).intValue();
        }
    }

}
