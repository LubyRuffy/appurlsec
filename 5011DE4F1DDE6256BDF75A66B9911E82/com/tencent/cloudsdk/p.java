package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: SourceFile
public class p {
    public String a;
    public String b;
    public int c;
    public String d;
    public int e;

    public p() {
        this.a = RContactStorage.PRIMARY_KEY;
        this.b = RContactStorage.PRIMARY_KEY;
        this.c = 0;
        this.d = RContactStorage.PRIMARY_KEY;
        this.e = 0;
    }

    public JSONObject a() {
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put("ccv", this.a);
            r1_JSONObject.put("imei", this.b);
            r1_JSONObject.put("cid", this.c);
            r1_JSONObject.put("android", this.d);
            r1_JSONObject.put("netType", this.e);
        } catch (JSONException e) {
            WnsClientLog.e("UserInfo", new StringBuilder(">>> toJsonObject() E: ").append(e.toString()).toString());
        }
        return r1_JSONObject;
    }
}