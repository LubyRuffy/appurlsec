package com.zkmm.adsdk;

import android.os.AsyncTask;
import java.io.File;

// compiled from: SourceFile
final class cb extends AsyncTask {
    private /* synthetic */ AdDisplayer a;
    private final /* synthetic */ boolean b;
    private final /* synthetic */ String c;

    cb(AdDisplayer r1_AdDisplayer, boolean r2z, String r3_String) {
        this.a = r1_AdDisplayer;
        this.b = r2z;
        this.c = r3_String;
    }

    protected final /* synthetic */ Object doInBackground(Object ... r10_ObjectA) {
        r10_ObjectA = r10_ObjectA;
        String r3_String = s.a((String) r10_ObjectA[0], (String) r10_ObjectA[1]);
        if (this.b) {
            g r0_g = (g) r10_ObjectA[3];
            String r1_String = new StringBuilder(String.valueOf((String) r10_ObjectA[1])).append((String) r10_ObjectA[2]).append(File.separator).toString();
            File r4_File = new File(r1_String);
            if (!r4_File.exists()) {
                r4_File.mkdirs();
            }
            r0_g.d = new StringBuilder("file://").append(r1_String).append("index.html?w=").append(((float) this.a.n.widthPixels) / this.a.n.density).append("&h=").append(((float) this.a.n.heightPixels) / this.a.n.density).toString();
            s.a(r0_g, new StringBuilder(String.valueOf(r1_String)).append("object.temp").toString());
            this.a.c = (String) r10_ObjectA[1];
            if (this.a.e == null || (!this.a.e.isShowing())) {
                File[] r4_FileA = new File(m.P).listFiles();
                if (r4_FileA == null || r4_FileA.length <= 0) {
                    return r3_String;
                }
                int r5i = r4_FileA.length;
                int r1i = 0;
                while (r1i < r5i) {
                    File r2_File = r4_FileA[r1i];
                    if (!r2_File.getAbsolutePath().contains(new StringBuilder(String.valueOf(r0_g.a)).toString())) {
                        s.a(r2_File);
                    }
                    r1i++;
                }
            }
        }
        return r3_String;
    }

    protected final /* synthetic */ void onPostExecute(Object r4_Object) {
        String r4_String = (String) r4_Object;
        if (!this.b) {
            if (r4_String != null) {
                this.a.h.clearHistory();
                this.a.h.loadUrl(this.c);
            } else if (this.a.u != null) {
                this.a.u.onFailedToReceiveAd(new ErrorCode(36, "OTHER_ERROR_DOWNLOADING_AD_RESOURCES"));
            }
        }
    }
}