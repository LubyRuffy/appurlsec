package com.amap.api.location;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import com.amap.api.location.core.a;
import com.amap.api.location.core.c;
import com.amap.api.location.core.d;
import com.aps.g;
import com.aps.h;
import com.aps.i;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.tauth.Constants;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: IAPSManager.java
public class b implements Runnable {
    private static b e;
    private i a;
    private volatile boolean b;
    private Thread c;
    private Context d;
    private long f;
    private a g;

    protected b(Context r5_Context, a r6_a) {
        this.a = null;
        this.b = true;
        this.c = null;
        this.f = 2000;
        this.b = true;
        this.d = r5_Context;
        this.a = g.a();
        c.a(r5_Context);
        this.a.a(r5_Context);
        this.a.a("api_serverSDK_130905##S128DF1572465B890OE3F7A13167KLEI##" + c.b(r5_Context) + "," + c.a());
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put(SharedPref.KEY, c.b(r5_Context));
            r1_JSONObject.put("X-INFO", c.a(r5_Context).b());
            JSONObject r0_JSONObject = new JSONObject();
            r0_JSONObject.put("ex", com.aps.b.b(c.a(r5_Context).d().getBytes()));
            r1_JSONObject.put("X-BIZ", r0_JSONObject);
            r1_JSONObject.put("User-Agent", d.b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.a.a(r1_JSONObject);
        this.g = r6_a;
    }

    private AMapLocation a(com.aps.c r5_com_aps_c) {
        AMapLocation r0_AMapLocation = new AMapLocation(RContactStorage.PRIMARY_KEY);
        r0_AMapLocation.setProvider(LocationProviderProxy.AMapNetwork);
        r0_AMapLocation.setLatitude(r5_com_aps_c.d());
        r0_AMapLocation.setLongitude(r5_com_aps_c.c());
        r0_AMapLocation.setAccuracy(r5_com_aps_c.e());
        r0_AMapLocation.setTime(r5_com_aps_c.f());
        r0_AMapLocation.setPoiId(r5_com_aps_c.a());
        r0_AMapLocation.setFloor(r5_com_aps_c.b());
        Bundle r1_Bundle = new Bundle();
        r1_Bundle.putString("citycode", r5_com_aps_c.i());
        r1_Bundle.putString(Constants.PARAM_APP_DESC, r5_com_aps_c.j());
        r1_Bundle.putString("adcode", r5_com_aps_c.k());
        r0_AMapLocation.setExtras(r1_Bundle);
        try {
            a(r0_AMapLocation, r5_com_aps_c.i(), r5_com_aps_c.k(), r5_com_aps_c.j());
        } catch (Exception e) {
        }
        return r0_AMapLocation;
    }

    static b a(Context r1_Context, a r2_a) {
        if (e == null) {
            e = new b(r1_Context, r2_a);
        }
        return e;
    }

    private void a(AMapLocation r7_AMapLocation, String r8_String, String r9_String, String r10_String) {
        String[] r0_StringA = r10_String.split(" ");
        r7_AMapLocation.setCityCode(r8_String);
        r7_AMapLocation.setAdCode(r9_String);
        if (r8_String.equals(RContactStorage.PRIMARY_KEY) || (!a(r8_String))) {
            if (r0_StringA.length > 3) {
                r7_AMapLocation.setProvince(r0_StringA[0]);
                r7_AMapLocation.setCity(r0_StringA[1]);
                r7_AMapLocation.setDistrict(r0_StringA[2]);
            }
        } else {
            if (r0_StringA.length > 2) {
                r7_AMapLocation.setCity(r0_StringA[0]);
                r7_AMapLocation.setDistrict(r0_StringA[1]);
            }
        }
    }

    private boolean a(String r2_String) {
        return r2_String.endsWith("010") || r2_String.endsWith("021") || r2_String.endsWith("022") || r2_String.endsWith("023");
    }

    private com.aps.c c() throws Exception {
        return d();
    }

    private com.aps.c d() {
        try {
            return this.a != null ? this.a.b() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean e() {
        boolean r0z = false;
        if (System.currentTimeMillis() - a.c <= 5 * this.f) {
            return false;
        }
        a.b = r0z;
        return true;
    }

    void a() {
        this.b = false;
        if (this.c != null) {
            this.c.interrupt();
        }
        this.a.c();
        this.a = null;
        e = null;
    }

    void a(long r3j) {
        if (r3j > this.f) {
            this.f = r3j;
        }
    }

    void a(PendingIntent r2_PendingIntent) {
        this.a.a(r2_PendingIntent);
    }

    void a(h r2_h, PendingIntent r3_PendingIntent) {
        this.a.a(r2_h, r3_PendingIntent);
    }

    int b_() {
        return this.a != null ? this.a.e() : 0;
    }

    public void run() {
        Object r1_Object;
        Message r0_Message;
        Looper.prepare();
        while (this.b && !Thread.interrupted()) {
            this.c = Thread.currentThread();
            r1_Object = null;
            try {
                if (((!a.b) || e()) && a.d) {
                    AMapLocation r0_AMapLocation;
                    com.aps.c r0_com_aps_c = c();
                    r0_AMapLocation = r0_com_aps_c != null ? a(r0_com_aps_c) : null;
                    if (r0_AMapLocation == null || (!a.d)) {
                        if (a.a != -1) {
                            a.a(this.d);
                        }
                        try {
                            Thread.sleep(this.f);
                        } catch (Exception e) {
                        }
                    } else {
                        if ((!a.b) || e()) {
                            Message r1_Message = new Message();
                            r1_Message.obj = r0_AMapLocation;
                            r1_Message.what = a.a;
                            this.g.sendMessage(r1_Message);
                        }
                        if (a.a != -1) {
                            Thread.sleep(this.f);
                        } else {
                            a.a(this.d);
                            Thread.sleep(this.f);
                        }
                    }
                } else {
                    try {
                        Thread.sleep(this.f);
                    } catch (Exception e_2) {
                        if (0 == 0 || (!a.d)) {
                            if (a.a != -1) {
                                a.a(this.d);
                            }
                            Thread.sleep(this.f);
                        } else {
                            if ((!a.b) || e()) {
                                r0_Message = new Message();
                                r0_Message.obj = r1_Object;
                                r0_Message.what = a.a;
                                this.g.sendMessage(r0_Message);
                            }
                            if (a.a != -1) {
                                Thread.sleep(this.f);
                            } else {
                                a.a(this.d);
                                Thread.sleep(this.f);
                            }
                        }
                    }
                    if (0 == 0 || (!a.d)) {
                        if (a.a == -1) {
                            a.a(this.d);
                        }
                        try {
                            Thread.sleep(this.f);
                        } catch (Exception e_3) {
                        }
                    } else if ((!a.b) || e()) {
                        r0_Message = new Message();
                        r0_Message.obj = r1_Object;
                        r0_Message.what = a.a;
                        this.g.sendMessage(r0_Message);
                        if (a.a == -1) {
                            Thread.sleep(this.f);
                        } else {
                            a.a(this.d);
                            Thread.sleep(this.f);
                        }
                    } else {
                        if (a.a == -1) {
                            a.a(this.d);
                        }
                        Thread.sleep(this.f);
                    }
                }
            } catch (Exception e_4) {
                e_4.printStackTrace();
                if (0 == 0 || (!a.d)) {
                    if (a.a != -1) {
                        a.a(this.d);
                    }
                    Thread.sleep(this.f);
                } else {
                    if ((!a.b) || e()) {
                        r0_Message = new Message();
                        r0_Message.obj = r1_Object;
                        r0_Message.what = a.a;
                        this.g.sendMessage(r0_Message);
                    }
                    if (a.a != -1) {
                        Thread.sleep(this.f);
                    } else {
                        a.a(this.d);
                        Thread.sleep(this.f);
                    }
                }
            }
        }
    }
}