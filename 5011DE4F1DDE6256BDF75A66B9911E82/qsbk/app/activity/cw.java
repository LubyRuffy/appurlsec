package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: SingleArticle.java
class cw implements OnClickListener {
    final /* synthetic */ SingleArticle a;

    cw(SingleArticle r1_SingleArticle) {
        this.a = r1_SingleArticle;
    }

    public void onClick(View r3_View) {
        this.a.ai = false;
        this.a.k();
    }
}