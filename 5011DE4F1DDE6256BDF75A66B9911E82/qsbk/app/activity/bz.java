package qsbk.app.activity;

import android.text.TextUtils;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import qsbk.app.cache.SecureFileCache.Callback;
import qsbk.app.loader.AsyncDataLoader;

// compiled from: OneProfileActivity.java
class bz implements Callback {
    final /* synthetic */ OneProfileActivity a;

    bz(OneProfileActivity r1_OneProfileActivity) {
        this.a = r1_OneProfileActivity;
    }

    public void onFinished(File r6_File, String r7_String) {
        if (!TextUtils.isEmpty(r7_String)) {
            this.a.h(r7_String);
            this.a.r.post(this.a.X);
        }
        if (r6_File == null || (!r6_File.exists()) || Math.abs(r6_File.lastModified() - System.currentTimeMillis()) > 120000) {
            try {
                Thread.sleep(Util.MILLSECONDS_OF_SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.a.L = new AsyncDataLoader(new c(this.a.u), "OneProfileBaseInfoThread");
            this.a.L.execute(new Void[0]);
        }
    }
}