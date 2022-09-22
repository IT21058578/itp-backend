package com.web.backend.utils.heaps;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * Heap that stores the smallest values passed into it in a largest-first order.
 * Takes in key-value pairs. Compares key to maintain order.
 * @param <T> Type of the value passed in.
 */
public class CustomMaxHeap<T> {
    int maxSize;
    PriorityQueue<HeapNode<T>> queue;

    public CustomMaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new PriorityQueue<>(maxSize, Comparator.reverseOrder());
    }

    public void add(double key, T value) {
        if (queue.size() == maxSize && queue.peek().getKey() > key) {
            queue.poll();
            queue.add(new HeapNode<>(key, value));
        } else if (queue.size() < maxSize) {
            queue.add(new HeapNode<>(key, value));
        }
    }

    public ArrayList<T> toList() {
        var list = new ArrayList<T>(queue.size());
        HeapNode<T> last;
        while ((last = queue.poll()) != null) {
            list.add(last.getValue());
        }
        return list;
    }

    public T pollAndGetValue() {
        return Objects.requireNonNull(queue.poll()).getValue();
    }
}
