package com.zkmm.adsdk;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.webkit.WebView;

// compiled from: SourceFile
final class an implements OnCompletionListener {
    private final /* synthetic */ Dialog a;
    private final /* synthetic */ WebView b;

    an(aD r1_aD, Dialog r2_Dialog, WebView r3_WebView) {
        this.a = r2_Dialog;
        this.b = r3_WebView;
    }

    public final void onCompletion(MediaPlayer r3_MediaPlayer) {
        if (this.a != null) {
            this.a.dismiss();
        }
        try {
            this.b.loadUrl("javascript:adwoDoPlayVideoComplete();");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}