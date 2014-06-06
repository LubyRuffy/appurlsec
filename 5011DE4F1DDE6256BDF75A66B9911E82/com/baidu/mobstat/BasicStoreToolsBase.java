package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

public class BasicStoreToolsBase {
    private final String a;
    private SharedPreferences b;
    private SharedPreferences c;

    public BasicStoreToolsBase() {
        this.a = "__Baidu_Stat_SDK_SendRem";
    }

    private SharedPreferences a(Context r3_Context) {
        if (this.b == null) {
            this.b = r3_Context.getSharedPreferences("__Baidu_Stat_SDK_SendRem", 0);
        }
        return this.b;
    }

    private SharedPreferences b(Context r2_Context) {
        if (this.c == null) {
            this.c = PreferenceManager.getDefaultSharedPreferences(r2_Context);
        }
        return this.c;
    }

    public boolean getBoolean(Context r2_Context, String r3_String, boolean r4z) {
        return a(r2_Context).getBoolean(r3_String, r4z);
    }

    public Float getFloatt(Context r3_Context, String r4_String, int r5i) {
        return Float.valueOf(a(r3_Context).getFloat(r4_String, (float) r5i));
    }

    public int getInt(Context r2_Context, String r3_String, int r4i) {
        return a(r2_Context).getInt(r3_String, r4i);
    }

    public long getLong(Context r3_Context, String r4_String, long r5j) {
        return a(r3_Context).getLong(r4_String, r5j);
    }

    public boolean getSharedBoolean(Context r2_Context, String r3_String, boolean r4z) {
        return b(r2_Context).getBoolean(r3_String, r4z);
    }

    public int getSharedInt(Context r2_Context, String r3_String, int r4i) {
        return b(r2_Context).getInt(r3_String, r4i);
    }

    public long getSharedLong(Context r3_Context, String r4_String, long r5j) {
        return b(r3_Context).getLong(r4_String, r5j);
    }

    public String getSharedString(Context r2_Context, String r3_String, String r4_String) {
        return b(r2_Context).getString(r3_String, r4_String);
    }

    public String getString(Context r2_Context, String r3_String, String r4_String) {
        return a(r2_Context).getString(r3_String, r4_String);
    }

    public void putBoolean(Context r2_Context, String r3_String, boolean r4z) {
        a(r2_Context).edit().putBoolean(r3_String, r4z).commit();
    }

    public void putFloat(Context r3_Context, String r4_String, Float r5_Float) {
        a(r3_Context).edit().putFloat(r4_String, r5_Float.floatValue()).commit();
    }

    public void putInt(Context r2_Context, String r3_String, int r4i) {
        a(r2_Context).edit().putInt(r3_String, r4i).commit();
    }

    public void putLong(Context r2_Context, String r3_String, long r4j) {
        a(r2_Context).edit().putLong(r3_String, r4j).commit();
    }

    public void putSharedBoolean(Context r2_Context, String r3_String, boolean r4z) {
        b(r2_Context).edit().putBoolean(r3_String, r4z).commit();
    }

    public void putSharedInt(Context r2_Context, String r3_String, int r4i) {
        b(r2_Context).edit().putInt(r3_String, r4i).commit();
    }

    public void putSharedLong(Context r2_Context, String r3_String, long r4j) {
        b(r2_Context).edit().putLong(r3_String, r4j).commit();
    }

    public void putSharedString(Context r2_Context, String r3_String, String r4_String) {
        b(r2_Context).edit().putString(r3_String, r4_String).commit();
    }

    public void putString(Context r2_Context, String r3_String, String r4_String) {
        a(r2_Context).edit().putString(r3_String, r4_String).commit();
    }

    public void removeShare(Context r2_Context, String r3_String) {
        b(r2_Context).edit().remove(r3_String).commit();
    }

    public void removeString(Context r2_Context, String r3_String) {
        a(r2_Context).edit().remove(r3_String);
    }

    public boolean updateShareBoolean(Intent r2_Intent, Activity r3_Activity, String r4_String) {
        return updateShareBoolean(r2_Intent, r3_Activity, r4_String, true);
    }

    public boolean updateShareBoolean(Intent r3_Intent, Activity r4_Activity, String r5_String, boolean r6z) {
        if (r3_Intent != null) {
            boolean r0z = r3_Intent.getBooleanExtra(r5_String, r6z);
            if (r0z != getSharedBoolean(r4_Activity, r5_String, r6z)) {
                putSharedBoolean(r4_Activity, r5_String, r0z);
                return true;
            }
        }
        return false;
    }

    public boolean updateShareInt(Intent r3_Intent, Activity r4_Activity, String r5_String, int r6i) {
        if (r3_Intent != null) {
            int r0i = r3_Intent.getIntExtra(r5_String, r6i);
            if (r0i != getSharedInt(r4_Activity, r5_String, r6i)) {
                putSharedInt(r4_Activity, r5_String, r0i);
                return true;
            }
        }
        return false;
    }

    public boolean updateShareString(Intent r4_Intent, Activity r5_Activity, String r6_String) {
        String r1_String = null;
        if (r4_Intent != null) {
            CharSequence r0_CharSequence = r4_Intent.getStringExtra(r6_String);
            if (r0_CharSequence != null) {
                r0_CharSequence = r0_CharSequence.trim();
                if (r0_CharSequence.length() == 0) {
                    r0_CharSequence = null;
                }
            }
            if (!TextUtils.equals(r0_CharSequence, getSharedString(r5_Activity, r6_String, r1_String))) {
                putSharedString(r5_Activity, r6_String, r0_CharSequence);
                return true;
            }
        }
        return false;
    }
}