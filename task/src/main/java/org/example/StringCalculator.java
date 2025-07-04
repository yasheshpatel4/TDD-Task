package org.example;

import java.util.*;
import java.util.regex.*;

public class StringCalculator {

    // Adds numbers from a string with support for custom/multiple delimiters; throws on negatives, overflow, or invalid input
    public static int add(String numbers) {
        if (numbers == null || numbers.trim().isEmpty()) return 0;

        String delimiterRegex = ",|\n"; // Default delimiters
        String input = numbers;

        // Check for custom delimiter syntax
        if (numbers.startsWith("//")) {
            int newlineIndex = numbers.indexOf("\n");
            String delimiterSection = numbers.substring(2, newlineIndex);
            input = numbers.substring(newlineIndex + 1);

            // Handle multiple or multi-character delimiters
            if (delimiterSection.contains("[")) {
                List<String> delimiters = new ArrayList<>();
                Matcher matcher = Pattern.compile("\\[(.*?)]").matcher(delimiterSection);
                while (matcher.find()) {
                    delimiters.add(Pattern.quote(matcher.group(1)));
                }
                delimiterRegex = String.join("|", delimiters);
            } else {
                delimiterRegex = Pattern.quote(delimiterSection); // Single custom delimiter
            }
        }

        String[] tokens = input.split(delimiterRegex);
        int sum = 0;
        List<Integer> negativeNumbers = new ArrayList<>();

        for (String token : tokens) {
            if (!token.trim().isEmpty()) {
                try {
                    int number = Integer.parseInt(token.trim());
                    if (number < 0) {
                        negativeNumbers.add(number); // Track negatives
                    } else {
                        sum = Math.addExact(sum, number); // Throws on overflow
                    }
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Invalid number format: '" + token.trim() + "'");
                }
            }
        }

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers not allowed: " + negativeNumbers);
        }

        return sum;
    }
}
