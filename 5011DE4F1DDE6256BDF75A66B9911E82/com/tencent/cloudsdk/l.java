package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: SourceFile
public class l {
    public p a;
    public i[] b;

    public l() {
        this.a = null;
        this.b = null;
    }

    public JSONObject a() {
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put("userinfo", this.a.a());
            JSONArray r2_JSONArray = new JSONArray();
            i[] r3_iA = this.b;
            int r4i = r3_iA.length;
            int r0i = 0;
            while (r0i < r4i) {
                r2_JSONArray.put(r3_iA[r0i].a());
                r0i++;
            }
            r1_JSONObject.put("info", r2_JSONArray);
        } catch (JSONException e) {
            WnsClientLog.e("ReqReport", new StringBuilder(">>> toJsonObject() E: ").append(e.toString()).toString());
        }
        return r1_JSONObject;
    }
}