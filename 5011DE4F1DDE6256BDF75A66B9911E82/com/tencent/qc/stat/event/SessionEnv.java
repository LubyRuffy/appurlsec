package com.tencent.qc.stat.event;

import android.content.Context;
import com.tencent.qc.stat.common.Env;
import org.json.JSONObject;

// compiled from: ProGuard
public class SessionEnv extends Event {
    private Env a;
    private JSONObject g;

    public SessionEnv(Context r2_Context, int r3i, JSONObject r4_JSONObject) {
        super(r2_Context, r3i);
        this.g = null;
        this.a = new Env(r2_Context);
        this.g = r4_JSONObject;
    }

    public EventType a() {
        return EventType.b;
    }

    public boolean a(JSONObject r3_JSONObject) {
        r3_JSONObject.put("ut", this.e.c());
        if (this.g != null) {
            r3_JSONObject.put("cfg", this.g);
        }
        this.a.a(r3_JSONObject);
        return true;
    }
}