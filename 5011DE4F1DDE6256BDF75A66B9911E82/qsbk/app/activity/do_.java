package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;

// compiled from: UserSettingNavi.java
class do_ implements OnClickListener {
    final /* synthetic */ UserSettingNavi a;

    do_(UserSettingNavi r1_UserSettingNavi) {
        this.a = r1_UserSettingNavi;
    }

    public void onClick(View r4_View) {
        this.a.finish();
        this.a.overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
    }
}