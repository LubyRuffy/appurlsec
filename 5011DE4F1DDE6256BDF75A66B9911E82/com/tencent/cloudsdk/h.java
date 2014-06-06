package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Constants;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;

// compiled from: SourceFile
public class h {
    public int a;
    public String b;
    public String c;
    public long d;

    public h() {
        this.a = 0;
        this.b = RContactStorage.PRIMARY_KEY;
        this.c = RContactStorage.PRIMARY_KEY;
        this.d = 0;
    }

    public boolean a(JSONObject r6_JSONObject) {
        if (r6_JSONObject == null) {
            return false;
        }
        try {
            if (!r6_JSONObject.isNull(QsbkDatabase.TYPE)) {
                this.a = r6_JSONObject.getInt(QsbkDatabase.TYPE);
            }
            if (!r6_JSONObject.isNull("md5")) {
                this.b = r6_JSONObject.getString("md5");
            }
            if (!r6_JSONObject.isNull(Constants.PARAM_URL)) {
                this.c = r6_JSONObject.getString(Constants.PARAM_URL);
            }
            if (!r6_JSONObject.isNull("len")) {
                this.d = r6_JSONObject.getLong("len");
            }
            return true;
        } catch (JSONException e) {
            WnsClientLog.e("DownloadPluginData", new StringBuilder(">>> parseJsonObject() E\uff1a").append(e.toString()).toString());
            return false;
        }
    }
}