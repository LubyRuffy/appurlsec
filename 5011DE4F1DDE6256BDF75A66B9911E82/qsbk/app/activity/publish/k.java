package qsbk.app.activity.publish;

// compiled from: Publish_Image.java
class k implements Runnable {
    final /* synthetic */ Publish_Image a;

    k(Publish_Image r1_Publish_Image) {
        this.a = r1_Publish_Image;
    }

    public void run() {
        this.a.a(this.a.t);
        if (this.a.w) {
            if (this.a.Q != null) {
                this.a.m();
            } else {
                this.a.l();
            }
        }
    }
}