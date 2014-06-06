package com.baidu.mobstat;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.baidu.mobstat.a.c;
import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.json.JSONException;
import qsbk.app.database.QsbkDatabase;

class b {
    static String a;
    String b;
    String c;
    String d;
    int e;
    String f;
    String g;
    int h;
    int i;
    String j;
    String k;
    String l;
    String m;
    String n;
    String o;
    String p;

    static {
        a = "Android";
    }

    b() {
        this.c = null;
        this.d = null;
        this.e = -1;
        this.j = null;
    }

    public synchronized void a(Context r3_Context) {
        if (this.e != -1) {
        } else {
            com.baidu.mobstat.a.b.d(r3_Context, "android.permission.READ_PHONE_STATE");
            com.baidu.mobstat.a.b.d(r3_Context, "android.permission.INTERNET");
            com.baidu.mobstat.a.b.d(r3_Context, "android.permission.ACCESS_NETWORK_STATE");
            com.baidu.mobstat.a.b.d(r3_Context, "android.permission.WRITE_SETTINGS");
            TelephonyManager r0_TelephonyManager = (TelephonyManager) r3_Context.getSystemService("phone");
            this.b = CooperService.getOSVersion();
            this.l = CooperService.getPhoneModel();
            this.g = CooperService.getDeviceId(r0_TelephonyManager, r3_Context);
            this.d = CooperService.getCIUD(r3_Context);
            try {
                this.k = CooperService.getOperator(r0_TelephonyManager);
            } catch (Exception e) {
                c.a(e);
            }
            try {
                this.h = x.a(r3_Context);
                this.i = x.b(r3_Context);
                if (r3_Context.getResources().getConfiguration().orientation == 2) {
                    c.a("stat", "Configuration.ORIENTATION_LANDSCAPE");
                    this.h ^= this.i;
                    this.i = this.h ^ this.i;
                    this.h ^= this.i;
                }
            } catch (Exception e_2) {
                c.a(e_2);
            }
            this.j = CooperService.getAppChannel(r3_Context);
            this.c = CooperService.getAppKey(r3_Context);
            try {
                this.e = CooperService.getAppVersionCode(r3_Context);
                this.f = CooperService.getAppVersionName(r3_Context);
            } catch (Exception e_3) {
                c.a(e_3);
            }
            try {
            } catch (Exception e_4) {
                c.a(e_4);
            }
            if (CooperService.checkCellLocationSetting(r3_Context)) {
                this.m = x.e(r3_Context);
                try {
                } catch (Exception e_5) {
                    c.a(e_5);
                }
                if (CooperService.checkGPSLocationSetting(r3_Context)) {
                    this.n = RContactStorage.PRIMARY_KEY;
                    if (CooperService.checkWifiLocationSetting(r3_Context)) {
                        this.o = x.h(r3_Context);
                        this.p = CooperService.getLinkedWay(r3_Context);
                    } else {
                        this.o = RContactStorage.PRIMARY_KEY;
                        this.p = CooperService.getLinkedWay(r3_Context);
                    }
                } else {
                    this.n = x.f(r3_Context);
                    try {
                    } catch (Exception e_6) {
                        c.a(e_6);
                    }
                    if (CooperService.checkWifiLocationSetting(r3_Context)) {
                        this.o = x.h(r3_Context);
                        try {
                            this.p = CooperService.getLinkedWay(r3_Context);
                        } catch (Exception e_7) {
                            c.a(e_7);
                        }
                    } else {
                        this.o = RContactStorage.PRIMARY_KEY;
                        this.p = CooperService.getLinkedWay(r3_Context);
                    }
                }
            } else {
                this.m = "0_0_0";
                if (CooperService.checkGPSLocationSetting(r3_Context)) {
                    this.n = RContactStorage.PRIMARY_KEY;
                    if (CooperService.checkWifiLocationSetting(r3_Context)) {
                        this.o = x.h(r3_Context);
                        this.p = CooperService.getLinkedWay(r3_Context);
                    } else {
                        this.o = RContactStorage.PRIMARY_KEY;
                        this.p = CooperService.getLinkedWay(r3_Context);
                    }
                } else {
                    this.n = x.f(r3_Context);
                    if (CooperService.checkWifiLocationSetting(r3_Context)) {
                        this.o = RContactStorage.PRIMARY_KEY;
                        this.p = CooperService.getLinkedWay(r3_Context);
                    } else {
                        this.o = x.h(r3_Context);
                        this.p = CooperService.getLinkedWay(r3_Context);
                    }
                }
            }
        }
    }

    public synchronized void b_(Context r5_Context) {
        try {
            if (DataCore.b.length() > 0) {
            } else {
                a(r5_Context);
                DataCore.b.put("o", a == null ? RContactStorage.PRIMARY_KEY : a);
                DataCore.b.put("s", this.b == null ? RContactStorage.PRIMARY_KEY : this.b);
                DataCore.b.put("k", this.c == null ? RContactStorage.PRIMARY_KEY : this.c);
                DataCore.b.put("i", this.d == null ? RContactStorage.PRIMARY_KEY : this.d);
                DataCore.b.put("v", "3.4");
                DataCore.b.put(QsbkDatabase.A, this.e);
                DataCore.b.put("n", this.f == null ? RContactStorage.PRIMARY_KEY : this.f);
                DataCore.b.put("d", this.g == null ? RContactStorage.PRIMARY_KEY : this.g);
                DataCore.b.put("w", this.h);
                DataCore.b.put("h", this.i);
                DataCore.b.put(KEYS.CTXTSETTING, this.j == null ? RContactStorage.PRIMARY_KEY : this.j);
                DataCore.b.put("op", this.k == null ? RContactStorage.PRIMARY_KEY : this.k);
                DataCore.b.put("m", this.l == null ? RContactStorage.PRIMARY_KEY : this.l);
                DataCore.b.put("cl", this.m);
                DataCore.b.put("gl", this.n == null ? RContactStorage.PRIMARY_KEY : this.n);
                DataCore.b.put("wl", this.o == null ? RContactStorage.PRIMARY_KEY : this.o);
                DataCore.b.put("l", this.p == null ? RContactStorage.PRIMARY_KEY : this.p);
                DataCore.b.put(QsbkDatabase.T, System.currentTimeMillis());
                DataCore.b.put("sq", 0);
                c.a("stat", DataCore.b.toString());
            }
        } catch (JSONException e) {
            String r0_String = "header ini error";
            c.a("stat", r0_String);
            throw new RuntimeException(r0_String);
        } catch (Throwable th) {
        }
    }
}