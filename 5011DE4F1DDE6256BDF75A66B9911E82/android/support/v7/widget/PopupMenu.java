package android.support.v7.widget;

import android.content.Context;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuBuilder.Callback;
import android.support.v7.internal.view.menu.MenuPopupHelper;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class PopupMenu implements Callback, MenuPresenter.Callback {
    private Context a;
    private MenuBuilder b;
    private View c;
    private MenuPopupHelper d;
    private OnMenuItemClickListener e;
    private OnDismissListener f;

    public static interface OnDismissListener {
        public void onDismiss(PopupMenu r1_PopupMenu);
    }

    public static interface OnMenuItemClickListener {
        public boolean onMenuItemClick(MenuItem r1_MenuItem);
    }

    public PopupMenu(Context r3_Context, View r4_View) {
        this.a = r3_Context;
        this.b = new MenuBuilder(r3_Context);
        this.b.setCallback(this);
        this.c = r4_View;
        this.d = new MenuPopupHelper(r3_Context, this.b, r4_View);
        this.d.setCallback(this);
    }

    public void dismiss() {
        this.d.dismiss();
    }

    public Menu getMenu() {
        return this.b;
    }

    public MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.a);
    }

    public void inflate(int r3i) {
        getMenuInflater().inflate(r3i, this.b);
    }

    public void onCloseMenu(MenuBuilder r2_MenuBuilder, boolean r3z) {
        if (this.f != null) {
            this.f.onDismiss(this);
        }
    }

    public void onCloseSubMenu(SubMenuBuilder r1_SubMenuBuilder) {
    }

    public boolean onMenuItemSelected(MenuBuilder r2_MenuBuilder, MenuItem r3_MenuItem) {
        return this.e != null ? this.e.onMenuItemClick(r3_MenuItem) : false;
    }

    public void onMenuModeChange(MenuBuilder r1_MenuBuilder) {
    }

    public boolean onOpenSubMenu(MenuBuilder r5_MenuBuilder) {
        if (r5_MenuBuilder == null) {
            return false;
        }
        if (!r5_MenuBuilder.hasVisibleItems()) {
            return true;
        }
        new MenuPopupHelper(this.a, r5_MenuBuilder, this.c).show();
        return true;
    }

    public void setOnDismissListener(OnDismissListener r1_OnDismissListener) {
        this.f = r1_OnDismissListener;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener r1_OnMenuItemClickListener) {
        this.e = r1_OnMenuItemClickListener;
    }

    public void show() {
        this.d.show();
    }
}