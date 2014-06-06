package com.crashlytics.android.internal;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.crashlytics.android.Crashlytics;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import org.apache.cordova.NetworkManager;

// compiled from: SourceFile
public final class ao {
    private static final Pattern a;
    private static final String b;
    private final ReentrantLock c;
    private final boolean d;
    private final boolean e;
    private final Context f;

    static {
        a = Pattern.compile("[^\\p{Alnum}]");
        b = Pattern.quote("/");
    }

    public ao(Context r6_Context) {
        this.c = new ReentrantLock();
        if (r6_Context == null) {
            throw new IllegalArgumentException("appContext must not be null");
        } else {
            this.f = r6_Context;
            this.d = ab.a(r6_Context, "com.crashlytics.CollectDeviceIdentifiers", true);
            if (!this.d) {
                v.a().b().a(Crashlytics.TAG, new StringBuilder("Device ID collection disabled for ").append(r6_Context.getPackageName()).toString());
            }
            this.e = ab.a(r6_Context, "com.crashlytics.CollectUserIdentifiers", true);
            if (!this.e) {
                v.a().b().a(Crashlytics.TAG, new StringBuilder("User information collection disabled for ").append(r6_Context.getPackageName()).toString());
            }
        }
    }

    private String a(SharedPreferences r4_SharedPreferences) {
        this.c.lock();
        String r0_String = r4_SharedPreferences.getString("crashlytics.installation.id", null);
        if (r0_String == null) {
            r0_String = b(UUID.randomUUID().toString());
            r4_SharedPreferences.edit().putString("crashlytics.installation.id", r0_String).commit();
        }
        this.c.unlock();
        return r0_String;
    }

    private static void a(Map<ap, String> r0_Map_ap__String, ap r1_ap, String r2_String) {
        if (r2_String != null) {
            r0_Map_ap__String.put(r1_ap, r2_String);
        }
    }

    private boolean a(String r2_String) {
        return this.f.checkCallingPermission(r2_String) == 0;
    }

    private static String b(String r2_String) {
        return r2_String == null ? null : a.matcher(r2_String).replaceAll(RContactStorage.PRIMARY_KEY).toLowerCase(Locale.US);
    }

    private static String c(String r2_String) {
        return r2_String.replaceAll(b, RContactStorage.PRIMARY_KEY);
    }

    private String i() {
        if ((!this.d) || VERSION.SDK_INT < 9) {
            return null;
        }
        try {
            return b((String) Build.class.getField("SERIAL").get(null));
        } catch (Exception e) {
            v.a().b().a(Crashlytics.TAG, "Could not retrieve android.os.Build.SERIAL value", e);
        }
    }

    public final boolean a() {
        return this.e;
    }

    public final String b() {
        String r0_String = v.a().i();
        if (r0_String != null) {
            return r0_String;
        }
        SharedPreferences r1_SharedPreferences = ab.a();
        r0_String = r1_SharedPreferences.getString("crashlytics.installation.id", null);
        return r0_String == null ? a(r1_SharedPreferences) : r0_String;
    }

    public final String c() {
        Locale r0_Locale = Locale.US;
        Object[] r2_ObjectA = new Object[2];
        r2_ObjectA[0] = c(VERSION.RELEASE);
        r2_ObjectA[1] = c(VERSION.INCREMENTAL);
        return String.format(r0_Locale, "%s/%s", r2_ObjectA);
    }

    public final String d() {
        Locale r0_Locale = Locale.US;
        Object[] r2_ObjectA = new Object[2];
        r2_ObjectA[0] = c(Build.MANUFACTURER);
        r2_ObjectA[1] = c(Build.MODEL);
        return String.format(r0_Locale, "%s/%s", r2_ObjectA);
    }

    public final String e() {
        String r0_String = RContactStorage.PRIMARY_KEY;
        if (!(this.d)) {
            return r0_String;
        }
        r0_String = g();
        if (r0_String != null) {
            return r0_String;
        }
        SharedPreferences r1_SharedPreferences = ab.a();
        r0_String = r1_SharedPreferences.getString("crashlytics.installation.id", null);
        return r0_String == null ? a(r1_SharedPreferences) : r0_String;
    }

    public final Map<ap, String> f() {
        String r0_String;
        String r1_String = null;
        Map r2_Map = new HashMap();
        a(r2_Map, ap.c, g());
        ap r3_ap = ap.d;
        if (this.d && a("android.permission.READ_PHONE_STATE")) {
            TelephonyManager r0_TelephonyManager = (TelephonyManager) this.f.getSystemService("phone");
            if (r0_TelephonyManager != null) {
                r0_String = b(r0_TelephonyManager.getDeviceId());
            }
            r0_String = null;
        } else {
            r0_String = null;
        }
        a(r2_Map, r3_ap, r0_String);
        a(r2_Map, ap.e, i());
        r3_ap = ap.a;
        if (this.d && a("android.permission.ACCESS_WIFI_STATE")) {
            WifiManager r0_WifiManager = (WifiManager) this.f.getSystemService(NetworkManager.WIFI);
            if (r0_WifiManager != null) {
                WifiInfo r0_WifiInfo = r0_WifiManager.getConnectionInfo();
                if (r0_WifiInfo != null) {
                    r1_String = b(r0_WifiInfo.getMacAddress());
                }
            }
            a(r2_Map, r3_ap, r1_String);
            a(r2_Map, ap.b, h());
            return Collections.unmodifiableMap(r2_Map);
        } else {
            a(r2_Map, r3_ap, r1_String);
            a(r2_Map, ap.b, h());
            return Collections.unmodifiableMap(r2_Map);
        }
    }

    public final String g() {
        if (!(this.d)) {
            return null;
        }
        String r1_String = Secure.getString(this.f.getContentResolver(), "android_id");
        return "9774d56d682e549c".equals(r1_String) ? null : b(r1_String);
    }

    public final String h() {
        if (!(this.d) || !a("android.permission.BLUETOOTH")) {
            return null;
        }
        try {
            BluetoothAdapter r0_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (r0_BluetoothAdapter != null) {
                b(r0_BluetoothAdapter.getAddress());
            }
        } catch (Exception e) {
            v.a().b().a(Crashlytics.TAG, "Utils#getBluetoothMacAddress failed, returning null. Requires prior call to BluetoothAdatpter.getDefaultAdapter() on thread that has called Looper.prepare()", e);
        }
        return null;
    }
}