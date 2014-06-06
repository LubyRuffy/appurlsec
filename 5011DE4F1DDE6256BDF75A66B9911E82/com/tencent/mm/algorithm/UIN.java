package com.tencent.mm.algorithm;

public class UIN extends Number {
    private int a;

    public UIN(int r2i) {
        this.a = 0;
        this.a = r2i;
    }

    public UIN(long r3j) {
        this.a = 0;
        this.a = (int) (-1 & r3j);
    }

    public static int valueOf(String r4_String) {
        try {
            return new UIN(Long.valueOf(r4_String).longValue()).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public double doubleValue() {
        return ((double) (((long) this.a) | 0)) + 0.0d;
    }

    public float floatValue() {
        return (float) (((double) (((long) this.a) | 0)) + 0.0d);
    }

    public int intValue() {
        return this.a;
    }

    public long longValue() {
        return ((long) this.a) & 4294967295L;
    }

    public String toString() {
        return String.valueOf(((long) this.a) & 4294967295L);
    }

    public int value() {
        return this.a;
    }
}