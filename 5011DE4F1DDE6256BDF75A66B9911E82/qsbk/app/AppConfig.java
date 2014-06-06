package qsbk.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.Properties;

public class AppConfig {
    public static final String CONF_APP_UNIQUEID = "APP_UNIQUEID";
    public static final String CONF_LOAD_IMAGE = "perf_loadimage";
    private static AppConfig b;
    private Context a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(Properties r5_Properties) {
        /*
        r4_this = this;
        r2 = 0;
        r0 = r4.a;	 //Catch:{ Exception -> 0x0021, all -> 0x002c }
        r1 = "config";
        r3 = 0;
        r0 = r0.getDir(r1, r3);	 //Catch:{ Exception -> 0x0021, all -> 0x002c }
        r3 = new java.io.File;	 //Catch:{ Exception -> 0x0021, all -> 0x002c }
        r1 = "config";
        r3.<init>(r0, r1);	 //Catch:{ Exception -> 0x0021, all -> 0x002c }
        r1 = new java.io.FileOutputStream;	 //Catch:{ Exception -> 0x0021, all -> 0x002c }
        r1.<init>(r3);	 //Catch:{ Exception -> 0x0021, all -> 0x002c }
        r0 = 0;
        r5.store(r1, r0);	 //Catch:{ Exception -> 0x0038 }
        r1.flush();	 //Catch:{ Exception -> 0x0038 }
        r1.close();	 //Catch:{ Exception -> 0x0031 }
    L_0x0020:
        return;
    L_0x0021:
        r0 = move-exception;
        r1 = r2;
    L_0x0023:
        r0.printStackTrace();	 //Catch:{ all -> 0x0035 }
        r1.close();	 //Catch:{ Exception -> 0x002a }
        goto L_0x0020;
    L_0x002a:
        r0 = move-exception;
        goto L_0x0020;
    L_0x002c:
        r0 = move-exception;
    L_0x002d:
        r2.close();	 //Catch:{ Exception -> 0x0033 }
    L_0x0030:
        throw r0;
    L_0x0031:
        r0 = move-exception;
        goto L_0x0020;
    L_0x0033:
        r1 = move-exception;
        goto L_0x0030;
    L_0x0035:
        r0 = move-exception;
        r2 = r1;
        goto L_0x002d;
    L_0x0038:
        r0 = move-exception;
        goto L_0x0023;
        */

    }

    public static AppConfig getAppConfig(Context r1_Context) {
        if (b == null) {
            b = new AppConfig();
            b.a = r1_Context;
        }
        return b;
    }

    public static SharedPreferences getSharedPreferences(Context r1_Context) {
        return PreferenceManager.getDefaultSharedPreferences(r1_Context);
    }

    public static String loadImageCondition(Context r3_Context) {
        return getSharedPreferences(r3_Context).getString(CONF_LOAD_IMAGE, "auto");
    }

    public String get(String r2_String) {
        Properties r0_Properties = get();
        return r0_Properties != null ? r0_Properties.getProperty(r2_String) : null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Properties get() {
        /*
        r6_this = this;
        r1 = 0;
        r2 = new java.util.Properties;
        r2.<init>();
        r0 = r6.a;	 //Catch:{ Exception -> 0x0038, all -> 0x0040 }
        r3 = "config";
        r4 = 0;
        r3 = r0.getDir(r3, r4);	 //Catch:{ Exception -> 0x0038, all -> 0x0040 }
        r0 = new java.io.FileInputStream;	 //Catch:{ Exception -> 0x0038, all -> 0x0040 }
        r4 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0038, all -> 0x0040 }
        r4.<init>();	 //Catch:{ Exception -> 0x0038, all -> 0x0040 }
        r3 = r3.getPath();	 //Catch:{ Exception -> 0x0038, all -> 0x0040 }
        r3 = r4.append(r3);	 //Catch:{ Exception -> 0x0038, all -> 0x0040 }
        r4 = java.io.File.separator;	 //Catch:{ Exception -> 0x0038, all -> 0x0040 }
        r3 = r3.append(r4);	 //Catch:{ Exception -> 0x0038, all -> 0x0040 }
        r4 = "config";
        r3 = r3.append(r4);	 //Catch:{ Exception -> 0x0038, all -> 0x0040 }
        r3 = r3.toString();	 //Catch:{ Exception -> 0x0038, all -> 0x0040 }
        r0.<init>(r3);	 //Catch:{ Exception -> 0x0038, all -> 0x0040 }
        r2.load(r0);	 //Catch:{ Exception -> 0x004e, all -> 0x0049 }
        r0.close();	 //Catch:{ Exception -> 0x0045 }
    L_0x0037:
        return r2;
    L_0x0038:
        r0 = move-exception;
        r0 = r1;
    L_0x003a:
        r0.close();	 //Catch:{ Exception -> 0x003e }
        goto L_0x0037;
    L_0x003e:
        r0 = move-exception;
        goto L_0x0037;
    L_0x0040:
        r0 = move-exception;
    L_0x0041:
        r1.close();	 //Catch:{ Exception -> 0x0047 }
    L_0x0044:
        throw r0;
    L_0x0045:
        r0 = move-exception;
        goto L_0x0037;
    L_0x0047:
        r1 = move-exception;
        goto L_0x0044;
    L_0x0049:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x0041;
    L_0x004e:
        r1 = move-exception;
        goto L_0x003a;
        */

    }

    public void remove(String ... r5_StringA) {
        Properties r1_Properties = get();
        int r2i = r5_StringA.length;
        int r0i = 0;
        while (r0i < r2i) {
            r1_Properties.remove(r5_StringA[r0i]);
            r0i++;
        }
        a(r1_Properties);
    }

    public void set(String r2_String, String r3_String) {
        Properties r0_Properties = get();
        r0_Properties.setProperty(r2_String, r3_String);
        a(r0_Properties);
    }

    public void set(Properties r2_Properties) {
        Properties r0_Properties = get();
        r0_Properties.putAll(r2_Properties);
        a(r0_Properties);
    }
}