package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        ImmutableLinkedList a = new ImmutableLinkedList();
        ImmutableLinkedList b = a.add(6);
        ImmutableLinkedList c = b.add(7);
        ImmutableLinkedList d = c.add(8);
        c.next.value = 10;
        System.out.println("kjeqweqkhb");
        System.out.println(d);
    }
}