package qsbk.app.service;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.QsbkApp;
import qsbk.app.activity.LoginActivity;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: VerifyUserInfoService.java
class d implements OnClickListener {
    final /* synthetic */ c a;

    d(c r1_c) {
        this.a = r1_c;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        r4_DialogInterface.dismiss();
        SharePreferenceUtils.remove("loginUser");
        QsbkApp.currentUser = null;
        Intent r0_Intent = new Intent(QsbkApp.mContext, LoginActivity.class);
        r0_Intent.setFlags(268435456);
        this.a.a.startActivity(r0_Intent);
        if (QsbkApp.tmpContext != null) {
            QsbkApp.tmpContext.finish();
        }
    }
}