package android.support.v4.app;

// compiled from: FragmentManager.java
class m implements Runnable {
    final /* synthetic */ l a;

    m(l r1_l) {
        this.a = r1_l;
    }

    public void run() {
        this.a.execPendingActions();
    }
}