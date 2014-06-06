package qsbk.app.activity;

// compiled from: AuditNativeActivity.java
class e implements Runnable {
    final /* synthetic */ AuditNativeActivity a;

    e(AuditNativeActivity r1_AuditNativeActivity) {
        this.a = r1_AuditNativeActivity;
    }

    public void run() {
        this.a.W.clearAnimation();
        this.a.G.setDuration(this.a.G.getDuration() + 1);
        this.a.W.startAnimation(this.a.G);
        this.a.b(null);
    }
}