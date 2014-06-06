package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.openapi.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.platformtools.Log;
import qsbk.app.share.ShareUtils;

public class WXWebpageObject implements IMediaObject {
    public String webpageUrl;

    public WXWebpageObject(String r1_String) {
        this.webpageUrl = r1_String;
    }

    public boolean checkArgs() {
        if (this.webpageUrl != null && this.webpageUrl.length() != 0 && this.webpageUrl.length() <= 10240) {
            return true;
        }
        Log.e("MicroMsg.SDK.WXWebpageObject", "checkArgs fail, webpageUrl is invalid");
        return false;
    }

    public void serialize(Bundle r3_Bundle) {
        r3_Bundle.putString("_wxwebpageobject_webpageUrl", this.webpageUrl);
    }

    public int type() {
        return ShareUtils.SHARE_SMS;
    }

    public void unserialize(Bundle r2_Bundle) {
        this.webpageUrl = r2_Bundle.getString("_wxwebpageobject_webpageUrl");
    }
}