package com.web.backend.utils.heaps;

import java.util.Objects;
import java.util.PriorityQueue;

/**
 * Heap that stores the largest values passed into it in a smallest-first order.
 * Takes in key-value pairs. Compares key to maintain order.
 * @param <T> Type of the value passed in.
 */
public class CustomMinHeap<T> {
    int maxSize;
    PriorityQueue<HeapNode<T>> queue;

    public CustomMinHeap(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new PriorityQueue<>(maxSize);
    }

    public void add(double key, T value) {
        if (queue.size() == maxSize && queue.peek().getKey() < key) {
            queue.poll();
            queue.add(new HeapNode<>(key, value));
        } else if (queue.size() < maxSize) {
            queue.add(new HeapNode<>(key, value));
        }
    }

    public T pollAndGetValue() {
        return Objects.requireNonNull(queue.poll()).getValue();
    }
}
