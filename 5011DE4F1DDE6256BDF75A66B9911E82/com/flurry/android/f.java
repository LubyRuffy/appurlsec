package com.flurry.android;

// compiled from: SourceFile
final class f implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ ag b;

    f(ag r1_ag, String r2_String) {
        this.b = r1_ag;
        this.a = r2_String;
    }

    public final void run() {
        CallbackEvent r0_CallbackEvent = new CallbackEvent(101);
        r0_CallbackEvent.setMessage(this.a);
        this.b.y.onMarketAppLaunchError(r0_CallbackEvent);
    }
}