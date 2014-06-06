package com.tencent.open;

import android.graphics.Bitmap;
import android.os.Message;
import android.util.Log;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;

// compiled from: ProGuard
class u implements Runnable {
    final /* synthetic */ AsynLoadImg a;

    u(AsynLoadImg r1_AsynLoadImg) {
        this.a = r1_AsynLoadImg;
    }

    public void run() {
        int r1i = 0;
        Log.v("AsynLoadImg", "saveFileRunnable:");
        String r0_String = "share_qq_" + Util.f(this.a.c) + Util.PHOTO_DEFAULT_EXT;
        String r2_String = AsynLoadImg.e + r0_String;
        File r3_File = new File(r2_String);
        Message r4_Message = this.a.g.obtainMessage();
        if (r3_File.exists()) {
            r4_Message.arg1 = r1i;
            r4_Message.obj = r2_String;
            Log.v("AsynLoadImg", "file exists: time:" + (System.currentTimeMillis() - this.a.f));
        } else {
            boolean r0z;
            Bitmap r3_Bitmap = AsynLoadImg.a(this.a.c);
            if (r3_Bitmap != null) {
                r0z = this.a.a(r3_Bitmap, r0_String);
            } else {
                Log.v("AsynLoadImg", "saveFileRunnable:get bmp fail---");
                r0z = false;
            }
            if (r0z) {
                r4_Message.arg1 = 0;
                r4_Message.obj = r2_String;
            } else {
                r4_Message.arg1 = 1;
            }
            Log.v("AsynLoadImg", "file not exists: download time:" + (System.currentTimeMillis() - this.a.f));
        }
        this.a.g.sendMessage(r4_Message);
    }
}