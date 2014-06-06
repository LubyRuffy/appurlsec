package com.zkmm.adsdk;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Message;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
final class c implements OnCompletionListener {
    private /* synthetic */ b a;
    private final /* synthetic */ String b;

    c(b r1_b, String r2_String) {
        this.a = r1_b;
        this.b = r2_String;
    }

    public final void onCompletion(MediaPlayer r4_MediaPlayer) {
        if (r4_MediaPlayer != null) {
            r4_MediaPlayer.stop();
            r4_MediaPlayer.release();
        }
        if (cs.b(this.a.a.a) < cs.c(this.a.a.a)) {
            Message r0_Message = cs.d(this.a.a.a).obtainMessage(XListViewHeader.STATE_REFRESHING);
            r0_Message.obj = this.b;
            cs.d(this.a.a.a).dispatchMessage(r0_Message);
        } else {
            cs.a(this.a.a.a, 1);
            cs.b(this.a.a.a, 0);
            cs.a = false;
        }
    }
}