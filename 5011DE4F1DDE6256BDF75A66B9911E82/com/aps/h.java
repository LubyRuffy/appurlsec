package com.aps;

// compiled from: Fence.java
public class h {
    public double a;
    public double b;
    public float c;
    private long d;

    public h() {
        this.a = 0.0d;
        this.b = 0.0d;
        this.c = 0.0f;
        this.d = -1;
    }

    public long a() {
        return this.d;
    }

    public void a(long r3j) {
        if (r3j >= 0) {
            this.d = o.a() + r3j;
        } else {
            this.d = r3j;
        }
    }

    public String b() {
        StringBuilder r0_StringBuilder = new StringBuilder();
        r0_StringBuilder.append(this.a).append("#").append(this.b).append("#").append(this.c);
        return r0_StringBuilder.toString();
    }
}