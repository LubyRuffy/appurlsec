package android.support.v7.app;

import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.NativeActionModeAwareLayout;
import android.support.v7.internal.widget.NativeActionModeAwareLayout.OnActionModeForChildListener;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;

// compiled from: ActionBarImplHC.java
class g extends ActionBarImplBase implements OnActionModeForChildListener {
    final NativeActionModeAwareLayout e;
    private ActionMode f;

    // compiled from: ActionBarImplHC.java
    private class a implements Callback {
        private final Callback b;

        a(Callback r2_Callback) {
            this.b = r2_Callback;
        }

        public boolean onActionItemClicked(ActionMode r2_ActionMode, MenuItem r3_MenuItem) {
            return this.b.onActionItemClicked(r2_ActionMode, r3_MenuItem);
        }

        public boolean onCreateActionMode(ActionMode r3_ActionMode, Menu r4_Menu) {
            boolean r0z = this.b.onCreateActionMode(r3_ActionMode, r4_Menu);
            if (r0z) {
                g.this.f = r3_ActionMode;
                g.this.a();
            }
            return r0z;
        }

        public void onDestroyActionMode(ActionMode r3_ActionMode) {
            this.b.onDestroyActionMode(r3_ActionMode);
            g.this.b();
            g.this.f = null;
        }

        public boolean onPrepareActionMode(ActionMode r2_ActionMode, Menu r3_Menu) {
            return this.b.onPrepareActionMode(r2_ActionMode, r3_Menu);
        }
    }

    public g(ActionBarActivity r2_ActionBarActivity, a r3_a) {
        super(r2_ActionBarActivity, r3_a);
        this.e = (NativeActionModeAwareLayout) r2_ActionBarActivity.findViewById(R.id.action_bar_root);
        if (this.e != null) {
            this.e.setActionModeForChildListener(this);
        }
    }

    boolean c() {
        return this.f == null && super.c();
    }

    public void hide() {
        super.hide();
        if (this.f != null) {
            this.f.finish();
        }
    }

    public Callback onActionModeForChild(Callback r2_Callback) {
        return new a(r2_Callback);
    }

    public void show() {
        super.show();
        if (this.f != null) {
            this.f.finish();
        }
    }
}