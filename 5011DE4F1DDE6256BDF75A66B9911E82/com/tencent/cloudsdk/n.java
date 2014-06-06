package com.tencent.cloudsdk;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: SourceFile
public class n {
    public h[] a;
    public int b;
    public int c;

    public n() {
        this.a = null;
        this.b = 0;
        this.c = 0;
    }

    public boolean a(JSONObject r7_JSONObject) {
        boolean r0z;
        if (r7_JSONObject != null) {
            if (!r7_JSONObject.isNull("urls")) {
                JSONArray r2_JSONArray = r7_JSONObject.getJSONArray("urls");
                List r3_List = new ArrayList();
                int r0i = 0;
                while (r0i < r2_JSONArray.length()) {
                    h r4_h = new h();
                    r4_h.a(r2_JSONArray.getJSONObject(r0i));
                    r3_List.add(r4_h);
                    r0i++;
                }
                this.a = (h[]) r3_List.toArray(new h[0]);
            }
            if (!r7_JSONObject.isNull("interval")) {
                this.b = r7_JSONObject.getInt("interval");
            }
            if (!r7_JSONObject.isNull("allowNetType")) {
                this.c = r7_JSONObject.getInt("allowNetType");
            }
            r0z = true;
        } else {
            r0z = false;
        }
        return r0z;
    }
}