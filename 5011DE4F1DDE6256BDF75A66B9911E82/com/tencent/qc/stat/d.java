package com.tencent.qc.stat;

// compiled from: ProGuard
class d implements Runnable {
    final /* synthetic */ StatStore a;

    d(StatStore r1_StatStore) {
        this.a = r1_StatStore;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r9_this = this;
        r8 = 0;
        r0 = r9.a;	 //Catch:{ Exception -> 0x0065, all -> 0x005b }
        r0 = r0.d;	 //Catch:{ Exception -> 0x0065, all -> 0x005b }
        r0 = r0.getReadableDatabase();	 //Catch:{ Exception -> 0x0065, all -> 0x005b }
        r1 = "config";
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 //Catch:{ Exception -> 0x0065, all -> 0x005b }
    L_0x0017:
        r0 = r1.moveToNext();	 //Catch:{ Exception -> 0x0047 }
        if (r0 == 0) goto L_0x0055;
    L_0x001d:
        r0 = 0;
        r0 = r1.getInt(r0);	 //Catch:{ Exception -> 0x0047 }
        r2 = 1;
        r2 = r1.getString(r2);	 //Catch:{ Exception -> 0x0047 }
        r3 = 2;
        r3 = r1.getString(r3);	 //Catch:{ Exception -> 0x0047 }
        r4 = 3;
        r4 = r1.getInt(r4);	 //Catch:{ Exception -> 0x0047 }
        r5 = new com.tencent.qc.stat.r;	 //Catch:{ Exception -> 0x0047 }
        r5.<init>(r0);	 //Catch:{ Exception -> 0x0047 }
        r5.a = r0;	 //Catch:{ Exception -> 0x0047 }
        r0 = new org.json.JSONObject;	 //Catch:{ Exception -> 0x0047 }
        r0.<init>(r2);	 //Catch:{ Exception -> 0x0047 }
        r5.b = r0;	 //Catch:{ Exception -> 0x0047 }
        r5.c = r3;	 //Catch:{ Exception -> 0x0047 }
        r5.d = r4;	 //Catch:{ Exception -> 0x0047 }
        com.tencent.qc.stat.StatConfig.a(r5);	 //Catch:{ Exception -> 0x0047 }
        goto L_0x0017;
    L_0x0047:
        r0 = move-exception;
    L_0x0048:
        r2 = com.tencent.qc.stat.StatStore.e;	 //Catch:{ all -> 0x0063 }
        r2.b(r0);	 //Catch:{ all -> 0x0063 }
        if (r1 == 0) goto L_0x0054;
    L_0x0051:
        r1.close();
    L_0x0054:
        return;
    L_0x0055:
        if (r1 == 0) goto L_0x0054;
    L_0x0057:
        r1.close();
        goto L_0x0054;
    L_0x005b:
        r0 = move-exception;
        r1 = r8;
    L_0x005d:
        if (r1 == 0) goto L_0x0062;
    L_0x005f:
        r1.close();
    L_0x0062:
        throw r0;
    L_0x0063:
        r0 = move-exception;
        goto L_0x005d;
    L_0x0065:
        r0 = move-exception;
        r1 = r8;
        goto L_0x0048;
        */

    }
}