package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;

// compiled from: UserSettingNavi.java
class ds implements OnClickListener {
    final /* synthetic */ UserSettingNavi a;

    ds(UserSettingNavi r1_UserSettingNavi) {
        this.a = r1_UserSettingNavi;
    }

    public void onClick(DialogInterface r3_DialogInterface, int r4i) {
        if (r4i > 2 || r4i < 0) {
            r4i = 0;
            this.a.d.setText(UserSettingNavi.p[r4i]);
            QsbkApp.getInstance().setContentTextSize(UserSettingNavi.o[r4i]);
            r3_DialogInterface.dismiss();
        } else {
            this.a.d.setText(UserSettingNavi.p[r4i]);
            QsbkApp.getInstance().setContentTextSize(UserSettingNavi.o[r4i]);
            r3_DialogInterface.dismiss();
        }
    }
}