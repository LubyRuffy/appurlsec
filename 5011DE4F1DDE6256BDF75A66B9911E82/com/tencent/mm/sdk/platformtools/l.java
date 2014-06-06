package com.tencent.mm.sdk.platformtools;

import android.widget.ListView;
import com.tencent.mm.sdk.platformtools.BackwardSupportUtil.SmoothScrollFactory.IScroll;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;

class l implements IScroll {
    l() {
    }

    public void doScroll(ListView r3_ListView) {
        if (r3_ListView.getFirstVisiblePosition() > 10) {
            r3_ListView.setSelection(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO);
        }
        r3_ListView.smoothScrollToPosition(0);
    }

    public void doScroll(ListView r4_ListView, int r5i) {
        int r0i = r4_ListView.getFirstVisiblePosition();
        if (r0i <= r5i || r0i - r5i <= 10) {
            if (r0i >= r5i || r5i - r0i <= 10) {
                r4_ListView.smoothScrollToPosition(r5i);
            } else {
                r4_ListView.setSelection(r5i - 10);
            }
        } else {
            r4_ListView.setSelection(r5i + 10);
        }
        r4_ListView.smoothScrollToPosition(r5i);
    }
}