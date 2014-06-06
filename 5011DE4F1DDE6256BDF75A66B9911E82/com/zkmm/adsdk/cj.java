package com.zkmm.adsdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsPromptResult;

// compiled from: SourceFile
final class cj implements OnClickListener {
    private final /* synthetic */ JsPromptResult a;

    cj(k r1_k, JsPromptResult r2_JsPromptResult) {
        this.a = r2_JsPromptResult;
    }

    public final void onClick(DialogInterface r2_DialogInterface, int r3i) {
        this.a.cancel();
    }
}