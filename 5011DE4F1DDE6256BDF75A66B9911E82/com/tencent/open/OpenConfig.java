package com.tencent.open;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Constants;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;

// compiled from: ProGuard
public class OpenConfig {
    private static HashMap a;
    private static String b;
    private Context c;
    private String d;
    private JSONObject e;
    private long f;
    private int g;
    private boolean h;

    static {
        a = null;
        b = null;
    }

    private OpenConfig(Context r3_Context, String r4_String) {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 0;
        this.g = 0;
        this.h = true;
        this.c = r3_Context;
        this.d = r4_String;
        a();
        b();
    }

    public static OpenConfig a(Context r2_Context, String r3_String) {
        if (a == null) {
            a = new HashMap();
        }
        if (r3_String != null) {
            b = r3_String;
        }
        if (r3_String == null) {
            r3_String = b != null ? b : "0";
        }
        OpenConfig r0_OpenConfig = (OpenConfig) a.get(r3_String);
        if (r0_OpenConfig != null) {
            return r0_OpenConfig;
        }
        r0_OpenConfig = new OpenConfig(r2_Context, r3_String);
        a.put(r3_String, r0_OpenConfig);
        return r0_OpenConfig;
    }

    private void a() {
        try {
            this.e = new JSONObject(e("com.tencent.open.config.json"));
        } catch (JSONException e) {
            this.e = new JSONObject();
        }
    }

    private void a(String r3_String, String r4_String) {
        try {
            if (this.d != null) {
                r3_String = r3_String + "." + this.d;
            }
            OutputStreamWriter r1_OutputStreamWriter = new OutputStreamWriter(this.c.openFileOutput(r3_String, 0));
            r1_OutputStreamWriter.write(r4_String);
            r1_OutputStreamWriter.flush();
            r1_OutputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void a(JSONObject r3_JSONObject) {
        f("cgi back, do update");
        this.e = r3_JSONObject;
        a("com.tencent.open.config.json", r3_JSONObject.toString());
        this.f = SystemClock.elapsedRealtime();
    }

    private void b() {
        if (this.g != 0) {
            f("update thread is running, return");
        } else {
            this.g = 1;
            Bundle r0_Bundle = new Bundle();
            r0_Bundle.putString(Constants.PARAM_APP_ID, this.d);
            r0_Bundle.putString("appid_for_getting_config", this.d);
            r0_Bundle.putString("status_os", VERSION.RELEASE);
            r0_Bundle.putString("status_machine", Build.MODEL);
            r0_Bundle.putString("status_version", VERSION.SDK);
            r0_Bundle.putString("sdkv", Constants.SDK_VERSION);
            r0_Bundle.putString("sdkp", QsbkDatabase.A);
            new m(this, r0_Bundle).start();
        }
    }

    private void c() {
        int r0i = this.e.optInt("Common_frequency");
        if (r0i == 0) {
            r0i = 1;
        }
        if (SystemClock.elapsedRealtime() - this.f >= ((long) (r0i * 3600000))) {
            b();
        }
    }

    private String e(String r6_String) {
        String r1_String;
        InputStream r0_InputStream;
        BufferedReader r3_BufferedReader;
        r1_String = RContactStorage.PRIMARY_KEY;
        try {
            r0_InputStream = this.c.openFileInput(this.d != null ? r6_String + "." + this.d : r6_String);
        } catch (FileNotFoundException e) {
            r0_InputStream = this.c.getAssets().open(r6_String);
        }
        r3_BufferedReader = new BufferedReader(new InputStreamReader(r0_InputStream));
        StringBuffer r2_StringBuffer = new StringBuffer();
        while (true) {
            try {
                String r4_String = r3_BufferedReader.readLine();
                if (r4_String != null) {
                    r2_StringBuffer.append(r4_String);
                } else {
                    r1_String = r2_StringBuffer.toString();
                    try {
                        r0_InputStream.close();
                        r3_BufferedReader.close();
                        return r1_String;
                    } catch (IOException e_2) {
                        e_2.printStackTrace();
                        return r1_String;
                    }
                }
            } catch (IOException e_3) {
                e_3.printStackTrace();
                r0_InputStream.close();
                r3_BufferedReader.close();
                return r1_String;
            }
        }
    }

    private void f(String r4_String) {
        if (this.h) {
            Log.i("OpenConfig", r4_String + "; appid: " + this.d);
        }
    }

    public String a(String r3_String) {
        f("get " + r3_String);
        c();
        return this.e.optString(r3_String);
    }

    public int b(String r3_String) {
        f("get " + r3_String);
        c();
        return this.e.optInt(r3_String);
    }

    public long c(String r3_String) {
        f("get " + r3_String);
        c();
        return this.e.optLong(r3_String);
    }

    public boolean d(String r4_String) {
        f("get " + r4_String);
        c();
        Object r0_Object = this.e.opt(r4_String);
        if (r0_Object == null) {
            return false;
        }
        if (r0_Object instanceof Integer) {
            return !r0_Object.equals(Integer.valueOf(0));
        } else {
            if (r0_Object instanceof Boolean) {
                return ((Boolean) r0_Object).booleanValue();
            }
            return false;
        }
    }
}