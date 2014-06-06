package qsbk.app.core;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractCollection<E> implements Collection<E> {
    protected AbstractCollection() {
    }

    public boolean add(E r2_E) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends E> r4_Collection__extends_E) {
        boolean r0z = false;
        Iterator r1_Iterator = r4_Collection__extends_E.iterator();
        while (r1_Iterator.hasNext()) {
            if (add(r1_Iterator.next())) {
                r0z = true;
            }
        }
        return r0z;
    }

    public void clear() {
        Iterator r0_Iterator = iterator();
        while (r0_Iterator.hasNext()) {
            r0_Iterator.next();
            r0_Iterator.remove();
        }
    }

    public boolean contains(Object r4_Object) {
        Iterator r1_Iterator = iterator();
        if (r4_Object != null) {
            while (r1_Iterator.hasNext()) {
                if (r4_Object.equals(r1_Iterator.next())) {
                    return true;
                }
            }
            return false;
        }
        while (r1_Iterator.hasNext()) {
            if (r1_Iterator.next() == null) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(Collection<?> r3_Collection_) {
        Iterator r0_Iterator = r3_Collection_.iterator();
        while (r0_Iterator.hasNext()) {
            if (!contains(r0_Iterator.next())) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public abstract Iterator<E> iterator();

    public boolean remove(Object r4_Object) {
        Iterator r1_Iterator = iterator();
        if (r4_Object != null) {
            while (r1_Iterator.hasNext()) {
                if (r4_Object.equals(r1_Iterator.next())) {
                    r1_Iterator.remove();
                    return true;
                }
            }
            return false;
        }
        while (r1_Iterator.hasNext()) {
            if (r1_Iterator.next() == null) {
                r1_Iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean removeAll(Collection<?> r4_Collection_) {
        boolean r0z = false;
        Iterator r1_Iterator = iterator();
        while (r1_Iterator.hasNext()) {
            if (r4_Collection_.contains(r1_Iterator.next())) {
                r1_Iterator.remove();
                r0z = true;
            }
        }
        return r0z;
    }

    public boolean retainAll(Collection<?> r4_Collection_) {
        boolean r0z = false;
        Iterator r1_Iterator = iterator();
        while (r1_Iterator.hasNext()) {
            if (!r4_Collection_.contains(r1_Iterator.next())) {
                r1_Iterator.remove();
                r0z = true;
            }
        }
        return r0z;
    }

    public abstract int size();

    public Object[] toArray() {
        int r2i = size();
        int r0i = 0;
        Iterator r3_Iterator = iterator();
        Object[] r4_ObjectA = new Object[r2i];
        while (r0i < r2i) {
            r4_ObjectA[r0i] = r3_Iterator.next();
            r0i++;
        }
        return r4_ObjectA;
    }

    public <T> T[] toArray(T[] r6_TA) {
        T[] r0_TA;
        int r0i = size();
        int r1i = 0;
        r0_TA = r0i > r6_TA.length ? (Object[]) Array.newInstance(r6_TA.getClass().getComponentType(), r0i) : r6_TA;
        Iterator r3_Iterator = iterator();
        while (r3_Iterator.hasNext()) {
            r0_TA[r1i] = r3_Iterator.next();
            r1i++;
        }
        if (r1i < r0_TA.length) {
            r0_TA[r1i] = null;
        }
        return r0_TA;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder r0_StringBuilder = new StringBuilder(size() * 16);
        r0_StringBuilder.append('[');
        Iterator r1_Iterator = iterator();
        while (r1_Iterator.hasNext()) {
            AbstractCollection r2_AbstractCollection = r1_Iterator.next();
            if (r2_AbstractCollection != this) {
                r0_StringBuilder.append(r2_AbstractCollection);
            } else {
                r0_StringBuilder.append("(this Collection)");
            }
            if (r1_Iterator.hasNext()) {
                r0_StringBuilder.append(", ");
            }
        }
        r0_StringBuilder.append(']');
        return r0_StringBuilder.toString();
    }
}