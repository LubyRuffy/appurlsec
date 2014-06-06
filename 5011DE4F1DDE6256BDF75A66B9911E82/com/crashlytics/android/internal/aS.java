package com.crashlytics.android.internal;

import android.content.Context;
import com.crashlytics.android.Crashlytics;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import javax.crypto.Cipher;
import org.json.JSONObject;

// compiled from: SourceFile
public final class aS {
    private final AtomicReference<aX> a;
    private aW b;
    private boolean c;

    private aS() {
        this.a = new AtomicReference();
        this.c = false;
    }

    public static aS a() {
        return bp.a();
    }

    private static String a(String r10_String, String r11_String, Context r12_Context) {
        try {
            Cipher r2_Cipher = ab.b(1, ab.a(r10_String + r11_String.replaceAll("\\.", new StringBuffer(new String(new char[]{'s', 'l', 'c'})).reverse().toString())));
            JSONObject r3_JSONObject = new JSONObject();
            ao r4_ao = new ao(r12_Context);
            r3_JSONObject.put("APPLICATION_INSTALLATION_UUID".toLowerCase(Locale.US), r4_ao.b());
            Iterator r5_Iterator = r4_ao.f().entrySet().iterator();
            while (r5_Iterator.hasNext()) {
                Entry r0_Entry = (Entry) r5_Iterator.next();
                try {
                    r3_JSONObject.put(((ap) r0_Entry.getKey()).name().toLowerCase(Locale.US), r0_Entry.getValue());
                } catch (Exception e) {
                    v.a().b().a(Crashlytics.TAG, new StringBuilder("Could not write value to JSON: ").append(((ap) r0_Entry.getKey()).name()).toString(), e);
                }
            }
            r3_JSONObject.put("os_version", r4_ao.c());
            r3_JSONObject.put("model", r4_ao.d());
            String r0_String = RContactStorage.PRIMARY_KEY;
            if (r3_JSONObject.length() <= 0) {
                return r0_String;
            }
            try {
                return ab.a(r2_Cipher.doFinal(r3_JSONObject.toString().getBytes()));
            } catch (GeneralSecurityException e_2) {
                v.a().b().a(Crashlytics.TAG, "Could not encrypt IDs", e_2);
                return r0_String;
            }
        } catch (GeneralSecurityException e_3) {
            v.a().b().a(Crashlytics.TAG, "Could not create cipher to encrypt headers.", e_3);
            return RContactStorage.PRIMARY_KEY;
        }
    }

    public final synchronized aS a(Context r14_Context, av r15_av, String r16_String, String r17_String, String r18_String) {
        aS r1_aS;
        if (this.c) {
            r1_aS = this;
        } else {
            if (this.b == null) {
                String r2_String = r.a(r14_Context, false);
                String r1_String = r14_Context.getPackageName();
                String r5_String = r14_Context.getPackageManager().getInstallerPackageName(r1_String);
                ah r9_ah = new ah();
                aY r10_aY = new aY();
                aN r11_aN = new aN();
                String r8_String = ab.g(r14_Context);
                Locale r3_Locale = Locale.US;
                Object[] r6_ObjectA = new Object[1];
                r6_ObjectA[0] = r1_String;
                bo r12_bo = new bo(r18_String, String.format(r3_Locale, "https://settings.crashlytics.com/spi/v2/platforms/android/apps/%s/settings", r6_ObjectA), r15_av);
                String r3_String = a(r2_String, r1_String, r14_Context);
                String[] r1_StringA = new String[1];
                r1_StringA[0] = ab.i(r14_Context);
                this.b = new aW(new aZ(r2_String, r3_String, ab.a(r1_StringA), r17_String, r16_String, ai.a(r5_String).a(), r8_String), r9_ah, r10_aY, r11_aN, r12_bo);
            }
            this.c = true;
            r1_aS = this;
        }
        return r1_aS;
    }

    public final <T> T a(aU<T> r2_aU_T, T r3_T) {
        aX r0_aX = (aX) this.a.get();
        return r0_aX == null ? r3_T : r2_aU_T.a(r0_aX);
    }

    public final aX b() {
        return (aX) this.a.get();
    }

    public final synchronized boolean c() {
        aX r0_aX = this.b.a();
        this.a.set(r0_aX);
        return r0_aX != null;
    }

    public final synchronized boolean d() {
        aX r0_aX = this.b.a(aV.b);
        this.a.set(r0_aX);
        if (r0_aX == null) {
            v.a().b().a(Crashlytics.TAG, "Failed to force reload of settings from Crashlytics.", null);
        }
        return r0_aX != null;
    }
}