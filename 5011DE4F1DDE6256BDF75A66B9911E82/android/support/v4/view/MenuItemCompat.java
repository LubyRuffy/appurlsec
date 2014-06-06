package android.support.v4.view;

import android.os.Build.VERSION;
import android.support.v4.internal.view.SupportMenuItem;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class MenuItemCompat {
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    public static final int SHOW_AS_ACTION_NEVER = 0;
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    static final d a;

    public static interface OnActionExpandListener {
        public boolean onMenuItemActionCollapse(MenuItem r1_MenuItem);

        public boolean onMenuItemActionExpand(MenuItem r1_MenuItem);
    }

    static interface d {
        public boolean collapseActionView(MenuItem r1_MenuItem);

        public boolean expandActionView(MenuItem r1_MenuItem);

        public View getActionView(MenuItem r1_MenuItem);

        public boolean isActionViewExpanded(MenuItem r1_MenuItem);

        public MenuItem setActionView(MenuItem r1_MenuItem, int r2i);

        public MenuItem setActionView(MenuItem r1_MenuItem, View r2_View);

        public MenuItem setOnActionExpandListener(MenuItem r1_MenuItem, android.support.v4.view.MenuItemCompat.OnActionExpandListener r2_android_support_v4_view_MenuItemCompat_OnActionExpandListener);

        public void setShowAsAction(MenuItem r1_MenuItem, int r2i);
    }

    static class a implements d {
        a() {
        }

        public boolean collapseActionView(MenuItem r2_MenuItem) {
            return false;
        }

        public boolean expandActionView(MenuItem r2_MenuItem) {
            return false;
        }

        public View getActionView(MenuItem r2_MenuItem) {
            return null;
        }

        public boolean isActionViewExpanded(MenuItem r2_MenuItem) {
            return false;
        }

        public MenuItem setActionView(MenuItem r1_MenuItem, int r2i) {
            return r1_MenuItem;
        }

        public MenuItem setActionView(MenuItem r1_MenuItem, View r2_View) {
            return r1_MenuItem;
        }

        public MenuItem setOnActionExpandListener(MenuItem r1_MenuItem, android.support.v4.view.MenuItemCompat.OnActionExpandListener r2_android_support_v4_view_MenuItemCompat_OnActionExpandListener) {
            return r1_MenuItem;
        }

        public void setShowAsAction(MenuItem r1_MenuItem, int r2i) {
        }
    }

    static class b implements d {
        b() {
        }

        public boolean collapseActionView(MenuItem r2_MenuItem) {
            return false;
        }

        public boolean expandActionView(MenuItem r2_MenuItem) {
            return false;
        }

        public View getActionView(MenuItem r2_MenuItem) {
            return j.getActionView(r2_MenuItem);
        }

        public boolean isActionViewExpanded(MenuItem r2_MenuItem) {
            return false;
        }

        public MenuItem setActionView(MenuItem r2_MenuItem, int r3i) {
            return j.setActionView(r2_MenuItem, r3i);
        }

        public MenuItem setActionView(MenuItem r2_MenuItem, View r3_View) {
            return j.setActionView(r2_MenuItem, r3_View);
        }

        public MenuItem setOnActionExpandListener(MenuItem r1_MenuItem, android.support.v4.view.MenuItemCompat.OnActionExpandListener r2_android_support_v4_view_MenuItemCompat_OnActionExpandListener) {
            return r1_MenuItem;
        }

        public void setShowAsAction(MenuItem r1_MenuItem, int r2i) {
            j.setShowAsAction(r1_MenuItem, r2i);
        }
    }

    static class c extends b {
        c() {
        }

        public boolean collapseActionView(MenuItem r2_MenuItem) {
            return k.collapseActionView(r2_MenuItem);
        }

        public boolean expandActionView(MenuItem r2_MenuItem) {
            return k.expandActionView(r2_MenuItem);
        }

        public boolean isActionViewExpanded(MenuItem r2_MenuItem) {
            return k.isActionViewExpanded(r2_MenuItem);
        }

        public MenuItem setOnActionExpandListener(MenuItem r2_MenuItem, android.support.v4.view.MenuItemCompat.OnActionExpandListener r3_android_support_v4_view_MenuItemCompat_OnActionExpandListener) {
            return r3_android_support_v4_view_MenuItemCompat_OnActionExpandListener == null ? k.setOnActionExpandListener(r2_MenuItem, null) : k.setOnActionExpandListener(r2_MenuItem, new i(this, r3_android_support_v4_view_MenuItemCompat_OnActionExpandListener));
        }
    }

    static {
        int r0i = VERSION.SDK_INT;
        if (r0i >= 14) {
            a = new c();
        } else if (r0i >= 11) {
            a = new b();
        } else {
            a = new a();
        }
    }

    public static boolean collapseActionView(MenuItem r1_MenuItem) {
        return r1_MenuItem instanceof SupportMenuItem ? ((SupportMenuItem) r1_MenuItem).collapseActionView() : a.collapseActionView(r1_MenuItem);
    }

    public static boolean expandActionView(MenuItem r1_MenuItem) {
        return r1_MenuItem instanceof SupportMenuItem ? ((SupportMenuItem) r1_MenuItem).expandActionView() : a.expandActionView(r1_MenuItem);
    }

    public static ActionProvider getActionProvider(MenuItem r2_MenuItem) {
        if (r2_MenuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem) r2_MenuItem).getSupportActionProvider();
        }
        Log.w("MenuItemCompat", "getActionProvider: item does not implement SupportMenuItem; returning null");
        return null;
    }

    public static View getActionView(MenuItem r1_MenuItem) {
        return r1_MenuItem instanceof SupportMenuItem ? ((SupportMenuItem) r1_MenuItem).getActionView() : a.getActionView(r1_MenuItem);
    }

    public static boolean isActionViewExpanded(MenuItem r1_MenuItem) {
        return r1_MenuItem instanceof SupportMenuItem ? ((SupportMenuItem) r1_MenuItem).isActionViewExpanded() : a.isActionViewExpanded(r1_MenuItem);
    }

    public static MenuItem setActionProvider(MenuItem r2_MenuItem, ActionProvider r3_ActionProvider) {
        if (r2_MenuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem) r2_MenuItem).setSupportActionProvider(r3_ActionProvider);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return r2_MenuItem;
    }

    public static MenuItem setActionView(MenuItem r1_MenuItem, int r2i) {
        return r1_MenuItem instanceof SupportMenuItem ? ((SupportMenuItem) r1_MenuItem).setActionView(r2i) : a.setActionView(r1_MenuItem, r2i);
    }

    public static MenuItem setActionView(MenuItem r1_MenuItem, View r2_View) {
        return r1_MenuItem instanceof SupportMenuItem ? ((SupportMenuItem) r1_MenuItem).setActionView(r2_View) : a.setActionView(r1_MenuItem, r2_View);
    }

    public static MenuItem setOnActionExpandListener(MenuItem r1_MenuItem, OnActionExpandListener r2_OnActionExpandListener) {
        return r1_MenuItem instanceof SupportMenuItem ? ((SupportMenuItem) r1_MenuItem).setSupportOnActionExpandListener(r2_OnActionExpandListener) : a.setOnActionExpandListener(r1_MenuItem, r2_OnActionExpandListener);
    }

    public static void setShowAsAction(MenuItem r1_MenuItem, int r2i) {
        if (r1_MenuItem instanceof SupportMenuItem) {
            ((SupportMenuItem) r1_MenuItem).setShowAsAction(r2i);
        } else {
            a.setShowAsAction(r1_MenuItem, r2i);
        }
    }
}