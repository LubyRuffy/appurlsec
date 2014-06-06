package qsbk.app.service;

import android.text.TextUtils;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: VerifyUserInfoService.java
class e extends Thread {
    final /* synthetic */ VerifyUserInfoService a;

    e(VerifyUserInfoService r1_VerifyUserInfoService, String r2_String) {
        this.a = r1_VerifyUserInfoService;
        super(r2_String);
    }

    public void run() {
        String r0_String = SharePreferenceUtils.getSharePreferencesValue("lastVerifyTime");
        if (TextUtils.isEmpty(r0_String)) {
            this.a.b();
        } else {
            if (System.currentTimeMillis() - Long.valueOf(r0_String).longValue() > 259200000) {
                this.a.b();
            } else {
                this.a.b.obtainMessage(1, Boolean.valueOf(false)).sendToTarget();
            }
        }
    }
}