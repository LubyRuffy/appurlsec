package qsbk.app.activity;

// compiled from: OneProfileActivity.java
class cg implements Runnable {
    final /* synthetic */ e a;

    cg(e r1_e) {
        this.a = r1_e;
    }

    public void run() {
        this.a.a.startAnimation(this.a.b);
        this.a.a.setVisibility(0);
    }
}