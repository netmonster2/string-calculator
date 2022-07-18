package org.kata.stringcalculator;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {

    private static final String[] DEFAULT_DELIMITERS = {"\n", ","};

    public int add(String numbers) {
        String sumExpression;
        if (numbers == null || numbers.isBlank())
            return 0;
        else if (numbers.startsWith("//") && numbers.indexOf('\n') == 3) {
            char customDelimiter = numbers.charAt(2);
            sumExpression = numbers.substring(4);
            sumExpression = prepareExpression(sumExpression, String.valueOf(customDelimiter));
        } else {
            sumExpression = prepareExpression(numbers, DEFAULT_DELIMITERS);
        }

        return evaluateMathExpression(sumExpression);
    }

    /**
     * Prepare the expression to be calculated
     *
     * @param input      The expression
     * @param delimiters The expected delimiters
     * @return The prepared expression
     */
    private String prepareExpression(String input, String... delimiters) {
        String result = input.trim().replace(" ", "");
        for (String delimiter : delimiters) {
            result = result.replace(delimiter, "+");
        }

        validateExpression(result);

        return result;
    }

    /**
     * Validate the expression
     *
     * @param result The expression to check
     */
    private void validateExpression(String result) {
        if (result.contains("-")) {
            List<String> negativeNumbers = new ArrayList<>();
            Arrays.stream(result.split("\\+")).forEach(element -> {
                if (NumberUtils.toInt(element) < 0)
                    negativeNumbers.add(element);
            });

            throw new IllegalArgumentException("Negative numbers are present: " + negativeNumbers);
        }

        if (result.contains("++"))
            throw new IllegalArgumentException("Only one delimiter is allowed between two numbers");

        if (!NumberUtils.isDigits(result.replace("+", "")))
            throw new IllegalArgumentException("Only numbers and delimiters are allowed");

        if (result.startsWith("+") || result.endsWith("+"))
            throw new IllegalArgumentException("Starting or ending with delimiter is not allowed");
    }

    /**
     * Evaluate the math expression to calculate the result
     *
     * @param exp Expression to evaluate
     * @return The calculation result
     */
    private int evaluateMathExpression(String exp) {
        DoubleEvaluator evaluator = new DoubleEvaluator();
        return evaluator.evaluate(exp).intValue();
    }

}
