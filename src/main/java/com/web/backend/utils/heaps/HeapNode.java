package com.web.backend.utils.heaps;

import lombok.Data;

@Data
class HeapNode<U> implements Comparable<HeapNode<U>> {
    private double key;
    private U value;

    public HeapNode(double key, U value) {
        assert value != null; //U should be some actual object or value.
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(HeapNode<U> o) {
        return Double.compare(this.getKey(), o.getKey());
    }
}
