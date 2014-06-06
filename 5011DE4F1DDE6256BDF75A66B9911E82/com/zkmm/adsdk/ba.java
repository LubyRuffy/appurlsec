package com.zkmm.adsdk;

// compiled from: SourceFile
final class ba implements Runnable {
    private /* synthetic */ g a;

    ba(g r1_g) {
        this.a = r1_g;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r6_this = this;
        r1 = 0;
        r5 = 1;
        r0 = r6.a;
        r0 = r0.n;
        if (r0 != 0) goto L_0x00c3;
    L_0x0008:
        r0 = r6.a;
        r0 = r0.b;
        if (r0 == 0) goto L_0x005c;
    L_0x000e:
        r0 = r6.a;	 //Catch:{ IOException -> 0x00a8 }
        r0 = r0.b;	 //Catch:{ IOException -> 0x00a8 }
        r2 = r6.a;	 //Catch:{ IOException -> 0x00a8 }
        r2 = r2.b;	 //Catch:{ IOException -> 0x00a8 }
        r3 = "clk?p0";
        r2 = r2.indexOf(r3);	 //Catch:{ IOException -> 0x00a8 }
        if (r2 <= 0) goto L_0x0022;
    L_0x001e:
        r0 = r6.a;	 //Catch:{ IOException -> 0x00a8 }
        r0 = r0.b;	 //Catch:{ IOException -> 0x00a8 }
    L_0x0022:
        r2 = new java.net.URL;	 //Catch:{ IOException -> 0x00a8 }
        r2.<init>(r0);	 //Catch:{ IOException -> 0x00a8 }
        r0 = r2.openConnection();	 //Catch:{ IOException -> 0x00a8 }
        r0 = (java.net.HttpURLConnection) r0;	 //Catch:{ IOException -> 0x00a8 }
        r2 = com.zkmm.adsdk.s.a;	 //Catch:{ IOException -> 0x00f1 }
        r0.setConnectTimeout(r2);	 //Catch:{ IOException -> 0x00f1 }
        r2 = com.zkmm.adsdk.s.a;	 //Catch:{ IOException -> 0x00f1 }
        r0.setReadTimeout(r2);	 //Catch:{ IOException -> 0x00f1 }
        r0.connect();	 //Catch:{ IOException -> 0x00f1 }
        r1 = r0.getInputStream();	 //Catch:{ IOException -> 0x00f1 }
        r2 = r0.getResponseCode();	 //Catch:{ IOException -> 0x00f1 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 != r3) goto L_0x0052;
    L_0x0046:
        r2 = r6.a;	 //Catch:{ IOException -> 0x00f1 }
        r3 = 1;
        r2.n = r3;	 //Catch:{ IOException -> 0x00f1 }
        r2 = "Adwo SDK";
        r3 = "Charging successfully.";
        android.util.Log.v(r2, r3);	 //Catch:{ IOException -> 0x00f1 }
    L_0x0052:
        if (r1 == 0) goto L_0x0057;
    L_0x0054:
        r1.close();	 //Catch:{ Exception -> 0x00ee }
    L_0x0057:
        if (r0 == 0) goto L_0x005c;
    L_0x0059:
        r0.disconnect();	 //Catch:{ Exception -> 0x00ee }
    L_0x005c:
        r0 = com.zkmm.adsdk.m.w;
        r1 = r6.a;
        r1 = r1.a;
        r1 = java.lang.Integer.valueOf(r1);
        r0 = r0.containsKey(r1);
        if (r0 == 0) goto L_0x00cb;
    L_0x006c:
        r0 = com.zkmm.adsdk.m.w;
        r1 = r6.a;
        r1 = r1.a;
        r1 = java.lang.Integer.valueOf(r1);
        r0 = r0.get(r1);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r0 = r0 + 1;
        r1 = com.zkmm.adsdk.m.w;
        r2 = r6.a;
        r2 = r2.a;
        r2 = java.lang.Integer.valueOf(r2);
        r0 = java.lang.Integer.valueOf(r0);
        r1.put(r2, r0);
    L_0x0093:
        r0 = r6.a;
        r0 = r0.h;
        if (r0 == 0) goto L_0x00a7;
    L_0x0099:
        r0 = r6.a;
        r0 = r0.h;
        r2 = r0.size();
        if (r2 == 0) goto L_0x00a7;
    L_0x00a3:
        r0 = 0;
        r1 = r0;
    L_0x00a5:
        if (r1 < r2) goto L_0x00dd;
    L_0x00a7:
        return;
    L_0x00a8:
        r0 = move-exception;
        r0 = r1;
    L_0x00aa:
        r2 = "Adwo SDK";
        r3 = new java.lang.StringBuilder;
        r4 = "Could not determine final click destination URL.  Will try to follow anyway.  ";
        r3.<init>(r4);
        r4 = r6.a;
        r4 = r4.b;
        r3 = r3.append(r4);
        r3 = r3.toString();
        android.util.Log.w(r2, r3);
        goto L_0x0052;
    L_0x00c3:
        r0 = "Adwo SDK";
        r1 = "This ad had already been clicked. ";
        android.util.Log.e(r0, r1);
        goto L_0x005c;
    L_0x00cb:
        r0 = com.zkmm.adsdk.m.w;
        r1 = r6.a;
        r1 = r1.a;
        r1 = java.lang.Integer.valueOf(r1);
        r2 = java.lang.Integer.valueOf(r5);
        r0.put(r1, r2);
        goto L_0x0093;
    L_0x00dd:
        r0 = r6.a;
        r0 = r0.h;
        r0 = r0.get(r1);
        r0 = (java.lang.String) r0;
        com.zkmm.adsdk.s.a(r0);
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x00a5;
    L_0x00ee:
        r0 = move-exception;
        goto L_0x005c;
    L_0x00f1:
        r2 = move-exception;
        goto L_0x00aa;
        */

    }
}