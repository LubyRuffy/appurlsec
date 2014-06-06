package com.crashlytics.android.internal;

import org.json.JSONObject;

// compiled from: SourceFile
public class aN {
    aN() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JSONObject a() {
        /*
        r6_this = this;
        r1 = 0;
        r0 = com.crashlytics.android.internal.v.a();
        r0 = r0.b();
        r2 = "Crashlytics";
        r3 = "Reading cached settings...";
        r0.a(r2, r3);
        r0 = new java.io.File;	 //Catch:{ Exception -> 0x004b, all -> 0x0063 }
        r2 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x004b, all -> 0x0063 }
        r2 = r2.h();	 //Catch:{ Exception -> 0x004b, all -> 0x0063 }
        r3 = "com.crashlytics.settings.json";
        r0.<init>(r2, r3);	 //Catch:{ Exception -> 0x004b, all -> 0x0063 }
        r2 = r0.exists();	 //Catch:{ Exception -> 0x004b, all -> 0x0063 }
        if (r2 == 0) goto L_0x003a;
    L_0x0025:
        r2 = new java.io.FileInputStream;	 //Catch:{ Exception -> 0x004b, all -> 0x0063 }
        r2.<init>(r0);	 //Catch:{ Exception -> 0x004b, all -> 0x0063 }
        r3 = com.crashlytics.android.internal.ab.a(r2);	 //Catch:{ Exception -> 0x006d }
        r0 = new org.json.JSONObject;	 //Catch:{ Exception -> 0x006d }
        r0.<init>(r3);	 //Catch:{ Exception -> 0x006d }
        r1 = r2;
    L_0x0034:
        r2 = "Error while closing settings cache file.";
        com.crashlytics.android.internal.ab.a(r1, r2);
    L_0x0039:
        return r0;
    L_0x003a:
        r0 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x004b, all -> 0x0063 }
        r0 = r0.b();	 //Catch:{ Exception -> 0x004b, all -> 0x0063 }
        r2 = "Crashlytics";
        r3 = "No cached settings found.";
        r0.a(r2, r3);	 //Catch:{ Exception -> 0x004b, all -> 0x0063 }
        r0 = r1;
        goto L_0x0034;
    L_0x004b:
        r0 = move-exception;
        r2 = r1;
    L_0x004d:
        r3 = com.crashlytics.android.internal.v.a();	 //Catch:{ all -> 0x006a }
        r3 = r3.b();	 //Catch:{ all -> 0x006a }
        r4 = "Crashlytics";
        r5 = "Failed to fetch cached settings";
        r3.a(r4, r5, r0);	 //Catch:{ all -> 0x006a }
        r0 = "Error while closing settings cache file.";
        com.crashlytics.android.internal.ab.a(r2, r0);
        r0 = r1;
        goto L_0x0039;
    L_0x0063:
        r0 = move-exception;
    L_0x0064:
        r2 = "Error while closing settings cache file.";
        com.crashlytics.android.internal.ab.a(r1, r2);
        throw r0;
    L_0x006a:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0064;
    L_0x006d:
        r0 = move-exception;
        goto L_0x004d;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(long r6j, JSONObject r8_JSONObject) {
        /*
        r5_this = this;
        r0 = com.crashlytics.android.internal.v.a();
        r0 = r0.b();
        r1 = "Crashlytics";
        r2 = "Writing settings to cache file...";
        r0.a(r1, r2);
        if (r8 == 0) goto L_0x003a;
    L_0x0011:
        r2 = 0;
        r0 = "expires_at";
        r8.put(r0, r6);	 //Catch:{ Exception -> 0x003b, all -> 0x0052 }
        r1 = new java.io.FileWriter;	 //Catch:{ Exception -> 0x003b, all -> 0x0052 }
        r0 = new java.io.File;	 //Catch:{ Exception -> 0x003b, all -> 0x0052 }
        r3 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x003b, all -> 0x0052 }
        r3 = r3.h();	 //Catch:{ Exception -> 0x003b, all -> 0x0052 }
        r4 = "com.crashlytics.settings.json";
        r0.<init>(r3, r4);	 //Catch:{ Exception -> 0x003b, all -> 0x0052 }
        r1.<init>(r0);	 //Catch:{ Exception -> 0x003b, all -> 0x0052 }
        r0 = r8.toString();	 //Catch:{ Exception -> 0x005c }
        r1.write(r0);	 //Catch:{ Exception -> 0x005c }
        r1.flush();	 //Catch:{ Exception -> 0x005c }
        r0 = "Failed to close settings writer.";
        com.crashlytics.android.internal.ab.a(r1, r0);
    L_0x003a:
        return;
    L_0x003b:
        r0 = move-exception;
        r1 = r2;
    L_0x003d:
        r2 = com.crashlytics.android.internal.v.a();	 //Catch:{ all -> 0x0059 }
        r2 = r2.b();	 //Catch:{ all -> 0x0059 }
        r3 = "Crashlytics";
        r4 = "Failed to cache settings";
        r2.a(r3, r4, r0);	 //Catch:{ all -> 0x0059 }
        r0 = "Failed to close settings writer.";
        com.crashlytics.android.internal.ab.a(r1, r0);
        goto L_0x003a;
    L_0x0052:
        r0 = move-exception;
    L_0x0053:
        r1 = "Failed to close settings writer.";
        com.crashlytics.android.internal.ab.a(r2, r1);
        throw r0;
    L_0x0059:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0053;
    L_0x005c:
        r0 = move-exception;
        goto L_0x003d;
        */

    }
}