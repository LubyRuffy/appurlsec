package com.zkmm.adsdk;

import android.os.AsyncTask;

// compiled from: SourceFile
final class ca extends AsyncTask {
    private /* synthetic */ AdDisplayer a;

    ca(AdDisplayer r1_AdDisplayer) {
        this.a = r1_AdDisplayer;
    }

    protected final /* synthetic */ Object doInBackground(Object ... r3_ObjectA) {
        return m.b(AdDisplayer.m, (byte) 0);
    }

    protected final /* synthetic */ void onPostExecute(Object r4_Object) {
        g r4_g = (g) r4_Object;
        if (r4_g != null) {
            this.a.v = r4_g;
            if (r4_g.m == null) {
                if (AdDisplayer.b == 1) {
                    AdDisplayer.b(this.a, r4_g);
                } else {
                    if (this.a.u != null) {
                        this.a.u.onReceiveAd();
                    }
                }
            } else {
                if (this.a.u != null) {
                    this.a.u.onFailedToReceiveAd(r4_g.m);
                }
                this.a.v = null;
            }
        } else {
            if (this.a.u != null) {
                this.a.u.onFailedToReceiveAd(new ErrorCode(30, "OTHER_ERROR_INTERNET"));
            }
        }
    }
}