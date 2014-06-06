package com.tencent.cloudsdk;

import qsbk.app.widget.ProfileHeaderListView;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class dt implements dj {
    private boolean a;

    public dt() {
        this.a = false;
    }

    public int a(int r4i) {
        if (b()) {
            switch (r4i) {
                case ProfileHeaderListView.INVALID_TAB_ID:
                case XListViewHeader.STATE_NORMAL:
                case XListViewHeader.STATE_REFRESHING:
                case XListViewFooter.STATE_NOMORE:
                case XListViewFooter.STATE_NODATA:
                    return 1;
                case XListViewHeader.STATE_READY:
                    return XListViewFooter.STATE_NOMORE;
            }
            return 1;
        } else {
            switch (r4i) {
                case ProfileHeaderListView.INVALID_TAB_ID:
                    return 1;
                case XListViewHeader.STATE_NORMAL:
                case XListViewHeader.STATE_READY:
                case XListViewFooter.STATE_NOMORE:
                case XListViewFooter.STATE_NODATA:
                    return XListViewFooter.STATE_NOMORE;
                case XListViewHeader.STATE_REFRESHING:
                    return XListViewHeader.STATE_REFRESHING;
            }
            return XListViewFooter.STATE_NOMORE;
        }
    }

    public long a() {
        return (long) bv.c().i();
    }

    public void a(boolean r1z) {
        this.a = r1z;
    }

    public boolean b() {
        return this.a;
    }

    public long c() {
        return (long) bv.c().j();
    }
}