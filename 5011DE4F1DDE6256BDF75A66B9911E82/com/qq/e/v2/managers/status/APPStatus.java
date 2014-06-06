package com.qq.e.v2.managers.status;

import android.content.Context;
import com.qq.e.v2.util.StringUtil;

public class APPStatus {
    private String a;
    private Context b;

    public APPStatus(String r1_String, Context r2_Context) {
        this.a = r1_String;
        this.b = r2_Context;
    }

    public String getAPPID() {
        return this.a;
    }

    public String getAPPName() {
        return this.b.getPackageName();
    }

    public String getAPPVersion() {
        String r1_String = getAPPName();
        if (StringUtil.isEmpty(r1_String)) {
            return null;
        }
        try {
            return this.b.getPackageManager().getPackageInfo(r1_String, 0).versionName;
        } catch (Exception e) {
            return null;
        }
    }
}