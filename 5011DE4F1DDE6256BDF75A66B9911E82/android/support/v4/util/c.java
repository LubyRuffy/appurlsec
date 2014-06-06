package android.support.v4.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// compiled from: MapCollections.java
abstract class c<K, V> {
    b b;
    c c;
    e d;

    // compiled from: MapCollections.java
    final class a<T> implements Iterator<T> {
        final int a;
        int b;
        int c;
        boolean d;

        a(int r3i) {
            this.d = false;
            this.a = r3i;
            this.b = c.this.a();
        }

        public boolean hasNext() {
            return this.c < this.b;
        }

        public T next() {
            T r0_T = c.this.a(this.c, this.a);
            this.c++;
            this.d = true;
            return r0_T;
        }

        public void remove() {
            if (this.d) {
                this.c--;
                this.b--;
                this.d = false;
                c.this.a(this.c);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    // compiled from: MapCollections.java
    final class b implements Set<Entry<K, V>> {
        b() {
        }

        public boolean add(Entry<K, V> r2_Entry_K__V) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends Entry<K, V>> r6_Collection__extends_Entry_K__V) {
            int r1i = c.this.a();
            Iterator r2_Iterator = r6_Collection__extends_Entry_K__V.iterator();
            while (r2_Iterator.hasNext()) {
                Entry r0_Entry = (Entry) r2_Iterator.next();
                c.this.a(r0_Entry.getKey(), r0_Entry.getValue());
            }
            return r1i != c.this.a();
        }

        public void clear() {
            c.this.c();
        }

        public boolean contains(Object r4_Object) {
            if (!(r4_Object instanceof Entry)) {
                return false;
            }
            Entry r4_Entry = (Entry) r4_Object;
            int r1i = c.this.a(r4_Entry.getKey());
            return r1i >= 0 ? b.equal(c.this.a(r1i, 1), r4_Entry.getValue()) : false;
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

        public boolean equals(Object r2_Object) {
            return c.equalsSetHelper(this, r2_Object);
        }

        public int hashCode() {
            int r3i = c.this.a() - 1;
            int r4i = 0;
            while (r3i >= 0) {
                Object r0_Object = c.this.a(r3i, 0);
                Object r5_Object = c.this.a(r3i, 1);
                r3i--;
                r4i += (r5_Object == null ? 0 : r5_Object.hashCode()) ^ (r0_Object == null ? 0 : r0_Object.hashCode());
            }
            return r4i;
        }

        public boolean isEmpty() {
            return c.this.a() == 0;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new d();
        }

        public boolean remove(Object r2_Object) {
            throw new UnsupportedOperationException();
        }

        public boolean removeAll(Collection<?> r2_Collection_) {
            throw new UnsupportedOperationException();
        }

        public boolean retainAll(Collection<?> r2_Collection_) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            return c.this.a();
        }

        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        public <T> T[] toArray(T[] r2_TA) {
            throw new UnsupportedOperationException();
        }
    }

    // compiled from: MapCollections.java
    final class c implements Set<K> {
        c() {
        }

        public boolean add(K r2_K) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends K> r2_Collection__extends_K) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            c.this.c();
        }

        public boolean contains(Object r2_Object) {
            return c.this.a(r2_Object) >= 0;
        }

        public boolean containsAll(Collection<?> r2_Collection_) {
            return c.containsAllHelper(c.this.b(), r2_Collection_);
        }

        public boolean equals(Object r2_Object) {
            return c.equalsSetHelper(this, r2_Object);
        }

        public int hashCode() {
            int r2i = c.this.a() - 1;
            int r3i = 0;
            while (r2i >= 0) {
                Object r0_Object = c.this.a(r2i, 0);
                r3i += r0_Object == null ? 0 : r0_Object.hashCode();
                r2i--;
            }
            return r3i;
        }

        public boolean isEmpty() {
            return c.this.a() == 0;
        }

        public Iterator<K> iterator() {
            return new a(0);
        }

        public boolean remove(Object r3_Object) {
            int r0i = c.this.a(r3_Object);
            if (r0i < 0) {
                return false;
            }
            c.this.a(r0i);
            return true;
        }

        public boolean removeAll(Collection<?> r2_Collection_) {
            return c.removeAllHelper(c.this.b(), r2_Collection_);
        }

        public boolean retainAll(Collection<?> r2_Collection_) {
            return c.retainAllHelper(c.this.b(), r2_Collection_);
        }

        public int size() {
            return c.this.a();
        }

        public Object[] toArray() {
            return c.this.toArrayHelper(0);
        }

        public <T> T[] toArray(T[] r3_TA) {
            return c.this.toArrayHelper(r3_TA, 0);
        }
    }

    // compiled from: MapCollections.java
    final class d implements Iterator<Entry<K, V>>, Entry<K, V> {
        int a;
        int b;
        boolean c;

        d() {
            this.c = false;
            this.a = c.this.a() - 1;
            this.b = -1;
        }

        public final boolean equals(Object r6_Object) {
            boolean r0z = true;
            if (this.c) {
                if (!(r6_Object instanceof Entry)) {
                    return false;
                }
                Entry r6_Entry = (Entry) r6_Object;
                if (b.equal(r6_Entry.getKey(), c.this.a(this.b, 0)) && b.equal(r6_Entry.getValue(), c.this.a(this.b, 1))) {
                    return r0z;
                }
                r0z = false;
                return r0z;
            } else {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
        }

        public K getKey() {
            if (this.c) {
                return c.this.a(this.b, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public V getValue() {
            if (this.c) {
                return c.this.a(this.b, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public boolean hasNext() {
            return this.b < this.a;
        }

        public final int hashCode() {
            int r0i = 0;
            if (this.c) {
                int r1i;
                Object r1_Object = c.this.a(this.b, 0);
                Object r2_Object = c.this.a(this.b, 1);
                r1i = r1_Object == null ? 0 : r1_Object.hashCode();
                if (r2_Object == null) {
                    return r0i ^ r1i;
                }
                r0i = r2_Object.hashCode();
                return r0i ^ r1i;
            } else {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
        }

        public Entry<K, V> next() {
            this.b++;
            this.c = true;
            return this;
        }

        public void remove() {
            if (this.c) {
                this.b--;
                this.a--;
                this.c = false;
                c.this.a(this.b);
            } else {
                throw new IllegalStateException();
            }
        }

        public V setValue(V r3_V) {
            if (this.c) {
                return c.this.a(this.b, (Object)r3_V);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

    // compiled from: MapCollections.java
    final class e implements Collection<V> {
        e() {
        }

        public boolean add(V r2_V) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends V> r2_Collection__extends_V) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            c.this.c();
        }

        public boolean contains(Object r2_Object) {
            return c.this.b(r2_Object) >= 0;
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
            return c.this.a() == 0;
        }

        public Iterator<V> iterator() {
            return new a(1);
        }

        public boolean remove(Object r3_Object) {
            int r0i = c.this.b(r3_Object);
            if (r0i < 0) {
                return false;
            }
            c.this.a(r0i);
            return true;
        }

        public boolean removeAll(Collection<?> r6_Collection_) {
            int r0i = 0;
            int r3i = c.this.a();
            boolean r1z = false;
            while (r0i < r3i) {
                if (r6_Collection_.contains(c.this.a(r0i, 1))) {
                    c.this.a(r0i);
                    r0i--;
                    r3i--;
                    r1z = true;
                }
                r0i++;
            }
            return r1z;
        }

        public boolean retainAll(Collection<?> r6_Collection_) {
            int r0i = 0;
            int r3i = c.this.a();
            boolean r1z = false;
            while (r0i < r3i) {
                if (!r6_Collection_.contains(c.this.a(r0i, 1))) {
                    c.this.a(r0i);
                    r0i--;
                    r3i--;
                    r1z = true;
                }
                r0i++;
            }
            return r1z;
        }

        public int size() {
            return c.this.a();
        }

        public Object[] toArray() {
            return c.this.toArrayHelper(1);
        }

        public <T> T[] toArray(T[] r3_TA) {
            return c.this.toArrayHelper(r3_TA, 1);
        }
    }

    c() {
    }

    public static <K, V> boolean containsAllHelper(Map<K, V> r2_Map_K__V, Collection<?> r3_Collection_) {
        Iterator r0_Iterator = r3_Collection_.iterator();
        while (r0_Iterator.hasNext()) {
            if (!r2_Map_K__V.containsKey(r0_Iterator.next())) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean equalsSetHelper(Set<T> r4_Set_T, Object r5_Object) {
        boolean r0z = true;
        if (r4_Set_T == r5_Object) {
            return true;
        }
        if (!(r5_Object instanceof Set)) {
            return false;
        }
        Set r5_Set = (Set) r5_Object;
        try {
            if (r4_Set_T.size() == r5_Set.size() && r4_Set_T.containsAll(r5_Set)) {
                return r0z;
            }
            r0z = false;
            return r0z;
        } catch (NullPointerException e) {
            return false;
        } catch (ClassCastException e_2) {
            return false;
        }
    }

    public static <K, V> boolean removeAllHelper(Map<K, V> r3_Map_K__V, Collection<?> r4_Collection_) {
        int r0i = r3_Map_K__V.size();
        Iterator r1_Iterator = r4_Collection_.iterator();
        while (r1_Iterator.hasNext()) {
            r3_Map_K__V.remove(r1_Iterator.next());
        }
        return r0i != r3_Map_K__V.size();
    }

    public static <K, V> boolean retainAllHelper(Map<K, V> r3_Map_K__V, Collection<?> r4_Collection_) {
        int r0i = r3_Map_K__V.size();
        Iterator r1_Iterator = r3_Map_K__V.keySet().iterator();
        while (r1_Iterator.hasNext()) {
            if (!r4_Collection_.contains(r1_Iterator.next())) {
                r1_Iterator.remove();
            }
        }
        return r0i != r3_Map_K__V.size();
    }

    protected abstract int a();

    protected abstract int a(Object r1_Object);

    protected abstract Object a(int r1i, int r2i);

    protected abstract V a(int r1i, V r2_V);

    protected abstract void a(int r1i);

    protected abstract void a(K r1_K, V r2_V);

    protected abstract int b(Object r1_Object);

    protected abstract Map<K, V> b();

    protected abstract void c_();

    public Set<Entry<K, V>> getEntrySet() {
        if (this.b == null) {
            this.b = new b();
        }
        return this.b;
    }

    public Set<K> getKeySet() {
        if (this.c == null) {
            this.c = new c();
        }
        return this.c;
    }

    public Collection<V> getValues() {
        if (this.d == null) {
            this.d = new e();
        }
        return this.d;
    }

    public Object[] toArrayHelper(int r5i) {
        int r1i = a();
        Object[] r2_ObjectA = new Object[r1i];
        int r0i = 0;
        while (r0i < r1i) {
            r2_ObjectA[r0i] = a(r0i, r5i);
            r0i++;
        }
        return r2_ObjectA;
    }

    public <T> T[] toArrayHelper(T[] r5_TA, int r6i) {
        T[] r0_TA;
        int r2i = a();
        r0_TA = r5_TA.length < r2i ? (Object[]) Array.newInstance(r5_TA.getClass().getComponentType(), r2i) : r5_TA;
        int r1i = 0;
        while (r1i < r2i) {
            r0_TA[r1i] = a(r1i, r6i);
            r1i++;
        }
        if (r0_TA.length > r2i) {
            r0_TA[r2i] = null;
        }
        return r0_TA;
    }
}