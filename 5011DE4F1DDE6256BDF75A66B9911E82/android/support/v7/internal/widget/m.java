package android.support.v7.internal.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

// compiled from: ListPopupWindow.java
class m implements OnItemSelectedListener {
    final /* synthetic */ ListPopupWindow a;

    m(ListPopupWindow r1_ListPopupWindow) {
        this.a = r1_ListPopupWindow;
    }

    public void onItemSelected(AdapterView<?> r3_AdapterView_, View r4_View, int r5i, long r6j) {
        if (r5i != -1) {
            a r0_a = this.a.e;
            if (r0_a != null) {
                r0_a.a = false;
            }
        }
    }

    public void onNothingSelected(AdapterView<?> r1_AdapterView_) {
    }
}