package com.flurry.android;

import android.os.SystemClock;
import java.util.Map;

// compiled from: SourceFile
final class u {
    private String a;
    private Map b;
    private long c;
    private boolean d;
    private long e;
    private byte[] f;

    public u(String r1_String, Map r2_Map, long r3j, boolean r5z) {
        this.a = r1_String;
        this.b = r2_Map;
        this.c = r3j;
        this.d = r5z;
    }

    public final void a() {
        this.e = SystemClock.elapsedRealtime() - this.c;
    }

    public final boolean a(String r5_String) {
        return this.d && this.e == 0 && this.a.equals(r5_String);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] b() {
        /*
        r6_this = this;
        r0 = r6.f;
        if (r0 != 0) goto L_0x0032;
    L_0x0004:
        r0 = 0;
        r3 = new java.io.ByteArrayOutputStream;	 //Catch:{ IOException -> 0x008b, all -> 0x007e }
        r3.<init>();	 //Catch:{ IOException -> 0x008b, all -> 0x007e }
        r2 = new java.io.DataOutputStream;	 //Catch:{ IOException -> 0x008b, all -> 0x007e }
        r2.<init>(r3);	 //Catch:{ IOException -> 0x008b, all -> 0x007e }
        r0 = r6.a;	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r2.writeUTF(r0);	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r0 = r6.b;	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        if (r0 != 0) goto L_0x0035;
    L_0x0018:
        r0 = 0;
        r2.writeShort(r0);	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
    L_0x001c:
        r0 = r6.c;	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r2.writeLong(r0);	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r0 = r6.e;	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r2.writeLong(r0);	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r2.flush();	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r0 = r3.toByteArray();	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r6.f = r0;	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        com.flurry.android.ad.a(r2);
    L_0x0032:
        r0 = r6.f;
        return r0;
    L_0x0035:
        r0 = r6.b;	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r0 = r0.size();	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r2.writeShort(r0);	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r0 = r6.b;	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r0 = r0.entrySet();	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r4 = r0.iterator();	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
    L_0x0048:
        r0 = r4.hasNext();	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        if (r0 == 0) goto L_0x001c;
    L_0x004e:
        r0 = r4.next();	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r0 = (java.util.Map.Entry) r0;	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r1 = r0.getKey();	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r1 = (java.lang.String) r1;	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r5 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        r1 = com.flurry.android.ad.a(r1, r5);	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r2.writeUTF(r1);	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r0 = r0.getValue();	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r0 = (java.lang.String) r0;	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r1 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        r0 = com.flurry.android.ad.a(r0, r1);	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        r2.writeUTF(r0);	 //Catch:{ IOException -> 0x0073, all -> 0x0085 }
        goto L_0x0048;
    L_0x0073:
        r0 = move-exception;
        r0 = r2;
    L_0x0075:
        r1 = 0;
        r1 = new byte[r1];	 //Catch:{ all -> 0x0087 }
        r6.f = r1;	 //Catch:{ all -> 0x0087 }
        com.flurry.android.ad.a(r0);
        goto L_0x0032;
    L_0x007e:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0081:
        com.flurry.android.ad.a(r2);
        throw r0;
    L_0x0085:
        r0 = move-exception;
        goto L_0x0081;
    L_0x0087:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x0081;
    L_0x008b:
        r1 = move-exception;
        goto L_0x0075;
        */

    }
}