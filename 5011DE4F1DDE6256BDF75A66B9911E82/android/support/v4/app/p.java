package android.support.v4.app;

// compiled from: FragmentManager.java
class p implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ l c;

    p(l r1_l, int r2i, int r3i) {
        this.c = r1_l;
        this.a = r2i;
        this.b = r3i;
    }

    public void run() {
        this.c.a(this.c.o.a, null, this.a, this.b);
    }
}