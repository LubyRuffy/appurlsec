package android.support.v4.app;

// compiled from: FragmentManager.java
class o implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ l c;

    o(l r1_l, String r2_String, int r3i) {
        this.c = r1_l;
        this.a = r2_String;
        this.b = r3i;
    }

    public void run() {
        this.c.a(this.c.o.a, this.a, -1, this.b);
    }
}