package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.HttpUtils;

// compiled from: ManageMyContentsActivity.java
class bh implements OnClickListener {
    final /* synthetic */ be a;

    bh(be r1_be) {
        this.a = r1_be;
    }

    public void onClick(DialogInterface r5_DialogInterface, int r6i) {
        if (HttpUtils.isNetworkConnected(this.a.a)) {
            Toast.makeText(QsbkApp.mContext, "\u6b63\u5728\u5220\u9664\uff0c\u8bf7\u7a0d\u540e...", 0).show();
            this.a.a.m();
        } else {
            Toast.makeText(QsbkApp.mContext, this.a.a.getResources().getString(R.string.network_not_connected), 0).show();
        }
    }
}