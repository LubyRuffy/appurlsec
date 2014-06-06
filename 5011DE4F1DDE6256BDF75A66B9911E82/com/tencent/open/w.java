package com.tencent.open;

import android.app.Activity;
import android.os.Bundle;
import com.tencent.tauth.IUiListener;
import java.lang.ref.WeakReference;

// compiled from: ProGuard
class w {
    public IUiListener a;
    public Bundle b;
    public String c;
    public WeakReference d;

    public w(Activity r2_Activity, String r3_String, Bundle r4_Bundle, IUiListener r5_IUiListener) {
        this.d = new WeakReference(r2_Activity);
        this.c = r3_String;
        this.b = r4_Bundle;
        this.a = r5_IUiListener;
    }
}