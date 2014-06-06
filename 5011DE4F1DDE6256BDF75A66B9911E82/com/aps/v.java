package com.aps;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

final class v implements Serializable {
    protected int a;
    protected int b;
    protected short c;
    protected short d;
    protected int e;
    protected byte f;
    private byte g;

    v() {
        this.g = (byte) 4;
        this.a = 0;
        this.b = 0;
        this.c = (short) 0;
        this.d = (short) 0;
        this.e = 0;
        this.f = (byte) 0;
    }

    protected final Boolean a(DataOutputStream r3_DataOutputStream) {
        try {
            r3_DataOutputStream.writeByte(this.g);
            r3_DataOutputStream.writeInt(this.a);
            r3_DataOutputStream.writeInt(this.b);
            r3_DataOutputStream.writeShort(this.c);
            r3_DataOutputStream.writeShort(this.d);
            r3_DataOutputStream.writeInt(this.e);
            r3_DataOutputStream.writeByte(this.f);
            return Boolean.valueOf(true);
        } catch (IOException e) {
            return Boolean.valueOf(false);
        }
    }
}