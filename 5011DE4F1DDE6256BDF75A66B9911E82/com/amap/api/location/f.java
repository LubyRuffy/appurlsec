package com.amap.api.location;

// compiled from: RequestLocationEntity.java
public class f {
    public long a;
    public float b;
    public AMapLocationListener c;
    public boolean d;
    public AMapLocation e;
    public String f;

    public f(long r2j, float r4f, AMapLocationListener r5_AMapLocationListener, String r6_String) {
        this.d = true;
        this.e = null;
        this.a = r2j;
        this.b = r4f;
        this.c = r5_AMapLocationListener;
        this.f = r6_String;
    }

    public boolean equals(Object r5_Object) {
        if (this == r5_Object) {
            return true;
        }
        if (r5_Object == null) {
            return false;
        }
        if (getClass() != r5_Object.getClass()) {
            return false;
        }
        f r5_f = (f) r5_Object;
        if (this.c == null) {
            return r5_f.c == null;
        } else {
            if (this.c.equals(r5_f.c)) {
                return true;
            }
            return false;
        }
    }

    public int hashCode() {
        return (this.c == null ? 0 : this.c.hashCode()) + 31;
    }
}