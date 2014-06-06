package com.baidu.kirin.c;

import android.content.Context;
import com.baidu.kirin.b.a;

public class c extends a {
    public c(Context r2_Context, String r3_String) {
        super(r2_Context, r3_String);
        this.c = getClass().getName();
    }

    protected void b() {
        try {
            this.d.put("logID", a.a(this.a).d());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}