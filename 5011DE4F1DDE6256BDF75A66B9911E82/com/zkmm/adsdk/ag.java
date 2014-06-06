package com.zkmm.adsdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.webkit.JsResult;

// compiled from: SourceFile
final class ag implements OnCancelListener {
    private final /* synthetic */ JsResult a;

    ag(ax r1_ax, JsResult r2_JsResult) {
        this.a = r2_JsResult;
    }

    public final void onCancel(DialogInterface r2_DialogInterface) {
        this.a.cancel();
    }
}