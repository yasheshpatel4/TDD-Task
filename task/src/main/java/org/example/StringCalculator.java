package org.example;

import java.util.*;
import java.util.regex.*;

public class StringCalculator {

    /**
     * Adds numbers from a string input. Supports:
     * - Comma and newline as default delimiters
     * - Custom delimiters: single, multiple, or multi-character
     * - Throws exception for negative numbers (lists all)
     *
     * @param numbers The input string
     * @return The sum of numbers
     * @throws IllegalArgumentException for negative numbers
     * @throws NumberFormatException for invalid integer parsing
     */
    public static int add(String numbers) {
        if (numbers == null || numbers.trim().isEmpty()) return 0;

        String delimiterRegex = ",|\n";
        String input = numbers;

        // Custom delimiter format: //;\n or //[*][%%]\n
        if (numbers.startsWith("//")) {
            int newlineIndex = numbers.indexOf("\n");
            String delimiterSection = numbers.substring(2, newlineIndex);
            input = numbers.substring(newlineIndex + 1);

            if (delimiterSection.contains("[")) {
                List<String> delimiters = new ArrayList<>();
                Matcher matcher = Pattern.compile("\\[(.*?)]").matcher(delimiterSection);
                while (matcher.find()) {
                    delimiters.add(Pattern.quote(matcher.group(1)));
                }
                delimiterRegex = String.join("|", delimiters);
            } else {
                delimiterRegex = Pattern.quote(delimiterSection);
            }
        }

        String[] tokens = input.split(delimiterRegex);
        int sum = 0;
        List<Integer> negativeNumbers = new ArrayList<>();

        for (String token : tokens) {
            if (!token.trim().isEmpty()) {
                int number = Integer.parseInt(token.trim());
                if (number < 0) {
                    negativeNumbers.add(number);
                } else {
                    sum = Math.addExact(sum, number); // Ensure no overflow
                }
            }
        }

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers not allowed: " + negativeNumbers);
        }

        return sum;
    }
}