package com.qq.e.v2.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
    public static JSONArray bytesToJsonArray(byte[] r3_byteA) {
        JSONArray r1_JSONArray = new JSONArray();
        if (r3_byteA != null) {
            int r0i = 0;
            while (r0i < r3_byteA.length) {
                r1_JSONArray.put(r3_byteA[r0i]);
                r0i++;
            }
        }
        return r1_JSONArray;
    }

    public static byte[] jsonArrayToBytes(JSONArray r4_JSONArray) {
        if (r4_JSONArray == null) {
            return null;
        }
        int r3i = r4_JSONArray.length();
        byte[] r0_byteA = new byte[r3i];
        int r2i = 0;
        while (r2i < r3i) {
            try {
                r0_byteA[r2i] = (byte) r4_JSONArray.getInt(r2i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            r2i++;
        }
        return r0_byteA;
    }

    public static void jsonToMap(JSONObject r3_JSONObject, Map<String, Object> r4_Map_String__Object) throws JSONException {
        if (r3_JSONObject == null || r4_Map_String__Object == null) {
        } else {
            Iterator r1_Iterator = r3_JSONObject.keys();
            while (r1_Iterator.hasNext()) {
                String r0_String = (String) r1_Iterator.next();
                r4_Map_String__Object.put(r0_String, r3_JSONObject.get(r0_String));
            }
        }
    }

    public static JSONObject mapToJson(Map<?, ?> r5_Map___) {
        JSONObject r2_JSONObject = new JSONObject();
        try {
            Iterator r3_Iterator = r5_Map___.entrySet().iterator();
            while (r3_Iterator.hasNext()) {
                Entry r0_Entry = (Entry) r3_Iterator.next();
                Object r1_Object = r0_Entry.getKey();
                if (r1_Object instanceof String) {
                    String r1_String = (String) r1_Object;
                    Object r0_Object = r0_Entry.getValue();
                    if (r0_Object instanceof Map) {
                        r2_JSONObject.put(r1_String, mapToJson((Map) r0_Object));
                    } else if (r0_Object instanceof Collection) {
                        r2_JSONObject.put(r1_String, new JSONArray((Collection) r0_Object));
                    } else {
                        r2_JSONObject.putOpt(r1_String, r0_Object);
                    }
                }
            }
        } catch (JSONException e) {
            GDTLogger.e("build Json error", e);
        }
        return r2_JSONObject;
    }
}