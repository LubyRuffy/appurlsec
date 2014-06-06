package com.flurry.android;

import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// compiled from: SourceFile
final class ab {
    final String a;
    ah b;
    long c;
    List d;
    private byte e;

    ab(String r4_String, byte r5b, long r6j) {
        this.d = new ArrayList();
        this.a = r4_String;
        this.e = r5b;
        this.d.add(new r((byte) 1, r6j));
    }

    final long a() {
        return ((r) this.d.get(0)).b;
    }

    final void a(r r2_r) {
        this.d.add(r2_r);
    }

    final void a(DataOutput r5_DataOutput) {
        r5_DataOutput.writeUTF(this.a);
        r5_DataOutput.writeByte(this.e);
        if (this.b == null) {
            r5_DataOutput.writeLong(0);
            r5_DataOutput.writeLong(0);
            r5_DataOutput.writeByte(0);
        } else {
            r5_DataOutput.writeLong(this.b.a);
            r5_DataOutput.writeLong(this.b.e);
            byte[] r0_byteA = this.b.g;
            r5_DataOutput.writeByte(r0_byteA.length);
            r5_DataOutput.write(r0_byteA);
        }
        r5_DataOutput.writeShort(this.d.size());
        Iterator r1_Iterator = this.d.iterator();
        while (r1_Iterator.hasNext()) {
            r r0_r = (r) r1_Iterator.next();
            r5_DataOutput.writeByte(r0_r.a);
            r5_DataOutput.writeLong(r0_r.b);
        }
    }

    public final String toString() {
        StringBuilder r1_StringBuilder = new StringBuilder();
        r1_StringBuilder.append("{hook: " + this.a + ", ad: " + this.b.d + ", transitions: [");
        Iterator r2_Iterator = this.d.iterator();
        while (r2_Iterator.hasNext()) {
            r1_StringBuilder.append((r) r2_Iterator.next());
            r1_StringBuilder.append(",");
        }
        r1_StringBuilder.append("]}");
        return r1_StringBuilder.toString();
    }
}