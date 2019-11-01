package ua.edu.ucu.collections.immutable;

public class ImmutableLinkedList2 implements ImmutableList {

    private Object value;
    private ImmutableLinkedList2 next, last;

    public ImmutableLinkedList2() {
        value = null;
        next = null;
        last = null;
    }

    public ImmutableLinkedList2(Object e) {
        value = e;
        next = null;
        last = null;
//        previous=null;
    }

    public ImmutableLinkedList2 copy() {
        ImmutableLinkedList2 new_elem = new ImmutableLinkedList2(value);
        ImmutableLinkedList2 tmp_next = next;
        while (tmp_next != null) {
            if (new_elem.last == null) {
                new_elem.next = tmp_next;
                new_elem.last = tmp_next;
            } else {
                new_elem.last.next = tmp_next;
                new_elem.last = tmp_next;
            }
            tmp_next = tmp_next.next;
        }
        return new_elem;
    }

    @Override
    public ImmutableLinkedList2 add(Object e) {
        ImmutableLinkedList2 new_elem = copy();
        if (new_elem.value == null) {
            new_elem.value = e;
        } else if (new_elem.last == null) {
            ImmutableLinkedList2 child = new ImmutableLinkedList2(e);
            new_elem.last = child;
            new_elem.next = child;
//            new_elem.last.previous=this;
        } else {
            ImmutableLinkedList2 child = new ImmutableLinkedList2(e);
            new_elem.last.next = child;
            new_elem.last = child;
        }
        return new_elem;
    }

    @Override
    public ImmutableLinkedList2 add(int index, Object e) {
        ImmutableLinkedList2 new_elem = copy();
        ImmutableLinkedList2 indexed_elem = new_elem;
        int i = 0;
        while (i < index) {
            indexed_elem = indexed_elem.next;
            i++;
        }
        ImmutableLinkedList2 tail_elem = indexed_elem.next;
        indexed_elem.next = new ImmutableLinkedList2(e);
        indexed_elem.next.next = tail_elem;
        return null;
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        ImmutableLinkedList2 new_elem = copy();
        for (Object elem : c) {
            new_elem = new_elem.add(elem);
        }
        return new_elem;
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        ImmutableLinkedList2 new_elem = copy();
        int i = index;
        for (Object elem : c) {
            new_elem.add(i, elem);
            i++;
        }
        return new_elem;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public ImmutableList remove(int index) {
        return null;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        return null;
    }

    @Override
    public int indexOf(Object e) {
        if (value == null || next == null) {
            return -1;
        }
        if (value == e) {
            return 0;
        }
        int s = next.indexOf(e);
        if (s == -1) {
            return s;
        }
        return 1 + s;
    }

    @Override
    public int size() {
        if (value == null) {
            return 0;
        } else {
            return 1 + next.size();
        }
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList2();
    }

    @Override
    public boolean isEmpty() {
        return (value == null);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public String toString() {
        return null;
    }
}
