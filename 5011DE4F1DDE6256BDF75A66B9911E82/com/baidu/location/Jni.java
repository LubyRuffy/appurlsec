package com.baidu.location;

class Jni {
    private static int a;
    private static int b;
    private static int c;
    private static int d;
    private static int e;
    private static int f;
    private static int g;
    private static int h;
    private static boolean i;

    static {
        a = 0;
        b = 1;
        c = 2;
        d = 11;
        e = 12;
        f = 13;
        g = 14;
        h = 1024;
        i = false;
        try {
            System.loadLibrary("locSDK3");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            i = true;
        }
    }

    private static native String a(byte[] r1_byteA, int r2i);

    private static native String b(double r1d, double r3d, int r5i, int r6i);

    public static String if(String r7_String) {
        int r0i = 740;
        int r2i = 0;
        if (i) {
            return "err!";
        }
        byte[] r3_byteA = r7_String.getBytes();
        byte[] r4_byteA = new byte[h];
        int r1i = r3_byteA.length;
        if (r1i > 740) {
            r1i = 0;
            while (r2i < r0i) {
                if (r3_byteA[r2i] == 0) {
                    r4_byteA[r1i] = r3_byteA[r2i];
                    r1i++;
                } else {
                    j.if(f.v, "\\0 found in string");
                }
                r2i++;
            }
            j.if(f.v, "number:" + r3_byteA.length);
            return a(r4_byteA, 132456) + "|tp=3";
        } else {
            r0i = r1i;
            r1i = 0;
            while (r2i < r0i) {
                if (r3_byteA[r2i] == 0) {
                    j.if(f.v, "\\0 found in string");
                } else {
                    r4_byteA[r1i] = r3_byteA[r2i];
                    r1i++;
                }
                r2i++;
            }
            j.if(f.v, "number:" + r3_byteA.length);
            return a(r4_byteA, 132456) + "|tp=3";
        }
    }

    public static double[] if(double r7d, double r9d, String r11_String) {
        double[] r6_doubleA = new double[]{0.0d, 0.0d};
        if (i) {
            return r6_doubleA;
        }
        int r4i = -1;
        if (r11_String.equals("bd09")) {
            r4i = a;
        } else if (r11_String.equals("bd09ll")) {
            r4i = b;
        } else if (r11_String.equals("gcj02")) {
            r4i = c;
        } else if (r11_String.equals("gps2gcj")) {
            r4i = d;
        } else if (r11_String.equals("bd092gcj")) {
            r4i = e;
        } else if (r11_String.equals("bd09ll2gcj")) {
            r4i = f;
        }
        j.if(f.v, "type:" + r4i);
        try {
            String[] r0_StringA = b(r7d, r9d, r4i, 132456).split(":");
            r6_doubleA[0] = Double.parseDouble(r0_StringA[0]);
            r6_doubleA[1] = Double.parseDouble(r0_StringA[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r6_doubleA;
    }
}