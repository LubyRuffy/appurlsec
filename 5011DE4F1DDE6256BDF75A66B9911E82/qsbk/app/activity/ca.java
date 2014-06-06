package qsbk.app.activity;

import android.text.TextUtils;
import java.io.File;
import qsbk.app.cache.SecureFileCache.Callback;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: OneProfileActivity.java
class ca implements Callback {
    final /* synthetic */ OneProfileActivity a;

    ca(OneProfileActivity r1_OneProfileActivity) {
        this.a = r1_OneProfileActivity;
    }

    public void onFinished(File r7_File, String r8_String) {
        if (!TextUtils.isEmpty(r8_String)) {
            this.a.r.post(new b(r8_String));
        }
        if (r7_File == null || (!r7_File.exists()) || Math.abs(r7_File.lastModified() - System.currentTimeMillis()) > 120000) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.a.M = new AsyncDataLoader(new a(((Integer) this.a.H.get(Integer.valueOf(XListViewHeader.STATE_REFRESHING))).intValue(), this.a.v), "MyArticleLoaderThread_1");
            this.a.M.execute(new Void[0]);
        }
    }
}