package com.tencent.mm.sdk.platformtools;

import android.widget.ListView;
import com.tencent.mm.sdk.platformtools.BackwardSupportUtil.SmoothScrollFactory.IScroll;

class k implements IScroll {
    k() {
    }

    public void doScroll(ListView r2_ListView) {
        r2_ListView.setSelection(0);
    }

    public void doScroll(ListView r1_ListView, int r2i) {
        r1_ListView.setSelection(r2i);
    }
}