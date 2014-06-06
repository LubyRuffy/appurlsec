package qsbk.app.activity;

// compiled from: GuideActivity.java
class an implements Runnable {
    final /* synthetic */ GuideActivity a;

    an(GuideActivity r1_GuideActivity) {
        this.a = r1_GuideActivity;
    }

    public void run() {
        if (!this.a.isFinishing()) {
            this.a.finish();
        }
    }
}