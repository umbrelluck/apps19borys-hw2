package ua.edu.ucu.collections.immutable;

public class ImmutableLinkedList implements ImmutableList {

    private Object value;
    private ImmutableLinkedList next;
    private ImmutableLinkedList previous;

    public ImmutableLinkedList() {
        value = null;
        next = null;
        previous = null;
    }

    public ImmutableLinkedList(Object e) {
        value = e;
        next = null;
        previous = null;
    }

    @Override
    public int size() {
        if (this.value == null) {
            return 0;
        }
        ImmutableLinkedList index = this;
        int i = 1;
        while (index != null) {
            index = index.next;
            i += 1;
        }
        return i;
    }

    public ImmutableLinkedList last() {
        ImmutableLinkedList last_elem = this;
        while (last_elem.next != null) {
            last_elem = last_elem.next;
        }
        return last_elem;
    }

    private ImmutableLinkedList copy() {
        ImmutableLinkedList newElem = new ImmutableLinkedList();
        ImmutableLinkedList newElemIter = newElem;
        ImmutableLinkedList thisIterator = this;
        while (thisIterator != null) {
            if (newElem.value == null) {
                newElem.value = thisIterator.value;
            } else {
                newElemIter.next = new
                        ImmutableLinkedList(thisIterator.value);
                newElemIter.next.previous = newElemIter;
                newElemIter = newElemIter.next;
            }
            thisIterator = thisIterator.next;
        }
        return newElem;
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        ImmutableLinkedList newElem = copy();
        if (newElem.value == null) {
            newElem.value = e;
            return newElem;
        }
        ImmutableLinkedList last_elem = newElem.last();
        last_elem.next = new ImmutableLinkedList(e);
        last_elem.next.previous = last_elem;
        return newElem;
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        if (index > size()) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList newElem = copy();
        ImmutableLinkedList index_elem = newElem;
        int i = 0;
        while (i < index && index_elem.next != null) {
            i++;
            index_elem = index_elem.next;
        }
        ImmutableLinkedList tail = index_elem.next;
        index_elem.next = new ImmutableLinkedList(e);
        index_elem.next.previous = index_elem;

        index_elem.next.next = tail;
        tail.previous = index_elem.next;
        return newElem;
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        ImmutableLinkedList newElem = copy();
        for (Object elem : c) {
            newElem = newElem.add(elem);
        }
        return newElem;
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        ImmutableLinkedList newElem = copy();
        int i = 0;
        for (Object elem : c) {
            newElem = newElem.add(i, elem);
            i++;
        }
        return newElem;
    }

    @Override
    public Object get(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        int i = 0;
        ImmutableLinkedList ind_elem = this;
        while (i != index) {
            i++;
            ind_elem = ind_elem.next;
        }
        return ind_elem.value;
    }

    private ImmutableLinkedList helperInd(ImmutableLinkedList newElem,
                                          int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList ind_elem = newElem;
        int i = 0;
        while (i != index) {
            i++;
            ind_elem = ind_elem.next;
        }
        return ind_elem;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        ImmutableLinkedList newElem = copy();
        ImmutableLinkedList ind_elem = helperInd(newElem, index);
        ind_elem.previous.next = ind_elem.next;
        ind_elem.next.previous = ind_elem.previous;
        return newElem;
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        ImmutableLinkedList newElem = copy();
        ImmutableLinkedList ind_elem = helperInd(newElem, index);
        ind_elem.value = e;
        return newElem;
    }

    @Override
    public int indexOf(Object e) {
        int i = 0;
        ImmutableLinkedList indexof = this;
        while (indexof != null) {
            if (indexof.value == e) {
                return i;
            }
            i++;
            indexof = indexof.next;
        }
        return -1;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return this.value == null;
    }

    @Override
    public Object[] toArray() {
        ImmutableLinkedList iter = this;
        int sz = size();
        if (sz == 0) {
            return new Object[0];
        }
        Object[] array = new Object[size()];
        for (int i = 0; i < sz; i++, iter = iter.next) {
            array[i] = iter.value;
        }
        return array;
    }

    @Override
    public String toString() {
        ImmutableLinkedList iter = this;
        StringBuilder str = new StringBuilder();
        while (iter != null) {
            if (iter.next == null) {
                str.append(iter.value);
            } else {
                str.append(iter.value).append(", ");
            }
            iter = iter.next;
        }
        return str.toString();
    }

    public ImmutableLinkedList addFirst(Object e) {
        return add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return add(e);
    }

    public Object getFirst() {
        return this.value;
    }

    public Object getLast() {
        return last().value;
    }

    public ImmutableLinkedList removeFirst() {
        return remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return remove(size() - 1);
    }


}