package com.crashlytics.android.internal;

import org.json.JSONObject;

// compiled from: SourceFile
final class bo extends Z implements ba {
    public bo(String r2_String, String r3_String, av r4_av) {
        this(r2_String, r3_String, r4_av, ax.a);
    }

    private bo(String r1_String, String r2_String, av r3_av, ax r4_ax) {
        super(r1_String, r2_String, r3_av, r4_ax);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final JSONObject a(aZ r8_aZ) {
        /*
        r7_this = this;
        r1 = 0;
        r0 = new java.util.HashMap;	 //Catch:{ Exception -> 0x00d3, all -> 0x011b }
        r0.<init>();	 //Catch:{ Exception -> 0x00d3, all -> 0x011b }
        r2 = "build_version";
        r3 = r8.e;	 //Catch:{ Exception -> 0x00d3, all -> 0x011b }
        r0.put(r2, r3);	 //Catch:{ Exception -> 0x00d3, all -> 0x011b }
        r2 = "display_version";
        r3 = r8.d;	 //Catch:{ Exception -> 0x00d3, all -> 0x011b }
        r0.put(r2, r3);	 //Catch:{ Exception -> 0x00d3, all -> 0x011b }
        r2 = "source";
        r3 = r8.f;	 //Catch:{ Exception -> 0x00d3, all -> 0x011b }
        r3 = java.lang.Integer.toString(r3);	 //Catch:{ Exception -> 0x00d3, all -> 0x011b }
        r0.put(r2, r3);	 //Catch:{ Exception -> 0x00d3, all -> 0x011b }
        r2 = r8.g;	 //Catch:{ Exception -> 0x00d3, all -> 0x011b }
        if (r2 == 0) goto L_0x002a;
    L_0x0023:
        r2 = "icon_hash";
        r3 = r8.g;	 //Catch:{ Exception -> 0x00d3, all -> 0x011b }
        r0.put(r2, r3);	 //Catch:{ Exception -> 0x00d3, all -> 0x011b }
    L_0x002a:
        r2 = r8.c;	 //Catch:{ Exception -> 0x00d3, all -> 0x011b }
        r3 = com.crashlytics.android.internal.ab.e(r2);	 //Catch:{ Exception -> 0x00d3, all -> 0x011b }
        if (r3 != 0) goto L_0x0037;
    L_0x0032:
        r3 = "instance";
        r0.put(r3, r2);	 //Catch:{ Exception -> 0x00d3, all -> 0x011b }
    L_0x0037:
        r2 = r7.a(r0);	 //Catch:{ Exception -> 0x00d3, all -> 0x011b }
        r3 = "X-CRASHLYTICS-API-KEY";
        r4 = r8.a;	 //Catch:{ Exception -> 0x0144 }
        r3 = r2.a(r3, r4);	 //Catch:{ Exception -> 0x0144 }
        r4 = "X-CRASHLYTICS-API-CLIENT-TYPE";
        r5 = "android";
        r3 = r3.a(r4, r5);	 //Catch:{ Exception -> 0x0144 }
        r4 = "X-CRASHLYTICS-D";
        r5 = r8.b;	 //Catch:{ Exception -> 0x0144 }
        r3 = r3.a(r4, r5);	 //Catch:{ Exception -> 0x0144 }
        r4 = "X-CRASHLYTICS-API-CLIENT-VERSION";
        r5 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x0144 }
        r5 = r5.getVersion();	 //Catch:{ Exception -> 0x0144 }
        r3 = r3.a(r4, r5);	 //Catch:{ Exception -> 0x0144 }
        r4 = "Accept";
        r5 = "application/json";
        r2 = r3.a(r4, r5);	 //Catch:{ Exception -> 0x0144 }
        r3 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x0144 }
        r3 = r3.b();	 //Catch:{ Exception -> 0x0144 }
        r4 = "Crashlytics";
        r5 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0144 }
        r6 = "Requesting settings from ";
        r5.<init>(r6);	 //Catch:{ Exception -> 0x0144 }
        r6 = r7.a();	 //Catch:{ Exception -> 0x0144 }
        r5 = r5.append(r6);	 //Catch:{ Exception -> 0x0144 }
        r5 = r5.toString();	 //Catch:{ Exception -> 0x0144 }
        r3.a(r4, r5);	 //Catch:{ Exception -> 0x0144 }
        r3 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x0144 }
        r3 = r3.b();	 //Catch:{ Exception -> 0x0144 }
        r4 = "Crashlytics";
        r5 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0144 }
        r6 = "Settings query params were: ";
        r5.<init>(r6);	 //Catch:{ Exception -> 0x0144 }
        r0 = r5.append(r0);	 //Catch:{ Exception -> 0x0144 }
        r0 = r0.toString();	 //Catch:{ Exception -> 0x0144 }
        r3.a(r4, r0);	 //Catch:{ Exception -> 0x0144 }
        r0 = new org.json.JSONObject;	 //Catch:{ Exception -> 0x0144 }
        r3 = r2.c();	 //Catch:{ Exception -> 0x0144 }
        r0.<init>(r3);	 //Catch:{ Exception -> 0x0144 }
        if (r2 == 0) goto L_0x00d2;
    L_0x00b0:
        r1 = com.crashlytics.android.internal.v.a();
        r1 = r1.b();
        r3 = "Crashlytics";
        r4 = new java.lang.StringBuilder;
        r5 = "Settings request ID: ";
        r4.<init>(r5);
        r5 = "X-REQUEST-ID";
        r2 = r2.a(r5);
        r2 = r4.append(r2);
        r2 = r2.toString();
        r1.a(r3, r2);
    L_0x00d2:
        return r0;
    L_0x00d3:
        r0 = move-exception;
        r2 = r1;
    L_0x00d5:
        r3 = com.crashlytics.android.internal.v.a();	 //Catch:{ all -> 0x0142 }
        r3 = r3.b();	 //Catch:{ all -> 0x0142 }
        r4 = "Crashlytics";
        r5 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x0142 }
        r6 = "Failed to retrieve settings from ";
        r5.<init>(r6);	 //Catch:{ all -> 0x0142 }
        r6 = r7.a();	 //Catch:{ all -> 0x0142 }
        r5 = r5.append(r6);	 //Catch:{ all -> 0x0142 }
        r5 = r5.toString();	 //Catch:{ all -> 0x0142 }
        r3.a(r4, r5, r0);	 //Catch:{ all -> 0x0142 }
        if (r2 == 0) goto L_0x0146;
    L_0x00f7:
        r0 = com.crashlytics.android.internal.v.a();
        r0 = r0.b();
        r3 = "Crashlytics";
        r4 = new java.lang.StringBuilder;
        r5 = "Settings request ID: ";
        r4.<init>(r5);
        r5 = "X-REQUEST-ID";
        r2 = r2.a(r5);
        r2 = r4.append(r2);
        r2 = r2.toString();
        r0.a(r3, r2);
        r0 = r1;
        goto L_0x00d2;
    L_0x011b:
        r0 = move-exception;
        r2 = r1;
    L_0x011d:
        if (r2 == 0) goto L_0x0141;
    L_0x011f:
        r1 = com.crashlytics.android.internal.v.a();
        r1 = r1.b();
        r3 = "Crashlytics";
        r4 = new java.lang.StringBuilder;
        r5 = "Settings request ID: ";
        r4.<init>(r5);
        r5 = "X-REQUEST-ID";
        r2 = r2.a(r5);
        r2 = r4.append(r2);
        r2 = r2.toString();
        r1.a(r3, r2);
    L_0x0141:
        throw r0;
    L_0x0142:
        r0 = move-exception;
        goto L_0x011d;
    L_0x0144:
        r0 = move-exception;
        goto L_0x00d5;
    L_0x0146:
        r0 = r1;
        goto L_0x00d2;
        */

    }
}