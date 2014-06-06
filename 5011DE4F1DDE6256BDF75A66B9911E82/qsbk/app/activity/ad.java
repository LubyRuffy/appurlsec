package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.tencent.tauth.IUiListener;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;

// compiled from: EditInfoEntranceActivity.java
class ad implements OnClickListener {
    final /* synthetic */ ac a;

    ad(ac r1_ac) {
        this.a = r1_ac;
    }

    public void onClick(DialogInterface r5_DialogInterface, int r6i) {
        if (r6i == 0) {
            this.a.a.F = ThirdPartyConstants.THIRDPARTY_TYLE_QQ;
            IUiListener r0_IUiListener = new c(null);
            this.a.a.p = ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, this.a.a.getApplicationContext());
            this.a.a.p.login(this.a.a, "get_user_info,get_simple_userinfo", r0_IUiListener);
        }
        r5_DialogInterface.dismiss();
    }
}