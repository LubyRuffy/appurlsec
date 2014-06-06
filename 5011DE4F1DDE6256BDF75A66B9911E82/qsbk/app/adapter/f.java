package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import qsbk.app.QsbkApp;
import qsbk.app.utils.HttpUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: AdapterForLinearLayout.java
class f implements OnClickListener {
    final /* synthetic */ a a;

    f(a r1_a) {
        this.a = r1_a;
    }

    public void onClick(DialogInterface r5_DialogInterface, int r6i) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        switch (r6i) {
            case XListViewHeader.STATE_NORMAL:
                r0_String = "abusive";
                break;
            case XListViewHeader.STATE_READY:
                r0_String = "porn";
                break;
            case XListViewHeader.STATE_REFRESHING:
                r0_String = MyContentsAdapter.SPAM;
                break;
            case XListViewFooter.STATE_NOMORE:
                r0_String = "waste";
                break;
        }
        if (HttpUtils.netIsAvailable()) {
            r5_DialogInterface.dismiss();
            this.a.a.reportComment(this.a.c.optString(LocaleUtil.INDONESIAN), r0_String);
        } else {
            Toast.makeText(QsbkApp.mContext, "\u672a\u53d1\u73b0\u53ef\u7528\u7f51\u7edc\uff0c\u8bf7\u7a0d\u5019\u518d\u8bd5", 1).show();
            r5_DialogInterface.dismiss();
        }
    }
}