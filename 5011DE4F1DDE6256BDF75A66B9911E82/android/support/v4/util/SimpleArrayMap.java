package android.support.v4.util;

import java.util.Map;
import qsbk.app.utils.Base64;

public class SimpleArrayMap<K, V> {
    static Object[] b;
    static int c;
    static Object[] d;
    static int e;
    int[] f;
    Object[] g;
    int h;

    public SimpleArrayMap() {
        this.f = b.a;
        this.g = b.c;
        this.h = 0;
    }

    public SimpleArrayMap(int r2i) {
        if (r2i == 0) {
            this.f = b.a;
            this.g = b.c;
        } else {
            a(r2i);
        }
        this.h = 0;
    }

    public SimpleArrayMap(SimpleArrayMap r1_SimpleArrayMap) {
        this();
        if (r1_SimpleArrayMap != null) {
            putAll(r1_SimpleArrayMap);
        }
    }

    private void a(int r6i) {
        Object[] r2_ObjectA;
        if (r6i == 8) {
            synchronized (ArrayMap.class) {
                if (d != null) {
                    r2_ObjectA = d;
                    this.g = r2_ObjectA;
                    d = (Object[]) r2_ObjectA[0];
                    this.f = (int[]) r2_ObjectA[1];
                    r2_ObjectA[1] = null;
                    r2_ObjectA[0] = null;
                    e--;
                } else {
                    this.f = new int[r6i];
                    this.g = new Object[(r6i << 1)];
                }
            }
        } else {
            if (r6i == 4) {
                synchronized (ArrayMap.class) {
                    if (b != null) {
                        r2_ObjectA = b;
                        this.g = r2_ObjectA;
                        b = (Object[]) r2_ObjectA[0];
                        this.f = (int[]) r2_ObjectA[1];
                        r2_ObjectA[1] = null;
                        r2_ObjectA[0] = null;
                        c--;
                    }
                }
            }
            this.f = new int[r6i];
            this.g = new Object[(r6i << 1)];
        }
    }

    private static void a(int[] r4_intA, Object[] r5_ObjectA, int r6i) {
        int r0i;
        if (r4_intA.length == 8) {
            synchronized (ArrayMap.class) {
                if (e < 10) {
                    r5_ObjectA[0] = d;
                    r5_ObjectA[1] = r4_intA;
                    r0i = r6i << 1 - 1;
                    while (r0i >= 2) {
                        r5_ObjectA[r0i] = null;
                        r0i--;
                    }
                    d = r5_ObjectA;
                    e++;
                }
            }
        } else if (r4_intA.length == 4) {
            synchronized (ArrayMap.class) {
                if (c < 10) {
                    r5_ObjectA[0] = b;
                    r5_ObjectA[1] = r4_intA;
                    r0i = r6i << 1 - 1;
                    while (r0i >= 2) {
                        r5_ObjectA[r0i] = null;
                        r0i--;
                    }
                    b = r5_ObjectA;
                    c++;
                }
            }
        }
    }

    int a() {
        int r2i = this.h;
        if (r2i == 0) {
            return -1;
        }
        int r0i = b.a(this.f, r2i, 0);
        if (r0i < 0 || this.g[r0i << 1] == null) {
            return r0i;
        }
        int r1i = r0i + 1;
        while (r1i < r2i && this.f[r1i] == 0) {
            if (this.g[r1i << 1] == null) {
                return r1i;
            }
            r1i++;
        }
        r0i--;
        while (r0i >= 0 && this.f[r0i] == 0) {
            if (this.g[r0i << 1] == null) {
                return r0i;
            }
            r0i--;
        }
        return r1i ^ -1;
    }

    int a(Object r5_Object) {
        int r0i = 1;
        int r1i = this.h * 2;
        Object[] r2_ObjectA = this.g;
        if (r5_Object == null) {
            while (r0i < r1i) {
                if (r2_ObjectA[r0i] == null) {
                    return r0i >> 1;
                }
                r0i += 2;
            }
            return -1;
        }
        while (r0i < r1i) {
            if (r5_Object.equals(r2_ObjectA[r0i])) {
                return r0i >> 1;
            }
            r0i += 2;
        }
        return -1;
    }

    int a(Object r6_Object, int r7i) {
        int r2i = this.h;
        if (r2i == 0) {
            return -1;
        }
        int r0i = b.a(this.f, r2i, r7i);
        if (r0i < 0 || r6_Object.equals(this.g[r0i << 1])) {
            return r0i;
        }
        int r1i = r0i + 1;
        while (r1i < r2i && this.f[r1i] == r7i) {
            if (r6_Object.equals(this.g[r1i << 1])) {
                return r1i;
            }
            r1i++;
        }
        r0i--;
        while (r0i >= 0 && this.f[r0i] == r7i) {
            if (r6_Object.equals(this.g[r0i << 1])) {
                return r0i;
            }
            r0i--;
        }
        return r1i ^ -1;
    }

    public void clear() {
        if (this.h != 0) {
            a(this.f, this.g, this.h);
            this.f = b.a;
            this.g = b.c;
            this.h = 0;
        }
    }

    public boolean containsKey(Object r4_Object) {
        if (r4_Object == null) {
            return a() >= 0;
        } else {
            if (a(r4_Object, r4_Object.hashCode()) < 0) {
                return false;
            }
            return true;
        }
    }

    public boolean containsValue(Object r2_Object) {
        return a(r2_Object) >= 0;
    }

    public void ensureCapacity(int r6i) {
        if (this.f.length < r6i) {
            Object r0_Object = this.f;
            Object r1_Object = this.g;
            a(r6i);
            if (this.h > 0) {
                System.arraycopy(r0_Object, 0, this.f, 0, this.h);
                System.arraycopy(r1_Object, 0, this.g, 0, this.h << 1);
            }
            a(r0_Object, r1_Object, this.h);
        }
    }

    public boolean equals(Object r7_Object) {
        if (this == r7_Object) {
            return true;
        }
        if (!(r7_Object instanceof Map)) {
            return false;
        }
        Map r7_Map = (Map) r7_Object;
        if (size() != r7_Map.size()) {
            return false;
        }
        int r2i = 0;
        while (true) {
            try {
                if (r2i >= this.h) {
                    return true;
                }
                Object r3_Object = keyAt(r2i);
                Object r4_Object = valueAt(r2i);
                Object r5_Object = r7_Map.get(r3_Object);
                if (r4_Object == null) {
                    if (!(r5_Object == null && r7_Map.containsKey(r3_Object))) {
                        return false;
                    }
                } else if (!r4_Object.equals(r5_Object)) {
                    return false;
                }
                r2i++;
            } catch (NullPointerException e) {
                return false;
            } catch (ClassCastException e_2) {
                return false;
            }
        }
    }

    public V get(Object r3_Object) {
        int r0i;
        r0i = r3_Object == null ? a() : a(r3_Object, r3_Object.hashCode());
        return r0i >= 0 ? this.g[r0i << 1 + 1] : null;
    }

    public int hashCode() {
        int[] r5_intA = this.f;
        Object[] r6_ObjectA = this.g;
        int r7i = this.h;
        int r2i = 1;
        int r3i = 0;
        int r4i = 0;
        while (r3i < r7i) {
            Object r0_Object = r6_ObjectA[r2i];
            r4i += (r0_Object == null ? 0 : r0_Object.hashCode()) ^ r5_intA[r3i];
            r3i++;
            r2i += 2;
        }
        return r4i;
    }

    public boolean isEmpty() {
        return this.h <= 0;
    }

    public K keyAt(int r3i) {
        return this.g[r3i << 1];
    }

    public V put(K r8_K, V r9_V) {
        int r2i;
        int r3i;
        int r0i = Base64.DONT_BREAK_LINES;
        if (r8_K == null) {
            r2i = a();
            r3i = 0;
        } else {
            r3i = r8_K.hashCode();
            r2i = a(r8_K, r3i);
        }
        if (r2i >= 0) {
            int r1i = r2i << 1 + 1;
            V r0_V = this.g[r1i];
            this.g[r1i] = r9_V;
            return r0_V;
        } else {
            r2i ^= -1;
            if (this.h >= this.f.length) {
                if (this.h >= 8) {
                    r0i = this.h + this.h >> 1;
                } else if (this.h < 4) {
                    r0i = 4;
                }
                Object r1_Object = this.f;
                Object r5_Object = this.g;
                a(r0i);
                if (this.f.length > 0) {
                    System.arraycopy(r1_Object, 0, this.f, 0, r1_Object.length);
                    System.arraycopy(r5_Object, 0, this.g, 0, r5_Object.length);
                }
                a(r1_Object, r5_Object, this.h);
            }
            if (r2i < this.h) {
                System.arraycopy(this.f, r2i, this.f, r2i + 1, this.h - r2i);
                System.arraycopy(this.g, r2i << 1, this.g, (r2i + 1) << 1, (this.h - r2i) << 1);
            }
            this.f[r2i] = r3i;
            this.g[r2i << 1] = r8_K;
            this.g[r2i << 1 + 1] = r9_V;
            this.h++;
            return null;
        }
    }

    public void putAll(SimpleArrayMap<? extends K, ? extends V> r6_SimpleArrayMap__extends_K___extends_V) {
        int r0i = 0;
        int r1i = r6_SimpleArrayMap__extends_K___extends_V.h;
        ensureCapacity(this.h + r1i);
        if (this.h == 0) {
            if (r1i > 0) {
                System.arraycopy(r6_SimpleArrayMap__extends_K___extends_V.f, 0, this.f, 0, r1i);
                System.arraycopy(r6_SimpleArrayMap__extends_K___extends_V.g, 0, this.g, 0, r1i << 1);
                this.h = r1i;
            }
            return;
        }
        while (r0i < r1i) {
            put(r6_SimpleArrayMap__extends_K___extends_V.keyAt(r0i), r6_SimpleArrayMap__extends_K___extends_V.valueAt(r0i));
            r0i++;
        }
    }

    public V remove(Object r2_Object) {
        int r0i;
        r0i = r2_Object == null ? a() : a(r2_Object, r2_Object.hashCode());
        return r0i >= 0 ? removeAt(r0i) : null;
    }

    public V removeAt(int r8i) {
        int r0i = Base64.DONT_BREAK_LINES;
        V r1_V = this.g[r8i << 1 + 1];
        if (this.h <= 1) {
            a(this.f, this.g, this.h);
            this.f = b.a;
            this.g = b.c;
            this.h = 0;
        } else if (this.f.length <= 8 || this.h >= this.f.length / 3) {
            this.h--;
            if (r8i < this.h) {
                System.arraycopy(this.f, r8i + 1, this.f, r8i, this.h - r8i);
                System.arraycopy(this.g, (r8i + 1) << 1, this.g, r8i << 1, (this.h - r8i) << 1);
            }
            this.g[this.h << 1] = null;
            this.g[this.h << 1 + 1] = null;
        } else {
            if (this.h > 8) {
                r0i = this.h + this.h >> 1;
            }
            Object r2_Object = this.f;
            Object r3_Object = this.g;
            a(r0i);
            this.h--;
            if (r8i > 0) {
                System.arraycopy(r2_Object, 0, this.f, 0, r8i);
                System.arraycopy(r3_Object, 0, this.g, 0, r8i << 1);
            }
            if (r8i < this.h) {
                System.arraycopy(r2_Object, r8i + 1, this.f, r8i, this.h - r8i);
                System.arraycopy(r3_Object, (r8i + 1) << 1, this.g, r8i << 1, (this.h - r8i) << 1);
            }
        }
        return r1_V;
    }

    public V setValueAt(int r4i, V r5_V) {
        int r0i = r4i << 1 + 1;
        V r1_V = this.g[r0i];
        this.g[r0i] = r5_V;
        return r1_V;
    }

    public int size() {
        return this.h;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder r1_StringBuilder = new StringBuilder(this.h * 28);
        r1_StringBuilder.append('{');
        int r0i = 0;
        while (r0i < this.h) {
            if (r0i > 0) {
                r1_StringBuilder.append(", ");
            }
            SimpleArrayMap r2_SimpleArrayMap = keyAt(r0i);
            if (r2_SimpleArrayMap != this) {
                r1_StringBuilder.append(r2_SimpleArrayMap);
            } else {
                r1_StringBuilder.append("(this Map)");
            }
            r1_StringBuilder.append('=');
            r2_SimpleArrayMap = valueAt(r0i);
            if (r2_SimpleArrayMap != this) {
                r1_StringBuilder.append(r2_SimpleArrayMap);
            } else {
                r1_StringBuilder.append("(this Map)");
            }
            r0i++;
        }
        r1_StringBuilder.append('}');
        return r1_StringBuilder.toString();
    }

    public V valueAt(int r3i) {
        return this.g[r3i << 1 + 1];
    }
}