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
public class dp implements dk {
    private dn a;
    private dt b;
    private eb c;
    private dr d;

    public dp(dn r2_dn, dt r3_dt, eb r4_eb) {
        this.a = r2_dn;
        this.b = r3_dt;
        this.c = r4_eb;
        this.d = new dr();
    }

    private Bundle a(do_ r10_do_, boolean r11z) {
        int r0i = 1;
        long r2j = 0;
        Bundle r4_Bundle = new Bundle(40);
        r4_Bundle.putInt(Constants.PARAM_TYPE_ID, 1000201);
        r4_Bundle.putLong(Globalization.TIME, r10_do_.d / 1000);
        r4_Bundle.putString("dt", Build.MODEL);
        r4_Bundle.putString("dm", r10_do_.a);
        r4_Bundle.putInt("mt", 1);
        r4_Bundle.putString("sv", VERSION.RELEASE);
        String r1_String = "rm";
        if (r11z) {
            r0i = 0;
        }
        r4_Bundle.putInt(r1_String, r0i);
        r4_Bundle.putLong("ip", r10_do_.b);
        r4_Bundle.putInt("ipt", r10_do_.c);
        r4_Bundle.putInt("rt", r10_do_.e);
        r4_Bundle.putInt(KEYS.Banner_RF, r10_do_.f);
        if (!r11z) {
            r4_Bundle.putInt("rt0", r10_do_.g);
            r4_Bundle.putInt("rf0", r10_do_.h);
            r0i = r10_do_.g - r10_do_.h;
            r4_Bundle.putLong("rl0", r0i <= 0 ? 0 : r10_do_.i / ((long) r0i));
            r4_Bundle.putInt("rt1", r10_do_.j);
            r4_Bundle.putInt("rf1", r10_do_.k);
            r0i = r10_do_.j - r10_do_.k;
            r4_Bundle.putLong("rl1", r0i <= 0 ? 0 : r10_do_.l / ((long) r0i));
            r4_Bundle.putInt("rt2", r10_do_.m);
            r4_Bundle.putInt("rf2", r10_do_.n);
            r0i = r10_do_.m - r10_do_.n;
            r4_Bundle.putLong("rl2", r0i <= 0 ? 0 : r10_do_.o / ((long) r0i));
            r4_Bundle.putInt("rt3", r10_do_.p);
            r4_Bundle.putInt("rf3", r10_do_.q);
            r0i = r10_do_.p - r10_do_.q;
            r4_Bundle.putLong("rl3", r0i <= 0 ? 0 : r10_do_.r / ((long) r0i));
            r4_Bundle.putInt("rt4", r10_do_.s);
            r4_Bundle.putInt("rf4", r10_do_.t);
            r0i = r10_do_.s - r10_do_.t;
            r1_String = "rl4";
            if (r0i <= 0) {
                r4_Bundle.putLong(r1_String, r2j);
            } else {
                r2j = r10_do_.u / ((long) r0i);
                r4_Bundle.putLong(r1_String, r2j);
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
                do_ r0_do_ = (do_) ((Entry) r2_Iterator.next()).getValue();
                if (System.currentTimeMillis() - r0_do_.d <= this.b.c()) {
                    r1_List.add(a(r0_do_, r8z));
                }
            }
            er.a("HttpStatisticsReportImp", new StringBuilder("\u4e0a\u62a5\u5e76\u6e05\u7a7aHTTP\u7edf\u8ba1\u6570\u636e\uff1a").append(r1_List).toString());
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