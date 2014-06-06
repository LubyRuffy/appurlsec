package com.androidquery.util;

import java.io.ByteArrayOutputStream;

public class PredefinedBAOS extends ByteArrayOutputStream {
    public PredefinedBAOS(int r1i) {
        super(r1i);
    }

    public byte[] toByteArray() {
        return this.count == this.buf.length ? this.buf : super.toByteArray();
    }
}