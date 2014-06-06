package qsbk.app.core;

import java.util.Iterator;

public interface Deque<E> extends Queue<E> {
    public boolean add(E r1_E);

    public void addFirst(E r1_E);

    public void addLast(E r1_E);

    public boolean contains(Object r1_Object);

    public Iterator<E> descendingIterator();

    public E element();

    public E getFirst();

    public E getLast();

    public Iterator<E> iterator();

    public boolean offer(E r1_E);

    public boolean offerFirst(E r1_E);

    public boolean offerLast(E r1_E);

    public E peek();

    public E peekFirst();

    public E peekLast();

    public E poll();

    public E pollFirst();

    public E pollLast();

    public E pop();

    public void push(E r1_E);

    public E remove();

    public boolean remove(Object r1_Object);

    public E removeFirst();

    public boolean removeFirstOccurrence(Object r1_Object);

    public E removeLast();

    public boolean removeLastOccurrence(Object r1_Object);

    public int size();
}