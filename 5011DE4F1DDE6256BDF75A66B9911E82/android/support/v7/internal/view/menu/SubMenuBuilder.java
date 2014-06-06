package android.support.v7.internal.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.view.menu.MenuBuilder.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class SubMenuBuilder extends MenuBuilder implements SubMenu {
    private MenuBuilder d;
    private MenuItemImpl e;

    public SubMenuBuilder(Context r1_Context, MenuBuilder r2_MenuBuilder, MenuItemImpl r3_MenuItemImpl) {
        super(r1_Context);
        this.d = r2_MenuBuilder;
        this.e = r3_MenuItemImpl;
    }

    public void clearHeader() {
    }

    public boolean collapseItemActionView(MenuItemImpl r2_MenuItemImpl) {
        return this.d.collapseItemActionView(r2_MenuItemImpl);
    }

    public boolean dispatchMenuItemSelected(MenuBuilder r2_MenuBuilder, MenuItem r3_MenuItem) {
        return super.dispatchMenuItemSelected(r2_MenuBuilder, r3_MenuItem) || this.d.dispatchMenuItemSelected(r2_MenuBuilder, r3_MenuItem);
    }

    public boolean expandItemActionView(MenuItemImpl r2_MenuItemImpl) {
        return this.d.expandItemActionView(r2_MenuItemImpl);
    }

    public String getActionViewStatesKey() {
        int r0i;
        r0i = this.e != null ? this.e.getItemId() : 0;
        return r0i == 0 ? null : super.getActionViewStatesKey() + ":" + r0i;
    }

    public MenuItem getItem() {
        return this.e;
    }

    public Menu getParentMenu() {
        return this.d;
    }

    public MenuBuilder getRootMenu() {
        return this.d;
    }

    public boolean isQwertyMode() {
        return this.d.isQwertyMode();
    }

    public boolean isShortcutsVisible() {
        return this.d.isShortcutsVisible();
    }

    public void setCallback(Callback r2_Callback) {
        this.d.setCallback(r2_Callback);
    }

    public SubMenu setHeaderIcon(int r2i) {
        super.a(getContext().getResources().getDrawable(r2i));
        return this;
    }

    public SubMenu setHeaderIcon(Drawable r1_Drawable) {
        super.a(r1_Drawable);
        return this;
    }

    public SubMenu setHeaderTitle(int r2i) {
        super.a(getContext().getResources().getString(r2i));
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence r1_CharSequence) {
        super.a(r1_CharSequence);
        return this;
    }

    public SubMenu setHeaderView(View r1_View) {
        super.a(r1_View);
        return this;
    }

    public SubMenu setIcon(int r2i) {
        this.e.setIcon(r2i);
        return this;
    }

    public SubMenu setIcon(Drawable r2_Drawable) {
        this.e.setIcon(r2_Drawable);
        return this;
    }

    public void setQwertyMode(boolean r2z) {
        this.d.setQwertyMode(r2z);
    }

    public void setShortcutsVisible(boolean r2z) {
        this.d.setShortcutsVisible(r2z);
    }
}