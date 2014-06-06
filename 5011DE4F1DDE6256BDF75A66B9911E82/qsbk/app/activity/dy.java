package qsbk.app.activity;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import qsbk.app.QsbkApp;

// compiled from: UserSettingNavi.java
class dy extends Handler {
    final /* synthetic */ UserSettingNavi a;

    dy(UserSettingNavi r1_UserSettingNavi) {
        this.a = r1_UserSettingNavi;
    }

    public void handleMessage(Message r4_Message) {
        if (((Boolean) r4_Message.obj).booleanValue()) {
            this.a.g().show();
        } else {
            Toast.makeText(QsbkApp.mContext, "\u6ca1\u6709\u68c0\u6d4b\u5230\u65b0\u7248\u672c", 1).show();
        }
    }
}