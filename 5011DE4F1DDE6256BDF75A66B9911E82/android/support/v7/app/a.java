package android.support.v7.app;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle.Delegate;
import android.support.v4.app.NavUtils;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

// compiled from: ActionBarActivityDelegate.java
abstract class a {
    final ActionBarActivity a;
    boolean b;
    boolean c;
    private ActionBar d;
    private MenuInflater e;
    private boolean f;

    a(ActionBarActivity r1_ActionBarActivity) {
        this.a = r1_ActionBarActivity;
    }

    static a a_(ActionBarActivity r2_ActionBarActivity) {
        int r0i = VERSION.SDK_INT;
        if (r0i >= 16) {
            return new f(r2_ActionBarActivity);
        }
        if (r0i >= 14) {
            return new e(r2_ActionBarActivity);
        }
        if (r0i >= 11) {
            return new d(r2_ActionBarActivity);
        }
        return new b(r2_ActionBarActivity);
    }

    final ActionBar a_() {
        if (this.b || this.c) {
            if (this.d == null) {
                this.d = createSupportActionBar();
                if (this.f) {
                    this.d.setDisplayHomeAsUpEnabled(true);
                }
            }
        } else {
            this.d = null;
        }
        return this.d;
    }

    abstract void a_(int r1i);

    abstract void a_(boolean r1z);

    boolean a_(View r3_View, Menu r4_Menu) {
        return VERSION.SDK_INT < 16 ? this.a.onPrepareOptionsMenu(r4_Menu) : this.a.b(r3_View, r4_Menu);
    }

    abstract void addContentView(View r1_View, LayoutParams r2_LayoutParams);

    MenuInflater b() {
        if (this.e == null) {
            ActionBar r0_ActionBar = a();
            if (r0_ActionBar != null) {
                this.e = new SupportMenuInflater(r0_ActionBar.getThemedContext());
            } else {
                this.e = new SupportMenuInflater(this.a);
            }
        }
        return this.e;
    }

    abstract void b(boolean r1z);

    protected final String c() {
        try {
            ActivityInfo r1_ActivityInfo = this.a.getPackageManager().getActivityInfo(this.a.getComponentName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            return r1_ActivityInfo.metaData != null ? r1_ActivityInfo.metaData.getString("android.support.UI_OPTIONS") : null;
        } catch (NameNotFoundException e) {
            Log.e("ActionBarActivityDelegate", "getUiOptionsFromMetadata: Activity '" + this.a.getClass().getSimpleName() + "' not in manifest");
            return null;
        }
    }

    abstract void c(boolean r1z);

    abstract ActionBar createSupportActionBar();

    protected final Context d() {
        Context r0_Context = this.a;
        ActionBar r1_ActionBar = a();
        return r1_ActionBar != null ? r1_ActionBar.getThemedContext() : r0_Context;
    }

    abstract Delegate getDrawerToggleDelegate();

    abstract boolean onBackPressed();

    abstract void onConfigurationChanged(Configuration r1_Configuration);

    abstract void onContentChanged();

    void onCreate(Bundle r5_Bundle) {
        TypedArray r0_TypedArray = this.a.obtainStyledAttributes(R.styleable.ActionBarWindow);
        if (r0_TypedArray.hasValue(0)) {
            this.b = r0_TypedArray.getBoolean(0, false);
            this.c = r0_TypedArray.getBoolean(1, false);
            r0_TypedArray.recycle();
            if (NavUtils.getParentActivityName(this.a) != null) {
                if (this.d == null) {
                    this.f = true;
                } else {
                    this.d.setDisplayHomeAsUpEnabled(true);
                }
            }
        } else {
            r0_TypedArray.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
    }

    abstract boolean onCreatePanelMenu(int r1i, Menu r2_Menu);

    abstract View onCreatePanelView(int r1i);

    abstract boolean onMenuItemSelected(int r1i, MenuItem r2_MenuItem);

    abstract void onPostResume();

    abstract boolean onPreparePanel(int r1i, View r2_View, Menu r3_Menu);

    abstract void onStop();

    abstract void onTitleChanged(CharSequence r1_CharSequence);

    abstract void setContentView(int r1i);

    abstract void setContentView(View r1_View);

    abstract void setContentView(View r1_View, LayoutParams r2_LayoutParams);

    abstract ActionMode startSupportActionMode(Callback r1_Callback);

    abstract void supportInvalidateOptionsMenu();

    abstract boolean supportRequestWindowFeature(int r1i);
}