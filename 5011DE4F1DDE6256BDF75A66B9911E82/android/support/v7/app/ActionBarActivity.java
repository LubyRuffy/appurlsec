package android.support.v7.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle.Delegate;
import android.support.v4.app.ActionBarDrawerToggle.DelegateProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.app.TaskStackBuilder.SupportParentable;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class ActionBarActivity extends FragmentActivity implements DelegateProvider, SupportParentable, a {
    a n;

    void a(int r1i) {
        super.setContentView(r1i);
    }

    void a(View r1_View) {
        super.setContentView(r1_View);
    }

    void a(View r1_View, LayoutParams r2_LayoutParams) {
        super.setContentView(r1_View, r2_LayoutParams);
    }

    boolean a(int r2i, Menu r3_Menu) {
        return super.onCreatePanelMenu(r2i, r3_Menu);
    }

    boolean a(int r2i, MenuItem r3_MenuItem) {
        return super.onMenuItemSelected(r2i, r3_MenuItem);
    }

    boolean a(int r2i, View r3_View, Menu r4_Menu) {
        return super.onPreparePanel(r2i, r3_View, r4_Menu);
    }

    protected boolean a(View r2_View, Menu r3_Menu) {
        return this.n.a(r2_View, r3_Menu);
    }

    public void addContentView(View r2_View, LayoutParams r3_LayoutParams) {
        this.n.addContentView(r2_View, r3_LayoutParams);
    }

    void b(View r1_View, LayoutParams r2_LayoutParams) {
        super.addContentView(r1_View, r2_LayoutParams);
    }

    boolean b(View r2_View, Menu r3_Menu) {
        return super.a(r2_View, r3_Menu);
    }

    public final Delegate getDrawerToggleDelegate() {
        return this.n.getDrawerToggleDelegate();
    }

    public MenuInflater getMenuInflater() {
        return this.n.b();
    }

    public ActionBar getSupportActionBar() {
        return this.n.a();
    }

    public Intent getSupportParentActivityIntent() {
        return NavUtils.getParentActivityIntent(this);
    }

    public void onBackPressed() {
        if (!this.n.onBackPressed()) {
            super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration r2_Configuration) {
        super.onConfigurationChanged(r2_Configuration);
        this.n.onConfigurationChanged(r2_Configuration);
    }

    public final void onContentChanged() {
        this.n.onContentChanged();
    }

    protected void onCreate(Bundle r2_Bundle) {
        this.n = a.a(this);
        super.onCreate(r2_Bundle);
        this.n.onCreate(r2_Bundle);
    }

    public boolean onCreatePanelMenu(int r2i, Menu r3_Menu) {
        return this.n.onCreatePanelMenu(r2i, r3_Menu);
    }

    public View onCreatePanelView(int r2i) {
        return r2i == 0 ? this.n.onCreatePanelView(r2i) : super.onCreatePanelView(r2i);
    }

    public void onCreateSupportNavigateUpTaskStack(TaskStackBuilder r1_TaskStackBuilder) {
        r1_TaskStackBuilder.addParentStack((Activity)this);
    }

    public final boolean onMenuItemSelected(int r4i, MenuItem r5_MenuItem) {
        if (this.n.onMenuItemSelected(r4i, r5_MenuItem)) {
            return true;
        }
        ActionBar r0_ActionBar = getSupportActionBar();
        return (r5_MenuItem.getItemId() != 16908332 || r0_ActionBar == null || (r0_ActionBar.getDisplayOptions() & 4) == 0) ? false : onSupportNavigateUp();
    }

    protected void onPostResume() {
        super.onPostResume();
        this.n.onPostResume();
    }

    public boolean onPreparePanel(int r2i, View r3_View, Menu r4_Menu) {
        return this.n.onPreparePanel(r2i, r3_View, r4_Menu);
    }

    public void onPrepareSupportNavigateUpTaskStack(TaskStackBuilder r1_TaskStackBuilder) {
    }

    protected void onStop() {
        super.onStop();
        this.n.onStop();
    }

    public void onSupportActionModeFinished(ActionMode r1_ActionMode) {
    }

    public void onSupportActionModeStarted(ActionMode r1_ActionMode) {
    }

    public void onSupportContentChanged() {
    }

    public boolean onSupportNavigateUp() {
        Intent r0_Intent = getSupportParentActivityIntent();
        if (r0_Intent == null) {
            return false;
        }
        if (supportShouldUpRecreateTask(r0_Intent)) {
            TaskStackBuilder r0_TaskStackBuilder = TaskStackBuilder.create(this);
            onCreateSupportNavigateUpTaskStack(r0_TaskStackBuilder);
            onPrepareSupportNavigateUpTaskStack(r0_TaskStackBuilder);
            r0_TaskStackBuilder.startActivities();
            try {
                ActivityCompat.finishAffinity(this);
            } catch (IllegalStateException e) {
                finish();
            }
        } else {
            supportNavigateUpTo(r0_Intent);
        }
        return true;
    }

    protected void onTitleChanged(CharSequence r2_CharSequence, int r3i) {
        super.onTitleChanged(r2_CharSequence, r3i);
        this.n.onTitleChanged(r2_CharSequence);
    }

    public void setContentView(int r2i) {
        this.n.setContentView(r2i);
    }

    public void setContentView(View r2_View) {
        this.n.setContentView(r2_View);
    }

    public void setContentView(View r2_View, LayoutParams r3_LayoutParams) {
        this.n.setContentView(r2_View, r3_LayoutParams);
    }

    public void setSupportProgress(int r2i) {
        this.n.a(r2i);
    }

    public void setSupportProgressBarIndeterminate(boolean r2z) {
        this.n.c(r2z);
    }

    public void setSupportProgressBarIndeterminateVisibility(boolean r2z) {
        this.n.b(r2z);
    }

    public void setSupportProgressBarVisibility(boolean r2z) {
        this.n.a(r2z);
    }

    public ActionMode startSupportActionMode(Callback r2_Callback) {
        return this.n.startSupportActionMode(r2_Callback);
    }

    public void supportInvalidateOptionsMenu() {
        if (VERSION.SDK_INT >= 14) {
            super.supportInvalidateOptionsMenu();
        }
        this.n.supportInvalidateOptionsMenu();
    }

    public void supportNavigateUpTo(Intent r1_Intent) {
        NavUtils.navigateUpTo(this, r1_Intent);
    }

    public boolean supportRequestWindowFeature(int r2i) {
        return this.n.supportRequestWindowFeature(r2i);
    }

    public boolean supportShouldUpRecreateTask(Intent r2_Intent) {
        return NavUtils.shouldUpRecreateTask(this, r2_Intent);
    }
}