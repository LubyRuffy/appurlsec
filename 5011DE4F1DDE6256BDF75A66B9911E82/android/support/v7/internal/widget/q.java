package android.support.v7.internal.widget;

import android.support.v7.internal.widget.AdapterViewICS.OnItemClickListener;
import android.view.View;

// compiled from: SpinnerICS.java
class q implements OnItemClickListener {
    final /* synthetic */ p a;
    final /* synthetic */ c b;

    q(c r1_c, p r2_p) {
        this.b = r1_c;
        this.a = r2_p;
    }

    public void onItemClick(AdapterViewICS r4_AdapterViewICS, View r5_View, int r6i, long r7j) {
        this.b.b.setSelection(r6i);
        if (this.b.b.t != null) {
            this.b.b.performItemClick(r5_View, r6i, c.a(this.b).getItemId(r6i));
        }
        this.b.dismiss();
    }
}