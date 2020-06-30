package com.alphasystem;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class StringPermutationTest {

    @Test
    void stringWithNoRepeat() {
        final List<String> permutations = StringPermutation.permutations("abc");
        assertEquals(6, permutations.size());
        List<String> expected = Arrays.asList("abc", "acb", "bac", "bca", "cab", "cba");
        assertIterableEquals(expected, permutations);
    }

    @Test
    void stringWithRepeat() {
        final List<String> permutations = StringPermutation.permutations("baca");
        assertEquals(12, permutations.size());
        List<String> expected = Arrays.asList("aabc", "aacb", "abac", "abca", "acab", "acba", "baac", "baca", "bcaa",
                "caab", "caba", "cbaa");
        assertIterableEquals(expected, permutations);
    }
}
