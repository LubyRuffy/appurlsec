package com.amap.api.location.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.mm.sdk.contact.RContactStorage;

public class GeoPoint implements Parcelable {
    public static final Creator<GeoPoint> CREATOR;
    private long a;
    private long b;
    private double c;
    private double d;

    static {
        CREATOR = new f();
    }

    public GeoPoint() {
        this.a = -9223372036854775808L;
        this.b = -9223372036854775808L;
        this.c = 4.9E-324d;
        this.d = 4.9E-324d;
        this.a = 0;
        this.b = 0;
    }

    public GeoPoint(int r5i, int r6i) {
        this.a = -9223372036854775808L;
        this.b = -9223372036854775808L;
        this.c = 4.9E-324d;
        this.d = 4.9E-324d;
        this.a = (long) r5i;
        this.b = (long) r6i;
    }

    public GeoPoint(long r5j, long r7j) {
        this.a = -9223372036854775808L;
        this.b = -9223372036854775808L;
        this.c = 4.9E-324d;
        this.d = 4.9E-324d;
        this.a = r5j;
        this.b = r7j;
    }

    private GeoPoint(Parcel r5_Parcel) {
        this.a = -9223372036854775808L;
        this.b = -9223372036854775808L;
        this.c = 4.9E-324d;
        this.d = 4.9E-324d;
        this.a = r5_Parcel.readLong();
        this.b = r5_Parcel.readLong();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object r6_Object) {
        if (r6_Object == null || r6_Object.getClass() != getClass()) {
            return false;
        }
        GeoPoint r6_GeoPoint = (GeoPoint) r6_Object;
        return (this.c > r6_GeoPoint.c ? 1 : (this.c == r6_GeoPoint.c? 0 : -1)) == 0 && this.d == r6_GeoPoint.d && this.a == r6_GeoPoint.a && this.b == r6_GeoPoint.b;
    }

    public int getLatitudeE6() {
        return (int) this.a;
    }

    public int getLongitudeE6() {
        return (int) this.b;
    }

    public int hashCode() {
        return (int) (this.d * 7.0d + this.c * 11.0d);
    }

    public String toString() {
        return RContactStorage.PRIMARY_KEY + this.a + "," + this.b;
    }

    public void writeToParcel(Parcel r3_Parcel, int r4i) {
        r3_Parcel.writeLong(this.a);
        r3_Parcel.writeLong(this.b);
    }
}