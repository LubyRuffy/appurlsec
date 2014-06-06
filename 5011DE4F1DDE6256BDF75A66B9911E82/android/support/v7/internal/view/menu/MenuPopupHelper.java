package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.view.menu.MenuView.ItemView;
import android.support.v7.internal.widget.ListPopupWindow;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;
import java.util.ArrayList;
import qsbk.app.widget.listview.XListViewHeader;

public class MenuPopupHelper implements MenuPresenter, OnKeyListener, OnGlobalLayoutListener, OnItemClickListener, OnDismissListener {
    static final int b;
    private Context a;
    boolean c;
    private LayoutInflater d;
    private ListPopupWindow e;
    private MenuBuilder f;
    private int g;
    private View h;
    private boolean i;
    private ViewTreeObserver j;
    private a k;
    private Callback l;
    private ViewGroup m;

    private class a extends BaseAdapter {
        private MenuBuilder b;
        private int c;

        public a(MenuBuilder r3_MenuBuilder) {
            this.c = -1;
            this.b = r3_MenuBuilder;
            a();
        }

        void a_() {
            MenuItemImpl r2_MenuItemImpl = MenuPopupHelper.this.f.getExpandedItem();
            if (r2_MenuItemImpl != null) {
                ArrayList r3_ArrayList = MenuPopupHelper.this.f.d();
                int r4i = r3_ArrayList.size();
                int r1i = 0;
                while (r1i < r4i) {
                    if (((MenuItemImpl) r3_ArrayList.get(r1i)) == r2_MenuItemImpl) {
                        this.c = r1i;
                        return;
                    } else {
                        r1i++;
                    }
                }
            }
            this.c = -1;
        }

        public int getCount() {
            ArrayList r0_ArrayList;
            r0_ArrayList = MenuPopupHelper.this.i ? this.b.d() : this.b.b();
            return this.c < 0 ? r0_ArrayList.size() : r0_ArrayList.size() - 1;
        }

        public MenuItemImpl getItem(int r3i) {
            ArrayList r0_ArrayList;
            r0_ArrayList = MenuPopupHelper.this.i ? this.b.d() : this.b.b();
            if (this.c < 0 || r3i < this.c) {
                return (MenuItemImpl) r0_ArrayList.get(r3i);
            }
            r3i++;
            return (MenuItemImpl) r0_ArrayList.get(r3i);
        }

        public long getItemId(int r3i) {
            return (long) r3i;
        }

        public View getView(int r6i, View r7_View, ViewGroup r8_ViewGroup) {
            View r1_View;
            r1_View = r7_View == null ? MenuPopupHelper.this.d.inflate(b, r8_ViewGroup, false) : r7_View;
            ItemView r0_ItemView = (ItemView) r1_View;
            if (MenuPopupHelper.this.c) {
                ((ListMenuItemView) r1_View).setForceShowIcon(true);
            }
            r0_ItemView.initialize(getItem(r6i), 0);
            return r1_View;
        }

        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    static {
        b = R.layout.abc_popup_menu_item_layout;
    }

    public MenuPopupHelper(Context r3_Context, MenuBuilder r4_MenuBuilder) {
        this(r3_Context, r4_MenuBuilder, null, false);
    }

    public MenuPopupHelper(Context r2_Context, MenuBuilder r3_MenuBuilder, View r4_View) {
        this(r2_Context, r3_MenuBuilder, r4_View, false);
    }

    public MenuPopupHelper(Context r4_Context, MenuBuilder r5_MenuBuilder, View r6_View, boolean r7z) {
        this.a = r4_Context;
        this.d = LayoutInflater.from(r4_Context);
        this.f = r5_MenuBuilder;
        this.i = r7z;
        Resources r0_Resources = r4_Context.getResources();
        this.g = Math.max(r0_Resources.getDisplayMetrics().widthPixels / 2, r0_Resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.h = r6_View;
        r5_MenuBuilder.addMenuPresenter(this);
    }

    private int a(ListAdapter r11_ListAdapter) {
        int r6i = MeasureSpec.makeMeasureSpec(0, 0);
        int r7i = MeasureSpec.makeMeasureSpec(0, 0);
        int r8i = r11_ListAdapter.getCount();
        int r4i = 0;
        int r1i = 0;
        View r3_View = null;
        int r5i = 0;
        while (r4i < r8i) {
            View r1_View;
            int r0i = r11_ListAdapter.getItemViewType(r4i);
            if (r0i != r1i) {
                r1_View = null;
            } else {
                r0i = r1i;
                r1_View = r3_View;
            }
            if (this.m == null) {
                this.m = new FrameLayout(this.a);
            }
            r3_View = r11_ListAdapter.getView(r4i, r1_View, this.m);
            r3_View.measure(r6i, r7i);
            r5i = Math.max(r5i, r3_View.getMeasuredWidth());
            r4i++;
            r1i = r0i;
        }
        return r5i;
    }

    public boolean collapseItemActionView(MenuBuilder r2_MenuBuilder, MenuItemImpl r3_MenuItemImpl) {
        return false;
    }

    public void dismiss() {
        if (isShowing()) {
            this.e.dismiss();
        }
    }

    public boolean expandItemActionView(MenuBuilder r2_MenuBuilder, MenuItemImpl r3_MenuItemImpl) {
        return false;
    }

    public boolean flagActionItems() {
        return false;
    }

    public int getId() {
        return 0;
    }

    public MenuView getMenuView(ViewGroup r3_ViewGroup) {
        throw new UnsupportedOperationException("MenuPopupHelpers manage their own views");
    }

    public void initForMenu(Context r1_Context, MenuBuilder r2_MenuBuilder) {
    }

    public boolean isShowing() {
        return this.e != null && this.e.isShowing();
    }

    public void onCloseMenu(MenuBuilder r2_MenuBuilder, boolean r3z) {
        if (r2_MenuBuilder != this.f) {
        } else {
            dismiss();
            if (this.l != null) {
                this.l.onCloseMenu(r2_MenuBuilder, r3z);
            }
        }
    }

    public void onDismiss() {
        this.e = null;
        this.f.close();
        if (this.j != null) {
            if (!this.j.isAlive()) {
                this.j = this.h.getViewTreeObserver();
            }
            this.j.removeGlobalOnLayoutListener(this);
            this.j = null;
        }
    }

    public void onGlobalLayout() {
        if (isShowing()) {
            View r0_View = this.h;
            if (r0_View == null || (!r0_View.isShown())) {
                dismiss();
            } else if (isShowing()) {
                this.e.show();
            }
        }
    }

    public void onItemClick(AdapterView<?> r4_AdapterView_, View r5_View, int r6i, long r7j) {
        a r0_a = this.k;
        r0_a.b.performItemAction(r0_a.getItem(r6i), 0);
    }

    public boolean onKey(View r3_View, int r4i, KeyEvent r5_KeyEvent) {
        if (r5_KeyEvent.getAction() != 1 || r4i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    public void onRestoreInstanceState(Parcelable r1_Parcelable) {
    }

    public Parcelable onSaveInstanceState() {
        return null;
    }

    public boolean onSubMenuSelected(SubMenuBuilder r8_SubMenuBuilder) {
        if (r8_SubMenuBuilder.hasVisibleItems()) {
            boolean r0z;
            MenuPopupHelper r3_MenuPopupHelper = new MenuPopupHelper(this.a, r8_SubMenuBuilder, this.h, false);
            r3_MenuPopupHelper.setCallback(this.l);
            int r4i = r8_SubMenuBuilder.size();
            int r0i = 0;
            while (r0i < r4i) {
                MenuItem r5_MenuItem = r8_SubMenuBuilder.getItem(r0i);
                if ((!r5_MenuItem.isVisible()) || r5_MenuItem.getIcon() == null) {
                    r0i++;
                } else {
                    r0z = true;
                    break;
                }
            }
            r0z = false;
            r3_MenuPopupHelper.setForceShowIcon(r0z);
            if (r3_MenuPopupHelper.tryShow()) {
                if (this.l == null) {
                    return true;
                }
                this.l.onOpenSubMenu(r8_SubMenuBuilder);
                return true;
            }
        }
        return false;
    }

    public void setAnchorView(View r1_View) {
        this.h = r1_View;
    }

    public void setCallback(Callback r1_Callback) {
        this.l = r1_Callback;
    }

    public void setForceShowIcon(boolean r1z) {
        this.c = r1z;
    }

    public void show() {
        if (!tryShow()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public boolean tryShow() {
        int r0i = 0;
        this.e = new ListPopupWindow(this.a, null, R.attr.popupMenuStyle);
        this.e.setOnDismissListener(this);
        this.e.setOnItemClickListener(this);
        this.k = new a(this.f);
        this.e.setAdapter(this.k);
        this.e.setModal(true);
        View r2_View = this.h;
        if (r2_View == null) {
            return false;
        }
        if (this.j == null) {
            r0i = 1;
        }
        this.j = r2_View.getViewTreeObserver();
        if (r0i != 0) {
            this.j.addOnGlobalLayoutListener(this);
        }
        this.e.setAnchorView(r2_View);
        this.e.setContentWidth(Math.min(a(this.k), this.g));
        this.e.setInputMethodMode(XListViewHeader.STATE_REFRESHING);
        this.e.show();
        this.e.getListView().setOnKeyListener(this);
        return true;
    }

    public void updateMenuView(boolean r2z) {
        if (this.k != null) {
            this.k.notifyDataSetChanged();
        }
    }
}