package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList array;

    public Queue() {
        array = new ImmutableLinkedList();
    }

    public Queue(Object e) {
        array = new ImmutableLinkedList(e);
    }

    public Object peek() {
        return array.getFirst();
    }

    public Object dequeue() {
        Object elem = array.getFirst();
        array = array.removeFirst();
        return elem;
    }

    public void enqueue(Object e) {
        array = array.addLast(e);
    }
}
