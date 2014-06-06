package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.baidu.mobstat.StatService;
import com.tencent.mm.sdk.contact.RContactStorage;

// compiled from: UserSettingNavi.java
class dv implements OnClickListener {
    final /* synthetic */ du a;

    dv(du r1_du) {
        this.a = r1_du;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        r4_DialogInterface.dismiss();
        StatService.onEvent(this.a.a, "cancel_close_push_on_setting", RContactStorage.PRIMARY_KEY);
        this.a.a.i.setChecked(true);
    }
}