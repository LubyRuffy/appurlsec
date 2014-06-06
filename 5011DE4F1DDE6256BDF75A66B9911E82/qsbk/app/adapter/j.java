package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.QsbkApp;
import qsbk.app.service.VersionService;

// compiled from: UserMenuLayoutAdapter.java
class j implements OnClickListener {
    final /* synthetic */ UserMenuLayoutAdapter a;

    j(UserMenuLayoutAdapter r1_UserMenuLayoutAdapter) {
        this.a = r1_UserMenuLayoutAdapter;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        new k(this, "bqk-UserMenu2").start();
        this.a.a.startService(new Intent(QsbkApp.mContext, VersionService.class));
    }
}