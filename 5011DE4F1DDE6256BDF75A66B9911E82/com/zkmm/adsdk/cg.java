package com.zkmm.adsdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsResult;

// compiled from: SourceFile
final class cg implements OnClickListener {
    private final /* synthetic */ JsResult a;

    cg(k r1_k, JsResult r2_JsResult) {
        this.a = r2_JsResult;
    }

    public final void onClick(DialogInterface r2_DialogInterface, int r3i) {
        this.a.cancel();
    }
}