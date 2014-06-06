package qsbk.app.activity.base;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

// compiled from: CommDialogActivity.java
class c implements OnItemClickListener {
    final /* synthetic */ CommDialogActivity a;

    c(CommDialogActivity r1_CommDialogActivity) {
        this.a = r1_CommDialogActivity;
    }

    public void onItemClick(AdapterView<?> r2_AdapterView_, View r3_View, int r4i, long r5j) {
        this.a.confirmOption(r4i);
    }
}