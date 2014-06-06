package qsbk.app.activity;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

// compiled from: UserSettingNavi.java
class dx implements OnCheckedChangeListener {
    final /* synthetic */ UserSettingNavi a;

    dx(UserSettingNavi r1_UserSettingNavi) {
        this.a = r1_UserSettingNavi;
    }

    public void onCheckedChanged(CompoundButton r2_CompoundButton, boolean r3z) {
        this.a.l = r3z;
    }
}