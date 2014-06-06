package qsbk.app.activity;

import android.os.Handler;
import android.os.Message;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import qsbk.app.Constants;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: ImageViewer.java
class ap extends Handler {
    final /* synthetic */ ImageViewer a;

    ap(ImageViewer r1_ImageViewer) {
        this.a = r1_ImageViewer;
    }

    public void handleMessage(Message r5_Message) {
        switch (r5_Message.what) {
            case XListViewHeader.STATE_NORMAL:
                this.a.y.setMax(this.a.s);
                break;
            case XListViewHeader.STATE_READY:
                this.a.y.setProgress(this.a.t);
                break;
            case XListViewHeader.STATE_REFRESHING:
                this.a.y.setVisibility(XListViewFooter.STATE_NODATA);
                String r0_String = this.a.b(Constants.IMG_CACHE_PATH_MEDIUM);
                if (r0_String.endsWith(Util.PHOTO_DEFAULT_EXT) || r0_String.endsWith(".png")) {
                    this.a.o = this.a.c(r0_String);
                    if (this.a.o != null) {
                        this.a.x.setImageBitmap(this.a.o);
                        this.a.x.setVisibility(0);
                        this.a.w.setVisibility(Base64.DONT_BREAK_LINES);
                        this.a.p = true;
                        this.a.t = 0;
                        this.a.s = 0;
                    } else {
                        new File(r0_String).delete();
                        this.a.t = 0;
                        this.a.s = 0;
                    }
                } else {
                    this.a.t = 0;
                    this.a.s = 0;
                }
                break;
            case XListViewFooter.STATE_NOMORE:
                this.a.i();
                break;
        }
        super.handleMessage(r5_Message);
    }
}