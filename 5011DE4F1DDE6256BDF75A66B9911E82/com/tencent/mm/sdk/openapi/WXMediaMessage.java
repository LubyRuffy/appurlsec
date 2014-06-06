package com.tencent.mm.sdk.openapi;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import com.tencent.mm.sdk.platformtools.Log;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public final class WXMediaMessage {
    public static final String ACTION_WXAPPMESSAGE = "com.tencent.mm.sdk.openapi.Intent.ACTION_WXAPPMESSAGE";
    public String description;
    public IMediaObject mediaObject;
    public int sdkVer;
    public byte[] thumbData;
    public String title;

    public static interface IMediaObject {
        public static final int TYPE_APPDATA = 7;
        public static final int TYPE_EMOJI = 8;
        public static final int TYPE_FILE = 6;
        public static final int TYPE_IMAGE = 2;
        public static final int TYPE_MUSIC = 3;
        public static final int TYPE_TEXT = 1;
        public static final int TYPE_UNKNOWN = 0;
        public static final int TYPE_URL = 5;
        public static final int TYPE_VIDEO = 4;

        public boolean checkArgs();

        public void serialize(Bundle r1_Bundle);

        public int type();

        public void unserialize(Bundle r1_Bundle);
    }

    public static class Builder {
        public static final String KEY_IDENTIFIER = "_wxobject_identifier_";

        public static WXMediaMessage fromBundle(Bundle r5_Bundle) {
            WXMediaMessage r1_WXMediaMessage = new WXMediaMessage();
            r1_WXMediaMessage.sdkVer = r5_Bundle.getInt("_wxobject_sdkVer");
            r1_WXMediaMessage.title = r5_Bundle.getString("_wxobject_title");
            r1_WXMediaMessage.description = r5_Bundle.getString("_wxobject_description");
            r1_WXMediaMessage.thumbData = r5_Bundle.getByteArray("_wxobject_thumbdata");
            String r2_String = r5_Bundle.getString(KEY_IDENTIFIER);
            if (r2_String == null || r2_String.length() <= 0) {
                return r1_WXMediaMessage;
            }
            try {
                r1_WXMediaMessage.mediaObject = (com.tencent.mm.sdk.openapi.WXMediaMessage.IMediaObject) Class.forName(r2_String).newInstance();
                r1_WXMediaMessage.mediaObject.unserialize(r5_Bundle);
                return r1_WXMediaMessage;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("MicroMsg.SDK.WXMediaMessage", new StringBuilder("get media object from bundle failed: unknown ident ").append(r2_String).toString());
                return r1_WXMediaMessage;
            }
        }

        public static Bundle toBundle(WXMediaMessage r3_WXMediaMessage) {
            Bundle r0_Bundle = new Bundle();
            r0_Bundle.putInt("_wxobject_sdkVer", r3_WXMediaMessage.sdkVer);
            r0_Bundle.putString("_wxobject_title", r3_WXMediaMessage.title);
            r0_Bundle.putString("_wxobject_description", r3_WXMediaMessage.description);
            r0_Bundle.putByteArray("_wxobject_thumbdata", r3_WXMediaMessage.thumbData);
            if (r3_WXMediaMessage.mediaObject != null) {
                r0_Bundle.putString(KEY_IDENTIFIER, r3_WXMediaMessage.mediaObject.getClass().getName());
                r3_WXMediaMessage.mediaObject.serialize(r0_Bundle);
            }
            return r0_Bundle;
        }
    }

    public WXMediaMessage() {
        this(null);
    }

    public WXMediaMessage(IMediaObject r1_IMediaObject) {
        this.mediaObject = r1_IMediaObject;
    }

    final boolean checkArgs() {
        if (getType() == 8) {
            if (this.thumbData == null || this.thumbData.length == 0) {
                Log.e("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, thumbData should not be null when send emoji");
                return false;
            }
        }
        if (this.thumbData == null || this.thumbData.length <= 32768) {
            if (this.title == null || this.title.length() <= 512) {
                if (this.description == null || this.description.length() <= 1024) {
                    if (this.mediaObject != null) {
                        return this.mediaObject.checkArgs();
                    }
                    Log.e("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, mediaObject is null");
                    return false;
                } else {
                    Log.e("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, description is invalid");
                    return false;
                }
            } else {
                Log.e("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, title is invalid");
                return false;
            }
        } else {
            Log.e("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, thumbData is invalid");
            return false;
        }
    }

    public final int getType() {
        return this.mediaObject == null ? 0 : this.mediaObject.type();
    }

    public final void setThumbImage(Bitmap r4_Bitmap) {
        try {
            OutputStream r0_OutputStream = new ByteArrayOutputStream();
            r4_Bitmap.compress(CompressFormat.JPEG, 85, r0_OutputStream);
            this.thumbData = r0_OutputStream.toByteArray();
            r0_OutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MicroMsg.SDK.WXMediaMessage", "put thumb failed");
        }
    }
}