package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBar.OnMenuVisibilityListener;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.internal.widget.ActionBarContainer;
import android.support.v7.internal.widget.ActionBarContextView;
import android.support.v7.internal.widget.ActionBarOverlayLayout;
import android.support.v7.internal.widget.ActionBarView;
import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.SpinnerAdapter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

class ActionBarImplBase extends ActionBar {
    private boolean A;
    private a B;
    a a;
    ActionMode b;
    Callback c;
    final Handler d;
    private Context e;
    private Context f;
    private ActionBarActivity g;
    private ActionBarOverlayLayout h;
    private ActionBarContainer i;
    private ViewGroup j;
    private ActionBarView k;
    private ActionBarContextView l;
    private ActionBarContainer m;
    private ScrollingTabContainerView n;
    private ArrayList<TabImpl> o;
    private TabImpl p;
    private int q;
    private boolean r;
    private ArrayList<OnMenuVisibilityListener> s;
    private int t;
    private boolean u;
    private int v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    public class TabImpl extends Tab {
        private TabListener b;
        private Object c;
        private Drawable d;
        private CharSequence e;
        private CharSequence f;
        private int g;
        private View h;

        public TabImpl() {
            this.g = -1;
        }

        public TabListener getCallback() {
            return this.b;
        }

        public CharSequence getContentDescription() {
            return this.f;
        }

        public View getCustomView() {
            return this.h;
        }

        public Drawable getIcon() {
            return this.d;
        }

        public int getPosition() {
            return this.g;
        }

        public Object getTag() {
            return this.c;
        }

        public CharSequence getText() {
            return this.e;
        }

        public void select() {
            ActionBarImplBase.this.selectTab(this);
        }

        public Tab setContentDescription(int r2i) {
            return setContentDescription(ActionBarImplBase.this.e.getResources().getText(r2i));
        }

        public Tab setContentDescription(CharSequence r3_CharSequence) {
            this.f = r3_CharSequence;
            if (this.g >= 0) {
                ActionBarImplBase.this.n.updateTab(this.g);
            }
            return this;
        }

        public Tab setCustomView(int r3i) {
            return setCustomView(LayoutInflater.from(ActionBarImplBase.this.getThemedContext()).inflate(r3i, null));
        }

        public Tab setCustomView(View r3_View) {
            this.h = r3_View;
            if (this.g >= 0) {
                ActionBarImplBase.this.n.updateTab(this.g);
            }
            return this;
        }

        public Tab setIcon(int r2i) {
            return setIcon(ActionBarImplBase.this.e.getResources().getDrawable(r2i));
        }

        public Tab setIcon(Drawable r3_Drawable) {
            this.d = r3_Drawable;
            if (this.g >= 0) {
                ActionBarImplBase.this.n.updateTab(this.g);
            }
            return this;
        }

        public void setPosition(int r1i) {
            this.g = r1i;
        }

        public Tab setTabListener(TabListener r1_TabListener) {
            this.b = r1_TabListener;
            return this;
        }

        public Tab setTag(Object r1_Object) {
            this.c = r1_Object;
            return this;
        }

        public Tab setText(int r2i) {
            return setText(ActionBarImplBase.this.e.getResources().getText(r2i));
        }

        public Tab setText(CharSequence r3_CharSequence) {
            this.e = r3_CharSequence;
            if (this.g >= 0) {
                ActionBarImplBase.this.n.updateTab(this.g);
            }
            return this;
        }
    }

    class a extends ActionMode implements MenuBuilder.Callback {
        private Callback b;
        private MenuBuilder c;
        private WeakReference<View> d;

        public a(Callback r4_Callback) {
            this.b = r4_Callback;
            this.c = new MenuBuilder(ActionBarImplBase.this.getThemedContext()).setDefaultShowAsAction(1);
            this.c.setCallback(this);
        }

        public boolean dispatchOnCreate() {
            this.c.stopDispatchingItemsChanged();
            boolean r0z = this.b.onCreateActionMode(this, this.c);
            this.c.startDispatchingItemsChanged();
            return r0z;
        }

        public void finish() {
            if (ActionBarImplBase.this != this) {
            } else {
                if (ActionBarImplBase.b(ActionBarImplBase.this.w, ActionBarImplBase.this.x, false)) {
                    this.b.onDestroyActionMode(this);
                } else {
                    ActionBarImplBase.this.b = this;
                    ActionBarImplBase.this.c = this.b;
                }
                this.b = null;
                ActionBarImplBase.this.a(false);
                ActionBarImplBase.this.l.closeMode();
                ActionBarImplBase.this.k.sendAccessibilityEvent(Base64.ORDERED);
                ActionBarImplBase.this = null;
            }
        }

        public View getCustomView() {
            return this.d != null ? (View) this.d.get() : null;
        }

        public Menu getMenu() {
            return this.c;
        }

        public MenuInflater getMenuInflater() {
            return new SupportMenuInflater(ActionBarImplBase.this.getThemedContext());
        }

        public CharSequence getSubtitle() {
            return ActionBarImplBase.this.l.getSubtitle();
        }

        public CharSequence getTitle() {
            return ActionBarImplBase.this.l.getTitle();
        }

        public void invalidate() {
            this.c.stopDispatchingItemsChanged();
            this.b.onPrepareActionMode(this, this.c);
            this.c.startDispatchingItemsChanged();
        }

        public boolean isTitleOptional() {
            return ActionBarImplBase.this.l.isTitleOptional();
        }

        public void onCloseMenu(MenuBuilder r1_MenuBuilder, boolean r2z) {
        }

        public void onCloseSubMenu(SubMenuBuilder r1_SubMenuBuilder) {
        }

        public boolean onMenuItemSelected(MenuBuilder r2_MenuBuilder, MenuItem r3_MenuItem) {
            return this.b != null ? this.b.onActionItemClicked(this, r3_MenuItem) : false;
        }

        public void onMenuModeChange(MenuBuilder r2_MenuBuilder) {
            if (this.b == null) {
            } else {
                invalidate();
                ActionBarImplBase.this.l.showOverflowMenu();
            }
        }

        public void onMenuModeChange(Menu r2_Menu) {
            if (this.b == null) {
            } else {
                invalidate();
                ActionBarImplBase.this.l.showOverflowMenu();
            }
        }

        public boolean onSubMenuSelected(SubMenuBuilder r3_SubMenuBuilder) {
            if (this.b == null) {
                return false;
            }
            if (r3_SubMenuBuilder.hasVisibleItems()) {
                return true;
            }
            return true;
        }

        public void setCustomView(View r2_View) {
            ActionBarImplBase.this.l.setCustomView(r2_View);
            this.d = new WeakReference(r2_View);
        }

        public void setSubtitle(int r2i) {
            setSubtitle(ActionBarImplBase.this.e.getResources().getString(r2i));
        }

        public void setSubtitle(CharSequence r2_CharSequence) {
            ActionBarImplBase.this.l.setSubtitle(r2_CharSequence);
        }

        public void setTitle(int r2i) {
            setTitle(ActionBarImplBase.this.e.getResources().getString(r2i));
        }

        public void setTitle(CharSequence r2_CharSequence) {
            ActionBarImplBase.this.l.setTitle(r2_CharSequence);
        }

        public void setTitleOptionalHint(boolean r2z) {
            super.setTitleOptionalHint(r2z);
            ActionBarImplBase.this.l.setTitleOptional(r2z);
        }
    }

    public ActionBarImplBase(ActionBarActivity r2_ActionBarActivity, a r3_a) {
        this.o = new ArrayList();
        this.q = -1;
        this.s = new ArrayList();
        this.d = new Handler();
        this.v = 0;
        this.z = true;
        this.g = r2_ActionBarActivity;
        this.e = r2_ActionBarActivity;
        this.B = r3_a;
        a(this.g);
    }

    private void a(Tab r4_Tab, int r5i) {
        TabImpl r4_TabImpl = (TabImpl) r4_Tab;
        if (r4_TabImpl.getCallback() == null) {
            throw new IllegalStateException("Action Bar Tab must have a Callback");
        } else {
            r4_TabImpl.setPosition(r5i);
            this.o.add(r5i, r4_TabImpl);
            int r2i = this.o.size();
            int r1i = r5i + 1;
            while (r1i < r2i) {
                ((TabImpl) this.o.get(r1i)).setPosition(r1i);
                r1i++;
            }
        }
    }

    private void a(ActionBarActivity r6_ActionBarActivity) {
        boolean r2z = false;
        this.h = (ActionBarOverlayLayout) r6_ActionBarActivity.findViewById(R.id.action_bar_overlay_layout);
        if (this.h != null) {
            this.h.setActionBar(this);
        }
        this.k = (ActionBarView) r6_ActionBarActivity.findViewById(R.id.action_bar);
        this.l = (ActionBarContextView) r6_ActionBarActivity.findViewById(R.id.action_context_bar);
        this.i = (ActionBarContainer) r6_ActionBarActivity.findViewById(R.id.action_bar_container);
        this.j = (ViewGroup) r6_ActionBarActivity.findViewById(R.id.top_action_bar);
        if (this.j == null) {
            this.j = this.i;
        }
        this.m = (ActionBarContainer) r6_ActionBarActivity.findViewById(R.id.split_action_bar);
        if (this.k == null || this.l == null || this.i == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with a compatible window decor layout");
        } else {
            int r0i;
            this.k.setContextView(this.l);
            this.t = this.k.isSplitActionBar() ? 1 : 0;
            r0i = (this.k.getDisplayOptions() & 4) != 0 ? 1 : 0;
            if (r0i != 0) {
                this.r = true;
            }
            ActionBarPolicy r3_ActionBarPolicy = ActionBarPolicy.get(this.e);
            if (r3_ActionBarPolicy.enableHomeButtonByDefault() || r0i != 0) {
                r2z = true;
                setHomeButtonEnabled(r2z);
                b(r3_ActionBarPolicy.hasEmbeddedTabs());
                setTitle(this.g.getTitle());
            } else {
                setHomeButtonEnabled(r2z);
                b(r3_ActionBarPolicy.hasEmbeddedTabs());
                setTitle(this.g.getTitle());
            }
        }
    }

    private void b(boolean r6z) {
        int r0i;
        ScrollingTabContainerView r3_ScrollingTabContainerView = null;
        boolean r1z = true;
        this.u = r6z;
        if (this.u) {
            this.i.setTabContainer(r3_ScrollingTabContainerView);
            this.k.setEmbeddedTabView(this.n);
        } else {
            this.k.setEmbeddedTabView(r3_ScrollingTabContainerView);
            this.i.setTabContainer(this.n);
        }
        r0i = getNavigationMode() == 2 ? 1 : 0;
        if (this.n != null) {
            if (r0i != 0) {
                this.n.setVisibility(0);
            } else {
                this.n.setVisibility(Base64.DONT_BREAK_LINES);
            }
        }
        ActionBarView r3_ActionBarView = this.k;
        if (this.u || r0i == 0) {
            r1z = false;
            r3_ActionBarView.setCollapsable(r1z);
        } else {
            r3_ActionBarView.setCollapsable(r1z);
        }
    }

    private static boolean b(boolean r1z, boolean r2z, boolean r3z) {
        if (r3z) {
            return true;
        }
        if (r1z || r2z) {
            return false;
        }
        return true;
    }

    private void c(boolean r4z) {
        if (b(this.w, this.x, this.y)) {
            if (!this.z) {
                this.z = true;
                doShow(r4z);
            }
        } else {
            if (this.z) {
                this.z = false;
                doHide(r4z);
            }
        }
    }

    private void d() {
        if (this.n != null) {
        } else {
            ScrollingTabContainerView r0_ScrollingTabContainerView = new ScrollingTabContainerView(this.e);
            if (this.u) {
                r0_ScrollingTabContainerView.setVisibility(0);
                this.k.setEmbeddedTabView(r0_ScrollingTabContainerView);
            } else {
                if (getNavigationMode() == 2) {
                    r0_ScrollingTabContainerView.setVisibility(0);
                } else {
                    r0_ScrollingTabContainerView.setVisibility(Base64.DONT_BREAK_LINES);
                }
                this.i.setTabContainer(r0_ScrollingTabContainerView);
            }
            this.n = r0_ScrollingTabContainerView;
        }
    }

    private void e() {
        if (this.p != null) {
            selectTab(null);
        }
        this.o.clear();
        if (this.n != null) {
            this.n.removeAllTabs();
        }
        this.q = -1;
    }

    void a() {
        if (!this.y) {
            this.y = true;
            c(false);
        }
    }

    void a(boolean r5z) {
        int r2i = Base64.DONT_BREAK_LINES;
        if (r5z) {
            a();
        } else {
            b();
        }
        this.k.animateToVisibility(r5z ? XListViewFooter.STATE_NODATA : 0);
        this.l.animateToVisibility(r5z ? 0 : 8);
        if (this.n == null || this.k.hasEmbeddedTabs() || (!this.k.isCollapsed())) {
        } else {
            ScrollingTabContainerView r0_ScrollingTabContainerView = this.n;
            if (r5z) {
                r0_ScrollingTabContainerView.setVisibility(r2i);
            } else {
                r2i = 0;
                r0_ScrollingTabContainerView.setVisibility(r2i);
            }
        }
    }

    public void addOnMenuVisibilityListener(OnMenuVisibilityListener r2_OnMenuVisibilityListener) {
        this.s.add(r2_OnMenuVisibilityListener);
    }

    public void addTab(Tab r2_Tab) {
        addTab(r2_Tab, this.o.isEmpty());
    }

    public void addTab(Tab r2_Tab, int r3i) {
        addTab(r2_Tab, r3i, this.o.isEmpty());
    }

    public void addTab(Tab r2_Tab, int r3i, boolean r4z) {
        d();
        this.n.addTab(r2_Tab, r3i, r4z);
        a(r2_Tab, r3i);
        if (r4z) {
            selectTab(r2_Tab);
        }
    }

    public void addTab(Tab r2_Tab, boolean r3z) {
        d();
        this.n.addTab(r2_Tab, r3z);
        a(r2_Tab, this.o.size());
        if (r3z) {
            selectTab(r2_Tab);
        }
    }

    void b() {
        if (this.y) {
            this.y = false;
            c(false);
        }
    }

    boolean c() {
        return this.A;
    }

    public void doHide(boolean r5z) {
        this.j.clearAnimation();
        if (this.j.getVisibility() == 8) {
        } else {
            int r0i;
            if (c() || r5z) {
                r0i = 1;
                if (r0i == 0) {
                    this.j.startAnimation(AnimationUtils.loadAnimation(this.e, R.anim.abc_slide_out_top));
                }
                this.j.setVisibility(Base64.DONT_BREAK_LINES);
                if (this.m == null || this.m.getVisibility() == 8) {
                } else {
                    if (r0i != 0) {
                        this.m.startAnimation(AnimationUtils.loadAnimation(this.e, R.anim.abc_slide_out_bottom));
                    }
                    this.m.setVisibility(Base64.DONT_BREAK_LINES);
                }
            } else {
                r0i = 0;
                if (r0i == 0) {
                    this.j.setVisibility(Base64.DONT_BREAK_LINES);
                    if (this.m == null || this.m.getVisibility() == 8) {
                    } else if (r0i != 0) {
                        this.m.setVisibility(Base64.DONT_BREAK_LINES);
                    } else {
                        this.m.startAnimation(AnimationUtils.loadAnimation(this.e, R.anim.abc_slide_out_bottom));
                        this.m.setVisibility(Base64.DONT_BREAK_LINES);
                    }
                }
            }
            this.j.startAnimation(AnimationUtils.loadAnimation(this.e, R.anim.abc_slide_out_top));
            this.j.setVisibility(Base64.DONT_BREAK_LINES);
            if (this.m == null || this.m.getVisibility() == 8) {
            } else {
                if (r0i != 0) {
                    this.m.startAnimation(AnimationUtils.loadAnimation(this.e, R.anim.abc_slide_out_bottom));
                }
                this.m.setVisibility(Base64.DONT_BREAK_LINES);
            }
        }
    }

    public void doShow(boolean r5z) {
        this.j.clearAnimation();
        if (this.j.getVisibility() == 0) {
        } else {
            int r0i;
            if (c() || r5z) {
                r0i = 1;
                if (r0i == 0) {
                    this.j.startAnimation(AnimationUtils.loadAnimation(this.e, R.anim.abc_slide_in_top));
                }
                this.j.setVisibility(0);
                if (this.m == null || this.m.getVisibility() == 0) {
                } else {
                    if (r0i != 0) {
                        this.m.startAnimation(AnimationUtils.loadAnimation(this.e, R.anim.abc_slide_in_bottom));
                    }
                    this.m.setVisibility(0);
                }
            } else {
                r0i = 0;
                if (r0i == 0) {
                    this.j.setVisibility(0);
                    if (this.m == null || this.m.getVisibility() == 0) {
                    } else if (r0i != 0) {
                        this.m.setVisibility(0);
                    } else {
                        this.m.startAnimation(AnimationUtils.loadAnimation(this.e, R.anim.abc_slide_in_bottom));
                        this.m.setVisibility(0);
                    }
                }
            }
            this.j.startAnimation(AnimationUtils.loadAnimation(this.e, R.anim.abc_slide_in_top));
            this.j.setVisibility(0);
            if (this.m == null || this.m.getVisibility() == 0) {
            } else {
                if (r0i != 0) {
                    this.m.startAnimation(AnimationUtils.loadAnimation(this.e, R.anim.abc_slide_in_bottom));
                }
                this.m.setVisibility(0);
            }
        }
    }

    public View getCustomView() {
        return this.k.getCustomNavigationView();
    }

    public int getDisplayOptions() {
        return this.k.getDisplayOptions();
    }

    public int getHeight() {
        return this.i.getHeight();
    }

    public int getNavigationItemCount() {
        switch (this.k.getNavigationMode()) {
            case XListViewHeader.STATE_READY:
                SpinnerAdapter r1_SpinnerAdapter = this.k.getDropdownAdapter();
                return r1_SpinnerAdapter != null ? r1_SpinnerAdapter.getCount() : 0;
            case XListViewHeader.STATE_REFRESHING:
                return this.o.size();
        }
        return 0;
    }

    public int getNavigationMode() {
        return this.k.getNavigationMode();
    }

    public int getSelectedNavigationIndex() {
        switch (this.k.getNavigationMode()) {
            case XListViewHeader.STATE_READY:
                return this.k.getDropdownSelectedPosition();
            case XListViewHeader.STATE_REFRESHING:
                return this.p != null ? this.p.getPosition() : -1;
        }
        return -1;
    }

    public Tab getSelectedTab() {
        return this.p;
    }

    public CharSequence getSubtitle() {
        return this.k.getSubtitle();
    }

    public Tab getTabAt(int r2i) {
        return (Tab) this.o.get(r2i);
    }

    public int getTabCount() {
        return this.o.size();
    }

    public Context getThemedContext() {
        if (this.f == null) {
            TypedValue r0_TypedValue = new TypedValue();
            this.e.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, r0_TypedValue, true);
            int r0i = r0_TypedValue.resourceId;
            if (r0i != 0) {
                this.f = new ContextThemeWrapper(this.e, r0i);
            } else {
                this.f = this.e;
            }
        }
        return this.f;
    }

    public CharSequence getTitle() {
        return this.k.getTitle();
    }

    public boolean hasNonEmbeddedTabs() {
        return !(this.u) && getNavigationMode() == 2;
    }

    public void hide() {
        if (!this.w) {
            this.w = true;
            c(false);
        }
    }

    public boolean isShowing() {
        return this.z;
    }

    public Tab newTab() {
        return new TabImpl();
    }

    public void onConfigurationChanged(Configuration r2_Configuration) {
        b(ActionBarPolicy.get(this.e).hasEmbeddedTabs());
    }

    public void removeAllTabs() {
        e();
    }

    public void removeOnMenuVisibilityListener(OnMenuVisibilityListener r2_OnMenuVisibilityListener) {
        this.s.remove(r2_OnMenuVisibilityListener);
    }

    public void removeTab(Tab r2_Tab) {
        removeTabAt(r2_Tab.getPosition());
    }

    public void removeTabAt(int r5i) {
        if (this.n == null) {
        } else {
            int r1i;
            r1i = this.p != null ? this.p.getPosition() : this.q;
            this.n.removeTabAt(r5i);
            TabImpl r0_TabImpl = (TabImpl) this.o.remove(r5i);
            if (r0_TabImpl != null) {
                r0_TabImpl.setPosition(-1);
            }
            int r3i = this.o.size();
            int r2i = r5i;
            while (r2i < r3i) {
                ((TabImpl) this.o.get(r2i)).setPosition(r2i);
                r2i++;
            }
            if (r1i == r5i) {
                selectTab(this.o.isEmpty() ? null : (TabImpl) this.o.get(Math.max(0, r5i - 1)));
            }
        }
    }

    public void selectTab(Tab r4_Tab) {
        int r0i = -1;
        if (getNavigationMode() != 2) {
            if (r4_Tab != null) {
                r0i = r4_Tab.getPosition();
            }
            this.q = r0i;
        } else {
            FragmentTransaction r1_FragmentTransaction = this.g.getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
            if (this.p == r4_Tab) {
                if (this.p != null) {
                    this.p.getCallback().onTabReselected(this.p, r1_FragmentTransaction);
                    this.n.animateToTab(r4_Tab.getPosition());
                }
            } else {
                ScrollingTabContainerView r2_ScrollingTabContainerView = this.n;
                if (r4_Tab != null) {
                    r0i = r4_Tab.getPosition();
                }
                r2_ScrollingTabContainerView.setTabSelected(r0i);
                if (this.p != null) {
                    this.p.getCallback().onTabUnselected(this.p, r1_FragmentTransaction);
                }
                this.p = (TabImpl) r4_Tab;
                if (this.p != null) {
                    this.p.getCallback().onTabSelected(this.p, r1_FragmentTransaction);
                }
            }
            if (!r1_FragmentTransaction.isEmpty()) {
                r1_FragmentTransaction.commit();
            }
        }
    }

    public void setBackgroundDrawable(Drawable r2_Drawable) {
        this.i.setPrimaryBackground(r2_Drawable);
    }

    public void setCustomView(int r4i) {
        setCustomView(LayoutInflater.from(getThemedContext()).inflate(r4i, this.k, false));
    }

    public void setCustomView(View r2_View) {
        this.k.setCustomNavigationView(r2_View);
    }

    public void setCustomView(View r2_View, LayoutParams r3_LayoutParams) {
        r2_View.setLayoutParams(r3_LayoutParams);
        this.k.setCustomNavigationView(r2_View);
    }

    public void setDisplayHomeAsUpEnabled(boolean r3z) {
        setDisplayOptions(r3z ? 4 : 0, XListViewFooter.STATE_NODATA);
    }

    public void setDisplayOptions(int r2i) {
        if ((r2i & 4) != 0) {
            this.r = true;
        }
        this.k.setDisplayOptions(r2i);
    }

    public void setDisplayOptions(int r5i, int r6i) {
        int r0i = this.k.getDisplayOptions();
        if ((r6i & 4) != 0) {
            this.r = true;
        }
        this.k.setDisplayOptions((r0i & (r6i ^ -1)) | (r5i & r6i));
    }

    public void setDisplayShowCustomEnabled(boolean r3z) {
        setDisplayOptions(r3z ? 16 : 0, Base64.URL_SAFE);
    }

    public void setDisplayShowHomeEnabled(boolean r3z) {
        setDisplayOptions(r3z ? 2 : 0, XListViewHeader.STATE_REFRESHING);
    }

    public void setDisplayShowTitleEnabled(boolean r3z) {
        setDisplayOptions(r3z ? 8 : 0, Base64.DONT_BREAK_LINES);
    }

    public void setDisplayUseLogoEnabled(boolean r3z) {
        setDisplayOptions(r3z ? 1 : 0, 1);
    }

    public void setHomeButtonEnabled(boolean r2z) {
        this.k.setHomeButtonEnabled(r2z);
    }

    public void setIcon(int r2i) {
        this.k.setIcon(r2i);
    }

    public void setIcon(Drawable r2_Drawable) {
        this.k.setIcon(r2_Drawable);
    }

    public void setListNavigationCallbacks(SpinnerAdapter r2_SpinnerAdapter, OnNavigationListener r3_OnNavigationListener) {
        this.k.setDropdownAdapter(r2_SpinnerAdapter);
        this.k.setCallback(r3_OnNavigationListener);
    }

    public void setLogo(int r2i) {
        this.k.setLogo(r2i);
    }

    public void setLogo(Drawable r2_Drawable) {
        this.k.setLogo(r2_Drawable);
    }

    public void setNavigationMode(int r5i) {
        boolean r0z = false;
        switch (this.k.getNavigationMode()) {
            case XListViewHeader.STATE_REFRESHING:
                this.q = getSelectedNavigationIndex();
                selectTab(null);
                this.n.setVisibility(Base64.DONT_BREAK_LINES);
                break;
        }
        this.k.setNavigationMode(r5i);
        switch (r5i) {
            case XListViewHeader.STATE_REFRESHING:
                d();
                this.n.setVisibility(0);
                if (this.q != -1) {
                    setSelectedNavigationItem(this.q);
                    this.q = -1;
                }
                break;
        }
        ActionBarView r1_ActionBarView = this.k;
        if (r5i != 2 || this.u) {
            r1_ActionBarView.setCollapsable(r0z);
        } else {
            r0z = true;
            r1_ActionBarView.setCollapsable(r0z);
        }
    }

    public void setSelectedNavigationItem(int r3i) {
        switch (this.k.getNavigationMode()) {
            case XListViewHeader.STATE_READY:
                this.k.setDropdownSelectedPosition(r3i);
                return;
            case XListViewHeader.STATE_REFRESHING:
                selectTab((Tab) this.o.get(r3i));
                return;
        }
        throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
    }

    public void setShowHideAnimationEnabled(boolean r2z) {
        this.A = r2z;
        if (!r2z) {
            this.j.clearAnimation();
            if (this.m != null) {
                this.m.clearAnimation();
            }
        }
    }

    public void setSubtitle(int r2i) {
        setSubtitle(this.e.getString(r2i));
    }

    public void setSubtitle(CharSequence r2_CharSequence) {
        this.k.setSubtitle(r2_CharSequence);
    }

    public void setTitle(int r2i) {
        setTitle(this.e.getString(r2i));
    }

    public void setTitle(CharSequence r2_CharSequence) {
        this.k.setTitle(r2_CharSequence);
    }

    public void show() {
        if (this.w) {
            this.w = false;
            c(false);
        }
    }

    public ActionMode startActionMode(Callback r4_Callback) {
        if (this.a != null) {
            this.a.finish();
        }
        this.l.killMode();
        ActionMode r0_ActionMode = new a(r4_Callback);
        if (!r0_ActionMode.dispatchOnCreate()) {
            return null;
        }
        r0_ActionMode.invalidate();
        this.l.initForMode(r0_ActionMode);
        a(true);
        if (this.m == null || this.t != 1 || this.m.getVisibility() == 0) {
            this.l.sendAccessibilityEvent(Base64.ORDERED);
            this.a = r0_ActionMode;
            return r0_ActionMode;
        } else {
            this.m.setVisibility(0);
            this.l.sendAccessibilityEvent(Base64.ORDERED);
            this.a = r0_ActionMode;
            return r0_ActionMode;
        }
    }
}