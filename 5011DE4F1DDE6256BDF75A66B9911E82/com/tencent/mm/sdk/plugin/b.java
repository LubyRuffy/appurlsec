package com.tencent.mm.sdk.plugin;

import com.tencent.mm.sdk.plugin.MMPluginOAuth.Receiver;

class b implements Runnable {
    final /* synthetic */ MMPluginOAuth a;
    final /* synthetic */ String b;
    final /* synthetic */ Receiver c;

    b(Receiver r1_Receiver, MMPluginOAuth r2_MMPluginOAuth, String r3_String) {
        this.c = r1_Receiver;
        this.a = r2_MMPluginOAuth;
        this.b = r3_String;
    }

    public void run() {
        MMPluginOAuth.a(this.a, this.b);
    }
}