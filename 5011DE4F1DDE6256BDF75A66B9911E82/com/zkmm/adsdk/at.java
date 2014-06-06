package com.zkmm.adsdk;

import android.content.Context;
import android.os.AsyncTask;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.tencent.tauth.Constants;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;

// compiled from: SourceFile
final class at extends AsyncTask {
    private /* synthetic */ ZKMMAdView a;

    at(ZKMMAdView r1_ZKMMAdView) {
        this.a = r1_ZKMMAdView;
    }

    protected final /* synthetic */ Object doInBackground(Object ... r3_ObjectA) {
        return m.a(this.a.getContext(), (byte) 0);
    }

    protected final /* synthetic */ void onPostExecute(Object r6_Object) {
        int r2i = Constants.ERROR_IO_CharacterCodingException_UnmappableCharacterException;
        j r6_j = (j) r6_Object;
        Context r0_Context = this.a.getContext();
        if (r6_j != null) {
            if (r6_j.a() == null) {
                synchronized (this) {
                    this.a.m = false;
                    int r1i = super.getVisibility();
                    cs r2_cs = new cs(r6_j, r0_Context);
                    r2_cs.setVisibility(r1i);
                    ZKMMAdView r3_ZKMMAdView = this.a;
                    int r3i = ZKMMAdView.a();
                    ZKMMAdView r4_ZKMMAdView = this.a;
                    LayoutParams r1_LayoutParams = new RelativeLayout.LayoutParams(r3i, ZKMMAdView.b());
                    r1_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION);
                    r2_cs.setLayoutParams(r1_LayoutParams);
                    if (this.a.b != null) {
                        this.a.b.onReceiveAd(this.a);
                    }
                    this.a.n.post(new au(this, r2_cs, r6_j, r0_Context));
                }
            } else {
                if (this.a.b != null) {
                    if (r6_j != null) {
                        this.a.b.onFailedToReceiveAd(this.a, r6_j.a());
                    } else {
                        this.a.b.onFailedToReceiveAd(this.a, new ErrorCode(r2i, "ERR_SOCKET_TIMEOUT"));
                    }
                }
            }
        } else {
            if (this.a.b != null) {
                if (r6_j != null) {
                    this.a.b.onFailedToReceiveAd(this.a, r6_j.a());
                } else {
                    this.a.b.onFailedToReceiveAd(this.a, new ErrorCode(r2i, "ERR_SOCKET_TIMEOUT"));
                }
            }
        }
    }
}