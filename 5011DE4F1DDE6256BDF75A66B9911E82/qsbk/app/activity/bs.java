package qsbk.app.activity;

// compiled from: OneProfileActivity.java
class bs implements Runnable {
    final /* synthetic */ OneProfileActivity a;

    bs(OneProfileActivity r1_OneProfileActivity) {
        this.a = r1_OneProfileActivity;
    }

    public void run() {
        this.a.setSupportProgressBarIndeterminateVisibility(true);
    }
}