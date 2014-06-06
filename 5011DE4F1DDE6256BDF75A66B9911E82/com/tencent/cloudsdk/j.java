package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;

// compiled from: SourceFile
public class j {
    public int a;
    public String b;

    public j() {
        this.a = 0;
        this.b = RContactStorage.PRIMARY_KEY;
    }

    public JSONObject a() {
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put(QsbkDatabase.TYPE, this.a);
            r1_JSONObject.put("md5", this.b);
        } catch (JSONException e) {
            WnsClientLog.e("PluginData", new StringBuilder(">>> toJsonObject() E\uff1a").append(e.toString()).toString());
        }
        return r1_JSONObject;
    }
}