package qsbk.app.activity.base;

// compiled from: MysBaseActivity.java
class aj implements Runnable {
    final /* synthetic */ MysBaseActivity a;

    aj(MysBaseActivity r1_MysBaseActivity) {
        this.a = r1_MysBaseActivity;
    }

    public void run() {
        this.a.q.clear();
        this.a.r.clear();
        this.a.p = null;
        this.a.A = null;
        this.a.s = null;
    }
}