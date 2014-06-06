package com.flurry.android;

import java.io.DataInput;

// compiled from: SourceFile
public final class AdImage extends k {
    long a;
    int b;
    int c;
    String d;
    byte[] e;

    AdImage() {
    }

    AdImage(DataInput r1_DataInput) {
        a(r1_DataInput);
    }

    final void a(DataInput r3_DataInput) {
        this.a = r3_DataInput.readLong();
        this.b = r3_DataInput.readInt();
        this.c = r3_DataInput.readInt();
        this.d = r3_DataInput.readUTF();
        this.e = new byte[r3_DataInput.readInt()];
        r3_DataInput.readFully(this.e);
    }

    public final int getHeight() {
        return this.c;
    }

    public final long getId() {
        return this.a;
    }

    public final byte[] getImageData() {
        return this.e;
    }

    public final String getMimeType() {
        return this.d;
    }

    public final int getWidth() {
        return this.b;
    }
}