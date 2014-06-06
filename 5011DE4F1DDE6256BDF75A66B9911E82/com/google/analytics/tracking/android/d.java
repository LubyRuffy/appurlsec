package com.google.analytics.tracking.android;

import com.google.android.gms.analytics.internal.Command;
import java.util.Collection;
import java.util.Map;

// compiled from: AnalyticsStore.java
interface d {
    public void clearHits();

    public void close();

    public void dispatch();

    public void putHit(Map<String, String> r1_Map_String__String, long r2j, String r4_String, Collection<Command> r5_Collection_Command);

    public void setDispatch(boolean r1z);

    public void setThrottlingEnabled(boolean r1z);
}