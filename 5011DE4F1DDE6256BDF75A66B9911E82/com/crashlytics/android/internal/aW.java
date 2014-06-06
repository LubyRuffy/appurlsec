package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: SourceFile
public class aW {
    private aZ a;
    private final aY b;
    private final ah c;
    private final aN d;
    private final ba e;

    public aW(aZ r1_aZ, ah r2_ah, aY r3_aY, aN r4_aN, ba r5_ba) {
        this.a = r1_aZ;
        this.c = r2_ah;
        this.b = r3_aY;
        this.d = r4_aN;
        this.e = r5_ba;
    }

    private void a(JSONObject r5_JSONObject, String r6_String) throws JSONException {
        if (!ab.e(v.a().getContext())) {
            r5_JSONObject = this.b.a(r5_JSONObject);
        }
        v.a().b().a(Crashlytics.TAG, r6_String + r5_JSONObject.toString());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private aX b(aV r8_aV) {
        /*
        r7_this = this;
        r1 = 0;
        r0 = com.crashlytics.android.internal.aV.b;	 //Catch:{ Exception -> 0x007c }
        r0 = r0.equals(r8);	 //Catch:{ Exception -> 0x007c }
        if (r0 != 0) goto L_0x0058;
    L_0x0009:
        r0 = r7.d;	 //Catch:{ Exception -> 0x007c }
        r2 = r0.a();	 //Catch:{ Exception -> 0x007c }
        if (r2 == 0) goto L_0x006c;
    L_0x0011:
        r0 = r7.b;	 //Catch:{ Exception -> 0x007c }
        r3 = r7.c;	 //Catch:{ Exception -> 0x007c }
        r0 = r0.a(r3, r2);	 //Catch:{ Exception -> 0x007c }
        if (r0 == 0) goto L_0x005a;
    L_0x001b:
        r3 = "Loaded cached settings: ";
        r7.a(r2, r3);	 //Catch:{ Exception -> 0x007c }
        r2 = r7.c;	 //Catch:{ Exception -> 0x007c }
        r2 = r2.a();	 //Catch:{ Exception -> 0x007c }
        r4 = com.crashlytics.android.internal.aV.c;	 //Catch:{ Exception -> 0x007c }
        r4 = r4.equals(r8);	 //Catch:{ Exception -> 0x007c }
        if (r4 != 0) goto L_0x0037;
    L_0x002e:
        r4 = r0.f;	 //Catch:{ Exception -> 0x007c }
        r2 = (r4 > r2 ? 1 : (r4 == r2? 0 : -1));
        if (r2 >= 0) goto L_0x0047;
    L_0x0034:
        r2 = 1;
    L_0x0035:
        if (r2 != 0) goto L_0x0049;
    L_0x0037:
        r1 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x0090 }
        r1 = r1.b();	 //Catch:{ Exception -> 0x0090 }
        r2 = "Crashlytics";
        r3 = "Returning cached settings.";
        r1.a(r2, r3);	 //Catch:{ Exception -> 0x0090 }
    L_0x0046:
        return r0;
    L_0x0047:
        r2 = 0;
        goto L_0x0035;
    L_0x0049:
        r0 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x007c }
        r0 = r0.b();	 //Catch:{ Exception -> 0x007c }
        r2 = "Crashlytics";
        r3 = "Cached settings have expired.";
        r0.a(r2, r3);	 //Catch:{ Exception -> 0x007c }
    L_0x0058:
        r0 = r1;
        goto L_0x0046;
    L_0x005a:
        r0 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x007c }
        r0 = r0.b();	 //Catch:{ Exception -> 0x007c }
        r2 = "Crashlytics";
        r3 = "Failed to transform cached settings data.";
        r4 = 0;
        r0.a(r2, r3, r4);	 //Catch:{ Exception -> 0x007c }
        r0 = r1;
        goto L_0x0046;
    L_0x006c:
        r0 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x007c }
        r0 = r0.b();	 //Catch:{ Exception -> 0x007c }
        r2 = "Crashlytics";
        r3 = "No cached settings data found.";
        r0.a(r2, r3);	 //Catch:{ Exception -> 0x007c }
        goto L_0x0058;
    L_0x007c:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x0080:
        r2 = com.crashlytics.android.internal.v.a();
        r2 = r2.b();
        r3 = "Crashlytics";
        r4 = "Failed to get cached settings";
        r2.a(r3, r4, r1);
        goto L_0x0046;
    L_0x0090:
        r1 = move-exception;
        goto L_0x0080;
        */

    }

    public aX a() {
        return a(aV.a);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public aX a(aV r7_aV) {
        /*
        r6_this = this;
        r1 = 0;
        r0 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x0039 }
        r0 = r0.f();	 //Catch:{ Exception -> 0x0039 }
        if (r0 != 0) goto L_0x000f;
    L_0x000b:
        r1 = r6.b(r7);	 //Catch:{ Exception -> 0x0039 }
    L_0x000f:
        if (r1 != 0) goto L_0x002f;
    L_0x0011:
        r0 = r6.e;	 //Catch:{ Exception -> 0x004d }
        r2 = r6.a;	 //Catch:{ Exception -> 0x004d }
        r0 = r0.a(r2);	 //Catch:{ Exception -> 0x004d }
        if (r0 == 0) goto L_0x002f;
    L_0x001b:
        r2 = r6.b;	 //Catch:{ Exception -> 0x004d }
        r3 = r6.c;	 //Catch:{ Exception -> 0x004d }
        r1 = r2.a(r3, r0);	 //Catch:{ Exception -> 0x004d }
        r2 = r6.d;	 //Catch:{ Exception -> 0x004d }
        r3 = r1.f;	 //Catch:{ Exception -> 0x004d }
        r2.a(r3, r0);	 //Catch:{ Exception -> 0x004d }
        r2 = "Loaded settings: ";
        r6.a(r0, r2);	 //Catch:{ Exception -> 0x004d }
    L_0x002f:
        r0 = r1;
        if (r0 != 0) goto L_0x0038;
    L_0x0032:
        r1 = com.crashlytics.android.internal.aV.c;	 //Catch:{ Exception -> 0x0052 }
        r0 = r6.b(r1);	 //Catch:{ Exception -> 0x0052 }
    L_0x0038:
        return r0;
    L_0x0039:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
    L_0x003d:
        r2 = com.crashlytics.android.internal.v.a();
        r2 = r2.b();
        r3 = "Crashlytics";
        r4 = "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.";
        r2.a(r3, r4, r1);
        goto L_0x0038;
    L_0x004d:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x003d;
    L_0x0052:
        r1 = move-exception;
        goto L_0x003d;
        */

    }
}