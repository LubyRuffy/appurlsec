package com.crashlytics.android.internal;

import com.qiubai.library.adview.util.AdViewUtil;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.bean.Base;
import qsbk.app.database.QsbkDatabase;

// compiled from: SourceFile
final class bh {
    bh() {
    }

    private static JSONObject a(Map<String, String> r4_Map_String__String) throws JSONException {
        JSONObject r2_JSONObject = new JSONObject();
        Iterator r3_Iterator = r4_Map_String__String.entrySet().iterator();
        while (r3_Iterator.hasNext()) {
            Entry r0_Entry = (Entry) r3_Iterator.next();
            r2_JSONObject.put((String) r0_Entry.getKey(), r0_Entry.getValue());
        }
        return r2_JSONObject;
    }

    public final byte[] a(bf r5_bf) throws IOException {
        try {
            JSONObject r0_JSONObject = new JSONObject();
            r0_JSONObject.put("appBundleId", r5_bf.a);
            r0_JSONObject.put("executionId", r5_bf.b);
            r0_JSONObject.put("installationId", r5_bf.c);
            r0_JSONObject.put("androidId", r5_bf.d);
            r0_JSONObject.put("osVersion", r5_bf.e);
            r0_JSONObject.put("deviceModel", r5_bf.f);
            r0_JSONObject.put("appVersionCode", r5_bf.g);
            r0_JSONObject.put("appVersionName", r5_bf.h);
            r0_JSONObject.put(AdViewUtil.PREFS_STRING_TIMESTAMP, r5_bf.i);
            r0_JSONObject.put(QsbkDatabase.TYPE, r5_bf.j.toString());
            r0_JSONObject.put("details", a(r5_bf.k));
            return r0_JSONObject.toString().getBytes(Base.UTF8);
        } catch (JSONException e) {
            throw new IOException(e.getMessage());
        }
    }
}