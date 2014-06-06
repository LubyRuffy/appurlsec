package com.tencent.cloudsdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.androidquery.util.Constants;
import com.qiubai.library.adview.AdViewManager;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.cloudsdk.defaultsdk.mna.tsocket.GlobalContext;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class bv {
    private static bv n;
    private static int o;
    private static final byte[] p;
    int a;
    int b;
    int c;
    int d;
    long e;
    int f;
    int g;
    List h;
    List i;
    List j;
    int k;
    int l;
    int m;
    private ca q;

    static {
        n = null;
        o = 1;
        p = new byte[1];
    }

    private bv(Context r3_Context) {
        this.a = 15000;
        this.b = 15000;
        this.c = 8000;
        this.d = 2;
        this.e = 300000;
        this.f = 2080;
        this.g = 600000;
        this.h = new ArrayList();
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = 300000;
        this.l = 7200000;
        this.m = 65536;
        er.a("AnsSetting", ">>>new AnsSetting()");
        this.q = new ca(r3_Context);
    }

    public static void a() {
        er.a("AnsSetting", ">>>AnsSetting \u9759\u6001\u6784\u9020\u51fd\u6570");
        SharedPreferences r0_SharedPreferences = GlobalContext.getContext().getSharedPreferences("ansetting", 0);
        if (r0_SharedPreferences.getInt("ver", 0) == 0) {
            er.a("AnsSetting", ">>>AnsSetting \u9759\u6001\u6784\u9020\u51fd\u6570. [\u5c06\u914d\u7f6e\u7248\u672c\u53f7\u5199\u5165\u672c\u5730:1]");
            Editor r0_Editor = r0_SharedPreferences.edit();
            r0_Editor.putInt("ver", 1);
            r0_Editor.commit();
        } else {
            o = r0_SharedPreferences.getInt("ver", 0);
        }
    }

    private void a(String r9_String) {
        try {
            JSONObject r1_JSONObject = new JSONObject(r9_String);
            this.c = r1_JSONObject.getInt("AnsPort");
            this.a = r1_JSONObject.getInt("AnsQuTo") * 1000;
            this.b = r1_JSONObject.getInt("TstSpTo") * 1000;
            this.d = r1_JSONObject.getInt("TsoRepMaxLinkErr");
            this.e = (long) (r1_JSONObject.getInt("ReportIntval") * 1000);
            this.f = r1_JSONObject.getInt("HttpOcTestPort");
            this.g = r1_JSONObject.getInt("DomainRetryTo") * 1000;
            o = r1_JSONObject.getInt("Ver");
            this.k = r1_JSONObject.getInt("MonitorReportInterval") * 1000;
            this.l = r1_JSONObject.getInt("ClearBufInterval") * 1000;
            this.m = r1_JSONObject.getInt("MaxBufLength");
            JSONArray r2_JSONArray = r1_JSONObject.getJSONArray("AnsList");
            Map r3_Map = new HashMap(3);
            int r1i = 0;
            while (r1i < r2_JSONArray.length()) {
                String r0_String = r2_JSONArray.getString(r1i);
                if (r0_String == null || r0_String.equals(RContactStorage.PRIMARY_KEY)) {
                    r1i++;
                } else {
                    String[] r0_StringA = r0_String.split("\\|");
                    if (r0_StringA.length == 2) {
                        short r4s = Short.valueOf(r0_StringA[1]).shortValue();
                        Object r5_Object = r0_StringA[0];
                        List r0_List = (List) r3_Map.get(Short.valueOf(r4s));
                        if (r0_List == null) {
                            r0_List = new ArrayList(3);
                            r3_Map.put(Short.valueOf(r4s), r0_List);
                        }
                        r0_List.add(r5_Object);
                    }
                    r1i++;
                }
            }
            n.a((List) r3_Map.get(Short.valueOf((short) 1)));
            n.b((List) r3_Map.get(Short.valueOf((short) 2)));
            n.c((List) r3_Map.get(Short.valueOf((short) 4)));
            this.q.b(this);
        } catch (JSONException e) {
            Throwable r0_Throwable = e;
            WnsClientLog.e("AnsSetting", r0_Throwable.getMessage(), r0_Throwable);
        }
    }

    public static void a(String r3_String, int r4i, int r5i) {
        er.a("AnsSetting", new StringBuilder(">>>\u62c9\u53d6ANS\u914d\u7f6e\uff1a[ip\uff1a").append(r3_String).append("][port:").append(r4i).append("][sVer:").append(r5i).append("]").toString());
        en.a.post(new bw(r3_String, r4i, c().a, r5i));
    }

    public static synchronized bv c() {
        bv r0_bv;
        int r0i = 0;
        synchronized (bv.class) {
            long r3j = System.currentTimeMillis();
            if (n == null) {
                String[] r5_StringA;
                int r6i;
                int r1i;
                String r7_String;
                n = new bv(GlobalContext.getContext());
                er.a("AnsSetting", new StringBuilder(">>>\u521d\u59cb\u5316ANS\u914d\u7f6e\u5b9e\u4f8b[").append(r3j).append("]").toString());
                n.b();
                if (n.h.size() == 0) {
                    r5_StringA = bz.a;
                    r6i = r5_StringA.length;
                    r1i = 0;
                    while (r1i < r6i) {
                        r7_String = r5_StringA[r1i];
                        er.a("AnsSetting", new StringBuilder(">>\u5b58\u50a8\u6ca1\u6709\u7535\u4fe1IP\u4fe1\u606f\uff0c\u521d\u59cb\u5316\u4e3a\u9ed8\u8ba4IP[ip:").append(r7_String).append("]").toString());
                        n.h.add(r7_String);
                        r1i++;
                    }
                }
                if (n.j.size() == 0) {
                    r5_StringA = bz.c;
                    r6i = r5_StringA.length;
                    r1i = 0;
                    while (r1i < r6i) {
                        r7_String = r5_StringA[r1i];
                        er.a("AnsSetting", new StringBuilder(">>>\u5b58\u50a8\u6ca1\u6709\u79fb\u52a8IP\u4fe1\u606f\uff0c\u521d\u59cb\u5316\u4e3a\u9ed8\u8ba4IP[ip:").append(r7_String).append("]").toString());
                        n.j.add(r7_String);
                        r1i++;
                    }
                }
                if (n.i.size() == 0) {
                    String[] r1_StringA = bz.b;
                    int r5i = r1_StringA.length;
                    while (r0i < r5i) {
                        String r6_String = r1_StringA[r0i];
                        er.a("AnsSetting", new StringBuilder(">>>\u5b58\u50a8\u6ca1\u6709\u8054\u901aIP\u4fe1\u606f\uff0c\u521d\u59cb\u5316\u4e3a\u9ed8\u8ba4IP[ip:").append(r6_String).append("]").toString());
                        n.i.add(r6_String);
                        r0i++;
                    }
                }
                er.a("AnsSetting", new StringBuilder(">>>\u521d\u59cb\u5316ANS\u914d\u7f6e\u5b9e\u4f8b\u7ed3\u675f,\u8017\u65f6\uff1a[").append(System.currentTimeMillis() - r3j).append("]").toString());
            }
            r0_bv = n;
        }
        return r0_bv;
    }

    public static void m() {
        if (n == null) {
            en.a.post(new bl());
        }
    }

    public static int n() {
        return o;
    }

    public void a(List r3_List) {
        synchronized (p) {
            if (r3_List != null) {
                if (r3_List.size() > 0) {
                    this.h.clear();
                    this.h.addAll(r3_List);
                }
            }
        }
    }

    public void b() {
        this.q.a(this);
    }

    public void b(List r3_List) {
        synchronized (p) {
            if (r3_List != null) {
                if (r3_List.size() > 0) {
                    this.i.clear();
                    this.i.addAll(r3_List);
                }
            }
        }
    }

    public void c(List r3_List) {
        synchronized (p) {
            if (r3_List != null) {
                if (r3_List.size() > 0) {
                    this.j.clear();
                    this.j.addAll(r3_List);
                }
            }
        }
    }

    public int d() {
        return this.b > 0 ? this.b : 15000;
    }

    public long e() {
        return (this.e > 0 ? 1 : (this.e == 0? 0 : -1)) > 0 ? this.e : AdViewManager.CONFIG_SERVER_LIMIT_MSTIME;
    }

    public int f() {
        return this.d > 0 ? this.d : XListViewHeader.STATE_REFRESHING;
    }

    public int g() {
        return this.f > 0 ? this.f : 2080;
    }

    public int h() {
        return this.g > 0 ? this.g : 600000;
    }

    public int i() {
        return this.k > 0 ? this.k : 300000;
    }

    public int j() {
        return this.l > 0 ? this.l : 7200000;
    }

    public int k() {
        return this.m > 0 ? this.m : Constants.FLAG_ACTIVITY_NO_ANIMATION;
    }

    public int l() {
        return this.a > 0 ? this.a : 15000;
    }

    public List o() {
        List r0_List;
        synchronized (p) {
            r0_List = this.h;
        }
        return r0_List;
    }

    public List p() {
        List r0_List;
        synchronized (p) {
            r0_List = this.i;
        }
        return r0_List;
    }

    public List q() {
        List r0_List;
        synchronized (p) {
            r0_List = this.j;
        }
        return r0_List;
    }
}