package Queue;

import java.util.ArrayList;
import java.util.List;

// Leetcode: 

// head [1, 2, 3] tail

public class CircularQueue {

    int size;
    int head;
    int tail;
    int count; // count is needed as we cannot tell if queue is empty or full
    int[] queue;

    public CircularQueue(int k) {
        size = k;
        head = 0; // keeping head = 0 instead -1 prevents less complexity
        tail = -1;
        count = 0;
        queue = new int[k];
    }
    
    public boolean enQueue(int value) {
        if(isFull())
            return false;
        
        tail = (tail + 1) % size;
        queue[tail] = value;
        count++;
        return true;
    }
    
    // In a circular queue, you don’t remove elements physically, you just move the head pointer.
    public boolean deQueue() {
        if(isEmpty())
            return false;

        head = (head + 1) % size;
        count--;
        return true;
    }
    
    public int Front() {
        if(isEmpty())
            return -1;
        
        return queue[head];
    }
    
    public int Rear() {
        if(isEmpty())
            return -1;
        
        return queue[tail];
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    public boolean isFull() {
        return count == size;
    }
}