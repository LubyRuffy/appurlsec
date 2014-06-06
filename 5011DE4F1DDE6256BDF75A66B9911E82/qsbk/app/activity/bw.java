package qsbk.app.activity;

// compiled from: OneProfileActivity.java
class bw implements Runnable {
    final /* synthetic */ OneProfileActivity a;

    bw(OneProfileActivity r1_OneProfileActivity) {
        this.a = r1_OneProfileActivity;
    }

    public void run() {
        this.a.j();
        this.a.l();
        this.a.m();
        this.a.u.notifyDataSetChanged();
        this.a.y();
    }
}