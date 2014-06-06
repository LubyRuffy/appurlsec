package com.zkmm.adsdk;

import android.content.Context;
import java.util.Iterator;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
final class au implements Runnable {
    private /* synthetic */ at a;
    private final /* synthetic */ cs b;
    private final /* synthetic */ j c;
    private final /* synthetic */ Context d;

    au(at r1_at, cs r2_cs, j r3_j, Context r4_Context) {
        this.a = r1_at;
        this.b = r2_cs;
        this.c = r3_j;
        this.d = r4_Context;
    }

    public final void run() {
        int r2i = 1;
        this.a.a.addView(this.b);
        int r0i = (byte) (this.c.m & 15);
        if (r0i <= (byte) 1 || r0i >= 16) {
            if (this.a.a.i != 1) {
                s.a(this.a.a, r0i);
            } else {
                r0i = this.a.a.j.nextInt(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER);
                if (r0i == 0 || r0i == 1) {
                    r0i = XListViewHeader.STATE_REFRESHING;
                    s.a(this.a.a, r0i);
                } else {
                    s.a(this.a.a, r0i);
                }
            }
        } else {
            s.a(this.a.a, r0i);
        }
        if (m.x.containsKey(Integer.valueOf(this.c.a))) {
            m.x.put(Integer.valueOf(this.c.a), Integer.valueOf(((Integer) m.x.get(Integer.valueOf(this.c.a))).intValue() + 1));
        } else {
            m.x.put(Integer.valueOf(this.c.a), Integer.valueOf(r2i));
        }
        if (this.c.f != null) {
            new av(this, this.c).start();
        }
        if (this.c.g == null || this.c.g.size() <= 0) {
            try {
                if (this.a.a.a == null) {
                    this.a.a.removeView(this.a.a.a);
                }
            } catch (Exception e) {
            }
            this.a.a.a = this.b;
        } else {
            Iterator r1_Iterator = this.c.g.iterator();
            while (r1_Iterator.hasNext()) {
                if (s.j(this.d, (String) r1_Iterator.next())) {
                    s.b = this.c.a;
                }
            }
            if (this.a.a.a == null) {
                this.a.a.a = this.b;
            } else {
                this.a.a.removeView(this.a.a.a);
                this.a.a.a = this.b;
            }
        }
    }
}