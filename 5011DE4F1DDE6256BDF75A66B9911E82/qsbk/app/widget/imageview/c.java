package qsbk.app.widget.imageview;

// compiled from: GestureImageViewTouchListener.java
class c implements ZoomAnimationListener {
    final /* synthetic */ GestureImageViewTouchListener a;

    c(GestureImageViewTouchListener r1_GestureImageViewTouchListener) {
        this.a = r1_GestureImageViewTouchListener;
    }

    public void onComplete() {
        this.a.inZoom = false;
        this.a.handleUp();
    }

    public void onZoom(float r2f, float r3f, float r4f) {
        if (r2f > this.a.maxScale || r2f < this.a.minScale) {
        } else {
            this.a.handleScale(r2f, r3f, r4f);
        }
    }
}