package android.support.v7.internal.view.menu;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.view.menu.MenuView.ItemView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public abstract class BaseMenuPresenter implements MenuPresenter {
    private Callback a;
    private int b;
    protected Context c;
    protected Context d;
    protected MenuBuilder e;
    protected LayoutInflater f;
    protected LayoutInflater g;
    protected MenuView h;
    private int i;
    private int j;

    public BaseMenuPresenter(Context r2_Context, int r3i, int r4i) {
        this.c = r2_Context;
        this.f = LayoutInflater.from(r2_Context);
        this.b = r3i;
        this.i = r4i;
    }

    protected void a(View r2_View, int r3i) {
        ViewGroup r0_ViewGroup = (ViewGroup) r2_View.getParent();
        if (r0_ViewGroup != null) {
            r0_ViewGroup.removeView(r2_View);
        }
        ((ViewGroup) this.h).addView(r2_View, r3i);
    }

    public abstract void bindItemView(MenuItemImpl r1_MenuItemImpl, ItemView r2_ItemView);

    public boolean collapseItemActionView(MenuBuilder r2_MenuBuilder, MenuItemImpl r3_MenuItemImpl) {
        return false;
    }

    public ItemView createItemView(ViewGroup r4_ViewGroup) {
        return (ItemView) this.f.inflate(this.i, r4_ViewGroup, false);
    }

    public boolean expandItemActionView(MenuBuilder r2_MenuBuilder, MenuItemImpl r3_MenuItemImpl) {
        return false;
    }

    protected boolean filterLeftoverView(ViewGroup r2_ViewGroup, int r3i) {
        r2_ViewGroup.removeViewAt(r3i);
        return true;
    }

    public boolean flagActionItems() {
        return false;
    }

    public int getId() {
        return this.j;
    }

    public View getItemView(MenuItemImpl r2_MenuItemImpl, View r3_View, ViewGroup r4_ViewGroup) {
        ItemView r0_ItemView;
        r0_ItemView = r3_View instanceof ItemView ? (ItemView) r3_View : createItemView(r4_ViewGroup);
        bindItemView(r2_MenuItemImpl, r0_ItemView);
        return (View) r0_ItemView;
    }

    public MenuView getMenuView(ViewGroup r4_ViewGroup) {
        if (this.h == null) {
            this.h = (MenuView) this.f.inflate(this.b, r4_ViewGroup, false);
            this.h.initialize(this.e);
            updateMenuView(true);
        }
        return this.h;
    }

    public void initForMenu(Context r2_Context, MenuBuilder r3_MenuBuilder) {
        this.d = r2_Context;
        this.g = LayoutInflater.from(this.d);
        this.e = r3_MenuBuilder;
    }

    public void onCloseMenu(MenuBuilder r2_MenuBuilder, boolean r3z) {
        if (this.a != null) {
            this.a.onCloseMenu(r2_MenuBuilder, r3z);
        }
    }

    public boolean onSubMenuSelected(SubMenuBuilder r2_SubMenuBuilder) {
        return this.a != null ? this.a.onOpenSubMenu(r2_SubMenuBuilder) : false;
    }

    public void setCallback(Callback r1_Callback) {
        this.a = r1_Callback;
    }

    public void setId(int r1i) {
        this.j = r1i;
    }

    public boolean shouldIncludeItem(int r2i, MenuItemImpl r3_MenuItemImpl) {
        return true;
    }

    public void updateMenuView(boolean r11z) {
        ViewGroup r0_ViewGroup = (ViewGroup) this.h;
        if (r0_ViewGroup == null) {
        } else {
            int r4i;
            if (this.e != null) {
                this.e.flagActionItems();
                ArrayList r7_ArrayList = this.e.b();
                int r8i = r7_ArrayList.size();
                int r6i = 0;
                r4i = 0;
                while (r6i < r8i) {
                    int r1i;
                    MenuItemImpl r1_MenuItemImpl = (MenuItemImpl) r7_ArrayList.get(r6i);
                    if (shouldIncludeItem(r4i, r1_MenuItemImpl)) {
                        MenuItemImpl r2_MenuItemImpl;
                        View r3_View = r0_ViewGroup.getChildAt(r4i);
                        r2_MenuItemImpl = r3_View instanceof ItemView ? ((ItemView) r3_View).getItemData() : null;
                        View r9_View = getItemView(r1_MenuItemImpl, r3_View, r0_ViewGroup);
                        if (r1_MenuItemImpl != r2_MenuItemImpl) {
                            r9_View.setPressed(false);
                        }
                        if (r9_View != r3_View) {
                            a(r9_View, r4i);
                        }
                        r1i = r4i + 1;
                    } else {
                        r1i = r4i;
                    }
                    r6i++;
                    r4i = r1i;
                }
            } else {
                r4i = 0;
            }
            while (r4i < r0_ViewGroup.getChildCount() && !filterLeftoverView(r0_ViewGroup, r4i)) {
                r4i++;
            }
        }
    }
}