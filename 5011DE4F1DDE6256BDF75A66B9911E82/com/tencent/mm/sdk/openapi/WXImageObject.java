package com.tencent.mm.sdk.openapi;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import com.tencent.mm.sdk.openapi.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.platformtools.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import qsbk.app.widget.listview.XListViewHeader;

public class WXImageObject implements IMediaObject {
    public byte[] imageData;
    public String imagePath;
    public String imageUrl;

    public WXImageObject(Bitmap r4_Bitmap) {
        try {
            OutputStream r0_OutputStream = new ByteArrayOutputStream();
            r4_Bitmap.compress(CompressFormat.JPEG, 85, r0_OutputStream);
            this.imageData = r0_OutputStream.toByteArray();
            r0_OutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WXImageObject(byte[] r1_byteA) {
        this.imageData = r1_byteA;
    }

    public boolean checkArgs() {
        String r1_String;
        int r1i;
        File r2_File;
        if (this.imageData == null || this.imageData.length == 0) {
            if (this.imagePath == null || this.imagePath.length() == 0) {
                if (this.imageUrl == null || this.imageUrl.length() == 0) {
                    Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, all arguments are null");
                    return false;
                } else if (this.imageData == null || this.imageData.length <= 10485760) {
                    if (this.imagePath == null || this.imagePath.length() <= 10240) {
                        if (this.imagePath != null) {
                            r1_String = this.imagePath;
                            if (r1_String == null || r1_String.length() == 0) {
                                r1i = 0;
                                if (r1i > 10485760) {
                                    Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, image content is too large");
                                }
                            } else {
                                r2_File = new File(r1_String);
                                if (r2_File.exists()) {
                                    r1i = (int) r2_File.length();
                                    if (r1i > 10485760) {
                                        Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, image content is too large");
                                    }
                                } else {
                                    r1i = 0;
                                    if (r1i > 10485760) {
                                    }
                                }
                            }
                            Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, image content is too large");
                            return false;
                        }
                        if (this.imageUrl == null || this.imageUrl.length() <= 10240) {
                            return true;
                        }
                        Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, url is invalid");
                        return false;
                    } else {
                        Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, path is invalid");
                        return false;
                    }
                } else {
                    Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, content is too large");
                    return false;
                }
            } else if (this.imageData == null || this.imageData.length <= 10485760) {
                if (this.imagePath == null || this.imagePath.length() <= 10240) {
                    if (this.imagePath != null) {
                        if (this.imageUrl == null || this.imageUrl.length() <= 10240) {
                            return true;
                        }
                        Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, url is invalid");
                        return false;
                    } else {
                        r1_String = this.imagePath;
                        if (r1_String == null || r1_String.length() == 0) {
                            r1i = 0;
                            if (r1i > 10485760) {
                                if (this.imageUrl == null || this.imageUrl.length() <= 10240) {
                                    return true;
                                }
                                Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, url is invalid");
                                return false;
                            }
                        } else {
                            r2_File = new File(r1_String);
                            if (r2_File.exists()) {
                                r1i = (int) r2_File.length();
                                if (r1i > 10485760) {
                                    Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, image content is too large");
                                }
                                if (this.imageUrl == null || this.imageUrl.length() <= 10240) {
                                    return true;
                                }
                                Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, url is invalid");
                                return false;
                            } else {
                                r1i = 0;
                                if (r1i > 10485760) {
                                    if (this.imageUrl == null || this.imageUrl.length() <= 10240) {
                                        return true;
                                    }
                                    Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, url is invalid");
                                    return false;
                                }
                            }
                        }
                        Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, image content is too large");
                        return false;
                    }
                } else {
                    Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, path is invalid");
                    return false;
                }
            } else {
                Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, content is too large");
                return false;
            }
        } else if (this.imageData == null || this.imageData.length <= 10485760) {
            if (this.imagePath == null || this.imagePath.length() <= 10240) {
                if (this.imagePath != null) {
                    r1_String = this.imagePath;
                    if (r1_String == null || r1_String.length() == 0) {
                        r1i = 0;
                        if (r1i > 10485760) {
                            Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, image content is too large");
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
                                Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, image content is too large");
                            }
                        }
                    }
                    Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, image content is too large");
                    return false;
                }
                if (this.imageUrl == null || this.imageUrl.length() <= 10240) {
                    return true;
                }
                Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, url is invalid");
                return false;
            } else {
                Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, path is invalid");
                return false;
            }
        } else {
            Log.e("MicroMsg.SDK.WXImageObject", "checkArgs fail, content is too large");
            return false;
        }
    }

    public void serialize(Bundle r3_Bundle) {
        r3_Bundle.putByteArray("_wximageobject_imageData", this.imageData);
        r3_Bundle.putString("_wximageobject_imagePath", this.imagePath);
        r3_Bundle.putString("_wximageobject_imageUrl", this.imageUrl);
    }

    public void setImagePath(String r1_String) {
        this.imagePath = r1_String;
    }

    public int type() {
        return XListViewHeader.STATE_REFRESHING;
    }

    public void unserialize(Bundle r2_Bundle) {
        this.imageData = r2_Bundle.getByteArray("_wximageobject_imageData");
        this.imagePath = r2_Bundle.getString("_wximageobject_imagePath");
        this.imageUrl = r2_Bundle.getString("_wximageobject_imageUrl");
    }
}