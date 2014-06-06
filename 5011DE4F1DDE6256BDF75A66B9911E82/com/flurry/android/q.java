package com.flurry.android;

import java.io.DataInput;

// compiled from: SourceFile
final class q extends k {
    String a;
    byte b;
    byte c;
    o d;

    q() {
    }

    q(DataInput r2_DataInput) {
        this.a = r2_DataInput.readUTF();
        this.b = r2_DataInput.readByte();
        this.c = r2_DataInput.readByte();
    }

    public final String toString() {
        return "{name: " + this.a + ", blockId: " + this.b + ", themeId: " + this.c;
    }
}