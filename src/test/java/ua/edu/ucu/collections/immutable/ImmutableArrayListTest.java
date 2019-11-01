package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableArrayListTest {
    private static final int CAP = 20;
    private ImmutableArrayList empty;
    private ImmutableArrayList array;

    @Before
    public void setUp() throws Exception {
        empty = new ImmutableArrayList(CAP);
        array = new ImmutableArrayList(CAP).add(0);
        array = array.add(1);
        array = array.add(2);
        System.out.println("");
    }

    @Test
    public void add() {
        ImmutableArrayList array2 = empty.add(8);
        Object[] arr = {8};
        Object[] arr_emp = {0, 1, 2};
        assertArrayEquals(arr, array2.toArray());
        assertArrayEquals(arr_emp, array.toArray());
    }

    @Test
    public void testAdd() {
        int i = 1;
        ImmutableArrayList ar = array.add(i, 10);
        Object[] exp = {0, 10, 1, 2};
        assertArrayEquals(exp, ar.toArray());
    }

    @Test
    public void addAll() {
        ImmutableArrayList arr = array;
        Object[] els = {4, 5, 6, 7,};
        arr = arr.addAll(els);
        Object[] exp = {0, 1, 2, 4, 5, 6, 7};
        assertArrayEquals(exp, arr.toArray());
    }

    @Test
    public void testAddAll() {
        int index = 1;
        ImmutableArrayList arr = array;
        Object[] els = {4, 5, 6, 7};
        arr = arr.addAll(index, els);
        Object[] exp = {0, 4, 5, 6, 7, 1, 2};
        assertArrayEquals(exp, arr.toArray());
    }

    @Test
    public void get() {
        assertEquals(2, array.get(2));
    }

    @Test
    public void remove() {
        ImmutableArrayList arr = array;
        for (int i = 4; i < 8; i++) {
            arr = arr.add(i);
        }
        arr = arr.remove(3);
        Object[] exp = {0, 1, 2, 5, 6, 7};
        assertArrayEquals(exp, arr.toArray());
    }

    @Test
    public void set() {
        ImmutableArrayList arr = array.set(1, 20);
        Object[] exp = {0, 20, 2};
        assertArrayEquals(exp, arr.toArray());
    }

    @Test
    public void indexOf() {
        assertEquals(-1, array.indexOf(3));
        ImmutableArrayList arr = array.add(5);
        assertEquals(2, array.indexOf(2));
    }

    @Test
    public void size() {
        assertEquals(3, array.size());
    }

    @Test
    public void clear() {
        ImmutableArrayList arr = array.clear();
        Object[] exp = {};
        assertArrayEquals(exp, arr.toArray());
    }

    @Test
    public void isEmpty() {
        assertTrue(empty.isEmpty());
        assertFalse(array.isEmpty());

    }

    @Test
    public void toArray() {
        Object[] exp = {0, 1, 2};
        assertArrayEquals(exp, array.toArray());
    }

    @Test
    public void testToString() {
        assertEquals("0, 1, 2", array.toString());
    }
}
