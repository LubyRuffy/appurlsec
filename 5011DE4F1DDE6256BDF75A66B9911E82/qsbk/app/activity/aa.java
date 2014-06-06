package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.thirdparty.sso.SsoHandler;

// compiled from: EditInfoEntranceActivity.java
class aa implements OnClickListener {
    final /* synthetic */ EditInfoEntranceActivity a;

    aa(EditInfoEntranceActivity r1_EditInfoEntranceActivity) {
        this.a = r1_EditInfoEntranceActivity;
    }

    public void onClick(View r5_View) {
        if (TextUtils.isEmpty(QsbkApp.currentUser.wb)) {
            this.a.F = ThirdPartyConstants.THIRDPARTY_TYLE_SINA;
            this.a.E = ThirdParty.getInstance(ThirdPartyConstants.SINA_CONSUMER_KEY, ThirdPartyConstants.SINA_REDIRECT_URL, ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
            this.a.o = new SsoHandler(this.a, this.a.E);
            this.a.o.authorize(new a());
        } else {
            Builder r0_Builder = new Builder(this.a).setTitle("\u64cd\u4f5c");
            CharSequence[] r1_CharSequenceA = new CharSequence[2];
            r1_CharSequenceA[0] = "\u6362\u4e00\u4e2a";
            r1_CharSequenceA[1] = "\u53d6\u6d88";
            r0_Builder.setItems(r1_CharSequenceA, new ab(this)).show();
        }
    }
}