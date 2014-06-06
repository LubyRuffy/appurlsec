package com.tencent.cloudsdk;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.tencent.cloudsdk.defaultsdk.mna.tsocket.GlobalContext;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
class cc implements Runnable {
    final /* synthetic */ cf a;
    private Object b;
    private boolean c;
    private String d;
    private boolean e;
    private short f;

    public cc(cf r5_cf, Object r6_Object, short r7s, boolean r8z) {
        this.a = r5_cf;
        this.b = null;
        this.c = false;
        this.d = null;
        this.f = (short) 0;
        this.b = r6_Object;
        this.f = r7s;
        this.e = r8z;
        if (this.f == (short) 1) {
            this.d = cf.a(r5_cf, ((cl) r6_Object).b, Integer.valueOf(((cl) r6_Object).c).intValue(), r8z);
            er.a(cf.c(), new StringBuilder(">>>\u5c06\u7ed3\u679c\u7f13\u5b58\u81f3\u5185\u5b58 key=").append(this.d).toString());
            if (!cf.d().containsKey(this.d)) {
                cf.d().put(this.d, (cl) r6_Object);
                this.c = true;
            }
        }
    }

    public void run() {
        switch (this.f) {
            case XListViewHeader.STATE_READY:
                if (this.c) {
                    cf.a(this.a, (cl) this.b, this.e);
                }
                break;
            case XListViewHeader.STATE_REFRESHING:
                if (GlobalContext.getContext() != null) {
                    SharedPreferences r0_SharedPreferences = GlobalContext.getContext().getSharedPreferences("clr_exp", 0);
                    if (System.currentTimeMillis() > r0_SharedPreferences.getLong("last_clr_date", 0)) {
                        cf.a(this.a);
                        Editor r0_Editor = r0_SharedPreferences.edit();
                        r0_Editor.putLong("last_clr_date", System.currentTimeMillis() + 604800000);
                        r0_Editor.commit();
                    }
                }
                cf.a(this.a, true);
                break;
        }
    }
}