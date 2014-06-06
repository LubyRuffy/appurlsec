package com.qq.e.v2.managers.setting;

import android.content.Context;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.Base64;
import com.qq.e.comm.a;
import com.qq.e.v2.constants.Constants.SETTING;
import com.qq.e.v2.util.GDTLogger;
import com.qq.e.v2.util.StringUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.io.IOException;
import qsbk.app.bean.Base;

public class SM {
    private a a;
    private String b;
    private a c;
    private a d;
    private String e;
    private b f;
    private b g;
    private String h;
    private String i;
    private Context j;

    public SM(Context r5_Context) {
        this.i = RContactStorage.PRIMARY_KEY;
        this.j = r5_Context;
        this.a = new a();
        this.d = new a();
        this.g = new c();
        try {
            this.h = StringUtil.readAll(new File(this.j.getDir(SETTING.SETTINGDIR, 0), SETTING.SUID_FILE));
        } catch (Throwable th) {
            this.h = null;
            GDTLogger.e("IO Exception while loading suid");
        }
        d r0_d = a.b(this.j);
        if (r0_d != null) {
            this.e = r0_d.a();
            this.f = r0_d.b();
        } else {
            GDTLogger.d("Load Local SDK Cloud setting fail");
        }
        a r0_a = a.a(this.j);
        if (r0_a != null) {
            this.c = r0_a.b();
            this.b = r0_a.a();
        } else {
            GDTLogger.d("Load Local DEV Cloud setting fail");
        }
    }

    private static int a(Object r2_Object) {
        if (r2_Object == null) {
            return ExploreByTouchHelper.INVALID_ID;
        }
        if (r2_Object instanceof Integer) {
            return ((Integer) r2_Object).intValue();
        }
        try {
            return Integer.parseInt(r2_Object.toString());
        } catch (Exception e) {
            return ExploreByTouchHelper.INVALID_ID;
        }
    }

    public Object get(String r5_String) {
        if (StringUtil.isEmpty(r5_String)) {
            return null;
        }
        Object r1_Object;
        try {
            Object r1_Object_2;
            if (this.a.a(r5_String) != null) {
                r1_Object_2 = this.a.a(r5_String);
                if (r1_Object_2 != null) {
                    return r1_Object_2;
                }
            }
            if (this.c != null) {
                r1_Object = this.c.a(r5_String);
                if (r1_Object != null) {
                    return r1_Object;
                }
            }
            if (this.d != null) {
                r1_Object = this.d.a(r5_String);
                if (r1_Object != null) {
                    return r1_Object;
                }
            }
            if (this.f != null) {
                r1_Object = this.f.a(r5_String);
                if (r1_Object != null) {
                    return r1_Object;
                }
            }
            return this.g != null ? this.g.a(r5_String) : null;
        } catch (Throwable th) {
            GDTLogger.report(new StringBuilder("Exception in settingManager.get Setting for key:").append(r5_String).toString(), th);
            return null;
        }
    }

    public String getDevCloudSettingSig() {
        return this.b;
    }

    public Object getForPlacement(String r4_String, String r5_String) {
        if (StringUtil.isEmpty(r4_String) || StringUtil.isEmpty(r5_String)) {
            return null;
        }
        Object r0_Object;
        try {
            Object r0_Object_2;
            if (this.a != null) {
                r0_Object_2 = this.a.a(r4_String, r5_String);
                if (r0_Object_2 != null) {
                    return r0_Object_2;
                }
            }
            if (this.c != null) {
                r0_Object = this.c.a(r4_String, r5_String);
                if (r0_Object != null) {
                    return r0_Object;
                }
            }
            if (this.d != null) {
                r0_Object = this.d.a(r4_String, r5_String);
                if (r0_Object != null) {
                    return r0_Object;
                }
            }
            return get(r4_String);
        } catch (Throwable th) {
            GDTLogger.report("Exception in settingManager.getForPlacement", th);
            return null;
        }
    }

    public int getInteger(String r2_String) {
        return a(get(r2_String));
    }

    public int getIntegerForPlacement(String r2_String, String r3_String) {
        return a(getForPlacement(r2_String, r3_String));
    }

    public String getSdkCloudSettingSig() {
        return this.e;
    }

    public String getSid() {
        return this.i;
    }

    public String getString(String r2_String) {
        Object r0_Object = get(r2_String);
        return r0_Object == null ? null : r0_Object.toString();
    }

    public String getStringForPlacement(String r2_String, String r3_String) {
        Object r0_Object = getForPlacement(r2_String, r3_String);
        return r0_Object == null ? null : r0_Object.toString();
    }

    public String getSuid() {
        return this.h;
    }

    public void setDEVCodeSetting(String r2_String, Object r3_Object) {
        this.d.a(r2_String, r3_Object);
    }

    public void setDEVCodeSetting(String r2_String, Object r3_Object, String r4_String) {
        this.d.a(r2_String, r3_Object, r4_String);
    }

    public void updateContextSetting(String r5_String) {
        try {
            a r0_a = new a();
            if (!StringUtil.isEmpty(r5_String)) {
                r0_a = new a(new String(Base64.decode(r5_String, 0), Base.UTF8));
            }
            this.a = r0_a;
        } catch (Throwable th) {
            GDTLogger.report("Exception while update Context Setting", th);
        }
    }

    public void updateDEVCloudSetting(String r2_String, String r3_String) {
        a.b(this.j, r2_String, r3_String);
    }

    public void updateSDKCloudSetting(String r2_String, String r3_String) {
        a.a(this.j, r2_String, r3_String);
    }

    public void updateSID(String r1_String) {
        this.i = r1_String;
    }

    public void updateSUID(String r5_String) {
        if (StringUtil.isEmpty(r5_String) || r5_String.equals(this.h)) {
        } else {
            this.h = r5_String;
            try {
                StringUtil.writeTo(r5_String, new File(this.j.getDir(SETTING.SETTINGDIR, 0), SETTING.SUID_FILE));
            } catch (IOException e) {
                GDTLogger.report("Exception while persit suid", e);
            }
        }
    }
}