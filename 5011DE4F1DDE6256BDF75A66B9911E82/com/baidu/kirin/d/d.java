package com.baidu.kirin.d;

import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import com.baidu.kirin.KirinConfig;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import qsbk.app.bean.Base;

public class d {
    public static String a;
    public static String b;
    private static Context c;

    static {
        a = "KIRINUPDATE";
        b = "kirin_update.log";
    }

    public static int a(String r2_String) {
        if (KirinConfig.DEBUG_MODE) {
            a(d(r2_String), null);
        }
        return KirinConfig.LOG_LEVEL <= 1 ? Log.d(a, d(r2_String)) : 0;
    }

    public static int a(String r5_String, Throwable r6_Throwable) {
        if (c == null) {
            return 0;
        }
        try {
            FileOutputStream r2_FileOutputStream = c.openFileOutput(b, AccessibilityNodeInfoCompat.ACTION_PASTE);
            StringBuilder r1_StringBuilder = new StringBuilder();
            r1_StringBuilder.append(new SimpleDateFormat("MM-dd hh:mm:ss.S").format(new Date())).append("\t").append(d(r5_String)).append("\n").append(Log.getStackTraceString(r6_Throwable));
            byte[] r3_byteA = r1_StringBuilder.toString().getBytes(Base.UTF8);
            int r1i = r3_byteA.length;
            r2_FileOutputStream.write(r3_byteA);
            r2_FileOutputStream.close();
            return r1i;
        } catch (FileNotFoundException e) {
            return 0;
        } catch (UnsupportedEncodingException e_2) {
            return 0;
        } catch (IOException e_3) {
            return 0;
        }
    }

    private static String a() {
        StackTraceElement[] r2_StackTraceElementA = Thread.currentThread().getStackTrace();
        if (r2_StackTraceElementA == null) {
            return null;
        }
        int r3i = r2_StackTraceElementA.length;
        int r1i = 0;
        while (r1i < r3i) {
            StackTraceElement r4_StackTraceElement = r2_StackTraceElementA[r1i];
            if (!r4_StackTraceElement.isNativeMethod() && !r4_StackTraceElement.getClassName().equals(Thread.class.getName()) && !r4_StackTraceElement.getClassName().equals("com.baidu.kirin.util.KirinLog")) {
                return "[" + Thread.currentThread().getName() + "(" + Thread.currentThread().getId() + "): " + r4_StackTraceElement.getFileName() + ":" + r4_StackTraceElement.getLineNumber() + "]";
            }
            r1i++;
        }
        return null;
    }

    public static int b(String r2_String) {
        if (KirinConfig.DEBUG_MODE) {
            a(d(r2_String), null);
        }
        return KirinConfig.LOG_LEVEL <= 3 ? Log.w(a, d(r2_String)) : 0;
    }

    public static int c(String r2_String) {
        if (KirinConfig.DEBUG_MODE) {
            a(d(r2_String), null);
        }
        return KirinConfig.LOG_LEVEL <= 4 ? Log.e(a, d(r2_String)) : 0;
    }

    private static String d_(String r2_String) {
        String r0_String = a();
        return r0_String == null ? r2_String : r0_String + " - " + r2_String;
    }
}