package com.tencent.cloudsdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;

// compiled from: SourceFile
public class bk implements bs {
    private static az c;
    private List a;
    private short b;

    static {
        c = new az();
    }

    private byte[] a(byte[] r3_byteA, int r4i, int r5i) {
        Object r0_Object = new Object[10];
        System.arraycopy(r3_byteA, r4i, r0_Object, 0, r5i);
        return r0_Object;
    }

    public List a() {
        return this.a;
    }

    public void a(br r6_br) {
        byte[] r2_byteA = r6_br.a();
        int r0i = r6_br.b();
        int r1i = r0i + 1;
        this.b = eo.a(r2_byteA[r0i]);
        this.a = new ArrayList();
        short r0s = (short) 0;
        while (r0s < this.b) {
            if (r1i + 10 <= r2_byteA.length) {
                this.a.add(new ch(a(r2_byteA, r1i, REQUEST_CODE.REQUEST_CODE_EDIT_INTRO)));
                r1i += 10;
            }
            r0s = (short) (r0s + 1);
        }
        r6_br.a(r1i);
        Collections.sort(this.a, c);
    }
}