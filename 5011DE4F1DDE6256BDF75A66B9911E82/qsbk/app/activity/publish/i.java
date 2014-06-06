package qsbk.app.activity.publish;

// compiled from: Publish_Image.java
class i implements Runnable {
    final /* synthetic */ Publish_Image a;

    i(Publish_Image r1_Publish_Image) {
        this.a = r1_Publish_Image;
    }

    public void run() {
        this.a.N = this.a.n.getHeight();
        this.a.O = this.a.n.getWidth();
        this.a.k();
        if (this.a.w) {
            this.a.l();
        }
    }
}