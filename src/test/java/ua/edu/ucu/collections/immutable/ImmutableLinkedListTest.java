package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {
    private ImmutableLinkedList empty;
    private ImmutableLinkedList list;

    @Before
    public void setUp() throws Exception {
        empty = new ImmutableLinkedList();
        list = new ImmutableLinkedList();
        for (int i = 0; i < 3; i++) {
            list = list.add(i);
        }
    }

    @Test
    public void size() {
        assertEquals(3, list.size());
        assertEquals(0, empty.size());
    }

    @Test
    public void last() {
        ImmutableLinkedList lst = new ImmutableLinkedList(10);
        ImmutableLinkedList new_elem = list.add(lst);
        assertEquals(lst, new_elem.last());
        assertEquals(empty, empty.last());

    }

    @Test
    public void add() {
        Object[] first = list.toArray();
        ImmutableLinkedList lst = list.add(3);
        Object[] exp = {0, 1, 2, 3};
        assertArrayEquals(exp, lst.toArray());
        assertArrayEquals(first, list.toArray());
    }

    @Test
    public void testAdd() {
        Object[] first = list.toArray();
        ImmutableLinkedList lst = list.add(2, 3);
        Object[] exp = {0, 1, 3, 2};
        assertArrayEquals(exp, lst.toArray());
        assertArrayEquals(first, list.toArray());
    }

    @Test
    public void addAll() {
        Object[] addable = {3, 4, 5, 6};
        ImmutableLinkedList lst = list.add(addable);
        Object[] exp = {0, 1, 2, 3, 4, 5, 6};
        assertArrayEquals(exp, lst.toArray());
    }

    @Test
    public void testAddAll() {
        Object[] addable = {3, 4, 5, 6};
        ImmutableLinkedList lst = list.add(2, addable);
        Object[] exp = {0, 1, 3, 4, 5, 6, 2};
        assertArrayEquals(exp, lst.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get() {
        assertEquals(1, list.get(1));
        list.get(25);
    }


    @Test
    public void remove() {
        ImmutableLinkedList lst = list.remove(1);
        Object[] exp = {0, 2};
        assertArrayEquals(exp, lst.toArray());
    }

    @Test
    public void set() {
        ImmutableLinkedList lst = list.set(2, 20);
        Object[] exp = {0, 1, 20};
        assertArrayEquals(exp, lst.toArray());
    }

    @Test
    public void indexOf() {
        assertEquals(2, list.indexOf(2));
        assertEquals(-1, list.indexOf(5));
    }

    @Test
    public void clear() {
        ImmutableLinkedList lst = list.clear();
        Object[] exp = {};
        assertArrayEquals(exp, lst.toArray());
    }

    @Test
    public void isEmpty() {
        assertTrue(empty.isEmpty());
        assertFalse(list.isEmpty());
    }

    @Test
    public void toArray() {
        Object[] add = {5, 6, 7, 8, 9, 4};
        ImmutableLinkedList lst = list.add(1, add);
        Object[] exp = {0, 5, 6, 7, 8, 9, 4, 1, 2};
        assertArrayEquals(exp, lst.toArray());
    }

    @Test
    public void testToString() {
        Object[] add = {5, 6, 7, 8, 9, 4};
        ImmutableLinkedList lst = list.add(1, add);
        assertEquals("0, 5, 6, 7, 8, 9, 4, 1, 2", lst.toString());
    }

    @Test
    public void addFirst() {
        ImmutableLinkedList lst = list.addFirst(7);
        Object[] exp = {7, 0, 1, 2};
        assertArrayEquals(exp, lst.toArray());
    }

    @Test
    public void addLast() {
        ImmutableLinkedList lst = list.addLast(7);
        Object[] exp = {0, 1, 2, 7};
        assertArrayEquals(exp, lst.toArray());
    }

    @Test
    public void getFirst() {
        assertEquals(0, list.getFirst());
    }

    @Test
    public void getLast() {
        assertEquals(2, list.getLast());
    }

    @Test
    public void removeFirst() {
        ImmutableLinkedList lst = list.removeFirst();
        Object[] exp = {1, 2};
        assertArrayEquals(exp, lst.toArray());
    }

    @Test
    public void removeLast() {
        ImmutableLinkedList lst = list.removeLast();
        Object[] exp = {0, 1};
        assertArrayEquals(exp, lst.toArray());
    }
}
