package qsbk.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsResult;

// compiled from: About.java
class d implements OnClickListener {
    final /* synthetic */ JsResult a;
    final /* synthetic */ c b;

    d(c r1_c, JsResult r2_JsResult) {
        this.b = r1_c;
        this.a = r2_JsResult;
    }

    public void onClick(DialogInterface r2_DialogInterface, int r3i) {
        this.a.confirm();
    }
}