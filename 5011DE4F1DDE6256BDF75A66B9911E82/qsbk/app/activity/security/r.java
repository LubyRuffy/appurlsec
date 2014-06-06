package qsbk.app.activity.security;

// compiled from: SecurityBindActivity.java
class r implements Runnable {
    final /* synthetic */ b a;

    r(b r1_b) {
        this.a = r1_b;
    }

    public void run() {
        this.a.a.b.reAuth(this.a.a, b.a(this.a), new c(this.a.a, null));
    }
}