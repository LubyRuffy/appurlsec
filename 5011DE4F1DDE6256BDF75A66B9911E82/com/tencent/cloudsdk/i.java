package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Constants;
import org.apache.cordova.Globalization;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;

// compiled from: SourceFile
public class i {
    public int a;
    public String b;
    public String c;
    public int d;
    public int e;

    public i() {
        this.a = 0;
        this.b = RContactStorage.PRIMARY_KEY;
        this.c = RContactStorage.PRIMARY_KEY;
        this.d = 0;
        this.e = 0;
    }

    public JSONObject a() {
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put(QsbkDatabase.TYPE, this.a);
            r1_JSONObject.put("md5", this.b);
            r1_JSONObject.put(Constants.PARAM_URL, this.c);
            r1_JSONObject.put(Globalization.TIME, this.d);
            r1_JSONObject.put("code", this.e);
        } catch (JSONException e) {
            WnsClientLog.e("DownloadReportData", new StringBuilder(">>> toJsonObject E: ").append(e.toString()).toString());
        }
        return r1_JSONObject;
    }
}