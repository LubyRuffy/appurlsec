package qsbk.app.activity;

// compiled from: AuditNativeActivity.java
class f implements Runnable {
    final /* synthetic */ AuditNativeActivity a;

    f(AuditNativeActivity r1_AuditNativeActivity) {
        this.a = r1_AuditNativeActivity;
    }

    public void run() {
        this.a.W.clearAnimation();
        this.a.I.setDuration(this.a.I.getDuration() + 1);
        this.a.W.startAnimation(this.a.I);
        this.a.b(null);
    }
}