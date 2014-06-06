package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: AdapterForLinearLayout.java
class e implements OnClickListener {
    final /* synthetic */ a a;

    e(a r1_a) {
        this.a = r1_a;
    }

    public void onClick(DialogInterface r1_DialogInterface, int r2i) {
        r1_DialogInterface.dismiss();
    }
}