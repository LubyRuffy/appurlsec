package android.support.v7.internal.view;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuWrapperFactory;
import android.support.v7.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class ActionModeWrapper extends ActionMode {
    final MenuInflater a;
    final android.view.ActionMode b;

    public static class CallbackWrapper implements Callback {
        final ActionMode.Callback a;
        final Context b;
        private ActionModeWrapper c;

        public CallbackWrapper(Context r1_Context, ActionMode.Callback r2_ActionMode_Callback) {
            this.b = r1_Context;
            this.a = r2_ActionMode_Callback;
        }

        private ActionMode a(android.view.ActionMode r3_android_view_ActionMode) {
            return (this.c == null || this.c.b != r3_android_view_ActionMode) ? new ActionModeWrapper(this.b, r3_android_view_ActionMode) : this.c;
        }

        public boolean onActionItemClicked(android.view.ActionMode r4_android_view_ActionMode, MenuItem r5_MenuItem) {
            return this.a.onActionItemClicked(a(r4_android_view_ActionMode), MenuWrapperFactory.createMenuItemWrapper(r5_MenuItem));
        }

        public boolean onCreateActionMode(android.view.ActionMode r4_android_view_ActionMode, Menu r5_Menu) {
            return this.a.onCreateActionMode(a(r4_android_view_ActionMode), MenuWrapperFactory.createMenuWrapper(r5_Menu));
        }

        public void onDestroyActionMode(android.view.ActionMode r3_android_view_ActionMode) {
            this.a.onDestroyActionMode(a(r3_android_view_ActionMode));
        }

        public boolean onPrepareActionMode(android.view.ActionMode r4_android_view_ActionMode, Menu r5_Menu) {
            return this.a.onPrepareActionMode(a(r4_android_view_ActionMode), MenuWrapperFactory.createMenuWrapper(r5_Menu));
        }

        public void setLastStartedActionMode(ActionModeWrapper r1_ActionModeWrapper) {
            this.c = r1_ActionModeWrapper;
        }
    }

    public ActionModeWrapper(Context r2_Context, android.view.ActionMode r3_android_view_ActionMode) {
        this.b = r3_android_view_ActionMode;
        this.a = new SupportMenuInflater(r2_Context);
    }

    public void finish() {
        this.b.finish();
    }

    public View getCustomView() {
        return this.b.getCustomView();
    }

    public Menu getMenu() {
        return MenuWrapperFactory.createMenuWrapper(this.b.getMenu());
    }

    public MenuInflater getMenuInflater() {
        return this.a;
    }

    public CharSequence getSubtitle() {
        return this.b.getSubtitle();
    }

    public Object getTag() {
        return this.b.getTag();
    }

    public CharSequence getTitle() {
        return this.b.getTitle();
    }

    public boolean getTitleOptionalHint() {
        return this.b.getTitleOptionalHint();
    }

    public void invalidate() {
        this.b.invalidate();
    }

    public boolean isTitleOptional() {
        return this.b.isTitleOptional();
    }

    public void setCustomView(View r2_View) {
        this.b.setCustomView(r2_View);
    }

    public void setSubtitle(int r2i) {
        this.b.setSubtitle(r2i);
    }

    public void setSubtitle(CharSequence r2_CharSequence) {
        this.b.setSubtitle(r2_CharSequence);
    }

    public void setTag(Object r2_Object) {
        this.b.setTag(r2_Object);
    }

    public void setTitle(int r2i) {
        this.b.setTitle(r2i);
    }

    public void setTitle(CharSequence r2_CharSequence) {
        this.b.setTitle(r2_CharSequence);
    }

    public void setTitleOptionalHint(boolean r2z) {
        this.b.setTitleOptionalHint(r2z);
    }
}