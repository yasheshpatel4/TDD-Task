package org.example;

import java.util.*;
import java.util.regex.*;

public class StringCalculator {

    // Adds numbers from a string with support for custom/multiple delimiters; throws on negatives, overflow, or invalid input
    public static int add(String numbers) {
        if (numbers == null || numbers.trim().isEmpty()) return 0;

        String delimiterRegex = ",|\n"; // Default delimiters
        String input = numbers;
        int custom_operator=0;
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
                if(delimiterSection.equals("*")){
                    custom_operator=1;
                    delimiterRegex= String.join("|","*");
                }

                delimiterRegex = Pattern.quote(delimiterSection); // Single custom delimiter
            }
        }

        String[] tokens = input.split(delimiterRegex);
        int sum = 0;
        int mul_ans=1;
        List<Integer> negativeNumbers = new ArrayList<>();

        for (String token : tokens) {
            if (!token.trim().isEmpty()) {
                try {
                    int number = Integer.parseInt(token.trim());
                    if (number < 0) {
                        negativeNumbers.add(number); // Track negatives
                    } else if(custom_operator==0){
                        sum = Math.addExact(sum, number); // Throws on overflow
                    }
                    else{
                        mul_ans*=(number);
                    }
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Invalid number format: '" + token.trim() + "'");
                }
            }
        }

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers not allowed: " + negativeNumbers);
        }
        if(custom_operator==0){
            return sum;
        }
        else{
            return mul_ans;
        }

    }
}
