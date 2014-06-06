package qsbk.app.activity;

// compiled from: AuditNativeActivity.java
class g implements Runnable {
    final /* synthetic */ AuditNativeActivity a;

    g(AuditNativeActivity r1_AuditNativeActivity) {
        this.a = r1_AuditNativeActivity;
    }

    public void run() {
        this.a.O.clearAnimation();
        this.a.O.startAnimation(this.a.F);
    }
}