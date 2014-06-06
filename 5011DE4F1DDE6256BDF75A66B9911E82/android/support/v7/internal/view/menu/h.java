package android.support.v7.internal.view.menu;

import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

// compiled from: SubMenuWrapperICS.java
class h extends g implements SupportSubMenu {
    h(SubMenu r1_SubMenu) {
        super(r1_SubMenu);
    }

    public void clearHeader() {
        ((SubMenu) this.a).clearHeader();
    }

    public MenuItem getItem() {
        return a(((SubMenu) this.a).getItem());
    }

    public SubMenu getWrappedObject() {
        return (SubMenu) this.a;
    }

    public SubMenu setHeaderIcon(int r2i) {
        ((SubMenu) this.a).setHeaderIcon(r2i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable r2_Drawable) {
        ((SubMenu) this.a).setHeaderIcon(r2_Drawable);
        return this;
    }

    public SubMenu setHeaderTitle(int r2i) {
        ((SubMenu) this.a).setHeaderTitle(r2i);
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence r2_CharSequence) {
        ((SubMenu) this.a).setHeaderTitle(r2_CharSequence);
        return this;
    }

    public SubMenu setHeaderView(View r2_View) {
        ((SubMenu) this.a).setHeaderView(r2_View);
        return this;
    }

    public SubMenu setIcon(int r2i) {
        ((SubMenu) this.a).setIcon(r2i);
        return this;
    }

    public SubMenu setIcon(Drawable r2_Drawable) {
        ((SubMenu) this.a).setIcon(r2_Drawable);
        return this;
    }
}