package com.tencent.qc.stat;

import android.content.Context;
import com.tencent.qc.stat.common.StatCommonHelper;
import com.tencent.qc.stat.common.StatPreferences;
import com.tencent.qc.stat.event.Event;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: ProGuard
class t implements Runnable {
    private Event a;
    private StatReportStrategy b;

    public t(Event r2_Event) {
        this.b = null;
        this.a = r2_Event;
        this.b = StatConfig.a();
    }

    private void a() {
        l.b().a(this.a, new a(this));
    }

    public void run() {
        if (StatConfig.c()) {
            StatService.f.b("Lauch stat task in thread:" + Thread.currentThread().getName());
            Context r0_Context = this.a.c();
            if (StatCommonHelper.h(r0_Context)) {
                if (StatConfig.m() && StatCommonHelper.g(r0_Context)) {
                    this.b = StatReportStrategy.a;
                }
                switch (p.a[this.b.ordinal()]) {
                    case XListViewHeader.STATE_READY:
                        a();
                    case XListViewHeader.STATE_REFRESHING:
                        if (StatCommonHelper.e(r0_Context)) {
                            a();
                        } else {
                            StatStore.a(r0_Context).b(this.a, null);
                        }
                    case XListViewFooter.STATE_NOMORE:
                    case XListViewFooter.STATE_NODATA:
                        StatStore.a(r0_Context).b(this.a, null);
                    case ShareUtils.SHARE_SMS:
                        StatStore.a(r0_Context).b(this.a, new k(this, r0_Context));
                    case ShareUtils.SHARE_COPY:
                        try {
                            StatStore.a(r0_Context).b(this.a, null);
                            String r1_String = "last_period_ts";
                            Long r2_Long = Long.valueOf(StatPreferences.a(r0_Context, r1_String, 0));
                            Long r3_Long = Long.valueOf(System.currentTimeMillis());
                            if (Long.valueOf(Long.valueOf(r3_Long.longValue() - r2_Long.longValue()).longValue() / 60000).longValue() > ((long) StatConfig.l())) {
                                StatStore.a(r0_Context).a(-1);
                                StatPreferences.b(r0_Context, r1_String, r3_Long.longValue());
                            }
                        } catch (Exception e) {
                            StatService.f.b(e);
                        }
                }
                StatService.f.e("Invalid stat strategy:" + StatConfig.a());
            } else {
                StatStore.a(r0_Context).b(this.a, null);
            }
        }
    }
}