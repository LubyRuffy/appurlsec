package qsbk.app.activity;

import qsbk.app.R;

// compiled from: OneProfileActivity.java
class br implements Runnable {
    final /* synthetic */ OneProfileActivity a;

    br(OneProfileActivity r1_OneProfileActivity) {
        this.a = r1_OneProfileActivity;
    }

    public void run() {
        this.a.b(this.a.getString(R.string.no_contents));
    }
}