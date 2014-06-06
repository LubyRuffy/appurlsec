package com.tencent.qc.stat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: ProGuard
class b implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ StatStore b;

    b(StatStore r1_StatStore, int r2i) {
        this.b = r1_StatStore;
        this.a = r2i;
    }

    public void run() {
        if (this.b.b == 0) {
        } else {
            int r1i;
            StatStore.e.b("Load " + Integer.toString(this.b.b) + " unsent events");
            List r2_List = new ArrayList();
            List r3_List = new ArrayList();
            int r0i = this.a;
            if (r0i == -1 || r0i > StatConfig.g()) {
                r1i = StatConfig.g();
                r0_StatStore = this.b;
                r0_StatStore.b -= r1i;
                this.b.c(r3_List, r1i);
                StatStore.e.b("Peek " + Integer.toString(r3_List.size()) + " unsent events.");
            } else {
                r1i = r0i;
                r0_StatStore = this.b;
                r0_StatStore.b -= r1i;
                this.b.c(r3_List, r1i);
                StatStore.e.b("Peek " + Integer.toString(r3_List.size()) + " unsent events.");
            }
            if (!r3_List.isEmpty()) {
                this.b.b(r3_List, (int)XListViewHeader.STATE_REFRESHING);
                Iterator r4_Iterator = r3_List.iterator();
                while (r4_Iterator.hasNext()) {
                    r2_List.add(((s) r4_Iterator.next()).b);
                }
                l.b().b(r2_List, new m(this, r3_List, r1i));
            }
        }
    }
}