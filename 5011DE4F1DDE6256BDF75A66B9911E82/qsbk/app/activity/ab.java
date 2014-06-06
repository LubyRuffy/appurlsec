package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.thirdparty.sso.SsoHandler;

// compiled from: EditInfoEntranceActivity.java
class ab implements OnClickListener {
    final /* synthetic */ aa a;

    ab(aa r1_aa) {
        this.a = r1_aa;
    }

    public void onClick(DialogInterface r5_DialogInterface, int r6i) {
        if (r6i == 0) {
            this.a.a.F = ThirdPartyConstants.THIRDPARTY_TYLE_SINA;
            this.a.a.E = ThirdParty.getInstance(ThirdPartyConstants.SINA_CONSUMER_KEY, ThirdPartyConstants.SINA_REDIRECT_URL, ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
            this.a.a.o = new SsoHandler(this.a.a, this.a.a.E);
            this.a.a.o.authorize(new a());
        }
        r5_DialogInterface.dismiss();
    }
}