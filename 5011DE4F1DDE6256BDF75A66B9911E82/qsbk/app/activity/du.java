package qsbk.app.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

// compiled from: UserSettingNavi.java
class du implements OnCheckedChangeListener {
    final /* synthetic */ UserSettingNavi a;

    du(UserSettingNavi r1_UserSettingNavi) {
        this.a = r1_UserSettingNavi;
    }

    public void onCheckedChanged(CompoundButton r4_CompoundButton, boolean r5z) {
        if (!r5z) {
            AlertDialog r0_AlertDialog = new Builder(this.a).setTitle("\u6e29\u99a8\u63d0\u793a").setMessage("\u5173\u95ed\u63a8\u9001\u5c31\u4eab\u53d7\u4e0d\u5230\u4fbf\u4fbf\u541b\u6bcf\u5929\u7cbe\u9009\u7684\u65e0\u8282\u64cd\u7cd7\u4e8b\uff0c\u4f60\u786e\u5b9a\u5173\u95ed\u5417\uff1f").setPositiveButton("\u5173\u95ed", new dw(this)).setNegativeButton("\u53d6\u6d88", new dv(this)).create();
            r0_AlertDialog.setCanceledOnTouchOutside(false);
            r0_AlertDialog.show();
        }
        this.a.j = r5z;
    }
}