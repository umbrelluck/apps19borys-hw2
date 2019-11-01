package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public class ImmutableArrayList implements ImmutableList {
    private int size_r;
    private int capacity;
    private Object[] array;

    public ImmutableArrayList(int cap) {
        size_r = 0;
        capacity = cap;
        array = new Object[cap];
    }

    private ImmutableArrayList resize() {
        ImmutableArrayList new_elem = new ImmutableArrayList(capacity * 2);
        new_elem.array = Arrays.copyOf(array, size_r);
        return new_elem;
    }

    private ImmutableArrayList copy() {
        ImmutableArrayList new_elem = new ImmutableArrayList(capacity);
        new_elem.array = Arrays.copyOf(array, size_r);
        return new_elem;
    }

    @Override
    public ImmutableArrayList add(Object e) {
        ImmutableArrayList new_elem = copy();
        if (isEmpty()) {
            new_elem.array[size_r] = e;
            new_elem.size_r++;
        } else {
            new_elem = new_elem.resize();
            new_elem.array[size_r] = e;
            new_elem.size_r++;
        }
        return new_elem;
    }

    @Override
    public ImmutableArrayList add(int index, Object e) {
        ImmutableArrayList new_elem = copy();
        if (index >= size_r) {
            throw new IndexOutOfBoundsException();
        } else {
            if (isEmpty()) {
                new_elem.array[index] = e;
                new_elem.size_r++;
            } else {
                new_elem = new_elem.resize();
                new_elem.array[index] = e;
                new_elem.size_r++;
            }
        }
        return new_elem;
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        ImmutableArrayList new_elem = copy();
        for (Object elem : c) {
            new_elem = new_elem.add(elem);
        }
        return new_elem;
    }

    @Override
    public ImmutableArrayList addAll(int index, Object[] c) {
        if (index >= size_r) {
            throw new IndexOutOfBoundsException();
        }
        int i = index;
        ImmutableArrayList new_elem = copy();
        for (Object elem : c) {
            new_elem = new_elem.add(i, elem);
            i++;
        }
        return new_elem;
    }

    @Override
    public Object get(int index) {
        if (index < size_r) {
            return array[index];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public ImmutableArrayList remove(int index) {
        ImmutableArrayList new_elem = copy();
        if (index < size_r) {
            new_elem.array[index] = null;
            return new_elem;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public ImmutableArrayList set(int index, Object e) {
        ImmutableArrayList new_elem = copy();
        if (index < size_r) {
            new_elem.array[index] = e;
            return new_elem;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < size_r; ++i) {
            if (array[i] == e) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size_r;
    }

    @Override
    public ImmutableArrayList clear() {
        return new ImmutableArrayList(capacity);
    }

    @Override
    public boolean isEmpty() {
        return size_r != capacity;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size_r);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size_r; i++) {
            if (i == size_r - 1) {
                str.append(array[i]);
            } else {
                str.append(array[i]).append(", ");
            }
        }
        return str.toString();
    }
}
