package com.alphasystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class StringCombination {

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

    /**
     * Returns all possible combination from the given input string.
     *
     * @param s input string
     * @return all possible permutations
     */
    public static List<String> permutations(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }

        List<String> results = new ArrayList<>();
        permutations(toFrequencyPairs(s), new char[s.length()], 0, results);
        return results;
    }

    public static List<String> combinations(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }

        List<String> results = new ArrayList<>();
        combinations(toFrequencyPairs(s), new char[s.length()], 0, 0, results);
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

    private static void permutations(List<Pair> pairList,
                                     char[] data,
                                     int level,
                                     List<String> results) {
        if (level == data.length) {
            // base case
            results.add(new String(data));
            return;
        }

        for (int i = 0; i < pairList.size(); i++) {
            final Pair pair = pairList.get(i);
            if (pair.getCount() == 0) {
                continue;
            }
            data[level] = pair.getCharacter();
            pairList.set(i, pair.decrementCount());
            permutations(pairList, data, level + 1, results);
            pairList.set(i, pair);
        }
    }


    private static void combinations(List<Pair> pairList,
                                     char[] data,
                                     int pos,
                                     int len,
                                     List<String> results) {
        results.add(new String(data, 0, len));
        for (int i = pos; i < pairList.size(); i++) {
            final Pair pair = pairList.get(i);
            if (pair.getCount() == 0) {
                continue;
            }
            data[len] = pair.getCharacter();
            pairList.set(i, pair.decrementCount());
            combinations(pairList, data, i, len + 1, results);
            pairList.set(i, pair);
        }
    }
}
