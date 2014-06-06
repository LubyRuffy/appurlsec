package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: AdapterForLinearLayout.java
class d implements OnClickListener {
    final /* synthetic */ c a;

    d(c r1_c) {
        this.a = r1_c;
    }

    public void onClick(DialogInterface r1_DialogInterface, int r2i) {
        r1_DialogInterface.dismiss();
    }
}