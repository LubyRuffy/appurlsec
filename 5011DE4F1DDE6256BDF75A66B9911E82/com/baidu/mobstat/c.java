package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashMap;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;

class c {
    private static HandlerThread c;
    private static Handler d;
    private static c e;
    HashMap<String, h> a;
    public final String b;

    static {
        c = new HandlerThread("EventHandleThread");
        e = new c();
    }

    private c() {
        this.a = new HashMap();
        this.b = "$|$";
        c.start();
        c.setPriority(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO);
        d = new Handler(c.getLooper());
    }

    public static c a() {
        return e;
    }

    public String a(String r3_String, String r4_String) {
        return "__sdk_" + r3_String + "$|$" + r4_String;
    }

    public void a(Context r10_Context, String r11_String, String r12_String, int r13i, long r14j) {
        d.post(new d(this, r11_String, r12_String, r13i, r14j, r10_Context));
    }

    public void a(Context r8_Context, String r9_String, String r10_String, long r11j) {
        d.post(new e(this, r11j, r9_String, r10_String));
    }

    public void b(Context r9_Context, String r10_String, String r11_String, long r12j) {
        d.post(new f(this, r10_String, r11_String, r12j, r9_Context));
    }

    public void c_(Context r9_Context, String r10_String, String r11_String, long r12j) {
        d.post(new g(this, r12j, r10_String, r11_String, r9_Context));
    }
}