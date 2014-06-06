package qsbk.app.activity;

import android.view.View;
import android.view.View.OnLongClickListener;

// compiled from: SingleArticle.java
class cr implements OnLongClickListener {
    final /* synthetic */ SingleArticle a;

    cr(SingleArticle r1_SingleArticle) {
        this.a = r1_SingleArticle;
    }

    public boolean onLongClick(View r2_View) {
        this.a.j();
        return false;
    }
}