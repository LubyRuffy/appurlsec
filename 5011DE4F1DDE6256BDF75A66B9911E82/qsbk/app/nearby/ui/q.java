package qsbk.app.nearby.ui;

// compiled from: NearByListActivity.java
class q implements Runnable {
    final /* synthetic */ NearByListActivity a;

    q(NearByListActivity r1_NearByListActivity) {
        this.a = r1_NearByListActivity;
    }

    public void run() {
        NearByListActivity.f(this.a).getLocation(this.a);
    }
}