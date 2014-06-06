package android.support.v7.internal.view.menu;

import android.content.Context;
import android.os.Parcelable;
import android.view.ViewGroup;

public interface MenuPresenter {

    public static interface Callback {
        public void onCloseMenu(MenuBuilder r1_MenuBuilder, boolean r2z);

        public boolean onOpenSubMenu(MenuBuilder r1_MenuBuilder);
    }

    public boolean collapseItemActionView(MenuBuilder r1_MenuBuilder, MenuItemImpl r2_MenuItemImpl);

    public boolean expandItemActionView(MenuBuilder r1_MenuBuilder, MenuItemImpl r2_MenuItemImpl);

    public boolean flagActionItems();

    public int getId();

    public MenuView getMenuView(ViewGroup r1_ViewGroup);

    public void initForMenu(Context r1_Context, MenuBuilder r2_MenuBuilder);

    public void onCloseMenu(MenuBuilder r1_MenuBuilder, boolean r2z);

    public void onRestoreInstanceState(Parcelable r1_Parcelable);

    public Parcelable onSaveInstanceState();

    public boolean onSubMenuSelected(SubMenuBuilder r1_SubMenuBuilder);

    public void setCallback(Callback r1_Callback);

    public void updateMenuView(boolean r1z);
}