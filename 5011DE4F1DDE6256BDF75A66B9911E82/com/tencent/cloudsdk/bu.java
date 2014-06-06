package com.tencent.cloudsdk;

// compiled from: SourceFile
public class bu implements bs {
    private int a;
    private int b;
    private int c;

    public int a() {
        return this.c;
    }

    public void a(br r11_br) {
        int r0i = r11_br.b();
        byte[] r1_byteA = r11_br.a();
        byte[] r2_byteA = new byte[2];
        int r3i = r0i + 1;
        r2_byteA[0] = r1_byteA[r0i];
        r0i = r3i + 1;
        r2_byteA[1] = r1_byteA[r3i];
        this.c = eo.c(r2_byteA);
        er.a("SettingParser", new StringBuilder(">>>\u89e3\u6790\u5230\u914d\u7f6e\u7248\u672c\u53f7:").append(this.c).append(",tid:").append(Thread.currentThread().getId()).toString());
        r2_byteA = new byte[4];
        r3i = r0i + 1;
        r2_byteA[0] = r1_byteA[r0i];
        r0i = r3i + 1;
        r2_byteA[1] = r1_byteA[r3i];
        r3i = r0i + 1;
        r2_byteA[2] = r1_byteA[r0i];
        int r4i = r3i + 1;
        r2_byteA[3] = r1_byteA[r3i];
        this.a = eo.a(r2_byteA);
        er.a("SettingParser", new StringBuilder(">>>\u89e3\u6790\u5230\u8fc7\u671f\u65f6\u957f:").append(this.a).append(",tid:").append(Thread.currentThread().getId()).toString());
        byte[] r0_byteA = new byte[2];
        int r2i = r4i + 1;
        r0_byteA[0] = r1_byteA[r4i];
        r0_byteA[1] = r1_byteA[r2i];
        this.b = eo.b(r0_byteA);
        er.a("SettingParser", new StringBuilder(">>>\u89e3\u6790\u5230\u91cd\u65b0\u6d4b\u901f\u65f6\u957f:").append(this.b).append(",tid:").append(Thread.currentThread().getId()).toString());
        r11_br.a(r2i + 1);
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.a;
    }
}