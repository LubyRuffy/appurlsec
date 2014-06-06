package com.baidu.kirin.d;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    public static String a(String r8_String) {
        int r0i;
        try {
            int r0i_2;
            JSONObject r3_JSONObject = new JSONObject(r8_String);
            String[] r4_StringA = new String[r3_JSONObject.length()];
            Iterator r5_Iterator = r3_JSONObject.keys();
            int r1i = 0;
            while (r5_Iterator.hasNext()) {
                String r0_String = RContactStorage.PRIMARY_KEY;
                r0_String = (String) r5_Iterator.next();
                try {
                    r4_StringA[r1i] = r0_String + ":" + r3_JSONObject.getString(r0_String);
                    r0i_2 = r1i + 1;
                } catch (JSONException e) {
                    e.printStackTrace();
                    r0i_2 = r1i;
                }
                r1i = r0i_2;
            }
            Arrays.sort(r4_StringA);
            String r1_String = r4_StringA[0];
            r0i = 1;
            while (r0i < r4_StringA.length) {
                r1_String = r1_String + ", " + r4_StringA[r0i];
                r0i++;
            }
            return r1_String;
        } catch (JSONException e_2) {
            d.b("String to JSONObject error, return desc string!");
            e_2.printStackTrace();
            return r8_String;
        }
    }

    public static JSONObject a(JSONObject r4_JSONObject, JSONObject r5_JSONObject) {
        String r0_String;
        JSONObject r1_JSONObject = new JSONObject();
        Iterator r2_Iterator = r4_JSONObject.keys();
        while (r2_Iterator.hasNext()) {
            r0_String = (String) r2_Iterator.next();
            try {
                r1_JSONObject.put(r0_String, r4_JSONObject.getString(r0_String));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        r2_Iterator = r5_JSONObject.keys();
        while (r2_Iterator.hasNext()) {
            r0_String = (String) r2_Iterator.next();
            try {
                r1_JSONObject.put(r0_String, r5_JSONObject.getString(r0_String));
            } catch (JSONException e_2) {
                e_2.printStackTrace();
            }
        }
        return r1_JSONObject;
    }
}