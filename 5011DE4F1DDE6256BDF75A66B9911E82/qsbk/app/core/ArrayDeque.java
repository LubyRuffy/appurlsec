package qsbk.app.core;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import qsbk.app.utils.Base64;

public class ArrayDeque<E> extends AbstractCollection<E> implements Serializable, Cloneable, Deque<E> {
    static final /* synthetic */ boolean a;
    private transient E[] b;
    private transient int c;
    private transient int d;


    private class a implements Iterator<E> {
        private int b;
        private int c;
        private int d;

        private a() {
            this.b = ArrayDeque.this.c;
            this.c = ArrayDeque.this.d;
            this.d = -1;
        }

        public boolean hasNext() {
            return this.b != this.c;
        }

        public E next() {
            if (this.b == this.c) {
                throw new NoSuchElementException();
            } else {
                E r0_E = ArrayDeque.this.b[this.b];
                if (ArrayDeque.this.d != this.c || r0_E == null) {
                    throw new ConcurrentModificationException();
                } else {
                    this.d = this.b;
                    this.b = (this.b + 1) & (ArrayDeque.this.b.length - 1);
                    return r0_E;
                }
            }
        }

        public void remove() {
            if (this.d < 0) {
                throw new IllegalStateException();
            } else {
                if (ArrayDeque.this.b(this.d)) {
                    this.b = (this.b - 1) & (ArrayDeque.this.b.length - 1);
                    this.c = ArrayDeque.this.d;
                }
                this.d = -1;
            }
        }
    }

    private class b implements Iterator<E> {
        private int b;
        private int c;
        private int d;

        private b() {
            this.b = ArrayDeque.this.d;
            this.c = ArrayDeque.this.c;
            this.d = -1;
        }

        public boolean hasNext() {
            return this.b != this.c;
        }

        public E next() {
            if (this.b == this.c) {
                throw new NoSuchElementException();
            } else {
                this.b = (this.b - 1) & (ArrayDeque.this.b.length - 1);
                E r0_E = ArrayDeque.this.b[this.b];
                if (ArrayDeque.this.c != this.c || r0_E == null) {
                    throw new ConcurrentModificationException();
                } else {
                    this.d = this.b;
                    return r0_E;
                }
            }
        }

        public void remove() {
            if (this.d < 0) {
                throw new IllegalStateException();
            } else {
                if (!ArrayDeque.this.b(this.d)) {
                    this.b = (this.b + 1) & (ArrayDeque.this.b.length - 1);
                    this.c = ArrayDeque.this.c;
                }
                this.d = -1;
            }
        }
    }

    static {
        a = !ArrayDeque.class.desiredAssertionStatus();
    }

    public ArrayDeque() {
        this.b = new Object[16];
    }

    public ArrayDeque(int r1i) {
        a(r1i);
    }

    public ArrayDeque(Collection<? extends E> r2_Collection__extends_E) {
        a(r2_Collection__extends_E.size());
        addAll(r2_Collection__extends_E);
    }

    private void a() {
        if (a || this.c == this.d) {
            int r1i = this.c;
            int r2i = this.b.length;
            int r3i = r2i - r1i;
            int r0i = r2i << 1;
            if (r0i < 0) {
                throw new IllegalStateException("Sorry, deque too big");
            } else {
                Object r0_Object = new Object[r0i];
                System.arraycopy(this.b, r1i, r0_Object, 0, r3i);
                System.arraycopy(this.b, 0, r0_Object, r3i, r1i);
                this.b = (Object[]) r0_Object;
                this.c = 0;
                this.d = r2i;
            }
        } else {
            throw new AssertionError();
        }
    }

    private void a(int r3i) {
        int r0i = Base64.DONT_BREAK_LINES;
        if (r3i >= 8) {
            r0i = (r3i >>> 1) | r3i;
            r0i |= r0i >>> 2;
            r0i |= r0i >>> 4;
            r0i |= r0i >>> 8;
            r0i = r0i | (r0i >>> 16) + 1;
            if (r0i < 0) {
                r0i >>>= 1;
            }
        }
        this.b = new Object[r0i];
    }

    private <T> T[] a(T[] r5_TA) {
        if (this.c < this.d) {
            System.arraycopy(this.b, this.c, r5_TA, 0, size());
        } else if (this.c > this.d) {
            int r0i = this.b.length - this.c;
            System.arraycopy(this.b, this.c, r5_TA, 0, r0i);
            System.arraycopy(this.b, 0, r5_TA, r0i, this.d);
        }
        return r5_TA;
    }

    private void b() {
        if (a || this.b[this.d] == null) {
            if (!a) {
                if (this.c == this.d) {
                    if (this.b[this.c] == null) {
                    }
                } else if (this.b[this.c] == null || this.b[(this.d - 1) & (this.b.length - 1)] == null) {
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            if (a || this.b[(this.c - 1) & (this.b.length - 1)] == null) {
            } else {
                throw new AssertionError();
            }
        } else {
            throw new AssertionError();
        }
    }

    private boolean b(int r10i) {
        int r1i = 1;
        boolean r0z = false;
        b();
        Object r2_Object = this.b;
        int r3i = r2_Object.length - 1;
        int r4i = this.c;
        int r5i = this.d;
        int r6i = (r10i - r4i) & r3i;
        int r7i = (r5i - r10i) & r3i;
        if (r6i >= ((r5i - r4i) & r3i)) {
            throw new ConcurrentModificationException();
        } else if (r6i < r7i) {
            if (r4i <= r10i) {
                System.arraycopy(r2_Object, r4i, r2_Object, r4i + 1, r6i);
            } else {
                System.arraycopy(r2_Object, 0, r2_Object, r1i, r10i);
                r2_Object[0] = r2_Object[r3i];
                System.arraycopy(r2_Object, r4i, r2_Object, r4i + 1, r3i - r4i);
            }
            r2_Object[r4i] = null;
            this.c = (r4i + 1) & r3i;
            return false;
        } else {
            if (r10i < r5i) {
                System.arraycopy(r2_Object, r10i + 1, r2_Object, r10i, r7i);
                this.d = r5i - 1;
            } else {
                System.arraycopy(r2_Object, r10i + 1, r2_Object, r10i, r3i - r10i);
                r2_Object[r3i] = r2_Object[r0z];
                System.arraycopy(r2_Object, 1, r2_Object, r0z, r5i);
                this.d = (r5i - 1) & r3i;
            }
            return true;
        }
    }

    public boolean add(E r2_E) {
        addLast(r2_E);
        return true;
    }

    public void addFirst(E r4_E) {
        if (r4_E == null) {
            throw new NullPointerException();
        } else {
            Object[] r0_ObjectA = this.b;
            int r1i = (this.c - 1) & (this.b.length - 1);
            this.c = r1i;
            r0_ObjectA[r1i] = r4_E;
            if (this.c == this.d) {
                a();
            }
        }
    }

    public void addLast(E r3_E) {
        if (r3_E == null) {
            throw new NullPointerException();
        } else {
            this.b[this.d] = r3_E;
            int r0i = (this.d + 1) & (this.b.length - 1);
            this.d = r0i;
            if (r0i == this.c) {
                a();
            }
        }
    }

    public void clear() {
        int r0i = this.c;
        int r1i = this.d;
        if (r0i != r1i) {
            this.d = 0;
            this.c = 0;
            int r2i = this.b.length - 1;
            while (true) {
                this.b[r0i] = null;
                r0i = (r0i + 1) & r2i;
                if (r0i == r1i) {
                }
            }
        }
    }

    public ArrayDeque<E> clone() {
        try {
            ArrayDeque<E> r0_ArrayDeque_E = (ArrayDeque) super.clone();
            r0_ArrayDeque_E.b = Arrays.copyOf(this.b, this.b.length);
            return r0_ArrayDeque_E;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public boolean contains(Object r5_Object) {
        if (r5_Object == null) {
            return false;
        }
        int r2i = this.b.length - 1;
        int r0i = this.c;
        while (true) {
            Object r3_Object = this.b[r0i];
            if (r3_Object == null) {
                return false;
            }
            if (r5_Object.equals(r3_Object)) {
                return true;
            }
            r0i = (r0i + 1) & r2i;
        }
    }

    public Iterator<E> descendingIterator() {
        return new b(null);
    }

    public E element() {
        return getFirst();
    }

    public E getFirst() {
        E r0_E = this.b[this.c];
        if (r0_E != null) {
            return r0_E;
        }
        throw new NoSuchElementException();
    }

    public E getLast() {
        E r0_E = this.b[(this.d - 1) & (this.b.length - 1)];
        if (r0_E != null) {
            return r0_E;
        }
        throw new NoSuchElementException();
    }

    public boolean isEmpty() {
        return this.c == this.d;
    }

    public Iterator<E> iterator() {
        return new a(null);
    }

    public boolean offer(E r2_E) {
        return offerLast(r2_E);
    }

    public boolean offerFirst(E r2_E) {
        addFirst(r2_E);
        return true;
    }

    public boolean offerLast(E r2_E) {
        addLast(r2_E);
        return true;
    }

    public E peek() {
        return peekFirst();
    }

    public E peekFirst() {
        return this.b[this.c];
    }

    public E peekLast() {
        return this.b[(this.d - 1) & (this.b.length - 1)];
    }

    public E poll() {
        return pollFirst();
    }

    public E pollFirst() {
        E r0_E = null;
        int r2i = this.c;
        E r1_E = this.b[r2i];
        if (r1_E == null) {
            return null;
        }
        this.b[r2i] = r0_E;
        this.c = (r2i + 1) & (this.b.length - 1);
        return r1_E;
    }

    public E pollLast() {
        E r0_E = null;
        int r2i = (this.b.length - 1) & (this.d - 1);
        E r1_E = this.b[r2i];
        if (r1_E == null) {
            return null;
        }
        this.b[r2i] = r0_E;
        this.d = r2i;
        return r1_E;
    }

    public E pop() {
        return removeFirst();
    }

    public void push(E r1_E) {
        addFirst(r1_E);
    }

    public E remove() {
        return removeFirst();
    }

    public boolean remove(Object r2_Object) {
        return removeFirstOccurrence(r2_Object);
    }

    public E removeFirst() {
        E r0_E = pollFirst();
        if (r0_E != null) {
            return r0_E;
        }
        throw new NoSuchElementException();
    }

    public boolean removeFirstOccurrence(Object r5_Object) {
        if (r5_Object == null) {
            return false;
        }
        int r2i = this.b.length - 1;
        int r0i = this.c;
        while (true) {
            Object r3_Object = this.b[r0i];
            if (r3_Object == null) {
                return false;
            }
            if (r5_Object.equals(r3_Object)) {
                b(r0i);
                return true;
            } else {
                r0i = (r0i + 1) & r2i;
            }
        }
    }

    public E removeLast() {
        E r0_E = pollLast();
        if (r0_E != null) {
            return r0_E;
        }
        throw new NoSuchElementException();
    }

    public boolean removeLastOccurrence(Object r5_Object) {
        if (r5_Object == null) {
            return false;
        }
        int r2i = this.b.length - 1;
        int r0i = (this.d - 1) & r2i;
        while (true) {
            Object r3_Object = this.b[r0i];
            if (r3_Object == null) {
                return false;
            }
            if (r5_Object.equals(r3_Object)) {
                b(r0i);
                return true;
            } else {
                r0i = (r0i - 1) & r2i;
            }
        }
    }

    public int size() {
        return (this.d - this.c) & (this.b.length - 1);
    }

    public Object[] toArray() {
        return a(new Object[size()]);
    }

    public <T> T[] toArray(T[] r4_TA) {
        T[] r0_TA;
        int r1i = size();
        r0_TA = r4_TA.length < r1i ? (Object[]) Array.newInstance(r4_TA.getClass().getComponentType(), r1i) : r4_TA;
        a((Object[])r0_TA);
        if (r0_TA.length > r1i) {
            r0_TA[r1i] = null;
        }
        return r0_TA;
    }
}