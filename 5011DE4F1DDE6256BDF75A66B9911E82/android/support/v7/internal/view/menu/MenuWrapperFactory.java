package android.support.v7.internal.view.menu;

import android.os.Build.VERSION;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public final class MenuWrapperFactory {
    private MenuWrapperFactory() {
    }

    public static MenuItem createMenuItemWrapper(MenuItem r2_MenuItem) {
        if (VERSION.SDK_INT >= 16) {
            return new f(r2_MenuItem);
        }
        if (VERSION.SDK_INT >= 14) {
            return new MenuItemWrapperICS(r2_MenuItem);
        }
        return r2_MenuItem;
    }

    public static Menu createMenuWrapper(Menu r2_Menu) {
        return VERSION.SDK_INT >= 14 ? new g(r2_Menu) : r2_Menu;
    }

    public static SupportMenuItem createSupportMenuItemWrapper(MenuItem r2_MenuItem) {
        if (VERSION.SDK_INT >= 16) {
            return new f(r2_MenuItem);
        }
        if (VERSION.SDK_INT >= 14) {
            return new MenuItemWrapperICS(r2_MenuItem);
        }
        throw new UnsupportedOperationException();
    }

    public static SupportMenu createSupportMenuWrapper(Menu r2_Menu) {
        if (VERSION.SDK_INT >= 14) {
            return new g(r2_Menu);
        }
        throw new UnsupportedOperationException();
    }

    public static SupportSubMenu createSupportSubMenuWrapper(SubMenu r2_SubMenu) {
        if (VERSION.SDK_INT >= 14) {
            return new h(r2_SubMenu);
        }
        throw new UnsupportedOperationException();
    }
}