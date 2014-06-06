package qsbk.app.activity;

// compiled from: OneProfileActivity.java
class bu implements Runnable {
    final /* synthetic */ OneProfileActivity a;

    bu(OneProfileActivity r1_OneProfileActivity) {
        this.a = r1_OneProfileActivity;
    }

    public void run() {
        this.a.v.notifyDataSetChanged();
        this.a.y();
    }
}