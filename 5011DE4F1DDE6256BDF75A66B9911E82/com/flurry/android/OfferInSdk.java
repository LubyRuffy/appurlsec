package com.flurry.android;

// compiled from: SourceFile
public final class OfferInSdk {
    long a;
    ab b;
    String c;
    String d;
    int e;
    AdImage f;

    OfferInSdk(long r1j, ab r3_ab, AdImage r4_AdImage, String r5_String, String r6_String, int r7i) {
        this.a = r1j;
        this.b = r3_ab;
        this.c = r5_String;
        this.f = r4_AdImage;
        this.d = r6_String;
        this.e = r7i;
    }

    public final String toString() {
        StringBuilder r0_StringBuilder = new StringBuilder();
        r0_StringBuilder.append("[id=" + this.a).append(",name=" + this.c + "]");
        return r0_StringBuilder.toString();
    }
}