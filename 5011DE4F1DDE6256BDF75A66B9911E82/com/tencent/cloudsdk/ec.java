package com.tencent.cloudsdk;

import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class ec {
    private dz a;
    private ed b;

    public ec() {
        this.a = new dz();
        this.b = new ed();
    }

    public eb a(int r2i) {
        eb r0_eb = this.a;
        switch (r2i) {
            case XListViewHeader.STATE_REFRESHING:
                return this.b;
        }
        return r0_eb;
    }
}