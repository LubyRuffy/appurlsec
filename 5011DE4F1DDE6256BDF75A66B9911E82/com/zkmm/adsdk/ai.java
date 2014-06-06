package com.zkmm.adsdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsPromptResult;

// compiled from: SourceFile
final class ai implements OnClickListener {
    private final /* synthetic */ JsPromptResult a;

    ai(ax r1_ax, JsPromptResult r2_JsPromptResult) {
        this.a = r2_JsPromptResult;
    }

    public final void onClick(DialogInterface r2_DialogInterface, int r3i) {
        this.a.cancel();
    }
}