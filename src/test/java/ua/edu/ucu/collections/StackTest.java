package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {
    Stack empty;
    Stack stack;

    @Before
    public void setUp() throws Exception {
        empty = new Stack();
        stack = new Stack(0);
        stack.push(1);
        stack.push(2);
    }

    @Test
    public void peek() {
        assertEquals(2, stack.peek());
        assertNull(empty.peek());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void pop() {
        Object el = stack.pop();
        assertEquals(2, el);
        Object[] exp = {0, 1};
        assertArrayEquals(exp, stack.toArray());
        empty.pop();
    }

    @Test
    public void push() {
        Object[] exp = {0, 1, 2, 3};
        stack.push(3);
        assertArrayEquals(exp, stack.toArray());
    }

    @Test
    public void testToString() {
        assertEquals("0, 1, 2", stack.toString());
    }

    @Test
    public void toArray() {
        Object[] exp = {0, 1, 2};
        assertArrayEquals(exp, stack.toArray());
    }
}
