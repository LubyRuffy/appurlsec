package com.tencent.qc.stat;

import com.tencent.mm.sdk.contact.RContactStorage;
import org.json.JSONObject;

// compiled from: ProGuard
class r {
    int a;
    JSONObject b;
    String c;
    int d;

    public r(int r2i) {
        this.b = new JSONObject();
        this.c = RContactStorage.PRIMARY_KEY;
        this.d = 0;
        this.a = r2i;
    }

    String a() {
        return this.b.length() == 0 ? RContactStorage.PRIMARY_KEY : this.b.toString();
    }
}