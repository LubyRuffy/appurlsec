package qsbk.app.activity;

// compiled from: OneProfileActivity.java
class bt implements Runnable {
    final /* synthetic */ OneProfileActivity a;

    bt(OneProfileActivity r1_OneProfileActivity) {
        this.a = r1_OneProfileActivity;
    }

    public void run() {
        this.a.setSupportProgressBarIndeterminateVisibility(false);
    }
}