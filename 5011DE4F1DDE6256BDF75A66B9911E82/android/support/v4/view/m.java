package android.support.v4.view;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: PagerTabStrip.java
class m implements OnClickListener {
    final /* synthetic */ PagerTabStrip a;

    m(PagerTabStrip r1_PagerTabStrip) {
        this.a = r1_PagerTabStrip;
    }

    public void onClick(View r3_View) {
        this.a.a.setCurrentItem(this.a.a.getCurrentItem() - 1);
    }
}