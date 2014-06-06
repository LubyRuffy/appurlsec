package qsbk.app.activity;

// compiled from: AuditNativeActivity.java
class l implements Runnable {
    final /* synthetic */ AuditNativeActivity a;

    l(AuditNativeActivity r1_AuditNativeActivity) {
        this.a = r1_AuditNativeActivity;
    }

    public void run() {
        this.a.Z = this.a.z.getWidth();
        this.a.aa = this.a.z.getHeight();
        this.a.ab = this.a.S.getHeight();
        this.a.ac = this.a.O.getHeight();
        this.a.ad = this.a.ab - this.a.ac;
        this.a.o();
        this.a.b(0);
    }
}