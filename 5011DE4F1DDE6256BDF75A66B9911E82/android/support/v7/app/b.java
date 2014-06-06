package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActionBarDrawerToggle.Delegate;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.ListMenuPresenter;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuBuilder.Callback;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.view.menu.MenuWrapperFactory;
import android.support.v7.internal.widget.ActionBarContainer;
import android.support.v7.internal.widget.ActionBarContextView;
import android.support.v7.internal.widget.ActionBarView;
import android.support.v7.internal.widget.ProgressBarICS;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.tencent.tauth.Constants;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.utils.audit.RequestListener;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: ActionBarActivityDelegateBase.java
class b extends a implements Callback, MenuPresenter.Callback {
    private static final int[] d;
    private ActionBarView e;
    private ListMenuPresenter f;
    private MenuBuilder g;
    private ActionMode h;
    private boolean i;
    private CharSequence j;
    private boolean k;
    private boolean l;
    private boolean m;
    private final Runnable n;

    // compiled from: ActionBarActivityDelegateBase.java
    private class a implements Delegate {
        private a() {
        }

        public Drawable getThemeUpIndicator() {
            TypedArray r0_TypedArray = b.this.obtainStyledAttributes(d);
            Drawable r1_Drawable = r0_TypedArray.getDrawable(0);
            r0_TypedArray.recycle();
            return r1_Drawable;
        }

        public void setActionBarDescription(int r1i) {
        }

        public void setActionBarUpIndicator(Drawable r2_Drawable, int r3i) {
            if (b.this.e != null) {
                b.this.e.setHomeAsUpIndicator(r2_Drawable);
            }
        }
    }

    // compiled from: ActionBarActivityDelegateBase.java
    private class b implements ActionMode.Callback {
        private ActionMode.Callback b;

        public b(ActionMode.Callback r2_ActionMode_Callback) {
            this.b = r2_ActionMode_Callback;
        }

        public boolean onActionItemClicked(ActionMode r2_ActionMode, MenuItem r3_MenuItem) {
            return this.b.onActionItemClicked(r2_ActionMode, r3_MenuItem);
        }

        public boolean onCreateActionMode(ActionMode r2_ActionMode, Menu r3_Menu) {
            return this.b.onCreateActionMode(r2_ActionMode, r3_Menu);
        }

        public void onDestroyActionMode(ActionMode r3_ActionMode) {
            this.b.onDestroyActionMode(r3_ActionMode);
            b.this.onSupportActionModeFinished(r3_ActionMode);
            b.this.h = null;
        }

        public boolean onPrepareActionMode(ActionMode r2_ActionMode, Menu r3_Menu) {
            return this.b.onPrepareActionMode(r2_ActionMode, r3_Menu);
        }
    }

    static {
        int[] r0_intA = new int[1];
        r0_intA[0] = R.attr.homeAsUpIndicator;
        d = r0_intA;
    }

    b(ActionBarActivity r2_ActionBarActivity) {
        super(r2_ActionBarActivity);
        this.n = new c(this);
    }

    private MenuView a(Context r4_Context, MenuPresenter.Callback r5_MenuPresenter_Callback) {
        if (this.g == null) {
            return null;
        }
        if (this.f == null) {
            TypedArray r0_TypedArray = r4_Context.obtainStyledAttributes(R.styleable.Theme);
            int r1i = r0_TypedArray.getResourceId(XListViewFooter.STATE_NODATA, R.style.Theme_AppCompat_CompactMenu);
            r0_TypedArray.recycle();
            this.f = new ListMenuPresenter(R.layout.abc_list_menu_item_layout, r1i);
            this.f.setCallback(r5_MenuPresenter_Callback);
            this.g.addMenuPresenter(this.f);
        } else {
            this.f.updateMenuView(false);
        }
        return this.f.getMenuView(new FrameLayout(r4_Context));
    }

    private void a(MenuBuilder r3_MenuBuilder) {
        if (r3_MenuBuilder == this.g) {
        } else {
            if (this.g != null) {
                this.g.removeMenuPresenter(this.f);
            }
            this.g = r3_MenuBuilder;
            if (r3_MenuBuilder == null || this.f == null) {
            } else {
                r3_MenuBuilder.addMenuPresenter(this.f);
            }
            if (this.e != null) {
                this.e.setMenu(r3_MenuBuilder, this);
            }
        }
    }

    private void a(MenuBuilder r2_MenuBuilder, boolean r3z) {
        if (this.e == null || (!this.e.isOverflowReserved())) {
            r2_MenuBuilder.close();
        } else if (this.e.isOverflowMenuShowing() && r3z) {
            this.e.hideOverflowMenu();
        } else {
            if (this.e.getVisibility() == 0) {
                this.e.showOverflowMenu();
            }
        }
    }

    private void a(ProgressBarICS r4_ProgressBarICS, ProgressBarICS r5_ProgressBarICS) {
        if (this.l && r5_ProgressBarICS.getVisibility() == 4) {
            r5_ProgressBarICS.setVisibility(0);
            if ((!this.k) || r4_ProgressBarICS.getProgress() >= 10000) {
            } else {
                r4_ProgressBarICS.setVisibility(0);
            }
        } else if (this.k || r4_ProgressBarICS.getProgress() >= 10000) {
        } else {
            r4_ProgressBarICS.setVisibility(0);
        }
    }

    private void b_(int r7i) {
        ProgressBarICS r2_ProgressBarICS = h();
        ProgressBarICS r3_ProgressBarICS = i();
        if (r7i == -1) {
            if (this.k) {
                int r0i;
                r0i = (r3_ProgressBarICS.isIndeterminate() || r3_ProgressBarICS.getProgress() < 10000) ? 0 : XListViewFooter.STATE_NODATA;
                r3_ProgressBarICS.setVisibility(r0i);
            }
            if (this.l) {
                r2_ProgressBarICS.setVisibility(0);
            }
        } else if (r7i == -2) {
            if (this.k) {
                r3_ProgressBarICS.setVisibility(Base64.DONT_BREAK_LINES);
            }
            if (this.l) {
                r2_ProgressBarICS.setVisibility(Base64.DONT_BREAK_LINES);
            }
        } else if (r7i == -3) {
            r3_ProgressBarICS.setIndeterminate(true);
        } else if (r7i == -4) {
            r3_ProgressBarICS.setIndeterminate(false);
        } else if (r7i < 0 || r7i > 10000) {
        } else {
            r3_ProgressBarICS.setProgress(r7i + 0);
            if (r7i < 10000) {
                a(r3_ProgressBarICS, r2_ProgressBarICS);
            } else {
                b(r3_ProgressBarICS, r2_ProgressBarICS);
            }
        }
    }

    private void b_(ProgressBarICS r3_ProgressBarICS, ProgressBarICS r4_ProgressBarICS) {
        if (this.l && r4_ProgressBarICS.getVisibility() == 0) {
            r4_ProgressBarICS.setVisibility(XListViewFooter.STATE_NODATA);
            if (this.k && r3_ProgressBarICS.getVisibility() == 0) {
                r3_ProgressBarICS.setVisibility(XListViewFooter.STATE_NODATA);
            }
        } else if (this.k || r3_ProgressBarICS.getVisibility() == 0) {
        } else {
            r3_ProgressBarICS.setVisibility(XListViewFooter.STATE_NODATA);
        }
    }

    private MenuBuilder g() {
        MenuBuilder r0_MenuBuilder = new MenuBuilder(d());
        r0_MenuBuilder.setCallback(this);
        return r0_MenuBuilder;
    }

    private ProgressBarICS h() {
        ProgressBarICS r0_ProgressBarICS = (ProgressBarICS) this.e.findViewById(R.id.progress_circular);
        if (r0_ProgressBarICS != null) {
            r0_ProgressBarICS.setVisibility(XListViewFooter.STATE_NODATA);
        }
        return r0_ProgressBarICS;
    }

    private ProgressBarICS i() {
        ProgressBarICS r0_ProgressBarICS = (ProgressBarICS) this.e.findViewById(R.id.progress_horizontal);
        if (r0_ProgressBarICS != null) {
            r0_ProgressBarICS.setVisibility(XListViewFooter.STATE_NODATA);
        }
        return r0_ProgressBarICS;
    }

    void a(int r2i) {
        b(r2i + 0);
    }

    void a(boolean r2z) {
        b(r2z ? -1 : RequestListener.DEFAULT_LOADED_SIZE);
    }

    public void addContentView(View r3_View, LayoutParams r4_LayoutParams) {
        e();
        if (this.b) {
            ((ViewGroup) this.a.findViewById(16908290)).addView(r3_View, r4_LayoutParams);
        } else {
            this.a.a(r3_View, r4_LayoutParams);
        }
        this.a.onSupportContentChanged();
    }

    void b_(boolean r2z) {
        b(r2z ? -1 : RequestListener.DEFAULT_LOADED_SIZE);
    }

    void c(boolean r2z) {
        b(r2z ? Constants.ERROR_URL : Constants.ERROR_JSON);
    }

    public ActionBar createSupportActionBar() {
        e();
        return new ActionBarImplBase(this.a, this.a);
    }

    final void e() {
        if ((!this.b) || this.i) {
        } else {
            boolean r2z;
            if (this.c) {
                this.a.a(R.layout.abc_action_bar_decor_overlay);
            } else {
                this.a.a(R.layout.abc_action_bar_decor);
            }
            this.e = (ActionBarView) this.a.findViewById(R.id.action_bar);
            this.e.setWindowCallback(this.a);
            if (this.k) {
                this.e.initProgress();
            }
            if (this.l) {
                this.e.initIndeterminateProgress();
            }
            boolean r3z = "splitActionBarWhenNarrow".equals(c());
            if (r3z) {
                r2z = this.a.getResources().getBoolean(R.bool.abc_split_action_bar_is_narrow);
            } else {
                TypedArray r1_TypedArray = this.a.obtainStyledAttributes(R.styleable.ActionBarWindow);
                boolean r0z = r1_TypedArray.getBoolean(XListViewHeader.STATE_REFRESHING, false);
                r1_TypedArray.recycle();
                r2z = r0z;
            }
            ActionBarContainer r0_ActionBarContainer = (ActionBarContainer) this.a.findViewById(R.id.split_action_bar);
            if (r0_ActionBarContainer != null) {
                this.e.setSplitView(r0_ActionBarContainer);
                this.e.setSplitActionBar(r2z);
                this.e.setSplitWhenNarrow(r3z);
                ActionBarContextView r1_ActionBarContextView = (ActionBarContextView) this.a.findViewById(R.id.action_context_bar);
                r1_ActionBarContextView.setSplitView(r0_ActionBarContainer);
                r1_ActionBarContextView.setSplitActionBar(r2z);
                r1_ActionBarContextView.setSplitWhenNarrow(r3z);
            }
            this.a.findViewById(16908290).setId(-1);
            this.a.findViewById(R.id.action_bar_activity_content).setId(16908290);
            if (this.j != null) {
                this.e.setWindowTitle(this.j);
                this.j = null;
            }
            this.i = true;
            supportInvalidateOptionsMenu();
        }
    }

    Delegate getDrawerToggleDelegate() {
        return new a(null);
    }

    public boolean onBackPressed() {
        if (this.h != null) {
            this.h.finish();
            return true;
        } else {
            if (this.e == null || (!this.e.hasExpandedActionView())) {
                return false;
            }
            this.e.collapseActionView();
            return true;
        }
    }

    public void onCloseMenu(MenuBuilder r2_MenuBuilder, boolean r3z) {
        this.a.closeOptionsMenu();
    }

    public void onConfigurationChanged(Configuration r2_Configuration) {
        if (this.b && this.i) {
            ((ActionBarImplBase) a()).onConfigurationChanged(r2_Configuration);
        }
    }

    public void onContentChanged() {
    }

    public boolean onCreatePanelMenu(int r2i, Menu r3_Menu) {
        return r2i != 0 ? this.a.a(r2i, r3_Menu) : false;
    }

    public View onCreatePanelView(int r6i) {
        if (r6i != 0) {
            return null;
        }
        boolean r2z = true;
        Menu r1_Menu = this.g;
        if (this.h == null) {
            if (r1_Menu == null) {
                r1_Menu = g();
                a((MenuBuilder)r1_Menu);
                r1_Menu.stopDispatchingItemsChanged();
                r2z = this.a.a(0, r1_Menu);
            }
            if (r2z) {
                r1_Menu.stopDispatchingItemsChanged();
                r2z = this.a.a(0, null, r1_Menu);
            }
        }
        if (r2z) {
            View r0_View = (View) a(this.a, (MenuPresenter.Callback)this);
            r1_Menu.startDispatchingItemsChanged();
            return r0_View;
        } else {
            a(null);
            return null;
        }
    }

    public boolean onMenuItemSelected(int r2i, MenuItem r3_MenuItem) {
        if (r2i == 0) {
            r3_MenuItem = MenuWrapperFactory.createMenuItemWrapper(r3_MenuItem);
        }
        return this.a.a(r2i, r3_MenuItem);
    }

    public boolean onMenuItemSelected(MenuBuilder r3_MenuBuilder, MenuItem r4_MenuItem) {
        return this.a.onMenuItemSelected(0, r4_MenuItem);
    }

    public void onMenuModeChange(MenuBuilder r2_MenuBuilder) {
        a(r2_MenuBuilder, true);
    }

    public boolean onOpenSubMenu(MenuBuilder r2_MenuBuilder) {
        return false;
    }

    public void onPostResume() {
        ActionBarImplBase r0_ActionBarImplBase = (ActionBarImplBase) a();
        if (r0_ActionBarImplBase != null) {
            r0_ActionBarImplBase.setShowHideAnimationEnabled(true);
        }
    }

    public boolean onPreparePanel(int r2i, View r3_View, Menu r4_Menu) {
        return r2i != 0 ? this.a.a(r2i, r3_View, r4_Menu) : false;
    }

    public void onStop() {
        ActionBarImplBase r0_ActionBarImplBase = (ActionBarImplBase) a();
        if (r0_ActionBarImplBase != null) {
            r0_ActionBarImplBase.setShowHideAnimationEnabled(false);
        }
    }

    public void onTitleChanged(CharSequence r2_CharSequence) {
        if (this.e != null) {
            this.e.setWindowTitle(r2_CharSequence);
        } else {
            this.j = r2_CharSequence;
        }
    }

    public void setContentView(int r3i) {
        e();
        if (this.b) {
            ViewGroup r0_ViewGroup = (ViewGroup) this.a.findViewById(16908290);
            r0_ViewGroup.removeAllViews();
            this.a.getLayoutInflater().inflate(r3i, r0_ViewGroup);
        } else {
            this.a.a(r3i);
        }
        this.a.onSupportContentChanged();
    }

    public void setContentView(View r3_View) {
        e();
        if (this.b) {
            ViewGroup r0_ViewGroup = (ViewGroup) this.a.findViewById(16908290);
            r0_ViewGroup.removeAllViews();
            r0_ViewGroup.addView(r3_View);
        } else {
            this.a.a(r3_View);
        }
        this.a.onSupportContentChanged();
    }

    public void setContentView(View r3_View, LayoutParams r4_LayoutParams) {
        e();
        if (this.b) {
            ViewGroup r0_ViewGroup = (ViewGroup) this.a.findViewById(16908290);
            r0_ViewGroup.removeAllViews();
            r0_ViewGroup.addView(r3_View, r4_LayoutParams);
        } else {
            this.a.a(r3_View, r4_LayoutParams);
        }
        this.a.onSupportContentChanged();
    }

    public ActionMode startSupportActionMode(ActionMode.Callback r3_ActionMode_Callback) {
        if (r3_ActionMode_Callback == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        } else {
            if (this.h != null) {
                this.h.finish();
            }
            ActionMode.Callback r1_ActionMode_Callback = new b(r3_ActionMode_Callback);
            ActionBarImplBase r0_ActionBarImplBase = (ActionBarImplBase) a();
            if (r0_ActionBarImplBase != null) {
                this.h = r0_ActionBarImplBase.startActionMode(r1_ActionMode_Callback);
            }
            if (this.h != null) {
                this.a.onSupportActionModeStarted(this.h);
            }
            return this.h;
        }
    }

    public void supportInvalidateOptionsMenu() {
        if (!this.m) {
            this.m = true;
            this.a.getWindow().getDecorView().post(this.n);
        }
    }

    public boolean supportRequestWindowFeature(int r2i) {
        switch (r2i) {
            case XListViewHeader.STATE_REFRESHING:
                this.k = true;
                return true;
            case ShareUtils.SHARE_SMS:
                this.l = true;
                return true;
            case Base64.DONT_BREAK_LINES:
                this.b = true;
                return true;
            case REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY:
                this.c = true;
                return true;
        }
        return this.a.requestWindowFeature(r2i);
    }
}