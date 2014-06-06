package com.baidu.kirin.c;

import android.content.Context;
import com.baidu.kirin.KirinConfig;
import com.baidu.kirin.d.d;
import com.baidu.mobstat.CooperService;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    protected Context a;
    protected String b;
    protected String c;
    protected JSONObject d;
    protected JSONObject e;
    protected boolean f;
    private int g;

    a(Context r4_Context, String r5_String) {
        this.g = -1;
        this.a = r4_Context;
        this.b = KirinConfig.PREURL + r5_String;
        d.a("PostUrl: " + this.b);
        this.d = new JSONObject();
        try {
            this.d.put("appkey", com.baidu.kirin.a.a.b(this.a));
            this.d.put("version_code", com.baidu.kirin.a.a.d(this.a));
            this.d.put("version_name", com.baidu.kirin.a.a.c(this.a));
            this.d.put("deviceid", com.baidu.kirin.a.a.h(r4_Context));
            this.d.put("channel", com.baidu.kirin.a.a.a(r4_Context));
            this.d.put("sdk_version", CooperService.getMTJSDKVersion());
            this.d.put("sdk_tag", "mtj");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        b();
    }

    public JSONObject a_() {
        return this.d;
    }

    public void a_(String r2_String, Object r3_Object) {
        try {
            this.d.put(r2_String, r3_Object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void b() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JSONObject c() {
        /*
        r5_this = this;
        r1 = 0;
        r4 = 0;
        r0 = r5.a;
        r0 = com.baidu.kirin.a.a.o(r0);
        if (r0 == 0) goto L_0x00f5;
    L_0x000a:
        r0 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0104 }
        r0.<init>();	 //Catch:{ Exception -> 0x0104 }
        r2 = r5.c;	 //Catch:{ Exception -> 0x0104 }
        r0 = r0.append(r2);	 //Catch:{ Exception -> 0x0104 }
        r2 = " send Content is:";
        r0 = r0.append(r2);	 //Catch:{ Exception -> 0x0104 }
        r2 = r5.d;	 //Catch:{ Exception -> 0x0104 }
        r2 = r2.toString();	 //Catch:{ Exception -> 0x0104 }
        r0 = r0.append(r2);	 //Catch:{ Exception -> 0x0104 }
        r0 = r0.toString();	 //Catch:{ Exception -> 0x0104 }
        com.baidu.kirin.d.d.a(r0);	 //Catch:{ Exception -> 0x0104 }
        r0 = r5.d;	 //Catch:{ Exception -> 0x0104 }
        r0 = r0.toString();	 //Catch:{ Exception -> 0x0104 }
        r0 = com.baidu.kirin.d.a.a(r0);	 //Catch:{ Exception -> 0x0104 }
        r2 = r5.b;	 //Catch:{ Exception -> 0x0104 }
        r0 = com.baidu.kirin.d.g.a(r2, r0);	 //Catch:{ Exception -> 0x0104 }
        r2 = r0.a();	 //Catch:{ Exception -> 0x00a3 }
        r5.g = r2;	 //Catch:{ Exception -> 0x00a3 }
        r2 = r0.b();	 //Catch:{ Exception -> 0x00a3 }
        if (r2 != 0) goto L_0x007b;
    L_0x0048:
        r2 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x00a3 }
        r2.<init>();	 //Catch:{ Exception -> 0x00a3 }
        r3 = r5.c;	 //Catch:{ Exception -> 0x00a3 }
        r2 = r2.append(r3);	 //Catch:{ Exception -> 0x00a3 }
        r3 = " : ";
        r2 = r2.append(r3);	 //Catch:{ Exception -> 0x00a3 }
        r3 = r0.c();	 //Catch:{ Exception -> 0x00a3 }
        r2 = r2.append(r3);	 //Catch:{ Exception -> 0x00a3 }
        r2 = r2.toString();	 //Catch:{ Exception -> 0x00a3 }
        com.baidu.kirin.d.d.c(r2);	 //Catch:{ Exception -> 0x00a3 }
        r2 = 0;
        r5.f = r2;	 //Catch:{ Exception -> 0x00a3 }
    L_0x006b:
        r2 = r5.f;
        if (r2 == 0) goto L_0x00ff;
    L_0x006f:
        r0 = r0.d();
        r5.e = r0;
        r5.e();
        r1 = r5.e;
    L_0x007a:
        return r1;
    L_0x007b:
        r2 = r5.g;	 //Catch:{ Exception -> 0x00a3 }
        if (r2 != 0) goto L_0x00c8;
    L_0x007f:
        r2 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x00a3 }
        r2.<init>();	 //Catch:{ Exception -> 0x00a3 }
        r3 = r5.c;	 //Catch:{ Exception -> 0x00a3 }
        r2 = r2.append(r3);	 //Catch:{ Exception -> 0x00a3 }
        r3 = " : ";
        r2 = r2.append(r3);	 //Catch:{ Exception -> 0x00a3 }
        r3 = r0.c();	 //Catch:{ Exception -> 0x00a3 }
        r2 = r2.append(r3);	 //Catch:{ Exception -> 0x00a3 }
        r2 = r2.toString();	 //Catch:{ Exception -> 0x00a3 }
        com.baidu.kirin.d.d.a(r2);	 //Catch:{ Exception -> 0x00a3 }
        r2 = 1;
        r5.f = r2;	 //Catch:{ Exception -> 0x00a3 }
        goto L_0x006b;
    L_0x00a3:
        r2 = move-exception;
    L_0x00a4:
        r2.printStackTrace();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Fuck, ";
        r2 = r2.append(r3);
        r3 = r5.c;
        r2 = r2.append(r3);
        r3 = " post Exception!";
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.baidu.kirin.d.d.c(r2);
        r5.f = r4;
        goto L_0x006b;
    L_0x00c8:
        r2 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x00a3 }
        r2.<init>();	 //Catch:{ Exception -> 0x00a3 }
        r3 = r5.c;	 //Catch:{ Exception -> 0x00a3 }
        r2 = r2.append(r3);	 //Catch:{ Exception -> 0x00a3 }
        r3 = "Backend return Code is not zeror, is : ";
        r2 = r2.append(r3);	 //Catch:{ Exception -> 0x00a3 }
        r3 = r5.g;	 //Catch:{ Exception -> 0x00a3 }
        r2 = r2.append(r3);	 //Catch:{ Exception -> 0x00a3 }
        r2 = r2.toString();	 //Catch:{ Exception -> 0x00a3 }
        com.baidu.kirin.d.d.c(r2);	 //Catch:{ Exception -> 0x00a3 }
        r2 = 0;
        r5.f = r2;	 //Catch:{ Exception -> 0x00a3 }
        r2 = r0.d();	 //Catch:{ Exception -> 0x00a3 }
        r5.e = r2;	 //Catch:{ Exception -> 0x00a3 }
        r5.f();	 //Catch:{ Exception -> 0x00a3 }
        r1 = r5.e;	 //Catch:{ Exception -> 0x00a3 }
        goto L_0x007a;
    L_0x00f5:
        r5.f = r4;
        r0 = "network has sth wrong!";
        com.baidu.kirin.d.d.a(r0);
        r0 = r1;
        goto L_0x006b;
    L_0x00ff:
        r5.f();
        goto L_0x007a;
    L_0x0104:
        r0 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x00a4;
        */

    }

    public int d() {
        return this.g;
    }

    protected void e() {
    }

    protected void f() {
    }
}