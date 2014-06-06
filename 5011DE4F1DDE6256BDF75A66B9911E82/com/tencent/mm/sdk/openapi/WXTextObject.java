package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.openapi.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.platformtools.Log;

public class WXTextObject implements IMediaObject {
    public String text;

    public WXTextObject() {
        this(null);
    }

    public WXTextObject(String r1_String) {
        this.text = r1_String;
    }

    public boolean checkArgs() {
        if (this.text != null && this.text.length() != 0 && this.text.length() <= 10240) {
            return true;
        }
        Log.e("MicroMsg.SDK.WXTextObject", "checkArgs fail, text is invalid");
        return false;
    }

    public void serialize(Bundle r3_Bundle) {
        r3_Bundle.putString("_wxtextobject_text", this.text);
    }

    public int type() {
        return 1;
    }

    public void unserialize(Bundle r2_Bundle) {
        this.text = r2_Bundle.getString("_wxtextobject_text");
    }
}