package com.flurry.android;

import java.util.Iterator;
import java.util.List;

// compiled from: SourceFile
final class v implements Runnable {
    private /* synthetic */ List a;

    v(List r1_List) {
        this.a = r1_List;
    }

    public final void run() {
        Iterator r1_Iterator = this.a.iterator();
        while (r1_Iterator.hasNext()) {
            ((aa) r1_Iterator.next()).a();
        }
    }
}