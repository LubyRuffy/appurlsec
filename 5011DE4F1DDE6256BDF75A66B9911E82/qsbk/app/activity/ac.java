package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.tauth.IUiListener;
import qsbk.app.QsbkApp;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;

// compiled from: EditInfoEntranceActivity.java
class ac implements OnClickListener {
    final /* synthetic */ EditInfoEntranceActivity a;

    ac(EditInfoEntranceActivity r1_EditInfoEntranceActivity) {
        this.a = r1_EditInfoEntranceActivity;
    }

    public void onClick(View r5_View) {
        if (TextUtils.isEmpty(QsbkApp.currentUser.qq)) {
            this.a.F = ThirdPartyConstants.THIRDPARTY_TYLE_QQ;
            IUiListener r0_IUiListener = new c(null);
            this.a.p = ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, this.a.getApplicationContext());
            this.a.p.login(this.a, "get_user_info,get_simple_userinfo", r0_IUiListener);
        } else {
            Builder r0_Builder = new Builder(this.a).setTitle("\u64cd\u4f5c");
            CharSequence[] r1_CharSequenceA = new CharSequence[2];
            r1_CharSequenceA[0] = "\u6362\u4e00\u4e2a";
            r1_CharSequenceA[1] = "\u53d6\u6d88";
            r0_Builder.setItems(r1_CharSequenceA, new ad(this)).show();
        }
    }
}