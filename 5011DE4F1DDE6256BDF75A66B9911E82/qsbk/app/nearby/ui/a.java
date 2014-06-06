package qsbk.app.nearby.ui;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: AbstractDialog.java
class a implements OnClickListener {
    final /* synthetic */ AbstractDialog a;

    a(AbstractDialog r1_AbstractDialog) {
        this.a = r1_AbstractDialog;
    }

    public void onClick(View r4_View) {
        this.a.saveContent();
        this.a.g.onClick(this.a, -1);
    }
}