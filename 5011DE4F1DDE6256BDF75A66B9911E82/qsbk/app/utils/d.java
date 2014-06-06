package qsbk.app.utils;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: BrightnessSetting.java
class d implements OnClickListener {
    final /* synthetic */ BrightnessSetting a;

    d(BrightnessSetting r1_BrightnessSetting) {
        this.a = r1_BrightnessSetting;
    }

    public void onClick(DialogInterface r3_DialogInterface, int r4i) {
        if (this.a.c.isChecked()) {
            if (UIHelper.isNightTheme()) {
                SharePreferenceUtils.setSharePreferencesValue("isFlollowSystem_night", "true");
            } else {
                SharePreferenceUtils.setSharePreferencesValue("isFlollowSystem", "true");
            }
        } else if (UIHelper.isNightTheme()) {
            SharePreferenceUtils.setSharePreferencesValue("isFlollowSystem_night", "false");
            SharePreferenceUtils.setSharePreferencesValue("brightness_night", String.valueOf(this.a.b.getProgress()));
        } else {
            SharePreferenceUtils.setSharePreferencesValue("isFlollowSystem", "false");
            SharePreferenceUtils.setSharePreferencesValue("brightness", String.valueOf(this.a.b.getProgress()));
        }
    }
}