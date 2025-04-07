package com.orangehrm.utils;

public class NumberExtractorUtility {

    public static int extractNumberFromParentheses(String text) {
        // Regex pattern to match number inside parentheses
        String numericString = text.replaceAll(".*?\\((\\d+)\\).*", "$1");

        if (numericString.isEmpty() || numericString.equals(text)) {
            throw new NumberFormatException("No number in parentheses found in text: " + text);
        }

        return Integer.parseInt(numericString);
    }
}