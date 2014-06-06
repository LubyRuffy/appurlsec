package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.openapi.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.platformtools.Log;
import java.io.File;
import qsbk.app.share.ShareUtils;

public class WXAppExtendObject implements IMediaObject {
    public String extInfo;
    public byte[] fileData;
    public String filePath;

    public WXAppExtendObject(String r1_String, String r2_String) {
        this.extInfo = r1_String;
        this.filePath = r2_String;
    }

    public WXAppExtendObject(String r1_String, byte[] r2_byteA) {
        this.extInfo = r1_String;
        this.fileData = r2_byteA;
    }

    public boolean checkArgs() {
        String r1_String;
        int r1i;
        File r2_File;
        if (this.extInfo == null || this.extInfo.length() == 0) {
            if (this.filePath == null || this.filePath.length() == 0) {
                if (this.fileData == null || this.fileData.length == 0) {
                    Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, all arguments is null");
                    return false;
                } else if (this.extInfo == null || this.extInfo.length() <= 2048) {
                    if (this.filePath == null || this.filePath.length() <= 10240) {
                        if (this.filePath != null) {
                            r1_String = this.filePath;
                            if (r1_String == null || r1_String.length() == 0) {
                                r1i = 0;
                                if (r1i > 10485760) {
                                    Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileSize is too large");
                                }
                            } else {
                                r2_File = new File(r1_String);
                                if (r2_File.exists()) {
                                    r1i = (int) r2_File.length();
                                    if (r1i > 10485760) {
                                        Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileSize is too large");
                                    }
                                } else {
                                    r1i = 0;
                                    if (r1i > 10485760) {
                                    }
                                }
                            }
                            Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileSize is too large");
                            return false;
                        }
                        if (this.fileData == null || this.fileData.length <= 10485760) {
                            return true;
                        }
                        Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileData is too large");
                        return false;
                    } else {
                        Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, filePath is invalid");
                        return false;
                    }
                } else {
                    Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, extInfo is invalid");
                    return false;
                }
            } else if (this.extInfo == null || this.extInfo.length() <= 2048) {
                if (this.filePath == null || this.filePath.length() <= 10240) {
                    if (this.filePath != null) {
                        if (this.fileData == null || this.fileData.length <= 10485760) {
                            return true;
                        }
                        Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileData is too large");
                        return false;
                    } else {
                        r1_String = this.filePath;
                        if (r1_String == null || r1_String.length() == 0) {
                            r1i = 0;
                            if (r1i > 10485760) {
                                if (this.fileData == null || this.fileData.length <= 10485760) {
                                    return true;
                                }
                                Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileData is too large");
                                return false;
                            }
                        } else {
                            r2_File = new File(r1_String);
                            if (r2_File.exists()) {
                                r1i = (int) r2_File.length();
                                if (r1i > 10485760) {
                                    Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileSize is too large");
                                }
                                if (this.fileData == null || this.fileData.length <= 10485760) {
                                    return true;
                                }
                                Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileData is too large");
                                return false;
                            } else {
                                r1i = 0;
                                if (r1i > 10485760) {
                                    if (this.fileData == null || this.fileData.length <= 10485760) {
                                        return true;
                                    }
                                    Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileData is too large");
                                    return false;
                                }
                            }
                        }
                        Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileSize is too large");
                        return false;
                    }
                } else {
                    Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, filePath is invalid");
                    return false;
                }
            } else {
                Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, extInfo is invalid");
                return false;
            }
        } else if (this.extInfo == null || this.extInfo.length() <= 2048) {
            if (this.filePath == null || this.filePath.length() <= 10240) {
                if (this.filePath != null) {
                    r1_String = this.filePath;
                    if (r1_String == null || r1_String.length() == 0) {
                        r1i = 0;
                        if (r1i > 10485760) {
                            Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileSize is too large");
                        }
                    } else {
                        r2_File = new File(r1_String);
                        if (r2_File.exists()) {
                            r1i = 0;
                            if (r1i > 10485760) {
                            }
                        } else {
                            r1i = (int) r2_File.length();
                            if (r1i > 10485760) {
                                Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileSize is too large");
                            }
                        }
                    }
                    Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileSize is too large");
                    return false;
                }
                if (this.fileData == null || this.fileData.length <= 10485760) {
                    return true;
                }
                Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileData is too large");
                return false;
            } else {
                Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, filePath is invalid");
                return false;
            }
        } else {
            Log.e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, extInfo is invalid");
            return false;
        }
    }

    public void serialize(Bundle r3_Bundle) {
        r3_Bundle.putString("_wxappextendobject_extInfo", this.extInfo);
        r3_Bundle.putByteArray("_wxappextendobject_fileData", this.fileData);
        r3_Bundle.putString("_wxappextendobject_filePath", this.filePath);
    }

    public int type() {
        return ShareUtils.SHARE_COLLECT;
    }

    public void unserialize(Bundle r2_Bundle) {
        this.extInfo = r2_Bundle.getString("_wxappextendobject_extInfo");
        this.fileData = r2_Bundle.getByteArray("_wxappextendobject_fileData");
        this.filePath = r2_Bundle.getString("_wxappextendobject_filePath");
    }
}