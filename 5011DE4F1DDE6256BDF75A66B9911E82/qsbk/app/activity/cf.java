package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.QsbkApp;
import qsbk.app.activity.group.TopActivityGroup;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: OneProfileActivity.java
class cf implements OnClickListener {
    final /* synthetic */ OneProfileActivity a;

    cf(OneProfileActivity r1_OneProfileActivity) {
        this.a = r1_OneProfileActivity;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        r4_DialogInterface.dismiss();
        SharePreferenceUtils.remove("loginUser");
        QsbkApp.currentUser = null;
        this.a.startActivity(new Intent(this.a, TopActivityGroup.class));
        SharePreferenceUtils.setSharePreferencesValue(QsbkDatabase.TOKEN, "nologin");
        this.a.w();
    }
}