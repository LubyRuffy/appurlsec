package com.google.analytics.tracking.android;

import com.google.analytics.tracking.android.Analytics.AppOptOutCallback;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

interface AnalyticsThread {

    public static interface ClientIdCallback {
        public void reportClientId(String r1_String);
    }

    public void dispatch();

    public LinkedBlockingQueue<Runnable> getQueue();

    public Thread getThread();

    public void requestAppOptOut(AppOptOutCallback r1_AppOptOutCallback);

    public void requestClientId(ClientIdCallback r1_ClientIdCallback);

    public void sendHit(Map<String, String> r1_Map_String__String);

    public void setAppOptOut(boolean r1z);
}