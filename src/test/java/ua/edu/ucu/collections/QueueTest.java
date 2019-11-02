package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {
    Queue empty;
    Queue queue;

    @Before
    public void setUp() throws Exception {
        empty = new Queue();
        queue = new Queue(0);
        queue.enqueue(1);
        queue.enqueue(2);
    }

    @Test
    public void peek() {
        assertEquals(0, queue.peek());
        assertNull(empty.peek());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void dequeue() {
        Object[] exp = {1, 2};
        Object first = queue.dequeue();
        assertArrayEquals(exp, queue.toArray());
        assertEquals(0, first);
        empty.dequeue();
    }

    @Test
    public void enqueue() {
        queue.enqueue(3);
        Object[] exp = {0, 1, 2, 3};
        assertArrayEquals(exp, queue.toArray());
    }

    @Test
    public void toStringTest() {
        assertEquals("0, 1, 2", queue.toString());
    }

    @Test
    public void toArray() {
        Object[] exp = {0, 1, 2};
        assertArrayEquals(exp, queue.toArray());
    }
}
