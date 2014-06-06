package com.baidu.kirin.d;

import org.json.JSONException;
import org.json.JSONObject;

public class e {
    public boolean a;
    public int b;
    public String c;
    public JSONObject d;

    public e() {
        this.d = null;
    }

    public int a() {
        return this.b;
    }

    public void a(int r1i) {
        this.b = r1i;
    }

    public void a(String r4_String) {
        this.c = r4_String;
        try {
            this.d = new JSONObject(this.c);
        } catch (JSONException e) {
            d.c("message to JSONObject error!! msg[" + this.c + "]");
            e.printStackTrace();
        }
    }

    public void a(boolean r1z) {
        this.a = r1z;
    }

    public boolean b() {
        return this.a;
    }

    public String c() {
        return this.c;
    }

    public JSONObject d() {
        return this.d != null ? this.d : null;
    }
}