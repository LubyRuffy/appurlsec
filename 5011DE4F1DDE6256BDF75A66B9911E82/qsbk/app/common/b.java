package qsbk.app.common;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.AppManager;

// compiled from: UIHelper.java
final class b implements OnClickListener {
    final /* synthetic */ Context a;

    b(Context r1_Context) {
        this.a = r1_Context;
    }

    public void onClick(DialogInterface r3_DialogInterface, int r4i) {
        r3_DialogInterface.dismiss();
        AppManager.getAppManager().AppExit(this.a);
    }
}