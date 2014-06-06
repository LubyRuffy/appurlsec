package qsbk.app.activity.group;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.baidu.mobstat.StatService;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.manager.PushMessageManager;

// compiled from: TopActivityGroup.java
class j implements OnClickListener {
    final /* synthetic */ i a;

    j(i r1_i) {
        this.a = r1_i;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        r4_DialogInterface.dismiss();
        PushMessageManager.updateRecvMsg(true, this.a.a);
        StatService.onEvent(this.a.a, "open_push_on_start", RContactStorage.PRIMARY_KEY);
    }
}