package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: UserSetting.java
class dg implements OnClickListener {
    final /* synthetic */ de a;

    dg(de r1_de) {
        this.a = r1_de;
    }

    public void onClick(DialogInterface r2_DialogInterface, int r3i) {
        switch (r3i) {
            case XListViewHeader.STATE_NORMAL:
                this.a.a.F.getPicFromCapture();
                break;
            case XListViewHeader.STATE_READY:
                this.a.a.F.getPicFromContent();
                break;
        }
    }
}