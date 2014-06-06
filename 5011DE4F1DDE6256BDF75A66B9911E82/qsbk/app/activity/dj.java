package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.QsbkApp;
import qsbk.app.activity.group.TopActivityGroup;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: UserSetting.java
class dj implements OnClickListener {
    final /* synthetic */ dh a;

    dj(dh r1_dh) {
        this.a = r1_dh;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        r4_DialogInterface.dismiss();
        this.a.a.finish();
        SharePreferenceUtils.remove("loginUser");
        QsbkApp.currentUser = null;
        this.a.a.startActivity(new Intent(this.a.a, TopActivityGroup.class));
        SharePreferenceUtils.setSharePreferencesValue(QsbkDatabase.TOKEN, "nologin");
        this.a.a.logout();
    }
}