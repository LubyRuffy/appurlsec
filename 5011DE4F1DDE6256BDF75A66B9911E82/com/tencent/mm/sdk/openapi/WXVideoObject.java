package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.openapi.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.platformtools.Log;
import qsbk.app.widget.listview.XListViewFooter;

public class WXVideoObject implements IMediaObject {
    public String videoLowBandUrl;
    public String videoUrl;

    public boolean checkArgs() {
        if (this.videoUrl == null || this.videoUrl.length() == 0) {
            if (this.videoLowBandUrl == null || this.videoLowBandUrl.length() == 0) {
                Log.e("MicroMsg.SDK.WXVideoObject", "both arguments are null");
                return false;
            } else if (this.videoUrl == null || this.videoUrl.length() <= 10240) {
                if (this.videoLowBandUrl == null || this.videoLowBandUrl.length() <= 10240) {
                    return true;
                }
                Log.e("MicroMsg.SDK.WXVideoObject", "checkArgs fail, videoLowBandUrl is too long");
                return false;
            } else {
                Log.e("MicroMsg.SDK.WXVideoObject", "checkArgs fail, videoUrl is too long");
                return false;
            }
        } else if (this.videoUrl == null || this.videoUrl.length() <= 10240) {
            if (this.videoLowBandUrl == null || this.videoLowBandUrl.length() <= 10240) {
                return true;
            }
            Log.e("MicroMsg.SDK.WXVideoObject", "checkArgs fail, videoLowBandUrl is too long");
            return false;
        } else {
            Log.e("MicroMsg.SDK.WXVideoObject", "checkArgs fail, videoUrl is too long");
            return false;
        }
    }

    public void serialize(Bundle r3_Bundle) {
        r3_Bundle.putString("_wxvideoobject_videoUrl", this.videoUrl);
        r3_Bundle.putString("_wxvideoobject_videoLowBandUrl", this.videoLowBandUrl);
    }

    public int type() {
        return XListViewFooter.STATE_NODATA;
    }

    public void unserialize(Bundle r2_Bundle) {
        this.videoUrl = r2_Bundle.getString("_wxvideoobject_videoUrl");
        this.videoLowBandUrl = r2_Bundle.getString("_wxvideoobject_videoLowBandUrl");
    }
}