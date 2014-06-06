package qsbk.app.service;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.Constants;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: VersionCheckService.java
class n implements OnClickListener {
    final /* synthetic */ VersionCheckService a;

    n(VersionCheckService r1_VersionCheckService) {
        this.a = r1_VersionCheckService;
    }

    public void onClick(DialogInterface r3_DialogInterface, int r4i) {
        SharePreferenceUtils.setSharePreferencesValue(VersionCheckService.CANCLE_UDATE_TIME, "-1");
        SharePreferenceUtils.setSharePreferencesValue(VersionCheckService.CANCLE_UDATE_VERSION, Constants.serverVersion);
        r3_DialogInterface.dismiss();
    }
}