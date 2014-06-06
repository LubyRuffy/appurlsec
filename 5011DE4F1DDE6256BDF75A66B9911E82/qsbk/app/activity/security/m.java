package qsbk.app.activity.security;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.tencent.tauth.IUiListener;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;

// compiled from: SecurityBindActivity.java
class m implements OnClickListener {
    final /* synthetic */ SecurityBindActivity a;

    m(SecurityBindActivity r1_SecurityBindActivity) {
        this.a = r1_SecurityBindActivity;
    }

    public void onClick(DialogInterface r5_DialogInterface, int r6i) {
        if (r6i == 0) {
            SecurityBindActivity.a(this.a, ThirdPartyConstants.THIRDPARTY_TYLE_QQ);
            IUiListener r0_IUiListener = new c(this.a, null);
            this.a.b = ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, this.a.getApplicationContext());
            this.a.b.login(this.a, "get_user_info,get_simple_userinfo", r0_IUiListener);
        }
        r5_DialogInterface.dismiss();
    }
}