package android.support.v4.view;

import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.view.MenuItem;

// compiled from: MenuItemCompat.java
class i implements b {
    final /* synthetic */ OnActionExpandListener a;
    final /* synthetic */ c b;

    i(c r1_c, OnActionExpandListener r2_OnActionExpandListener) {
        this.b = r1_c;
        this.a = r2_OnActionExpandListener;
    }

    public boolean onMenuItemActionCollapse(MenuItem r2_MenuItem) {
        return this.a.onMenuItemActionCollapse(r2_MenuItem);
    }

    public boolean onMenuItemActionExpand(MenuItem r2_MenuItem) {
        return this.a.onMenuItemActionExpand(r2_MenuItem);
    }
}