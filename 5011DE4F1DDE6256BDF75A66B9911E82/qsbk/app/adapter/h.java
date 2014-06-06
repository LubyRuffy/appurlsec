package qsbk.app.adapter;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import qsbk.app.QsbkApp;

// compiled from: UserMenuLayoutAdapter.java
class h extends Handler {
    final /* synthetic */ UserMenuLayoutAdapter a;

    h(UserMenuLayoutAdapter r1_UserMenuLayoutAdapter) {
        this.a = r1_UserMenuLayoutAdapter;
    }

    public void handleMessage(Message r4_Message) {
        if (((Boolean) r4_Message.obj).booleanValue()) {
            this.a.d().show();
        } else {
            Toast.makeText(QsbkApp.mContext, "\u6ca1\u6709\u68c0\u6d4b\u5230\u65b0\u7248\u672c", 1).show();
        }
    }
}