package qsbk.app.utils;

// compiled from: ActivityExitHelper.java
class a implements Runnable {
    final /* synthetic */ ActivityExitHelper a;

    a(ActivityExitHelper r1_ActivityExitHelper) {
        this.a = r1_ActivityExitHelper;
    }

    public void run() {
        this.a.a = Boolean.valueOf(false);
        this.a.d = Boolean.valueOf(false);
    }
}