package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: EditInfoBaseActivity.java
class v implements OnClickListener {
    final /* synthetic */ EditInfoBaseActivity a;

    v(EditInfoBaseActivity r1_EditInfoBaseActivity) {
        this.a = r1_EditInfoBaseActivity;
    }

    public void onClick(View r2_View) {
        this.a.onCancel(r2_View);
    }
}