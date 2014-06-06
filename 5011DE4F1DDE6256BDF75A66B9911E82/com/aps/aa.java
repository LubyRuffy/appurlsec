package com.aps;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class aa {
    protected File a;
    protected int[] b;
    private ArrayList c;
    private boolean d;

    protected aa(File r2_File, ArrayList r3_ArrayList, int[] r4_intA) {
        this.d = false;
        this.a = r2_File;
        this.c = r3_ArrayList;
        this.b = r4_intA;
    }

    protected final void a(boolean r1z) {
        this.d = r1z;
    }

    public byte[] a() {
        OutputStream r1_OutputStream = new ByteArrayOutputStream();
        DataOutputStream r2_DataOutputStream = new DataOutputStream(r1_OutputStream);
        Iterator r3_Iterator = this.c.iterator();
        while (r3_Iterator.hasNext()) {
            byte[] r0_byteA = (byte[]) r3_Iterator.next();
            try {
                r2_DataOutputStream.writeInt(r0_byteA.length);
                r2_DataOutputStream.write(r0_byteA);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            r1_OutputStream.close();
            r2_DataOutputStream.close();
        } catch (IOException e_2) {
            e_2.printStackTrace();
        }
        return r1_OutputStream.toByteArray();
    }

    protected final boolean b() {
        return this.d;
    }
}