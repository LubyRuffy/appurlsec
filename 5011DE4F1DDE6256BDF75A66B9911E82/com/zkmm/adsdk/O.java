package com.zkmm.adsdk;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;

// compiled from: SourceFile
public final class O extends AlertDialog {
    private aQ a;
    private int b;
    private int c;
    private int d;
    private LayoutParams e;

    public final void dismiss() {
        super.dismiss();
        this.a = null;
    }

    protected final void onCreate(Bundle r5_Bundle) {
        requestWindowFeature(1);
        super.onCreate(r5_Bundle);
        this.e = new LayoutParams(this.b, this.c + this.d);
        setContentView(null, this.e);
    }
}