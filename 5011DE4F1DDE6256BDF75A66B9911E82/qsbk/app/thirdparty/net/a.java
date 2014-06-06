package qsbk.app.thirdparty.net;

import qsbk.app.thirdparty.ThirdPartyException;
import qsbk.app.thirdparty.ThirdPartyParameters;

// compiled from: AsyncWeiboRunner.java
final class a extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ ThirdPartyParameters c;
    final /* synthetic */ RequestListener d;

    a(String r1_String, String r2_String, String r3_String, ThirdPartyParameters r4_ThirdPartyParameters, RequestListener r5_RequestListener) {
        this.a = r2_String;
        this.b = r3_String;
        this.c = r4_ThirdPartyParameters;
        this.d = r5_RequestListener;
        super(r1_String);
    }

    public void run() {
        try {
            this.d.onComplete(HttpManager.openUrl(this.a, this.b, this.c));
        } catch (ThirdPartyException e) {
            this.d.onError(e);
        }
    }
}