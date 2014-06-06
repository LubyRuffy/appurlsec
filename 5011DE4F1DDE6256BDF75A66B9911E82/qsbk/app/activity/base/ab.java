package qsbk.app.activity.base;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.baidu.mobstat.StatService;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.QsbkApp;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: LRParentActivity.java
class ab extends Handler {
    final /* synthetic */ LRParentActivity a;

    ab(LRParentActivity r1_LRParentActivity) {
        this.a = r1_LRParentActivity;
    }

    public void handleMessage(Message r6_Message) {
        switch (r6_Message.what) {
            case XListViewHeader.STATE_NORMAL:
                if (this.a.e().contains("user/signup")) {
                    StatService.onEvent(this.a, "register", "pass", 1);
                }
                this.a.handleToken(this.a.q);
                this.a.j();
                this.a.S.setVisibility(XListViewFooter.STATE_NODATA);
                this.a.finish();
                if (this.a.s != null) {
                    this.a.O.startActivity(new Intent(this.a.O, this.a.s));
                }
                return;
        }
        if (this.a.e().contains("user/signup")) {
            StatService.onEvent(this.a, "register", AdViewUtil.COUNTFAIL, 1);
        }
        this.a.S.setVisibility(XListViewFooter.STATE_NODATA);
        this.a.n.setText(RContactStorage.PRIMARY_KEY);
        Toast.makeText(QsbkApp.mContext, (String) r6_Message.obj, 1).show();
    }
}