package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: UserSetting.java
class db implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ UserSetting b;

    db(UserSetting r1_UserSetting, String r2_String) {
        this.b = r1_UserSetting;
        this.a = r2_String;
    }

    public void onClick(DialogInterface r3_DialogInterface, int r4i) {
        if (r4i == 0) {
            SharePreferenceUtils.remove(this.a);
            this.b.f(this.a);
        }
        r3_DialogInterface.dismiss();
    }
}