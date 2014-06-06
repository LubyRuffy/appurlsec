package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.Base64;

// compiled from: SingleArticle.java
class cl implements OnClickListener {
    final /* synthetic */ SingleArticle a;

    cl(SingleArticle r1_SingleArticle) {
        this.a = r1_SingleArticle;
    }

    public void onClick(View r2_View) {
        r2_View.setVisibility(Base64.DONT_BREAK_LINES);
        this.a.d();
    }
}