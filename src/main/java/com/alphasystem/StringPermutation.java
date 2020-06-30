package com.alphasystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class StringPermutation {

    private static final int VALUE_OF_A = 'a';

    private static final class Pair {
        private final char c;
        private final int count;

        private Pair(char c) {
            this(c, 1);
        }

        private Pair(char c, int count) {
            this.c = c;
            this.count = count;
        }

        public char getCharacter() {
            return c;
        }

        public int getCount() {
            return count;
        }

        public Pair incrementCount() {
            return new Pair(c, count + 1);
        }

        public Pair decrementCount() {
            return new Pair(c, count - 1);
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "c=" + c +
                    ", count=" + count +
                    '}';
        }
    }

    public static List<String> allCombinations(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }

        List<String> results = new ArrayList<>();
        allCombinations(toFrequencyPairs(s), new char[s.length()], 0, results);
        return results;
    }

    private static List<Pair> toFrequencyPairs(String s) {
        Pair[] frequencyArray = new Pair[26];
        s.toLowerCase()
                .chars()
                .forEach(i -> {
                    int index = i - VALUE_OF_A;
                    Pair pair = frequencyArray[index];
                    if (pair == null) {
                        pair = new Pair((char) i);
                    } else {
                        pair = pair.incrementCount();
                    }
                    frequencyArray[index] = pair;
                });
        return Arrays.stream(frequencyArray).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private static void allCombinations(List<Pair> pairList,
                                        char[] results,
                                        int level,
                                        List<String> permutations) {
        if (level == results.length) {
            // base case
            permutations.add(new String(results));
            return;
        }

        for (int i = 0; i < pairList.size(); i++) {
            final Pair pair = pairList.get(i);
            if (pair.getCount() == 0) {
                continue;
            }
            results[level] = pair.getCharacter();
            pairList.set(i, pair.decrementCount());
            allCombinations(pairList, results, level + 1, permutations);
            pairList.set(i, pair);
        }
    }
}
