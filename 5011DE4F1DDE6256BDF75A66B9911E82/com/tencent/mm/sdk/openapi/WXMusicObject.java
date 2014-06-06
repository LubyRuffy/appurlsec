package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.openapi.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.platformtools.Log;
import qsbk.app.widget.listview.XListViewFooter;

public class WXMusicObject implements IMediaObject {
    public String musicLowBandUrl;
    public String musicUrl;

    public boolean checkArgs() {
        if (this.musicUrl == null || this.musicUrl.length() == 0) {
            if (this.musicLowBandUrl == null || this.musicLowBandUrl.length() == 0) {
                Log.e("MicroMsg.SDK.WXMusicObject", "both arguments are null");
                return false;
            } else if (this.musicUrl == null || this.musicUrl.length() <= 10240) {
                if (this.musicLowBandUrl == null || this.musicLowBandUrl.length() <= 10240) {
                    return true;
                }
                Log.e("MicroMsg.SDK.WXMusicObject", "checkArgs fail, musicLowBandUrl is too long");
                return false;
            } else {
                Log.e("MicroMsg.SDK.WXMusicObject", "checkArgs fail, musicUrl is too long");
                return false;
            }
        } else if (this.musicUrl == null || this.musicUrl.length() <= 10240) {
            if (this.musicLowBandUrl == null || this.musicLowBandUrl.length() <= 10240) {
                return true;
            }
            Log.e("MicroMsg.SDK.WXMusicObject", "checkArgs fail, musicLowBandUrl is too long");
            return false;
        } else {
            Log.e("MicroMsg.SDK.WXMusicObject", "checkArgs fail, musicUrl is too long");
            return false;
        }
    }

    public void serialize(Bundle r3_Bundle) {
        r3_Bundle.putString("_wxmusicobject_musicUrl", this.musicUrl);
        r3_Bundle.putString("_wxmusicobject_musicLowBandUrl", this.musicLowBandUrl);
    }

    public int type() {
        return XListViewFooter.STATE_NOMORE;
    }

    public void unserialize(Bundle r2_Bundle) {
        this.musicUrl = r2_Bundle.getString("_wxmusicobject_musicUrl");
        this.musicLowBandUrl = r2_Bundle.getString("_wxmusicobject_musicLowBandUrl");
    }
}