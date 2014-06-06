package com.baidu.a.a.a.b;

import android.content.Context;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.baidu.a.a.a.a.a;
import com.qiubai.library.adview.util.AdViewNetFetchThread;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class b {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String a(Context r7_Context) {
        /*
        r2 = 1;
        r0 = "android.permission.WRITE_SETTINGS";
        a(r7, r0);
        r0 = "android.permission.READ_PHONE_STATE";
        a(r7, r0);
        r0 = "android.permission.WRITE_EXTERNAL_STORAGE";
        a(r7, r0);
        r1 = 0;
        r3 = "";
        r0 = r7.getContentResolver();	 //Catch:{ Exception -> 0x0050 }
        r4 = "bd_setting_i";
        r3 = android.provider.Settings.System.getString(r0, r4);	 //Catch:{ Exception -> 0x0050 }
        if (r3 != 0) goto L_0x0109;
    L_0x001f:
        r0 = b(r7);	 //Catch:{ Exception -> 0x0050 }
    L_0x0023:
        r3 = r7.getContentResolver();	 //Catch:{ Exception -> 0x0106 }
        r4 = "bd_setting_i";
        android.provider.Settings.System.putString(r3, r4, r0);	 //Catch:{ Exception -> 0x0106 }
    L_0x002c:
        r4 = c(r7);
        r3 = "";
        if (r1 == 0) goto L_0x005c;
    L_0x0034:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "com.baidu";
        r0 = r0.append(r1);
        r0 = r0.append(r4);
        r0 = r0.toString();
        r0 = r0.getBytes();
        r3 = com.baidu.a.a.a.b.c.a(r0, r2);
    L_0x004f:
        return r3;
    L_0x0050:
        r0 = move-exception;
        r1 = r0;
        r0 = r3;
    L_0x0053:
        r3 = "DeviceId";
        r4 = "Settings.System.getString or putString failed";
        android.util.Log.e(r3, r4, r1);
        r1 = r2;
        goto L_0x002c;
    L_0x005c:
        r1 = 0;
        r3 = r7.getContentResolver();
        r5 = "com.baidu.deviceid";
        r3 = android.provider.Settings.System.getString(r3, r5);
        r5 = android.text.TextUtils.isEmpty(r3);
        if (r5 == 0) goto L_0x00a6;
    L_0x006d:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "com.baidu";
        r1 = r1.append(r3);
        r1 = r1.append(r0);
        r1 = r1.append(r4);
        r1 = r1.toString();
        r1 = r1.getBytes();
        r1 = com.baidu.a.a.a.b.c.a(r1, r2);
        r3 = r7.getContentResolver();
        r3 = android.provider.Settings.System.getString(r3, r1);
        r5 = android.text.TextUtils.isEmpty(r3);
        if (r5 != 0) goto L_0x00a6;
    L_0x009a:
        r5 = r7.getContentResolver();
        r6 = "com.baidu.deviceid";
        android.provider.Settings.System.putString(r5, r6, r3);
        a(r0, r3);
    L_0x00a6:
        r5 = android.text.TextUtils.isEmpty(r3);
        if (r5 == 0) goto L_0x00c6;
    L_0x00ac:
        r3 = a(r0);
        r5 = android.text.TextUtils.isEmpty(r3);
        if (r5 != 0) goto L_0x00c6;
    L_0x00b6:
        r5 = r7.getContentResolver();
        android.provider.Settings.System.putString(r5, r1, r3);
        r5 = r7.getContentResolver();
        r6 = "com.baidu.deviceid";
        android.provider.Settings.System.putString(r5, r6, r3);
    L_0x00c6:
        r5 = android.text.TextUtils.isEmpty(r3);
        if (r5 == 0) goto L_0x004f;
    L_0x00cc:
        r3 = java.util.UUID.randomUUID();
        r3 = r3.toString();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r5 = r5.append(r0);
        r4 = r5.append(r4);
        r3 = r4.append(r3);
        r3 = r3.toString();
        r3 = r3.getBytes();
        r3 = com.baidu.a.a.a.b.c.a(r3, r2);
        r2 = r7.getContentResolver();
        android.provider.Settings.System.putString(r2, r1, r3);
        r1 = r7.getContentResolver();
        r2 = "com.baidu.deviceid";
        android.provider.Settings.System.putString(r1, r2, r3);
        a(r0, r3);
        goto L_0x004f;
    L_0x0106:
        r1 = move-exception;
        goto L_0x0053;
    L_0x0109:
        r0 = r3;
        goto L_0x0023;
        */

    }

    private static String a(String r5_String) {
        if (TextUtils.isEmpty(r5_String)) {
            return RContactStorage.PRIMARY_KEY;
        }
        String r0_String = RContactStorage.PRIMARY_KEY;
        try {
            BufferedReader r1_BufferedReader = new BufferedReader(new FileReader(new File(Environment.getExternalStorageDirectory(), "baidu/.cuid")));
            StringBuilder r2_StringBuilder = new StringBuilder();
            while (true) {
                String r3_String = r1_BufferedReader.readLine();
                if (r3_String != null) {
                    r2_StringBuilder.append(r3_String);
                    r2_StringBuilder.append("\r\n");
                } else {
                    r1_BufferedReader.close();
                    String[] r1_StringA = new String(a.b("30212102dicudiab", "30212102dicudiab", com.baidu.a.a.a.a.b.a(r2_StringBuilder.toString().getBytes()))).split("=");
                    return (r1_StringA != null && r1_StringA.length == 2 && r5_String.equals(r1_StringA[0])) ? r1_StringA[1] : r0_String;
                }
            }
        } catch (FileNotFoundException e) {
            return r0_String;
        } catch (IOException e_2) {
            return r0_String;
        } catch (Exception e_3) {
            return r0_String;
        }
    }

    private static void a(Context r3_Context, String r4_String) {
        if ((r3_Context.checkCallingOrSelfPermission(r4_String) == 0 ? 1 : 0) == 0) {
            throw new SecurityException("Permission Denial: requires permission " + r4_String);
        }
    }

    private static void a(String r4_String, String r5_String) {
        if (TextUtils.isEmpty(r4_String)) {
        } else {
            StringBuilder r0_StringBuilder = new StringBuilder();
            r0_StringBuilder.append(r4_String);
            r0_StringBuilder.append("=");
            r0_StringBuilder.append(r5_String);
            File r1_File = new File(Environment.getExternalStorageDirectory(), "baidu/.cuid");
            try {
                new File(r1_File.getParent()).mkdirs();
                FileWriter r2_FileWriter = new FileWriter(r1_File, false);
                r2_FileWriter.write(com.baidu.a.a.a.a.b.a(a.a("30212102dicudiab", "30212102dicudiab", r0_StringBuilder.toString().getBytes()), AdViewNetFetchThread.NetEncoding));
                r2_FileWriter.flush();
                r2_FileWriter.close();
            } catch (IOException e) {
            } catch (Exception e_2) {
            }
        }
    }

    public static String b_(Context r2_Context) {
        String r1_String = RContactStorage.PRIMARY_KEY;
        TelephonyManager r0_TelephonyManager = (TelephonyManager) r2_Context.getSystemService("phone");
        if (r0_TelephonyManager == null) {
            return r1_String;
        }
        String r0_String = r0_TelephonyManager.getDeviceId();
        return TextUtils.isEmpty(r0_String) ? RContactStorage.PRIMARY_KEY : r0_String;
    }

    public static String c(Context r2_Context) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        r0_String = Secure.getString(r2_Context.getContentResolver(), "android_id");
        return TextUtils.isEmpty(r0_String) ? RContactStorage.PRIMARY_KEY : r0_String;
    }
}