package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.openapi.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.platformtools.Log;
import java.io.File;
import qsbk.app.utils.Base64;

public class WXEmojiObject implements IMediaObject {
    public byte[] emojiData;
    public String emojiPath;

    public WXEmojiObject() {
        this.emojiData = null;
        this.emojiPath = null;
    }

    public WXEmojiObject(String r1_String) {
        this.emojiPath = r1_String;
    }

    public WXEmojiObject(byte[] r1_byteA) {
        this.emojiData = r1_byteA;
    }

    public boolean checkArgs() {
        String r1_String;
        int r1i;
        File r2_File;
        if (this.emojiData == null || this.emojiData.length == 0) {
            if (this.emojiPath == null || this.emojiPath.length() == 0) {
                Log.e("MicroMsg.SDK.WXEmojiObject", "checkArgs fail, both arguments is null");
                return false;
            } else if (this.emojiData == null || this.emojiData.length <= 10485760) {
                if (this.emojiPath == null) {
                    r1_String = this.emojiPath;
                    if (r1_String == null || r1_String.length() == 0) {
                        r1i = 0;
                        if (r1i <= 10485760) {
                            Log.e("MicroMsg.SDK.WXEmojiObject", "checkArgs fail, emojiSize is too large");
                        }
                    } else {
                        r2_File = new File(r1_String);
                        if (r2_File.exists()) {
                            r1i = 0;
                            if (r1i <= 10485760) {
                            }
                        } else {
                            r1i = (int) r2_File.length();
                            if (r1i <= 10485760) {
                                Log.e("MicroMsg.SDK.WXEmojiObject", "checkArgs fail, emojiSize is too large");
                            }
                        }
                    }
                    Log.e("MicroMsg.SDK.WXEmojiObject", "checkArgs fail, emojiSize is too large");
                    return false;
                }
                return true;
            } else {
                Log.e("MicroMsg.SDK.WXEmojiObject", "checkArgs fail, emojiData is too large");
                return false;
            }
        } else if (this.emojiData == null || this.emojiData.length <= 10485760) {
            if (this.emojiPath == null) {
                return true;
            }
            r1_String = this.emojiPath;
            if (r1_String == null || r1_String.length() == 0) {
                r1i = 0;
                if (r1i <= 10485760) {
                    return true;
                }
            } else {
                r2_File = new File(r1_String);
                if (r2_File.exists()) {
                    r1i = (int) r2_File.length();
                    if (r1i <= 10485760) {
                        Log.e("MicroMsg.SDK.WXEmojiObject", "checkArgs fail, emojiSize is too large");
                    }
                    return true;
                } else {
                    r1i = 0;
                    if (r1i <= 10485760) {
                        return true;
                    }
                }
            }
            Log.e("MicroMsg.SDK.WXEmojiObject", "checkArgs fail, emojiSize is too large");
            return false;
        } else {
            Log.e("MicroMsg.SDK.WXEmojiObject", "checkArgs fail, emojiData is too large");
            return false;
        }
    }

    public void serialize(Bundle r3_Bundle) {
        r3_Bundle.putByteArray("_wxemojiobject_emojiData", this.emojiData);
        r3_Bundle.putString("_wxemojiobject_emojiPath", this.emojiPath);
    }

    public void setEmojiData(byte[] r1_byteA) {
        this.emojiData = r1_byteA;
    }

    public void setEmojiPath(String r1_String) {
        this.emojiPath = r1_String;
    }

    public int type() {
        return Base64.DONT_BREAK_LINES;
    }

    public void unserialize(Bundle r2_Bundle) {
        this.emojiData = r2_Bundle.getByteArray("_wxemojiobject_emojiData");
        this.emojiPath = r2_Bundle.getString("_wxemojiobject_emojiPath");
    }
}