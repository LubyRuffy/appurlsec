package com.baidu.location;

import com.amap.api.location.LocationManagerProxy;

public abstract class BDNotifyListener {
    public int Notified;
    public float differDistance;
    public boolean isAdded;
    public String mCoorType;
    public double mLatitude;
    public double mLatitudeC;
    public double mLongitude;
    public double mLongitudeC;
    public i mNotifyCache;
    public float mRadius;

    public BDNotifyListener() {
        this.mLatitude = 4.9E-324d;
        this.mLongitude = 4.9E-324d;
        this.mRadius = 0.0f;
        this.differDistance = 0.0f;
        this.mCoorType = null;
        this.mLatitudeC = 4.9E-324d;
        this.mLongitudeC = 4.9E-324d;
        this.Notified = 0;
        this.isAdded = false;
        this.mNotifyCache = null;
    }

    public void SetNotifyLocation(double r3d, double r5d, float r7f, String r8_String) {
        this.mLatitude = r3d;
        this.mLongitude = r5d;
        if (r7f < 0.0f) {
            this.mRadius = 200.0f;
        } else {
            this.mRadius = r7f;
        }
        if (r8_String.equals("gcj02") || r8_String.equals("bd09") || r8_String.equals("bd09ll") || r8_String.equals(LocationManagerProxy.GPS_PROVIDER)) {
            this.mCoorType = r8_String;
        } else {
            this.mCoorType = "gcj02";
        }
        if (this.mCoorType.equals("gcj02")) {
            this.mLatitudeC = this.mLatitude;
            this.mLongitudeC = this.mLongitude;
        }
        if (this.isAdded) {
            this.Notified = 0;
            this.mNotifyCache.a(this);
        }
    }

    public void onNotify(BDLocation r4_BDLocation, float r5f) {
        j.if(f.v, "new location, not far from the destination..." + r5f);
    }
}