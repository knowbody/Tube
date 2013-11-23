package com.algo.structures;

import java.util.Collection;
import java.util.Iterator;

public class LinkedList<Station> implements Collection<Station> {

    transient int size;
    transient Node<Station> head;
    transient Node<Station> last;

    @Override
    public boolean add(Station s) {
        addLast(s);
        return true;
    }

    void addLast(Station s) {
        final Node<Station> l = last;
        final Node<Station> newStation = new Node<>(null, l, s);
        last = newStation;
        if (l == null) {
            head = newStation;
        } else {
            l.next = newStation;
        }
        size++;
    }

    @Override
    public boolean addAll(Collection<? extends Station> col) {
        return addAll(size, col);
    }

    /**
     * Inserts all of the elements in the specified collection into this list,
     * starting at the specified position. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right (increases
     * their indices). The new elements will appear in the list in the order
     * that they are returned by the specified collection's iterator.
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c     collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException      if the specified collection is null
     */
    public boolean addAll(int index, Collection<? extends Station> col) {
        Object[] array = col.toArray();
        int numNew = array.length;
        if (numNew == 0) {
            return false;
        }

        Node<Station> predecessor, successor;
        if (index == size) {
            successor = null;
            predecessor = last;
        } else {
            successor = node(index);
            predecessor = successor.prev;
        }

        for (Object obj : array) {
            @SuppressWarnings("unchecked")
            Station s = (Station) obj;
            Node<Station> newStation = new Node<>(null, predecessor, s);
            if (predecessor == null) {
                head = newStation;
            } else {
                predecessor.next = newStation;
            }
            predecessor = newStation;
        }

        if (successor == null) {
            last = predecessor;
        } else {
            predecessor.next = successor;
            successor.prev = predecessor;
        }

        size += numNew;
        return true;
    }

    /**
     * Returns the (non-null) Node at the specified element index.
     */
    Node<Station> node(int index) {
        if (index < (size >> 1)) {
            Node<Station> temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp;
        } else {
            Node<Station> temp = last;
            for (int i = size - 1; i > index; i--) {
                temp = temp.prev;
            }
            return temp;
        }
    }

    @Override
    public int size() {
        return size;
    }

    public Station get(int index) {
        return node(index).station;
    }

    @Override
    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        int i = 0;
        for (Node<Station> x = head; x != null; x = x.next) {
            newArray[i++] = x.station;
        }
        return newArray;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<Station> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    private class Node<Station> {

        Node<Station> next;
        Node<Station> prev;
        Station station;

        public Node(Node<Station> next, Node<Station> prev, Station station) {
            this.next = next;
            this.prev = prev;
            this.station = station;
        }
    }
}