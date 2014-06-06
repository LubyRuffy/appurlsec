package qsbk.app.activity;

import qsbk.app.utils.Base64;

// compiled from: OneProfileActivity.java
class ch implements Runnable {
    final /* synthetic */ e a;

    ch(e r1_e) {
        this.a = r1_e;
    }

    public void run() {
        this.a.a.startAnimation(this.a.c);
        this.a.a.setVisibility(Base64.DONT_BREAK_LINES);
    }
}