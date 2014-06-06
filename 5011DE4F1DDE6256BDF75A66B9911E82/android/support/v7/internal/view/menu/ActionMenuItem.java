package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.MenuItemCompat;
import android.view.ActionProvider;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class ActionMenuItem implements SupportMenuItem {
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
    private Context l;
    private OnMenuItemClickListener m;
    private int n;

    public ActionMenuItem(Context r2_Context, int r3i, int r4i, int r5i, int r6i, CharSequence r7_CharSequence) {
        this.k = 0;
        this.n = 16;
        this.l = r2_Context;
        this.a = r4i;
        this.b = r3i;
        this.c = r5i;
        this.d = r6i;
        this.e = r7_CharSequence;
    }

    public boolean collapseActionView() {
        return false;
    }

    public boolean expandActionView() {
        return false;
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    public View getActionView() {
        return null;
    }

    public char getAlphabeticShortcut() {
        return this.i;
    }

    public int getGroupId() {
        return this.b;
    }

    public Drawable getIcon() {
        return this.j;
    }

    public Intent getIntent() {
        return this.g;
    }

    public int getItemId() {
        return this.a;
    }

    public ContextMenuInfo getMenuInfo() {
        return null;
    }

    public char getNumericShortcut() {
        return this.h;
    }

    public int getOrder() {
        return this.d;
    }

    public SubMenu getSubMenu() {
        return null;
    }

    public android.support.v4.view.ActionProvider getSupportActionProvider() {
        return null;
    }

    public CharSequence getTitle() {
        return this.e;
    }

    public CharSequence getTitleCondensed() {
        return this.f;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean invoke() {
        if (this.m != null && this.m.onMenuItemClick(this)) {
            return true;
        }
        if (this.g == null) {
            return false;
        }
        this.l.startActivity(this.g);
        return true;
    }

    public boolean isActionViewExpanded() {
        return false;
    }

    public boolean isCheckable() {
        return (this.n & 1) != 0;
    }

    public boolean isChecked() {
        return (this.n & 2) != 0;
    }

    public boolean isEnabled() {
        return (this.n & 16) != 0;
    }

    public boolean isVisible() {
        return (this.n & 8) == 0;
    }

    public MenuItem setActionProvider(ActionProvider r2_ActionProvider) {
        throw new UnsupportedOperationException();
    }

    public SupportMenuItem setActionView(int r2i) {
        throw new UnsupportedOperationException();
    }

    public SupportMenuItem setActionView(View r2_View) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setAlphabeticShortcut(char r1c) {
        this.i = r1c;
        return this;
    }

    public MenuItem setCheckable(boolean r3z) {
        this.n = (r3z ? 1 : 0) | (this.n & -2);
        return this;
    }

    public MenuItem setChecked(boolean r3z) {
        this.n = (r3z ? XListViewHeader.STATE_REFRESHING : 0) | (this.n & -3);
        return this;
    }

    public MenuItem setEnabled(boolean r3z) {
        this.n = (r3z ? Base64.URL_SAFE : 0) | (this.n & -17);
        return this;
    }

    public ActionMenuItem setExclusiveCheckable(boolean r3z) {
        this.n = (r3z ? XListViewFooter.STATE_NODATA : 0) | (this.n & -5);
        return this;
    }

    public MenuItem setIcon(int r2i) {
        this.k = r2i;
        this.j = this.l.getResources().getDrawable(r2i);
        return this;
    }

    public MenuItem setIcon(Drawable r2_Drawable) {
        this.j = r2_Drawable;
        this.k = 0;
        return this;
    }

    public MenuItem setIntent(Intent r1_Intent) {
        this.g = r1_Intent;
        return this;
    }

    public MenuItem setNumericShortcut(char r1c) {
        this.h = r1c;
        return this;
    }

    public MenuItem setOnActionExpandListener(OnActionExpandListener r2_OnActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener r1_OnMenuItemClickListener) {
        this.m = r1_OnMenuItemClickListener;
        return this;
    }

    public MenuItem setShortcut(char r1c, char r2c) {
        this.h = r1c;
        this.i = r2c;
        return this;
    }

    public void setShowAsAction(int r1i) {
    }

    public SupportMenuItem setShowAsActionFlags(int r1i) {
        setShowAsAction(r1i);
        return this;
    }

    public SupportMenuItem setSupportActionProvider(android.support.v4.view.ActionProvider r2_android_support_v4_view_ActionProvider) {
        throw new UnsupportedOperationException();
    }

    public SupportMenuItem setSupportOnActionExpandListener(MenuItemCompat.OnActionExpandListener r1_MenuItemCompat_OnActionExpandListener) {
        return this;
    }

    public MenuItem setTitle(int r2i) {
        this.e = this.l.getResources().getString(r2i);
        return this;
    }

    public MenuItem setTitle(CharSequence r1_CharSequence) {
        this.e = r1_CharSequence;
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence r1_CharSequence) {
        this.f = r1_CharSequence;
        return this;
    }

    public MenuItem setVisible(boolean r3z) {
        this.n = (r3z ? 0 : Base64.DONT_BREAK_LINES) | (this.n & 8);
        return this;
    }
}