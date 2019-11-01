package ua.edu.ucu.collections.immutable;

import java.lang.reflect.Array;

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
        ImmutableLinkedList new_elem = new ImmutableLinkedList();
        ImmutableLinkedList new_elem_iter = new_elem;
        ImmutableLinkedList this_iterator = this;
        while (this_iterator != null) {
            if (new_elem.value == null) {
                new_elem.value = this_iterator.value;
            } else {
                ImmutableLinkedList adab = new ImmutableLinkedList(this_iterator.value);
                new_elem_iter.next = adab;
                new_elem_iter.next.previous = new_elem_iter;
                new_elem_iter = new_elem_iter.next;
            }
            this_iterator = this_iterator.next;
        }
        return new_elem;
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        ImmutableLinkedList new_elem = copy();
        if (new_elem.value == null) {
            new_elem.value = e;
            return new_elem;
        }
        ImmutableLinkedList last_elem = new_elem.last();
        last_elem.next = new ImmutableLinkedList(e);
        last_elem.next.previous = last_elem;
        return new_elem;
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        if (index > size()) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList new_elem = copy();
        ImmutableLinkedList index_elem = new_elem;
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
        return new_elem;
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        ImmutableLinkedList new_elem = copy();
        for (Object elem : c) {
            new_elem = new_elem.add(elem);
        }
        return new_elem;
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        ImmutableLinkedList new_elem = copy();
        int i = 0;
        for (Object elem : c) {
            new_elem = new_elem.add(i, elem);
            i++;
        }
        return new_elem;
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

    private ImmutableLinkedList helper_ind(ImmutableLinkedList new_elem,
                                       int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList ind_elem = new_elem;
        int i = 0;
        while (i != index) {
            i++;
            ind_elem = ind_elem.next;
        }
        return ind_elem;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        ImmutableLinkedList new_elem = copy();
        ImmutableLinkedList ind_elem = helper_ind(new_elem, index);
        ind_elem.previous.next = ind_elem.next;
        ind_elem.next.previous = ind_elem.previous;
        return new_elem;
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        ImmutableLinkedList new_elem = copy();
        ImmutableLinkedList ind_elem = helper_ind(new_elem, index);
        ind_elem.value = e;
        return new_elem;
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
        Object[] array = new Array[size()];
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