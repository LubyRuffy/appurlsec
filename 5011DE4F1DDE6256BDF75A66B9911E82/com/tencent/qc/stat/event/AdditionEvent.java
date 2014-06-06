package com.tencent.qc.stat.event;

import android.content.Context;
import com.tencent.qc.stat.StatConfig;
import com.tencent.qc.stat.common.StatCommonHelper;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;
import qsbk.app.thirdparty.ThirdPartyConstants;

// compiled from: ProGuard
public class AdditionEvent extends Event {
    Map a;

    public AdditionEvent(Context r2_Context, int r3i, Map r4_Map) {
        super(r2_Context, r3i);
        this.a = null;
        this.a = r4_Map;
    }

    public EventType a() {
        return EventType.e;
    }

    public boolean a(JSONObject r4_JSONObject) {
        StatCommonHelper.a(r4_JSONObject, ThirdPartyConstants.THIRDPARTY_TYLE_QQ, StatConfig.k());
        if (this.a == null || this.a.size() <= 0) {
            return true;
        }
        Iterator r2_Iterator = this.a.entrySet().iterator();
        while (r2_Iterator.hasNext()) {
            Entry r0_Entry = (Entry) r2_Iterator.next();
            r4_JSONObject.put((String) r0_Entry.getKey(), r0_Entry.getValue());
        }
        return true;
    }
}