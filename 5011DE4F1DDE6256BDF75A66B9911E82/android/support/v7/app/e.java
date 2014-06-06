package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle.Delegate;
import android.support.v7.internal.view.ActionModeWrapper;
import android.support.v7.internal.view.ActionModeWrapper.CallbackWrapper;
import android.support.v7.internal.view.menu.MenuWrapperFactory;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.Window.Callback;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.utils.Base64;

// compiled from: ActionBarActivityDelegateICS.java
class e extends a {
    Menu d;

    // compiled from: ActionBarActivityDelegateICS.java
    class a implements Callback {
        final Callback a;

        public a(Callback r2_Callback) {
            this.a = r2_Callback;
        }

        public boolean dispatchGenericMotionEvent(MotionEvent r2_MotionEvent) {
            return this.a.dispatchGenericMotionEvent(r2_MotionEvent);
        }

        public boolean dispatchKeyEvent(KeyEvent r2_KeyEvent) {
            return this.a.dispatchKeyEvent(r2_KeyEvent);
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent r2_KeyEvent) {
            return this.a.dispatchKeyShortcutEvent(r2_KeyEvent);
        }

        public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent r2_AccessibilityEvent) {
            return this.a.dispatchPopulateAccessibilityEvent(r2_AccessibilityEvent);
        }

        public boolean dispatchTouchEvent(MotionEvent r2_MotionEvent) {
            return this.a.dispatchTouchEvent(r2_MotionEvent);
        }

        public boolean dispatchTrackballEvent(MotionEvent r2_MotionEvent) {
            return this.a.dispatchTrackballEvent(r2_MotionEvent);
        }

        public void onActionModeFinished(ActionMode r2_ActionMode) {
            this.a.onActionModeFinished(r2_ActionMode);
            e.this.onActionModeFinished(r2_ActionMode);
        }

        public void onActionModeStarted(ActionMode r2_ActionMode) {
            this.a.onActionModeStarted(r2_ActionMode);
            e.this.onActionModeStarted(r2_ActionMode);
        }

        public void onAttachedToWindow() {
            this.a.onAttachedToWindow();
        }

        public void onContentChanged() {
            this.a.onContentChanged();
        }

        public boolean onCreatePanelMenu(int r2i, Menu r3_Menu) {
            return this.a.onCreatePanelMenu(r2i, r3_Menu);
        }

        public View onCreatePanelView(int r2i) {
            return this.a.onCreatePanelView(r2i);
        }

        public void onDetachedFromWindow() {
            this.a.onDetachedFromWindow();
        }

        public boolean onMenuItemSelected(int r2i, MenuItem r3_MenuItem) {
            return this.a.onMenuItemSelected(r2i, r3_MenuItem);
        }

        public boolean onMenuOpened(int r2i, Menu r3_Menu) {
            return this.a.onMenuOpened(r2i, r3_Menu);
        }

        public void onPanelClosed(int r2i, Menu r3_Menu) {
            this.a.onPanelClosed(r2i, r3_Menu);
        }

        public boolean onPreparePanel(int r2i, View r3_View, Menu r4_Menu) {
            return this.a.onPreparePanel(r2i, r3_View, r4_Menu);
        }

        public boolean onSearchRequested() {
            return this.a.onSearchRequested();
        }

        public void onWindowAttributesChanged(LayoutParams r2_LayoutParams) {
            this.a.onWindowAttributesChanged(r2_LayoutParams);
        }

        public void onWindowFocusChanged(boolean r2z) {
            this.a.onWindowFocusChanged(r2z);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback r2_ActionMode_Callback) {
            return this.a.onWindowStartingActionMode(r2_ActionMode_Callback);
        }
    }

    e(ActionBarActivity r1_ActionBarActivity) {
        super(r1_ActionBarActivity);
    }

    Callback a(Callback r2_Callback) {
        return new a(r2_Callback);
    }

    void a(int r2i) {
        this.a.setProgress(r2i);
    }

    void a(boolean r2z) {
        this.a.setProgressBarVisibility(r2z);
    }

    public void addContentView(View r2_View, ViewGroup.LayoutParams r3_ViewGroup_LayoutParams) {
        this.a.b(r2_View, r3_ViewGroup_LayoutParams);
    }

    void b(boolean r2z) {
        this.a.setProgressBarIndeterminateVisibility(r2z);
    }

    void c(boolean r2z) {
        this.a.setProgressBarIndeterminate(r2z);
    }

    public ActionBar createSupportActionBar() {
        return new h(this.a, this.a);
    }

    public Delegate getDrawerToggleDelegate() {
        return null;
    }

    public void onActionModeFinished(ActionMode r4_ActionMode) {
        this.a.onSupportActionModeFinished(new ActionModeWrapper(d(), r4_ActionMode));
    }

    public void onActionModeStarted(ActionMode r4_ActionMode) {
        this.a.onSupportActionModeStarted(new ActionModeWrapper(d(), r4_ActionMode));
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onConfigurationChanged(Configuration r1_Configuration) {
    }

    public void onContentChanged() {
        this.a.onSupportContentChanged();
    }

    public void onCreate(Bundle r4_Bundle) {
        if ("splitActionBarWhenNarrow".equals(c())) {
            this.a.getWindow().setUiOptions(1, 1);
        }
        super.onCreate(r4_Bundle);
        if (this.b) {
            this.a.requestWindowFeature(Base64.DONT_BREAK_LINES);
        }
        if (this.c) {
            this.a.requestWindowFeature(REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY);
        }
        Window r0_Window = this.a.getWindow();
        r0_Window.setCallback(a(r0_Window.getCallback()));
    }

    public boolean onCreatePanelMenu(int r3i, Menu r4_Menu) {
        if (r3i != 0 && r3i != 8) {
            return this.a.a(r3i, r4_Menu);
        }
        if (this.d == null) {
            this.d = MenuWrapperFactory.createMenuWrapper(r4_Menu);
        }
        return this.a.a(r3i, this.d);
    }

    public View onCreatePanelView(int r2i) {
        return null;
    }

    public boolean onMenuItemSelected(int r2i, MenuItem r3_MenuItem) {
        if (r2i == 0) {
            r3_MenuItem = MenuWrapperFactory.createMenuItemWrapper(r3_MenuItem);
        }
        return this.a.a(r2i, r3_MenuItem);
    }

    public void onPostResume() {
    }

    public boolean onPreparePanel(int r3i, View r4_View, Menu r5_Menu) {
        return (r3i == 0 || r3i == 8) ? this.a.a(r3i, r4_View, this.d) : this.a.a(r3i, r4_View, r5_Menu);
    }

    public void onStop() {
    }

    public void onTitleChanged(CharSequence r1_CharSequence) {
    }

    public void setContentView(int r2i) {
        this.a.a(r2i);
    }

    public void setContentView(View r2_View) {
        this.a.a(r2_View);
    }

    public void setContentView(View r2_View, ViewGroup.LayoutParams r3_ViewGroup_LayoutParams) {
        this.a.a(r2_View, r3_ViewGroup_LayoutParams);
    }

    public android.support.v7.view.ActionMode startSupportActionMode(android.support.v7.view.ActionMode.Callback r5_android_support_v7_view_ActionMode_Callback) {
        if (r5_android_support_v7_view_ActionMode_Callback == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        } else {
            Context r1_Context = d();
            CallbackWrapper r2_CallbackWrapper = new CallbackWrapper(r1_Context, r5_android_support_v7_view_ActionMode_Callback);
            ActionMode r3_ActionMode = this.a.startActionMode(r2_CallbackWrapper);
            if (r3_ActionMode == null) {
                return null;
            }
            android.support.v7.view.ActionMode r0_android_support_v7_view_ActionMode = new ActionModeWrapper(r1_Context, r3_ActionMode);
            r2_CallbackWrapper.setLastStartedActionMode(r0_android_support_v7_view_ActionMode);
            return r0_android_support_v7_view_ActionMode;
        }
    }

    public void supportInvalidateOptionsMenu() {
        this.d = null;
    }

    public boolean supportRequestWindowFeature(int r2i) {
        return this.a.requestWindowFeature(r2i);
    }
}