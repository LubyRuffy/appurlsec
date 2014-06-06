package android.support.v7.internal.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.view.menu.MenuView.ItemView;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;

public class ListMenuPresenter implements MenuPresenter, OnItemClickListener {
    public static final String VIEWS_TAG = "android:menu:list";
    Context a;
    LayoutInflater b;
    MenuBuilder c;
    ExpandedMenuView d;
    int e;
    int f;
    a g;
    private int h;
    private Callback i;
    private int j;

    private class a extends BaseAdapter {
        private int b;

        public a() {
            this.b = -1;
            a();
        }

        void a_() {
            MenuItemImpl r2_MenuItemImpl = ListMenuPresenter.this.c.getExpandedItem();
            if (r2_MenuItemImpl != null) {
                ArrayList r3_ArrayList = ListMenuPresenter.this.c.d();
                int r4i = r3_ArrayList.size();
                int r1i = 0;
                while (r1i < r4i) {
                    if (((MenuItemImpl) r3_ArrayList.get(r1i)) == r2_MenuItemImpl) {
                        this.b = r1i;
                        return;
                    } else {
                        r1i++;
                    }
                }
            }
            this.b = -1;
        }

        public int getCount() {
            int r0i = ListMenuPresenter.this.c.d().size() - ListMenuPresenter.this.h;
            return this.b < 0 ? r0i : r0i - 1;
        }

        public MenuItemImpl getItem(int r4i) {
            ArrayList r1_ArrayList = ListMenuPresenter.this.c.d();
            int r0i = ListMenuPresenter.this.h + r4i;
            if (this.b < 0 || r0i < this.b) {
                return (MenuItemImpl) r1_ArrayList.get(r0i);
            }
            r0i++;
            return (MenuItemImpl) r1_ArrayList.get(r0i);
        }

        public long getItemId(int r3i) {
            return (long) r3i;
        }

        public View getView(int r5i, View r6_View, ViewGroup r7_ViewGroup) {
            View r1_View;
            r1_View = r6_View == null ? ListMenuPresenter.this.b.inflate(ListMenuPresenter.this.f, r7_ViewGroup, false) : r6_View;
            ((ItemView) r1_View).initialize(getItem(r5i), 0);
            return r1_View;
        }

        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public ListMenuPresenter(int r1i, int r2i) {
        this.f = r1i;
        this.e = r2i;
    }

    public ListMenuPresenter(Context r2_Context, int r3i) {
        this(r3i, 0);
        this.a = r2_Context;
        this.b = LayoutInflater.from(this.a);
    }

    public boolean collapseItemActionView(MenuBuilder r2_MenuBuilder, MenuItemImpl r3_MenuItemImpl) {
        return false;
    }

    public boolean expandItemActionView(MenuBuilder r2_MenuBuilder, MenuItemImpl r3_MenuItemImpl) {
        return false;
    }

    public boolean flagActionItems() {
        return false;
    }

    public ListAdapter getAdapter() {
        if (this.g == null) {
            this.g = new a();
        }
        return this.g;
    }

    public int getId() {
        return this.j;
    }

    public MenuView getMenuView(ViewGroup r4_ViewGroup) {
        if (this.g == null) {
            this.g = new a();
        }
        if (this.g.isEmpty()) {
            return null;
        }
        if (this.d == null) {
            this.d = (ExpandedMenuView) this.b.inflate(R.layout.abc_expanded_menu_layout, r4_ViewGroup, false);
            this.d.setAdapter(this.g);
            this.d.setOnItemClickListener(this);
        }
        return this.d;
    }

    public void initForMenu(Context r3_Context, MenuBuilder r4_MenuBuilder) {
        if (this.e != 0) {
            this.a = new ContextThemeWrapper(r3_Context, this.e);
            this.b = LayoutInflater.from(this.a);
        } else if (this.a != null) {
            this.a = r3_Context;
            if (this.b == null) {
                this.b = LayoutInflater.from(this.a);
            }
        }
        this.c = r4_MenuBuilder;
        if (this.g != null) {
            this.g.notifyDataSetChanged();
        }
    }

    public void onCloseMenu(MenuBuilder r2_MenuBuilder, boolean r3z) {
        if (this.i != null) {
            this.i.onCloseMenu(r2_MenuBuilder, r3z);
        }
    }

    public void onItemClick(AdapterView<?> r4_AdapterView_, View r5_View, int r6i, long r7j) {
        this.c.performItemAction(this.g.getItem(r6i), 0);
    }

    public void onRestoreInstanceState(Parcelable r1_Parcelable) {
        restoreHierarchyState((Bundle) r1_Parcelable);
    }

    public Parcelable onSaveInstanceState() {
        if (this.d == null) {
            return null;
        }
        Parcelable r0_Parcelable = new Bundle();
        saveHierarchyState(r0_Parcelable);
        return r0_Parcelable;
    }

    public boolean onSubMenuSelected(SubMenuBuilder r3_SubMenuBuilder) {
        if (!r3_SubMenuBuilder.hasVisibleItems()) {
            return false;
        }
        new MenuDialogHelper(r3_SubMenuBuilder).show(null);
        if (this.i != null) {
            this.i.onOpenSubMenu(r3_SubMenuBuilder);
        }
        return true;
    }

    public void restoreHierarchyState(Bundle r3_Bundle) {
        SparseArray r0_SparseArray = r3_Bundle.getSparseParcelableArray(VIEWS_TAG);
        if (r0_SparseArray != null) {
            this.d.restoreHierarchyState(r0_SparseArray);
        }
    }

    public void saveHierarchyState(Bundle r3_Bundle) {
        SparseArray r0_SparseArray = new SparseArray();
        if (this.d != null) {
            this.d.saveHierarchyState(r0_SparseArray);
        }
        r3_Bundle.putSparseParcelableArray(VIEWS_TAG, r0_SparseArray);
    }

    public void setCallback(Callback r1_Callback) {
        this.i = r1_Callback;
    }

    public void setId(int r1i) {
        this.j = r1i;
    }

    public void setItemIndexOffset(int r2i) {
        this.h = r2i;
        if (this.d != null) {
            updateMenuView(false);
        }
    }

    public void updateMenuView(boolean r2z) {
        if (this.g != null) {
            this.g.notifyDataSetChanged();
        }
    }
}