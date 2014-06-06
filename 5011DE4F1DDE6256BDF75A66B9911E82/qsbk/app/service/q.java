package qsbk.app.service;

// compiled from: VersionService.java
class q extends Thread {
    final /* synthetic */ p a;

    q(p r1_p, String r2_String) {
        this.a = r1_p;
        super(r2_String);
    }

    public void run() {
        this.a.a.c = false;
        this.a.a.d.sendMessage(this.a.a.d.obtainMessage());
    }
}