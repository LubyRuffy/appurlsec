package com.aps;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

final class al implements Serializable {
    protected short a;
    protected int b;
    protected byte c;
    protected byte d;
    protected ArrayList e;
    private byte f;

    al() {
        this.f = (byte) 2;
        this.a = (short) 0;
        this.b = 0;
        this.c = (byte) 0;
        this.d = (byte) 0;
        this.e = new ArrayList();
    }

    protected final Boolean a(DataOutputStream r4_DataOutputStream) {
        try {
            r4_DataOutputStream.writeByte(this.f);
            r4_DataOutputStream.writeShort(this.a);
            r4_DataOutputStream.writeInt(this.b);
            r4_DataOutputStream.writeByte(this.c);
            r4_DataOutputStream.writeByte(this.d);
            byte r2b = (byte) 0;
            while (r2b < this.d) {
                r4_DataOutputStream.writeShort(((w) this.e.get(r2b)).a);
                r4_DataOutputStream.writeInt(((w) this.e.get(r2b)).b);
                r4_DataOutputStream.writeByte(((w) this.e.get(r2b)).c);
                r2b++;
            }
            return Boolean.valueOf(true);
        } catch (IOException e) {
            return Boolean.valueOf(false);
        }
    }
}