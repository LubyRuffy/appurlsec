package qsbk.app.activity.security;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: SecurityBindActivity.java
class p extends Handler {
    final /* synthetic */ SecurityBindActivity a;

    p(SecurityBindActivity r1_SecurityBindActivity) {
        this.a = r1_SecurityBindActivity;
    }

    public void handleMessage(Message r4_Message) {
        this.a.i.setText("\u8bbe\u5b9a\u5bc6\u4fdd");
        if (r4_Message.what != 0) {
            Toast.makeText(this.a, (String) r4_Message.obj, 1).show();
        } else {
            try {
                if (this.a.t.equals(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)) {
                    this.a.j.setText(this.a.v.getString(ThirdPartyConstants.THIRDPARTY_TYLE_SINA));
                    QsbkApp.currentUser.wb = this.a.v.getString(ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
                    SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
                    if (TextUtils.isEmpty(this.a.y)) {
                        this.a.finish();
                        this.a.overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
                    }
                } else {
                    this.a.k.setText(this.a.v.getString(ThirdPartyConstants.THIRDPARTY_TYLE_QQ));
                    QsbkApp.currentUser.wb = this.a.v.getString(ThirdPartyConstants.THIRDPARTY_TYLE_QQ);
                    SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
                    if (TextUtils.isEmpty(this.a.y)) {
                    } else {
                        this.a.finish();
                        this.a.overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}