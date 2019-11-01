package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;


public class Stack {
    private ImmutableLinkedList array;

    public Stack() {
        array = new ImmutableLinkedList();
    }

    public Stack(Object e) {
        array = new ImmutableLinkedList(e);
    }

    public Object peek() {
        return array.getLast();
    }

    public Object pop() {
        Object elem = array.getLast();
        array = array.removeLast();
        return elem;
    }

    public void push(Object e) {
        array = array.addLast(e);
    }
}
