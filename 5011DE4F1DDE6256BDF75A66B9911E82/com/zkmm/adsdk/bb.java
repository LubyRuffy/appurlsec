package com.zkmm.adsdk;

// compiled from: SourceFile
final class bb implements Runnable {
    private /* synthetic */ g a;

    bb(g r1_g) {
        this.a = r1_g;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r5_this = this;
        r1 = 0;
        r0 = r5.a;
        r0 = r0.q;
        if (r0 != 0) goto L_0x007b;
    L_0x0007:
        r0 = r5.a;
        r0 = r0.c;
        if (r0 == 0) goto L_0x004b;
    L_0x000d:
        r0 = r5.a;	 //Catch:{ IOException -> 0x0060 }
        r0 = r0.c;	 //Catch:{ IOException -> 0x0060 }
        r2 = new java.net.URL;	 //Catch:{ IOException -> 0x0060 }
        r2.<init>(r0);	 //Catch:{ IOException -> 0x0060 }
        r0 = r2.openConnection();	 //Catch:{ IOException -> 0x0060 }
        r0 = (java.net.HttpURLConnection) r0;	 //Catch:{ IOException -> 0x0060 }
        r2 = com.zkmm.adsdk.s.a;	 //Catch:{ IOException -> 0x0096 }
        r0.setConnectTimeout(r2);	 //Catch:{ IOException -> 0x0096 }
        r2 = com.zkmm.adsdk.s.a;	 //Catch:{ IOException -> 0x0096 }
        r0.setReadTimeout(r2);	 //Catch:{ IOException -> 0x0096 }
        r0.connect();	 //Catch:{ IOException -> 0x0096 }
        r1 = r0.getInputStream();	 //Catch:{ IOException -> 0x0096 }
        r2 = r0.getResponseCode();	 //Catch:{ IOException -> 0x0096 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 != r3) goto L_0x0041;
    L_0x0035:
        r2 = r5.a;	 //Catch:{ IOException -> 0x0096 }
        r3 = 1;
        r2.q = r3;	 //Catch:{ IOException -> 0x0096 }
        r2 = "Adwo SDK";
        r3 = "Charging successfully.";
        android.util.Log.v(r2, r3);	 //Catch:{ IOException -> 0x0096 }
    L_0x0041:
        if (r1 == 0) goto L_0x0046;
    L_0x0043:
        r1.close();	 //Catch:{ Exception -> 0x0094 }
    L_0x0046:
        if (r0 == 0) goto L_0x004b;
    L_0x0048:
        r0.disconnect();	 //Catch:{ Exception -> 0x0094 }
    L_0x004b:
        r0 = r5.a;
        r0 = r0.i;
        if (r0 == 0) goto L_0x005f;
    L_0x0051:
        r0 = r5.a;
        r0 = r0.i;
        r2 = r0.size();
        if (r2 == 0) goto L_0x005f;
    L_0x005b:
        r0 = 0;
        r1 = r0;
    L_0x005d:
        if (r1 < r2) goto L_0x0083;
    L_0x005f:
        return;
    L_0x0060:
        r0 = move-exception;
        r0 = r1;
    L_0x0062:
        r2 = "Adwo SDK";
        r3 = new java.lang.StringBuilder;
        r4 = "Could not determine final click destination URL.  Will try to follow anyway.  ";
        r3.<init>(r4);
        r4 = r5.a;
        r4 = r4.c;
        r3 = r3.append(r4);
        r3 = r3.toString();
        android.util.Log.w(r2, r3);
        goto L_0x0041;
    L_0x007b:
        r0 = "Adwo SDK";
        r1 = "This ad had already been charged for showing. ";
        android.util.Log.e(r0, r1);
        goto L_0x004b;
    L_0x0083:
        r0 = r5.a;
        r0 = r0.i;
        r0 = r0.get(r1);
        r0 = (java.lang.String) r0;
        com.zkmm.adsdk.s.a(r0);
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x005d;
    L_0x0094:
        r0 = move-exception;
        goto L_0x004b;
    L_0x0096:
        r2 = move-exception;
        goto L_0x0062;
        */

    }
}