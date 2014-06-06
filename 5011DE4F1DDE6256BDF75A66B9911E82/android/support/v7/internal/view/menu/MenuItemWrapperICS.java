package android.support.v7.internal.view.menu;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.util.Log;
import android.view.ActionProvider;
import android.view.CollapsibleActionView;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

public class MenuItemWrapperICS extends b<MenuItem> implements SupportMenuItem {
    private final boolean b;
    private boolean c;
    private Method d;

    class a extends ActionProvider {
        final android.support.v4.view.ActionProvider a;

        public a(android.support.v4.view.ActionProvider r4_android_support_v4_view_ActionProvider) {
            super(r4_android_support_v4_view_ActionProvider.getContext());
            this.a = r4_android_support_v4_view_ActionProvider;
            if (MenuItemWrapperICS.this) {
                this.a.setVisibilityListener(new e(this, MenuItemWrapperICS.this));
            }
        }

        public boolean hasSubMenu() {
            return this.a.hasSubMenu();
        }

        public View onCreateActionView() {
            if (MenuItemWrapperICS.this) {
                MenuItemWrapperICS.this.b();
            }
            return this.a.onCreateActionView();
        }

        public boolean onPerformDefaultAction() {
            return this.a.onPerformDefaultAction();
        }

        public void onPrepareSubMenu(SubMenu r3_SubMenu) {
            this.a.onPrepareSubMenu(MenuItemWrapperICS.this.a(r3_SubMenu));
        }
    }

    static class b extends FrameLayout implements CollapsibleActionView {
        final android.support.v7.view.CollapsibleActionView a;

        b(View r2_View) {
            super(r2_View.getContext());
            this.a = (android.support.v7.view.CollapsibleActionView) r2_View;
            addView(r2_View);
        }

        View a() {
            return (View) this.a;
        }

        public void onActionViewCollapsed() {
            this.a.onActionViewCollapsed();
        }

        public void onActionViewExpanded() {
            this.a.onActionViewExpanded();
        }
    }

    private class c extends c<OnActionExpandListener> implements MenuItem.OnActionExpandListener {
        c(OnActionExpandListener r2_OnActionExpandListener) {
            super(r2_OnActionExpandListener);
        }

        public boolean onMenuItemActionCollapse(MenuItem r3_MenuItem) {
            return ((OnActionExpandListener) this.a).onMenuItemActionCollapse(MenuItemWrapperICS.this.a(r3_MenuItem));
        }

        public boolean onMenuItemActionExpand(MenuItem r3_MenuItem) {
            return ((OnActionExpandListener) this.a).onMenuItemActionExpand(MenuItemWrapperICS.this.a(r3_MenuItem));
        }
    }

    private class d extends c<OnMenuItemClickListener> implements OnMenuItemClickListener {
        d(OnMenuItemClickListener r2_OnMenuItemClickListener) {
            super(r2_OnMenuItemClickListener);
        }

        public boolean onMenuItemClick(MenuItem r3_MenuItem) {
            return ((OnMenuItemClickListener) this.a).onMenuItemClick(MenuItemWrapperICS.this.a(r3_MenuItem));
        }
    }

    MenuItemWrapperICS(MenuItem r2_MenuItem) {
        this(r2_MenuItem, true);
    }

    MenuItemWrapperICS(MenuItem r2_MenuItem, boolean r3z) {
        super(r2_MenuItem);
        this.c = r2_MenuItem.isVisible();
        this.b = r3z;
    }

    a a(android.support.v4.view.ActionProvider r2_android_support_v4_view_ActionProvider) {
        return new a(r2_android_support_v4_view_ActionProvider);
    }

    final MenuItem a(boolean r2z) {
        return ((MenuItem) this.a).setVisible(r2z);
    }

    final boolean b() {
        boolean r0z = false;
        if (!(this.c)) {
            return false;
        }
        android.support.v4.view.ActionProvider r1_android_support_v4_view_ActionProvider = getSupportActionProvider();
        if (r1_android_support_v4_view_ActionProvider == null || (!r1_android_support_v4_view_ActionProvider.overridesItemVisibility()) || r1_android_support_v4_view_ActionProvider.isVisible()) {
            return false;
        }
        a(r0z);
        return true;
    }

    public boolean collapseActionView() {
        return ((MenuItem) this.a).collapseActionView();
    }

    public boolean expandActionView() {
        return ((MenuItem) this.a).expandActionView();
    }

    public ActionProvider getActionProvider() {
        return ((MenuItem) this.a).getActionProvider();
    }

    public View getActionView() {
        View r0_View = ((MenuItem) this.a).getActionView();
        return r0_View instanceof b ? ((b) r0_View).a() : r0_View;
    }

    public char getAlphabeticShortcut() {
        return ((MenuItem) this.a).getAlphabeticShortcut();
    }

    public int getGroupId() {
        return ((MenuItem) this.a).getGroupId();
    }

    public Drawable getIcon() {
        return ((MenuItem) this.a).getIcon();
    }

    public Intent getIntent() {
        return ((MenuItem) this.a).getIntent();
    }

    public int getItemId() {
        return ((MenuItem) this.a).getItemId();
    }

    public ContextMenuInfo getMenuInfo() {
        return ((MenuItem) this.a).getMenuInfo();
    }

    public char getNumericShortcut() {
        return ((MenuItem) this.a).getNumericShortcut();
    }

    public int getOrder() {
        return ((MenuItem) this.a).getOrder();
    }

    public SubMenu getSubMenu() {
        return a(((MenuItem) this.a).getSubMenu());
    }

    public android.support.v4.view.ActionProvider getSupportActionProvider() {
        a r0_a = (a) ((MenuItem) this.a).getActionProvider();
        return r0_a != null ? r0_a.a : null;
    }

    public CharSequence getTitle() {
        return ((MenuItem) this.a).getTitle();
    }

    public CharSequence getTitleCondensed() {
        return ((MenuItem) this.a).getTitleCondensed();
    }

    public boolean hasSubMenu() {
        return ((MenuItem) this.a).hasSubMenu();
    }

    public boolean isActionViewExpanded() {
        return ((MenuItem) this.a).isActionViewExpanded();
    }

    public boolean isCheckable() {
        return ((MenuItem) this.a).isCheckable();
    }

    public boolean isChecked() {
        return ((MenuItem) this.a).isChecked();
    }

    public boolean isEnabled() {
        return ((MenuItem) this.a).isEnabled();
    }

    public boolean isVisible() {
        return ((MenuItem) this.a).isVisible();
    }

    public MenuItem setActionProvider(ActionProvider r2_ActionProvider) {
        ((MenuItem) this.a).setActionProvider(r2_ActionProvider);
        if (r2_ActionProvider == null || (!this.b)) {
            return this;
        }
        b();
        return this;
    }

    public MenuItem setActionView(int r4i) {
        ((MenuItem) this.a).setActionView(r4i);
        View r1_View = ((MenuItem) this.a).getActionView();
        if (r1_View instanceof android.support.v7.view.CollapsibleActionView) {
            ((MenuItem) this.a).setActionView(new b(r1_View));
        }
        return this;
    }

    public MenuItem setActionView(View r2_View) {
        if (r2_View instanceof android.support.v7.view.CollapsibleActionView) {
            r2_View = new b(r2_View);
        }
        ((MenuItem) this.a).setActionView(r2_View);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char r2c) {
        ((MenuItem) this.a).setAlphabeticShortcut(r2c);
        return this;
    }

    public MenuItem setCheckable(boolean r2z) {
        ((MenuItem) this.a).setCheckable(r2z);
        return this;
    }

    public MenuItem setChecked(boolean r2z) {
        ((MenuItem) this.a).setChecked(r2z);
        return this;
    }

    public MenuItem setEnabled(boolean r2z) {
        ((MenuItem) this.a).setEnabled(r2z);
        return this;
    }

    public void setExclusiveCheckable(boolean r6z) {
        try {
            if (this.d == null) {
                Class r0_Class = ((MenuItem) this.a).getClass();
                Class[] r2_ClassA = new Class[1];
                r2_ClassA[0] = Boolean.TYPE;
                this.d = r0_Class.getDeclaredMethod("setExclusiveCheckable", r2_ClassA);
            }
            Method r0_Method = this.d;
            Object r1_Object = this.a;
            Object[] r2_ObjectA = new Object[1];
            r2_ObjectA[0] = Boolean.valueOf(r6z);
            r0_Method.invoke(r1_Object, r2_ObjectA);
        } catch (Exception e) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e);
        }
    }

    public MenuItem setIcon(int r2i) {
        ((MenuItem) this.a).setIcon(r2i);
        return this;
    }

    public MenuItem setIcon(Drawable r2_Drawable) {
        ((MenuItem) this.a).setIcon(r2_Drawable);
        return this;
    }

    public MenuItem setIntent(Intent r2_Intent) {
        ((MenuItem) this.a).setIntent(r2_Intent);
        return this;
    }

    public MenuItem setNumericShortcut(char r2c) {
        ((MenuItem) this.a).setNumericShortcut(r2c);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener r2_MenuItem_OnActionExpandListener) {
        ((MenuItem) this.a).setOnActionExpandListener(r2_MenuItem_OnActionExpandListener);
        return this;
    }

    public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener r3_OnMenuItemClickListener) {
        ((MenuItem) this.a).setOnMenuItemClickListener(r3_OnMenuItemClickListener != null ? new d(r3_OnMenuItemClickListener) : null);
        return this;
    }

    public MenuItem setShortcut(char r2c, char r3c) {
        ((MenuItem) this.a).setShortcut(r2c, r3c);
        return this;
    }

    public void setShowAsAction(int r2i) {
        ((MenuItem) this.a).setShowAsAction(r2i);
    }

    public MenuItem setShowAsActionFlags(int r2i) {
        ((MenuItem) this.a).setShowAsActionFlags(r2i);
        return this;
    }

    public SupportMenuItem setSupportActionProvider(android.support.v4.view.ActionProvider r3_android_support_v4_view_ActionProvider) {
        ((MenuItem) this.a).setActionProvider(r3_android_support_v4_view_ActionProvider != null ? a(r3_android_support_v4_view_ActionProvider) : null);
        return this;
    }

    public SupportMenuItem setSupportOnActionExpandListener(OnActionExpandListener r4_OnActionExpandListener) {
        ((MenuItem) this.a).setOnActionExpandListener(r4_OnActionExpandListener != null ? new c(r4_OnActionExpandListener) : null);
        return null;
    }

    public MenuItem setTitle(int r2i) {
        ((MenuItem) this.a).setTitle(r2i);
        return this;
    }

    public MenuItem setTitle(CharSequence r2_CharSequence) {
        ((MenuItem) this.a).setTitle(r2_CharSequence);
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence r2_CharSequence) {
        ((MenuItem) this.a).setTitleCondensed(r2_CharSequence);
        return this;
    }

    public MenuItem setVisible(boolean r2z) {
        if (this.b) {
            this.c = r2z;
            if (b()) {
                return this;
            }
        }
        return a(r2z);
    }
}