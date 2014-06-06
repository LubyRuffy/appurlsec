package android.support.v4.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ArrayMap<K, V> extends SimpleArrayMap<K, V> implements Map<K, V> {
    c<K, V> a;

    public ArrayMap(int r1i) {
        super(r1i);
    }

    public ArrayMap(SimpleArrayMap r1_SimpleArrayMap) {
        super(r1_SimpleArrayMap);
    }

    private c<K, V> b() {
        if (this.a == null) {
            this.a = new a(this);
        }
        return this.a;
    }

    public boolean containsAll(Collection<?> r2_Collection_) {
        return c.containsAllHelper(this, r2_Collection_);
    }

    public Set<Entry<K, V>> entrySet() {
        return b().getEntrySet();
    }

    public Set<K> keySet() {
        return b().getKeySet();
    }

    public void putAll(Map<? extends K, ? extends V> r4_Map__extends_K___extends_V) {
        ensureCapacity(this.h + r4_Map__extends_K___extends_V.size());
        Iterator r1_Iterator = r4_Map__extends_K___extends_V.entrySet().iterator();
        while (r1_Iterator.hasNext()) {
            Entry r0_Entry = (Entry) r1_Iterator.next();
            put(r0_Entry.getKey(), r0_Entry.getValue());
        }
    }

    public boolean removeAll(Collection<?> r2_Collection_) {
        return c.removeAllHelper(this, r2_Collection_);
    }

    public boolean retainAll(Collection<?> r2_Collection_) {
        return c.retainAllHelper(this, r2_Collection_);
    }

    public Collection<V> values() {
        return b().getValues();
    }
}