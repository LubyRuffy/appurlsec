package android.support.v7.internal.view.menu;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.os.IBinder;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

public class MenuDialogHelper implements OnClickListener, OnDismissListener, OnKeyListener, Callback {
    private MenuBuilder a;
    ListMenuPresenter b;
    private AlertDialog c;
    private Callback d;

    public MenuDialogHelper(MenuBuilder r1_MenuBuilder) {
        this.a = r1_MenuBuilder;
    }

    public void dismiss() {
        if (this.c != null) {
            this.c.dismiss();
        }
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        this.a.performItemAction((MenuItemImpl) this.b.getAdapter().getItem(r5i), 0);
    }

    public void onCloseMenu(MenuBuilder r2_MenuBuilder, boolean r3z) {
        if (r3z || r2_MenuBuilder == this.a) {
            dismiss();
            if (this.d == null) {
                this.d.onCloseMenu(r2_MenuBuilder, r3z);
            }
        } else if (this.d == null) {
        } else {
            this.d.onCloseMenu(r2_MenuBuilder, r3z);
        }
    }

    public void onDismiss(DialogInterface r4_DialogInterface) {
        this.b.onCloseMenu(this.a, true);
    }

    public boolean onKey(DialogInterface r3_DialogInterface, int r4i, KeyEvent r5_KeyEvent) {
        if (r4i != 82 && r4i != 4) {
            return this.a.performShortcut(r4i, r5_KeyEvent, 0);
        }
        Window r1_Window;
        View r1_View;
        DispatcherState r1_DispatcherState;
        if (r5_KeyEvent.getAction() == 0 && r5_KeyEvent.getRepeatCount() == 0) {
            r1_Window = this.c.getWindow();
            if (r1_Window != null) {
                r1_View = r1_Window.getDecorView();
                if (r1_View != null) {
                    r1_DispatcherState = r1_View.getKeyDispatcherState();
                    if (r1_DispatcherState != null) {
                        r1_DispatcherState.startTracking(r5_KeyEvent, this);
                        return true;
                    }
                }
            }
            return this.a.performShortcut(r4i, r5_KeyEvent, 0);
        } else {
            if (r5_KeyEvent.getAction() != 1 || r5_KeyEvent.isCanceled()) {
                return this.a.performShortcut(r4i, r5_KeyEvent, 0);
            }
            r1_Window = this.c.getWindow();
            if (r1_Window != null) {
                r1_View = r1_Window.getDecorView();
                if (r1_View != null) {
                    r1_DispatcherState = r1_View.getKeyDispatcherState();
                    if (r1_DispatcherState == null || (!r1_DispatcherState.isTracking(r5_KeyEvent))) {
                    } else {
                        this.a.a(true);
                        r3_DialogInterface.dismiss();
                        return true;
                    }
                }
            }
            return this.a.performShortcut(r4i, r5_KeyEvent, 0);
        }
    }

    public boolean onOpenSubMenu(MenuBuilder r2_MenuBuilder) {
        return this.d != null ? this.d.onOpenSubMenu(r2_MenuBuilder) : false;
    }

    public void setPresenterCallback(Callback r1_Callback) {
        this.d = r1_Callback;
    }

    public void show(IBinder r6_IBinder) {
        MenuBuilder r0_MenuBuilder = this.a;
        Builder r1_Builder = new Builder(r0_MenuBuilder.getContext());
        this.b = new ListMenuPresenter(R.layout.abc_list_menu_item_layout, R.style.Theme_AppCompat_CompactMenu_Dialog);
        this.b.setCallback(this);
        this.a.addMenuPresenter(this.b);
        r1_Builder.setAdapter(this.b.getAdapter(), this);
        View r2_View = r0_MenuBuilder.getHeaderView();
        if (r2_View != null) {
            r1_Builder.setCustomTitle(r2_View);
        } else {
            r1_Builder.setIcon(r0_MenuBuilder.getHeaderIcon()).setTitle(r0_MenuBuilder.getHeaderTitle());
        }
        r1_Builder.setOnKeyListener(this);
        this.c = r1_Builder.create();
        this.c.setOnDismissListener(this);
        LayoutParams r0_LayoutParams = this.c.getWindow().getAttributes();
        r0_LayoutParams.type = 1003;
        if (r6_IBinder != null) {
            r0_LayoutParams.token = r6_IBinder;
        }
        r0_LayoutParams.flags |= 131072;
        this.c.show();
    }
}