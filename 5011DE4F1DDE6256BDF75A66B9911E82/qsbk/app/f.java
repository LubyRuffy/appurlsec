package qsbk.app;

import android.os.Handler;
import android.os.Message;
import qsbk.app.utils.ToastAndDialog;

// compiled from: Qiushibaike.java
class f extends Handler {
    final /* synthetic */ Qiushibaike a;

    f(Qiushibaike r1_Qiushibaike) {
        this.a = r1_Qiushibaike;
    }

    public void handleMessage(Message r4_Message) {
        ToastAndDialog.makeText(QsbkApp.mContext, (String) r4_Message.obj, Integer.valueOf(1)).show();
    }
}