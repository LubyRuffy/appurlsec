package qsbk.app.activity;

// compiled from: OneProfileActivity.java
class bv implements Runnable {
    final /* synthetic */ OneProfileActivity a;

    bv(OneProfileActivity r1_OneProfileActivity) {
        this.a = r1_OneProfileActivity;
    }

    public void run() {
        this.a.w.notifyDataSetChanged();
        this.a.y();
    }
}