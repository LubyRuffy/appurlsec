package com.tencent.qc.stat.event;

import android.content.Context;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import java.util.Arrays;
import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: ProGuard
public class CustomEvent extends Event {
    protected Key a;
    private long g;

    // compiled from: ProGuard
    public class Key {
        String a;
        String[] b;
        Properties c;

        public Key() {
            this.c = null;
        }

        public boolean equals(Object r5_Object) {
            if (this == r5_Object) {
                return true;
            }
            if (!(r5_Object instanceof com.tencent.qc.stat.event.CustomEvent.Key)) {
                return false;
            }
            int r0i;
            com.tencent.qc.stat.event.CustomEvent.Key r5_com_tencent_qc_stat_event_CustomEvent_Key = (com.tencent.qc.stat.event.CustomEvent.Key) r5_Object;
            r0i = (this.a.equals(r5_com_tencent_qc_stat_event_CustomEvent_Key.a) && Arrays.equals(this.b, r5_com_tencent_qc_stat_event_CustomEvent_Key.b)) ? 1 : 0;
            if (this.c != null) {
                return r0i != 0 && this.c.equals(r5_com_tencent_qc_stat_event_CustomEvent_Key.c);
            } else {
                if (r0i == 0 || r5_com_tencent_qc_stat_event_CustomEvent_Key.c != null) {
                    return false;
                }
                return true;
            }
        }

        public int hashCode() {
            int r0i = 0;
            if (this.a != null) {
                r0i = this.a.hashCode();
            }
            if (this.b != null) {
                r0i ^= Arrays.hashCode(this.b);
            }
            return this.c != null ? r0i ^ this.c.hashCode() : r0i;
        }

        public String toString() {
            String r2_String = this.a;
            String r0_String = RContactStorage.PRIMARY_KEY;
            if (this.b == null || this.b.length <= 0) {
                if (this.c == null || this.c.size() <= 0) {
                    return r2_String + r0_String;
                }
                r0_String = r0_String + this.c.toString();
                return r2_String + r0_String;
            } else {
                String r1_String = this.b[0];
                int r0i = 1;
                while (r0i < this.b.length) {
                    r1_String = r1_String + "," + this.b[r0i];
                    r0i++;
                }
                r0_String = "[" + r1_String + "]";
                if (this.c == null || this.c.size() <= 0) {
                    return r2_String + r0_String;
                }
                r0_String = r0_String + this.c.toString();
                return r2_String + r0_String;
            }
        }
    }

    public CustomEvent(Context r3_Context, int r4i, String r5_String) {
        super(r3_Context, r4i);
        this.a = new Key();
        this.g = -1;
        this.a.a = r5_String;
    }

    public EventType a() {
        return EventType.d;
    }

    public void a(String[] r2_StringA) {
        this.a.b = r2_StringA;
    }

    public boolean a(JSONObject r6_JSONObject) {
        r6_JSONObject.put("ei", this.a.a);
        if (this.g > 0) {
            r6_JSONObject.put("du", this.g);
        }
        if (this.a.b != null) {
            JSONArray r1_JSONArray = new JSONArray();
            String[] r2_StringA = this.a.b;
            int r3i = r2_StringA.length;
            int r0i = 0;
            while (r0i < r3i) {
                r1_JSONArray.put(r2_StringA[r0i]);
                r0i++;
            }
            r6_JSONObject.put(LocaleUtil.ARABIC, r1_JSONArray);
        }
        if (this.a.c != null) {
            r6_JSONObject.put("kv", new JSONObject(this.a.c));
        }
        return true;
    }
}