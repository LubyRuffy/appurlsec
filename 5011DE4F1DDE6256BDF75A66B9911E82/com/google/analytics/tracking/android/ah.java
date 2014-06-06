package com.google.analytics.tracking.android;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

// compiled from: PersistentAnalyticsStore.java
class ah implements y {
    final /* synthetic */ ag a;

    ah(ag r1_ag) {
        this.a = r1_ag;
    }

    public HttpClient newInstance() {
        return new DefaultHttpClient();
    }
}