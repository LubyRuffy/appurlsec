package qsbk.app.activity;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: UserSettingNavi.java
class dt implements OnCheckedChangeListener {
    final /* synthetic */ UserSettingNavi a;

    dt(UserSettingNavi r1_UserSettingNavi) {
        this.a = r1_UserSettingNavi;
    }

    public void onCheckedChanged(CompoundButton r3_CompoundButton, boolean r4z) {
        if (r4z) {
            SharePreferenceUtils.setSharePreferencesValue("themeid", "night");
        } else {
            SharePreferenceUtils.setSharePreferencesValue("themeid", "day");
        }
        if (QsbkApp.currentTheme.equals(SharePreferenceUtils.getSharePreferencesValue("themeid"))) {
            this.a.h = false;
        } else {
            this.a.h = true;
        }
        this.a.onCreate(null);
        QsbkApp.getInstance().destoryImageWorker();
    }
}