package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: SourceFile
public class m {
    public String a;
    public JSONObject b;

    public m() {
        this.a = RContactStorage.PRIMARY_KEY;
        this.b = null;
    }

    public JSONObject a() {
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put("api", this.a);
            r1_JSONObject.put("para", this.b);
        } catch (JSONException e) {
            WnsClientLog.e("RequestWrapper", new StringBuilder(">>> toJsonObject() E: ").append(e.toString()).toString());
        }
        return r1_JSONObject;
    }
}