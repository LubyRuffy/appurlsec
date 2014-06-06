package android.support.v7.internal.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.internal.view.menu.MenuView.ItemView;
import android.util.Log;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.LinearLayout;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public final class MenuItemImpl implements SupportMenuItem {
    private static String w;
    private static String x;
    private static String y;
    private static String z;
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private CharSequence e;
    private CharSequence f;
    private Intent g;
    private char h;
    private char i;
    private Drawable j;
    private int k;
    private MenuBuilder l;
    private SubMenuBuilder m;
    private Runnable n;
    private OnMenuItemClickListener o;
    private int p;
    private int q;
    private View r;
    private ActionProvider s;
    private OnActionExpandListener t;
    private boolean u;
    private ContextMenuInfo v;

    MenuItemImpl(MenuBuilder r3_MenuBuilder, int r4i, int r5i, int r6i, int r7i, CharSequence r8_CharSequence, int r9i) {
        this.k = 0;
        this.p = 16;
        this.q = 0;
        this.u = false;
        this.l = r3_MenuBuilder;
        this.a = r5i;
        this.b = r4i;
        this.c = r6i;
        this.d = r7i;
        this.e = r8_CharSequence;
        this.q = r9i;
    }

    char a() {
        return this.i;
    }

    CharSequence a(ItemView r2_ItemView) {
        return (r2_ItemView == null || (!r2_ItemView.prefersCondensedTitle())) ? getTitle() : getTitleCondensed();
    }

    void a(SubMenuBuilder r2_SubMenuBuilder) {
        this.m = r2_SubMenuBuilder;
        r2_SubMenuBuilder.setHeaderTitle(getTitle());
    }

    void a(ContextMenuInfo r1_ContextMenuInfo) {
        this.v = r1_ContextMenuInfo;
    }

    void a(boolean r5z) {
        int r2i = this.p;
        this.p = (r5z ? XListViewHeader.STATE_REFRESHING : 0) | (this.p & -3);
        if (r2i != this.p) {
            this.l.b(false);
        }
    }

    public void actionFormatChanged() {
        this.l.b(this);
    }

    String b() {
        char r0c = a();
        if (r0c == '\u0000') {
            return RContactStorage.PRIMARY_KEY;
        }
        StringBuilder r1_StringBuilder = new StringBuilder(w);
        switch (r0c) {
            case Base64.DONT_BREAK_LINES:
                r1_StringBuilder.append(y);
                return r1_StringBuilder.toString();
            case REQUEST_CODE.REQUEST_CODE_EDIT_INTRO:
                r1_StringBuilder.append(x);
                return r1_StringBuilder.toString();
            case Base64.ORDERED:
                r1_StringBuilder.append(z);
                return r1_StringBuilder.toString();
        }
        r1_StringBuilder.append(r0c);
        return r1_StringBuilder.toString();
    }

    boolean b(boolean r5z) {
        int r2i = this.p;
        this.p = (r5z ? 0 : Base64.DONT_BREAK_LINES) | (this.p & -9);
        return r2i != this.p;
    }

    boolean c() {
        return this.l.isShortcutsVisible() && a() != '\u0000';
    }

    public boolean collapseActionView() {
        if ((this.q & 8) == 0) {
            return false;
        }
        if (this.r == null) {
            return true;
        }
        if (this.t == null || this.t.onMenuItemActionCollapse(this)) {
            return this.l.collapseItemActionView(this);
        }
        return false;
    }

    public boolean expandActionView() {
        if ((this.q & 8) == 0 || this.r == null) {
            return false;
        }
        if (this.t == null || this.t.onMenuItemActionExpand(this)) {
            return this.l.expandItemActionView(this);
        }
        return false;
    }

    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("Implementation should use getSupportActionProvider!");
    }

    public View getActionView() {
        if (this.r != null) {
            return this.r;
        }
        if (this.s == null) {
            return null;
        }
        this.r = this.s.onCreateActionView(this);
        return this.r;
    }

    public char getAlphabeticShortcut() {
        return this.i;
    }

    public int getGroupId() {
        return this.b;
    }

    public Drawable getIcon() {
        if (this.j != null) {
            return this.j;
        }
        if (this.k == 0) {
            return null;
        }
        Drawable r0_Drawable = this.l.a().getDrawable(this.k);
        this.k = 0;
        this.j = r0_Drawable;
        return r0_Drawable;
    }

    public Intent getIntent() {
        return this.g;
    }

    public int getItemId() {
        return this.a;
    }

    public ContextMenuInfo getMenuInfo() {
        return this.v;
    }

    public char getNumericShortcut() {
        return this.h;
    }

    public int getOrder() {
        return this.c;
    }

    public int getOrdering() {
        return this.d;
    }

    public SubMenu getSubMenu() {
        return this.m;
    }

    public ActionProvider getSupportActionProvider() {
        return this.s;
    }

    public CharSequence getTitle() {
        return this.e;
    }

    public CharSequence getTitleCondensed() {
        return this.f != null ? this.f : this.e;
    }

    public boolean hasCollapsibleActionView() {
        return (this.q & 8) != 0 && this.r != null;
    }

    public boolean hasSubMenu() {
        return this.m != null;
    }

    public boolean invoke() {
        if ((this.o != null && this.o.onMenuItemClick(this)) || this.l.dispatchMenuItemSelected(this.l.getRootMenu(), this)) {
            return true;
        }
        if (this.n != null) {
            this.n.run();
            return true;
        } else {
            if (this.g != null) {
                try {
                    this.l.getContext().startActivity(this.g);
                    return true;
                } catch (ActivityNotFoundException e) {
                    Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e);
                }
            }
            return this.s != null && this.s.onPerformDefaultAction();
        }
    }

    public boolean isActionButton() {
        return (this.p & 32) == 32;
    }

    public boolean isActionViewExpanded() {
        return this.u;
    }

    public boolean isCheckable() {
        return (this.p & 1) == 1;
    }

    public boolean isChecked() {
        return (this.p & 2) == 2;
    }

    public boolean isEnabled() {
        return (this.p & 16) != 0;
    }

    public boolean isExclusiveCheckable() {
        return (this.p & 4) != 0;
    }

    public boolean isVisible() {
        if (this.s == null || (!this.s.overridesItemVisibility())) {
            return (this.p & 8) == 0;
        } else {
            if ((this.p & 8) == 0 && this.s.isVisible()) {
                return true;
            }
            return false;
        }
    }

    public boolean requestsActionButton() {
        return (this.q & 1) == 1;
    }

    public boolean requiresActionButton() {
        return (this.q & 2) == 2;
    }

    public MenuItem setActionProvider(android.view.ActionProvider r3_android_view_ActionProvider) {
        throw new UnsupportedOperationException("Implementation should use setSupportActionProvider!");
    }

    public SupportMenuItem setActionView(int r4i) {
        Context r0_Context = this.l.getContext();
        setActionView(LayoutInflater.from(r0_Context).inflate(r4i, new LinearLayout(r0_Context), false));
        return this;
    }

    public SupportMenuItem setActionView(View r3_View) {
        this.r = r3_View;
        this.s = null;
        if (r3_View == null || r3_View.getId() != -1 || this.a <= 0) {
            this.l.b(this);
            return this;
        } else {
            r3_View.setId(this.a);
            this.l.b(this);
            return this;
        }
    }

    public void setActionViewExpanded(boolean r3z) {
        this.u = r3z;
        this.l.b(false);
    }

    public MenuItem setAlphabeticShortcut(char r3c) {
        if (this.i == r3c) {
            return this;
        }
        this.i = Character.toLowerCase(r3c);
        this.l.b(false);
        return this;
    }

    public MenuItem setCallback(Runnable r1_Runnable) {
        this.n = r1_Runnable;
        return this;
    }

    public MenuItem setCheckable(boolean r5z) {
        int r2i = this.p;
        this.p = (r5z ? 1 : 0) | (this.p & -2);
        if (r2i != this.p) {
            this.l.b(false);
        }
        return this;
    }

    public MenuItem setChecked(boolean r2z) {
        if ((this.p & 4) != 0) {
            this.l.a((MenuItem)this);
        } else {
            a(r2z);
        }
        return this;
    }

    public MenuItem setEnabled(boolean r3z) {
        if (r3z) {
            this.p |= 16;
        } else {
            this.p &= -17;
        }
        this.l.b(false);
        return this;
    }

    public void setExclusiveCheckable(boolean r3z) {
        this.p = (r3z ? XListViewFooter.STATE_NODATA : 0) | (this.p & -5);
    }

    public MenuItem setIcon(int r3i) {
        this.j = null;
        this.k = r3i;
        this.l.b(false);
        return this;
    }

    public MenuItem setIcon(Drawable r3_Drawable) {
        this.k = 0;
        this.j = r3_Drawable;
        this.l.b(false);
        return this;
    }

    public MenuItem setIntent(Intent r1_Intent) {
        this.g = r1_Intent;
        return this;
    }

    public void setIsActionButton(boolean r2z) {
        if (r2z) {
            this.p |= 32;
        } else {
            this.p &= -33;
        }
    }

    public MenuItem setNumericShortcut(char r3c) {
        if (this.h == r3c) {
            return this;
        }
        this.h = r3c;
        this.l.b(false);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener r3_MenuItem_OnActionExpandListener) {
        throw new UnsupportedOperationException("Implementation should use setSupportOnActionExpandListener!");
    }

    public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener r1_OnMenuItemClickListener) {
        this.o = r1_OnMenuItemClickListener;
        return this;
    }

    public MenuItem setShortcut(char r3c, char r4c) {
        this.h = r3c;
        this.i = Character.toLowerCase(r4c);
        this.l.b(false);
        return this;
    }

    public void setShowAsAction(int r3i) {
        switch ((r3i & 3)) {
            case XListViewHeader.STATE_NORMAL:
            case XListViewHeader.STATE_READY:
            case XListViewHeader.STATE_REFRESHING:
                this.q = r3i;
                this.l.b(this);
                return;
        }
        throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
    }

    public SupportMenuItem setShowAsActionFlags(int r1i) {
        setShowAsAction(r1i);
        return this;
    }

    public SupportMenuItem setSupportActionProvider(ActionProvider r3_ActionProvider) {
        if (this.s == r3_ActionProvider) {
            return this;
        }
        this.r = null;
        if (this.s != null) {
            this.s.setVisibilityListener(null);
        }
        this.s = r3_ActionProvider;
        this.l.b(true);
        if (r3_ActionProvider != null) {
            r3_ActionProvider.setVisibilityListener(new d(this));
        }
        return this;
    }

    public SupportMenuItem setSupportOnActionExpandListener(OnActionExpandListener r1_OnActionExpandListener) {
        this.t = r1_OnActionExpandListener;
        return this;
    }

    public MenuItem setTitle(int r2i) {
        return setTitle(this.l.getContext().getString(r2i));
    }

    public MenuItem setTitle(CharSequence r3_CharSequence) {
        this.e = r3_CharSequence;
        this.l.b(false);
        if (this.m != null) {
            this.m.setHeaderTitle(r3_CharSequence);
        }
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence r3_CharSequence) {
        this.f = r3_CharSequence;
        if (r3_CharSequence == null) {
            CharSequence r0_CharSequence = this.e;
        }
        this.l.b(false);
        return this;
    }

    public MenuItem setVisible(boolean r2z) {
        if (b(r2z)) {
            this.l.a(this);
        }
        return this;
    }

    public boolean shouldShowIcon() {
        return this.l.e();
    }

    public boolean showsTextAsAction() {
        return (this.q & 4) == 4;
    }

    public String toString() {
        return this.e.toString();
    }
}