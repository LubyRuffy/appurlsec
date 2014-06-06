package com.zkmm.adsdk;

import android.media.MediaPlayer;
import android.net.Uri;
import java.io.IOException;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: SourceFile
final class cl implements Runnable {
    private final /* synthetic */ String a;

    cl(q r1_q, String r2_String) {
        this.a = r2_String;
    }

    public final void run() {
        try {
            MediaPlayer r0_MediaPlayer = new MediaPlayer();
            r0_MediaPlayer.setAudioStreamType(XListViewFooter.STATE_NOMORE);
            r0_MediaPlayer.setOnCompletionListener(new cm(this));
            r0_MediaPlayer.setDataSource(AdDisplayer.m, Uri.parse(this.a));
            r0_MediaPlayer.prepare();
            r0_MediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e_2) {
            e_2.printStackTrace();
        }
    }
}