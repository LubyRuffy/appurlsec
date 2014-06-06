package com.aps;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

final class bb implements Serializable {
    protected byte a;
    protected ArrayList b;
    private byte c;

    bb() {
        this.c = (byte) 8;
        this.a = (byte) 0;
        this.b = new ArrayList();
    }

    protected final Boolean a(DataOutputStream r5_DataOutputStream) {
        try {
            r5_DataOutputStream.writeByte(this.c);
            r5_DataOutputStream.writeByte(this.a);
            byte r2b = (byte) 0;
            while (r2b < this.a) {
                bc r0_bc = (bc) this.b.get(r2b);
                r5_DataOutputStream.write(r0_bc.a);
                r5_DataOutputStream.writeShort(r0_bc.b);
                r5_DataOutputStream.write(ab.a(r0_bc.c, r0_bc.c.length));
                r2b++;
            }
            return Boolean.valueOf(true);
        } catch (IOException e) {
            return Boolean.valueOf(false);
        }
    }
}