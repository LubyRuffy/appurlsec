package qsbk.app.activity;

import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;

// compiled from: ImageViewer.java
class ar implements MediaScannerConnectionClient {
    final /* synthetic */ String a;
    final /* synthetic */ aq b;

    ar(aq r1_aq, String r2_String) {
        this.b = r1_aq;
        this.a = r2_String;
    }

    public void onMediaScannerConnected() {
        synchronized (this.b.a.u) {
            if (this.b.a.u.isConnected()) {
                this.b.a.u.scanFile(this.a, "image/jpeg");
            }
        }
    }

    public void onScanCompleted(String r2_String, Uri r3_Uri) {
        this.b.a.u.disconnect();
    }
}