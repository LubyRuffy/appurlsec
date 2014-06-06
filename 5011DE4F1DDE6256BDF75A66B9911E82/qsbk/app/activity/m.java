package qsbk.app.activity;

import android.view.View;

// compiled from: AuditNativeActivity.java
class m implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ AuditNativeActivity b;

    m(AuditNativeActivity r1_AuditNativeActivity, View r2_View) {
        this.b = r1_AuditNativeActivity;
        this.a = r2_View;
    }

    public void run() {
        this.a.startAnimation(this.b.M);
    }
}