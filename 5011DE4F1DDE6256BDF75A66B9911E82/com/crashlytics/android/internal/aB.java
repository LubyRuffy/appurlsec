package com.crashlytics.android.internal;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.crashlytics.android.Crashlytics;
import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public final class ab {
    public static final Comparator<File> a;
    private static Boolean b;
    private static final char[] c;
    private static long d;
    private static Boolean e;

    static {
        b = null;
        c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        d = -1;
        e = null;
        a = new bq();
    }

    public static int a(Context r3_Context, String r4_String, String r5_String) {
        Resources r1_Resources = r3_Context.getResources();
        int r0i = r3_Context.getApplicationContext().getApplicationInfo().icon;
        return r1_Resources.getIdentifier(r4_String, r5_String, r0i > 0 ? r3_Context.getResources().getResourcePackageName(r0i) : r3_Context.getPackageName());
    }

    public static int a(boolean r5z) {
        float r0f = b(v.a().getContext());
        if (!r5z) {
            return 1;
        }
        if (r5z && ((double) r0f) >= 99.0d) {
            return XListViewFooter.STATE_NOMORE;
        }
        if ((!r5z) || ((double) r0f) >= 99.0d) {
            return 0;
        }
        return XListViewHeader.STATE_REFRESHING;
    }

    public static long a(Context r2_Context) {
        MemoryInfo r1_MemoryInfo = new MemoryInfo();
        ((ActivityManager) r2_Context.getSystemService("activity")).getMemoryInfo(r1_MemoryInfo);
        return r1_MemoryInfo.availMem;
    }

    private static long a(String r4_String, String r5_String, int r6i) {
        return Long.parseLong(r4_String.split(r5_String)[0].trim()) * ((long) r6i);
    }

    public static RunningAppProcessInfo a(String r4_String, Context r5_Context) {
        List r0_List = ((ActivityManager) r5_Context.getSystemService("activity")).getRunningAppProcesses();
        if (r0_List != null) {
            Iterator r2_Iterator = r0_List.iterator();
            while (r2_Iterator.hasNext()) {
                RunningAppProcessInfo r0_RunningAppProcessInfo = (RunningAppProcessInfo) r2_Iterator.next();
                if (r0_RunningAppProcessInfo.processName.equals(r4_String)) {
                    return r0_RunningAppProcessInfo;
                }
            }
        }
        return null;
    }

    public static SharedPreferences a() {
        return v.a().getContext().getSharedPreferences("com.crashlytics.prefs", 0);
    }

    public static String a(int r5i) {
        if (r5i < 0) {
            throw new IllegalArgumentException("value must be zero or greater");
        } else {
            Locale r0_Locale = Locale.US;
            Object[] r2_ObjectA = new Object[1];
            r2_ObjectA[0] = Integer.valueOf(r5i);
            return String.format(r0_Locale, "%1$10s", r2_ObjectA).replace(' ', '0');
        }
    }

    public static String a(Context r1_Context, String r2_String) {
        int r0i = a(r1_Context, r2_String, "string");
        return r0i > 0 ? r1_Context.getString(r0i) : RContactStorage.PRIMARY_KEY;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static String a(File r7_File, String r8_String) {
        /*
        r0 = 0;
        r5 = 1;
        r1 = r7.exists();
        if (r1 == 0) goto L_0x0039;
    L_0x0008:
        r2 = new java.io.BufferedReader;	 //Catch:{ Exception -> 0x003a, all -> 0x005e }
        r1 = new java.io.FileReader;	 //Catch:{ Exception -> 0x003a, all -> 0x005e }
        r1.<init>(r7);	 //Catch:{ Exception -> 0x003a, all -> 0x005e }
        r3 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r2.<init>(r1, r3);	 //Catch:{ Exception -> 0x003a, all -> 0x005e }
    L_0x0014:
        r1 = r2.readLine();	 //Catch:{ Exception -> 0x0069 }
        if (r1 == 0) goto L_0x0034;
    L_0x001a:
        r3 = "\\s*:\\s*";
        r3 = java.util.regex.Pattern.compile(r3);	 //Catch:{ Exception -> 0x0069 }
        r4 = 2;
        r1 = r3.split(r1, r4);	 //Catch:{ Exception -> 0x0069 }
        r3 = r1.length;	 //Catch:{ Exception -> 0x0069 }
        if (r3 <= r5) goto L_0x0014;
    L_0x0028:
        r3 = 0;
        r3 = r1[r3];	 //Catch:{ Exception -> 0x0069 }
        r3 = r3.equals(r8);	 //Catch:{ Exception -> 0x0069 }
        if (r3 == 0) goto L_0x0014;
    L_0x0031:
        r3 = 1;
        r0 = r1[r3];	 //Catch:{ Exception -> 0x0069 }
    L_0x0034:
        r1 = "Failed to close system file reader.";
        a(r2, r1);
    L_0x0039:
        return r0;
    L_0x003a:
        r1 = move-exception;
        r2 = r0;
    L_0x003c:
        r3 = com.crashlytics.android.internal.v.a();	 //Catch:{ all -> 0x0067 }
        r3 = r3.b();	 //Catch:{ all -> 0x0067 }
        r4 = "Crashlytics";
        r5 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x0067 }
        r6 = "Error parsing ";
        r5.<init>(r6);	 //Catch:{ all -> 0x0067 }
        r5 = r5.append(r7);	 //Catch:{ all -> 0x0067 }
        r5 = r5.toString();	 //Catch:{ all -> 0x0067 }
        r3.a(r4, r5, r1);	 //Catch:{ all -> 0x0067 }
        r1 = "Failed to close system file reader.";
        a(r2, r1);
        goto L_0x0039;
    L_0x005e:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0061:
        r1 = "Failed to close system file reader.";
        a(r2, r1);
        throw r0;
    L_0x0067:
        r0 = move-exception;
        goto L_0x0061;
    L_0x0069:
        r1 = move-exception;
        goto L_0x003c;
        */

    }

    public static String a(InputStream r2_InputStream) throws IOException {
        Scanner r0_Scanner = new Scanner(r2_InputStream).useDelimiter("\\A");
        return r0_Scanner.hasNext() ? r0_Scanner.next() : RContactStorage.PRIMARY_KEY;
    }

    public static String a(String r2_String) {
        return a(r2_String.getBytes(), "SHA-1");
    }

    public static String a(byte[] r6_byteA) {
        char[] r1_charA = new char[(r6_byteA.length << 1)];
        int r0i = 0;
        while (r0i < r6_byteA.length) {
            int r2i = r6_byteA[r0i] & 255;
            r1_charA[r0i << 1] = c[r2i >>> 4];
            r1_charA[r0i << 1 + 1] = c[r2i & 15];
            r0i++;
        }
        return new String(r1_charA);
    }

    private static String a(byte[] r5_byteA, String r6_String) {
        try {
            MessageDigest r0_MessageDigest = MessageDigest.getInstance(r6_String);
            r0_MessageDigest.update(r5_byteA);
            return a(r0_MessageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            v.a().b().a(Crashlytics.TAG, new StringBuilder("Could not create hashing algorithm: ").append(r6_String).append(", returning empty string.").toString(), e);
            return RContactStorage.PRIMARY_KEY;
        }
    }

    public static String a(String ... r7_StringA) {
        if (r7_StringA == null || r7_StringA.length == 0) {
            return null;
        }
        List r2_List = new ArrayList();
        int r3i = r7_StringA.length;
        int r0i = 0;
        while (r0i < r3i) {
            String r4_String = r7_StringA[r0i];
            if (r4_String != null) {
                r2_List.add(r4_String.replace("-", RContactStorage.PRIMARY_KEY).toLowerCase(Locale.US));
            }
            r0i++;
        }
        Collections.sort(r2_List);
        StringBuilder r3_StringBuilder = new StringBuilder();
        Iterator r2_Iterator = r2_List.iterator();
        while (r2_Iterator.hasNext()) {
            r3_StringBuilder.append((String) r2_Iterator.next());
        }
        String r0_String = r3_StringBuilder.toString();
        return r0_String.length() > 0 ? a(r0_String) : null;
    }

    public static void a(int r3i, String r4_String) {
        if (e(v.a().getContext())) {
            v.a().b().a((int)XListViewFooter.STATE_NODATA, Crashlytics.TAG, r4_String);
        }
    }

    public static void a(Closeable r3_Closeable, String r4_String) {
        if (r3_Closeable != null) {
            try {
                r3_Closeable.close();
            } catch (IOException e) {
                v.a().b().a(Crashlytics.TAG, r4_String, e);
            }
        }
    }

    public static void a(Flushable r3_Flushable, String r4_String) {
        if (r3_Flushable != null) {
            try {
                r3_Flushable.flush();
            } catch (IOException e) {
                v.a().b().a(Crashlytics.TAG, r4_String, e);
            }
        }
    }

    public static void a(InputStream r2_InputStream, OutputStream r3_OutputStream, byte[] r4_byteA) throws IOException {
        while (true) {
            int r0i = r2_InputStream.read(r4_byteA);
            if (r0i != -1) {
                r3_OutputStream.write(r4_byteA, 0, r0i);
            } else {
                return;
            }
        }
    }

    public static boolean a(Context r2_Context, String r3_String, boolean r4z) {
        if (r2_Context == null) {
            return r4z;
        }
        Resources r0_Resources = r2_Context.getResources();
        if (r0_Resources == null) {
            return r4z;
        }
        int r1i = a(r2_Context, r3_String, "bool");
        if (r1i > 0) {
            return r0_Resources.getBoolean(r1i);
        }
        int r0i = a(r2_Context, r3_String, "string");
        return r0i > 0 ? Boolean.parseBoolean(r2_Context.getString(r0i)) : r4z;
    }

    public static float b(Context r4_Context) {
        Intent r0_Intent = r4_Context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return ((float) r0_Intent.getIntExtra("level", -1)) / ((float) r0_Intent.getIntExtra("scale", -1));
    }

    public static int b() {
        return br.a().ordinal();
    }

    public static long b(String r7_String) {
        StatFs r0_StatFs = new StatFs(r7_String);
        long r1j = (long) r0_StatFs.getBlockSize();
        return ((long) r0_StatFs.getBlockCount()) * r1j - r1j * ((long) r0_StatFs.getAvailableBlocks());
    }

    public static String b(int r1i) {
        switch (r1i) {
            case XListViewHeader.STATE_REFRESHING:
                return "V";
            case XListViewFooter.STATE_NOMORE:
                return "D";
            case XListViewFooter.STATE_NODATA:
                return "I";
            case ShareUtils.SHARE_SMS:
                return "W";
            case ShareUtils.SHARE_COPY:
                return "E";
            case ShareUtils.SHARE_COLLECT:
                return "A";
        }
        return "?";
    }

    private static String b(InputStream r4_InputStream) {
        try {
            MessageDigest r0_MessageDigest = MessageDigest.getInstance("SHA-1");
            byte[] r1_byteA = new byte[1024];
            while (true) {
                int r2i = r4_InputStream.read(r1_byteA);
                if (r2i == -1) {
                    return a(r0_MessageDigest.digest());
                }
                r0_MessageDigest.update(r1_byteA, 0, r2i);
            }
        } catch (Exception e) {
            v.a().b().a(Crashlytics.TAG, "Could not calculate hash for app icon.", e);
            return RContactStorage.PRIMARY_KEY;
        }
    }

    public static Cipher b(int r5i, String r6_String) throws InvalidKeyException {
        if (r6_String.length() < 32) {
            throw new InvalidKeyException("Key must be at least 32 bytes.");
        } else {
            Key r0_Key = new SecretKeySpec(r6_String.getBytes(), 0, 32, "AES/ECB/PKCS7Padding");
            try {
                Cipher r1_Cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
                r1_Cipher.init(1, r0_Key);
                return r1_Cipher;
            } catch (GeneralSecurityException e) {
                Throwable r0_Throwable = e;
                v.a().b().a(Crashlytics.TAG, "Could not create Cipher for AES/ECB/PKCS7Padding - should never happen.", r0_Throwable);
                throw new RuntimeException(r0_Throwable);
            }
        }
    }

    public static synchronized long c() {
        long r0j;
        synchronized (ab.class) {
            if (d == -1) {
                String r0_String = a(new File("/proc/meminfo"), "MemTotal");
                if (TextUtils.isEmpty(r0_String)) {
                    r0j = 0;
                } else {
                    String r4_String = r0_String.toUpperCase(Locale.US);
                    try {
                        if (r4_String.endsWith("KB")) {
                            r0j = a(r4_String, "KB", (int)Util.BYTE_OF_KB);
                        } else if (r4_String.endsWith("MB")) {
                            r0j = a(r4_String, "MB", (int)Util.BYTE_OF_MB);
                        } else if (r4_String.endsWith("GB")) {
                            r0j = a(r4_String, "GB", 1073741824);
                        } else {
                            v.a().b().a(Crashlytics.TAG, new StringBuilder("Unexpected meminfo format while computing RAM: ").append(r4_String).toString());
                            r0j = 0;
                        }
                    } catch (NumberFormatException e) {
                        v.a().b().a(Crashlytics.TAG, new StringBuilder("Unexpected meminfo format while computing RAM: ").append(r4_String).toString(), e);
                    }
                }
                d = r0j;
            }
            r0j = d;
        }
        return r0j;
    }

    public static void c(String r2_String) {
        if (e(v.a().getContext())) {
            v.a().b().a(Crashlytics.TAG, r2_String);
        }
    }

    public static boolean c(Context r3_Context) {
        if (d()) {
            return false;
        }
        if (((SensorManager) r3_Context.getSystemService("sensor")).getDefaultSensor(Base64.DONT_BREAK_LINES) != null) {
            return true;
        }
        return false;
    }

    public static void d(String r2_String) {
        if (e(v.a().getContext())) {
            v.a().b().d(Crashlytics.TAG, r2_String);
        }
    }

    public static boolean d() {
        return KEYS.SDKINFO.equals(Build.PRODUCT) || "google_sdk".equals(Build.PRODUCT) || Secure.getString(v.a().getContext().getContentResolver(), "android_id") == null;
    }

    public static boolean d(Context r2_Context) {
        boolean r0z = false;
        if (e == null) {
            if (!a(r2_Context, "com.crashlytics.SilenceCrashlyticsLogCat", false)) {
                r0z = true;
            }
            e = Boolean.valueOf(r0z);
        }
        return e.booleanValue();
    }

    public static boolean e() {
        boolean r1z = d();
        String r2_String = Build.TAGS;
        if ((!r1z && r2_String != null && r2_String.contains("test-keys")) || new File("/system/app/Superuser.apk").exists()) {
            return true;
        }
        File r2_File = new File("/system/xbin/su");
        return !r1z && r2_File.exists();
    }

    public static boolean e(Context r2_Context) {
        if (b == null) {
            b = Boolean.valueOf(a(r2_Context, "com.crashlytics.Trace", false));
        }
        return b.booleanValue();
    }

    public static boolean e(String r1_String) {
        return r1_String == null || r1_String.length() == 0;
    }

    public static int f() {
        int r0i;
        int r2i = 0;
        r0i = d() ? 1 : 0;
        if (e()) {
            r0i |= 2;
        }
        if (Debug.isDebuggerConnected() || Debug.waitingForDebugger()) {
            r2i = 1;
            return r2i == 0 ? r0i | 4 : r0i;
        } else if (r2i == 0) {
        }
    }

    public static boolean f(Context r1_Context) {
        return (r1_Context.getApplicationInfo().flags & 2) != 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String g(Context r6_Context) {
        /*
        r0 = 0;
        r1 = r6.getResources();	 //Catch:{ Exception -> 0x001f, all -> 0x0036 }
        r2 = h(r6);	 //Catch:{ Exception -> 0x001f, all -> 0x0036 }
        r2 = r1.openRawResource(r2);	 //Catch:{ Exception -> 0x001f, all -> 0x0036 }
        r1 = b(r2);	 //Catch:{ Exception -> 0x0041 }
        r3 = e(r1);	 //Catch:{ Exception -> 0x0041 }
        if (r3 == 0) goto L_0x001d;
    L_0x0017:
        r1 = "Failed to close icon input stream.";
        a(r2, r1);
    L_0x001c:
        return r0;
    L_0x001d:
        r0 = r1;
        goto L_0x0017;
    L_0x001f:
        r1 = move-exception;
        r2 = r0;
    L_0x0021:
        r3 = com.crashlytics.android.internal.v.a();	 //Catch:{ all -> 0x003f }
        r3 = r3.b();	 //Catch:{ all -> 0x003f }
        r4 = "Crashlytics";
        r5 = "Could not calculate hash for app icon.";
        r3.a(r4, r5, r1);	 //Catch:{ all -> 0x003f }
        r1 = "Failed to close icon input stream.";
        a(r2, r1);
        goto L_0x001c;
    L_0x0036:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0039:
        r1 = "Failed to close icon input stream.";
        a(r2, r1);
        throw r0;
    L_0x003f:
        r0 = move-exception;
        goto L_0x0039;
    L_0x0041:
        r1 = move-exception;
        goto L_0x0021;
        */

    }

    public static int h(Context r1_Context) {
        return r1_Context.getApplicationContext().getApplicationInfo().icon;
    }

    public static String i(Context r5_Context) {
        int r1i = a(r5_Context, "com.crashlytics.android.build_id", "string");
        if (r1i == 0) {
            return null;
        }
        String r0_String = r5_Context.getResources().getString(r1i);
        v.a().b().a(Crashlytics.TAG, new StringBuilder("Build ID is: ").append(r0_String).toString());
        return r0_String;
    }
}