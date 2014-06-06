package com.flurry.android;

import com.tencent.mm.sdk.contact.RContactStorage;

// compiled from: SourceFile
public final class Offer {
    private long a;
    private String b;
    private String c;
    private int d;
    private AdImage e;

    Offer(long r1j, AdImage r3_AdImage, String r4_String, String r5_String, int r6i) {
        this.a = r1j;
        this.b = r4_String;
        this.e = r3_AdImage;
        this.c = r5_String;
        this.d = r6i;
    }

    public final String getDescription() {
        return this.c;
    }

    public final long getId() {
        return this.a;
    }

    public final AdImage getImage() {
        return this.e;
    }

    public final String getName() {
        return this.b;
    }

    public final int getPrice() {
        return this.d;
    }

    public final String getUrl() {
        return RContactStorage.PRIMARY_KEY;
    }

    public final String toString() {
        StringBuilder r0_StringBuilder = new StringBuilder();
        r0_StringBuilder.append("[id=" + this.a + ",name=" + this.b + ",price=" + this.d + ", image size: " + this.e.e.length);
        return r0_StringBuilder.toString();
    }
}