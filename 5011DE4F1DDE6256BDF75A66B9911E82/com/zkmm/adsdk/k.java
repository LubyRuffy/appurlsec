package com.zkmm.adsdk;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;

// compiled from: SourceFile
public final class k extends WebChromeClient {
    protected k(AdDisplayer r1_AdDisplayer) {
    }

    public final boolean onJsAlert(WebView r5_WebView, String r6_String, String r7_String, JsResult r8_JsResult) {
        Builder r0_Builder = new Builder(AdDisplayer.m);
        r0_Builder.setMessage(r7_String).setPositiveButton("\u786e\u5b9a", null);
        r0_Builder.setCancelable(false);
        r0_Builder.create().show();
        r8_JsResult.confirm();
        return true;
    }

    public final boolean onJsConfirm(WebView r5_WebView, String r6_String, String r7_String, JsResult r8_JsResult) {
        Builder r0_Builder = new Builder(AdDisplayer.m);
        r0_Builder.setMessage(r7_String).setPositiveButton("\u786e\u5b9a", new cf(this, r8_JsResult)).setNeutralButton("\u53d6\u6d88", new cg(this, r8_JsResult));
        r0_Builder.setOnCancelListener(new ch(this, r8_JsResult));
        r0_Builder.create().show();
        return true;
    }

    public final boolean onJsPrompt(WebView r6_WebView, String r7_String, String r8_String, String r9_String, JsPromptResult r10_JsPromptResult) {
        Builder r0_Builder = new Builder(AdDisplayer.m);
        r0_Builder.setMessage(r8_String);
        View r1_View = new EditText(r6_WebView.getContext());
        r1_View.setSingleLine();
        r1_View.setText(r9_String);
        r0_Builder.setView(r1_View).setPositiveButton("\u786e\u5b9a", new ci(this, r10_JsPromptResult, r1_View)).setNeutralButton("\u53d6\u6d88", new cj(this, r10_JsPromptResult));
        r0_Builder.create().show();
        return true;
    }
}