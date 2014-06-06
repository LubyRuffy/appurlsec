package com.tencent.cloudsdk;

import qsbk.app.share.ShareUtils;

// compiled from: SourceFile
public class bg implements bi {
    private short a;
    private String b;
    private bf c;
    private byte[] d;

    public bg(short r2s, String r3_String, bf r4_bf) {
        this.a = r2s;
        this.b = r3_String;
        this.d = new byte[b()];
        this.c = r4_bf;
    }

    public byte[] a() {
        this.d[5] = (byte) this.a;
        this.d[6] = (byte) this.b.length();
        System.arraycopy(this.c.a(), 0, this.d, 0, ShareUtils.SHARE_SMS);
        System.arraycopy(this.b.getBytes(), 0, this.d, ShareUtils.SHARE_COLLECT, this.b.length());
        return this.d;
    }

    public int b() {
        return this.b.length() + 7;
    }
}