package qsbk.app.nearby.ui;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: InfoCompleteActivity.java
class i implements OnClickListener {
    final /* synthetic */ InfoCompleteActivity a;

    i(InfoCompleteActivity r1_InfoCompleteActivity) {
        this.a = r1_InfoCompleteActivity;
    }

    public void onClick(View r2_View) {
        this.a.r.getPicFromCapture();
    }
}