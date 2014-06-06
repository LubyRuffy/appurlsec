package com.zkmm.adsdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsPromptResult;
import android.widget.EditText;

// compiled from: SourceFile
final class ci implements OnClickListener {
    private final /* synthetic */ JsPromptResult a;
    private final /* synthetic */ EditText b;

    ci(k r1_k, JsPromptResult r2_JsPromptResult, EditText r3_EditText) {
        this.a = r2_JsPromptResult;
        this.b = r3_EditText;
    }

    public final void onClick(DialogInterface r3_DialogInterface, int r4i) {
        this.a.confirm(this.b.getText().toString());
    }
}