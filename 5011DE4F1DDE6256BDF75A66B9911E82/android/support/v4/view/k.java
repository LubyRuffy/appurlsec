package android.support.v4.view;

import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;

// compiled from: MenuItemCompatIcs.java
class k {

    // compiled from: MenuItemCompatIcs.java
    static interface b {
        public boolean onMenuItemActionCollapse(MenuItem r1_MenuItem);

        public boolean onMenuItemActionExpand(MenuItem r1_MenuItem);
    }

    // compiled from: MenuItemCompatIcs.java
    static class a implements OnActionExpandListener {
        private b a;

        public a(b r1_b) {
            this.a = r1_b;
        }

        public boolean onMenuItemActionCollapse(MenuItem r2_MenuItem) {
            return this.a.onMenuItemActionCollapse(r2_MenuItem);
        }

        public boolean onMenuItemActionExpand(MenuItem r2_MenuItem) {
            return this.a.onMenuItemActionExpand(r2_MenuItem);
        }
    }

    public static boolean collapseActionView(MenuItem r1_MenuItem) {
        return r1_MenuItem.collapseActionView();
    }

    public static boolean expandActionView(MenuItem r1_MenuItem) {
        return r1_MenuItem.expandActionView();
    }

    public static boolean isActionViewExpanded(MenuItem r1_MenuItem) {
        return r1_MenuItem.isActionViewExpanded();
    }

    public static MenuItem setOnActionExpandListener(MenuItem r1_MenuItem, b r2_b) {
        return r1_MenuItem.setOnActionExpandListener(new a(r2_b));
    }
}