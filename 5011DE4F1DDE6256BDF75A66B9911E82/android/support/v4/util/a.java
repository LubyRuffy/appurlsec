package android.support.v4.util;

import java.util.Map;

// compiled from: ArrayMap.java
class a extends c<K, V> {
    final /* synthetic */ ArrayMap a;

    a(ArrayMap r1_ArrayMap) {
        this.a = r1_ArrayMap;
    }

    protected int a_() {
        return this.a.h;
    }

    protected int a_(Object r3_Object) {
        return r3_Object == null ? this.a.a() : this.a.a(r3_Object, r3_Object.hashCode());
    }

    protected Object a_(int r3i, int r4i) {
        return this.a.g[r3i << 1 + r4i];
    }

    protected V a_(int r2i, V r3_V) {
        return this.a.setValueAt(r2i, r3_V);
    }

    protected void a_(int r2i) {
        this.a.removeAt(r2i);
    }

    protected void a_(K r2_K, V r3_V) {
        this.a.put(r2_K, r3_V);
    }

    protected int b(Object r2_Object) {
        return this.a.a(r2_Object);
    }

    protected Map<K, V> b() {
        return this.a;
    }

    protected void c() {
        this.a.clear();
    }
}