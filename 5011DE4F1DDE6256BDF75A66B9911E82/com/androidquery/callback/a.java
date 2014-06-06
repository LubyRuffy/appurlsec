package com.androidquery.callback;

import com.androidquery.util.Common;

// compiled from: AbstractAjaxCallback.java
class a implements Runnable {
    final /* synthetic */ AbstractAjaxCallback a;
    private final /* synthetic */ Object b;
    private final /* synthetic */ boolean c;

    a(AbstractAjaxCallback r1_AbstractAjaxCallback, Object r2_Object, boolean r3z) {
        this.a = r1_AbstractAjaxCallback;
        this.b = r2_Object;
        this.c = r3z;
    }

    public void run() {
        Common.showProgress(this.b, AbstractAjaxCallback.a(this.a), this.c);
    }
}