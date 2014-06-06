package android.support.v4.util;

public class SparseArrayCompat<E> implements Cloneable {
    private static final Object a;
    private boolean b;
    private int[] c;
    private Object[] d;
    private int e;

    static {
        a = new Object();
    }

    public SparseArrayCompat() {
        this(10);
    }

    public SparseArrayCompat(int r4i) {
        this.b = false;
        if (r4i == 0) {
            this.c = b.a;
            this.d = b.c;
        } else {
            int r0i = b.idealIntArraySize(r4i);
            this.c = new int[r0i];
            this.d = new Object[r0i];
        }
        this.e = 0;
    }

    private void a() {
        int r3i = this.e;
        int[] r4_intA = this.c;
        Object[] r5_ObjectA = this.d;
        int r1i = 0;
        int r0i = 0;
        while (r1i < r3i) {
            Object r6_Object = r5_ObjectA[r1i];
            if (r6_Object != a) {
                if (r1i != r0i) {
                    r4_intA[r0i] = r4_intA[r1i];
                    r5_ObjectA[r0i] = r6_Object;
                    r5_ObjectA[r1i] = null;
                }
                r0i++;
            }
            r1i++;
        }
        this.b = false;
        this.e = r0i;
    }

    public void append(int r7i, E r8_E) {
        if (this.e == 0 || r7i > this.c[this.e - 1]) {
            int r0i;
            if ((!this.b) || this.e < this.c.length) {
                r0i = this.e;
            } else {
                a();
                r0i = this.e;
            }
            if (r0i >= this.c.length) {
                int r1i = b.idealIntArraySize(r0i + 1);
                Object r2_Object = new Object[r1i];
                Object r1_Object = new Object[r1i];
                System.arraycopy(this.c, 0, r2_Object, 0, this.c.length);
                System.arraycopy(this.d, 0, r1_Object, 0, this.d.length);
                this.c = r2_Object;
                this.d = r1_Object;
            }
            this.c[r0i] = r7i;
            this.d[r0i] = r8_E;
            this.e = r0i + 1;
        } else {
            put(r7i, r8_E);
        }
    }

    public void clear() {
        int r2i = this.e;
        Object[] r3_ObjectA = this.d;
        int r0i = 0;
        while (r0i < r2i) {
            r3_ObjectA[r0i] = null;
            r0i++;
        }
        this.e = 0;
        this.b = false;
    }

    public SparseArrayCompat<E> clone() {
        SparseArrayCompat<E> r0_SparseArrayCompat_E;
        try {
            r0_SparseArrayCompat_E = (SparseArrayCompat) super.clone();
            r0_SparseArrayCompat_E.c = (int[]) this.c.clone();
            r0_SparseArrayCompat_E.d = (Object[]) this.d.clone();
            return r0_SparseArrayCompat_E;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void delete(int r4i) {
        int r0i = b.a(this.c, this.e, r4i);
        if (r0i < 0 || this.d[r0i] == a) {
        } else {
            this.d[r0i] = a;
            this.b = true;
        }
    }

    public E get(int r2i) {
        return get(r2i, null);
    }

    public E get(int r4i, E r5_E) {
        int r0i = b.a(this.c, this.e, r4i);
        return (r0i < 0 || this.d[r0i] == a) ? r5_E : this.d[r0i];
    }

    public int indexOfKey(int r3i) {
        if (this.b) {
            a();
        }
        return b.a(this.c, this.e, r3i);
    }

    public int indexOfValue(E r3_E) {
        if (this.b) {
            a();
        }
        int r0i = 0;
        while (r0i < this.e) {
            if (this.d[r0i] == r3_E) {
                return r0i;
            }
            r0i++;
        }
        return -1;
    }

    public int keyAt(int r2i) {
        if (this.b) {
            a();
        }
        return this.c[r2i];
    }

    public void put(int r7i, E r8_E) {
        int r0i = b.a(this.c, this.e, r7i);
        if (r0i >= 0) {
            this.d[r0i] = r8_E;
        } else {
            r0i ^= -1;
            if (r0i >= this.e || this.d[r0i] != a) {
                if ((!this.b) || this.e < this.c.length) {
                } else {
                    a();
                    r0i = b.a(this.c, this.e, r7i) ^ -1;
                }
                if (this.e >= this.c.length) {
                    int r1i = b.idealIntArraySize(this.e + 1);
                    Object r2_Object = new Object[r1i];
                    Object r1_Object = new Object[r1i];
                    System.arraycopy(this.c, 0, r2_Object, 0, this.c.length);
                    System.arraycopy(this.d, 0, r1_Object, 0, this.d.length);
                    this.c = r2_Object;
                    this.d = r1_Object;
                }
                if (this.e - r0i != 0) {
                    System.arraycopy(this.c, r0i, this.c, r0i + 1, this.e - r0i);
                    System.arraycopy(this.d, r0i, this.d, r0i + 1, this.e - r0i);
                }
                this.c[r0i] = r7i;
                this.d[r0i] = r8_E;
                this.e++;
            } else {
                this.c[r0i] = r7i;
                this.d[r0i] = r8_E;
            }
        }
    }

    public void remove(int r1i) {
        delete(r1i);
    }

    public void removeAt(int r3i) {
        if (this.d[r3i] != a) {
            this.d[r3i] = a;
            this.b = true;
        }
    }

    public void removeAtRange(int r3i, int r4i) {
        int r0i = Math.min(this.e, r3i + r4i);
        while (r3i < r0i) {
            removeAt(r3i);
            r3i++;
        }
    }

    public void setValueAt(int r2i, E r3_E) {
        if (this.b) {
            a();
        }
        this.d[r2i] = r3_E;
    }

    public int size() {
        if (this.b) {
            a();
        }
        return this.e;
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder r1_StringBuilder = new StringBuilder(this.e * 28);
        r1_StringBuilder.append('{');
        int r0i = 0;
        while (r0i < this.e) {
            if (r0i > 0) {
                r1_StringBuilder.append(", ");
            }
            r1_StringBuilder.append(keyAt(r0i));
            r1_StringBuilder.append('=');
            SparseArrayCompat r2_SparseArrayCompat = valueAt(r0i);
            if (r2_SparseArrayCompat != this) {
                r1_StringBuilder.append(r2_SparseArrayCompat);
            } else {
                r1_StringBuilder.append("(this Map)");
            }
            r0i++;
        }
        r1_StringBuilder.append('}');
        return r1_StringBuilder.toString();
    }

    public E valueAt(int r2i) {
        if (this.b) {
            a();
        }
        return this.d[r2i];
    }
}