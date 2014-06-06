package qsbk.app.exception;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.crashlytics.android.Crashlytics;
import com.google.analytics.tracking.android.GAServiceManager;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.util.Properties;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.SharePreferenceUtils;

public class CrashHandler implements UncaughtExceptionHandler {
    public static final boolean DEBUG = true;
    public static final String TAG = "CrashHandler";
    public static CrashHandler mInstance;
    protected Tracker a;
    private Context b;
    private Properties c;
    public UncaughtExceptionHandler mDefaultHandler;

    static {
        mInstance = new CrashHandler();
    }

    private CrashHandler() {
        this.mDefaultHandler = null;
        this.c = new Properties();
    }

    private int a(Thread r3_Thread, Throwable r4_Throwable, int r5i, boolean r6z) {
        if (r4_Throwable == null) {
            return 0;
        }
        collectCrashDeviceInfo(this.b);
        int r0i = b(r3_Thread, r4_Throwable, r5i, r6z);
        b(this.b);
        return r0i;
    }

    private String a(Thread r15_Thread) {
        String r0_String;
        OutputStream r3_OutputStream = new ByteArrayOutputStream();
        PrintStream r4_PrintStream = new PrintStream(r3_OutputStream);
        Thread[] r5_ThreadA = new Thread[140];
        Object[] r1_ObjectA = new Object[3];
        r1_ObjectA[0] = r15_Thread.getName();
        r1_ObjectA[1] = Long.valueOf(r15_Thread.getId());
        r1_ObjectA[2] = Long.valueOf(Thread.currentThread().getId());
        r4_PrintStream.printf("Crash occures in thread: %s[%d] and is processed in thread [%d]\n", r1_ObjectA);
        int r6i = Thread.enumerate(r5_ThreadA);
        int r1i = 0;
        while (r1i < r6i) {
            ThreadGroup r0_ThreadGroup = r5_ThreadA[r1i].getThreadGroup();
            r0_String = r0_ThreadGroup == null ? "null" : r0_ThreadGroup.getName();
            Object[] r8_ObjectA = new Object[4];
            r8_ObjectA[0] = Integer.valueOf(r1i);
            r8_ObjectA[1] = r5_ThreadA[r1i].getName();
            r8_ObjectA[2] = Long.valueOf(r5_ThreadA[r1i].getId());
            r8_ObjectA[3] = r0_String;
            r4_PrintStream.printf("\tthread %d: %s[%d]@%s\n", r8_ObjectA);
            r1i++;
        }
        r0_String = r3_OutputStream.toString();
        Log.i("crash-runtime", r0_String);
        return r0_String;
    }

    private String a(Throwable r4_Throwable) {
        OutputStream r1_OutputStream = new ByteArrayOutputStream();
        PrintStream r2_PrintStream = new PrintStream(r1_OutputStream);
        r4_Throwable.printStackTrace(r2_PrintStream);
        Throwable r0_Throwable = r4_Throwable.getCause();
        while (r0_Throwable != null) {
            r0_Throwable.printStackTrace(r2_PrintStream);
            r0_Throwable = r0_Throwable.getCause();
        }
        String r0_String = r1_OutputStream.toString();
        Log.e("crash-stack", r0_String);
        return r0_String;
    }

    private void a(Context r3_Context) {
        GoogleAnalytics r0_GoogleAnalytics = GoogleAnalytics.getInstance(r3_Context);
        this.a = r0_GoogleAnalytics.getTracker("UA-8780108-14");
        r0_GoogleAnalytics.setDefaultTracker(this.a);
        QsbkApp.getInstance().setSampleRate(this.a);
    }

    private void a(File r5_File) {
        if (r5_File.getName().endsWith(".cr")) {
            try {
                BufferedReader r0_BufferedReader = new BufferedReader(new FileReader(r5_File));
                StringBuffer r1_StringBuffer = new StringBuffer();
                while (true) {
                    String r2_String = r0_BufferedReader.readLine();
                    if (r2_String != null) {
                        if (r2_String.contains("STACK_TRACE")) {
                            r1_StringBuffer.append(r2_String + "\n");
                        }
                    } else {
                        r0_BufferedReader.close();
                        this.a.trackView((Build.MODEL + "/" + Build.PRODUCT + "/" + VERSION.RELEASE) + "/" + Constants.localVersionName + "/" + r1_StringBuffer.toString());
                        GAServiceManager.getInstance().dispatch();
                        r5_File.delete();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void a(String r5_String, String r6_String) throws IOException {
        String r0_String = DeviceUtils.getSDPath();
        if (TextUtils.isEmpty(r0_String)) {
            FileOutputStream r0_FileOutputStream = this.b.openFileOutput(r5_String, 1);
            r0_FileOutputStream.write(r6_String.getBytes());
            r0_FileOutputStream.flush();
            r0_FileOutputStream.close();
        } else {
            File r1_File = new File(r0_String + File.separator + this.b.getPackageName() + File.separator + "crash");
            if (r1_File.exists()) {
                FileUtils.removeOldFiles(r1_File, new b(this), 1048576);
            } else {
                r1_File.mkdirs();
            }
            FileWriter r1_FileWriter = new FileWriter(new File(r1_File, r5_String));
            r1_FileWriter.write(r6_String);
            r1_FileWriter.flush();
            r1_FileWriter.close();
        }
    }

    private void a(Thread r8_Thread, Throwable r9_Throwable, int r10i, String r11_String) {
        try {
            JSONObject r0_JSONObject = new JSONObject();
            r0_JSONObject.put("client", "android-qsbk-" + this.c.getProperty("versionName"));
            if (r11_String == null) {
                r0_JSONObject.put("stack", a(r9_Throwable));
            } else {
                r0_JSONObject.put("stack", r11_String + a(r9_Throwable));
            }
            r0_JSONObject.put("runtime", a(r8_Thread));
            r0_JSONObject.put("env", this.c.toString());
            String r1_String = SharePreferenceUtils.getSharePreferencesValue("appStartTime");
            if (TextUtils.isEmpty(r1_String)) {
                r0_JSONObject.put("life", 999999999);
            } else {
                r0_JSONObject.put("life", System.currentTimeMillis() - Long.valueOf(r1_String).longValue());
            }
            r0_JSONObject.put("kind", r10i);
            FileOutputStream r1_FileOutputStream = this.b.openFileOutput("crash-" + System.currentTimeMillis() + ".cr.qbk", 1);
            r1_FileOutputStream.write(r0_JSONObject.toString().getBytes());
            r1_FileOutputStream.flush();
            r1_FileOutputStream.close();
        } catch (Exception e) {
            Log.e(TAG, "error occures when writing custom crash file", e);
        }
    }

    private int b(Thread r11_Thread, Throwable r12_Throwable, int r13i, boolean r14z) {
        int r1i = 1;
        Writer r2_Writer = new StringWriter();
        PrintWriter r3_PrintWriter = new PrintWriter(r2_Writer);
        r12_Throwable.printStackTrace(r3_PrintWriter);
        Throwable r0_Throwable = r12_Throwable.getCause();
        while (r0_Throwable != null) {
            r0_Throwable.printStackTrace(r3_PrintWriter);
            r0_Throwable = r0_Throwable.getCause();
        }
        String r2_String = r2_Writer.toString();
        r3_PrintWriter.close();
        try {
            JSONObject r0_JSONObject = new JSONObject();
            r0_JSONObject.put("client", "android-qsbk-" + this.c.getProperty("versionName"));
            String r4_String = a(r12_Throwable);
            r0_JSONObject.put("stack", r4_String);
            r0_JSONObject.put("runtime", a(r11_Thread));
            r0_JSONObject.put("env", this.c.toString());
            String r3_String = SharePreferenceUtils.getSharePreferencesValue("appStartTime");
            if (TextUtils.isEmpty(r3_String)) {
                r0_JSONObject.put("life", 999999999);
            } else {
                r0_JSONObject.put("life", System.currentTimeMillis() - Long.valueOf(r3_String).longValue());
            }
            if (r11_Thread.getName().equals("main")) {
                r1i = r4_String.indexOf("qsb") >= 0 ? r13i + 9 : Base64.DONT_BREAK_LINES;
            } else if (r4_String.indexOf("qsb") >= 0) {
                r1i = ShareUtils.SHARE_COLLECT;
            } else {
                r1i = ShareUtils.SHARE_SMS;
            }
            r0_JSONObject.put("kind", r1i);
            a("crash-" + System.currentTimeMillis() + ".cr.qbk", r0_JSONObject.toString());
        } catch (Exception e) {
            Log.e(TAG, "error occures when writing custom crash file", e);
        }
        if (r1i < 6 || (!r14z)) {
            return r1i;
        }
        this.c.put("STACK_TRACE", r2_String);
        try {
            OutputStream r0_OutputStream = this.b.openFileOutput("crash-" + System.currentTimeMillis() + ".cr", 1);
            this.c.store(r0_OutputStream, RContactStorage.PRIMARY_KEY);
            r0_OutputStream.flush();
            r0_OutputStream.close();
        } catch (Exception e_2) {
            Log.e(TAG, "an error occured while writing report file...", e_2);
        }
        return r1i;
    }

    private void b(Context r5_Context) {
        File[] r1_FileA = c(r5_Context);
        if (r1_FileA == null || r1_FileA.length <= 0) {
        } else {
            int r2i = r1_FileA.length;
            int r0i = 0;
            while (r0i < r2i) {
                a(r1_FileA[r0i]);
                r0i++;
            }
        }
    }

    private File[] c(Context r12_Context) {
        Object r0_Object;
        int r7i;
        String r5_String;
        String[] r6_StringA;
        Object r2_Object;
        File r0_File = r12_Context.getFilesDir();
        FilenameFilter r3_FilenameFilter = new a(this);
        String[] r5_StringA = r0_File.list(r3_FilenameFilter);
        String r6_String = DeviceUtils.getSDPath();
        if (r5_StringA == null || r5_StringA.length <= 0) {
            r0_Object = null;
        } else {
            r0_Object = new Object[r5_StringA.length];
            r7i = r5_StringA.length;
            int r2i = 0;
            while (r2i < r7i) {
                r0_Object[r2i] = new File(r12_Context.getFilesDir(), r5_StringA[r2i]);
                r2i++;
            }
        }
        if (TextUtils.isEmpty(r6_String)) {
            r5_String = null;
            r6_StringA = null;
        } else {
            String r2_String = r6_String + File.separator + this.b.getPackageName() + File.separator + "crash";
            File r5_File = new File(r2_String);
            if (r5_File.exists()) {
                r5_String = r2_String;
                r6_StringA = r5_File.list(r3_FilenameFilter);
            } else {
                r5_String = r2_String;
                r6_StringA = null;
            }
        }
        if (r6_StringA == null || r6_StringA.length <= 0) {
            r2_Object = null;
        } else {
            r2_Object = new Object[r6_StringA.length];
            r7i = r6_StringA.length;
            int r3i = 0;
            while (r3i < r7i) {
                r2_Object[r3i] = new File(r5_String + File.separator + r6_StringA[r3i]);
                r3i++;
            }
        }
        if (r0_Object == null || r2_Object == null) {
            if (r0_Object != null && r0_Object.length > 0) {
                return r0_Object;
            }
            if (r2_Object != null) {
                return r2_Object;
            }
            return null;
        } else {
            Object r1_Object = new Object[(r0_Object.length + r2_Object.length)];
            System.arraycopy(r0_Object, 0, r1_Object, 0, r0_Object.length);
            System.arraycopy(r2_Object, 0, r1_Object, r0_Object.length, r2_Object.length);
            return r1_Object;
        }
    }

    public static CrashHandler getInstance() {
        return mInstance;
    }

    public void collectCrashDeviceInfo(Context r8_Context) {
        try {
            PackageInfo r1_PackageInfo = r8_Context.getPackageManager().getPackageInfo(r8_Context.getPackageName(), 1);
            if (r1_PackageInfo != null) {
                this.c.put("versionName", r1_PackageInfo.versionName == null ? "not set" : String.valueOf(r1_PackageInfo.versionName));
                this.c.put("versionCode", String.valueOf(r1_PackageInfo.versionCode));
            }
        } catch (NameNotFoundException e) {
            Log.e(TAG, "Error while collect package info", e);
        }
        Field[] r2_FieldA = Build.class.getDeclaredFields();
        int r3i = r2_FieldA.length;
        int r1i = 0;
        while (r1i < r3i) {
            Field r0_Field = r2_FieldA[r1i];
            try {
                r0_Field.setAccessible(DEBUG);
                this.c.put(r0_Field.getName(), String.valueOf(r0_Field.get(null)));
                Log.d(TAG, r0_Field.getName() + " : " + r0_Field.get(null));
            } catch (Exception e_2) {
                Log.e(TAG, "Error while collect crash info", e_2);
            }
            r1i++;
        }
    }

    public void init(Context r6_Context) {
        this.b = r6_Context;
        Crashlytics.start(this.b);
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Object[] r2_ObjectA = new Object[1];
        r2_ObjectA[0] = this.mDefaultHandler.toString();
        Log.d("crash", String.format("save defautl UEH: %s", r2_ObjectA));
        Thread.setDefaultUncaughtExceptionHandler(this);
        Thread.currentThread().setUncaughtExceptionHandler(this);
        a(r6_Context);
    }

    public void reportGuessException(Thread r2_Thread, Throwable r3_Throwable, int r4i) {
        collectCrashDeviceInfo(this.b);
        a(r2_Thread, r3_Throwable, r4i, null);
        b(this.b);
    }

    public void reportGuessException(Thread r2_Thread, Throwable r3_Throwable, int r4i, String r5_String) {
        collectCrashDeviceInfo(this.b);
        a(r2_Thread, r3_Throwable, r4i, r5_String);
        b(this.b);
    }

    public void sendPreviousReportsToServer() {
        b(this.b);
    }

    public void uncaughtException(Thread r10_Thread, Throwable r11_Throwable) {
        boolean r1z = DEBUG;
        a(r10_Thread, r11_Throwable, 0, false);
        Crashlytics.logException(r11_Throwable);
        if (r10_Thread.getName().equals("main")) {
            Log.e("crash-res", "FATAL EXCEPT, perhapse need to die, but now try to come back");
            int r0i = 1;
            while (r0i <= 10) {
                try {
                    Looper.loop();
                    return;
                } catch (Throwable th) {
                    Throwable r2_Throwable = th;
                    if (10 == r0i) {
                        Log.e("crash-close", "recover exception in main loop too many times, to die now");
                        a(r10_Thread, r2_Throwable, r0i, (boolean)DEBUG);
                        if (this.mDefaultHandler != null) {
                            this.mDefaultHandler.uncaughtException(r10_Thread, r2_Throwable);
                        }
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                    } else {
                        Object[] r5_ObjectA = new Object[1];
                        r5_ObjectA[0] = r11_Throwable.toString();
                        Log.e("crash-res", String.format("caught exception in main loop and try to recover: %s", r5_ObjectA));
                        a(r10_Thread, r2_Throwable, r0i, false);
                    }
                    r0i++;
                }
            }
        } else {
            Object[] r1_ObjectA = new Object[r1z];
            r1_ObjectA[0] = r11_Throwable.toString();
            Log.e("crash-res", String.format("ignore exception outside of main thread: %s", r1_ObjectA));
        }
    }
}