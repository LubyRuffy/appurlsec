package com.google.analytics.tracking.android;

import com.google.analytics.tracking.android.MetaModel.MetaInfo;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import qsbk.app.bean.Base;

// compiled from: HitBuilder.java
class x {
    static String a(w r5_w, long r6j) {
        long r3j = 0;
        StringBuilder r0_StringBuilder = new StringBuilder();
        r0_StringBuilder.append(r5_w.a());
        if (r5_w.c() > 0) {
            long r1j = r6j - r5_w.c();
            if (r1j >= r3j) {
                r0_StringBuilder.append("&").append(ModelFields.QUEUE_TIME).append("=").append(r1j);
            }
        }
        r0_StringBuilder.append("&").append(ModelFields.CACHE_BUSTER).append("=").append(r5_w.b());
        return r0_StringBuilder.toString();
    }

    public static String encode(String r3_String) {
        try {
            return URLEncoder.encode(r3_String, Base.UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("URL encoding failed for: " + r3_String);
        }
    }

    public static Map<String, String> generateHitParams(MetaModel r6_MetaModel, Map<String, String> r7_Map_String__String) {
        Map<String, String> r2_Map_String__String = new HashMap();
        Iterator r3_Iterator = r7_Map_String__String.entrySet().iterator();
        while (r3_Iterator.hasNext()) {
            Entry r0_Entry = (Entry) r3_Iterator.next();
            MetaInfo r1_MetaInfo = r6_MetaModel.a((String) r0_Entry.getKey());
            if (r1_MetaInfo != null) {
                String r4_String = r1_MetaInfo.getUrlParam();
                if (r4_String != null) {
                    String r0_String = (String) r0_Entry.getValue();
                    if (r1_MetaInfo.getFormatter() != null) {
                        r0_String = r1_MetaInfo.getFormatter().format(r0_String);
                    }
                    if (r0_String == null || r0_String.equals(r1_MetaInfo.getDefaultValue())) {
                    } else {
                        r2_Map_String__String.put(r4_String, r0_String);
                    }
                }
            }
        }
        return r2_Map_String__String;
    }
}