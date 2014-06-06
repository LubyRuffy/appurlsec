package com.tencent.mm.sdk.plugin;

class a implements Runnable {
    final /* synthetic */ MMPluginOAuth a;

    a(MMPluginOAuth r1_MMPluginOAuth) {
        this.a = r1_MMPluginOAuth;
    }

    public void run() {
        if (this.a.a != null) {
            this.a.a.onResult(this.a);
        }
    }
}