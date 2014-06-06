package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: MenuLayoutAdapter.java
class g implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ MenuLayoutAdapter c;

    g(MenuLayoutAdapter r1_MenuLayoutAdapter, int r2i, int r3i) {
        this.c = r1_MenuLayoutAdapter;
        this.a = r2i;
        this.b = r3i;
    }

    public void onClick(View r3_View) {
        this.c.i = false;
        if (this.c.a(this.a) || (!this.c.b(this.b))) {
            this.c.notifyDataSetInvalidated();
        } else {
            this.c.c(this.a);
            this.c.notifyDataSetInvalidated();
        }
    }
}