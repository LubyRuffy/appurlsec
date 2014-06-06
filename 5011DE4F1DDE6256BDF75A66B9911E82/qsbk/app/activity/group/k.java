package qsbk.app.activity.group;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.baidu.mobstat.StatService;
import com.tencent.mm.sdk.contact.RContactStorage;

// compiled from: TopActivityGroup.java
class k implements OnClickListener {
    final /* synthetic */ i a;

    k(i r1_i) {
        this.a = r1_i;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        r4_DialogInterface.dismiss();
        StatService.onEvent(this.a.a, "deny_open_push_on_start", RContactStorage.PRIMARY_KEY);
    }
}