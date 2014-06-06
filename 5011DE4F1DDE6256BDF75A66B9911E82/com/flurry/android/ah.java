package com.flurry.android;

import java.io.DataInput;

// compiled from: SourceFile
final class ah extends k {
    long a;
    long b;
    String c;
    String d;
    long e;
    Long f;
    byte[] g;
    AdImage h;

    ah() {
    }

    ah(DataInput r1_DataInput) {
        b(r1_DataInput);
    }

    private static String a(byte[] r4_byteA) {
        StringBuilder r1_StringBuilder = new StringBuilder();
        int r0i = 0;
        while (r0i < r4_byteA.length) {
            int r2i = (r4_byteA[r0i] >> 4) & 15;
            if (r2i < 10) {
                r1_StringBuilder.append((char) (r2i + 48));
            } else {
                r1_StringBuilder.append((char) (r2i + 65 - 10));
            }
            r2i = r4_byteA[r0i] & 15;
            if (r2i < 10) {
                r1_StringBuilder.append((char) (r2i + 48));
            } else {
                r1_StringBuilder.append((char) (r2i + 65 - 10));
            }
            r0i++;
        }
        return r1_StringBuilder.toString();
    }

    private void b(DataInput r3_DataInput) {
        this.a = r3_DataInput.readLong();
        this.b = r3_DataInput.readLong();
        this.d = r3_DataInput.readUTF();
        this.c = r3_DataInput.readUTF();
        this.e = r3_DataInput.readLong();
        this.f = Long.valueOf(r3_DataInput.readLong());
        this.g = new byte[r3_DataInput.readUnsignedByte()];
        r3_DataInput.readFully(this.g);
    }

    final void a(DataInput r1_DataInput) {
        b(r1_DataInput);
    }

    public final String toString() {
        return "ad {id=" + this.a + ", name='" + this.d + "', cookie: '" + a(this.g) + "'}";
    }
}