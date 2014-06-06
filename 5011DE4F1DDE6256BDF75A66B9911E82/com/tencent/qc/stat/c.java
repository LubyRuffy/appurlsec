package com.tencent.qc.stat;

// compiled from: ProGuard
class c implements Runnable {
    final /* synthetic */ r a;
    final /* synthetic */ StatStore b;

    c(StatStore r1_StatStore, r r2_r) {
        this.b = r1_StatStore;
        this.a = r2_r;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r13_this = this;
        r10 = 1;
        r9 = 0;
        r8 = 0;
        r0 = r13.a;
        r11 = r0.a();
        r0 = r11.length();
        if (r0 <= 0) goto L_0x00b3;
    L_0x000f:
        r0 = com.tencent.qc.stat.common.StatCommonHelper.a(r11);
        r1 = r13.a;
        r1 = r1.c;
        r1 = r0.equals(r1);
        if (r1 != 0) goto L_0x00b3;
    L_0x001d:
        r12 = new android.content.ContentValues;
        r12.<init>();
        r1 = "content";
        r2 = r13.a;
        r2 = r2.b;
        r2 = r2.toString();
        r12.put(r1, r2);
        r1 = "md5sum";
        r12.put(r1, r0);
        r1 = r13.a;
        r1.c = r0;
        r0 = "version";
        r1 = r13.a;
        r1 = r1.d;
        r1 = java.lang.Integer.valueOf(r1);
        r12.put(r0, r1);
        r0 = r13.b;	 //Catch:{ Exception -> 0x00b4, all -> 0x00c4 }
        r0 = com.tencent.qc.stat.StatStore.b(r0);	 //Catch:{ Exception -> 0x00b4, all -> 0x00c4 }
        r0 = r0.getReadableDatabase();	 //Catch:{ Exception -> 0x00b4, all -> 0x00c4 }
        r1 = "config";
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 //Catch:{ Exception -> 0x00b4, all -> 0x00c4 }
    L_0x005b:
        r0 = r1.moveToNext();	 //Catch:{ Exception -> 0x0107 }
        if (r0 == 0) goto L_0x010c;
    L_0x0061:
        r0 = 0;
        r0 = r1.getInt(r0);	 //Catch:{ Exception -> 0x0107 }
        r2 = r13.a;	 //Catch:{ Exception -> 0x0107 }
        r2 = r2.a;	 //Catch:{ Exception -> 0x0107 }
        if (r0 != r2) goto L_0x005b;
    L_0x006c:
        r0 = r10;
    L_0x006d:
        if (r1 == 0) goto L_0x0072;
    L_0x006f:
        r1.close();
    L_0x0072:
        if (r10 != r0) goto L_0x00cc;
    L_0x0074:
        r0 = r13.b;
        r0 = com.tencent.qc.stat.StatStore.b(r0);
        r0 = r0.getWritableDatabase();
        r1 = "config";
        r2 = "type=?";
        r3 = new java.lang.String[r10];
        r4 = r13.a;
        r4 = r4.a;
        r4 = java.lang.Integer.toString(r4);
        r3[r9] = r4;
        r0 = r0.update(r1, r12, r2, r3);
        r0 = (long) r0;
    L_0x0093:
        r2 = -1;
        r0 = (r0 > r2 ? 1 : (r0 == r2? 0 : -1));
        if (r0 != 0) goto L_0x00ea;
    L_0x0099:
        r0 = com.tencent.qc.stat.StatStore.c();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Failed to store cfg:";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        r0.e(r1);
    L_0x00b3:
        return;
    L_0x00b4:
        r0 = move-exception;
        r1 = r8;
    L_0x00b6:
        r2 = com.tencent.qc.stat.StatStore.c();	 //Catch:{ all -> 0x0105 }
        r2.b(r0);	 //Catch:{ all -> 0x0105 }
        if (r1 == 0) goto L_0x0109;
    L_0x00bf:
        r1.close();
        r0 = r9;
        goto L_0x0072;
    L_0x00c4:
        r0 = move-exception;
        r1 = r8;
    L_0x00c6:
        if (r1 == 0) goto L_0x00cb;
    L_0x00c8:
        r1.close();
    L_0x00cb:
        throw r0;
    L_0x00cc:
        r0 = "type";
        r1 = r13.a;
        r1 = r1.a;
        r1 = java.lang.Integer.valueOf(r1);
        r12.put(r0, r1);
        r0 = r13.b;
        r0 = com.tencent.qc.stat.StatStore.b(r0);
        r0 = r0.getWritableDatabase();
        r1 = "config";
        r0 = r0.insert(r1, r8, r12);
        goto L_0x0093;
    L_0x00ea:
        r0 = com.tencent.qc.stat.StatStore.c();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Sucessed to store cfg:";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        r0.b(r1);
        goto L_0x00b3;
    L_0x0105:
        r0 = move-exception;
        goto L_0x00c6;
    L_0x0107:
        r0 = move-exception;
        goto L_0x00b6;
    L_0x0109:
        r0 = r9;
        goto L_0x0072;
    L_0x010c:
        r0 = r9;
        goto L_0x006d;
        */

    }
}