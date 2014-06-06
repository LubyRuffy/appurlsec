package qsbk.app.widget.imageview;

// compiled from: ImageViewTouchBaseView.java
class g implements Runnable {
    final /* synthetic */ float a;
    final /* synthetic */ long b;
    final /* synthetic */ float c;
    final /* synthetic */ float d;
    final /* synthetic */ float e;
    final /* synthetic */ float f;
    final /* synthetic */ ImageViewTouchBaseView g;

    g(ImageViewTouchBaseView r1_ImageViewTouchBaseView, float r2f, long r3j, float r5f, float r6f, float r7f, float r8f) {
        this.g = r1_ImageViewTouchBaseView;
        this.a = r2f;
        this.b = r3j;
        this.c = r5f;
        this.d = r6f;
        this.e = r7f;
        this.f = r8f;
    }

    public void run() {
        float r0f = Math.min(this.a, (float) (System.currentTimeMillis() - this.b));
        this.g.zoomTo(this.c + this.d * r0f, this.e, this.f);
        if (r0f < this.a) {
            this.g.mHandler.post(this);
        }
    }
}