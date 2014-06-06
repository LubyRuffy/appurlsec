package qsbk.app.core;

// compiled from: AsyncTask.java
class d implements Runnable {
    final /* synthetic */ Runnable a;
    final /* synthetic */ c b;

    d(c r1_c, Runnable r2_Runnable) {
        this.b = r1_c;
        this.a = r2_Runnable;
    }

    public void run() {
        this.a.run();
        this.b.a();
    }
}