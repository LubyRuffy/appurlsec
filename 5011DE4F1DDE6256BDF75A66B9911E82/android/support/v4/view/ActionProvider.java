package android.support.v4.view;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public abstract class ActionProvider {
    private final Context a;
    private SubUiVisibilityListener b;
    private VisibilityListener c;

    public static interface SubUiVisibilityListener {
        public void onSubUiVisibilityChanged(boolean r1z);
    }

    public static interface VisibilityListener {
        public void onActionProviderVisibilityChanged(boolean r1z);
    }

    public ActionProvider(Context r1_Context) {
        this.a = r1_Context;
    }

    public Context getContext() {
        return this.a;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean isVisible() {
        return true;
    }

    public abstract View onCreateActionView();

    public View onCreateActionView(MenuItem r2_MenuItem) {
        return onCreateActionView();
    }

    public boolean onPerformDefaultAction() {
        return false;
    }

    public void onPrepareSubMenu(SubMenu r1_SubMenu) {
    }

    public boolean overridesItemVisibility() {
        return false;
    }

    public void refreshVisibility() {
        if (this.c == null || (!overridesItemVisibility())) {
        } else {
            this.c.onActionProviderVisibilityChanged(isVisible());
        }
    }

    public void setSubUiVisibilityListener(SubUiVisibilityListener r1_SubUiVisibilityListener) {
        this.b = r1_SubUiVisibilityListener;
    }

    public void setVisibilityListener(VisibilityListener r4_VisibilityListener) {
        if (this.c == null || r4_VisibilityListener == null) {
            this.c = r4_VisibilityListener;
        } else {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
            this.c = r4_VisibilityListener;
        }
    }

    public void subUiVisibilityChanged(boolean r2z) {
        if (this.b != null) {
            this.b.onSubUiVisibilityChanged(r2z);
        }
    }
}