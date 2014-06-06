package qsbk.app.widget.imageview;

// compiled from: ImageViewTouchBaseView.java
class h implements Runnable {
    float a;
    float b;
    final /* synthetic */ float c;
    final /* synthetic */ long d;
    final /* synthetic */ float e;
    final /* synthetic */ float f;
    final /* synthetic */ ImageViewTouchBaseView g;

    h(ImageViewTouchBaseView r2_ImageViewTouchBaseView, float r3f, long r4j, float r6f, float r7f) {
        this.g = r2_ImageViewTouchBaseView;
        this.c = r3f;
        this.d = r4j;
        this.e = r6f;
        this.f = r7f;
        this.a = 0.0f;
        this.b = 0.0f;
    }

    public void run() {
        float r0f = Math.min(this.c, (float) (System.currentTimeMillis() - this.d));
        r0f = r0f / this.c - 1.0f;
        float r1f = this.e * (((r0f * r0f) * r0f) + 1.0f);
        r0f = r0f / this.c - 1.0f;
        float r2f = this.f * (((r0f * r0f) * r0f) + 1.0f);
        this.g.panBy(r1f - this.a, r2f - this.b);
        this.a = r1f;
        this.b = r2f;
        if (r0f < this.c) {
            this.g.mHandler.post(this);
        }
    }
}