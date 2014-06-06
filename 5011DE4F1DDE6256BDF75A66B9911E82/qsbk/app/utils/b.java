package qsbk.app.utils;

// compiled from: ActivityExitHelper.java
class b implements Runnable {
    final /* synthetic */ ActivityExitHelper a;

    b(ActivityExitHelper r1_ActivityExitHelper) {
        this.a = r1_ActivityExitHelper;
    }

    public void run() {
        this.a.e = true;
    }
}