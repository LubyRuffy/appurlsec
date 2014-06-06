package android.support.v7.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public abstract class ActionMode {
    private Object a;
    private boolean b;

    public static interface Callback {
        public boolean onActionItemClicked(ActionMode r1_ActionMode, MenuItem r2_MenuItem);

        public boolean onCreateActionMode(ActionMode r1_ActionMode, Menu r2_Menu);

        public void onDestroyActionMode(ActionMode r1_ActionMode);

        public boolean onPrepareActionMode(ActionMode r1_ActionMode, Menu r2_Menu);
    }

    public abstract void finish();

    public abstract View getCustomView();

    public abstract Menu getMenu();

    public abstract MenuInflater getMenuInflater();

    public abstract CharSequence getSubtitle();

    public Object getTag() {
        return this.a;
    }

    public abstract CharSequence getTitle();

    public boolean getTitleOptionalHint() {
        return this.b;
    }

    public abstract void invalidate();

    public boolean isTitleOptional() {
        return false;
    }

    public boolean isUiFocusable() {
        return true;
    }

    public abstract void setCustomView(View r1_View);

    public abstract void setSubtitle(int r1i);

    public abstract void setSubtitle(CharSequence r1_CharSequence);

    public void setTag(Object r1_Object) {
        this.a = r1_Object;
    }

    public abstract void setTitle(int r1i);

    public abstract void setTitle(CharSequence r1_CharSequence);

    public void setTitleOptionalHint(boolean r1z) {
        this.b = r1z;
    }
}