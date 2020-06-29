package com.alphasystem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class StringOperations {

    /**
     * Given a string and list of distinct characters find a shortest substring containing each character at least once.
     *
     * @param s      Given string
     * @param source list of given characters
     * @return Shortest substring satisfying the condition
     */
    public static String findShortestSubstring(String s, Character[] source) {
        final int sourceLength = s.length();
        final int targetLength = source.length;
        if (sourceLength < targetLength) {
            return null;
        }
        String result = null;
        int first = -1;
        int second = -1;

        Map<Character, Boolean> found = new HashMap<>();
        Map<Character, Boolean> inputs = Arrays.stream(source).collect(Collectors.toMap(Function.identity(), c -> true));

        final char[] charArray = s.toCharArray();

        int currentIndex = 0;
        while (currentIndex < sourceLength) {
            char a = charArray[currentIndex];
            final Boolean b = inputs.get(a);
            if (b != null && b) {
                found.put(a, true);
                if (found.size() == targetLength) {
                    String temp = s.substring(first, currentIndex + 1);
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
