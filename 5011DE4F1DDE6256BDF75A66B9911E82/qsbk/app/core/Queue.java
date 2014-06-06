package qsbk.app.core;

import java.util.Collection;

public interface Queue<E> extends Collection<E> {
    public boolean add(E r1_E);

    public E element();

    public boolean offer(E r1_E);

    public E peek();

    public E poll();

    public E remove();
}