package com.flurry.android;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

// compiled from: SourceFile
final class g {
    private LinkedHashMap a;
    private int b;

    g(int r4i) {
        this.b = 50;
        this.a = new t(this, ((int) Math.ceil((double) (((float) true) / 0.75f))) + 1, 0.75f);
    }

    final synchronized int a() {
        return this.a.size();
    }

    final synchronized Object a(Object r2_Object) {
        return this.a.get(r2_Object);
    }

    final synchronized void a(Object r2_Object, Object r3_Object) {
        this.a.put(r2_Object, r3_Object);
    }

    final synchronized List b() {
        return new ArrayList(this.a.entrySet());
    }

    final synchronized Set c() {
        return this.a.keySet();
    }
}