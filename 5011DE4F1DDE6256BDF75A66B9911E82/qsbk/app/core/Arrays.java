package qsbk.app.core;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;

public class Arrays {
    static final /* synthetic */ boolean a;

    private static class a<E> extends AbstractList<E> implements Serializable, List<E>, RandomAccess {
        private final E[] a;

        a(E[] r2_EA) {
            if (r2_EA == null) {
                throw new NullPointerException();
            } else {
                this.a = r2_EA;
            }
        }

        public boolean contains(Object r7_Object) {
            Object[] r3_ObjectA;
            int r4i;
            int r2i;
            if (r7_Object != null) {
                r3_ObjectA = this.a;
                r4i = r3_ObjectA.length;
                r2i = 0;
                while (r2i < r4i) {
                    if (r7_Object.equals(r3_ObjectA[r2i])) {
                        return true;
                    }
                    r2i++;
                }
            } else {
                r3_ObjectA = this.a;
                r4i = r3_ObjectA.length;
                r2i = 0;
                while (r2i < r4i) {
                    if (r3_ObjectA[r2i] == null) {
                        return true;
                    }
                    r2i++;
                }
            }
            return false;
        }

        public E get(int r2i) {
            try {
                return this.a[r2i];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw e;
            }
        }

        public int indexOf(Object r3_Object) {
            int r0i = 0;
            if (r3_Object != null) {
                while (r0i < this.a.length) {
                    if (r3_Object.equals(this.a[r0i])) {
                        return r0i;
                    }
                    r0i++;
                }
                return -1;
            }
            while (r0i < this.a.length) {
                if (this.a[r0i] == null) {
                    return r0i;
                }
                r0i++;
            }
            return -1;
        }

        public int lastIndexOf(Object r3_Object) {
            int r0i;
            if (r3_Object != null) {
                r0i = this.a.length - 1;
                while (r0i >= 0) {
                    if (r3_Object.equals(this.a[r0i])) {
                        return r0i;
                    }
                    r0i--;
                }
            } else {
                r0i = this.a.length - 1;
                while (r0i >= 0) {
                    if (this.a[r0i] == null) {
                        return r0i;
                    }
                    r0i--;
                }
            }
            return -1;
        }

        public E set(int r3i, E r4_E) {
            E r0_E = this.a[r3i];
            this.a[r3i] = r4_E;
            return r0_E;
        }

        public int size() {
            return this.a.length;
        }

        public Object[] toArray() {
            return (Object[]) this.a.clone();
        }

        public <T> T[] toArray(T[] r5_TA) {
            T[] r0_TA;
            int r1i = size();
            r0_TA = r1i > r5_TA.length ? (Object[]) Array.newInstance(r5_TA.getClass().getComponentType(), r1i) : r5_TA;
            System.arraycopy(this.a, 0, r0_TA, 0, r1i);
            if (r1i < r0_TA.length) {
                r0_TA[r1i] = null;
            }
            return r0_TA;
        }
    }

    static {
        a = !Arrays.class.desiredAssertionStatus();
    }

    private Arrays() {
    }

    private static int a(Object r2_Object) {
        if (r2_Object == null) {
            return 0;
        }
        Class r0_Class = r2_Object.getClass().getComponentType();
        if (r0_Class == null) {
            return r2_Object.hashCode();
        }
        if (!r0_Class.isPrimitive()) {
            return deepHashCode((Object[]) r2_Object);
        }
        if (r0_Class.equals(Integer.TYPE)) {
            return hashCode((int[]) r2_Object);
        }
        if (r0_Class.equals(Character.TYPE)) {
            return hashCode((char[]) r2_Object);
        }
        if (r0_Class.equals(Boolean.TYPE)) {
            return hashCode((boolean[]) r2_Object);
        }
        if (r0_Class.equals(Byte.TYPE)) {
            return hashCode((byte[]) r2_Object);
        }
        if (r0_Class.equals(Long.TYPE)) {
            return hashCode((long[]) r2_Object);
        }
        if (r0_Class.equals(Float.TYPE)) {
            return hashCode((float[]) r2_Object);
        }
        if (r0_Class.equals(Double.TYPE)) {
            return hashCode((double[]) r2_Object);
        }
        return hashCode((short[]) r2_Object);
    }

    private static void a(int r1i, int r2i, int r3i) {
        if (r1i > r2i) {
            throw new IllegalArgumentException();
        } else if (r1i < 0 || r2i > r3i) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private static void a(Object[] r5_ObjectA, Object[] r6_ObjectA, StringBuilder r7_StringBuilder) {
        if (r5_ObjectA == null) {
            r7_StringBuilder.append("null");
        } else {
            r7_StringBuilder.append('[');
            int r1i = 0;
            while (r1i < r5_ObjectA.length) {
                if (r1i != 0) {
                    r7_StringBuilder.append(", ");
                }
                Object r0_Object = r5_ObjectA[r1i];
                if (r0_Object == null) {
                    r7_StringBuilder.append("null");
                } else {
                    Class r3_Class = r0_Object.getClass();
                    if (r3_Class.isArray()) {
                        r3_Class = r3_Class.getComponentType();
                        if (r3_Class.isPrimitive()) {
                            if (Boolean.TYPE.equals(r3_Class)) {
                                r7_StringBuilder.append(toString((boolean[]) r0_Object));
                            } else if (Byte.TYPE.equals(r3_Class)) {
                                r7_StringBuilder.append(toString((byte[]) r0_Object));
                            } else if (Character.TYPE.equals(r3_Class)) {
                                r7_StringBuilder.append(toString((char[]) r0_Object));
                            } else if (Double.TYPE.equals(r3_Class)) {
                                r7_StringBuilder.append(toString((double[]) r0_Object));
                            } else if (Float.TYPE.equals(r3_Class)) {
                                r7_StringBuilder.append(toString((float[]) r0_Object));
                            } else if (Integer.TYPE.equals(r3_Class)) {
                                r7_StringBuilder.append(toString((int[]) r0_Object));
                            } else if (Long.TYPE.equals(r3_Class)) {
                                r7_StringBuilder.append(toString((long[]) r0_Object));
                            } else if (Short.TYPE.equals(r3_Class)) {
                                r7_StringBuilder.append(toString((short[]) r0_Object));
                            } else {
                                throw new AssertionError();
                            }
                        } else if (a || r0_Object instanceof Object[]) {
                            if (a(r6_ObjectA, r0_Object)) {
                                r7_StringBuilder.append("[...]");
                            } else {
                                Object[] r0_ObjectA = (Object[]) r0_Object;
                                Object[] r3_ObjectA = new Object[(r6_ObjectA.length + 1)];
                                System.arraycopy(r6_ObjectA, 0, r3_ObjectA, 0, r6_ObjectA.length);
                                r3_ObjectA[r6_ObjectA.length] = r0_ObjectA;
                                a(r0_ObjectA, r3_ObjectA, r7_StringBuilder);
                            }
                        } else {
                            throw new AssertionError();
                        }
                    } else {
                        r7_StringBuilder.append(r5_ObjectA[r1i]);
                    }
                }
                r1i++;
            }
            r7_StringBuilder.append(']');
        }
    }

    private static boolean a(Object r3_Object, Object r4_Object) {
        if (r3_Object == r4_Object) {
            return true;
        }
        if (r3_Object == null || r4_Object == null) {
            return false;
        }
        Class r1_Class = r3_Object.getClass().getComponentType();
        if (r1_Class != r4_Object.getClass().getComponentType()) {
            return false;
        }
        if (r1_Class == null) {
            return r3_Object.equals(r4_Object);
        }
        if (!r1_Class.isPrimitive()) {
            return deepEquals((Object[]) r3_Object, (Object[]) r4_Object);
        }
        if (r1_Class.equals(Integer.TYPE)) {
            return equals((int[]) r3_Object, (int[]) r4_Object);
        }
        if (r1_Class.equals(Character.TYPE)) {
            return equals((char[]) r3_Object, (char[]) r4_Object);
        }
        if (r1_Class.equals(Boolean.TYPE)) {
            return equals((boolean[]) r3_Object, (boolean[]) r4_Object);
        }
        if (r1_Class.equals(Byte.TYPE)) {
            return equals((byte[]) r3_Object, (byte[]) r4_Object);
        }
        if (r1_Class.equals(Long.TYPE)) {
            return equals((long[]) r3_Object, (long[]) r4_Object);
        }
        if (r1_Class.equals(Float.TYPE)) {
            return equals((float[]) r3_Object, (float[]) r4_Object);
        }
        if (r1_Class.equals(Double.TYPE)) {
            return equals((double[]) r3_Object, (double[]) r4_Object);
        }
        return equals((short[]) r3_Object, (short[]) r4_Object);
    }

    private static boolean a(Object[] r4_ObjectA, Object r5_Object) {
        if (r4_ObjectA == null || r4_ObjectA.length == 0) {
            return false;
        }
        int r2i = r4_ObjectA.length;
        int r1i = 0;
        while (r1i < r2i) {
            if (r4_ObjectA[r1i] == r5_Object) {
                return true;
            }
            r1i++;
        }
        return false;
    }

    public static <T> List<T> asList(T ... r1_TA) {
        return new a(r1_TA);
    }

    public static int binarySearch(byte[] r2_byteA, byte r3b) {
        return binarySearch(r2_byteA, 0, r2_byteA.length, r3b);
    }

    public static int binarySearch(byte[] r4_byteA, int r5i, int r6i, byte r7b) {
        a(r5i, r6i, r4_byteA.length);
        int r0i = r6i - 1;
        int r1i = r5i;
        while (r1i <= r0i) {
            int r2i = (r1i + r0i) >>> 1;
            byte r3b = r4_byteA[r2i];
            if (r3b < r7b) {
                r1i = r2i + 1;
            } else {
                if (r3b <= r7b) {
                    return r2i;
                }
                r0i = r2i - 1;
            }
        }
        return r1i ^ -1;
    }

    public static int binarySearch(char[] r2_charA, char r3c) {
        return binarySearch(r2_charA, 0, r2_charA.length, r3c);
    }

    public static int binarySearch(char[] r4_charA, int r5i, int r6i, char r7c) {
        a(r5i, r6i, r4_charA.length);
        int r0i = r6i - 1;
        int r1i = r5i;
        while (r1i <= r0i) {
            int r2i = (r1i + r0i) >>> 1;
            char r3c = r4_charA[r2i];
            if (r3c < r7c) {
                r1i = r2i + 1;
            } else {
                if (r3c <= r7c) {
                    return r2i;
                }
                r0i = r2i - 1;
            }
        }
        return r1i ^ -1;
    }

    public static int binarySearch(double[] r2_doubleA, double r3d) {
        return binarySearch(r2_doubleA, 0, r2_doubleA.length, r3d);
    }

    public static int binarySearch(double[] r8_doubleA, int r9i, int r10i, double r11d) {
        a(r9i, r10i, r8_doubleA.length);
        int r0i = r10i - 1;
        int r1i = r9i;
        while (r1i <= r0i) {
            int r2i = (r1i + r0i) >>> 1;
            double r3d = r8_doubleA[r2i];
            if (r3d < r11d) {
                r1i = r2i + 1;
            } else if (r3d > r11d) {
                r0i = r2i - 1;
            } else {
                if (r3d != 0.0d && r3d == r11d) {
                    return r2i;
                }
                long r3j = Double.doubleToLongBits(r3d);
                long r5j = Double.doubleToLongBits(r11d);
                if (r3j < r5j) {
                    r1i = r2i + 1;
                } else {
                    if (r3j <= r5j) {
                        return r2i;
                    }
                    r0i = r2i - 1;
                }
            }
        }
        return r1i ^ -1;
    }

    public static int binarySearch(float[] r2_floatA, float r3f) {
        return binarySearch(r2_floatA, 0, r2_floatA.length, r3f);
    }

    public static int binarySearch(float[] r5_floatA, int r6i, int r7i, float r8f) {
        a(r6i, r7i, r5_floatA.length);
        int r0i = r7i - 1;
        int r1i = r6i;
        while (r1i <= r0i) {
            int r2i = (r1i + r0i) >>> 1;
            float r3f = r5_floatA[r2i];
            if (r3f < r8f) {
                r1i = r2i + 1;
            } else if (r3f > r8f) {
                r0i = r2i - 1;
            } else {
                if (r3f != 0.0f && r3f == r8f) {
                    return r2i;
                }
                int r3i = Float.floatToIntBits(r3f);
                int r4i = Float.floatToIntBits(r8f);
                if (r3i < r4i) {
                    r1i = r2i + 1;
                } else {
                    if (r3i <= r4i) {
                        return r2i;
                    }
                    r0i = r2i - 1;
                }
            }
        }
        return r1i ^ -1;
    }

    public static int binarySearch(int[] r2_intA, int r3i) {
        return binarySearch(r2_intA, 0, r2_intA.length, r3i);
    }

    public static int binarySearch(int[] r4_intA, int r5i, int r6i, int r7i) {
        a(r5i, r6i, r4_intA.length);
        int r0i = r6i - 1;
        int r1i = r5i;
        while (r1i <= r0i) {
            int r2i = (r1i + r0i) >>> 1;
            int r3i = r4_intA[r2i];
            if (r3i < r7i) {
                r1i = r2i + 1;
            } else {
                if (r3i <= r7i) {
                    return r2i;
                }
                r0i = r2i - 1;
            }
        }
        return r1i ^ -1;
    }

    public static int binarySearch(long[] r6_longA, int r7i, int r8i, long r9j) {
        a(r7i, r8i, r6_longA.length);
        int r0i = r8i - 1;
        int r1i = r7i;
        while (r1i <= r0i) {
            int r2i = (r1i + r0i) >>> 1;
            long r3j = r6_longA[r2i];
            if (r3j < r9j) {
                r1i = r2i + 1;
            } else {
                if (r3j <= r9j) {
                    return r2i;
                }
                r0i = r2i - 1;
            }
        }
        return r1i ^ -1;
    }

    public static int binarySearch(long[] r2_longA, long r3j) {
        return binarySearch(r2_longA, 0, r2_longA.length, r3j);
    }

    public static int binarySearch(Object[] r5_ObjectA, int r6i, int r7i, Object r8_Object) {
        a(r6i, r7i, r5_ObjectA.length);
        int r1i = r7i - 1;
        int r2i = r6i;
        while (r2i <= r1i) {
            int r3i = (r2i + r1i) >>> 1;
            int r0i = ((Comparable) r5_ObjectA[r3i]).compareTo(r8_Object);
            if (r0i < 0) {
                r1i = r3i + 1;
                r0i = r1i;
            } else {
                if (r0i <= 0) {
                    return r3i;
                }
                r0i = r3i - 1;
                r1i = r2i;
            }
            r2i = r1i;
            r1i = r0i;
        }
        return r2i ^ -1;
    }

    public static <T> int binarySearch(T[] r4_TA, int r5i, int r6i, T r7_T, Comparator<? super T> r8_Comparator__super_T) {
        if (r8_Comparator__super_T == null) {
            return binarySearch((Object[])r4_TA, r5i, r6i, (Object)r7_T);
        }
        a(r5i, r6i, r4_TA.length);
        int r0i = r6i - 1;
        int r1i = r5i;
        while (r1i <= r0i) {
            int r2i = (r1i + r0i) >>> 1;
            int r3i = r8_Comparator__super_T.compare(r4_TA[r2i], r7_T);
            if (r3i < 0) {
                r1i = r2i + 1;
            } else {
                if (r3i <= 0) {
                    return r2i;
                }
                r0i = r2i - 1;
            }
        }
        return r1i ^ -1;
    }

    public static int binarySearch(Object[] r2_ObjectA, Object r3_Object) {
        return binarySearch(r2_ObjectA, 0, r2_ObjectA.length, r3_Object);
    }

    public static <T> int binarySearch(T[] r2_TA, T r3_T, Comparator<? super T> r4_Comparator__super_T) {
        return binarySearch(r2_TA, 0, r2_TA.length, r3_T, r4_Comparator__super_T);
    }

    public static int binarySearch(short[] r4_shortA, int r5i, int r6i, short r7s) {
        a(r5i, r6i, r4_shortA.length);
        int r0i = r6i - 1;
        int r1i = r5i;
        while (r1i <= r0i) {
            int r2i = (r1i + r0i) >>> 1;
            short r3s = r4_shortA[r2i];
            if (r3s < r7s) {
                r1i = r2i + 1;
            } else {
                if (r3s <= r7s) {
                    return r2i;
                }
                r0i = r2i - 1;
            }
        }
        return r1i ^ -1;
    }

    public static int binarySearch(short[] r2_shortA, short r3s) {
        return binarySearch(r2_shortA, 0, r2_shortA.length, r3s);
    }

    public static byte[] copyOf(byte[] r1_byteA, int r2i) {
        if (r2i >= 0) {
            return copyOfRange(r1_byteA, 0, r2i);
        }
        throw new NegativeArraySizeException();
    }

    public static char[] copyOf(char[] r1_charA, int r2i) {
        if (r2i >= 0) {
            return copyOfRange(r1_charA, 0, r2i);
        }
        throw new NegativeArraySizeException();
    }

    public static double[] copyOf(double[] r1_doubleA, int r2i) {
        if (r2i >= 0) {
            return copyOfRange(r1_doubleA, 0, r2i);
        }
        throw new NegativeArraySizeException();
    }

    public static float[] copyOf(float[] r1_floatA, int r2i) {
        if (r2i >= 0) {
            return copyOfRange(r1_floatA, 0, r2i);
        }
        throw new NegativeArraySizeException();
    }

    public static int[] copyOf(int[] r1_intA, int r2i) {
        if (r2i >= 0) {
            return copyOfRange(r1_intA, 0, r2i);
        }
        throw new NegativeArraySizeException();
    }

    public static long[] copyOf(long[] r1_longA, int r2i) {
        if (r2i >= 0) {
            return copyOfRange(r1_longA, 0, r2i);
        }
        throw new NegativeArraySizeException();
    }

    public static <T> T[] copyOf(T[] r1_TA, int r2i) {
        if (r1_TA == null) {
            throw new NullPointerException();
        } else {
            if (r2i >= 0) {
                return copyOfRange((Object[])r1_TA, 0, r2i);
            }
            throw new NegativeArraySizeException();
        }
    }

    public static <T, U> T[] copyOf(U[] r1_UA, int r2i, Class<? extends T[]> r3_Class__extends_TA) {
        if (r2i >= 0) {
            return copyOfRange(r1_UA, 0, r2i, r3_Class__extends_TA);
        }
        throw new NegativeArraySizeException();
    }

    public static short[] copyOf(short[] r1_shortA, int r2i) {
        if (r2i >= 0) {
            return copyOfRange(r1_shortA, 0, r2i);
        }
        throw new NegativeArraySizeException();
    }

    public static boolean[] copyOf(boolean[] r1_booleanA, int r2i) {
        if (r2i >= 0) {
            return copyOfRange(r1_booleanA, 0, r2i);
        }
        throw new NegativeArraySizeException();
    }

    public static byte[] copyOfRange(byte[] r3_byteA, int r4i, int r5i) {
        if (r4i > r5i) {
            throw new IllegalArgumentException();
        } else {
            int r0i = r3_byteA.length;
            if (r4i < 0 || r4i > r0i) {
                throw new ArrayIndexOutOfBoundsException();
            } else {
                int r1i = r5i - r4i;
                Object r1_Object = new Object[r1i];
                System.arraycopy(r3_byteA, r4i, r1_Object, 0, Math.min(r1i, r0i - r4i));
                return r1_Object;
            }
        }
    }

    public static char[] copyOfRange(char[] r3_charA, int r4i, int r5i) {
        if (r4i > r5i) {
            throw new IllegalArgumentException();
        } else {
            int r0i = r3_charA.length;
            if (r4i < 0 || r4i > r0i) {
                throw new ArrayIndexOutOfBoundsException();
            } else {
                int r1i = r5i - r4i;
                Object r1_Object = new Object[r1i];
                System.arraycopy(r3_charA, r4i, r1_Object, 0, Math.min(r1i, r0i - r4i));
                return r1_Object;
            }
        }
    }

    public static double[] copyOfRange(double[] r3_doubleA, int r4i, int r5i) {
        if (r4i > r5i) {
            throw new IllegalArgumentException();
        } else {
            int r0i = r3_doubleA.length;
            if (r4i < 0 || r4i > r0i) {
                throw new ArrayIndexOutOfBoundsException();
            } else {
                int r1i = r5i - r4i;
                Object r1_Object = new Object[r1i];
                System.arraycopy(r3_doubleA, r4i, r1_Object, 0, Math.min(r1i, r0i - r4i));
                return r1_Object;
            }
        }
    }

    public static float[] copyOfRange(float[] r3_floatA, int r4i, int r5i) {
        if (r4i > r5i) {
            throw new IllegalArgumentException();
        } else {
            int r0i = r3_floatA.length;
            if (r4i < 0 || r4i > r0i) {
                throw new ArrayIndexOutOfBoundsException();
            } else {
                int r1i = r5i - r4i;
                Object r1_Object = new Object[r1i];
                System.arraycopy(r3_floatA, r4i, r1_Object, 0, Math.min(r1i, r0i - r4i));
                return r1_Object;
            }
        }
    }

    public static int[] copyOfRange(int[] r3_intA, int r4i, int r5i) {
        if (r4i > r5i) {
            throw new IllegalArgumentException();
        } else {
            int r0i = r3_intA.length;
            if (r4i < 0 || r4i > r0i) {
                throw new ArrayIndexOutOfBoundsException();
            } else {
                int r1i = r5i - r4i;
                Object r1_Object = new Object[r1i];
                System.arraycopy(r3_intA, r4i, r1_Object, 0, Math.min(r1i, r0i - r4i));
                return r1_Object;
            }
        }
    }

    public static long[] copyOfRange(long[] r3_longA, int r4i, int r5i) {
        if (r4i > r5i) {
            throw new IllegalArgumentException();
        } else {
            int r0i = r3_longA.length;
            if (r4i < 0 || r4i > r0i) {
                throw new ArrayIndexOutOfBoundsException();
            } else {
                int r1i = r5i - r4i;
                Object r1_Object = new Object[r1i];
                System.arraycopy(r3_longA, r4i, r1_Object, 0, Math.min(r1i, r0i - r4i));
                return r1_Object;
            }
        }
    }

    public static <T> T[] copyOfRange(T[] r3_TA, int r4i, int r5i) {
        int r0i = r3_TA.length;
        if (r4i > r5i) {
            throw new IllegalArgumentException();
        } else if (r4i < 0 || r4i > r0i) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            int r1i = r5i - r4i;
            Object[] r0_ObjectA = (Object[]) Array.newInstance(r3_TA.getClass().getComponentType(), r1i);
            System.arraycopy(r3_TA, r4i, r0_ObjectA, 0, Math.min(r1i, r0i - r4i));
            return r0_ObjectA;
        }
    }

    public static <T, U> T[] copyOfRange(U[] r3_UA, int r4i, int r5i, Class<? extends T[]> r6_Class__extends_TA) {
        if (r4i > r5i) {
            throw new IllegalArgumentException();
        } else {
            int r0i = r3_UA.length;
            if (r4i < 0 || r4i > r0i) {
                throw new ArrayIndexOutOfBoundsException();
            } else {
                int r1i = r5i - r4i;
                Object[] r0_ObjectA = (Object[]) Array.newInstance(r6_Class__extends_TA.getComponentType(), r1i);
                System.arraycopy(r3_UA, r4i, r0_ObjectA, 0, Math.min(r1i, r0i - r4i));
                return r0_ObjectA;
            }
        }
    }

    public static short[] copyOfRange(short[] r3_shortA, int r4i, int r5i) {
        if (r4i > r5i) {
            throw new IllegalArgumentException();
        } else {
            int r0i = r3_shortA.length;
            if (r4i < 0 || r4i > r0i) {
                throw new ArrayIndexOutOfBoundsException();
            } else {
                int r1i = r5i - r4i;
                Object r1_Object = new Object[r1i];
                System.arraycopy(r3_shortA, r4i, r1_Object, 0, Math.min(r1i, r0i - r4i));
                return r1_Object;
            }
        }
    }

    public static boolean[] copyOfRange(boolean[] r3_booleanA, int r4i, int r5i) {
        if (r4i > r5i) {
            throw new IllegalArgumentException();
        } else {
            int r0i = r3_booleanA.length;
            if (r4i < 0 || r4i > r0i) {
                throw new ArrayIndexOutOfBoundsException();
            } else {
                int r1i = r5i - r4i;
                Object r1_Object = new Object[r1i];
                System.arraycopy(r3_booleanA, r4i, r1_Object, 0, Math.min(r1i, r0i - r4i));
                return r1_Object;
            }
        }
    }

    public static boolean deepEquals(Object[] r5_ObjectA, Object[] r6_ObjectA) {
        if (r5_ObjectA == r6_ObjectA) {
            return true;
        }
        if (r5_ObjectA == null || r6_ObjectA == null || r5_ObjectA.length != r6_ObjectA.length) {
            return false;
        }
        int r0i = 0;
        while (r0i < r5_ObjectA.length) {
            if (!a(r5_ObjectA[r0i], r6_ObjectA[r0i])) {
                return false;
            }
            r0i++;
        }
        return true;
    }

    public static int deepHashCode(Object[] r5_ObjectA) {
        int r0i = 0;
        if (r5_ObjectA == null) {
            return r0i;
        }
        r0i = 1;
        int r1i = r0i;
        while (r1i < r5_ObjectA.length) {
            r1i++;
            r0i = a(r5_ObjectA[r1i]) + r0i * 31;
        }
        return r0i;
    }

    public static String deepToString(Object[] r3_ObjectA) {
        if (r3_ObjectA == null) {
            return "null";
        }
        StringBuilder r0_StringBuilder = new StringBuilder(r3_ObjectA.length * 9);
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = r3_ObjectA;
        a(r3_ObjectA, r1_ObjectA, r0_StringBuilder);
        return r0_StringBuilder.toString();
    }

    public static boolean equals(byte[] r5_byteA, byte[] r6_byteA) {
        if (r5_byteA == r6_byteA) {
            return true;
        }
        if (r5_byteA == null || r6_byteA == null || r5_byteA.length != r6_byteA.length) {
            return false;
        }
        int r0i = 0;
        while (r0i < r5_byteA.length) {
            if (r5_byteA[r0i] != r6_byteA[r0i]) {
                return false;
            }
            r0i++;
        }
        return true;
    }

    public static boolean equals(char[] r5_charA, char[] r6_charA) {
        if (r5_charA == r6_charA) {
            return true;
        }
        if (r5_charA == null || r6_charA == null || r5_charA.length != r6_charA.length) {
            return false;
        }
        int r0i = 0;
        while (r0i < r5_charA.length) {
            if (r5_charA[r0i] != r6_charA[r0i]) {
                return false;
            }
            r0i++;
        }
        return true;
    }

    public static boolean equals(double[] r7_doubleA, double[] r8_doubleA) {
        if (r7_doubleA == r8_doubleA) {
            return true;
        }
        if (r7_doubleA == null || r8_doubleA == null || r7_doubleA.length != r8_doubleA.length) {
            return false;
        }
        int r0i = 0;
        while (r0i < r7_doubleA.length) {
            if (Double.doubleToLongBits(r7_doubleA[r0i]) != Double.doubleToLongBits(r8_doubleA[r0i])) {
                return false;
            }
            r0i++;
        }
        return true;
    }

    public static boolean equals(float[] r5_floatA, float[] r6_floatA) {
        if (r5_floatA == r6_floatA) {
            return true;
        }
        if (r5_floatA == null || r6_floatA == null || r5_floatA.length != r6_floatA.length) {
            return false;
        }
        int r0i = 0;
        while (r0i < r5_floatA.length) {
            if (Float.floatToIntBits(r5_floatA[r0i]) != Float.floatToIntBits(r6_floatA[r0i])) {
                return false;
            }
            r0i++;
        }
        return true;
    }

    public static boolean equals(int[] r5_intA, int[] r6_intA) {
        if (r5_intA == r6_intA) {
            return true;
        }
        if (r5_intA == null || r6_intA == null || r5_intA.length != r6_intA.length) {
            return false;
        }
        int r0i = 0;
        while (r0i < r5_intA.length) {
            if (r5_intA[r0i] != r6_intA[r0i]) {
                return false;
            }
            r0i++;
        }
        return true;
    }

    public static boolean equals(long[] r7_longA, long[] r8_longA) {
        if (r7_longA == r8_longA) {
            return true;
        }
        if (r7_longA == null || r8_longA == null || r7_longA.length != r8_longA.length) {
            return false;
        }
        int r0i = 0;
        while (r0i < r7_longA.length) {
            if (r7_longA[r0i] != r8_longA[r0i]) {
                return false;
            }
            r0i++;
        }
        return true;
    }

    public static boolean equals(Object[] r5_ObjectA, Object[] r6_ObjectA) {
        if (r5_ObjectA == r6_ObjectA) {
            return true;
        }
        if (r5_ObjectA == null || r6_ObjectA == null || r5_ObjectA.length != r6_ObjectA.length) {
            return false;
        }
        int r0i = 0;
        while (r0i < r5_ObjectA.length) {
            Object r3_Object = r5_ObjectA[r0i];
            Object r4_Object = r6_ObjectA[r0i];
            if (r3_Object == null) {
                if (r4_Object != null) {
                    return false;
                }
                r0i++;
            } else if (!r3_Object.equals(r4_Object)) {
                return false;
            }
            r0i++;
        }
        return true;
    }

    public static boolean equals(short[] r5_shortA, short[] r6_shortA) {
        if (r5_shortA == r6_shortA) {
            return true;
        }
        if (r5_shortA == null || r6_shortA == null || r5_shortA.length != r6_shortA.length) {
            return false;
        }
        int r0i = 0;
        while (r0i < r5_shortA.length) {
            if (r5_shortA[r0i] != r6_shortA[r0i]) {
                return false;
            }
            r0i++;
        }
        return true;
    }

    public static boolean equals(boolean[] r5_booleanA, boolean[] r6_booleanA) {
        if (r5_booleanA == r6_booleanA) {
            return true;
        }
        if (r5_booleanA == null || r6_booleanA == null || r5_booleanA.length != r6_booleanA.length) {
            return false;
        }
        int r0i = 0;
        while (r0i < r5_booleanA.length) {
            if (r5_booleanA[r0i] != r6_booleanA[r0i]) {
                return false;
            }
            r0i++;
        }
        return true;
    }

    public static void fill(byte[] r2_byteA, byte r3b) {
        int r0i = 0;
        while (r0i < r2_byteA.length) {
            r2_byteA[r0i] = r3b;
            r0i++;
        }
    }

    public static void fill(int[] r2_intA, int r3i) {
        int r0i = 0;
        while (r0i < r2_intA.length) {
            r2_intA[r0i] = r3i;
            r0i++;
        }
    }

    public static void fill(Object[] r2_ObjectA, Object r3_Object) {
        int r0i = 0;
        while (r0i < r2_ObjectA.length) {
            r2_ObjectA[r0i] = r3_Object;
            r0i++;
        }
    }

    public static void fill(boolean[] r2_booleanA, boolean r3z) {
        int r0i = 0;
        while (r0i < r2_booleanA.length) {
            r2_booleanA[r0i] = r3z;
            r0i++;
        }
    }

    public static int hashCode(byte[] r5_byteA) {
        int r0i = 0;
        if (r5_byteA == null) {
            return r0i;
        }
        r0i = 1;
        int r1i = r0i;
        while (r1i < r5_byteA.length) {
            r1i++;
            r0i = r5_byteA[r1i] + r0i * 31;
        }
        return r0i;
    }

    public static int hashCode(char[] r5_charA) {
        int r0i = 0;
        if (r5_charA == null) {
            return r0i;
        }
        r0i = 1;
        int r1i = r0i;
        while (r1i < r5_charA.length) {
            r1i++;
            r0i = r5_charA[r1i] + r0i * 31;
        }
        return r0i;
    }

    public static int hashCode(double[] r9_doubleA) {
        int r0i = 0;
        if (r9_doubleA == null) {
            return r0i;
        }
        int r3i = r9_doubleA.length;
        r0i = 1;
        int r1i = r0i;
        while (r1i < r3i) {
            long r4j = Double.doubleToLongBits(r9_doubleA[r1i]);
            r1i++;
            r0i = ((int) (r4j ^ (r4j >>> 32))) + r0i * 31;
        }
        return r0i;
    }

    public static int hashCode(float[] r5_floatA) {
        int r0i = 0;
        if (r5_floatA == null) {
            return r0i;
        }
        r0i = 1;
        int r1i = r0i;
        while (r1i < r5_floatA.length) {
            r1i++;
            r0i = Float.floatToIntBits(r5_floatA[r1i]) + r0i * 31;
        }
        return r0i;
    }

    public static int hashCode(int[] r5_intA) {
        int r0i = 0;
        if (r5_intA == null) {
            return r0i;
        }
        r0i = 1;
        int r1i = r0i;
        while (r1i < r5_intA.length) {
            r1i++;
            r0i = r5_intA[r1i] + r0i * 31;
        }
        return r0i;
    }

    public static int hashCode(long[] r9_longA) {
        int r0i = 0;
        if (r9_longA == null) {
            return r0i;
        }
        int r3i = r9_longA.length;
        r0i = 1;
        int r1i = r0i;
        while (r1i < r3i) {
            long r4j = r9_longA[r1i];
            r1i++;
            r0i = ((int) (r4j ^ (r4j >>> 32))) + r0i * 31;
        }
        return r0i;
    }

    public static int hashCode(Object[] r5_ObjectA) {
        if (r5_ObjectA == null) {
            return 0;
        }
        int r2i = 1;
        int r4i = r5_ObjectA.length;
        int r3i = 0;
        while (r3i < r4i) {
            Object r0_Object = r5_ObjectA[r3i];
            r2i = r2i * 31 + (r0_Object == null ? 0 : r0_Object.hashCode());
            r3i++;
        }
        return r2i;
    }

    public static int hashCode(short[] r5_shortA) {
        int r0i = 0;
        if (r5_shortA == null) {
            return r0i;
        }
        r0i = 1;
        int r1i = r0i;
        while (r1i < r5_shortA.length) {
            r1i++;
            r0i = r5_shortA[r1i] + r0i * 31;
        }
        return r0i;
    }

    public static int hashCode(boolean[] r6_booleanA) {
        int r0i = 0;
        if (r6_booleanA == null) {
            return r0i;
        }
        r0i = 1;
        int r1i = r0i;
        while (r1i < r6_booleanA.length) {
            r1i++;
            r0i = r0i * 31 + (r6_booleanA[r1i] ? 1231 : 1237);
        }
        return r0i;
    }

    public static String toString(byte[] r3_byteA) {
        if (r3_byteA == null) {
            return "null";
        }
        if (r3_byteA.length == 0) {
            return "[]";
        }
        StringBuilder r1_StringBuilder = new StringBuilder(r3_byteA.length * 6);
        r1_StringBuilder.append('[');
        r1_StringBuilder.append(r3_byteA[0]);
        int r0i = 1;
        while (r0i < r3_byteA.length) {
            r1_StringBuilder.append(", ");
            r1_StringBuilder.append(r3_byteA[r0i]);
            r0i++;
        }
        r1_StringBuilder.append(']');
        return r1_StringBuilder.toString();
    }

    public static String toString(char[] r3_charA) {
        if (r3_charA == null) {
            return "null";
        }
        if (r3_charA.length == 0) {
            return "[]";
        }
        StringBuilder r1_StringBuilder = new StringBuilder(r3_charA.length * 3);
        r1_StringBuilder.append('[');
        r1_StringBuilder.append(r3_charA[0]);
        int r0i = 1;
        while (r0i < r3_charA.length) {
            r1_StringBuilder.append(", ");
            r1_StringBuilder.append(r3_charA[r0i]);
            r0i++;
        }
        r1_StringBuilder.append(']');
        return r1_StringBuilder.toString();
    }

    public static String toString(double[] r4_doubleA) {
        if (r4_doubleA == null) {
            return "null";
        }
        if (r4_doubleA.length == 0) {
            return "[]";
        }
        StringBuilder r1_StringBuilder = new StringBuilder(r4_doubleA.length * 7);
        r1_StringBuilder.append('[');
        r1_StringBuilder.append(r4_doubleA[0]);
        int r0i = 1;
        while (r0i < r4_doubleA.length) {
            r1_StringBuilder.append(", ");
            r1_StringBuilder.append(r4_doubleA[r0i]);
            r0i++;
        }
        r1_StringBuilder.append(']');
        return r1_StringBuilder.toString();
    }

    public static String toString(float[] r3_floatA) {
        if (r3_floatA == null) {
            return "null";
        }
        if (r3_floatA.length == 0) {
            return "[]";
        }
        StringBuilder r1_StringBuilder = new StringBuilder(r3_floatA.length * 7);
        r1_StringBuilder.append('[');
        r1_StringBuilder.append(r3_floatA[0]);
        int r0i = 1;
        while (r0i < r3_floatA.length) {
            r1_StringBuilder.append(", ");
            r1_StringBuilder.append(r3_floatA[r0i]);
            r0i++;
        }
        r1_StringBuilder.append(']');
        return r1_StringBuilder.toString();
    }

    public static String toString(int[] r3_intA) {
        if (r3_intA == null) {
            return "null";
        }
        if (r3_intA.length == 0) {
            return "[]";
        }
        StringBuilder r1_StringBuilder = new StringBuilder(r3_intA.length * 6);
        r1_StringBuilder.append('[');
        r1_StringBuilder.append(r3_intA[0]);
        int r0i = 1;
        while (r0i < r3_intA.length) {
            r1_StringBuilder.append(", ");
            r1_StringBuilder.append(r3_intA[r0i]);
            r0i++;
        }
        r1_StringBuilder.append(']');
        return r1_StringBuilder.toString();
    }

    public static String toString(long[] r4_longA) {
        if (r4_longA == null) {
            return "null";
        }
        if (r4_longA.length == 0) {
            return "[]";
        }
        StringBuilder r1_StringBuilder = new StringBuilder(r4_longA.length * 6);
        r1_StringBuilder.append('[');
        r1_StringBuilder.append(r4_longA[0]);
        int r0i = 1;
        while (r0i < r4_longA.length) {
            r1_StringBuilder.append(", ");
            r1_StringBuilder.append(r4_longA[r0i]);
            r0i++;
        }
        r1_StringBuilder.append(']');
        return r1_StringBuilder.toString();
    }

    public static String toString(Object[] r3_ObjectA) {
        if (r3_ObjectA == null) {
            return "null";
        }
        if (r3_ObjectA.length == 0) {
            return "[]";
        }
        StringBuilder r1_StringBuilder = new StringBuilder(r3_ObjectA.length * 7);
        r1_StringBuilder.append('[');
        r1_StringBuilder.append(r3_ObjectA[0]);
        int r0i = 1;
        while (r0i < r3_ObjectA.length) {
            r1_StringBuilder.append(", ");
            r1_StringBuilder.append(r3_ObjectA[r0i]);
            r0i++;
        }
        r1_StringBuilder.append(']');
        return r1_StringBuilder.toString();
    }

    public static String toString(short[] r3_shortA) {
        if (r3_shortA == null) {
            return "null";
        }
        if (r3_shortA.length == 0) {
            return "[]";
        }
        StringBuilder r1_StringBuilder = new StringBuilder(r3_shortA.length * 6);
        r1_StringBuilder.append('[');
        r1_StringBuilder.append(r3_shortA[0]);
        int r0i = 1;
        while (r0i < r3_shortA.length) {
            r1_StringBuilder.append(", ");
            r1_StringBuilder.append(r3_shortA[r0i]);
            r0i++;
        }
        r1_StringBuilder.append(']');
        return r1_StringBuilder.toString();
    }

    public static String toString(boolean[] r3_booleanA) {
        if (r3_booleanA == null) {
            return "null";
        }
        if (r3_booleanA.length == 0) {
            return "[]";
        }
        StringBuilder r1_StringBuilder = new StringBuilder(r3_booleanA.length * 7);
        r1_StringBuilder.append('[');
        r1_StringBuilder.append(r3_booleanA[0]);
        int r0i = 1;
        while (r0i < r3_booleanA.length) {
            r1_StringBuilder.append(", ");
            r1_StringBuilder.append(r3_booleanA[r0i]);
            r0i++;
        }
        r1_StringBuilder.append(']');
        return r1_StringBuilder.toString();
    }
}