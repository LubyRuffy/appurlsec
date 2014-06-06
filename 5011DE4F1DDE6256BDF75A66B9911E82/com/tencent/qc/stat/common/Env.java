package com.tencent.qc.stat.common;

import android.content.Context;
import org.json.JSONObject;

// compiled from: ProGuard
public class Env {
    static c a;
    private static JSONObject d;
    Integer b;
    String c;

    static {
        d = null;
    }

    public Env(Context r2_Context) {
        this.b = null;
        this.c = null;
        a(r2_Context);
        this.b = StatCommonHelper.o(r2_Context.getApplicationContext());
        this.c = StatCommonHelper.n(r2_Context);
    }

    static c a(Context r3_Context) {
        if (a == null) {
            a = new c(r3_Context.getApplicationContext(), null);
        }
        return a;
    }

    public void a(JSONObject r4_JSONObject) {
        JSONObject r0_JSONObject = new JSONObject();
        if (a != null) {
            a.a(r0_JSONObject);
        }
        StatCommonHelper.a(r0_JSONObject, "cn", this.c);
        if (this.b != null) {
            r0_JSONObject.put("tn", this.b);
        }
        r4_JSONObject.put("ev", r0_JSONObject);
        if (d == null || d.length() <= 0) {
        } else {
            r4_JSONObject.put("eva", d);
        }
    }
}