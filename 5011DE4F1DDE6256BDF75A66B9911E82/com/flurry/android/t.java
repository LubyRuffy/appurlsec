package com.flurry.android;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

// compiled from: SourceFile
final class t extends LinkedHashMap {
    private /* synthetic */ g a;

    t(g r2_g, int r3i, float r4f) {
        this.a = r2_g;
        super(r3i, r4f, true);
    }

    protected final boolean removeEldestEntry(Entry r3_Entry) {
        return size() > this.a.b;
    }
}