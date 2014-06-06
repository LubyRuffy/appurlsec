package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.baidu.mobstat.StatService;
import com.tencent.mm.sdk.contact.RContactStorage;

// compiled from: UserSettingNavi.java
class dw implements OnClickListener {
    final /* synthetic */ du a;

    dw(du r1_du) {
        this.a = r1_du;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        StatService.onEvent(this.a.a, "close_push_on_setting", RContactStorage.PRIMARY_KEY);
        r4_DialogInterface.dismiss();
    }
}