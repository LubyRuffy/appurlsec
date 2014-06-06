package com.qq.e.v2.managers.setting;

import com.qq.e.v2.util.GDTLogger;
import com.qq.e.v2.util.JsonUtil;
import com.qq.e.v2.util.StringUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private Map<String, Object> a;

    public b() {
        this.a = new ConcurrentHashMap();
    }

    public b(String r3_String) {
        this();
        GDTLogger.d(new StringBuilder("Initialize GDTSDKSetting,Json=").append(r3_String).toString());
        if (!StringUtil.isEmpty(r3_String)) {
            try {
                JsonUtil.jsonToMap(new JSONObject(r3_String), this.a);
            } catch (JSONException e) {
                GDTLogger.report("Exception while building GDTSDKSetting from json", e);
            }
        }
    }

    final Object a(String r2_String) {
        return this.a.get(r2_String);
    }

    final void a(String r2_String, Object r3_Object) {
        this.a.put(r2_String, r3_Object);
    }

    public String toString() {
        return new StringBuilder("GDTSDKSetting[").append(this.a.toString()).append("]").toString();
    }
}