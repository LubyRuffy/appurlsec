package android.support.v4.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LruCache<K, V> {
    private final LinkedHashMap<K, V> a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;

    public LruCache(int r5i) {
        if (r5i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else {
            this.c = r5i;
            this.a = new LinkedHashMap(0, 0.75f, true);
        }
    }

    private int b(K r4_K, V r5_V) {
        int r0i = a(r4_K, r5_V);
        if (r0i >= 0) {
            return r0i;
        }
        throw new IllegalStateException("Negative size: " + r4_K + "=" + r5_V);
    }

    protected int a(K r2_K, V r3_V) {
        return 1;
    }

    protected V a(K r2_K) {
        return null;
    }

    protected void a(boolean r1z, K r2_K, V r3_V, V r4_V) {
    }

    public final synchronized int createCount() {
        return this.e;
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final synchronized int evictionCount() {
        return this.f;
    }

    public final V get(K r5_K) {
        if (r5_K == null) {
            throw new NullPointerException("key == null");
        } else {
            V r0_V;
            synchronized (this) {
                r0_V = this.a.get(r5_K);
                if (r0_V != null) {
                    this.g++;
                } else {
                    this.h++;
                    V r1_V = a(r5_K);
                    if (r1_V == null) {
                        r0_V = null;
                    } else {
                        synchronized (this) {
                            this.e++;
                            r0_V = this.a.put(r5_K, r1_V);
                            if (r0_V != null) {
                                this.a.put(r5_K, r0_V);
                            } else {
                                this.b += b(r5_K, r1_V);
                            }
                        }
                        if (r0_V != null) {
                            a(false, r5_K, r1_V, r0_V);
                        } else {
                            trimToSize(this.c);
                            r0_V = r1_V;
                        }
                    }
                }
            }
            return r0_V;
        }
    }

    public final synchronized int hitCount() {
        return this.g;
    }

    public final synchronized int maxSize() {
        return this.c;
    }

    public final synchronized int missCount() {
        return this.h;
    }

    public final V put(K r4_K, V r5_V) {
        if (r4_K == null || r5_V == null) {
            throw new NullPointerException("key == null || value == null");
        } else {
            V r0_V;
            synchronized (this) {
                this.d++;
                this.b += b(r4_K, r5_V);
                r0_V = this.a.put(r4_K, r5_V);
                if (r0_V != null) {
                    this.b -= b(r4_K, r0_V);
                }
            }
            if (r0_V != null) {
                a(false, r4_K, r0_V, r5_V);
            }
            trimToSize(this.c);
            return r0_V;
        }
    }

    public final synchronized int putCount() {
        return this.d;
    }

    public final V remove(K r4_K) {
        if (r4_K == null) {
            throw new NullPointerException("key == null");
        } else {
            V r0_V;
            synchronized (this) {
                r0_V = this.a.remove(r4_K);
                if (r0_V != null) {
                    this.b -= b(r4_K, r0_V);
                }
            }
            if (r0_V != null) {
                a(false, r4_K, r0_V, null);
            }
            return r0_V;
        }
    }

    public final synchronized int size() {
        return this.b;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.a);
    }

    public final synchronized String toString() {
        String r0_String;
        int r0i = 0;
        synchronized (this) {
            int r1i = this.g + this.h;
            if (r1i != 0) {
                r0i = (this.g * 100) / r1i;
            }
            Object[] r2_ObjectA = new Object[4];
            r2_ObjectA[0] = Integer.valueOf(this.c);
            r2_ObjectA[1] = Integer.valueOf(this.g);
            r2_ObjectA[2] = Integer.valueOf(this.h);
            r2_ObjectA[3] = Integer.valueOf(r0i);
            r0_String = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", r2_ObjectA);
        }
        return r0_String;
    }

    public void trimToSize(int r5i) {
        while (true) {
            synchronized (this) {
                if (this.b >= 0) {
                    if ((!this.a.isEmpty()) || this.b == 0) {
                        if (this.b <= r5i || this.a.isEmpty()) {
                            return;
                        } else {
                            Entry r0_Entry = (Entry) this.a.entrySet().iterator().next();
                            Object r1_Object = r0_Entry.getKey();
                            Object r0_Object = r0_Entry.getValue();
                            this.a.remove(r1_Object);
                            this.b -= b(r1_Object, r0_Object);
                            this.f++;
                            a(true, r1_Object, r0_Object, null);
                        }
                    }
                }
                throw new IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
            }
        }
    }
}