package qsbk.app.widget.imageview;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

// compiled from: GestureImageView.java
class a implements OnTouchListener {
    final /* synthetic */ GestureImageView a;

    a(GestureImageView r1_GestureImageView) {
        this.a = r1_GestureImageView;
    }

    public boolean onTouch(View r2_View, MotionEvent r3_MotionEvent) {
        if (GestureImageView.access$000(this.a) != null) {
            GestureImageView.access$000(this.a).onTouch(r2_View, r3_MotionEvent);
        }
        return GestureImageView.access$100(this.a).onTouch(r2_View, r3_MotionEvent);
    }
}