package com.zkmm.adsdk;

import android.media.MediaPlayer;
import android.net.Uri;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: SourceFile
final class ak implements Runnable {
    private final /* synthetic */ String a;

    ak(aD r1_aD, String r2_String) {
        this.a = r2_String;
    }

    public final void run() {
        try {
            MediaPlayer r0_MediaPlayer = new MediaPlayer();
            r0_MediaPlayer.setAudioStreamType(XListViewFooter.STATE_NOMORE);
            r0_MediaPlayer.setOnCompletionListener(new al(this));
            r0_MediaPlayer.setDataSource(bj.j, Uri.parse(this.a));
            r0_MediaPlayer.prepare();
            r0_MediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}