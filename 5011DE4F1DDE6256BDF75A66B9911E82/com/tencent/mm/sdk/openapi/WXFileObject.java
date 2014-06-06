package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.openapi.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.platformtools.Log;
import java.io.File;
import qsbk.app.share.ShareUtils;

public class WXFileObject implements IMediaObject {
    public byte[] fileData;
    public String filePath;

    public WXFileObject() {
        this.fileData = null;
        this.filePath = null;
    }

    public WXFileObject(String r1_String) {
        this.filePath = r1_String;
    }

    public WXFileObject(byte[] r1_byteA) {
        this.fileData = r1_byteA;
    }

    public boolean checkArgs() {
        String r1_String;
        int r1i;
        File r2_File;
        if (this.fileData == null || this.fileData.length == 0) {
            if (this.filePath == null || this.filePath.length() == 0) {
                Log.e("MicroMsg.SDK.WXFileObject", "checkArgs fail, both arguments is null");
                return false;
            } else if (this.fileData == null || this.fileData.length <= 10485760) {
                if (this.filePath == null) {
                    r1_String = this.filePath;
                    if (r1_String == null || r1_String.length() == 0) {
                        r1i = 0;
                        if (r1i <= 10485760) {
                            Log.e("MicroMsg.SDK.WXFileObject", "checkArgs fail, fileSize is too large");
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
                                Log.e("MicroMsg.SDK.WXFileObject", "checkArgs fail, fileSize is too large");
                            }
                        }
                    }
                    Log.e("MicroMsg.SDK.WXFileObject", "checkArgs fail, fileSize is too large");
                    return false;
                }
                return true;
            } else {
                Log.e("MicroMsg.SDK.WXFileObject", "checkArgs fail, fileData is too large");
                return false;
            }
        } else if (this.fileData == null || this.fileData.length <= 10485760) {
            if (this.filePath == null) {
                return true;
            }
            r1_String = this.filePath;
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
                        Log.e("MicroMsg.SDK.WXFileObject", "checkArgs fail, fileSize is too large");
                    }
                    return true;
                } else {
                    r1i = 0;
                    if (r1i <= 10485760) {
                        return true;
                    }
                }
            }
            Log.e("MicroMsg.SDK.WXFileObject", "checkArgs fail, fileSize is too large");
            return false;
        } else {
            Log.e("MicroMsg.SDK.WXFileObject", "checkArgs fail, fileData is too large");
            return false;
        }
    }

    public void serialize(Bundle r3_Bundle) {
        r3_Bundle.putByteArray("_wxfileobject_fileData", this.fileData);
        r3_Bundle.putString("_wxfileobject_filePath", this.filePath);
    }

    public void setFileData(byte[] r1_byteA) {
        this.fileData = r1_byteA;
    }

    public void setFilePath(String r1_String) {
        this.filePath = r1_String;
    }

    public int type() {
        return ShareUtils.SHARE_COPY;
    }

    public void unserialize(Bundle r2_Bundle) {
        this.fileData = r2_Bundle.getByteArray("_wxfileobject_fileData");
        this.filePath = r2_Bundle.getString("_wxfileobject_filePath");
    }
}