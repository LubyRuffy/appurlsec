package com.baidu.mobstat;

import android.content.Context;
import com.baidu.mobstat.a.c;
import com.qq.e.v2.constants.Constants.KEYS;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.audit.Rotate3dAnimation;

class i {
    private static i a;
    private boolean b;

    static {
        a = new i();
    }

    private i() {
        this.b = false;
    }

    public static i a() {
        return a;
    }

    public void a(Context r3_Context) {
        c.a("stat", "openExceptonAnalysis");
        if (!this.b) {
            this.b = true;
            a.a().a(r3_Context);
        }
    }

    public void b(Context r9_Context) {
        if (r9_Context == null) {
            c.a("stat", "exceptonAnalysis, context=null");
        } else {
            JSONArray r2_JSONArray = a.a().b(r9_Context);
            if (r2_JSONArray == null) {
                c.a("stat", "no exception str");
            } else {
                c.a("stat", "move exception cache to stat cache");
                int r1i = 0;
                while (true) {
                    try {
                        if (r1i < r2_JSONArray.length()) {
                            JSONObject r0_JSONObject = (JSONObject) r2_JSONArray.get(r1i);
                            DataCore.getInstance().putException(r0_JSONObject.getLong(QsbkDatabase.T), r0_JSONObject.getString(KEYS.CTXTSETTING), r0_JSONObject.getString(Rotate3dAnimation.ROTATE_Y));
                            DataCore.getInstance().flush(r9_Context);
                            r1i++;
                        } else {
                            return;
                        }
                    } catch (Exception e) {
                        c.a("stat", e);
                    }
                }
            }
        }
    }
}