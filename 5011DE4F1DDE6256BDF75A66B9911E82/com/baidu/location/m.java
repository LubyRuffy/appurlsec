package com.baidu.location;

final class m extends Thread {
    m() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r12_this = this;
        r11 = 8;
        r0 = 1;
        r1 = -1;
        r2 = 0;
        r9 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        r3 = 3;
    L_0x0008:
        if (r3 <= 0) goto L_0x00a1;
    L_0x000a:
        r4 = new org.apache.http.client.methods.HttpPost;	 //Catch:{ Exception -> 0x00db }
        r5 = com.baidu.location.j.do();	 //Catch:{ Exception -> 0x00db }
        r4.<init>(r5);	 //Catch:{ Exception -> 0x00db }
        r5 = new java.util.ArrayList;	 //Catch:{ Exception -> 0x00db }
        r5.<init>();	 //Catch:{ Exception -> 0x00db }
        r6 = com.baidu.location.g.q();	 //Catch:{ Exception -> 0x00db }
        if (r6 == 0) goto L_0x00cd;
    L_0x001e:
        r6 = new org.apache.http.message.BasicNameValuePair;	 //Catch:{ Exception -> 0x00db }
        r7 = "qt";
        r8 = "grid";
        r6.<init>(r7, r8);	 //Catch:{ Exception -> 0x00db }
        r5.add(r6);	 //Catch:{ Exception -> 0x00db }
    L_0x002a:
        r6 = new org.apache.http.message.BasicNameValuePair;	 //Catch:{ Exception -> 0x00db }
        r7 = "req";
        r8 = com.baidu.location.g.r();	 //Catch:{ Exception -> 0x00db }
        r6.<init>(r7, r8);	 //Catch:{ Exception -> 0x00db }
        r5.add(r6);	 //Catch:{ Exception -> 0x00db }
        r6 = new org.apache.http.client.entity.UrlEncodedFormEntity;	 //Catch:{ Exception -> 0x00db }
        r7 = "utf-8";
        r6.<init>(r5, r7);	 //Catch:{ Exception -> 0x00db }
        r4.setEntity(r6);	 //Catch:{ Exception -> 0x00db }
        r5 = new org.apache.http.impl.client.DefaultHttpClient;	 //Catch:{ Exception -> 0x00db }
        r5.<init>();	 //Catch:{ Exception -> 0x00db }
        r6 = r5.getParams();	 //Catch:{ Exception -> 0x00db }
        r7 = "http.connection.timeout";
        r8 = com.baidu.location.g.e();	 //Catch:{ Exception -> 0x00db }
        r8 = java.lang.Integer.valueOf(r8);	 //Catch:{ Exception -> 0x00db }
        r6.setParameter(r7, r8);	 //Catch:{ Exception -> 0x00db }
        r6 = r5.getParams();	 //Catch:{ Exception -> 0x00db }
        r7 = "http.socket.timeout";
        r8 = com.baidu.location.g.e();	 //Catch:{ Exception -> 0x00db }
        r8 = java.lang.Integer.valueOf(r8);	 //Catch:{ Exception -> 0x00db }
        r6.setParameter(r7, r8);	 //Catch:{ Exception -> 0x00db }
        r6 = com.baidu.location.g.g();	 //Catch:{ Exception -> 0x00db }
        r7 = "req config...";
        com.baidu.location.j.if(r6, r7);	 //Catch:{ Exception -> 0x00db }
        r5 = r5.execute(r4);	 //Catch:{ Exception -> 0x00db }
        r6 = r5.getStatusLine();	 //Catch:{ Exception -> 0x00db }
        r6 = r6.getStatusCode();	 //Catch:{ Exception -> 0x00db }
        r7 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r6 != r7) goto L_0x0291;
    L_0x0082:
        r4 = com.baidu.location.g.q();	 //Catch:{ Exception -> 0x00db }
        if (r4 == 0) goto L_0x023d;
    L_0x0088:
        r4 = com.baidu.location.g.g();	 //Catch:{ Exception -> 0x00db }
        r6 = "req config response...";
        com.baidu.location.j.if(r4, r6);	 //Catch:{ Exception -> 0x00db }
        r3 = r5.getEntity();	 //Catch:{ Exception -> 0x010f }
        r4 = org.apache.http.util.EntityUtils.toByteArray(r3);	 //Catch:{ Exception -> 0x010f }
        if (r4 != 0) goto L_0x00e9;
    L_0x009b:
        r0 = r2;
    L_0x009c:
        if (r0 == 0) goto L_0x00a1;
    L_0x009e:
        com.baidu.location.g.for();	 //Catch:{ Exception -> 0x010f }
    L_0x00a1:
        com.baidu.location.j.if();	 //Catch:{ Exception -> 0x029e }
        r0 = com.baidu.location.j.t;	 //Catch:{ Exception -> 0x029e }
        if (r0 == r1) goto L_0x0296;
    L_0x00a8:
        r0 = com.baidu.location.j.t;	 //Catch:{ Exception -> 0x029e }
        r3 = com.baidu.location.j.t;	 //Catch:{ Exception -> 0x029e }
        com.baidu.location.j.if(r3);	 //Catch:{ Exception -> 0x029e }
    L_0x00af:
        if (r0 == r1) goto L_0x00b4;
    L_0x00b1:
        com.baidu.location.j.a(r0);	 //Catch:{ Exception -> 0x029e }
    L_0x00b4:
        r0 = com.baidu.location.g.s();	 //Catch:{ Exception -> 0x029e }
        r1 = 92;
        r0 = r0.obtainMessage(r1);	 //Catch:{ Exception -> 0x029e }
        r0.sendToTarget();	 //Catch:{ Exception -> 0x029e }
        r0 = 0;
        com.baidu.location.g.c(r0);	 //Catch:{ Exception -> 0x029e }
    L_0x00c5:
        r0 = 0;
        com.baidu.location.g.b(r0);
        com.baidu.location.g.d(r2);
        return;
    L_0x00cd:
        r6 = new org.apache.http.message.BasicNameValuePair;	 //Catch:{ Exception -> 0x00db }
        r7 = "qt";
        r8 = "conf";
        r6.<init>(r7, r8);	 //Catch:{ Exception -> 0x00db }
        r5.add(r6);	 //Catch:{ Exception -> 0x00db }
        goto L_0x002a;
    L_0x00db:
        r4 = move-exception;
        r4 = com.baidu.location.g.g();
        r5 = "Exception!!!";
        com.baidu.location.j.if(r4, r5);
    L_0x00e5:
        r3 = r3 + -1;
        goto L_0x0008;
    L_0x00e9:
        r3 = r4.length;	 //Catch:{ Exception -> 0x010f }
        r5 = 640; // 0x280 float:8.97E-43 double:3.16E-321;
        if (r3 >= r5) goto L_0x0111;
    L_0x00ee:
        r3 = com.baidu.location.g.g();	 //Catch:{ Exception -> 0x010f }
        r4 = "req config response.<640.";
        com.baidu.location.j.if(r3, r4);	 //Catch:{ Exception -> 0x010f }
        r3 = 0;
        com.baidu.location.j.ag = r3;	 //Catch:{ Exception -> 0x010f }
        r3 = com.baidu.location.j.Z;	 //Catch:{ Exception -> 0x010f }
        r5 = 4582862980812216730; // 0x3f9999999999999a float:-1.5881868E-23 double:0.025;
        r3 = r3 + r5;
        com.baidu.location.j.o = r3;	 //Catch:{ Exception -> 0x010f }
        r3 = com.baidu.location.j.J;	 //Catch:{ Exception -> 0x010f }
        r5 = 4582862980812216730; // 0x3f9999999999999a float:-1.5881868E-23 double:0.025;
        r3 = r3 - r5;
        com.baidu.location.j.if = r3;	 //Catch:{ Exception -> 0x010f }
        goto L_0x009c;
    L_0x010f:
        r0 = move-exception;
        goto L_0x00a1;
    L_0x0111:
        r3 = 1;
        com.baidu.location.j.ag = r3;	 //Catch:{ Exception -> 0x010f }
        r3 = 7;
        r3 = r4[r3];	 //Catch:{ Exception -> 0x010f }
        r5 = (long) r3;	 //Catch:{ Exception -> 0x010f }
        r5 = r5 & r9;
        r3 = 56;
        r5 = r5 << r3;
        r3 = 6;
        r3 = r4[r3];	 //Catch:{ Exception -> 0x010f }
        r7 = (long) r3;	 //Catch:{ Exception -> 0x010f }
        r7 = r7 & r9;
        r3 = 48;
        r7 = r7 << r3;
        r5 = r5 | r7;
        r3 = 5;
        r3 = r4[r3];	 //Catch:{ Exception -> 0x010f }
        r7 = (long) r3;	 //Catch:{ Exception -> 0x010f }
        r7 = r7 & r9;
        r3 = 40;
        r7 = r7 << r3;
        r5 = r5 | r7;
        r3 = 4;
        r3 = r4[r3];	 //Catch:{ Exception -> 0x010f }
        r7 = (long) r3;	 //Catch:{ Exception -> 0x010f }
        r7 = r7 & r9;
        r3 = 32;
        r7 = r7 << r3;
        r5 = r5 | r7;
        r3 = 3;
        r3 = r4[r3];	 //Catch:{ Exception -> 0x010f }
        r7 = (long) r3;	 //Catch:{ Exception -> 0x010f }
        r7 = r7 & r9;
        r3 = 24;
        r7 = r7 << r3;
        r5 = r5 | r7;
        r3 = 2;
        r3 = r4[r3];	 //Catch:{ Exception -> 0x010f }
        r7 = (long) r3;	 //Catch:{ Exception -> 0x010f }
        r7 = r7 & r9;
        r3 = 16;
        r7 = r7 << r3;
        r5 = r5 | r7;
        r3 = 1;
        r3 = r4[r3];	 //Catch:{ Exception -> 0x010f }
        r7 = (long) r3;	 //Catch:{ Exception -> 0x010f }
        r7 = r7 & r9;
        r7 = r7 << r11;
        r5 = r5 | r7;
        r3 = 0;
        r3 = r4[r3];	 //Catch:{ Exception -> 0x010f }
        r7 = (long) r3;	 //Catch:{ Exception -> 0x010f }
        r7 = r7 & r9;
        r5 = r5 | r7;
        r3 = java.lang.Long.valueOf(r5);	 //Catch:{ Exception -> 0x010f }
        r5 = com.baidu.location.g.g();	 //Catch:{ Exception -> 0x010f }
        r6 = "req config 1...";
        com.baidu.location.j.if(r5, r6);	 //Catch:{ Exception -> 0x010f }
        r5 = r3.longValue();	 //Catch:{ Exception -> 0x010f }
        r5 = java.lang.Double.longBitsToDouble(r5);	 //Catch:{ Exception -> 0x010f }
        com.baidu.location.j.if = r5;	 //Catch:{ Exception -> 0x010f }
        r5 = com.baidu.location.g.g();	 //Catch:{ Exception -> 0x010f }
        r6 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x010f }
        r6.<init>();	 //Catch:{ Exception -> 0x010f }
        r7 = "req config response:";
        r6 = r6.append(r7);	 //Catch:{ Exception -> 0x010f }
        r7 = r3.longValue();	 //Catch:{ Exception -> 0x010f }
        r7 = java.lang.Double.longBitsToDouble(r7);	 //Catch:{ Exception -> 0x010f }
        r3 = r6.append(r7);	 //Catch:{ Exception -> 0x010f }
        r3 = r3.toString();	 //Catch:{ Exception -> 0x010f }
        com.baidu.location.j.if(r5, r3);	 //Catch:{ Exception -> 0x010f }
        r3 = 15;
        r3 = r4[r3];	 //Catch:{ Exception -> 0x010f }
        r5 = (long) r3;	 //Catch:{ Exception -> 0x010f }
        r5 = r5 & r9;
        r3 = 56;
        r5 = r5 << r3;
        r3 = 14;
        r3 = r4[r3];	 //Catch:{ Exception -> 0x010f }
        r7 = (long) r3;	 //Catch:{ Exception -> 0x010f }
        r7 = r7 & r9;
        r3 = 48;
        r7 = r7 << r3;
        r5 = r5 | r7;
        r3 = 13;
        r3 = r4[r3];	 //Catch:{ Exception -> 0x010f }
        r7 = (long) r3;	 //Catch:{ Exception -> 0x010f }
        r7 = r7 & r9;
        r3 = 40;
        r7 = r7 << r3;
        r5 = r5 | r7;
        r3 = 12;
        r3 = r4[r3];	 //Catch:{ Exception -> 0x010f }
        r7 = (long) r3;	 //Catch:{ Exception -> 0x010f }
        r7 = r7 & r9;
        r3 = 32;
        r7 = r7 << r3;
        r5 = r5 | r7;
        r3 = 11;
        r3 = r4[r3];	 //Catch:{ Exception -> 0x010f }
        r7 = (long) r3;	 //Catch:{ Exception -> 0x010f }
        r7 = r7 & r9;
        r3 = 24;
        r7 = r7 << r3;
        r5 = r5 | r7;
        r3 = 10;
        r3 = r4[r3];	 //Catch:{ Exception -> 0x010f }
        r7 = (long) r3;	 //Catch:{ Exception -> 0x010f }
        r7 = r7 & r9;
        r3 = 16;
        r7 = r7 << r3;
        r5 = r5 | r7;
        r3 = 9;
        r3 = r4[r3];	 //Catch:{ Exception -> 0x010f }
        r7 = (long) r3;	 //Catch:{ Exception -> 0x010f }
        r7 = r7 & r9;
        r7 = r7 << r11;
        r5 = r5 | r7;
        r3 = 8;
        r3 = r4[r3];	 //Catch:{ Exception -> 0x010f }
        r7 = (long) r3;	 //Catch:{ Exception -> 0x010f }
        r7 = r7 & r9;
        r5 = r5 | r7;
        r3 = java.lang.Long.valueOf(r5);	 //Catch:{ Exception -> 0x010f }
        r5 = r3.longValue();	 //Catch:{ Exception -> 0x010f }
        r5 = java.lang.Double.longBitsToDouble(r5);	 //Catch:{ Exception -> 0x010f }
        com.baidu.location.j.o = r5;	 //Catch:{ Exception -> 0x010f }
        r5 = 625; // 0x271 float:8.76E-43 double:3.09E-321;
        r5 = new byte[r5];	 //Catch:{ Exception -> 0x010f }
        com.baidu.location.j.j = r5;	 //Catch:{ Exception -> 0x010f }
        r5 = com.baidu.location.g.g();	 //Catch:{ Exception -> 0x010f }
        r6 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x010f }
        r6.<init>();	 //Catch:{ Exception -> 0x010f }
        r7 = "req config response:";
        r6 = r6.append(r7);	 //Catch:{ Exception -> 0x010f }
        r7 = r3.longValue();	 //Catch:{ Exception -> 0x010f }
        r7 = java.lang.Double.longBitsToDouble(r7);	 //Catch:{ Exception -> 0x010f }
        r3 = r6.append(r7);	 //Catch:{ Exception -> 0x010f }
        r3 = r3.toString();	 //Catch:{ Exception -> 0x010f }
        com.baidu.location.j.if(r5, r3);	 //Catch:{ Exception -> 0x010f }
        r3 = r2;
    L_0x0210:
        r5 = 625; // 0x271 float:8.76E-43 double:3.09E-321;
        if (r3 >= r5) goto L_0x009c;
    L_0x0214:
        r5 = com.baidu.location.j.j;	 //Catch:{ Exception -> 0x010f }
        r6 = r3 + 16;
        r6 = r4[r6];	 //Catch:{ Exception -> 0x010f }
        r5[r3] = r6;	 //Catch:{ Exception -> 0x010f }
        r5 = com.baidu.location.g.g();	 //Catch:{ Exception -> 0x010f }
        r6 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x010f }
        r6.<init>();	 //Catch:{ Exception -> 0x010f }
        r7 = "req config value:";
        r6 = r6.append(r7);	 //Catch:{ Exception -> 0x010f }
        r7 = com.baidu.location.j.j;	 //Catch:{ Exception -> 0x010f }
        r7 = r7[r3];	 //Catch:{ Exception -> 0x010f }
        r6 = r6.append(r7);	 //Catch:{ Exception -> 0x010f }
        r6 = r6.toString();	 //Catch:{ Exception -> 0x010f }
        com.baidu.location.j.if(r5, r6);	 //Catch:{ Exception -> 0x010f }
        r3 = r3 + 1;
        goto L_0x0210;
    L_0x023d:
        r4 = r5.getEntity();	 //Catch:{ Exception -> 0x00db }
        r5 = "utf-8";
        r4 = org.apache.http.util.EntityUtils.toString(r4, r5);	 //Catch:{ Exception -> 0x00db }
        r5 = com.baidu.location.g.g();	 //Catch:{ Exception -> 0x00db }
        r6 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x00db }
        r6.<init>();	 //Catch:{ Exception -> 0x00db }
        r7 = "req config value:";
        r6 = r6.append(r7);	 //Catch:{ Exception -> 0x00db }
        r6 = r6.append(r4);	 //Catch:{ Exception -> 0x00db }
        r6 = r6.toString();	 //Catch:{ Exception -> 0x00db }
        com.baidu.location.j.if(r5, r6);	 //Catch:{ Exception -> 0x00db }
        r0 = com.baidu.location.g.if(r4);	 //Catch:{ Exception -> 0x02a5 }
        if (r0 == 0) goto L_0x0273;
    L_0x0267:
        r0 = com.baidu.location.g.g();	 //Catch:{ Exception -> 0x02a5 }
        r3 = "Save to config";
        com.baidu.location.j.if(r0, r3);	 //Catch:{ Exception -> 0x02a5 }
        com.baidu.location.g.c();	 //Catch:{ Exception -> 0x02a5 }
    L_0x0273:
        r0 = new org.json.JSONObject;	 //Catch:{ Exception -> 0x028e }
        r0.<init>(r4);	 //Catch:{ Exception -> 0x028e }
        r3 = "ctr";
        r3 = r0.has(r3);	 //Catch:{ Exception -> 0x028e }
        if (r3 == 0) goto L_0x00a1;
    L_0x0280:
        r3 = "ctr";
        r0 = r0.getString(r3);	 //Catch:{ Exception -> 0x028e }
        r0 = java.lang.Integer.parseInt(r0);	 //Catch:{ Exception -> 0x028e }
        com.baidu.location.j.t = r0;	 //Catch:{ Exception -> 0x028e }
        goto L_0x00a1;
    L_0x028e:
        r0 = move-exception;
        goto L_0x00a1;
    L_0x0291:
        r4.abort();	 //Catch:{ Exception -> 0x00db }
        goto L_0x00e5;
    L_0x0296:
        r0 = com.baidu.location.j.new;	 //Catch:{ Exception -> 0x029e }
        if (r0 == r1) goto L_0x02a7;
    L_0x029a:
        r0 = com.baidu.location.j.new;	 //Catch:{ Exception -> 0x029e }
        goto L_0x00af;
    L_0x029e:
        r0 = move-exception;
        r0 = 0;
        com.baidu.location.g.c(r0);
        goto L_0x00c5;
    L_0x02a5:
        r0 = move-exception;
        goto L_0x0273;
    L_0x02a7:
        r0 = r1;
        goto L_0x00af;
        */

    }
}