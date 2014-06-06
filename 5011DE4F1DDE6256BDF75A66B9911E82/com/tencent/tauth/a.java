package com.tencent.tauth;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.tencent.open.AsynLoadImgBack;
import com.tencent.open.Util;

// compiled from: ProGuard
class a implements AsynLoadImgBack {
    final /* synthetic */ Bundle a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ IUiListener d;
    final /* synthetic */ Activity e;
    final /* synthetic */ Tencent f;

    a(Tencent r1_Tencent, Bundle r2_Bundle, String r3_String, String r4_String, IUiListener r5_IUiListener, Activity r6_Activity) {
        this.f = r1_Tencent;
        this.a = r2_Bundle;
        this.b = r3_String;
        this.c = r4_String;
        this.d = r5_IUiListener;
        this.e = r6_Activity;
    }

    public void saved(int r6i, String r7_String) {
        if (r6i == 0) {
            this.a.putString("imageLocalUrl", r7_String);
        } else if (Util.e(this.b) && Util.e(this.c)) {
            this.d.onError(new UiError(-6, Constants.MSG_SHARE_GETIMG_ERROR, null));
            Log.v("shareToQQ", Constants.MSG_SHARE_GETIMG_ERROR);
            return;
        } else {
            Tencent.a(this.f, this.e, this.a, this.d);
        }
        Tencent.a(this.f, this.e, this.a, this.d);
    }
}