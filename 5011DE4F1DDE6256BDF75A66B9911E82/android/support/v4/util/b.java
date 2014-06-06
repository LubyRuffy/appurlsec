package android.support.v4.util;

import qsbk.app.widget.listview.XListViewFooter;

// compiled from: ContainerHelpers.java
class b {
    static final int[] a;
    static final long[] b;
    static final Object[] c;

    static {
        a = new int[0];
        b = new long[0];
        c = new Object[0];
    }

    static int a(int[] r4_intA, int r5i, int r6i) {
        int r1i = 0;
        int r0i = r5i - 1;
        while (r1i <= r0i) {
            int r2i = (r1i + r0i) >>> 1;
            int r3i = r4_intA[r2i];
            if (r3i < r6i) {
                r1i = r2i + 1;
            } else {
                if (r3i <= r6i) {
                    return r2i;
                }
                r0i = r2i - 1;
            }
        }
        return r1i ^ -1;
    }

    static int a(long[] r6_longA, int r7i, long r8j) {
        int r1i = 0;
        int r0i = r7i - 1;
        while (r1i <= r0i) {
            int r2i = (r1i + r0i) >>> 1;
            long r3j = r6_longA[r2i];
            if (r3j < r8j) {
                r1i = r2i + 1;
            } else {
                if (r3j <= r8j) {
                    return r2i;
                }
                r0i = r2i - 1;
            }
        }
        return r1i ^ -1;
    }

    public static boolean equal(Object r1_Object, Object r2_Object) {
        if (r1_Object != r2_Object) {
            if (r1_Object == null || (!r1_Object.equals(r2_Object))) {
                return false;
            }
        }
        return true;
    }

    public static int idealByteArraySize(int r3i) {
        int r0i = XListViewFooter.STATE_NODATA;
        while (r0i < 32) {
            if (r3i <= 1 << r0i - 12) {
                return 1 << r0i - 12;
            }
            r0i++;
        }
        return r3i;
    }

    public static int idealIntArraySize(int r1i) {
        return idealByteArraySize(r1i * 4) / 4;
    }

    public static int idealLongArraySize(int r1i) {
        return idealByteArraySize(r1i * 8) / 8;
    }
}