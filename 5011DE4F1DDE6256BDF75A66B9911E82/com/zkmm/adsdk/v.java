package com.zkmm.adsdk;

import java.util.Map;

// compiled from: SourceFile
final class v implements Runnable {
    private final /* synthetic */ Map a;

    v(Map r1_Map) {
        this.a = r1_Map;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r17_this = this;
        r0 = r17;
        r1 = r0.a;
        if (r1 == 0) goto L_0x00ca;
    L_0x0006:
        r0 = r17;
        r1 = r0.a;
        r1 = r1.size();
        if (r1 <= 0) goto L_0x00ca;
    L_0x0010:
        r6 = new java.io.ByteArrayOutputStream;
        r6.<init>();
        r7 = new java.io.ByteArrayOutputStream;
        r7.<init>();
        r8 = new java.io.ByteArrayOutputStream;
        r8.<init>();
        r9 = new java.io.ByteArrayOutputStream;
        r9.<init>();
        r3 = 0;
        r2 = 0;
        r1 = 0;
        r0 = r17;
        r4 = r0.a;
        r4 = r4.entrySet();
        r10 = r4.iterator();
        r4 = r2;
        r5 = r3;
        r3 = r1;
    L_0x0036:
        r1 = r10.hasNext();
        if (r1 != 0) goto L_0x00cb;
    L_0x003c:
        r1 = com.zkmm.adsdk.m.b;	 //Catch:{ Exception -> 0x0196 }
        r1 = r1.length;	 //Catch:{ Exception -> 0x0196 }
        if (r1 == 0) goto L_0x0046;
    L_0x0041:
        r1 = com.zkmm.adsdk.m.b;	 //Catch:{ Exception -> 0x0196 }
        r6.write(r1);	 //Catch:{ Exception -> 0x0196 }
    L_0x0046:
        r1 = 85;
        r6.write(r1);	 //Catch:{ Exception -> 0x0196 }
        r1 = com.zkmm.adsdk.m.c;	 //Catch:{ Exception -> 0x0196 }
        r1 = r1.length;	 //Catch:{ Exception -> 0x0196 }
        r1 = (byte) r1;	 //Catch:{ Exception -> 0x0196 }
        r6.write(r1);	 //Catch:{ Exception -> 0x0196 }
        r1 = com.zkmm.adsdk.m.c;	 //Catch:{ Exception -> 0x0196 }
        r1 = r1.length;	 //Catch:{ Exception -> 0x0196 }
        if (r1 == 0) goto L_0x005c;
    L_0x0057:
        r1 = com.zkmm.adsdk.m.c;	 //Catch:{ Exception -> 0x0196 }
        r6.write(r1);	 //Catch:{ Exception -> 0x0196 }
    L_0x005c:
        r1 = 77;
        r6.write(r1);	 //Catch:{ Exception -> 0x0196 }
        r1 = 0;
        r6.write(r1);	 //Catch:{ Exception -> 0x0196 }
        r1 = 83;
        r6.write(r1);	 //Catch:{ Exception -> 0x0196 }
        r1 = 0;
        r1 = com.zkmm.adsdk.s.a(r1);	 //Catch:{ Exception -> 0x0196 }
        r6.write(r1);	 //Catch:{ Exception -> 0x0196 }
        r1 = 66;
        r6.write(r1);	 //Catch:{ Exception -> 0x0196 }
        r1 = r7.toByteArray();	 //Catch:{ Exception -> 0x0196 }
        r2 = com.zkmm.adsdk.s.a(r3);	 //Catch:{ Exception -> 0x0196 }
        r6.write(r2);	 //Catch:{ Exception -> 0x0196 }
        r2 = r1.length;	 //Catch:{ Exception -> 0x0196 }
        if (r2 == 0) goto L_0x0088;
    L_0x0085:
        r6.write(r1);	 //Catch:{ Exception -> 0x0196 }
    L_0x0088:
        r1 = 72;
        r6.write(r1);	 //Catch:{ Exception -> 0x0196 }
        r1 = r8.toByteArray();	 //Catch:{ Exception -> 0x0196 }
        r2 = com.zkmm.adsdk.s.a(r4);	 //Catch:{ Exception -> 0x0196 }
        r6.write(r2);	 //Catch:{ Exception -> 0x0196 }
        r2 = r1.length;	 //Catch:{ Exception -> 0x0196 }
        if (r2 == 0) goto L_0x009e;
    L_0x009b:
        r6.write(r1);	 //Catch:{ Exception -> 0x0196 }
    L_0x009e:
        r1 = 76;
        r6.write(r1);	 //Catch:{ Exception -> 0x0196 }
        r1 = r9.toByteArray();	 //Catch:{ Exception -> 0x0196 }
        r2 = com.zkmm.adsdk.s.a(r5);	 //Catch:{ Exception -> 0x0196 }
        r6.write(r2);	 //Catch:{ Exception -> 0x0196 }
        r2 = r1.length;	 //Catch:{ Exception -> 0x0196 }
        if (r2 == 0) goto L_0x00b4;
    L_0x00b1:
        r6.write(r1);	 //Catch:{ Exception -> 0x0196 }
    L_0x00b4:
        r5 = r6.toByteArray();
        r4 = 0;
        r3 = 0;
        r2 = 0;
        r1 = new java.net.URL;	 //Catch:{ Exception -> 0x01f5 }
        r6 = "http://track.adwo.com:18088/track/a";
        r1.<init>(r6);	 //Catch:{ Exception -> 0x01f5 }
        r1 = r1.openConnection();	 //Catch:{ Exception -> 0x01f5 }
        r1 = (java.net.HttpURLConnection) r1;	 //Catch:{ Exception -> 0x01f5 }
        if (r1 != 0) goto L_0x019c;
    L_0x00ca:
        return;
    L_0x00cb:
        r1 = r10.next();	 //Catch:{ Exception -> 0x0190 }
        r1 = (java.util.Map.Entry) r1;	 //Catch:{ Exception -> 0x0190 }
        r2 = r1.getKey();	 //Catch:{ Exception -> 0x0190 }
        r2 = (java.lang.String) r2;	 //Catch:{ Exception -> 0x0190 }
        r1 = r1.getValue();	 //Catch:{ Exception -> 0x0190 }
        r1 = (java.lang.String) r1;	 //Catch:{ Exception -> 0x0190 }
        r11 = "=";
        r11 = r2.split(r11);	 //Catch:{ Exception -> 0x0190 }
        r12 = r11.length;	 //Catch:{ Exception -> 0x0190 }
        r13 = 1;
        if (r12 != r13) goto L_0x0124;
    L_0x00e7:
        r11 = "=";
        r1 = r1.split(r11);	 //Catch:{ Exception -> 0x0190 }
        r11 = 0;
        r11 = r1[r11];	 //Catch:{ Exception -> 0x0190 }
        r11 = java.lang.Double.parseDouble(r11);	 //Catch:{ Exception -> 0x0190 }
        r13 = 1;
        r13 = r1[r13];	 //Catch:{ Exception -> 0x0190 }
        r13 = java.lang.Double.parseDouble(r13);	 //Catch:{ Exception -> 0x0190 }
        r15 = 2;
        r1 = r1[r15];	 //Catch:{ Exception -> 0x0190 }
        r1 = java.lang.Integer.parseInt(r1);	 //Catch:{ Exception -> 0x0190 }
        r2 = r2.getBytes();	 //Catch:{ Exception -> 0x0190 }
        r9.write(r2);	 //Catch:{ Exception -> 0x0190 }
        r1 = com.zkmm.adsdk.s.a(r1);	 //Catch:{ Exception -> 0x0190 }
        r9.write(r1);	 //Catch:{ Exception -> 0x0190 }
        r1 = com.zkmm.adsdk.s.a(r11);	 //Catch:{ Exception -> 0x0190 }
        r9.write(r1);	 //Catch:{ Exception -> 0x0190 }
        r1 = com.zkmm.adsdk.s.a(r13);	 //Catch:{ Exception -> 0x0190 }
        r9.write(r1);	 //Catch:{ Exception -> 0x0190 }
        r1 = r5 + 1;
        r1 = (short) r1;	 //Catch:{ Exception -> 0x0190 }
        r5 = r1;
        goto L_0x0036;
    L_0x0124:
        r2 = r11.length;	 //Catch:{ Exception -> 0x0190 }
        r12 = 2;
        if (r2 != r12) goto L_0x015a;
    L_0x0128:
        r2 = 0;
        r2 = r11[r2];	 //Catch:{ Exception -> 0x0190 }
        r2 = java.lang.Integer.decode(r2);	 //Catch:{ Exception -> 0x0190 }
        r2 = r2.intValue();	 //Catch:{ Exception -> 0x0190 }
        r12 = 1;
        r11 = r11[r12];	 //Catch:{ Exception -> 0x0190 }
        r1 = java.lang.Long.decode(r1);	 //Catch:{ Exception -> 0x0190 }
        r12 = r1.longValue();	 //Catch:{ Exception -> 0x0190 }
        r12 = (double) r12;	 //Catch:{ Exception -> 0x0190 }
        r1 = r11.getBytes();	 //Catch:{ Exception -> 0x0190 }
        r7.write(r1);	 //Catch:{ Exception -> 0x0190 }
        r1 = com.zkmm.adsdk.s.a(r2);	 //Catch:{ Exception -> 0x0190 }
        r7.write(r1);	 //Catch:{ Exception -> 0x0190 }
        r1 = com.zkmm.adsdk.s.a(r12);	 //Catch:{ Exception -> 0x0190 }
        r7.write(r1);	 //Catch:{ Exception -> 0x0190 }
        r1 = r3 + 1;
        r1 = (short) r1;	 //Catch:{ Exception -> 0x0190 }
        r3 = r1;
        goto L_0x0036;
    L_0x015a:
        r2 = r11.length;	 //Catch:{ Exception -> 0x0190 }
        r12 = 3;
        if (r2 != r12) goto L_0x0036;
    L_0x015e:
        r2 = 0;
        r2 = r11[r2];	 //Catch:{ Exception -> 0x0190 }
        r2 = java.lang.Integer.decode(r2);	 //Catch:{ Exception -> 0x0190 }
        r2 = r2.intValue();	 //Catch:{ Exception -> 0x0190 }
        r12 = 1;
        r11 = r11[r12];	 //Catch:{ Exception -> 0x0190 }
        r1 = java.lang.Long.decode(r1);	 //Catch:{ Exception -> 0x0190 }
        r12 = r1.longValue();	 //Catch:{ Exception -> 0x0190 }
        r12 = (double) r12;	 //Catch:{ Exception -> 0x0190 }
        r1 = r11.getBytes();	 //Catch:{ Exception -> 0x0190 }
        r8.write(r1);	 //Catch:{ Exception -> 0x0190 }
        r1 = com.zkmm.adsdk.s.a(r2);	 //Catch:{ Exception -> 0x0190 }
        r8.write(r1);	 //Catch:{ Exception -> 0x0190 }
        r1 = com.zkmm.adsdk.s.a(r12);	 //Catch:{ Exception -> 0x0190 }
        r8.write(r1);	 //Catch:{ Exception -> 0x0190 }
        r1 = r4 + 1;
        r1 = (short) r1;
        r4 = r1;
        goto L_0x0036;
    L_0x0190:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0036;
    L_0x0196:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00b4;
    L_0x019c:
        r2 = com.zkmm.adsdk.s.a;	 //Catch:{ Exception -> 0x0202 }
        r1.setConnectTimeout(r2);	 //Catch:{ Exception -> 0x0202 }
        r2 = com.zkmm.adsdk.s.a;	 //Catch:{ Exception -> 0x0202 }
        r1.setReadTimeout(r2);	 //Catch:{ Exception -> 0x0202 }
        r2 = "POST";
        r1.setRequestMethod(r2);	 //Catch:{ Exception -> 0x0202 }
        r2 = 1;
        r1.setDoOutput(r2);	 //Catch:{ Exception -> 0x0202 }
        r2 = "Content-Type";
        r6 = "application/x-www-form-urlencoded";
        r1.setRequestProperty(r2, r6);	 //Catch:{ Exception -> 0x0202 }
        if (r5 == 0) goto L_0x01c2;
    L_0x01b8:
        r2 = "Content-Length";
        r6 = r5.length;	 //Catch:{ Exception -> 0x0202 }
        r6 = java.lang.Integer.toString(r6);	 //Catch:{ Exception -> 0x0202 }
        r1.setRequestProperty(r2, r6);	 //Catch:{ Exception -> 0x0202 }
    L_0x01c2:
        r1.connect();	 //Catch:{ Exception -> 0x0202 }
        r4 = r1.getOutputStream();	 //Catch:{ Exception -> 0x0202 }
        if (r5 == 0) goto L_0x01ce;
    L_0x01cb:
        r4.write(r5);	 //Catch:{ Exception -> 0x0202 }
    L_0x01ce:
        r3 = r1.getInputStream();	 //Catch:{ Exception -> 0x0202 }
        r2 = r1.getResponseCode();	 //Catch:{ Exception -> 0x0202 }
        r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 != r5) goto L_0x01e1;
    L_0x01da:
        r2 = "Adwo SDK";
        r5 = "update statistics successfully.";
        android.util.Log.v(r2, r5);	 //Catch:{ Exception -> 0x0202 }
    L_0x01e1:
        if (r3 == 0) goto L_0x01e6;
    L_0x01e3:
        r3.close();	 //Catch:{ Exception -> 0x01f2 }
    L_0x01e6:
        if (r4 == 0) goto L_0x01eb;
    L_0x01e8:
        r4.close();	 //Catch:{ Exception -> 0x01f2 }
    L_0x01eb:
        if (r1 == 0) goto L_0x00ca;
    L_0x01ed:
        r1.disconnect();	 //Catch:{ Exception -> 0x01f2 }
        goto L_0x00ca;
    L_0x01f2:
        r1 = move-exception;
        goto L_0x00ca;
    L_0x01f5:
        r1 = move-exception;
    L_0x01f6:
        r1.printStackTrace();
        r1 = "Adwo SDK";
        r5 = "Could not get an ad from Adwo servers,Network Error!";
        android.util.Log.w(r1, r5);
        r1 = r2;
        goto L_0x01e1;
    L_0x0202:
        r2 = move-exception;
        r16 = r2;
        r2 = r1;
        r1 = r16;
        goto L_0x01f6;
        */

    }
}