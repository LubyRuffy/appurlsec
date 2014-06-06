package com.tencent.cloudsdk;

// compiled from: SourceFile
public class cv {
    private static byte[] c;
    private static byte[] d;
    private String a;
    private int b;

    static {
        byte[] r0_byteA = new byte[12];
        r0_byteA[2] = (byte) 1;
        r0_byteA[5] = (byte) 1;
        c = r0_byteA;
        r0_byteA = new byte[5];
        r0_byteA[2] = (byte) 1;
        r0_byteA[4] = (byte) 1;
        d = r0_byteA;
    }

    public cv(String r2_String) {
        this.a = r2_String;
        this.b = cq.a().b();
    }

    private void a(byte[] r3_byteA) {
        r3_byteA[0] = (byte) ((this.b >>> 8) & 255);
        r3_byteA[1] = (byte) (this.b & 255);
    }

    public byte[] a() {
        if (this.a == null) {
            return null;
        }
        this.a = this.a.trim().toLowerCase();
        if (this.a.length() == 0) {
            return null;
        }
        Object r3_Object = new Object[(c.length + d.length + this.a.length() + 1)];
        String[] r4_StringA = this.a.split("\\.");
        int r2i = c.length;
        int r0i = 0;
        while (r0i < r4_StringA.length) {
            r3_Object[r2i] = (byte) r4_StringA[r0i].length();
            r2i++;
            Object r5_Object = r4_StringA[r0i].getBytes();
            System.arraycopy(r5_Object, 0, r3_Object, r2i, r5_Object.length);
            r2i += r5_Object.length;
            r0i++;
        }
        System.arraycopy(c, 0, r3_Object, 0, c.length);
        System.arraycopy(d, 0, r3_Object, r2i, d.length);
        a(r3_Object);
        return r3_Object;
    }

    public int b() {
        return this.b;
    }
}