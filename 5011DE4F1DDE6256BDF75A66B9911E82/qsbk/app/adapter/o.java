package qsbk.app.adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import java.io.File;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.widget.ProfileHeaderListView;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: UserMenuLayoutAdapter.java
class o extends Handler {
    final /* synthetic */ UserMenuLayoutAdapter a;

    o(UserMenuLayoutAdapter r1_UserMenuLayoutAdapter) {
        this.a = r1_UserMenuLayoutAdapter;
    }

    public void handleMessage(Message r6_Message) {
        if (!Thread.currentThread().isInterrupted()) {
            switch (r6_Message.what) {
                case ProfileHeaderListView.INVALID_TAB_ID:
                    QsbkApp.loading_process = -1;
                    break;
                case XListViewHeader.STATE_READY:
                    QsbkApp.loading_process = r6_Message.arg1;
                    break;
                case XListViewHeader.STATE_REFRESHING:
                    Intent r0_Intent = new Intent("android.intent.action.VIEW");
                    r0_Intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "qiushibaike_" + Constants.serviceVersionName + ".apk")), "application/vnd.android.package-archive");
                    this.a.a.startActivity(r0_Intent);
                    break;
            }
        }
        super.handleMessage(r6_Message);
    }
}