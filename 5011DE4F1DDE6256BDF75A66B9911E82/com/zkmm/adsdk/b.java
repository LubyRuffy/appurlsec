package com.zkmm.adsdk;

import android.media.MediaPlayer;
import android.net.Uri;
import java.io.IOException;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: SourceFile
final class b extends Thread {
    private /* synthetic */ a a;
    private final /* synthetic */ String b;

    b(a r1_a, String r2_String) {
        this.a = r1_a;
        this.b = r2_String;
    }

    public final void run() {
        try {
            MediaPlayer r0_MediaPlayer = new MediaPlayer();
            r0_MediaPlayer.setAudioStreamType(XListViewFooter.STATE_NOMORE);
            r0_MediaPlayer.setOnCompletionListener(new c(this, this.b));
            r0_MediaPlayer.setDataSource(this.a.a.getContext(), Uri.parse(this.b));
            r0_MediaPlayer.prepare();
            r0_MediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e_2) {
            e_2.printStackTrace();
        }
    }
}