package qsbk.app.share;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import qsbk.app.QsbkApp;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: ShareUtils.java
class e extends Handler {
    final /* synthetic */ ShareUtils a;

    e(ShareUtils r1_ShareUtils) {
        this.a = r1_ShareUtils;
    }

    public void handleMessage(Message r5_Message) {
        switch (r5_Message.what) {
            case XListViewHeader.STATE_NORMAL:
                Toast.makeText(QsbkApp.mContext, "\u5df2\u8bf7\u6c42\u81f3  " + ((String) r5_Message.obj), 1).show();
                return;
        }
        Toast.makeText(QsbkApp.mContext, "\u5206\u4eab\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\u4e0b", 1).show();
    }
}