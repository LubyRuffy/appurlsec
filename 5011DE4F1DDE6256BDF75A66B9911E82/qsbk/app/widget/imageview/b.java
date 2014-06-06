package qsbk.app.widget.imageview;

// compiled from: GestureImageViewTouchListener.java
class b implements FlingAnimationListener {
    final /* synthetic */ GestureImageViewTouchListener a;

    b(GestureImageViewTouchListener r1_GestureImageViewTouchListener) {
        this.a = r1_GestureImageViewTouchListener;
    }

    public void onComplete() {
    }

    public void onMove(float r4f, float r5f) {
        this.a.handleDrag(this.a.current.x + r4f, this.a.current.y + r5f);
    }
}