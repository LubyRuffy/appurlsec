package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import org.json.JSONObject;

// compiled from: SourceFile
public class o {
    public int a;
    public JSONObject b;

    public o() {
        this.a = 0;
        this.b = null;
    }

    public boolean a(JSONObject r6_JSONObject) {
        if (r6_JSONObject == null) {
            return false;
        }
        try {
            if (!r6_JSONObject.isNull("code")) {
                this.a = r6_JSONObject.getInt("code");
            }
            if (!r6_JSONObject.isNull("data")) {
                this.b = r6_JSONObject.getJSONObject("data");
            }
            return true;
        } catch (Exception e) {
            WnsClientLog.e("ResponseWrapper", new StringBuilder(">>> parseJsonObject() E: ").append(e.toString()).toString());
            return false;
        }
    }
}