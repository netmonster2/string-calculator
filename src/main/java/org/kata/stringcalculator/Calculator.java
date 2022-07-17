package org.kata.stringcalculator;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import org.apache.commons.lang3.math.NumberUtils;

public class Calculator {

    private static final String[] DEFAULT_SEPARATORS = {"\n", ","};

    public int add(String numbers) {
        String sumExpression;
        if (numbers == null || numbers.isBlank())
            return 0;
        else if (NumberUtils.isDigits(numbers)) {
            return NumberUtils.toInt(numbers);
        } else if (numbers.startsWith("//") && numbers.indexOf('\n') == 3) {
            char customSeparator = numbers.charAt(2);
            sumExpression = numbers.substring(4);
            sumExpression = prepareExpression(sumExpression, String.valueOf(customSeparator));
        } else {
            sumExpression = prepareExpression(numbers, DEFAULT_SEPARATORS);
        }

        return evaluateMathExpression(sumExpression);
    }

    private String prepareExpression(String input, String... separators) {
        String result = input;
        for (String separator : separators) {
            result = result.replace(separator, "+");
        }
        return result;
    }

    private int evaluateMathExpression(String exp) {
        DoubleEvaluator evaluator = new DoubleEvaluator();
        return evaluator.evaluate(exp).intValue();
    }

}
