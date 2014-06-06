package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: UserMenuLayoutAdapter.java
class l implements OnClickListener {
    final /* synthetic */ UserMenuLayoutAdapter a;

    l(UserMenuLayoutAdapter r1_UserMenuLayoutAdapter) {
        this.a = r1_UserMenuLayoutAdapter;
    }

    public void onClick(DialogInterface r1_DialogInterface, int r2i) {
        r1_DialogInterface.dismiss();
    }
}