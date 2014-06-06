package com.baidu.location;

import com.tencent.mm.sdk.contact.RContact;
import qsbk.app.utils.Base64;

public final class LocationClientOption {
    public static final int GpsFirst = 1;
    public static final int MIN_SCAN_SPAN = 1000;
    public static final int NetWorkFirst = 2;
    protected String a;
    protected String b;
    protected boolean c;
    protected int d;
    protected int e;
    protected String f;
    protected int g;
    protected boolean h;
    protected boolean i;
    protected boolean j;
    protected boolean k;
    protected float l;
    protected int m;
    protected String n;

    public LocationClientOption() {
        this.a = "gcj02";
        this.b = "detail";
        this.c = false;
        this.d = 0;
        this.e = 12000;
        this.f = "SDK2.0";
        this.g = 1;
        this.h = false;
        this.i = true;
        this.j = false;
        this.k = false;
        this.l = 500.0f;
        this.m = 3;
        this.n = "com.baidu.location.service_v2.9";
    }

    public LocationClientOption(LocationClientOption r4_LocationClientOption) {
        this.a = "gcj02";
        this.b = "detail";
        this.c = false;
        this.d = 0;
        this.e = 12000;
        this.f = "SDK2.0";
        this.g = 1;
        this.h = false;
        this.i = true;
        this.j = false;
        this.k = false;
        this.l = 500.0f;
        this.m = 3;
        this.n = "com.baidu.location.service_v2.9";
        this.a = r4_LocationClientOption.a;
        this.b = r4_LocationClientOption.b;
        this.c = r4_LocationClientOption.c;
        this.d = r4_LocationClientOption.d;
        this.e = r4_LocationClientOption.e;
        this.f = r4_LocationClientOption.f;
        this.g = r4_LocationClientOption.g;
        this.h = r4_LocationClientOption.h;
        this.k = r4_LocationClientOption.k;
        this.l = r4_LocationClientOption.l;
        this.m = r4_LocationClientOption.m;
        this.n = r4_LocationClientOption.n;
        this.i = r4_LocationClientOption.i;
    }

    public void disableCache(boolean r1z) {
        this.i = r1z;
    }

    public boolean equals(LocationClientOption r3_LocationClientOption) {
        return this.a.equals(r3_LocationClientOption.a) && this.b.equals(r3_LocationClientOption.b) && this.c == r3_LocationClientOption.c && this.d == r3_LocationClientOption.d && this.e == r3_LocationClientOption.e && this.f.equals(r3_LocationClientOption.f) && this.h == r3_LocationClientOption.h && this.g == r3_LocationClientOption.g && this.m == r3_LocationClientOption.m && this.k == r3_LocationClientOption.k && this.l == r3_LocationClientOption.l && this.i == r3_LocationClientOption.i;
    }

    public String getAddrType() {
        return this.b;
    }

    public String getCoorType() {
        return this.a;
    }

    public float getPoiDistance() {
        return this.l;
    }

    public boolean getPoiExtranInfo() {
        return this.k;
    }

    public int getPoiNumber() {
        return this.m;
    }

    public int getPriority() {
        return this.g;
    }

    public String getProdName() {
        return this.f;
    }

    public int getScanSpan() {
        return this.d;
    }

    public String getServiceName() {
        return this.n;
    }

    public int getTimeOut() {
        return this.e;
    }

    public boolean isDisableCache() {
        return this.i;
    }

    public boolean isLocationNotify() {
        return this.h;
    }

    public boolean isOpenGps() {
        return this.c;
    }

    public void setAddrType(String r3_String) {
        if (r3_String.length() > 32) {
            r3_String = r3_String.substring(0, Base64.ORDERED);
        }
        this.b = r3_String;
    }

    public void setCoorType(String r3_String) {
        String r0_String = r3_String.toLowerCase();
        if (r0_String.equals("gcj02") || r0_String.equals("bd09") || r0_String.equals("bd09ll")) {
            this.a = r0_String;
        }
    }

    public void setLocationNotify(boolean r1z) {
        this.h = r1z;
    }

    public void setOpenGps(boolean r1z) {
        this.c = r1z;
    }

    public void setPoiDistance(float r1f) {
        this.l = r1f;
    }

    public void setPoiExtraInfo(boolean r1z) {
        this.k = r1z;
    }

    public void setPoiNumber(int r2i) {
        if (r2i > 10) {
            r2i = 10;
        }
        this.m = r2i;
    }

    public void setPriority(int r2i) {
        if (r2i == 1 || r2i == 2) {
            this.g = r2i;
        }
    }

    public void setProdName(String r3_String) {
        if (r3_String.length() > 64) {
            r3_String = r3_String.substring(0, RContact.MM_CONTACTFLAG_FAVOURCONTACT);
        }
        this.f = r3_String;
    }

    public void setScanSpan(int r1i) {
        this.d = r1i;
    }

    public void setServiceName(String r1_String) {
        this.n = r1_String;
    }

    public void setTimeOut(int r1i) {
        this.e = r1i;
    }
}