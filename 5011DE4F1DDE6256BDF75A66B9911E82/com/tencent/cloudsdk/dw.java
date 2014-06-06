package com.tencent.cloudsdk;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.tauth.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.cordova.Globalization;

// compiled from: SourceFile
public class dw implements dk {
    private du a;
    private dt b;
    private eb c;
    private dr d;

    public dw(du r2_du, dt r3_dt, eb r4_eb) {
        this.a = r2_du;
        this.b = r3_dt;
        this.c = r4_eb;
        this.d = new dr();
    }

    private Bundle a(dv r10_dv, boolean r11z) {
        int r0i = 1;
        int r1i = 0;
        Bundle r4_Bundle = new Bundle(40);
        r4_Bundle.putInt(Constants.PARAM_TYPE_ID, 1000200);
        r4_Bundle.putLong(Globalization.TIME, r10_dv.d / 1000);
        r4_Bundle.putString("dt", Build.MODEL);
        r4_Bundle.putString("dm", r10_dv.a);
        r4_Bundle.putInt("mt", 1);
        r4_Bundle.putString("sv", VERSION.RELEASE);
        String r2_String = "rm";
        if (r11z) {
            r0i = 0;
        }
        r4_Bundle.putInt(r2_String, r0i);
        r4_Bundle.putLong("ip", r10_dv.b);
        r4_Bundle.putInt("ipt", r10_dv.c);
        r4_Bundle.putInt("ct", r10_dv.e);
        r4_Bundle.putInt("cf", r10_dv.f);
        r4_Bundle.putInt("rt", r10_dv.g);
        r4_Bundle.putInt(KEYS.Banner_RF, r10_dv.h);
        r4_Bundle.putInt("st", r10_dv.t);
        r4_Bundle.putInt("sf", r10_dv.u);
        if (!r11z) {
            r4_Bundle.putInt("rt0", r10_dv.i);
            r0i = r10_dv.i;
            r4_Bundle.putInt("rl0", r0i <= 0 ? 0 : r10_dv.j / r0i);
            r4_Bundle.putInt("st0", r10_dv.v);
            r4_Bundle.putInt("sf0", r10_dv.w);
            r0i = r10_dv.v - r10_dv.w;
            r4_Bundle.putInt("sl0", r0i <= 0 ? 0 : r10_dv.x / r0i);
            r4_Bundle.putInt("rt1", r10_dv.k);
            r0i = r10_dv.k;
            r4_Bundle.putInt("rl1", r0i <= 0 ? 0 : r10_dv.l / r0i);
            r4_Bundle.putInt("st1", r10_dv.y);
            r4_Bundle.putInt("sf1", r10_dv.z);
            r0i = r10_dv.y - r10_dv.z;
            r4_Bundle.putInt("sl1", r0i <= 0 ? 0 : r10_dv.A / r0i);
            r4_Bundle.putInt("rt2", r10_dv.m);
            r0i = r10_dv.m;
            r4_Bundle.putInt("rl2", r0i <= 0 ? 0 : r10_dv.n / r0i);
            r4_Bundle.putInt("st2", r10_dv.B);
            r4_Bundle.putInt("sf2", r10_dv.C);
            r0i = r10_dv.B - r10_dv.C;
            r4_Bundle.putInt("sl2", r0i <= 0 ? 0 : r10_dv.D / r0i);
            r4_Bundle.putInt("rt3", r10_dv.o);
            r0i = r10_dv.o;
            r4_Bundle.putInt("rl3", r0i <= 0 ? 0 : r10_dv.p / r0i);
            r4_Bundle.putInt("st3", r10_dv.E);
            r4_Bundle.putInt("sf3", r10_dv.F);
            r0i = r10_dv.E - r10_dv.F;
            r4_Bundle.putInt("sl3", r0i <= 0 ? 0 : r10_dv.G / r0i);
            r4_Bundle.putInt("rt4", r10_dv.q);
            int r2i = r10_dv.q;
            r4_Bundle.putInt("rl4", r2i <= 0 ? 0 : r10_dv.s / r2i);
            r4_Bundle.putLong("rs4", r2i <= 0 ? 0 : r10_dv.r / ((long) r2i));
            r4_Bundle.putInt("st4", r10_dv.H);
            r4_Bundle.putInt("sf4", r10_dv.I);
            r0i = r10_dv.H - r10_dv.I;
            r2_String = "sl4";
            if (r0i <= 0) {
                r4_Bundle.putInt(r2_String, r1i);
            } else {
                r1i = r10_dv.J / r0i;
                r4_Bundle.putInt(r2_String, r1i);
            }
        }
        return r4_Bundle;
    }

    private void a(boolean r8z) {
        List r1_List = new ArrayList();
        Map r0_Map = this.a.b();
        if (r0_Map == null || r0_Map.size() <= 0) {
        } else {
            Iterator r2_Iterator = new LinkedHashMap(r0_Map).entrySet().iterator();
            while (r2_Iterator.hasNext()) {
                dv r0_dv = (dv) ((Entry) r2_Iterator.next()).getValue();
                if (System.currentTimeMillis() - r0_dv.d <= this.b.c()) {
                    r1_List.add(a(r0_dv, r8z));
                }
            }
            er.a("TcpStatisticsReportImp", new StringBuilder("\u4e0a\u62a5\u5e76\u6e05\u7a7aTCP\u7edf\u8ba1\u6570\u636e\uff1a").append(r1_List).toString());
            this.a.a();
            this.c.b(r1_List, this.d);
        }
    }

    public void a() {
        this.a.a();
    }

    public void b() {
        a(false);
    }

    public void c() {
        a(true);
    }
}