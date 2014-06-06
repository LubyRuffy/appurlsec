package qsbk.app.activity;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.QsbkApp;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: MyContentsActivity.java
class bi extends Handler {
    final /* synthetic */ MyContentsActivity a;

    bi(MyContentsActivity r1_MyContentsActivity) {
        this.a = r1_MyContentsActivity;
    }

    public void handleMessage(Message r4_Message) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        switch (r4_Message.what) {
            case XListViewHeader.STATE_NORMAL:
                this.a.r.remove(this.a.y);
                this.a.q.notifyDataSetChanged();
                r0_String = "\u5220\u9664\u7cd7\u4e8b#" + this.a.y.id + "\u6210\u529f";
                Toast.makeText(QsbkApp.mContext, r0_String, 0).show();
            case AdViewUtil.NETWORK_TYPE_CUSTOMIZE:
                r0_String = "\u5220\u9664\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5\uff01";
                Toast.makeText(QsbkApp.mContext, r0_String, 0).show();
        }
        r0_String = (String) r4_Message.obj;
        Toast.makeText(QsbkApp.mContext, r0_String, 0).show();
    }
}