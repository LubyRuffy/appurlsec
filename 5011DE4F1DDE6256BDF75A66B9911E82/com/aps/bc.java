package com.aps;

import java.io.Serializable;

final class bc implements Serializable {
    protected byte[] a;
    protected short b;
    protected byte[] c;

    bc() {
        this.a = new byte[16];
        this.b = (short) 0;
        this.c = new byte[32];
    }
}