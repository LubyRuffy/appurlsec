package qsbk.app.activity.publish;

// compiled from: Publish_Image.java
class j implements Runnable {
    final /* synthetic */ Runnable a;
    final /* synthetic */ Publish_Image b;

    j(Publish_Image r1_Publish_Image, Runnable r2_Runnable) {
        this.b = r1_Publish_Image;
        this.a = r2_Runnable;
    }

    public void run() {
        this.b.M = this.b.o.getWidth();
        this.b.L = this.b.o.getHeight();
        this.b.c();
        this.b.g();
        this.b.a(this.b.t);
        this.b.n.setImageBitmap(this.b.t);
        this.b.I.postDelayed(this.a, 100);
    }
}