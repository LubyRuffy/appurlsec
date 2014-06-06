package qsbk.app.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;

// compiled from: UserSetting.java
class dd implements OnClickListener {
    final /* synthetic */ UserSetting a;

    dd(UserSetting r1_UserSetting) {
        this.a = r1_UserSetting;
    }

    public void onClick(View r4_View) {
        this.a.finish();
        if (!TextUtils.isEmpty(this.a.D)) {
            this.a.overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
        }
    }
}