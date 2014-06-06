package com.google.analytics.tracking.android;

import android.text.TextUtils;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.List;

// compiled from: NoopDispatcher.java
class ad implements g {
    ad() {
    }

    public int dispatchHits(List<w> r7_List_w) {
        if (r7_List_w == null) {
            return 0;
        }
        z.e("Hits not actually being sent as dispatch is false...");
        int r2i = Math.min(r7_List_w.size(), 40);
        int r3i = 0;
        while (r3i < r2i) {
            if (z.a()) {
                String r0_String;
                String r1_String;
                r0_String = TextUtils.isEmpty(((w) r7_List_w.get(r3i)).a()) ? RContactStorage.PRIMARY_KEY : x.a((w) r7_List_w.get(r3i), System.currentTimeMillis());
                if (TextUtils.isEmpty(r0_String)) {
                    r1_String = "Hit couldn't be read, wouldn't be sent:";
                } else if (r0_String.length() <= 2036) {
                    r1_String = "GET would be sent:";
                } else if (r0_String.length() > 8192) {
                    r1_String = "Would be too big:";
                } else {
                    r1_String = "POST would be sent:";
                }
                z.e(r1_String + r0_String);
            }
            r3i++;
        }
        return r2i;
    }

    public boolean okToDispatch() {
        return true;
    }
}