package android.support.v4.app;

// compiled from: ListFragment.java
class u implements Runnable {
    final /* synthetic */ ListFragment a;

    u(ListFragment r1_ListFragment) {
        this.a = r1_ListFragment;
    }

    public void run() {
        this.a.b.focusableViewAvailable(this.a.b);
    }
}