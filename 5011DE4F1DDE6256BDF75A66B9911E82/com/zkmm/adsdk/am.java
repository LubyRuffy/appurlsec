package com.zkmm.adsdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.widget.VideoView;

// compiled from: SourceFile
final class am implements OnDismissListener {
    private final /* synthetic */ VideoView a;

    am(aD r1_aD, VideoView r2_VideoView) {
        this.a = r2_VideoView;
    }

    public final void onDismiss(DialogInterface r2_DialogInterface) {
        if (this.a != null) {
            this.a.stopPlayback();
        }
    }
}