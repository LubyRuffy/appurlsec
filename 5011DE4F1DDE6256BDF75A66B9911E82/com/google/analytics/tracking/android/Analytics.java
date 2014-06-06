package com.google.analytics.tracking.android;

public interface Analytics {

    public static interface AppOptOutCallback {
        public void reportAppOptOut(boolean r1z);
    }

    public boolean debugEnabled();

    public Tracker getDefaultTracker();

    public Tracker getTracker(String r1_String);

    public void requestAppOptOut(AppOptOutCallback r1_AppOptOutCallback);

    public void setAppOptOut(boolean r1z);

    public void setDebug(boolean r1z);

    public void setDefaultTracker(Tracker r1_Tracker);
}