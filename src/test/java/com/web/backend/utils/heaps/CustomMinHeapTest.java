package com.web.backend.utils.heaps;

import com.web.backend.utils.heaps.CustomMinHeap;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CustomMinHeapTest {

    @Test
    void addMinHeap() {
        CustomMinHeap<Integer> minHeap = new CustomMinHeap<>(5);
        int[] items = {0, 1, 6, 4, 3, 2, 5};
        Arrays.stream(items).forEach(i -> minHeap.add(i, i));
    }

    @Test
    void pollAndGetValueFromMinHeap() {
        CustomMinHeap<Integer> minHeap = new CustomMinHeap<>(4);
        int[] items = {0, 1, 6, 4, 3, 2, 5};
        Arrays.stream(items).forEach(i -> minHeap.add(i, i));

        int[] expected = {3, 4, 5, 6};
        int[] actual = new int[4];
        for(int i = 0; i < 4; i++) { actual[i] = minHeap.pollAndGetValue();}

        assertArrayEquals(expected, actual);
    }
}