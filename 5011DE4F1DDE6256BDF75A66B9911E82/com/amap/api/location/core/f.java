package com.amap.api.location.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: GeoPoint.java
final class f implements Creator<GeoPoint> {
    f() {
    }

    public GeoPoint a(Parcel r3_Parcel) {
        return new GeoPoint((Parcel)null);
    }

    public GeoPoint[] a(int r2i) {
        return new GeoPoint[r2i];
    }

    public /* synthetic */ Object createFromParcel(Parcel r2_Parcel) {
        return a(r2_Parcel);
    }

    public /* synthetic */ Object[] newArray(int r2i) {
        return a(r2i);
    }
}