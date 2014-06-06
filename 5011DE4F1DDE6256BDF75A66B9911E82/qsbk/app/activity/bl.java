package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: MyContentsActivity.java
class bl implements OnClickListener {
    final /* synthetic */ bk a;

    bl(bk r1_bk) {
        this.a = r1_bk;
    }

    public void onClick(DialogInterface r1_DialogInterface, int r2i) {
        r1_DialogInterface.dismiss();
    }
}