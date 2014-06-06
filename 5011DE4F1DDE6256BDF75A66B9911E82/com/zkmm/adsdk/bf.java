package com.zkmm.adsdk;

// compiled from: SourceFile
final class bf implements Runnable {
    private /* synthetic */ be a;
    private final /* synthetic */ String b;

    bf(be r1_be, String r2_String) {
        this.a = r1_be;
        this.b = r2_String;
    }

    public final void run() {
        this.a.a.a.c.loadUrl(new StringBuilder("javascript:adwoVoiceRecordComplete(").append(this.b).append(");").toString());
    }
}