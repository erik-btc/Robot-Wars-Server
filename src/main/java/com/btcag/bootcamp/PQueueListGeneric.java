package com.btcag.bootcamp;

public class PQueueListGeneric<T> {
    @SuppressWarnings("unchecked")
    private T[] data = (T[]) new Object[10];
    private int elemCnt = 0; // Number of elements in the queue
    private int startIndex = 0; // Start index (front of the queue)

    // Get the value at a specific index (circular)
    private T getValueAtIndex(int i){
        if (i >= elemCnt) {
            return null;  // Ensure we're not accessing out-of-bounds elements
        }
        int ind = (startIndex + i) % data.length;
        return data[ind];
    }

    // Increment the index in a circular manner
    private int increase(int curValue) {
        return (curValue + 1) % data.length;
    }

    // Decrement the index in a circular manner
    private int decrease(int curValue) {
        return (curValue - 1 + data.length) % data.length;  // Ensure index stays positive
    }

    // Increase the array size when the queue is full
    private void enhanceArray() {
        int i = 0;
        @SuppressWarnings("unchecked")
        T[] newdata = (T[]) new Object[data.length * 2];
        while (i < elemCnt) {
            newdata[i] = getValueAtIndex(i);
            i++;
        }
        startIndex = 0;  // Reset the start index after reallocation
        this.data = newdata;
    }

    // Remove an element from the front of the queue
    public T popFront() {
        if (elemCnt <= 0) {
            return null;  // Empty queue
        }
        T val = data[startIndex];
        startIndex = increase(startIndex);  // Move the start index forward
        elemCnt--;  // Decrease element count
        return val;
    }

    // Remove an element from the end of the queue
    public T popLast() {
        if (elemCnt <= 0) {
            return null;  // Empty queue
        }
        int lastIndex = (startIndex + elemCnt - 1) % data.length;  // Correct index for the last element
        T val = data[lastIndex];
        elemCnt--;  // Decrease element count
        return val;
    }

    // Add an element at the end of the queue
    public void pushLast(T i) {
        if (elemCnt == data.length) {
            enhanceArray();  // Increase the array size if full
        }
        int maxIndex = (startIndex + elemCnt) % data.length;  // Correct index for the last position
        data[maxIndex] = i;
        elemCnt++;  // Increase element count
    }

    // Add an element at the front of the queue
    public void pushFront(T i) {
        if (elemCnt == data.length) {
            enhanceArray();  // Increase the array size if full
        }
        startIndex = decrease(startIndex);  // Move the start index backward
        data[startIndex] = i;
        elemCnt++;  // Increase element count
    }

    // Get the element at the specified index
    public T get(int index) {
        return getValueAtIndex(index);  // Return the value at the given index
    }

    // Get the current number of elements in the queue
    public int getElemCnt() {
        return elemCnt;
    }
}
