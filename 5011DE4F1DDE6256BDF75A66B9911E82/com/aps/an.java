package com.aps;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

final class an implements Serializable {
    protected int a;
    protected int b;
    protected int c;
    protected int d;
    protected int e;
    protected short f;
    protected byte g;
    protected byte h;
    protected long i;
    protected long j;
    private byte k;

    an() {
        this.k = (byte) 1;
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = (short) 0;
        this.g = (byte) 0;
        this.h = (byte) 0;
        this.i = 0;
        this.j = 0;
    }

    protected final Boolean a(DataOutputStream r4_DataOutputStream) {
        Boolean r0_Boolean = Boolean.valueOf(false);
        if (r4_DataOutputStream == null) {
            return r0_Boolean;
        }
        try {
            r4_DataOutputStream.writeByte(this.k);
            r4_DataOutputStream.writeInt(this.a);
            r4_DataOutputStream.writeInt(this.b);
            r4_DataOutputStream.writeInt(this.c);
            r4_DataOutputStream.writeInt(this.d);
            r4_DataOutputStream.writeInt(this.e);
            r4_DataOutputStream.writeShort(this.f);
            r4_DataOutputStream.writeByte(this.g);
            r4_DataOutputStream.writeByte(this.h);
            r4_DataOutputStream.writeLong(this.i);
            r4_DataOutputStream.writeLong(this.j);
            return Boolean.valueOf(true);
        } catch (IOException e) {
            return r0_Boolean;
        }
    }
}