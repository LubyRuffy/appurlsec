package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: EditInfoEntranceActivity.java
class ai implements OnClickListener {
    final /* synthetic */ ag a;

    ai(ag r1_ag) {
        this.a = r1_ag;
    }

    public void onClick(DialogInterface r2_DialogInterface, int r3i) {
        switch (r3i) {
            case XListViewHeader.STATE_NORMAL:
                this.a.a.v.getPicFromCapture();
                break;
            case XListViewHeader.STATE_READY:
                this.a.a.v.getPicFromContent();
                break;
        }
    }
}