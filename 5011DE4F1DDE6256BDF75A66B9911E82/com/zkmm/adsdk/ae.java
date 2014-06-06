package com.zkmm.adsdk;

import android.media.MediaRecorder;
import android.webkit.WebView;
import java.io.IOException;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public final class ae {
    String a;
    String b;
    WebView c;
    int d;
    private MediaRecorder e;
    private double f;

    public ae(double r3d, String r5_String, String r6_String, WebView r7_WebView, int r8i) {
        this.f = 0.0d;
        this.a = null;
        this.b = "/dev/null";
        this.f = r3d;
        this.a = r5_String;
        this.c = r7_WebView;
        this.d = r8i;
        if (r6_String != null) {
            this.b = r6_String;
        }
    }

    public final void a() {
        if (this.e == null) {
            this.e = new MediaRecorder();
        }
        try {
            this.e.setAudioSource(1);
            this.e.setOutputFormat(XListViewHeader.STATE_REFRESHING);
            this.e.setAudioEncoder(0);
            this.e.setOnErrorListener(new bc(this));
            this.e.setOnInfoListener(new bd(this));
            this.e.setOutputFile(this.b);
            this.e.setMaxDuration(((int) this.f) * 1000);
            this.e.prepare();
            this.e.getMaxAmplitude();
            this.e.start();
            if (this.c != null) {
                this.c.loadUrl("javascript:adwoVoiceStartRecording();");
            }
            this.e.getMaxAmplitude();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e_2) {
            e_2.printStackTrace();
        }
    }

    public final void b() {
        if (this.e != null) {
            this.e.stop();
            this.e.reset();
            this.e.release();
            this.e = null;
        }
    }

    public final int c() {
        return this.e != null ? this.e.getMaxAmplitude() : 0;
    }
}