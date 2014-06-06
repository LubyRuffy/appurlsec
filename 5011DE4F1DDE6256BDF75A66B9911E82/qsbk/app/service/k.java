package qsbk.app.service;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: VersionCheckService.java
class k implements OnClickListener {
    final /* synthetic */ VersionCheckService a;

    k(VersionCheckService r1_VersionCheckService) {
        this.a = r1_VersionCheckService;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        SharePreferenceUtils.setSharePreferencesValue(VersionCheckService.CANCLE_UDATE_TIME, String.valueOf(System.currentTimeMillis()));
        r4_DialogInterface.dismiss();
    }
}