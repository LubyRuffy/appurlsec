package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ActionProvider.SubUiVisibilityListener;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.internal.view.menu.ActionMenuView.ActionMenuChildView;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.view.menu.MenuView.ItemView;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import java.util.ArrayList;
import qsbk.app.utils.Base64;

public class ActionMenuPresenter extends BaseMenuPresenter implements SubUiVisibilityListener {
    final e a;
    int b;
    private View i;
    private boolean j;
    private boolean k;
    private int l;
    private int m;
    private int n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private int s;
    private final SparseBooleanArray t;
    private View u;
    private d v;
    private a w;
    private b x;


    private static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR;
        public int openSubMenuId;

        static {
            CREATOR = new a();
        }

        SavedState() {
        }

        SavedState(Parcel r2_Parcel) {
            this.openSubMenuId = r2_Parcel.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel r2_Parcel, int r3i) {
            r2_Parcel.writeInt(this.openSubMenuId);
        }
    }

    private class a extends MenuDialogHelper {
        public a(SubMenuBuilder r3_SubMenuBuilder) {
            super(r3_SubMenuBuilder);
            ActionMenuPresenter.this.setCallback(ActionMenuPresenter.this);
        }

        public void onDismiss(DialogInterface r3_DialogInterface) {
            super.onDismiss(r3_DialogInterface);
            ActionMenuPresenter.this.w = null;
            ActionMenuPresenter.this.b = 0;
        }
    }

    private class b implements Runnable {
        private d b;

        public b(d r2_d) {
            this.b = r2_d;
        }

        public void run() {
            ActionMenuPresenter.this.e.changeMenuMode();
            View r0_View = (View) ActionMenuPresenter.this.h;
            if (r0_View == null || r0_View.getWindowToken() == null || (!this.b.tryShow())) {
                ActionMenuPresenter.this.x = null;
            } else {
                ActionMenuPresenter.this.v = this.b;
                ActionMenuPresenter.this.x = null;
            }
        }
    }

    private class c extends ImageButton implements ActionMenuChildView {
        public c(Context r5_Context) {
            super(r5_Context, null, R.attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
        }

        public boolean needsDividerAfter() {
            return false;
        }

        public boolean needsDividerBefore() {
            return false;
        }

        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.showOverflowMenu();
            return true;
        }
    }

    private class d extends MenuPopupHelper {
        public d(Context r3_Context, MenuBuilder r4_MenuBuilder, View r5_View, boolean r6z) {
            super(r3_Context, r4_MenuBuilder, r5_View, r6z);
            setCallback(ActionMenuPresenter.this);
        }

        public void onDismiss() {
            super.onDismiss();
            ActionMenuPresenter.this.e.close();
            ActionMenuPresenter.this.v = null;
        }
    }

    private class e implements Callback {
        private e() {
        }

        public void onCloseMenu(MenuBuilder r3_MenuBuilder, boolean r4z) {
            if (r3_MenuBuilder instanceof SubMenuBuilder) {
                ((SubMenuBuilder) r3_MenuBuilder).getRootMenu().a(false);
            }
        }

        public boolean onOpenSubMenu(MenuBuilder r4_MenuBuilder) {
            if (r4_MenuBuilder == null) {
                return false;
            }
            ActionMenuPresenter.this.b = ((SubMenuBuilder) r4_MenuBuilder).getItem().getItemId();
            return false;
        }
    }

    public ActionMenuPresenter(Context r3_Context) {
        super(r3_Context, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
        this.t = new SparseBooleanArray();
        this.a = new e(null);
    }

    private View a(MenuItem r7_MenuItem) {
        ViewGroup r0_ViewGroup = (ViewGroup) this.h;
        if (r0_ViewGroup == null) {
            return null;
        }
        int r5i = r0_ViewGroup.getChildCount();
        int r4i = 0;
        while (r4i < r5i) {
            View r2_View = r0_ViewGroup.getChildAt(r4i);
            if (r2_View instanceof ItemView && ((ItemView) r2_View).getItemData() == r7_MenuItem) {
                return r2_View;
            }
            r4i++;
        }
        return null;
    }

    public void bindItemView(MenuItemImpl r2_MenuItemImpl, ItemView r3_ItemView) {
        r3_ItemView.initialize(r2_MenuItemImpl, 0);
        ((ActionMenuItemView) r3_ItemView).setItemInvoker((ActionMenuView) this.h);
    }

    public boolean dismissPopupMenus() {
        return hideOverflowMenu() | hideSubMenus();
    }

    public boolean filterLeftoverView(ViewGroup r3_ViewGroup, int r4i) {
        return r3_ViewGroup.getChildAt(r4i) == this.i ? false : super.filterLeftoverView(r3_ViewGroup, r4i);
    }

    public boolean flagActionItems() {
        MenuItemImpl r3_MenuItemImpl;
        int r3i;
        ArrayList r13_ArrayList = this.e.b();
        int r14i = r13_ArrayList.size();
        int r7i = this.n;
        int r9i = this.m;
        int r15i = MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup r2_ViewGroup = (ViewGroup) this.h;
        int r6i = 0;
        int r5i = 0;
        int r4i = 0;
        int r10i = 0;
        while (r10i < r14i) {
            r3_MenuItemImpl = (MenuItemImpl) r13_ArrayList.get(r10i);
            if (r3_MenuItemImpl.requiresActionButton()) {
                r6i++;
            } else if (r3_MenuItemImpl.requestsActionButton()) {
                r5i++;
            } else {
                r4i = 1;
            }
            r3i = (this.r && r3_MenuItemImpl.isActionViewExpanded()) ? 0 : r7i;
            r10i++;
            r7i = r3i;
        }
        if (this.j) {
            if (r4i != 0 || r6i + r5i > r7i) {
                r7i--;
            }
        }
        r10i = r7i - r6i;
        SparseBooleanArray r16_SparseBooleanArray = this.t;
        r16_SparseBooleanArray.clear();
        r3i = 0;
        if (this.p) {
            r3i = r9i / this.s;
            r5i = (r9i % this.s) / r3i + this.s;
        } else {
            r5i = 0;
        }
        int r12i = 0;
        r7i = 0;
        r4i = r3i;
        while (r12i < r14i) {
            r3_MenuItemImpl = (MenuItemImpl) r13_ArrayList.get(r12i);
            int r8i;
            if (r3_MenuItemImpl.requiresActionButton()) {
                View r6_View = getItemView(r3_MenuItemImpl, this.u, r2_ViewGroup);
                if (this.u == null) {
                    this.u = r6_View;
                }
                if (this.p) {
                    r4i -= ActionMenuView.a(r6_View, r5i, r4i, r15i, 0);
                } else {
                    r6_View.measure(r15i, r15i);
                }
                r6i = r6_View.getMeasuredWidth();
                r8i = r9i - r6i;
                if (r7i == 0) {
                    r7i = r3_MenuItemImpl.getGroupId();
                    if (r7i == 0) {
                        r16_SparseBooleanArray.put(r7i, true);
                    }
                    r3_MenuItemImpl.setIsActionButton(true);
                    r3i = r8i;
                    r7i = r10i;
                } else {
                    r6i = r7i;
                    r7i = r3_MenuItemImpl.getGroupId();
                    if (r7i == 0) {
                        r3_MenuItemImpl.setIsActionButton(true);
                        r3i = r8i;
                        r7i = r10i;
                    } else {
                        r16_SparseBooleanArray.put(r7i, true);
                        r3_MenuItemImpl.setIsActionButton(true);
                        r3i = r8i;
                        r7i = r10i;
                    }
                }
            } else if (r3_MenuItemImpl.requestsActionButton()) {
                boolean r6z;
                boolean r11z;
                int r17i = r3_MenuItemImpl.getGroupId();
                boolean r18z = r16_SparseBooleanArray.get(r17i);
                if ((r10i > 0 || r18z) && r9i > 0) {
                    r6z = (!this.p) || r4i > 0;
                }
                if (r6z) {
                    boolean r4z;
                    View r11_View = getItemView(r3_MenuItemImpl, this.u, r2_ViewGroup);
                    if (this.u == null) {
                        this.u = r11_View;
                    }
                    if (this.p) {
                        int r19i = ActionMenuView.a(r11_View, r5i, r4i, r15i, 0);
                        r8i = r4i - r19i;
                        r4z = r19i == 0 ? false : r6z;
                        r6i = r8i;
                    } else {
                        r11_View.measure(r15i, r15i);
                        r6i = r4i;
                        r4z = r6z;
                    }
                    r8i = r11_View.getMeasuredWidth();
                    r9i -= r8i;
                    if (r7i == 0) {
                        r7i = r8i;
                    }
                    if (this.p) {
                        r11z = r4z & (r9i >= 0 ? 1 : 0);
                        r8i = r7i;
                        r7i = r6i;
                    } else {
                        r11z = r4z & (r9i + r7i > 0 ? 1 : 0);
                        r8i = r7i;
                        r7i = r6i;
                    }
                } else {
                    r11z = r6z;
                    r8i = r7i;
                    r7i = r4i;
                }
                if ((!r11z) || r17i == 0) {
                    if (r18z) {
                        r16_SparseBooleanArray.put(r17i, false);
                        r6i = r10i;
                        r10i = 0;
                        while (r10i < r12i) {
                            MenuItemImpl r4_MenuItemImpl = (MenuItemImpl) r13_ArrayList.get(r10i);
                            if (r4_MenuItemImpl.getGroupId() == r17i) {
                                if (r4_MenuItemImpl.isActionButton()) {
                                    r6i++;
                                }
                                r4_MenuItemImpl.setIsActionButton(false);
                            }
                            r10i++;
                        }
                        r4i = r6i;
                    } else {
                        r4i = r10i;
                    }
                } else {
                    r16_SparseBooleanArray.put(r17i, true);
                    r4i = r10i;
                }
                if (r11z) {
                    r4i--;
                }
                r3_MenuItemImpl.setIsActionButton(r11z);
                r6i = r8i;
                r3i = r9i;
                r7i = r4i;
                r4i = r7i;
            } else {
                r6i = r7i;
                r3i = r9i;
                r7i = r10i;
            }
            r12i++;
            r9i = r3i;
            r10i = r7i;
            r7i = r6i;
        }
        return true;
    }

    public View getItemView(MenuItemImpl r4_MenuItemImpl, View r5_View, ViewGroup r6_ViewGroup) {
        View r0_View = r4_MenuItemImpl.getActionView();
        ActionMenuView r6_ActionMenuView;
        LayoutParams r1_LayoutParams;
        if (r0_View == null || r4_MenuItemImpl.hasCollapsibleActionView()) {
            if (!r5_View instanceof ActionMenuItemView) {
                r5_View = null;
            }
            r0_View = super.getItemView(r4_MenuItemImpl, r5_View, r6_ViewGroup);
            r0_View.setVisibility(r4_MenuItemImpl.isActionViewExpanded() ? 0 : Base64.DONT_BREAK_LINES);
            r6_ActionMenuView = (ActionMenuView) r6_ViewGroup;
            r1_LayoutParams = r0_View.getLayoutParams();
            if (r6_ActionMenuView.checkLayoutParams(r1_LayoutParams)) {
                r0_View.setLayoutParams(r6_ActionMenuView.a(r1_LayoutParams));
            }
            return r0_View;
        } else {
            if (r4_MenuItemImpl.isActionViewExpanded()) {
            }
            r0_View.setVisibility(r4_MenuItemImpl.isActionViewExpanded() ? 0 : Base64.DONT_BREAK_LINES);
            r6_ActionMenuView = (ActionMenuView) r6_ViewGroup;
            r1_LayoutParams = r0_View.getLayoutParams();
            if (r6_ActionMenuView.checkLayoutParams(r1_LayoutParams)) {
                return r0_View;
            }
            r0_View.setLayoutParams(r6_ActionMenuView.a(r1_LayoutParams));
            return r0_View;
        }
    }

    public MenuView getMenuView(ViewGroup r3_ViewGroup) {
        MenuView r1_MenuView = super.getMenuView(r3_ViewGroup);
        ((ActionMenuView) r1_MenuView).setPresenter(this);
        return r1_MenuView;
    }

    public boolean hideOverflowMenu() {
        if (this.x == null || this.h == null) {
            MenuPopupHelper r0_MenuPopupHelper = this.v;
            if (r0_MenuPopupHelper == null) {
                return false;
            }
            r0_MenuPopupHelper.dismiss();
            return true;
        } else {
            ((View) this.h).removeCallbacks(this.x);
            this.x = null;
            return true;
        }
    }

    public boolean hideSubMenus() {
        if (this.w == null) {
            return false;
        }
        this.w.dismiss();
        return true;
    }

    public void initForMenu(Context r7_Context, MenuBuilder r8_MenuBuilder) {
        super.initForMenu(r7_Context, r8_MenuBuilder);
        Resources r1_Resources = r7_Context.getResources();
        ActionBarPolicy r0_ActionBarPolicy = ActionBarPolicy.get(r7_Context);
        if (!this.k) {
            this.j = r0_ActionBarPolicy.showsOverflowMenuButton();
        }
        if (!this.q) {
            this.l = r0_ActionBarPolicy.getEmbeddedMenuWidthLimit();
        }
        if (!this.o) {
            this.n = r0_ActionBarPolicy.getMaxActionButtons();
        }
        int r0i = this.l;
        if (this.j) {
            if (this.i == null) {
                this.i = new c(this.c);
                int r2i = MeasureSpec.makeMeasureSpec(0, 0);
                this.i.measure(r2i, r2i);
            }
            r0i -= this.i.getMeasuredWidth();
        } else {
            this.i = null;
        }
        this.m = r0i;
        this.s = (int) (56.0f * r1_Resources.getDisplayMetrics().density);
        this.u = null;
    }

    public boolean isOverflowMenuShowing() {
        return this.v != null && this.v.isShowing();
    }

    public boolean isOverflowReserved() {
        return this.j;
    }

    public void onCloseMenu(MenuBuilder r1_MenuBuilder, boolean r2z) {
        dismissPopupMenus();
        super.onCloseMenu(r1_MenuBuilder, r2z);
    }

    public void onConfigurationChanged(Configuration r3_Configuration) {
        if (!this.o) {
            this.n = this.d.getResources().getInteger(R.integer.abc_max_action_buttons);
        }
        if (this.e != null) {
            this.e.b(true);
        }
    }

    public void onRestoreInstanceState(Parcelable r3_Parcelable) {
        SavedState r3_SavedState = (SavedState) r3_Parcelable;
        if (r3_SavedState.openSubMenuId > 0) {
            MenuItem r0_MenuItem = this.e.findItem(r3_SavedState.openSubMenuId);
            if (r0_MenuItem != null) {
                onSubMenuSelected((SubMenuBuilder) r0_MenuItem.getSubMenu());
            }
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable r0_Parcelable = new SavedState();
        r0_Parcelable.openSubMenuId = this.b;
        return r0_Parcelable;
    }

    public boolean onSubMenuSelected(SubMenuBuilder r5_SubMenuBuilder) {
        if (!r5_SubMenuBuilder.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder r0_SubMenuBuilder = r5_SubMenuBuilder;
        while (r0_SubMenuBuilder.getParentMenu() != this.e) {
            r0_SubMenuBuilder = (SubMenuBuilder) r0_SubMenuBuilder.getParentMenu();
        }
        if (a(r0_SubMenuBuilder.getItem()) == null) {
            if (this.i == null) {
                return false;
            }
            View r0_View = this.i;
        }
        this.b = r5_SubMenuBuilder.getItem().getItemId();
        this.w = new a(r5_SubMenuBuilder);
        this.w.show(null);
        super.onSubMenuSelected(r5_SubMenuBuilder);
        return true;
    }

    public void onSubUiVisibilityChanged(boolean r3z) {
        if (r3z) {
            super.onSubMenuSelected(null);
        } else {
            this.e.a(false);
        }
    }

    public void setExpandedActionViewsExclusive(boolean r1z) {
        this.r = r1z;
    }

    public void setItemLimit(int r2i) {
        this.n = r2i;
        this.o = true;
    }

    public void setReserveOverflow(boolean r2z) {
        this.j = r2z;
        this.k = true;
    }

    public void setWidthLimit(int r2i, boolean r3z) {
        this.l = r2i;
        this.p = r3z;
        this.q = true;
    }

    public boolean shouldIncludeItem(int r2i, MenuItemImpl r3_MenuItemImpl) {
        return r3_MenuItemImpl.isActionButton();
    }

    public boolean showOverflowMenu() {
        if ((!this.j) || isOverflowMenuShowing() || this.e == null || this.h == null || this.x != null) {
            return false;
        }
        this.x = new b(new d(this.d, this.e, this.i, true));
        ((View) this.h).post(this.x);
        super.onSubMenuSelected(null);
        return true;
    }

    public void updateMenuView(boolean r7z) {
        int r1i = 1;
        int r2i = 0;
        super.updateMenuView(r7z);
        if (this.h == null) {
        } else {
            int r3i;
            ArrayList r0_ArrayList;
            if (this.e != null) {
                ArrayList r4_ArrayList = this.e.c();
                int r5i = r4_ArrayList.size();
                r3i = 0;
                while (r3i < r5i) {
                    ActionProvider r0_ActionProvider = ((MenuItemImpl) r4_ArrayList.get(r3i)).getSupportActionProvider();
                    if (r0_ActionProvider != null) {
                        r0_ActionProvider.setSubUiVisibilityListener(this);
                    }
                    r3i++;
                }
            }
            r0_ArrayList = this.e != null ? this.e.d() : null;
            ViewGroup r0_ViewGroup;
            ActionMenuView r0_ActionMenuView;
            if ((!this.j) || r0_ArrayList == null) {
                if (r2i == 0) {
                    if (this.i != null) {
                        this.i = new c(this.c);
                    }
                    r0_ViewGroup = (ViewGroup) this.i.getParent();
                    if (r0_ViewGroup == this.h) {
                        if (r0_ViewGroup == null) {
                            r0_ViewGroup.removeView(this.i);
                        }
                        r0_ActionMenuView = (ActionMenuView) this.h;
                        r0_ActionMenuView.addView(this.i, r0_ActionMenuView.generateOverflowButtonLayoutParams());
                    }
                } else if (this.i == null || this.i.getParent() != this.h) {
                    ((ActionMenuView) this.h).setOverflowReserved(this.j);
                } else {
                    ((ViewGroup) this.h).removeView(this.i);
                }
                ((ActionMenuView) this.h).setOverflowReserved(this.j);
            } else {
                r3i = r0_ArrayList.size();
                if (r3i == 1) {
                    r2i = ((MenuItemImpl) r0_ArrayList.get(0)).isActionViewExpanded() ? 0 : 1;
                    if (r2i == 0) {
                        if (this.i == null || this.i.getParent() != this.h) {
                            ((ActionMenuView) this.h).setOverflowReserved(this.j);
                        } else {
                            ((ViewGroup) this.h).removeView(this.i);
                        }
                    } else if (this.i != null) {
                        r0_ViewGroup = (ViewGroup) this.i.getParent();
                        if (r0_ViewGroup == this.h) {
                            ((ActionMenuView) this.h).setOverflowReserved(this.j);
                        } else if (r0_ViewGroup == null) {
                            r0_ActionMenuView = (ActionMenuView) this.h;
                            r0_ActionMenuView.addView(this.i, r0_ActionMenuView.generateOverflowButtonLayoutParams());
                        } else {
                            r0_ViewGroup.removeView(this.i);
                            r0_ActionMenuView = (ActionMenuView) this.h;
                            r0_ActionMenuView.addView(this.i, r0_ActionMenuView.generateOverflowButtonLayoutParams());
                        }
                    } else {
                        this.i = new c(this.c);
                        r0_ViewGroup = (ViewGroup) this.i.getParent();
                        if (r0_ViewGroup == this.h) {
                            if (r0_ViewGroup == null) {
                                r0_ViewGroup.removeView(this.i);
                            }
                            r0_ActionMenuView = (ActionMenuView) this.h;
                            r0_ActionMenuView.addView(this.i, r0_ActionMenuView.generateOverflowButtonLayoutParams());
                        }
                    }
                    ((ActionMenuView) this.h).setOverflowReserved(this.j);
                } else if (r3i > 0) {
                    r2i = r1i;
                    if (r2i == 0) {
                        if (this.i != null) {
                            this.i = new c(this.c);
                        }
                        r0_ViewGroup = (ViewGroup) this.i.getParent();
                        if (r0_ViewGroup == this.h) {
                            ((ActionMenuView) this.h).setOverflowReserved(this.j);
                        } else if (r0_ViewGroup == null) {
                            r0_ActionMenuView = (ActionMenuView) this.h;
                            r0_ActionMenuView.addView(this.i, r0_ActionMenuView.generateOverflowButtonLayoutParams());
                        } else {
                            r0_ViewGroup.removeView(this.i);
                            r0_ActionMenuView = (ActionMenuView) this.h;
                            r0_ActionMenuView.addView(this.i, r0_ActionMenuView.generateOverflowButtonLayoutParams());
                        }
                    } else if (this.i == null || this.i.getParent() != this.h) {
                        ((ActionMenuView) this.h).setOverflowReserved(this.j);
                    } else {
                        ((ViewGroup) this.h).removeView(this.i);
                    }
                    ((ActionMenuView) this.h).setOverflowReserved(this.j);
                } else {
                    r1i = 0;
                    r2i = r1i;
                    if (r2i == 0) {
                        if (this.i == null || this.i.getParent() != this.h) {
                            ((ActionMenuView) this.h).setOverflowReserved(this.j);
                        } else {
                            ((ViewGroup) this.h).removeView(this.i);
                        }
                    } else if (this.i != null) {
                        r0_ViewGroup = (ViewGroup) this.i.getParent();
                        if (r0_ViewGroup == this.h) {
                            if (r0_ViewGroup == null) {
                                r0_ViewGroup.removeView(this.i);
                            }
                            r0_ActionMenuView = (ActionMenuView) this.h;
                            r0_ActionMenuView.addView(this.i, r0_ActionMenuView.generateOverflowButtonLayoutParams());
                        }
                    } else {
                        this.i = new c(this.c);
                        r0_ViewGroup = (ViewGroup) this.i.getParent();
                        if (r0_ViewGroup == this.h) {
                            ((ActionMenuView) this.h).setOverflowReserved(this.j);
                        } else if (r0_ViewGroup == null) {
                            r0_ActionMenuView = (ActionMenuView) this.h;
                            r0_ActionMenuView.addView(this.i, r0_ActionMenuView.generateOverflowButtonLayoutParams());
                        } else {
                            r0_ViewGroup.removeView(this.i);
                            r0_ActionMenuView = (ActionMenuView) this.h;
                            r0_ActionMenuView.addView(this.i, r0_ActionMenuView.generateOverflowButtonLayoutParams());
                        }
                    }
                    ((ActionMenuView) this.h).setOverflowReserved(this.j);
                }
            }
        }
    }
}