package com.google.analytics.tracking.android;

import com.google.android.gms.analytics.internal.Command;
import java.util.List;
import java.util.Map;

// compiled from: ServiceProxy.java
interface ai {
    public void clearHits();

    public void createService();

    public void dispatch();

    public void putHit(Map<String, String> r1_Map_String__String, long r2j, String r4_String, List<Command> r5_List_Command);
}