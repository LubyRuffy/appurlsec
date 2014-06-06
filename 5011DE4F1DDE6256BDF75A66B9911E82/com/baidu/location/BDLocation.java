package com.baidu.location;

import com.tencent.mm.sdk.contact.RContactStorage;
import org.apache.cordova.Globalization;
import org.json.JSONObject;
import qsbk.app.push.Utils;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.utils.audit.Rotate3dAnimation;

public final class BDLocation {
    public static final int TypeCacheLocation = 65;
    public static final int TypeCriteriaException = 62;
    public static final int TypeGpsLocation = 61;
    public static final int TypeNetWorkException = 63;
    public static final int TypeNetWorkLocation = 161;
    public static final int TypeNone = 0;
    public static final int TypeOffLineLocation = 66;
    public static final int TypeOffLineLocationFail = 67;
    public static final int TypeOffLineLocationNetworkFail = 68;
    public static final int TypeServerError = 167;
    private int a;
    private String b;
    private double c;
    private double d;
    private boolean e;
    private double f;
    private boolean g;
    private float h;
    private boolean i;
    private float j;
    private boolean k;
    private int l;
    private float m;
    public a mAddr;
    public String mServerString;
    private String n;
    private String o;
    private boolean p;
    private boolean q;
    private String r;
    private boolean s;

    public class a {
        public String byte;
        public String do;
        public String for;
        public String if;
        public String int;
        public String new;
        public String try;

        public a() {
            this.if = null;
            this.new = null;
            this.int = null;
            this.byte = null;
            this.do = null;
            this.for = null;
            this.try = null;
        }
    }

    public BDLocation() {
        this.a = 0;
        this.b = null;
        this.c = 4.9E-324d;
        this.d = 4.9E-324d;
        this.e = false;
        this.f = 4.9E-324d;
        this.g = false;
        this.h = 0.0f;
        this.i = false;
        this.j = 0.0f;
        this.k = false;
        this.l = -1;
        this.m = -1.0f;
        this.n = null;
        this.o = null;
        this.p = false;
        this.q = false;
        this.r = null;
        this.mServerString = null;
        this.s = false;
        this.mAddr = new a();
    }

    public BDLocation(double r6d, double r8d, float r10f) {
        this.a = 0;
        this.b = null;
        this.c = 4.9E-324d;
        this.d = 4.9E-324d;
        this.e = false;
        this.f = 4.9E-324d;
        this.g = false;
        this.h = 0.0f;
        this.i = false;
        this.j = 0.0f;
        this.k = false;
        this.l = -1;
        this.m = -1.0f;
        this.n = null;
        this.o = null;
        this.p = false;
        this.q = false;
        this.r = null;
        this.mServerString = null;
        this.s = false;
        this.mAddr = new a();
        this.c = r8d;
        this.d = r6d;
        this.j = r10f;
        this.b = j.a();
    }

    public BDLocation(String r6_String) {
        this.a = 0;
        this.b = null;
        this.c = 4.9E-324d;
        this.d = 4.9E-324d;
        this.e = false;
        this.f = 4.9E-324d;
        this.g = false;
        this.h = 0.0f;
        this.i = false;
        this.j = 0.0f;
        this.k = false;
        this.l = -1;
        this.m = -1.0f;
        this.n = null;
        this.o = null;
        this.p = false;
        this.q = false;
        this.r = null;
        this.mServerString = null;
        this.s = false;
        this.mAddr = new a();
        if (r6_String == null || r6_String.equals(RContactStorage.PRIMARY_KEY)) {
        } else {
            JSONObject r0_JSONObject;
            JSONObject r1_JSONObject;
            int r2i;
            try {
                r0_JSONObject = new JSONObject(r6_String);
                r1_JSONObject = r0_JSONObject.getJSONObject("result");
                r2i = Integer.parseInt(r1_JSONObject.getString(QQDialogAuthorizeActivity.ERROR_RET));
                setLocType(r2i);
                setTime(r1_JSONObject.getString(Globalization.TIME));
            } catch (Exception e) {
                e.printStackTrace();
                this.a = 0;
                this.p = false;
            }
            if (r2i == TypeGpsLocation) {
                r0_JSONObject = r0_JSONObject.getJSONObject(Utils.RESPONSE_CONTENT);
                r1_JSONObject = r0_JSONObject.getJSONObject("point");
                setLatitude(Double.parseDouble(r1_JSONObject.getString(Rotate3dAnimation.ROTATE_Y)));
                setLongitude(Double.parseDouble(r1_JSONObject.getString(Rotate3dAnimation.ROTATE_X)));
                setRadius(Float.parseFloat(r0_JSONObject.getString("radius")));
                setSpeed(Float.parseFloat(r0_JSONObject.getString("s")));
                setDerect(Float.parseFloat(r0_JSONObject.getString("d")));
                setSatelliteNumber(Integer.parseInt(r0_JSONObject.getString("n")));
            } else if (r2i == 161) {
                r1_JSONObject = r0_JSONObject.getJSONObject(Utils.RESPONSE_CONTENT);
                r0_JSONObject = r1_JSONObject.getJSONObject("point");
                setLatitude(Double.parseDouble(r0_JSONObject.getString(Rotate3dAnimation.ROTATE_Y)));
                setLongitude(Double.parseDouble(r0_JSONObject.getString(Rotate3dAnimation.ROTATE_X)));
                setRadius(Float.parseFloat(r1_JSONObject.getString("radius")));
                if (r1_JSONObject.has("addr")) {
                    String r0_String = r1_JSONObject.getString("addr");
                    this.mAddr.try = r0_String;
                    j.if(f.v, r0_String);
                    String[] r0_StringA = r0_String.split(",");
                    this.mAddr.if = r0_StringA[0];
                    this.mAddr.new = r0_StringA[1];
                    this.mAddr.int = r0_StringA[2];
                    this.mAddr.byte = r0_StringA[3];
                    this.mAddr.do = r0_StringA[4];
                    this.mAddr.for = r0_StringA[5];
                    if (this.mAddr.if.contains("\u5317\u4eac") && this.mAddr.new.contains("\u5317\u4eac")) {
                        j.if(f.v, "true,beijing");
                        r0_String = this.mAddr.if;
                        this.mAddr.try = r0_String + this.mAddr.int + this.mAddr.byte + this.mAddr.do;
                        this.p = true;
                    } else if (this.mAddr.if.contains("\u4e0a\u6d77") && this.mAddr.new.contains("\u4e0a\u6d77")) {
                        j.if(f.v, "true,beijing");
                        r0_String = this.mAddr.if;
                        this.mAddr.try = r0_String + this.mAddr.int + this.mAddr.byte + this.mAddr.do;
                        this.p = true;
                    } else if (this.mAddr.if.contains("\u5929\u6d25") && this.mAddr.new.contains("\u5929\u6d25")) {
                        j.if(f.v, "true,beijing");
                        r0_String = this.mAddr.if;
                        this.mAddr.try = r0_String + this.mAddr.int + this.mAddr.byte + this.mAddr.do;
                        this.p = true;
                    } else if (this.mAddr.if.contains("\u91cd\u5e86") && this.mAddr.new.contains("\u91cd\u5e86")) {
                        j.if(f.v, "true,beijing");
                        r0_String = this.mAddr.if;
                        this.mAddr.try = r0_String + this.mAddr.int + this.mAddr.byte + this.mAddr.do;
                        this.p = true;
                    } else {
                        r0_String = this.mAddr.if + this.mAddr.new;
                        this.mAddr.try = r0_String + this.mAddr.int + this.mAddr.byte + this.mAddr.do;
                        this.p = true;
                    }
                } else {
                    this.p = false;
                    setAddrStr(null);
                }
                if (r1_JSONObject.has("poi")) {
                    this.q = true;
                    this.o = r1_JSONObject.getJSONObject("poi").toString();
                }
            } else if (r2i == 66 || r2i == 68) {
                r0_JSONObject = r0_JSONObject.getJSONObject(Utils.RESPONSE_CONTENT);
                r1_JSONObject = r0_JSONObject.getJSONObject("point");
                setLatitude(Double.parseDouble(r1_JSONObject.getString(Rotate3dAnimation.ROTATE_Y)));
                setLongitude(Double.parseDouble(r1_JSONObject.getString(Rotate3dAnimation.ROTATE_X)));
                setRadius(Float.parseFloat(r0_JSONObject.getString("radius")));
                a(Boolean.valueOf(Boolean.parseBoolean(r0_JSONObject.getString("isCellChanged"))));
            }
        }
    }

    public BDLocation(String r6_String, double r7d, double r9d, float r11f, String r12_String, String r13_String) {
        this.a = 0;
        this.b = null;
        this.c = 4.9E-324d;
        this.d = 4.9E-324d;
        this.e = false;
        this.f = 4.9E-324d;
        this.g = false;
        this.h = 0.0f;
        this.i = false;
        this.j = 0.0f;
        this.k = false;
        this.l = -1;
        this.m = -1.0f;
        this.n = null;
        this.o = null;
        this.p = false;
        this.q = false;
        this.r = null;
        this.mServerString = null;
        this.s = false;
        this.mAddr = new a();
        this.b = r6_String;
        this.c = r7d;
        this.d = r9d;
        this.j = r11f;
        this.n = r12_String;
        this.r = r13_String;
        this.b = j.a();
    }

    private void a(Boolean r2_Boolean) {
        this.s = r2_Boolean.booleanValue();
    }

    public String getAddrStr() {
        return this.mAddr.try;
    }

    public double getAltitude() {
        return this.f;
    }

    public String getCity() {
        return this.mAddr.new;
    }

    public String getCityCode() {
        return this.mAddr.for;
    }

    public String getCoorType() {
        return this.n;
    }

    public float getDerect() {
        return this.m;
    }

    public String getDistrict() {
        return this.mAddr.int;
    }

    public double getLatitude() {
        return this.c;
    }

    public int getLocType() {
        return this.a;
    }

    public double getLongitude() {
        return this.d;
    }

    public String getPoi() {
        return this.o;
    }

    public String getProvince() {
        return this.mAddr.if;
    }

    public float getRadius() {
        return this.j;
    }

    public int getSatelliteNumber() {
        this.k = true;
        return this.l;
    }

    public float getSpeed() {
        return this.h;
    }

    public String getStreet() {
        return this.mAddr.byte;
    }

    public String getStreetNumber() {
        return this.mAddr.do;
    }

    public String getTime() {
        return this.b;
    }

    public boolean hasAddr() {
        return this.p;
    }

    public boolean hasAltitude() {
        return this.e;
    }

    public boolean hasPoi() {
        return this.q;
    }

    public boolean hasRadius() {
        return this.i;
    }

    public boolean hasSateNumber() {
        return this.k;
    }

    public boolean hasSpeed() {
        return this.g;
    }

    public boolean isCellChangeFlag() {
        return this.s;
    }

    public void setAddrStr(String r2_String) {
        this.r = r2_String;
        this.p = true;
    }

    public void setAltitude(double r2d) {
        this.f = r2d;
        this.e = true;
    }

    public void setCoorType(String r1_String) {
        this.n = r1_String;
    }

    public void setDerect(float r1f) {
        this.m = r1f;
    }

    public void setLatitude(double r1d) {
        this.c = r1d;
    }

    public void setLocType(int r1i) {
        this.a = r1i;
    }

    public void setLongitude(double r1d) {
        this.d = r1d;
    }

    public void setRadius(float r2f) {
        this.j = r2f;
        this.i = true;
    }

    public void setSatelliteNumber(int r1i) {
        this.l = r1i;
    }

    public void setSpeed(float r2f) {
        this.h = r2f;
        this.g = true;
    }

    public void setTime(String r1_String) {
        this.b = r1_String;
    }

    public String toJsonString() {
        return null;
    }

    public BDLocation toNewLocation(String r2_String) {
        return null;
    }
}