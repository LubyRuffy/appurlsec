package com.flurry.android;

import java.util.Map;

// compiled from: SourceFile
final class af implements Runnable {
    private /* synthetic */ Map a;
    private /* synthetic */ InstallReceiver b;

    af(InstallReceiver r1_InstallReceiver, Map r2_Map) {
        this.b = r1_InstallReceiver;
        this.a = r2_Map;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r8_this = this;
        r2 = 0;
        r5 = 1;
        r6 = 0;
        r1 = r8.b;	 //Catch:{ Throwable -> 0x009c }
        r1 = r1.b;	 //Catch:{ Throwable -> 0x009c }
        r1 = r1.getParentFile();	 //Catch:{ Throwable -> 0x009c }
        r3 = r1.mkdirs();	 //Catch:{ Throwable -> 0x009c }
        if (r3 != 0) goto L_0x0035;
    L_0x0013:
        r3 = r1.exists();	 //Catch:{ Throwable -> 0x009c }
        if (r3 != 0) goto L_0x0035;
    L_0x0019:
        r3 = "InstallReceiver";
        r4 = new java.lang.StringBuilder;	 //Catch:{ Throwable -> 0x009c }
        r4.<init>();	 //Catch:{ Throwable -> 0x009c }
        r5 = "Unable to create persistent dir: ";
        r4 = r4.append(r5);	 //Catch:{ Throwable -> 0x009c }
        r1 = r4.append(r1);	 //Catch:{ Throwable -> 0x009c }
        r1 = r1.toString();	 //Catch:{ Throwable -> 0x009c }
        com.flurry.android.i.b(r3, r1);	 //Catch:{ Throwable -> 0x009c }
        com.flurry.android.ad.a(r2);
    L_0x0034:
        return;
    L_0x0035:
        r1 = new java.io.FileOutputStream;	 //Catch:{ Throwable -> 0x009c }
        r3 = r8.b;	 //Catch:{ Throwable -> 0x009c }
        r3 = r3.b;	 //Catch:{ Throwable -> 0x009c }
        r1.<init>(r3);	 //Catch:{ Throwable -> 0x009c }
        r3 = new java.io.DataOutputStream;	 //Catch:{ Throwable -> 0x009c }
        r3.<init>(r1);	 //Catch:{ Throwable -> 0x009c }
        r1 = r8.a;	 //Catch:{ Throwable -> 0x0079, all -> 0x008c }
        r1 = r1.entrySet();	 //Catch:{ Throwable -> 0x0079, all -> 0x008c }
        r7 = r1.iterator();	 //Catch:{ Throwable -> 0x0079, all -> 0x008c }
        r4 = r5;
    L_0x0050:
        r1 = r7.hasNext();	 //Catch:{ Throwable -> 0x0079, all -> 0x008c }
        if (r1 == 0) goto L_0x0092;
    L_0x0056:
        r1 = r7.next();	 //Catch:{ Throwable -> 0x0079, all -> 0x008c }
        r0 = r1;
        r0 = (java.util.Map.Entry) r0;	 //Catch:{ Throwable -> 0x0079, all -> 0x008c }
        r2 = r0;
        if (r4 != r5) goto L_0x0086;
    L_0x0060:
        r4 = r6;
    L_0x0061:
        r1 = r2.getKey();	 //Catch:{ Throwable -> 0x0079, all -> 0x008c }
        r1 = (java.lang.String) r1;	 //Catch:{ Throwable -> 0x0079, all -> 0x008c }
        r3.writeUTF(r1);	 //Catch:{ Throwable -> 0x0079, all -> 0x008c }
        r1 = "=";
        r3.writeUTF(r1);	 //Catch:{ Throwable -> 0x0079, all -> 0x008c }
        r1 = r2.getValue();	 //Catch:{ Throwable -> 0x0079, all -> 0x008c }
        r1 = (java.lang.String) r1;	 //Catch:{ Throwable -> 0x0079, all -> 0x008c }
        r3.writeUTF(r1);	 //Catch:{ Throwable -> 0x0079, all -> 0x008c }
        goto L_0x0050;
    L_0x0079:
        r1 = move-exception;
        r2 = r3;
    L_0x007b:
        r3 = "InstallReceiver";
        r4 = "";
        com.flurry.android.i.b(r3, r4, r1);	 //Catch:{ all -> 0x009a }
        com.flurry.android.ad.a(r2);
        goto L_0x0034;
    L_0x0086:
        r1 = "&";
        r3.writeUTF(r1);	 //Catch:{ Throwable -> 0x0079, all -> 0x008c }
        goto L_0x0061;
    L_0x008c:
        r1 = move-exception;
        r2 = r3;
    L_0x008e:
        com.flurry.android.ad.a(r2);
        throw r1;
    L_0x0092:
        r1 = 0;
        r3.writeShort(r1);	 //Catch:{ Throwable -> 0x0079, all -> 0x008c }
        com.flurry.android.ad.a(r3);
        goto L_0x0034;
    L_0x009a:
        r1 = move-exception;
        goto L_0x008e;
    L_0x009c:
        r1 = move-exception;
        goto L_0x007b;
        */

    }
}