package com.androidquery.auth;

import com.androidquery.callback.AbstractAjaxCallback;

// compiled from: FacebookHandle.java
class a implements Runnable {
    final /* synthetic */ FacebookHandle a;
    private final /* synthetic */ AbstractAjaxCallback b;

    a(FacebookHandle r1_FacebookHandle, AbstractAjaxCallback r2_AbstractAjaxCallback) {
        this.a = r1_FacebookHandle;
        this.b = r2_AbstractAjaxCallback;
    }

    public void run() {
        this.a.auth(this.b);
    }
}