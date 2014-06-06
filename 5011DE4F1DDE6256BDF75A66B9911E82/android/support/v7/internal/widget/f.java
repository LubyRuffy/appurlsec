package android.support.v7.internal.widget;

import android.support.v7.internal.view.menu.MenuItemImpl;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: ActionBarView.java
class f implements OnClickListener {
    final /* synthetic */ ActionBarView a;

    f(ActionBarView r1_ActionBarView) {
        this.a = r1_ActionBarView;
    }

    public void onClick(View r2_View) {
        MenuItemImpl r0_MenuItemImpl = ActionBarView.b(this.a).b;
        if (r0_MenuItemImpl != null) {
            r0_MenuItemImpl.collapseActionView();
        }
    }
}