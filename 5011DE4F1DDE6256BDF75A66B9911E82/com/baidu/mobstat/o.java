package com.baidu.mobstat;

import com.baidu.mobstat.a.c;
import com.qq.e.v2.constants.Constants.KEYS;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class o {
    List<p> a;
    private long b;
    private long c;
    private int d;

    public o() {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.a = new ArrayList();
        a(System.currentTimeMillis());
    }

    public long a() {
        return this.b;
    }

    public void a(int r1i) {
        this.d = r1i;
    }

    public void a(long r1j) {
        this.b = r1j;
    }

    public void a(String r8_String, long r9j, long r11j) {
        this.a.add(new p(this, r8_String, r9j, r11j));
    }

    public void b() {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.a.clear();
        a(System.currentTimeMillis());
    }

    public void b(long r1j) {
        this.c = r1j;
    }

    public JSONObject c() {
        JSONObject r5_JSONObject = new JSONObject();
        try {
            r5_JSONObject.put("s", this.b);
            r5_JSONObject.put("e", this.c);
            r5_JSONObject.put("i", System.currentTimeMillis());
            r5_JSONObject.put(KEYS.CTXTSETTING, this.d);
            JSONArray r6_JSONArray = new JSONArray();
            int r4i = 0;
            while (r4i < this.a.size()) {
                JSONObject r7_JSONObject = new JSONObject();
                r7_JSONObject.put("n", ((p) this.a.get(r4i)).a());
                r7_JSONObject.put("d", ((p) this.a.get(r4i)).b());
                long r0j = ((p) this.a.get(r4i)).c() - this.b;
                if (r0j < 0) {
                    r0j = 0;
                }
                r7_JSONObject.put(KEYS.PLACEMENTS, r0j);
                r6_JSONArray.put(r7_JSONObject);
                r4i++;
            }
            r5_JSONObject.put("p", r6_JSONArray);
        } catch (JSONException e) {
            c.a("stat", "StatSession.constructJSONObject() failed");
        }
        return r5_JSONObject;
    }

    public int d() {
        return this.d;
    }
}