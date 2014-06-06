package qsbk.app.activity.group;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.HttpUtils;

// compiled from: HistoryActivityGroup.java
class b implements OnClickListener {
    final /* synthetic */ HistoryActivityGroup a;

    b(HistoryActivityGroup r1_HistoryActivityGroup) {
        this.a = r1_HistoryActivityGroup;
    }

    public void onClick(View r4_View) {
        if (HttpUtils.isNetworkConnected(this.a.a)) {
            this.a.setTitleName("\u7a7f\u8d8a\u4e2d...");
            this.a.l();
        } else {
            Toast.makeText(QsbkApp.mContext, R.string.network_not_connected, 0).show();
        }
    }
}