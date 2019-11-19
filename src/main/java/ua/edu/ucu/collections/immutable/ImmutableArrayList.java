package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public final class ImmutableArrayList implements ImmutableList {
    private int sizeR;
    private int capacity;
    private Object[] array;

    public ImmutableArrayList() {
        sizeR = 0;
        capacity = 2;
        array = new Object[2];
    }

    public ImmutableArrayList(int cap) {
        sizeR = 0;
        capacity = cap;
        array = new Object[cap];
    }

    private ImmutableArrayList resize() {
        ImmutableArrayList newElem = new ImmutableArrayList(capacity * 2);
        newElem.array = Arrays.copyOf(array, newElem.capacity);
        newElem.sizeR = sizeR;
        return newElem;
    }

    private ImmutableArrayList copy() {
        ImmutableArrayList newElem = new ImmutableArrayList(capacity);
        newElem.array = Arrays.copyOf(array, capacity);
        newElem.sizeR = sizeR;
        return newElem;
    }

    @Override
    public ImmutableArrayList add(Object e) {
        ImmutableArrayList newElem = copy();
        if (canFit()) {
            newElem.array[sizeR] = e;
            newElem.sizeR++;
        } else {
            newElem = newElem.resize();
            newElem.array[sizeR] = e;
            newElem.sizeR++;
        }
        return newElem;
    }

    @Override
    public ImmutableArrayList add(int index, Object e) {
        ImmutableArrayList newElem = copy();
        if (index > sizeR) {
            throw new IndexOutOfBoundsException();
        }
        if (index == sizeR) {
            return add(e);
        } else {
            if (!canFit()) {
                newElem = newElem.resize();
            }
            for (int i = sizeR; i > index; i--) {
                newElem.array[i] = newElem.array[i - 1];
            }
            newElem.array[index] = e;
            newElem.sizeR++;
            return newElem;
        }
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        ImmutableArrayList newElem = copy();
        for (Object elem : c) {
            newElem = newElem.add(elem);
        }
        return newElem;
    }

    @Override
    public ImmutableArrayList addAll(int index, Object[] c) {
        if (index >= sizeR) {
            throw new IndexOutOfBoundsException();
        }
        int i = index;
        ImmutableArrayList newElem = copy();
        for (Object elem : c) {
            newElem = newElem.add(i, elem);
            i++;
        }
        return newElem;
    }

    @Override
    public Object get(int index) {
        if (index < sizeR) {
            return array[index];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public ImmutableArrayList remove(int index) {
//        ImmutableArrayList newElem = copy();
        if (index < sizeR) {
//            newElem.array[index] = null;
//            for (int i = index; i < sizeR - 1; i++) {
//                newElem.array[i] = newElem.array[i + 1];
//            }
//            sizeR -= 1;
//            newElem.array[sizeR] = null;
//            return newElem;
            ImmutableArrayList newElem = new ImmutableArrayList(capacity);
            for (int i = 0; i < index; i++) {
                newElem.array[i] = array[i];
            }
            for (int i = index; i < sizeR - 1; i++) {
                newElem.array[i] = array[i + 1];
            }
            newElem.sizeR = sizeR - 1;
            return newElem;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public ImmutableArrayList set(int index, Object e) {
        ImmutableArrayList newElem = copy();
        if (index < sizeR) {
            newElem.array[index] = e;
            return newElem;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < sizeR; ++i) {
            if (array[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return sizeR;
    }

    @Override
    public ImmutableArrayList clear() {
        return new ImmutableArrayList(capacity);
    }

    private boolean canFit() {
        return sizeR != capacity;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, sizeR);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < sizeR; i++) {
            if (i == sizeR - 1) {
                str.append(array[i]);
            } else {
                str.append(array[i]).append(", ");
            }
        }
        return str.toString();
    }

    @Override
    public boolean isEmpty() {
        return sizeR == 0;
    }
}
