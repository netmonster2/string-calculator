package org.kata.stringcalculator;

import java.util.Scanner;

public class Application {

    private static final String EXIT_COMMAND = "exit";

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder();

        System.out.println("Welcome to the String calculator. Please provide the numbers and hit enter twice to calculate: ");

        System.out.print("-->");
        String lastInput = scanner.nextLine();
        getUserInput(scanner, input, lastInput);

        while (!EXIT_COMMAND.equals(input.toString())) {
            try {
                System.out.printf("Result for '%s': %s%n", input, calculator.add(input.toString()));
            } catch (IllegalArgumentException ex) {
                System.out.printf("Invalid input '%s', please try again: %s%n", input, ex.getMessage());
            }
            input = new StringBuilder();

            System.out.print("-->");
            lastInput = scanner.nextLine();
            getUserInput(scanner, input, lastInput);

        }
    }

    private static void getUserInput(Scanner scanner, StringBuilder input, String lastInput) {
        while (!lastInput.isBlank()) {
            input.append(lastInput).append("\n");
            lastInput = scanner.nextLine();
        }
    }
}
