package com.baidu.mobstat;

import android.content.Context;
import com.baidu.mobstat.a.c;
import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.audit.Rotate3dAnimation;

class a implements UncaughtExceptionHandler {
    private static a a;
    private UncaughtExceptionHandler b;
    private Context c;

    static {
        a = new a();
    }

    private a() {
        this.b = null;
        this.c = null;
    }

    public static a a_() {
        return a;
    }

    private void a_(long r5j, String r7_String, String r8_String) {
        if (this.c == null || r7_String == null || r7_String.trim().equals(RContactStorage.PRIMARY_KEY)) {
        } else {
            try {
                JSONObject r1_JSONObject = new JSONObject();
                r1_JSONObject.put(QsbkDatabase.T, r5j);
                r1_JSONObject.put(KEYS.CTXTSETTING, r7_String);
                r1_JSONObject.put(Rotate3dAnimation.ROTATE_Y, r8_String);
                JSONArray r0_JSONArray = b(this.c);
                if (r0_JSONArray == null) {
                    r0_JSONArray = new JSONArray();
                }
                r0_JSONArray.put(r1_JSONObject);
                FileOutputStream r1_FileOutputStream = this.c.openFileOutput("__local_except_cache.json", 0);
                r1_FileOutputStream.write(r0_JSONArray.toString().getBytes());
                r1_FileOutputStream.flush();
                r1_FileOutputStream.close();
                c.a("SDKCrashHandler", "Save Exception String Successlly");
            } catch (Exception e) {
                c.a("SDKCrashHandler", e);
            }
        }
    }

    public void a_(Context r2_Context) {
        if (this.b == null) {
            this.b = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
        if (this.c == null) {
            this.c = r2_Context.getApplicationContext();
        }
    }

    protected JSONArray b(Context r9_Context) {
        JSONArray r1_JSONArray = null;
        if (r9_Context == null) {
            return r1_JSONArray;
        }
        File r2_File = new File(r9_Context.getFilesDir(), "__local_except_cache.json");
        try {
            if (r2_File.exists()) {
                FileInputStream r0_FileInputStream = r9_Context.openFileInput("__local_except_cache.json");
                StringBuffer r3_StringBuffer = new StringBuffer();
                byte[] r4_byteA = new byte[1024];
                while (true) {
                    int r5i = r0_FileInputStream.read(r4_byteA);
                    if (r5i != -1) {
                        r3_StringBuffer.append(new String(r4_byteA, 0, r5i));
                    } else {
                        r0_FileInputStream.close();
                        r1_JSONArray = r3_StringBuffer.length() != 0 ? new JSONArray(r3_StringBuffer.toString()) : null;
                        try {
                            r2_File.delete();
                        } catch (Exception e) {
                            c.a("SDKCrashHandler", e);
                        }
                    }
                }
            }
        } catch (Exception e_2) {
            c.a("SDKCrashHandler", e_2);
        }
        return r1_JSONArray;
    }

    public void uncaughtException(Thread r5_Thread, Throwable r6_Throwable) {
        String r1_String = r6_Throwable.toString();
        String r0_String = RContactStorage.PRIMARY_KEY;
        Writer r0_Writer;
        PrintWriter r2_PrintWriter;
        if (r1_String == null || r1_String.equals(RContactStorage.PRIMARY_KEY)) {
            if (r0_String == null || r0_String.equals(RContactStorage.PRIMARY_KEY)) {
                r0_Writer = new StringWriter();
                r2_PrintWriter = new PrintWriter(r0_Writer);
                r6_Throwable.printStackTrace(r2_PrintWriter);
                r2_PrintWriter.close();
                r0_String = r0_Writer.toString();
                c.a("SDKCrashHandler", r0_String);
                a(System.currentTimeMillis(), r0_String, r1_String);
                if (this.b.equals(this)) {
                    this.b.uncaughtException(r5_Thread, r6_Throwable);
                }
                throw new RuntimeException(r6_Throwable);
            } else {
                r1_String = r0_String;
                r0_Writer = new StringWriter();
                r2_PrintWriter = new PrintWriter(r0_Writer);
                r6_Throwable.printStackTrace(r2_PrintWriter);
                r2_PrintWriter.close();
                r0_String = r0_Writer.toString();
                c.a("SDKCrashHandler", r0_String);
                a(System.currentTimeMillis(), r0_String, r1_String);
                if (this.b.equals(this)) {
                    throw new RuntimeException(r6_Throwable);
                } else {
                    this.b.uncaughtException(r5_Thread, r6_Throwable);
                    throw new RuntimeException(r6_Throwable);
                }
            }
        } else {
            String[] r0_StringA;
            try {
                r0_StringA = r1_String.split(":");
            } catch (Exception e) {
                c.b(e);
                r0_String = RContactStorage.PRIMARY_KEY;
            }
            if (r1_String.length() > 1) {
                r0_String = r0_StringA[0];
                if (r0_String == null || r0_String.equals(RContactStorage.PRIMARY_KEY)) {
                    r0_Writer = new StringWriter();
                    r2_PrintWriter = new PrintWriter(r0_Writer);
                    r6_Throwable.printStackTrace(r2_PrintWriter);
                    r2_PrintWriter.close();
                    r0_String = r0_Writer.toString();
                    c.a("SDKCrashHandler", r0_String);
                    a(System.currentTimeMillis(), r0_String, r1_String);
                    if (this.b.equals(this)) {
                        this.b.uncaughtException(r5_Thread, r6_Throwable);
                    }
                    throw new RuntimeException(r6_Throwable);
                } else {
                    r1_String = r0_String;
                    r0_Writer = new StringWriter();
                    r2_PrintWriter = new PrintWriter(r0_Writer);
                    r6_Throwable.printStackTrace(r2_PrintWriter);
                    r2_PrintWriter.close();
                    r0_String = r0_Writer.toString();
                    c.a("SDKCrashHandler", r0_String);
                    a(System.currentTimeMillis(), r0_String, r1_String);
                    if (this.b.equals(this)) {
                        throw new RuntimeException(r6_Throwable);
                    } else {
                        this.b.uncaughtException(r5_Thread, r6_Throwable);
                        throw new RuntimeException(r6_Throwable);
                    }
                }
            } else {
                r0_String = r1_String;
                if (r0_String == null || r0_String.equals(RContactStorage.PRIMARY_KEY)) {
                    r0_Writer = new StringWriter();
                    r2_PrintWriter = new PrintWriter(r0_Writer);
                    r6_Throwable.printStackTrace(r2_PrintWriter);
                    r2_PrintWriter.close();
                    r0_String = r0_Writer.toString();
                    c.a("SDKCrashHandler", r0_String);
                    a(System.currentTimeMillis(), r0_String, r1_String);
                    if (this.b.equals(this)) {
                        this.b.uncaughtException(r5_Thread, r6_Throwable);
                    }
                    throw new RuntimeException(r6_Throwable);
                } else {
                    r1_String = r0_String;
                    r0_Writer = new StringWriter();
                    r2_PrintWriter = new PrintWriter(r0_Writer);
                    r6_Throwable.printStackTrace(r2_PrintWriter);
                    r2_PrintWriter.close();
                    r0_String = r0_Writer.toString();
                    c.a("SDKCrashHandler", r0_String);
                    a(System.currentTimeMillis(), r0_String, r1_String);
                    if (this.b.equals(this)) {
                        throw new RuntimeException(r6_Throwable);
                    } else {
                        this.b.uncaughtException(r5_Thread, r6_Throwable);
                        throw new RuntimeException(r6_Throwable);
                    }
                }
            }
        }
    }
}