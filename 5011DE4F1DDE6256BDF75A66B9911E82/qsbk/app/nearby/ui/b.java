package qsbk.app.nearby.ui;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.audit.RequestListener;

// compiled from: AbstractDialog.java
class b implements OnClickListener {
    final /* synthetic */ AbstractDialog a;

    b(AbstractDialog r1_AbstractDialog) {
        this.a = r1_AbstractDialog;
    }

    public void onClick(View r4_View) {
        this.a.resetContent();
        this.a.h.onClick(this.a, RequestListener.DEFAULT_LOADED_SIZE);
    }
}