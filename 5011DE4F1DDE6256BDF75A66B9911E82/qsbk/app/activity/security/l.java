package qsbk.app.activity.security;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.thirdparty.sso.SsoHandler;

// compiled from: SecurityBindActivity.java
class l implements OnClickListener {
    final /* synthetic */ SecurityBindActivity a;

    l(SecurityBindActivity r1_SecurityBindActivity) {
        this.a = r1_SecurityBindActivity;
    }

    public void onClick(DialogInterface r5_DialogInterface, int r6i) {
        if (r6i == 0) {
            SecurityBindActivity.a(this.a, ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
            SecurityBindActivity.a(this.a, ThirdParty.getInstance(ThirdPartyConstants.SINA_CONSUMER_KEY, ThirdPartyConstants.SINA_REDIRECT_URL, ThirdPartyConstants.THIRDPARTY_TYLE_SINA));
            this.a.a = new SsoHandler(this.a, SecurityBindActivity.d(this.a));
            this.a.a.authorize(new a(this.a));
        }
        r5_DialogInterface.dismiss();
    }
}