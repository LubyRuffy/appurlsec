package com.zkmm.adsdk;

// compiled from: SourceFile
final class bi implements Comparable {
    protected double a;
    protected String b;
    protected String c;
    protected String d;
    protected int e;

    bi() {
        this.a = 0.0d;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = -1;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object r5_Object) {
        bi r5_bi = (bi) r5_Object;
        if (this.a > r5_bi.a) {
            return 1;
        }
        if (this.a < r5_bi.a) {
            return -1;
        }
        return 0;
    }

    public final String toString() {
        return new StringBuilder(String.valueOf(this.a)).append(":").append(this.b).append(":").append(this.c).toString();
    }
}