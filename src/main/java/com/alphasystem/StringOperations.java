package com.alphasystem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class StringOperations {

    private static final int VALUE_OF_A = 'a';

    public static String findShortestSubstring(String source, String chars) {
        return findShortestSubstring(source.toLowerCase(), chars.chars().mapToObj(c -> (char) c).toArray(Character[]::new));
    }

    public static String findShortestSubstring(String source, Character[] chars) {
        final int sourceLength = source.length();
        final int targetLength = chars.length;
        if (sourceLength < targetLength) {
            return null;
        }
        String result = null;
        int first = -1;
        int second = -1;

        Map<Character, Boolean> found = new HashMap<>();
        Map<Character, Boolean> inputs = Arrays.stream(chars).collect(Collectors.toMap(Function.identity(), c -> true));

        final char[] charArray = source.toCharArray();

        int currentIndex = 0;
        while (currentIndex < sourceLength) {
            char a = charArray[currentIndex];
            final Boolean b = inputs.get(a);
            if (b != null && b) {
                found.put(a, true);
                if (found.size() == targetLength) {
                    String temp = source.substring(first, currentIndex + 1);
                    if (result == null || temp.length() < result.length()) {
                        result = temp;
                    }
                    found.clear();
                    currentIndex = second;
                    first = -1;
                    second = -1;
                } else {
                    if (first == -1) {
                        first = currentIndex;
                    } else if (second == -1) {
                        second = currentIndex;
                    }
                    currentIndex++;
                }
            } else {
                currentIndex++;
            }
        } // end of while

        return result;
    }
}
