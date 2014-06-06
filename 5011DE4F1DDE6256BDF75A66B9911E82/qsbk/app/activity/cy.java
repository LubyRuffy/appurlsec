package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;

// compiled from: SingleArticle.java
class cy implements OnClickListener {
    final /* synthetic */ SingleArticle a;

    cy(SingleArticle r1_SingleArticle) {
        this.a = r1_SingleArticle;
    }

    public void onClick(View r5_View) {
        if (HttpUtils.netIsAvailable()) {
            this.a.I.setVisibility(Base64.DONT_BREAK_LINES);
            this.a.J.setVisibility(0);
            new AsyncDataLoader(this.a.aj, "qsbk-AT-SA-02").execute(new Void[0]);
        } else {
            Toast.makeText(QsbkApp.mContext, R.string.network_not_connected, 0).show();
        }
    }
}