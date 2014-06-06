package qsbk.app.activity;

import android.media.MediaScannerConnection;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.QsbkApp;

// compiled from: ImageViewer.java
class aq extends Handler {
    final /* synthetic */ ImageViewer a;

    aq(ImageViewer r1_ImageViewer) {
        this.a = r1_ImageViewer;
    }

    public void handleMessage(Message r8_Message) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        if (r8_Message.what == 1) {
            String r1_String = r8_Message.obj.toString();
            r0_String = "\u56fe\u7247\u5df2\u4fdd\u5b58\n(" + r1_String + ")";
            this.a.r = r0_String;
            this.a.u = new MediaScannerConnection(this.a, new ar(this, r1_String));
            this.a.u.connect();
        } else {
            r0_String = (String) r8_Message.obj;
        }
        this.a.q = true;
        Toast.makeText(QsbkApp.mContext, r0_String, 1).show();
    }
}