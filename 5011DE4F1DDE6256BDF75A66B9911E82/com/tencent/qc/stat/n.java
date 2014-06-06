package com.tencent.qc.stat;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;
import qsbk.app.message.api.ChatEngine;

// compiled from: ProGuard
class n extends DefaultConnectionKeepAliveStrategy {
    final /* synthetic */ l a;

    n(l r1_l) {
        this.a = r1_l;
    }

    public long getKeepAliveDuration(HttpResponse r5_HttpResponse, HttpContext r6_HttpContext) {
        long r0j = super.getKeepAliveDuration(r5_HttpResponse, r6_HttpContext);
        return (r0j > -1 ? 1 : (r0j == -1? 0 : -1)) == 0 ? ChatEngine.mQueryListInterval : r0j;
    }
}