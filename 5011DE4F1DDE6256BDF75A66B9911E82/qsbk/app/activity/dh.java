package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: UserSetting.java
class dh implements OnClickListener {
    final /* synthetic */ UserSetting a;

    dh(UserSetting r1_UserSetting) {
        this.a = r1_UserSetting;
    }

    public void onClick(View r4_View) {
        new Builder(this.a).setTitle("\u6e29\u99a8\u63d0\u793a").setMessage("\u786e\u5b9a\u8981\u9000\u51fa\u767b\u5f55\u5417\uff1f").setPositiveButton("\u786e\u5b9a", new dj(this)).setNegativeButton("\u53d6\u6d88", new di(this)).show();
    }
}