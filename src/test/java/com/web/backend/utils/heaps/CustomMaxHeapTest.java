package com.web.backend.utils.heaps;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CustomMaxHeapTest {

    @Test
    void addMinHeap() {
        CustomMaxHeap<Integer> maxHeap = new CustomMaxHeap<>(5);
        int[] items = {0, 1, 6, 4, 3, 2, 5};
        Arrays.stream(items).forEach(i -> maxHeap.add(i, i));
    }

    @Test
    void pollAndGetValueFromMinHeap() {
        CustomMaxHeap<Integer> maxHeap = new CustomMaxHeap<>(4);
        int[] items = {0, 1, 6, 4, 3, 2, 5};
        Arrays.stream(items).forEach(i -> maxHeap.add(i, i));

        int[] expected = {3, 2, 1, 0};
        int[] actual = new int[4];
        for(int i = 0; i < 4; i++) { actual[i] = maxHeap.pollAndGetValue();}

        assertArrayEquals(expected, actual);
    }
}