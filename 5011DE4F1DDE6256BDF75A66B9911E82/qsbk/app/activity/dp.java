package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: UserSettingNavi.java
class dp implements OnClickListener {
    final /* synthetic */ UserSettingNavi a;

    dp(UserSettingNavi r1_UserSettingNavi) {
        this.a = r1_UserSettingNavi;
    }

    public void onClick(DialogInterface r1_DialogInterface, int r2i) {
        r1_DialogInterface.dismiss();
    }
}