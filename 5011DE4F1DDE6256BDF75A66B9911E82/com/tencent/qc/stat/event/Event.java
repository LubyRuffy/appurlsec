package com.tencent.qc.stat.event;

import android.content.Context;
import android.util.Log;
import com.tencent.qc.stat.StatConfig;
import com.tencent.qc.stat.StatStore;
import com.tencent.qc.stat.common.StatCommonHelper;
import com.tencent.qc.stat.common.User;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public abstract class Event {
    protected String b;
    protected long c;
    protected int d;
    protected User e;
    protected Context f;

    Event(Context r5_Context, int r6i) {
        this.e = null;
        this.f = r5_Context;
        this.b = StatConfig.a(r5_Context);
        this.c = System.currentTimeMillis() / 1000;
        this.d = r6i;
        this.e = StatStore.a(r5_Context).b(r5_Context);
    }

    public abstract EventType a();

    public abstract boolean a(JSONObject r1_JSONObject);

    public long b() {
        return this.c;
    }

    public boolean b(JSONObject r4_JSONObject) {
        try {
            StatCommonHelper.a(r4_JSONObject, "ky", this.b);
            r4_JSONObject.put("et", a().a());
            r4_JSONObject.put("ui", this.e.a());
            StatCommonHelper.a(r4_JSONObject, "mc", this.e.b());
            r4_JSONObject.put("si", this.d);
            r4_JSONObject.put("ts", this.c);
            return a(r4_JSONObject);
        } catch (JSONException e) {
            Log.e("Event", "Failed to encode", e);
            return false;
        }
    }

    public Context c() {
        return this.f;
    }

    public String d() {
        JSONObject r0_JSONObject = new JSONObject();
        b(r0_JSONObject);
        return r0_JSONObject.toString();
    }
}