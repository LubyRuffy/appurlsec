package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.message.api.ChatEngine;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: OneProfileActivity.java
class cb implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ OneProfileActivity b;

    cb(OneProfileActivity r1_OneProfileActivity, String r2_String) {
        this.b = r1_OneProfileActivity;
        this.a = r2_String;
    }

    public void onClick(DialogInterface r6_DialogInterface, int r7i) {
        String r0_String = null;
        switch (r7i) {
            case XListViewHeader.STATE_NORMAL:
                r0_String = ChatEngine.BLACK_LIST;
                break;
            case XListViewHeader.STATE_READY:
                r0_String = ChatEngine.REPORT;
                break;
        }
        OneProfileActivity r1_OneProfileActivity = this.b;
        Object[] r2_ObjectA = new Object[1];
        r2_ObjectA[0] = this.a;
        r1_OneProfileActivity.f(String.format(r0_String, r2_ObjectA));
    }
}