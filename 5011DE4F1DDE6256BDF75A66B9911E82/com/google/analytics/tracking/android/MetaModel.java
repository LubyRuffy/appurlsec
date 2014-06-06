package com.google.analytics.tracking.android;

import java.util.HashMap;
import java.util.Map;

class MetaModel {
    private Map<String, MetaInfo> a;

    public static interface Formatter {
        public String format(String r1_String);
    }

    public static class MetaInfo {
        private final String a;
        private final String b;
        private final com.google.analytics.tracking.android.MetaModel.Formatter c;

        public MetaInfo(String r1_String, String r2_String, com.google.analytics.tracking.android.MetaModel.Formatter r3_com_google_analytics_tracking_android_MetaModel_Formatter) {
            this.a = r1_String;
            this.b = r2_String;
            this.c = r3_com_google_analytics_tracking_android_MetaModel_Formatter;
        }

        public String getDefaultValue() {
            return this.b;
        }

        public com.google.analytics.tracking.android.MetaModel.Formatter getFormatter() {
            return this.c;
        }

        public String getUrlParam() {
            return this.a;
        }
    }

    MetaModel() {
        this.a = new HashMap();
    }

    MetaInfo a(String r4_String) {
        return r4_String.startsWith("&") ? new MetaInfo(r4_String.substring(1), null, null) : (MetaInfo) this.a.get(r4_String);
    }

    public void addField(String r3_String, String r4_String, String r5_String, Formatter r6_Formatter) {
        this.a.put(r3_String, new MetaInfo(r4_String, r5_String, r6_Formatter));
    }
}