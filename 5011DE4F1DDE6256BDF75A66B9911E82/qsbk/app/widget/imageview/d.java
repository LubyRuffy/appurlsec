package qsbk.app.widget.imageview;

// compiled from: GestureImageViewTouchListener.java
class d implements MoveAnimationListener {
    final /* synthetic */ GestureImageView a;
    final /* synthetic */ GestureImageViewTouchListener b;

    d(GestureImageViewTouchListener r1_GestureImageViewTouchListener, GestureImageView r2_GestureImageView) {
        this.b = r1_GestureImageViewTouchListener;
        this.a = r2_GestureImageView;
    }

    public void onMove(float r2f, float r3f) {
        this.a.setPosition(r2f, r3f);
        this.a.redraw();
    }
}