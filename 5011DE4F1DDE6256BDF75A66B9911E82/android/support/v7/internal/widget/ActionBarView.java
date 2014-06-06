package android.support.v7.internal.widget;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.ActionMenuItem;
import android.support.v7.internal.view.menu.ActionMenuPresenter;
import android.support.v7.internal.view.menu.ActionMenuView;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.internal.widget.AdapterViewICS.OnItemSelectedListener;
import android.support.v7.view.CollapsibleActionView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window.Callback;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.qiubai.library.adview.util.AdViewUtil;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class ActionBarView extends a {
    public static final int DISPLAY_DEFAULT = 0;
    private ProgressBarICS A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private boolean H;
    private boolean I;
    private boolean J;
    private boolean K;
    private MenuBuilder L;
    private ActionBarContextView M;
    private ActionMenuItem N;
    private SpinnerAdapter O;
    private OnNavigationListener P;
    private Runnable Q;
    private a R;
    private final OnItemSelectedListener S;
    private final OnClickListener T;
    private final OnClickListener U;
    View g;
    Callback h;
    private int i;
    private int j;
    private CharSequence k;
    private CharSequence l;
    private Drawable m;
    private Drawable n;
    private Context o;
    private HomeView p;
    private HomeView q;
    private LinearLayout r;
    private TextView s;
    private TextView t;
    private View u;
    private p v;
    private LinearLayout w;
    private ScrollingTabContainerView x;
    private View y;
    private ProgressBarICS z;

    private static class HomeView extends FrameLayout {
        private ImageView a;
        private ImageView b;
        private int c;
        private int d;
        private Drawable e;

        public HomeView(Context r2_Context) {
            this(r2_Context, null);
        }

        public HomeView(Context r1_Context, AttributeSet r2_AttributeSet) {
            super(r1_Context, r2_AttributeSet);
        }

        public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent r3_AccessibilityEvent) {
            CharSequence r0_CharSequence = getContentDescription();
            if (!TextUtils.isEmpty(r0_CharSequence)) {
                r3_AccessibilityEvent.getText().add(r0_CharSequence);
            }
            return true;
        }

        public int getLeftOffset() {
            return this.a.getVisibility() == 8 ? this.c : 0;
        }

        protected void onConfigurationChanged(Configuration r2_Configuration) {
            super.onConfigurationChanged(r2_Configuration);
            if (this.d != 0) {
                setUpIndicator(this.d);
            }
        }

        protected void onFinishInflate() {
            this.a = (ImageView) findViewById(R.id.up);
            this.b = (ImageView) findViewById(R.id.home);
            this.e = this.a.getDrawable();
        }

        protected void onLayout(boolean r9z, int r10i, int r11i, int r12i, int r13i) {
            LayoutParams r0_LayoutParams;
            int r4i;
            int r0i;
            int r1i = 0;
            int r2i = (r13i - r11i) / 2;
            int r3i = r12i - r10i;
            if (this.a.getVisibility() != 8) {
                r0_LayoutParams = (LayoutParams) this.a.getLayoutParams();
                r4i = this.a.getMeasuredHeight();
                int r5i = this.a.getMeasuredWidth();
                int r6i = r2i - r4i / 2;
                this.a.layout(r1i, r6i, r5i, r4i + r6i);
                r0i = r0_LayoutParams.rightMargin + r0_LayoutParams.leftMargin + r5i;
                r1i = r3i - r0i;
                r10i += r0i;
                r1i = r0i;
            }
            r0_LayoutParams = (LayoutParams) this.b.getLayoutParams();
            r3i = this.b.getMeasuredHeight();
            r4i = this.b.getMeasuredWidth();
            r1i += Math.max(r0_LayoutParams.leftMargin, (r12i - r10i) / 2 - r4i / 2);
            r0i = Math.max(r0_LayoutParams.topMargin, r2i - r3i / 2);
            this.b.layout(r1i, r0i, r4i + r1i, r3i + r0i);
        }

        protected void onMeasure(int r11i, int r12i) {
            int r7i;
            int r3i = 0;
            measureChildWithMargins(this.a, r11i, 0, r12i, 0);
            LayoutParams r0_LayoutParams = (LayoutParams) this.a.getLayoutParams();
            this.c = r0_LayoutParams.leftMargin + this.a.getMeasuredWidth() + r0_LayoutParams.rightMargin;
            r7i = this.a.getVisibility() == Base64.DONT_BREAK_LINES ? 0 : this.c;
            measureChildWithMargins(this.b, r11i, r7i, r12i, r3i);
            r0_LayoutParams = (LayoutParams) this.b.getLayoutParams();
            r3i = r7i + r0_LayoutParams.leftMargin + this.b.getMeasuredWidth() + r0_LayoutParams.rightMargin;
            int r1i = Math.max(r0_LayoutParams.topMargin + this.a.getMeasuredHeight() + r0_LayoutParams.bottomMargin, r0_LayoutParams.bottomMargin + r0_LayoutParams.topMargin + this.b.getMeasuredHeight());
            int r4i = MeasureSpec.getMode(r11i);
            int r5i = MeasureSpec.getMode(r12i);
            int r2i = MeasureSpec.getSize(r11i);
            int r0i = MeasureSpec.getSize(r12i);
            switch (r4i) {
                case ExploreByTouchHelper.INVALID_ID:
                    r2i = Math.min(r3i, r2i);
                    break;
                case 1073741824:
                    break;
                default:
                    r2i = r3i;
                    break;
            }
            switch (r5i) {
                case ExploreByTouchHelper.INVALID_ID:
                    r0i = Math.min(r1i, r0i);
                    break;
                case 1073741824:
                    break;
                default:
                    r0i = r1i;
                    break;
            }
            setMeasuredDimension(r2i, r0i);
        }

        public void setIcon(Drawable r2_Drawable) {
            this.b.setImageDrawable(r2_Drawable);
        }

        public void setUp(boolean r3z) {
            this.a.setVisibility(r3z ? 0 : Base64.DONT_BREAK_LINES);
        }

        public void setUpIndicator(int r3i) {
            this.d = r3i;
            this.a.setImageDrawable(r3i != 0 ? getResources().getDrawable(r3i) : null);
        }

        public void setUpIndicator(Drawable r2_Drawable) {
            ImageView r0_ImageView = this.a;
            if (r2_Drawable != null) {
                r0_ImageView.setImageDrawable(r2_Drawable);
                this.d = 0;
            } else {
                r2_Drawable = this.e;
                r0_ImageView.setImageDrawable(r2_Drawable);
                this.d = 0;
            }
        }
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        int a;
        boolean b;

        static {
            CREATOR = new h();
        }

        private SavedState(Parcel r2_Parcel) {
            super(r2_Parcel);
            this.a = r2_Parcel.readInt();
            this.b = r2_Parcel.readInt() != 0;
        }

        SavedState(Parcelable r1_Parcelable) {
            super(r1_Parcelable);
        }

        public void writeToParcel(Parcel r2_Parcel, int r3i) {
            super.writeToParcel(r2_Parcel, r3i);
            r2_Parcel.writeInt(this.a);
            r2_Parcel.writeInt(this.b ? 1 : 0);
        }
    }

    private class a implements MenuPresenter {
        MenuBuilder a;
        MenuItemImpl b;

        private a() {
        }

        public boolean collapseItemActionView(MenuBuilder r6_MenuBuilder, MenuItemImpl r7_MenuItemImpl) {
            if (ActionBarView.this.g instanceof CollapsibleActionView) {
                ((CollapsibleActionView) ActionBarView.this.g).onActionViewCollapsed();
            }
            ActionBarView.this.removeView(ActionBarView.this.g);
            ActionBarView.this.removeView(ActionBarView.this.q);
            ActionBarView.this.g = null;
            if ((ActionBarView.this.j & 2) != 0) {
                ActionBarView.this.p.setVisibility(0);
            }
            if ((ActionBarView.this.j & 8) != 0) {
                if (ActionBarView.this.r == null) {
                    ActionBarView.this.a();
                } else {
                    ActionBarView.this.r.setVisibility(0);
                }
            }
            if (ActionBarView.this.x == null || ActionBarView.this.i != 2) {
                if (ActionBarView.this.v == null || ActionBarView.this.i != 1) {
                    if (ActionBarView.this.y == null || (ActionBarView.this.j & 16) == 0) {
                        ActionBarView.this.q.setIcon(null);
                        this.b = null;
                        ActionBarView.this.requestLayout();
                        r7_MenuItemImpl.setActionViewExpanded(false);
                        return true;
                    } else {
                        ActionBarView.this.y.setVisibility(0);
                        ActionBarView.this.q.setIcon(null);
                        this.b = null;
                        ActionBarView.this.requestLayout();
                        r7_MenuItemImpl.setActionViewExpanded(false);
                        return true;
                    }
                } else {
                    ActionBarView.this.v.setVisibility(0);
                    if (ActionBarView.this.y == null || (ActionBarView.this.j & 16) == 0) {
                        ActionBarView.this.q.setIcon(null);
                        this.b = null;
                        ActionBarView.this.requestLayout();
                        r7_MenuItemImpl.setActionViewExpanded(false);
                        return true;
                    } else {
                        ActionBarView.this.y.setVisibility(0);
                        ActionBarView.this.q.setIcon(null);
                        this.b = null;
                        ActionBarView.this.requestLayout();
                        r7_MenuItemImpl.setActionViewExpanded(false);
                        return true;
                    }
                }
            } else {
                ActionBarView.this.x.setVisibility(0);
                if (ActionBarView.this.v == null || ActionBarView.this.i != 1) {
                    if (ActionBarView.this.y == null || (ActionBarView.this.j & 16) == 0) {
                        ActionBarView.this.q.setIcon(null);
                        this.b = null;
                        ActionBarView.this.requestLayout();
                        r7_MenuItemImpl.setActionViewExpanded(false);
                        return true;
                    } else {
                        ActionBarView.this.y.setVisibility(0);
                        ActionBarView.this.q.setIcon(null);
                        this.b = null;
                        ActionBarView.this.requestLayout();
                        r7_MenuItemImpl.setActionViewExpanded(false);
                        return true;
                    }
                } else {
                    ActionBarView.this.v.setVisibility(0);
                    if (ActionBarView.this.y == null || (ActionBarView.this.j & 16) == 0) {
                        ActionBarView.this.q.setIcon(null);
                        this.b = null;
                        ActionBarView.this.requestLayout();
                        r7_MenuItemImpl.setActionViewExpanded(false);
                        return true;
                    } else {
                        ActionBarView.this.y.setVisibility(0);
                        ActionBarView.this.q.setIcon(null);
                        this.b = null;
                        ActionBarView.this.requestLayout();
                        r7_MenuItemImpl.setActionViewExpanded(false);
                        return true;
                    }
                }
            }
        }

        public boolean expandItemActionView(MenuBuilder r6_MenuBuilder, MenuItemImpl r7_MenuItemImpl) {
            ActionBarView.this.g = r7_MenuItemImpl.getActionView();
            ActionBarView.this.q.setIcon(ActionBarView.this.m.getConstantState().newDrawable(ActionBarView.this.getResources()));
            this.b = r7_MenuItemImpl;
            if (ActionBarView.this.g.getParent() != ActionBarView.this) {
                ActionBarView.this.addView(ActionBarView.this.g);
            }
            if (ActionBarView.this.q.getParent() != ActionBarView.this) {
                ActionBarView.this.addView(ActionBarView.this.q);
            }
            ActionBarView.this.p.setVisibility(Base64.DONT_BREAK_LINES);
            if (ActionBarView.this.r != null) {
                ActionBarView.this.r.setVisibility(Base64.DONT_BREAK_LINES);
            }
            if (ActionBarView.this.x != null) {
                ActionBarView.this.x.setVisibility(Base64.DONT_BREAK_LINES);
            }
            if (ActionBarView.this.v != null) {
                ActionBarView.this.v.setVisibility(Base64.DONT_BREAK_LINES);
            }
            if (ActionBarView.this.y != null) {
                ActionBarView.this.y.setVisibility(Base64.DONT_BREAK_LINES);
            }
            ActionBarView.this.requestLayout();
            r7_MenuItemImpl.setActionViewExpanded(true);
            if (ActionBarView.this.g instanceof CollapsibleActionView) {
                ((CollapsibleActionView) ActionBarView.this.g).onActionViewExpanded();
            }
            return true;
        }

        public boolean flagActionItems() {
            return false;
        }

        public int getId() {
            return 0;
        }

        public MenuView getMenuView(ViewGroup r2_ViewGroup) {
            return null;
        }

        public void initForMenu(Context r3_Context, MenuBuilder r4_MenuBuilder) {
            if (this.a == null || this.b == null) {
                this.a = r4_MenuBuilder;
            } else {
                this.a.collapseItemActionView(this.b);
                this.a = r4_MenuBuilder;
            }
        }

        public void onCloseMenu(MenuBuilder r1_MenuBuilder, boolean r2z) {
        }

        public void onRestoreInstanceState(Parcelable r1_Parcelable) {
        }

        public Parcelable onSaveInstanceState() {
            return null;
        }

        public boolean onSubMenuSelected(SubMenuBuilder r2_SubMenuBuilder) {
            return false;
        }

        public void setCallback(MenuPresenter.Callback r1_MenuPresenter_Callback) {
        }

        public void updateMenuView(boolean r6z) {
            if (this.b != null) {
                int r0i;
                if (this.a != null) {
                    int r3i = this.a.size();
                    int r2i = 0;
                    while (r2i < r3i) {
                        if (((SupportMenuItem) this.a.getItem(r2i)) == this.b) {
                            r0i = 1;
                            break;
                        } else {
                            r2i++;
                        }
                    }
                    r0i = 0;
                } else {
                    r0i = 0;
                }
                if (r0i == 0) {
                    collapseItemActionView(this.a, this.b);
                }
            }
        }
    }

    public ActionBarView(Context r10_Context, AttributeSet r11_AttributeSet) {
        super(r10_Context, r11_AttributeSet);
        this.j = -1;
        this.S = new e(this);
        this.T = new f(this);
        this.U = new g(this);
        this.o = r10_Context;
        setBackgroundResource(0);
        TypedArray r2_TypedArray = r10_Context.obtainStyledAttributes(r11_AttributeSet, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        ApplicationInfo r4_ApplicationInfo = r10_Context.getApplicationInfo();
        PackageManager r5_PackageManager = r10_Context.getPackageManager();
        this.i = r2_TypedArray.getInt(XListViewHeader.STATE_REFRESHING, 0);
        this.k = r2_TypedArray.getText(0);
        this.l = r2_TypedArray.getText(XListViewFooter.STATE_NODATA);
        this.n = r2_TypedArray.getDrawable(Base64.DONT_BREAK_LINES);
        LayoutInflater r4_LayoutInflater;
        int r5i;
        int r1i;
        if (this.n != null || VERSION.SDK_INT < 9) {
            this.m = r2_TypedArray.getDrawable(ShareUtils.SHARE_COLLECT);
            if (this.m != null) {
                if (!r10_Context instanceof Activity) {
                    try {
                        this.m = r5_PackageManager.getActivityIcon(((Activity) r10_Context).getComponentName());
                    } catch (NameNotFoundException e) {
                        Log.e("ActionBarView", "Activity component name not found!", e);
                    }
                }
                if (this.m == null) {
                    this.m = r4_ApplicationInfo.loadIcon(r5_PackageManager);
                }
            }
            r4_LayoutInflater = LayoutInflater.from(r10_Context);
            r5i = r2_TypedArray.getResourceId(REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION, R.layout.abc_action_bar_home);
            this.p = (HomeView) r4_LayoutInflater.inflate(r5i, this, false);
            this.q = (HomeView) r4_LayoutInflater.inflate(r5i, this, false);
            this.q.setUp(true);
            this.q.setOnClickListener(this.T);
            this.q.setContentDescription(getResources().getText(R.string.abc_action_bar_up_description));
            this.D = r2_TypedArray.getResourceId(ShareUtils.SHARE_SMS, 0);
            this.E = r2_TypedArray.getResourceId(ShareUtils.SHARE_COPY, 0);
            this.F = r2_TypedArray.getResourceId(NearbySelectView.TIME_15MIN, 0);
            this.G = r2_TypedArray.getResourceId(Base64.URL_SAFE, 0);
            this.B = r2_TypedArray.getDimensionPixelOffset(qsbk.app.R.styleable.ActionBar_progressBarPadding, 0);
            this.C = r2_TypedArray.getDimensionPixelOffset(qsbk.app.R.styleable.ActionBar_itemPadding, 0);
            setDisplayOptions(r2_TypedArray.getInt(XListViewFooter.STATE_NOMORE, 0));
            r1i = r2_TypedArray.getResourceId(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER, 0);
            if (r1i != 0) {
                this.y = r4_LayoutInflater.inflate(r1i, this, false);
                this.i = 0;
                setDisplayOptions(this.j | 16);
            }
            this.f = r2_TypedArray.getLayoutDimension(1, 0);
            r2_TypedArray.recycle();
            this.N = new ActionMenuItem(r10_Context, 0, 16908332, 0, 0, this.k);
            this.p.setOnClickListener(this.U);
            this.p.setClickable(true);
            this.p.setFocusable(true);
        } else {
            if (r10_Context instanceof Activity) {
                try {
                    this.n = r5_PackageManager.getActivityLogo(((Activity) r10_Context).getComponentName());
                } catch (NameNotFoundException e_2) {
                    Log.e("ActionBarView", "Activity component name not found!", e_2);
                }
            }
            if (this.n == null) {
                this.n = r4_ApplicationInfo.loadLogo(r5_PackageManager);
            }
            this.m = r2_TypedArray.getDrawable(ShareUtils.SHARE_COLLECT);
            if (this.m != null) {
                r4_LayoutInflater = LayoutInflater.from(r10_Context);
                r5i = r2_TypedArray.getResourceId(REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION, R.layout.abc_action_bar_home);
                this.p = (HomeView) r4_LayoutInflater.inflate(r5i, this, false);
                this.q = (HomeView) r4_LayoutInflater.inflate(r5i, this, false);
                this.q.setUp(true);
                this.q.setOnClickListener(this.T);
                this.q.setContentDescription(getResources().getText(R.string.abc_action_bar_up_description));
                this.D = r2_TypedArray.getResourceId(ShareUtils.SHARE_SMS, 0);
                this.E = r2_TypedArray.getResourceId(ShareUtils.SHARE_COPY, 0);
                this.F = r2_TypedArray.getResourceId(NearbySelectView.TIME_15MIN, 0);
                this.G = r2_TypedArray.getResourceId(Base64.URL_SAFE, 0);
                this.B = r2_TypedArray.getDimensionPixelOffset(qsbk.app.R.styleable.ActionBar_progressBarPadding, 0);
                this.C = r2_TypedArray.getDimensionPixelOffset(qsbk.app.R.styleable.ActionBar_itemPadding, 0);
                setDisplayOptions(r2_TypedArray.getInt(XListViewFooter.STATE_NOMORE, 0));
                r1i = r2_TypedArray.getResourceId(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER, 0);
                if (r1i != 0) {
                    this.f = r2_TypedArray.getLayoutDimension(1, 0);
                    r2_TypedArray.recycle();
                    this.N = new ActionMenuItem(r10_Context, 0, 16908332, 0, 0, this.k);
                    this.p.setOnClickListener(this.U);
                    this.p.setClickable(true);
                    this.p.setFocusable(true);
                } else {
                    this.y = r4_LayoutInflater.inflate(r1i, this, false);
                    this.i = 0;
                    setDisplayOptions(this.j | 16);
                    this.f = r2_TypedArray.getLayoutDimension(1, 0);
                    r2_TypedArray.recycle();
                    this.N = new ActionMenuItem(r10_Context, 0, 16908332, 0, 0, this.k);
                    this.p.setOnClickListener(this.U);
                    this.p.setClickable(true);
                    this.p.setFocusable(true);
                }
            } else if (r10_Context instanceof Activity) {
                if (this.m == null) {
                    r4_LayoutInflater = LayoutInflater.from(r10_Context);
                    r5i = r2_TypedArray.getResourceId(REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION, R.layout.abc_action_bar_home);
                    this.p = (HomeView) r4_LayoutInflater.inflate(r5i, this, false);
                    this.q = (HomeView) r4_LayoutInflater.inflate(r5i, this, false);
                    this.q.setUp(true);
                    this.q.setOnClickListener(this.T);
                    this.q.setContentDescription(getResources().getText(R.string.abc_action_bar_up_description));
                    this.D = r2_TypedArray.getResourceId(ShareUtils.SHARE_SMS, 0);
                    this.E = r2_TypedArray.getResourceId(ShareUtils.SHARE_COPY, 0);
                    this.F = r2_TypedArray.getResourceId(NearbySelectView.TIME_15MIN, 0);
                    this.G = r2_TypedArray.getResourceId(Base64.URL_SAFE, 0);
                    this.B = r2_TypedArray.getDimensionPixelOffset(qsbk.app.R.styleable.ActionBar_progressBarPadding, 0);
                    this.C = r2_TypedArray.getDimensionPixelOffset(qsbk.app.R.styleable.ActionBar_itemPadding, 0);
                    setDisplayOptions(r2_TypedArray.getInt(XListViewFooter.STATE_NOMORE, 0));
                    r1i = r2_TypedArray.getResourceId(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER, 0);
                    if (r1i != 0) {
                        this.y = r4_LayoutInflater.inflate(r1i, this, false);
                        this.i = 0;
                        setDisplayOptions(this.j | 16);
                    }
                    this.f = r2_TypedArray.getLayoutDimension(1, 0);
                    r2_TypedArray.recycle();
                    this.N = new ActionMenuItem(r10_Context, 0, 16908332, 0, 0, this.k);
                    this.p.setOnClickListener(this.U);
                    this.p.setClickable(true);
                    this.p.setFocusable(true);
                } else {
                    this.m = r4_ApplicationInfo.loadIcon(r5_PackageManager);
                    r4_LayoutInflater = LayoutInflater.from(r10_Context);
                    r5i = r2_TypedArray.getResourceId(REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION, R.layout.abc_action_bar_home);
                    this.p = (HomeView) r4_LayoutInflater.inflate(r5i, this, false);
                    this.q = (HomeView) r4_LayoutInflater.inflate(r5i, this, false);
                    this.q.setUp(true);
                    this.q.setOnClickListener(this.T);
                    this.q.setContentDescription(getResources().getText(R.string.abc_action_bar_up_description));
                    this.D = r2_TypedArray.getResourceId(ShareUtils.SHARE_SMS, 0);
                    this.E = r2_TypedArray.getResourceId(ShareUtils.SHARE_COPY, 0);
                    this.F = r2_TypedArray.getResourceId(NearbySelectView.TIME_15MIN, 0);
                    this.G = r2_TypedArray.getResourceId(Base64.URL_SAFE, 0);
                    this.B = r2_TypedArray.getDimensionPixelOffset(qsbk.app.R.styleable.ActionBar_progressBarPadding, 0);
                    this.C = r2_TypedArray.getDimensionPixelOffset(qsbk.app.R.styleable.ActionBar_itemPadding, 0);
                    setDisplayOptions(r2_TypedArray.getInt(XListViewFooter.STATE_NOMORE, 0));
                    r1i = r2_TypedArray.getResourceId(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER, 0);
                    if (r1i != 0) {
                        this.f = r2_TypedArray.getLayoutDimension(1, 0);
                        r2_TypedArray.recycle();
                        this.N = new ActionMenuItem(r10_Context, 0, 16908332, 0, 0, this.k);
                        this.p.setOnClickListener(this.U);
                        this.p.setClickable(true);
                        this.p.setFocusable(true);
                    } else {
                        this.y = r4_LayoutInflater.inflate(r1i, this, false);
                        this.i = 0;
                        setDisplayOptions(this.j | 16);
                        this.f = r2_TypedArray.getLayoutDimension(1, 0);
                        r2_TypedArray.recycle();
                        this.N = new ActionMenuItem(r10_Context, 0, 16908332, 0, 0, this.k);
                        this.p.setOnClickListener(this.U);
                        this.p.setClickable(true);
                        this.p.setFocusable(true);
                    }
                }
            } else {
                this.m = r5_PackageManager.getActivityIcon(((Activity) r10_Context).getComponentName());
                if (this.m == null) {
                    this.m = r4_ApplicationInfo.loadIcon(r5_PackageManager);
                }
                r4_LayoutInflater = LayoutInflater.from(r10_Context);
                r5i = r2_TypedArray.getResourceId(REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION, R.layout.abc_action_bar_home);
                this.p = (HomeView) r4_LayoutInflater.inflate(r5i, this, false);
                this.q = (HomeView) r4_LayoutInflater.inflate(r5i, this, false);
                this.q.setUp(true);
                this.q.setOnClickListener(this.T);
                this.q.setContentDescription(getResources().getText(R.string.abc_action_bar_up_description));
                this.D = r2_TypedArray.getResourceId(ShareUtils.SHARE_SMS, 0);
                this.E = r2_TypedArray.getResourceId(ShareUtils.SHARE_COPY, 0);
                this.F = r2_TypedArray.getResourceId(NearbySelectView.TIME_15MIN, 0);
                this.G = r2_TypedArray.getResourceId(Base64.URL_SAFE, 0);
                this.B = r2_TypedArray.getDimensionPixelOffset(qsbk.app.R.styleable.ActionBar_progressBarPadding, 0);
                this.C = r2_TypedArray.getDimensionPixelOffset(qsbk.app.R.styleable.ActionBar_itemPadding, 0);
                setDisplayOptions(r2_TypedArray.getInt(XListViewFooter.STATE_NOMORE, 0));
                r1i = r2_TypedArray.getResourceId(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER, 0);
                if (r1i != 0) {
                    this.y = r4_LayoutInflater.inflate(r1i, this, false);
                    this.i = 0;
                    setDisplayOptions(this.j | 16);
                }
                this.f = r2_TypedArray.getLayoutDimension(1, 0);
                r2_TypedArray.recycle();
                this.N = new ActionMenuItem(r10_Context, 0, 16908332, 0, 0, this.k);
                this.p.setOnClickListener(this.U);
                this.p.setClickable(true);
                this.p.setFocusable(true);
            }
        }
    }

    private void a() {
        boolean r1z = true;
        if (this.r == null) {
            int r0i;
            int r3i;
            int r4i;
            this.r = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.abc_action_bar_title_item, this, false);
            this.s = (TextView) this.r.findViewById(R.id.action_bar_title);
            this.t = (TextView) this.r.findViewById(R.id.action_bar_subtitle);
            this.u = this.r.findViewById(R.id.up);
            this.r.setOnClickListener(this.U);
            if (this.D != 0) {
                this.s.setTextAppearance(this.o, this.D);
            }
            if (this.k != null) {
                this.s.setText(this.k);
            }
            if (this.E != 0) {
                this.t.setTextAppearance(this.o, this.E);
            }
            if (this.l != null) {
                this.t.setText(this.l);
                this.t.setVisibility(0);
            }
            r0i = (this.j & 4) != 0 ? 1 : 0;
            r3i = (this.j & 2) != 0 ? 1 : 0;
            View r6_View = this.u;
            if (r3i == 0) {
                r4i = r0i != 0 ? 0 : XListViewFooter.STATE_NODATA;
            } else {
                r4i = 8;
            }
            r6_View.setVisibility(r4i);
            LinearLayout r4_LinearLayout = this.r;
            if (r0i == 0 || r3i != 0) {
                r1z = false;
                r4_LinearLayout.setEnabled(r1z);
            } else {
                r4_LinearLayout.setEnabled(r1z);
            }
        }
        addView(this.r);
        if (this.g == null) {
            if (!(TextUtils.isEmpty(this.k) && TextUtils.isEmpty(this.l))) {
                return;
            }
        }
        this.r.setVisibility(Base64.DONT_BREAK_LINES);
    }

    private void a(MenuBuilder r5_MenuBuilder) {
        if (r5_MenuBuilder != null) {
            r5_MenuBuilder.addMenuPresenter(this.b);
            r5_MenuBuilder.addMenuPresenter(this.R);
        } else {
            this.b.initForMenu(this.o, null);
            this.R.initForMenu(this.o, null);
        }
        this.b.updateMenuView(true);
        this.R.updateMenuView(true);
    }

    private void a(CharSequence r4_CharSequence) {
        int r1i = 0;
        this.k = r4_CharSequence;
        if (this.s != null) {
            int r0i;
            this.s.setText(r4_CharSequence);
            if (this.g != null || (this.j & 8) == 0) {
                r0i = 0;
            } else if (TextUtils.isEmpty(this.k) && TextUtils.isEmpty(this.l)) {
                r0i = 0;
            } else {
                r0i = 1;
            }
            LinearLayout r2_LinearLayout = this.r;
            if (r0i != 0) {
                r2_LinearLayout.setVisibility(r1i);
            } else {
                r1i = Base64.DONT_BREAK_LINES;
                r2_LinearLayout.setVisibility(r1i);
            }
        }
        if (this.N != null) {
            this.N.setTitle(r4_CharSequence);
        }
    }

    public /* bridge */ /* synthetic */ void animateToVisibility(int r1i) {
        super.animateToVisibility(r1i);
    }

    public void collapseActionView() {
        MenuItemImpl r0_MenuItemImpl;
        r0_MenuItemImpl = this.R == null ? null : this.R.b;
        if (r0_MenuItemImpl != null) {
            r0_MenuItemImpl.collapseActionView();
        }
    }

    public /* bridge */ /* synthetic */ void dismissPopupMenus() {
        super.dismissPopupMenus();
    }

    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ActionBar.LayoutParams(19);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet r3_AttributeSet) {
        return new ActionBar.LayoutParams(getContext(), r3_AttributeSet);
    }

    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1_ViewGroup_LayoutParams) {
        return r1_ViewGroup_LayoutParams == null ? generateDefaultLayoutParams() : r1_ViewGroup_LayoutParams;
    }

    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public View getCustomNavigationView() {
        return this.y;
    }

    public int getDisplayOptions() {
        return this.j;
    }

    public SpinnerAdapter getDropdownAdapter() {
        return this.O;
    }

    public int getDropdownSelectedPosition() {
        return this.v.getSelectedItemPosition();
    }

    public int getNavigationMode() {
        return this.i;
    }

    public CharSequence getSubtitle() {
        return this.l;
    }

    public CharSequence getTitle() {
        return this.k;
    }

    public boolean hasEmbeddedTabs() {
        return this.I;
    }

    public boolean hasExpandedActionView() {
        return this.R != null && this.R.b != null;
    }

    public /* bridge */ /* synthetic */ boolean hideOverflowMenu() {
        return super.hideOverflowMenu();
    }

    public void initIndeterminateProgress() {
        this.A = new ProgressBarICS(this.o, null, 0, this.G);
        this.A.setId(R.id.progress_circular);
        this.A.setVisibility(Base64.DONT_BREAK_LINES);
        addView(this.A);
    }

    public void initProgress() {
        this.z = new ProgressBarICS(this.o, null, 0, this.F);
        this.z.setId(R.id.progress_horizontal);
        this.z.setMax(10000);
        this.z.setVisibility(Base64.DONT_BREAK_LINES);
        addView(this.z);
    }

    public boolean isCollapsed() {
        return this.K;
    }

    public /* bridge */ /* synthetic */ boolean isOverflowMenuShowing() {
        return super.isOverflowMenuShowing();
    }

    public /* bridge */ /* synthetic */ boolean isOverflowReserved() {
        return super.isOverflowReserved();
    }

    public boolean isSplitActionBar() {
        return this.d;
    }

    protected void onConfigurationChanged(Configuration r3_Configuration) {
        super.onConfigurationChanged(r3_Configuration);
        this.s = null;
        this.t = null;
        this.u = null;
        ViewGroup.LayoutParams r0_ViewGroup_LayoutParams;
        if (this.r == null || this.r.getParent() != this) {
            this.r = null;
            if ((this.j & 8) == 0) {
                a();
            }
            if (this.x == null || (!this.I)) {
                if (this.z != null) {
                    removeView(this.z);
                    initProgress();
                }
                if (this.A == null) {
                    removeView(this.A);
                    initIndeterminateProgress();
                }
            } else {
                r0_ViewGroup_LayoutParams = this.x.getLayoutParams();
                if (r0_ViewGroup_LayoutParams != null) {
                    r0_ViewGroup_LayoutParams.width = -2;
                    r0_ViewGroup_LayoutParams.height = -1;
                }
                this.x.setAllowCollapse(true);
                if (this.z != null) {
                    if (this.A == null) {
                    } else {
                        removeView(this.A);
                        initIndeterminateProgress();
                    }
                } else {
                    removeView(this.z);
                    initProgress();
                    if (this.A == null) {
                        removeView(this.A);
                        initIndeterminateProgress();
                    }
                }
            }
        } else {
            removeView(this.r);
            this.r = null;
            if ((this.j & 8) == 0) {
                if (this.x == null || this.I) {
                    if (this.z != null) {
                        removeView(this.z);
                        initProgress();
                    }
                    if (this.A == null) {
                    } else {
                        removeView(this.A);
                        initIndeterminateProgress();
                    }
                } else {
                    r0_ViewGroup_LayoutParams = this.x.getLayoutParams();
                    if (r0_ViewGroup_LayoutParams != null) {
                        this.x.setAllowCollapse(true);
                        if (this.z != null) {
                            if (this.A == null) {
                                removeView(this.A);
                                initIndeterminateProgress();
                            }
                        } else {
                            removeView(this.z);
                            initProgress();
                            if (this.A == null) {
                            } else {
                                removeView(this.A);
                                initIndeterminateProgress();
                            }
                        }
                    } else {
                        r0_ViewGroup_LayoutParams.width = -2;
                        r0_ViewGroup_LayoutParams.height = -1;
                        this.x.setAllowCollapse(true);
                        if (this.z != null) {
                            removeView(this.z);
                            initProgress();
                        }
                        if (this.A == null) {
                            removeView(this.A);
                            initIndeterminateProgress();
                        }
                    }
                }
            } else {
                a();
                if (this.x == null || this.I) {
                    if (this.z != null) {
                        if (this.A == null) {
                        } else {
                            removeView(this.A);
                            initIndeterminateProgress();
                        }
                    } else {
                        removeView(this.z);
                        initProgress();
                        if (this.A == null) {
                            removeView(this.A);
                            initIndeterminateProgress();
                        }
                    }
                } else {
                    r0_ViewGroup_LayoutParams = this.x.getLayoutParams();
                    if (r0_ViewGroup_LayoutParams != null) {
                        r0_ViewGroup_LayoutParams.width = -2;
                        r0_ViewGroup_LayoutParams.height = -1;
                    }
                    this.x.setAllowCollapse(true);
                    if (this.z != null) {
                        removeView(this.z);
                        initProgress();
                    }
                    if (this.A == null) {
                    } else {
                        removeView(this.A);
                        initIndeterminateProgress();
                    }
                }
            }
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.Q);
        if (this.b != null) {
            this.b.hideOverflowMenu();
            this.b.hideSubMenus();
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        addView(this.p);
        if (this.y == null || (this.j & 16) == 0) {
        } else {
            ActionBarView r0_ActionBarView = this.y.getParent();
            if (r0_ActionBarView != this) {
                if (r0_ActionBarView instanceof ViewGroup) {
                    r0_ActionBarView.removeView(this.y);
                }
                addView(this.y);
            }
        }
    }

    protected void onLayout(boolean r10z, int r11i, int r12i, int r13i, int r14i) {
        int r1i = getPaddingLeft();
        int r2i = getPaddingTop();
        int r3i = r14i - r12i - getPaddingTop() - getPaddingBottom();
        if (r3i <= 0) {
        } else {
            View r0_View;
            int r4i;
            int r0i;
            View r7_View;
            ViewGroup.LayoutParams r0_ViewGroup_LayoutParams;
            ActionBar.LayoutParams r5_ActionBar_LayoutParams;
            int r8i;
            int r5i;
            int r6i;
            r0_View = this.g != null ? this.q : this.p;
            if (r0_View.getVisibility() != 8) {
                r4i = r0_View.getLeftOffset();
                r0i = b(r0_View, r1i + r4i, r2i, r3i) + r4i + r1i;
            } else {
                r0i = r1i;
            }
            if (this.g == null) {
                if (this.r == null || this.r.getVisibility() == 8 || (this.j & 8) == 0) {
                    r1i = 0;
                    if (r1i == 0) {
                        r0i += b(this.r, r0i, r2i, r3i);
                    }
                    switch (this.i) {
                        case XListViewHeader.STATE_NORMAL:
                            r1i = r0i;
                            break;
                        case XListViewHeader.STATE_READY:
                            if (this.w != null) {
                                if (r1i != 0) {
                                    r0i += this.C;
                                }
                                r1i = r0i + b(this.w, r0i, r2i, r3i) + this.C;
                            }
                            break;
                        case XListViewHeader.STATE_REFRESHING:
                            if (this.x != null) {
                                if (r1i != 0) {
                                    r0i += this.C;
                                }
                                r1i = r0i + b(this.x, r0i, r2i, r3i) + this.C;
                            }
                            break;
                    }
                    r1i = r0i;
                } else {
                    r1i = 1;
                    if (r1i == 0) {
                        switch (this.i) {
                            case XListViewHeader.STATE_NORMAL:
                                r1i = r0i;
                                break;
                            case XListViewHeader.STATE_READY:
                                if (this.w != null) {
                                    r1i = r0i;
                                } else if (r1i != 0) {
                                    r1i = r0i + b(this.w, r0i, r2i, r3i) + this.C;
                                } else {
                                    r0i += this.C;
                                    r1i = r0i + b(this.w, r0i, r2i, r3i) + this.C;
                                }
                                break;
                            case XListViewHeader.STATE_REFRESHING:
                                if (this.x != null) {
                                    r1i = r0i;
                                } else if (r1i != 0) {
                                    r1i = r0i + b(this.x, r0i, r2i, r3i) + this.C;
                                } else {
                                    r0i += this.C;
                                    r1i = r0i + b(this.x, r0i, r2i, r3i) + this.C;
                                }
                                break;
                        }
                        r1i = r0i;
                    }
                }
                r0i += b(this.r, r0i, r2i, r3i);
                switch (this.i) {
                    case XListViewHeader.STATE_NORMAL:
                        r1i = r0i;
                        break;
                    case XListViewHeader.STATE_READY:
                        if (this.w != null) {
                            if (r1i != 0) {
                                r0i += this.C;
                            }
                            r1i = r0i + b(this.w, r0i, r2i, r3i) + this.C;
                        }
                        break;
                    case XListViewHeader.STATE_REFRESHING:
                        if (this.x != null) {
                            if (r1i != 0) {
                                r0i += this.C;
                            }
                            r1i = r0i + b(this.x, r0i, r2i, r3i) + this.C;
                        }
                        break;
                }
                r1i = r0i;
            } else {
                r1i = r0i;
            }
            r0i = r13i - r11i - getPaddingRight();
            if (this.a == null || this.a.getParent() != this) {
            } else {
                c(this.a, r0i, r2i, r3i);
                r0i -= this.a.getMeasuredWidth();
            }
            if (this.A == null || this.A.getVisibility() == 8) {
                r2i = r0i;
            } else {
                c(this.A, r0i, r2i, r3i);
                r2i = r0i - this.A.getMeasuredWidth();
            }
            if (this.g != null) {
                r7_View = this.g;
            } else {
                r7_View = ((this.j & 16) == 0 || this.y == null) ? null : this.y;
                r0_ViewGroup_LayoutParams = r7_View.getLayoutParams();
                r5_ActionBar_LayoutParams = r0_ViewGroup_LayoutParams instanceof ActionBar.LayoutParams ? null : (ActionBar.LayoutParams) r0_ViewGroup_LayoutParams;
                r0i = r5_ActionBar_LayoutParams == null ? r5_ActionBar_LayoutParams.gravity : TimeUtils.HUNDRED_DAY_FIELD_LEN;
                r8i = r7_View.getMeasuredWidth();
                r4i = 0;
                r3i = 0;
                if (r5_ActionBar_LayoutParams == null) {
                    r5i = r5_ActionBar_LayoutParams.topMargin;
                    r6i = r2i - r5_ActionBar_LayoutParams.rightMargin;
                    r3i = r1i + r5_ActionBar_LayoutParams.leftMargin;
                    r4i = r5_ActionBar_LayoutParams.bottomMargin;
                } else {
                    r5i = r4i;
                    r6i = r2i;
                    r4i = r3i;
                    r3i = r1i;
                }
                r1i = r0i & 7;
                if (r1i != 1) {
                    r2i = (getWidth() - r8i) / 2;
                    if (r2i >= r3i) {
                        r1i = XListViewFooter.STATE_NOMORE;
                    } else if (r2i + r8i <= r6i) {
                        r1i = ShareUtils.SHARE_SMS;
                    }
                    r2i = r1i;
                } else if (r0i != -1) {
                }
                switch (r2i) {
                    case XListViewHeader.STATE_READY:
                        r2i = (getWidth() - r8i) / 2;
                        r1i = r0i & 112;
                        if (r0i != -1) {
                            r1i = 16;
                        }
                        r0i = 0;
                        switch (r1i) {
                            case Base64.URL_SAFE:
                                r0i = (((getHeight() - getPaddingBottom()) - getPaddingTop()) - r7_View.getMeasuredHeight()) / 2;
                                break;
                            case AdViewUtil.NETWORK_TYPE_ADUU:
                                r0i = getPaddingTop() + r5i;
                                break;
                            case 80:
                                r0i = getHeight() - getPaddingBottom() - r7_View.getMeasuredHeight() - r4i;
                                break;
                        }
                        r1i = r7_View.getMeasuredWidth();
                        r7_View.layout(r2i, r0i, r2i + r1i, r7_View.getMeasuredHeight() + r0i);
                        r0i = r3i + r1i;
                        if (this.z != null) {
                            this.z.bringToFront();
                            r0i = this.z.getMeasuredHeight() / 2;
                            this.z.layout(this.B, -r0i, this.B + this.z.getMeasuredWidth(), r0i);
                        }
                    case XListViewFooter.STATE_NOMORE:
                        r2i = r3i;
                        r1i = r0i & 112;
                        if (r0i != -1) {
                            r0i = 0;
                            switch (r1i) {
                                case Base64.URL_SAFE:
                                    r0i = (((getHeight() - getPaddingBottom()) - getPaddingTop()) - r7_View.getMeasuredHeight()) / 2;
                                    break;
                                case AdViewUtil.NETWORK_TYPE_ADUU:
                                    r0i = getPaddingTop() + r5i;
                                    break;
                                case 80:
                                    r0i = getHeight() - getPaddingBottom() - r7_View.getMeasuredHeight() - r4i;
                                    break;
                            }
                            r1i = r7_View.getMeasuredWidth();
                            r7_View.layout(r2i, r0i, r2i + r1i, r7_View.getMeasuredHeight() + r0i);
                            r0i = r3i + r1i;
                            if (this.z != null) {
                            } else {
                                this.z.bringToFront();
                                r0i = this.z.getMeasuredHeight() / 2;
                                this.z.layout(this.B, -r0i, this.B + this.z.getMeasuredWidth(), r0i);
                            }
                        } else {
                            r1i = 16;
                            r0i = 0;
                            switch (r1i) {
                                case Base64.URL_SAFE:
                                    r0i = (((getHeight() - getPaddingBottom()) - getPaddingTop()) - r7_View.getMeasuredHeight()) / 2;
                                    break;
                                case AdViewUtil.NETWORK_TYPE_ADUU:
                                    r0i = getPaddingTop() + r5i;
                                    break;
                                case 80:
                                    r0i = getHeight() - getPaddingBottom() - r7_View.getMeasuredHeight() - r4i;
                                    break;
                            }
                            r1i = r7_View.getMeasuredWidth();
                            r7_View.layout(r2i, r0i, r2i + r1i, r7_View.getMeasuredHeight() + r0i);
                            r0i = r3i + r1i;
                            if (this.z != null) {
                                this.z.bringToFront();
                                r0i = this.z.getMeasuredHeight() / 2;
                                this.z.layout(this.B, -r0i, this.B + this.z.getMeasuredWidth(), r0i);
                            }
                        }
                    case ShareUtils.SHARE_SMS:
                        r2i = r6i - r8i;
                        r1i = r0i & 112;
                        if (r0i != -1) {
                            r1i = 16;
                        }
                        r0i = 0;
                        switch (r1i) {
                            case Base64.URL_SAFE:
                                r0i = (((getHeight() - getPaddingBottom()) - getPaddingTop()) - r7_View.getMeasuredHeight()) / 2;
                                break;
                            case AdViewUtil.NETWORK_TYPE_ADUU:
                                r0i = getPaddingTop() + r5i;
                                break;
                            case 80:
                                r0i = getHeight() - getPaddingBottom() - r7_View.getMeasuredHeight() - r4i;
                                break;
                        }
                        r1i = r7_View.getMeasuredWidth();
                        r7_View.layout(r2i, r0i, r2i + r1i, r7_View.getMeasuredHeight() + r0i);
                        r0i = r3i + r1i;
                        if (this.z != null) {
                        } else {
                            this.z.bringToFront();
                            r0i = this.z.getMeasuredHeight() / 2;
                            this.z.layout(this.B, -r0i, this.B + this.z.getMeasuredWidth(), r0i);
                        }
                }
                r2i = 0;
                r1i = r0i & 112;
                if (r0i != -1) {
                    r0i = 0;
                    switch (r1i) {
                        case Base64.URL_SAFE:
                            r0i = (((getHeight() - getPaddingBottom()) - getPaddingTop()) - r7_View.getMeasuredHeight()) / 2;
                            break;
                        case AdViewUtil.NETWORK_TYPE_ADUU:
                            r0i = getPaddingTop() + r5i;
                            break;
                        case 80:
                            r0i = getHeight() - getPaddingBottom() - r7_View.getMeasuredHeight() - r4i;
                            break;
                    }
                    r1i = r7_View.getMeasuredWidth();
                    r7_View.layout(r2i, r0i, r2i + r1i, r7_View.getMeasuredHeight() + r0i);
                    r0i = r3i + r1i;
                    if (this.z != null) {
                        this.z.bringToFront();
                        r0i = this.z.getMeasuredHeight() / 2;
                        this.z.layout(this.B, -r0i, this.B + this.z.getMeasuredWidth(), r0i);
                    }
                } else {
                    r1i = 16;
                    r0i = 0;
                    switch (r1i) {
                        case Base64.URL_SAFE:
                            r0i = (((getHeight() - getPaddingBottom()) - getPaddingTop()) - r7_View.getMeasuredHeight()) / 2;
                            break;
                        case AdViewUtil.NETWORK_TYPE_ADUU:
                            r0i = getPaddingTop() + r5i;
                            break;
                        case 80:
                            r0i = getHeight() - getPaddingBottom() - r7_View.getMeasuredHeight() - r4i;
                            break;
                    }
                    r1i = r7_View.getMeasuredWidth();
                    r7_View.layout(r2i, r0i, r2i + r1i, r7_View.getMeasuredHeight() + r0i);
                    r0i = r3i + r1i;
                    if (this.z != null) {
                    } else {
                        this.z.bringToFront();
                        r0i = this.z.getMeasuredHeight() / 2;
                        this.z.layout(this.B, -r0i, this.B + this.z.getMeasuredWidth(), r0i);
                    }
                }
            }
            if (r7_View != null) {
                r0_ViewGroup_LayoutParams = r7_View.getLayoutParams();
                if (r0_ViewGroup_LayoutParams instanceof ActionBar.LayoutParams) {
                }
                if (r5_ActionBar_LayoutParams == null) {
                }
                r8i = r7_View.getMeasuredWidth();
                r4i = 0;
                r3i = 0;
                if (r5_ActionBar_LayoutParams == null) {
                    r5i = r4i;
                    r6i = r2i;
                    r4i = r3i;
                    r3i = r1i;
                } else {
                    r5i = r5_ActionBar_LayoutParams.topMargin;
                    r6i = r2i - r5_ActionBar_LayoutParams.rightMargin;
                    r3i = r1i + r5_ActionBar_LayoutParams.leftMargin;
                    r4i = r5_ActionBar_LayoutParams.bottomMargin;
                }
                r1i = r0i & 7;
                if (r1i != 1) {
                    r2i = r0i != -1 ? r1i : 3;
                } else {
                    r2i = (getWidth() - r8i) / 2;
                    if (r2i >= r3i) {
                        if (r2i + r8i <= r6i) {
                            r2i = r1i;
                        } else {
                            r1i = ShareUtils.SHARE_SMS;
                        }
                    } else {
                        r1i = XListViewFooter.STATE_NOMORE;
                    }
                    r2i = r1i;
                }
                switch (r2i) {
                    case XListViewHeader.STATE_READY:
                        r2i = (getWidth() - r8i) / 2;
                        r1i = r0i & 112;
                        if (r0i != -1) {
                            r1i = 16;
                        }
                        r0i = 0;
                        switch (r1i) {
                            case Base64.URL_SAFE:
                                r0i = (((getHeight() - getPaddingBottom()) - getPaddingTop()) - r7_View.getMeasuredHeight()) / 2;
                                break;
                            case AdViewUtil.NETWORK_TYPE_ADUU:
                                r0i = getPaddingTop() + r5i;
                                break;
                            case 80:
                                r0i = getHeight() - getPaddingBottom() - r7_View.getMeasuredHeight() - r4i;
                                break;
                        }
                        r1i = r7_View.getMeasuredWidth();
                        r7_View.layout(r2i, r0i, r2i + r1i, r7_View.getMeasuredHeight() + r0i);
                        r0i = r3i + r1i;
                        break;
                    case XListViewFooter.STATE_NOMORE:
                        r2i = r3i;
                        r1i = r0i & 112;
                        if (r0i != -1) {
                            r0i = 0;
                            switch (r1i) {
                                case Base64.URL_SAFE:
                                    r0i = (((getHeight() - getPaddingBottom()) - getPaddingTop()) - r7_View.getMeasuredHeight()) / 2;
                                    break;
                                case AdViewUtil.NETWORK_TYPE_ADUU:
                                    r0i = getPaddingTop() + r5i;
                                    break;
                                case 80:
                                    r0i = getHeight() - getPaddingBottom() - r7_View.getMeasuredHeight() - r4i;
                                    break;
                            }
                            r1i = r7_View.getMeasuredWidth();
                            r7_View.layout(r2i, r0i, r2i + r1i, r7_View.getMeasuredHeight() + r0i);
                            r0i = r3i + r1i;
                        } else {
                            r1i = 16;
                            r0i = 0;
                            switch (r1i) {
                                case Base64.URL_SAFE:
                                    r0i = (((getHeight() - getPaddingBottom()) - getPaddingTop()) - r7_View.getMeasuredHeight()) / 2;
                                    break;
                                case AdViewUtil.NETWORK_TYPE_ADUU:
                                    r0i = getPaddingTop() + r5i;
                                    break;
                                case 80:
                                    r0i = getHeight() - getPaddingBottom() - r7_View.getMeasuredHeight() - r4i;
                                    break;
                            }
                            r1i = r7_View.getMeasuredWidth();
                            r7_View.layout(r2i, r0i, r2i + r1i, r7_View.getMeasuredHeight() + r0i);
                            r0i = r3i + r1i;
                        }
                        break;
                    case ShareUtils.SHARE_SMS:
                        r2i = r6i - r8i;
                        r1i = r0i & 112;
                        if (r0i != -1) {
                            r1i = 16;
                        }
                        r0i = 0;
                        switch (r1i) {
                            case Base64.URL_SAFE:
                                r0i = (((getHeight() - getPaddingBottom()) - getPaddingTop()) - r7_View.getMeasuredHeight()) / 2;
                                break;
                            case AdViewUtil.NETWORK_TYPE_ADUU:
                                r0i = getPaddingTop() + r5i;
                                break;
                            case 80:
                                r0i = getHeight() - getPaddingBottom() - r7_View.getMeasuredHeight() - r4i;
                                break;
                        }
                        r1i = r7_View.getMeasuredWidth();
                        r7_View.layout(r2i, r0i, r2i + r1i, r7_View.getMeasuredHeight() + r0i);
                        r0i = r3i + r1i;
                        break;
                }
                r2i = 0;
                r1i = r0i & 112;
                if (r0i != -1) {
                    r0i = 0;
                    switch (r1i) {
                        case Base64.URL_SAFE:
                            r0i = (((getHeight() - getPaddingBottom()) - getPaddingTop()) - r7_View.getMeasuredHeight()) / 2;
                            break;
                        case AdViewUtil.NETWORK_TYPE_ADUU:
                            r0i = getPaddingTop() + r5i;
                            break;
                        case 80:
                            r0i = getHeight() - getPaddingBottom() - r7_View.getMeasuredHeight() - r4i;
                            break;
                    }
                    r1i = r7_View.getMeasuredWidth();
                    r7_View.layout(r2i, r0i, r2i + r1i, r7_View.getMeasuredHeight() + r0i);
                    r0i = r3i + r1i;
                } else {
                    r1i = 16;
                    r0i = 0;
                    switch (r1i) {
                        case Base64.URL_SAFE:
                            r0i = (((getHeight() - getPaddingBottom()) - getPaddingTop()) - r7_View.getMeasuredHeight()) / 2;
                            break;
                        case AdViewUtil.NETWORK_TYPE_ADUU:
                            r0i = getPaddingTop() + r5i;
                            break;
                        case 80:
                            r0i = getHeight() - getPaddingBottom() - r7_View.getMeasuredHeight() - r4i;
                            break;
                    }
                    r1i = r7_View.getMeasuredWidth();
                    r7_View.layout(r2i, r0i, r2i + r1i, r7_View.getMeasuredHeight() + r0i);
                    r0i = r3i + r1i;
                }
            }
            if (this.z != null) {
                this.z.bringToFront();
                r0i = this.z.getMeasuredHeight() / 2;
                this.z.layout(this.B, -r0i, this.B + this.z.getMeasuredWidth(), r0i);
            }
        }
    }

    protected void onMeasure(int r20i, int r21i) {
        int r1i;
        int r2i;
        int r13i = getChildCount();
        if (this.J) {
            r1i = 0;
            r2i = 0;
            while (r2i < r13i) {
                View r3_View = getChildAt(r2i);
                if (r3_View.getVisibility() != 8) {
                    if (!(r3_View == this.a && this.a.getChildCount() == 0)) {
                        r1i++;
                    }
                }
                r2i++;
            }
            if (r1i == 0) {
                setMeasuredDimension(0, 0);
                this.K = true;
                return;
            }
        }
        this.K = false;
        if (MeasureSpec.getMode(r20i) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_width=\"MATCH_PARENT\" (or fill_parent)");
        } else if (MeasureSpec.getMode(r21i) != -2147483648) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
        } else {
            int r3i;
            HomeView r1_HomeView;
            ViewGroup.LayoutParams r2_ViewGroup_LayoutParams;
            int r7i;
            View r12_View;
            ActionBar.LayoutParams r11_ActionBar_LayoutParams;
            int r8i;
            int r9i;
            int r16i;
            int r14i = MeasureSpec.getSize(r20i);
            r3i = this.f > 0 ? this.f : MeasureSpec.getSize(r21i);
            int r15i = getPaddingTop() + getPaddingBottom();
            r1i = getPaddingLeft();
            r2i = getPaddingRight();
            int r10i = r3i - r15i;
            int r6i = MeasureSpec.makeMeasureSpec(r10i, ExploreByTouchHelper.INVALID_ID);
            int r5i = r14i - r1i - r2i;
            int r4i = r5i / 2;
            r1_HomeView = this.g != null ? this.q : this.p;
            if (r1_HomeView.getVisibility() != 8) {
                r2_ViewGroup_LayoutParams = r1_HomeView.getLayoutParams();
                r1_HomeView.measure(r2_ViewGroup_LayoutParams.width < 0 ? MeasureSpec.makeMeasureSpec(r5i, ExploreByTouchHelper.INVALID_ID) : MeasureSpec.makeMeasureSpec(r2_ViewGroup_LayoutParams.width, 1073741824), MeasureSpec.makeMeasureSpec(r10i, 1073741824));
                r1i = r1_HomeView.getLeftOffset() + r1_HomeView.getMeasuredWidth();
                r2i = Math.max(0, r5i - r1i);
                r1i = Math.max(0, r2i - r1i);
            } else {
                r1i = r4i;
                r2i = r5i;
            }
            if (this.a == null || this.a.getParent() != this) {
            } else {
                r2i = a(this.a, r2i, r6i, 0);
                r4i = Math.max(0, r4i - this.a.getMeasuredWidth());
            }
            if (this.A == null || this.A.getVisibility() == 8) {
            } else {
                r2i = a(this.A, r2i, r6i, 0);
                r4i = Math.max(0, r4i - this.A.getMeasuredWidth());
            }
            r5i = (this.r == null || this.r.getVisibility() == 8 || (this.j & 8) == 0) ? 0 : 1;
            if (this.g == null) {
                switch (this.i) {
                    case XListViewHeader.STATE_READY:
                        if (this.w != null) {
                            r6i = r5i != 0 ? this.C * 2 : this.C;
                            r2i = Math.max(0, r2i - r6i);
                            r1i = Math.max(0, r1i - r6i);
                            this.w.measure(MeasureSpec.makeMeasureSpec(r2i, ExploreByTouchHelper.INVALID_ID), MeasureSpec.makeMeasureSpec(r10i, 1073741824));
                            r6i = this.w.getMeasuredWidth();
                            r2i = Math.max(0, r2i - r6i);
                            r6i = Math.max(0, r1i - r6i);
                            r7i = r2i;
                        }
                        break;
                    case XListViewHeader.STATE_REFRESHING:
                        if (this.x != null) {
                            r6i = r5i != 0 ? this.C * 2 : this.C;
                            r2i = Math.max(0, r2i - r6i);
                            r1i = Math.max(0, r1i - r6i);
                            this.x.measure(MeasureSpec.makeMeasureSpec(r2i, ExploreByTouchHelper.INVALID_ID), MeasureSpec.makeMeasureSpec(r10i, 1073741824));
                            r6i = this.x.getMeasuredWidth();
                            r2i = Math.max(0, r2i - r6i);
                            r6i = Math.max(0, r1i - r6i);
                            r7i = r2i;
                        }
                        break;
                }
                r6i = r1i;
                r7i = r2i;
            } else {
                r6i = r1i;
                r7i = r2i;
            }
            if (this.g != null) {
                r12_View = this.g;
            } else {
                r12_View = ((this.j & 16) == 0 || this.y == null) ? null : this.y;
                r2_ViewGroup_LayoutParams = generateLayoutParams(r12_View.getLayoutParams());
                r11_ActionBar_LayoutParams = r2_ViewGroup_LayoutParams instanceof ActionBar.LayoutParams ? null : (ActionBar.LayoutParams) r2_ViewGroup_LayoutParams;
                r8i = 0;
                r1i = 0;
                if (r11_ActionBar_LayoutParams == null) {
                    r8i = r11_ActionBar_LayoutParams.rightMargin + r11_ActionBar_LayoutParams.leftMargin;
                    r1i = r11_ActionBar_LayoutParams.topMargin + r11_ActionBar_LayoutParams.bottomMargin;
                }
                if (this.f <= 0) {
                    r9i = ExploreByTouchHelper.INVALID_ID;
                } else if (r2_ViewGroup_LayoutParams.height != -2) {
                }
                r16i = 0;
                if (r2_ViewGroup_LayoutParams.height >= 0) {
                    r10i = Math.min(r2_ViewGroup_LayoutParams.height, r10i);
                }
                r16i = Math.max(r16i, r10i - r1i);
                r1i = r2_ViewGroup_LayoutParams.width == -2 ? 1073741824 : ExploreByTouchHelper.INVALID_ID;
                r10i = Math.max(0, (r2_ViewGroup_LayoutParams.width < 0 ? Math.min(r2_ViewGroup_LayoutParams.width, r7i) : r7i) - r8i);
                if (((r11_ActionBar_LayoutParams == null ? r11_ActionBar_LayoutParams.gravity : TimeUtils.HUNDRED_DAY_FIELD_LEN) & 7) == 1 && r2_ViewGroup_LayoutParams.width == -1) {
                    r2i = Math.min(r6i, r4i) * 2;
                    r12_View.measure(MeasureSpec.makeMeasureSpec(r2i, r1i), MeasureSpec.makeMeasureSpec(r16i, r9i));
                    r7i -= r12_View.getMeasuredWidth() + r8i;
                } else {
                    r2i = r10i;
                    r12_View.measure(MeasureSpec.makeMeasureSpec(r2i, r1i), MeasureSpec.makeMeasureSpec(r16i, r9i));
                    r7i -= r12_View.getMeasuredWidth() + r8i;
                }
                if (this.g != null || r5i == 0) {
                } else {
                    a(this.r, r7i, MeasureSpec.makeMeasureSpec(this.f, 1073741824), 0);
                    Math.max(0, r6i - this.r.getMeasuredWidth());
                }
                if (this.f > 0) {
                    r2i = 0;
                    r3i = 0;
                    while (r3i < r13i) {
                        r1i = getChildAt(r3i).getMeasuredHeight() + r15i;
                        if (r1i <= r2i) {
                            r3i++;
                            r2i = r1i;
                        } else {
                            r1i = r2i;
                            r3i++;
                            r2i = r1i;
                        }
                    }
                    setMeasuredDimension(r14i, r2i);
                } else {
                    setMeasuredDimension(r14i, r3i);
                }
                if (this.M == null) {
                    this.M.setContentHeight(getMeasuredHeight());
                }
                if (this.z == null || this.z.getVisibility() == 8) {
                } else {
                    this.z.measure(MeasureSpec.makeMeasureSpec(r14i - this.B * 2, 1073741824), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), ExploreByTouchHelper.INVALID_ID));
                }
            }
            if (r12_View != null) {
                r2_ViewGroup_LayoutParams = generateLayoutParams(r12_View.getLayoutParams());
                if (r2_ViewGroup_LayoutParams instanceof ActionBar.LayoutParams) {
                }
                r8i = 0;
                r1i = 0;
                if (r11_ActionBar_LayoutParams == null) {
                    if (this.f <= 0) {
                        r9i = r2_ViewGroup_LayoutParams.height != -2 ? ExploreByTouchHelper.INVALID_ID : 1073741824;
                    } else {
                        r9i = ExploreByTouchHelper.INVALID_ID;
                    }
                    r16i = 0;
                    if (r2_ViewGroup_LayoutParams.height >= 0) {
                        r16i = Math.max(r16i, r10i - r1i);
                        if (r2_ViewGroup_LayoutParams.width == -2) {
                        }
                        if (r2_ViewGroup_LayoutParams.width < 0) {
                        }
                        r10i = Math.max(0, (r2_ViewGroup_LayoutParams.width < 0 ? Math.min(r2_ViewGroup_LayoutParams.width, r7i) : r7i) - r8i);
                        if (r11_ActionBar_LayoutParams == null) {
                        }
                        if (((r11_ActionBar_LayoutParams == null ? r11_ActionBar_LayoutParams.gravity : TimeUtils.HUNDRED_DAY_FIELD_LEN) & 7) == 1 || r2_ViewGroup_LayoutParams.width == -1) {
                            r2i = r10i;
                            r12_View.measure(MeasureSpec.makeMeasureSpec(r2i, r1i), MeasureSpec.makeMeasureSpec(r16i, r9i));
                            r7i -= r12_View.getMeasuredWidth() + r8i;
                        } else {
                            r2i = Math.min(r6i, r4i) * 2;
                            r12_View.measure(MeasureSpec.makeMeasureSpec(r2i, r1i), MeasureSpec.makeMeasureSpec(r16i, r9i));
                            r7i -= r12_View.getMeasuredWidth() + r8i;
                        }
                    } else {
                        r10i = Math.min(r2_ViewGroup_LayoutParams.height, r10i);
                        r16i = Math.max(r16i, r10i - r1i);
                        if (r2_ViewGroup_LayoutParams.width == -2) {
                        }
                        if (r2_ViewGroup_LayoutParams.width < 0) {
                        }
                        r10i = Math.max(0, (r2_ViewGroup_LayoutParams.width < 0 ? Math.min(r2_ViewGroup_LayoutParams.width, r7i) : r7i) - r8i);
                        if (r11_ActionBar_LayoutParams == null) {
                        }
                        if (((r11_ActionBar_LayoutParams == null ? r11_ActionBar_LayoutParams.gravity : TimeUtils.HUNDRED_DAY_FIELD_LEN) & 7) == 1 || r2_ViewGroup_LayoutParams.width == -1) {
                            r2i = r10i;
                            r12_View.measure(MeasureSpec.makeMeasureSpec(r2i, r1i), MeasureSpec.makeMeasureSpec(r16i, r9i));
                            r7i -= r12_View.getMeasuredWidth() + r8i;
                        } else {
                            r2i = Math.min(r6i, r4i) * 2;
                            r12_View.measure(MeasureSpec.makeMeasureSpec(r2i, r1i), MeasureSpec.makeMeasureSpec(r16i, r9i));
                            r7i -= r12_View.getMeasuredWidth() + r8i;
                        }
                    }
                } else {
                    r8i = r11_ActionBar_LayoutParams.rightMargin + r11_ActionBar_LayoutParams.leftMargin;
                    r1i = r11_ActionBar_LayoutParams.topMargin + r11_ActionBar_LayoutParams.bottomMargin;
                    if (this.f <= 0) {
                        r9i = ExploreByTouchHelper.INVALID_ID;
                    } else if (r2_ViewGroup_LayoutParams.height != -2) {
                    }
                    r16i = 0;
                    if (r2_ViewGroup_LayoutParams.height >= 0) {
                        r10i = Math.min(r2_ViewGroup_LayoutParams.height, r10i);
                    }
                    r16i = Math.max(r16i, r10i - r1i);
                    if (r2_ViewGroup_LayoutParams.width == -2) {
                    }
                    if (r2_ViewGroup_LayoutParams.width < 0) {
                    }
                    r10i = Math.max(0, (r2_ViewGroup_LayoutParams.width < 0 ? Math.min(r2_ViewGroup_LayoutParams.width, r7i) : r7i) - r8i);
                    if (r11_ActionBar_LayoutParams == null) {
                    }
                    if (((r11_ActionBar_LayoutParams == null ? r11_ActionBar_LayoutParams.gravity : TimeUtils.HUNDRED_DAY_FIELD_LEN) & 7) == 1 || r2_ViewGroup_LayoutParams.width == -1) {
                        r2i = r10i;
                        r12_View.measure(MeasureSpec.makeMeasureSpec(r2i, r1i), MeasureSpec.makeMeasureSpec(r16i, r9i));
                        r7i -= r12_View.getMeasuredWidth() + r8i;
                    } else {
                        r2i = Math.min(r6i, r4i) * 2;
                        r12_View.measure(MeasureSpec.makeMeasureSpec(r2i, r1i), MeasureSpec.makeMeasureSpec(r16i, r9i));
                        r7i -= r12_View.getMeasuredWidth() + r8i;
                    }
                }
            }
            if (this.g != null || r5i == 0) {
            } else {
                a(this.r, r7i, MeasureSpec.makeMeasureSpec(this.f, 1073741824), 0);
                Math.max(0, r6i - this.r.getMeasuredWidth());
            }
            if (this.f > 0) {
                setMeasuredDimension(r14i, r3i);
            } else {
                r2i = 0;
                r3i = 0;
                while (r3i < r13i) {
                    r1i = getChildAt(r3i).getMeasuredHeight() + r15i;
                    if (r1i <= r2i) {
                        r1i = r2i;
                    }
                    r3i++;
                    r2i = r1i;
                }
                setMeasuredDimension(r14i, r2i);
            }
            if (this.M == null) {
                if (this.z == null || this.z.getVisibility() == 8) {
                } else {
                    this.z.measure(MeasureSpec.makeMeasureSpec(r14i - this.B * 2, 1073741824), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), ExploreByTouchHelper.INVALID_ID));
                }
            } else {
                this.M.setContentHeight(getMeasuredHeight());
                if (this.z == null || this.z.getVisibility() == 8) {
                } else {
                    this.z.measure(MeasureSpec.makeMeasureSpec(r14i - this.B * 2, 1073741824), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), ExploreByTouchHelper.INVALID_ID));
                }
            }
        }
    }

    public void onRestoreInstanceState(Parcelable r3_Parcelable) {
        SavedState r3_SavedState = (SavedState) r3_Parcelable;
        super.onRestoreInstanceState(r3_SavedState.getSuperState());
        if (r3_SavedState.a == 0 || this.R == null || this.L == null) {
            if (!r3_SavedState.b) {
                postShowOverflowMenu();
            }
        } else {
            SupportMenuItem r0_SupportMenuItem = (SupportMenuItem) this.L.findItem(r3_SavedState.a);
            if (r0_SupportMenuItem != null) {
                r0_SupportMenuItem.expandActionView();
            }
            if (r3_SavedState.b) {
            } else {
                postShowOverflowMenu();
            }
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable r1_Parcelable = new SavedState(super.onSaveInstanceState());
        if (this.R == null || this.R.b == null) {
            r1_Parcelable.b = isOverflowMenuShowing();
            return r1_Parcelable;
        } else {
            r1_Parcelable.a = this.R.b.getItemId();
            r1_Parcelable.b = isOverflowMenuShowing();
            return r1_Parcelable;
        }
    }

    public /* bridge */ /* synthetic */ void postShowOverflowMenu() {
        super.postShowOverflowMenu();
    }

    public void setCallback(OnNavigationListener r1_OnNavigationListener) {
        this.P = r1_OnNavigationListener;
    }

    public void setCollapsable(boolean r1z) {
        this.J = r1z;
    }

    public /* bridge */ /* synthetic */ void setContentHeight(int r1i) {
        super.setContentHeight(r1i);
    }

    public void setContextView(ActionBarContextView r1_ActionBarContextView) {
        this.M = r1_ActionBarContextView;
    }

    public void setCustomNavigationView(View r3_View) {
        int r0i = (this.j & 16) != 0 ? 1 : 0;
        if (this.y == null || r0i == 0) {
            this.y = r3_View;
            if (this.y == null || r0i == 0) {
            } else {
                addView(this.y);
            }
        } else {
            removeView(this.y);
            this.y = r3_View;
            if (this.y == null || r0i == 0) {
            } else {
                addView(this.y);
            }
        }
    }

    public void setDisplayOptions(int r8i) {
        int r3i = Base64.DONT_BREAK_LINES;
        int r0i = -1;
        boolean r4z = true;
        int r5i;
        int r1i;
        boolean r1z;
        View r6_View;
        LinearLayout r3_LinearLayout;
        if (this.j == -1) {
            this.j = r8i;
            if ((r0i & 31) == 0) {
                r5i = (r8i & 2) == 0 ? 1 : 0;
                r1i = (r5i == 0 || this.g != null) ? 8 : 0;
                this.p.setVisibility(r1i);
                if ((r0i & 4) == 0) {
                    r1z = (r8i & 4) == 0;
                    this.p.setUp(r1z);
                    if (!r1z) {
                        setHomeButtonEnabled(true);
                    }
                }
                if ((r0i & 1) == 0) {
                    r1i = (this.n == null || (r8i & 1) == 0) ? 0 : 1;
                    this.p.setIcon(r1i == 0 ? this.n : this.m);
                }
                if ((r0i & 8) == 0) {
                    if ((r8i & 8) == 0) {
                        a();
                    } else {
                        removeView(this.r);
                    }
                }
                if (this.r == null || (r0i & 6) == 0) {
                    if ((r0i & 16) == 0 || this.y == null) {
                        requestLayout();
                    } else if ((r8i & 16) == 0) {
                        addView(this.y);
                        requestLayout();
                    } else {
                        removeView(this.y);
                        requestLayout();
                    }
                } else {
                    r1i = (this.j & 4) == 0 ? 1 : 0;
                    r6_View = this.u;
                    if (r5i != 0) {
                        r3i = r1i == 0 ? 0 : XListViewFooter.STATE_NODATA;
                    }
                    r6_View.setVisibility(r3i);
                    r3_LinearLayout = this.r;
                    if (r5i != 0 || r1i == 0) {
                        r4z = false;
                        r3_LinearLayout.setEnabled(r4z);
                    } else {
                        r3_LinearLayout.setEnabled(r4z);
                    }
                    if ((r0i & 16) == 0 || this.y == null) {
                        requestLayout();
                    } else if ((r8i & 16) == 0) {
                        removeView(this.y);
                        requestLayout();
                    } else {
                        addView(this.y);
                        requestLayout();
                    }
                }
            } else {
                invalidate();
            }
            if (this.p.isEnabled()) {
                this.p.setContentDescription(null);
            } else if ((r8i & 4) == 0) {
                this.p.setContentDescription(this.o.getResources().getText(R.string.abc_action_bar_up_description));
            } else {
                this.p.setContentDescription(this.o.getResources().getText(R.string.abc_action_bar_home_description));
            }
        } else {
            r0i = this.j ^ r8i;
            this.j = r8i;
            if ((r0i & 31) == 0) {
                invalidate();
            } else {
                if ((r8i & 2) == 0) {
                }
                if (r5i == 0 || this.g != null) {
                }
                this.p.setVisibility(r1i);
                if ((r0i & 4) == 0) {
                    if ((r0i & 1) == 0) {
                        if ((r0i & 8) == 0) {
                            if (this.r == null || (r0i & 6) == 0) {
                                if ((r0i & 16) == 0 || this.y == null) {
                                    requestLayout();
                                } else if ((r8i & 16) == 0) {
                                    addView(this.y);
                                    requestLayout();
                                } else {
                                    removeView(this.y);
                                    requestLayout();
                                }
                            } else {
                                if ((this.j & 4) == 0) {
                                }
                                r6_View = this.u;
                                if (r5i != 0) {
                                    r6_View.setVisibility(r3i);
                                    r3_LinearLayout = this.r;
                                    if (r5i != 0 || r1i == 0) {
                                        r4z = false;
                                        r3_LinearLayout.setEnabled(r4z);
                                    } else {
                                        r3_LinearLayout.setEnabled(r4z);
                                    }
                                    if ((r0i & 16) == 0 || this.y == null) {
                                        requestLayout();
                                    } else if ((r8i & 16) == 0) {
                                        removeView(this.y);
                                        requestLayout();
                                    } else {
                                        addView(this.y);
                                        requestLayout();
                                    }
                                } else if (r1i == 0) {
                                    r6_View.setVisibility(r3i);
                                    r3_LinearLayout = this.r;
                                    if (r5i != 0 || r1i == 0) {
                                        r4z = false;
                                        r3_LinearLayout.setEnabled(r4z);
                                    } else {
                                        r3_LinearLayout.setEnabled(r4z);
                                    }
                                    if ((r0i & 16) == 0 || this.y == null) {
                                        requestLayout();
                                    } else if ((r8i & 16) == 0) {
                                        addView(this.y);
                                        requestLayout();
                                    } else {
                                        removeView(this.y);
                                        requestLayout();
                                    }
                                } else {
                                    r6_View.setVisibility(r3i);
                                    r3_LinearLayout = this.r;
                                    if (r5i != 0 || r1i == 0) {
                                        r4z = false;
                                        r3_LinearLayout.setEnabled(r4z);
                                    } else {
                                        r3_LinearLayout.setEnabled(r4z);
                                    }
                                    if ((r0i & 16) == 0 || this.y == null) {
                                        requestLayout();
                                    } else if ((r8i & 16) == 0) {
                                        removeView(this.y);
                                        requestLayout();
                                    } else {
                                        addView(this.y);
                                        requestLayout();
                                    }
                                }
                            }
                        } else if ((r8i & 8) == 0) {
                            removeView(this.r);
                            if (this.r == null || (r0i & 6) == 0) {
                                if ((r0i & 16) == 0 || this.y == null) {
                                    requestLayout();
                                } else if ((r8i & 16) == 0) {
                                    addView(this.y);
                                    requestLayout();
                                } else {
                                    removeView(this.y);
                                    requestLayout();
                                }
                            } else {
                                if ((this.j & 4) == 0) {
                                }
                                r6_View = this.u;
                                if (r5i != 0) {
                                    if (r1i == 0) {
                                    }
                                }
                                r6_View.setVisibility(r3i);
                                r3_LinearLayout = this.r;
                                if (r5i != 0 || r1i == 0) {
                                    r4z = false;
                                    r3_LinearLayout.setEnabled(r4z);
                                } else {
                                    r3_LinearLayout.setEnabled(r4z);
                                }
                                if ((r0i & 16) == 0 || this.y == null) {
                                    requestLayout();
                                } else if ((r8i & 16) == 0) {
                                    removeView(this.y);
                                    requestLayout();
                                } else {
                                    addView(this.y);
                                    requestLayout();
                                }
                            }
                        } else {
                            a();
                            if (this.r == null || (r0i & 6) == 0) {
                                if ((r0i & 16) == 0 || this.y == null) {
                                    requestLayout();
                                } else if ((r8i & 16) == 0) {
                                    addView(this.y);
                                    requestLayout();
                                } else {
                                    removeView(this.y);
                                    requestLayout();
                                }
                            } else {
                                if ((this.j & 4) == 0) {
                                }
                                r6_View = this.u;
                                if (r5i != 0) {
                                    r6_View.setVisibility(r3i);
                                    r3_LinearLayout = this.r;
                                    if (r5i != 0 || r1i == 0) {
                                        r4z = false;
                                        r3_LinearLayout.setEnabled(r4z);
                                    } else {
                                        r3_LinearLayout.setEnabled(r4z);
                                    }
                                    if ((r0i & 16) == 0 || this.y == null) {
                                        requestLayout();
                                    } else if ((r8i & 16) == 0) {
                                        removeView(this.y);
                                        requestLayout();
                                    } else {
                                        addView(this.y);
                                        requestLayout();
                                    }
                                } else if (r1i == 0) {
                                    r6_View.setVisibility(r3i);
                                    r3_LinearLayout = this.r;
                                    if (r5i != 0 || r1i == 0) {
                                        r4z = false;
                                        r3_LinearLayout.setEnabled(r4z);
                                    } else {
                                        r3_LinearLayout.setEnabled(r4z);
                                    }
                                    if ((r0i & 16) == 0 || this.y == null) {
                                        requestLayout();
                                    } else if ((r8i & 16) == 0) {
                                        addView(this.y);
                                        requestLayout();
                                    } else {
                                        removeView(this.y);
                                        requestLayout();
                                    }
                                } else {
                                    r6_View.setVisibility(r3i);
                                    r3_LinearLayout = this.r;
                                    if (r5i != 0 || r1i == 0) {
                                        r4z = false;
                                        r3_LinearLayout.setEnabled(r4z);
                                    } else {
                                        r3_LinearLayout.setEnabled(r4z);
                                    }
                                    if ((r0i & 16) == 0 || this.y == null) {
                                        requestLayout();
                                    } else if ((r8i & 16) == 0) {
                                        removeView(this.y);
                                        requestLayout();
                                    } else {
                                        addView(this.y);
                                        requestLayout();
                                    }
                                }
                            }
                        }
                    } else {
                        if (this.n == null || (r8i & 1) == 0) {
                        }
                        if (r1i == 0) {
                        }
                        this.p.setIcon(r1i == 0 ? this.n : this.m);
                        if ((r0i & 8) == 0) {
                            if ((r8i & 8) == 0) {
                                a();
                            } else {
                                removeView(this.r);
                            }
                        }
                        if (this.r == null || (r0i & 6) == 0) {
                            if ((r0i & 16) == 0 || this.y == null) {
                                requestLayout();
                            } else if ((r8i & 16) == 0) {
                                addView(this.y);
                                requestLayout();
                            } else {
                                removeView(this.y);
                                requestLayout();
                            }
                        } else {
                            if ((this.j & 4) == 0) {
                            }
                            r6_View = this.u;
                            if (r5i != 0) {
                                if (r1i == 0) {
                                }
                            }
                            r6_View.setVisibility(r3i);
                            r3_LinearLayout = this.r;
                            if (r5i != 0 || r1i == 0) {
                                r4z = false;
                                r3_LinearLayout.setEnabled(r4z);
                            } else {
                                r3_LinearLayout.setEnabled(r4z);
                            }
                            if ((r0i & 16) == 0 || this.y == null) {
                                requestLayout();
                            } else if ((r8i & 16) == 0) {
                                removeView(this.y);
                                requestLayout();
                            } else {
                                addView(this.y);
                                requestLayout();
                            }
                        }
                    }
                } else {
                    if ((r8i & 4) == 0) {
                    }
                    this.p.setUp(r1z);
                    if (r1z) {
                        if ((r0i & 1) == 0) {
                            if (this.n == null || (r8i & 1) == 0) {
                            }
                            if (r1i == 0) {
                            }
                            this.p.setIcon(r1i == 0 ? this.n : this.m);
                        }
                        if ((r0i & 8) == 0) {
                            if (this.r == null || (r0i & 6) == 0) {
                                if ((r0i & 16) == 0 || this.y == null) {
                                    requestLayout();
                                } else if ((r8i & 16) == 0) {
                                    addView(this.y);
                                    requestLayout();
                                } else {
                                    removeView(this.y);
                                    requestLayout();
                                }
                            } else {
                                if ((this.j & 4) == 0) {
                                }
                                r6_View = this.u;
                                if (r5i != 0) {
                                    r6_View.setVisibility(r3i);
                                    r3_LinearLayout = this.r;
                                    if (r5i != 0 || r1i == 0) {
                                        r4z = false;
                                        r3_LinearLayout.setEnabled(r4z);
                                    } else {
                                        r3_LinearLayout.setEnabled(r4z);
                                    }
                                    if ((r0i & 16) == 0 || this.y == null) {
                                        requestLayout();
                                    } else if ((r8i & 16) == 0) {
                                        removeView(this.y);
                                        requestLayout();
                                    } else {
                                        addView(this.y);
                                        requestLayout();
                                    }
                                } else if (r1i == 0) {
                                    r6_View.setVisibility(r3i);
                                    r3_LinearLayout = this.r;
                                    if (r5i != 0 || r1i == 0) {
                                        r4z = false;
                                        r3_LinearLayout.setEnabled(r4z);
                                    } else {
                                        r3_LinearLayout.setEnabled(r4z);
                                    }
                                    if ((r0i & 16) == 0 || this.y == null) {
                                        requestLayout();
                                    } else if ((r8i & 16) == 0) {
                                        addView(this.y);
                                        requestLayout();
                                    } else {
                                        removeView(this.y);
                                        requestLayout();
                                    }
                                } else {
                                    r6_View.setVisibility(r3i);
                                    r3_LinearLayout = this.r;
                                    if (r5i != 0 || r1i == 0) {
                                        r4z = false;
                                        r3_LinearLayout.setEnabled(r4z);
                                    } else {
                                        r3_LinearLayout.setEnabled(r4z);
                                    }
                                    if ((r0i & 16) == 0 || this.y == null) {
                                        requestLayout();
                                    } else if ((r8i & 16) == 0) {
                                        removeView(this.y);
                                        requestLayout();
                                    } else {
                                        addView(this.y);
                                        requestLayout();
                                    }
                                }
                            }
                        } else if ((r8i & 8) == 0) {
                            removeView(this.r);
                            if (this.r == null || (r0i & 6) == 0) {
                                if ((r0i & 16) == 0 || this.y == null) {
                                    requestLayout();
                                } else if ((r8i & 16) == 0) {
                                    addView(this.y);
                                    requestLayout();
                                } else {
                                    removeView(this.y);
                                    requestLayout();
                                }
                            } else {
                                if ((this.j & 4) == 0) {
                                }
                                r6_View = this.u;
                                if (r5i != 0) {
                                    if (r1i == 0) {
                                    }
                                }
                                r6_View.setVisibility(r3i);
                                r3_LinearLayout = this.r;
                                if (r5i != 0 || r1i == 0) {
                                    r4z = false;
                                    r3_LinearLayout.setEnabled(r4z);
                                } else {
                                    r3_LinearLayout.setEnabled(r4z);
                                }
                                if ((r0i & 16) == 0 || this.y == null) {
                                    requestLayout();
                                } else if ((r8i & 16) == 0) {
                                    removeView(this.y);
                                    requestLayout();
                                } else {
                                    addView(this.y);
                                    requestLayout();
                                }
                            }
                        } else {
                            a();
                            if (this.r == null || (r0i & 6) == 0) {
                                if ((r0i & 16) == 0 || this.y == null) {
                                    requestLayout();
                                } else if ((r8i & 16) == 0) {
                                    addView(this.y);
                                    requestLayout();
                                } else {
                                    removeView(this.y);
                                    requestLayout();
                                }
                            } else {
                                if ((this.j & 4) == 0) {
                                }
                                r6_View = this.u;
                                if (r5i != 0) {
                                    r6_View.setVisibility(r3i);
                                    r3_LinearLayout = this.r;
                                    if (r5i != 0 || r1i == 0) {
                                        r4z = false;
                                        r3_LinearLayout.setEnabled(r4z);
                                    } else {
                                        r3_LinearLayout.setEnabled(r4z);
                                    }
                                    if ((r0i & 16) == 0 || this.y == null) {
                                        requestLayout();
                                    } else if ((r8i & 16) == 0) {
                                        removeView(this.y);
                                        requestLayout();
                                    } else {
                                        addView(this.y);
                                        requestLayout();
                                    }
                                } else if (r1i == 0) {
                                    r6_View.setVisibility(r3i);
                                    r3_LinearLayout = this.r;
                                    if (r5i != 0 || r1i == 0) {
                                        r4z = false;
                                        r3_LinearLayout.setEnabled(r4z);
                                    } else {
                                        r3_LinearLayout.setEnabled(r4z);
                                    }
                                    if ((r0i & 16) == 0 || this.y == null) {
                                        requestLayout();
                                    } else if ((r8i & 16) == 0) {
                                        addView(this.y);
                                        requestLayout();
                                    } else {
                                        removeView(this.y);
                                        requestLayout();
                                    }
                                } else {
                                    r6_View.setVisibility(r3i);
                                    r3_LinearLayout = this.r;
                                    if (r5i != 0 || r1i == 0) {
                                        r4z = false;
                                        r3_LinearLayout.setEnabled(r4z);
                                    } else {
                                        r3_LinearLayout.setEnabled(r4z);
                                    }
                                    if ((r0i & 16) == 0 || this.y == null) {
                                        requestLayout();
                                    } else if ((r8i & 16) == 0) {
                                        removeView(this.y);
                                        requestLayout();
                                    } else {
                                        addView(this.y);
                                        requestLayout();
                                    }
                                }
                            }
                        }
                    } else {
                        setHomeButtonEnabled(true);
                        if ((r0i & 1) == 0) {
                            if ((r0i & 8) == 0) {
                                if ((r8i & 8) == 0) {
                                    a();
                                } else {
                                    removeView(this.r);
                                }
                            }
                            if (this.r == null || (r0i & 6) == 0) {
                                if ((r0i & 16) == 0 || this.y == null) {
                                    requestLayout();
                                } else if ((r8i & 16) == 0) {
                                    addView(this.y);
                                    requestLayout();
                                } else {
                                    removeView(this.y);
                                    requestLayout();
                                }
                            } else {
                                if ((this.j & 4) == 0) {
                                }
                                r6_View = this.u;
                                if (r5i != 0) {
                                    if (r1i == 0) {
                                    }
                                }
                                r6_View.setVisibility(r3i);
                                r3_LinearLayout = this.r;
                                if (r5i != 0 || r1i == 0) {
                                    r4z = false;
                                    r3_LinearLayout.setEnabled(r4z);
                                } else {
                                    r3_LinearLayout.setEnabled(r4z);
                                }
                                if ((r0i & 16) == 0 || this.y == null) {
                                    requestLayout();
                                } else if ((r8i & 16) == 0) {
                                    removeView(this.y);
                                    requestLayout();
                                } else {
                                    addView(this.y);
                                    requestLayout();
                                }
                            }
                        } else {
                            if (this.n == null || (r8i & 1) == 0) {
                            }
                            if (r1i == 0) {
                            }
                            this.p.setIcon(r1i == 0 ? this.n : this.m);
                            if ((r0i & 8) == 0) {
                                if (this.r == null || (r0i & 6) == 0) {
                                    if ((r0i & 16) == 0 || this.y == null) {
                                        requestLayout();
                                    } else if ((r8i & 16) == 0) {
                                        addView(this.y);
                                        requestLayout();
                                    } else {
                                        removeView(this.y);
                                        requestLayout();
                                    }
                                } else {
                                    if ((this.j & 4) == 0) {
                                    }
                                    r6_View = this.u;
                                    if (r5i != 0) {
                                        r6_View.setVisibility(r3i);
                                        r3_LinearLayout = this.r;
                                        if (r5i != 0 || r1i == 0) {
                                            r4z = false;
                                            r3_LinearLayout.setEnabled(r4z);
                                        } else {
                                            r3_LinearLayout.setEnabled(r4z);
                                        }
                                        if ((r0i & 16) == 0 || this.y == null) {
                                            requestLayout();
                                        } else if ((r8i & 16) == 0) {
                                            removeView(this.y);
                                            requestLayout();
                                        } else {
                                            addView(this.y);
                                            requestLayout();
                                        }
                                    } else if (r1i == 0) {
                                        r6_View.setVisibility(r3i);
                                        r3_LinearLayout = this.r;
                                        if (r5i != 0 || r1i == 0) {
                                            r4z = false;
                                            r3_LinearLayout.setEnabled(r4z);
                                        } else {
                                            r3_LinearLayout.setEnabled(r4z);
                                        }
                                        if ((r0i & 16) == 0 || this.y == null) {
                                            requestLayout();
                                        } else if ((r8i & 16) == 0) {
                                            addView(this.y);
                                            requestLayout();
                                        } else {
                                            removeView(this.y);
                                            requestLayout();
                                        }
                                    } else {
                                        r6_View.setVisibility(r3i);
                                        r3_LinearLayout = this.r;
                                        if (r5i != 0 || r1i == 0) {
                                            r4z = false;
                                            r3_LinearLayout.setEnabled(r4z);
                                        } else {
                                            r3_LinearLayout.setEnabled(r4z);
                                        }
                                        if ((r0i & 16) == 0 || this.y == null) {
                                            requestLayout();
                                        } else if ((r8i & 16) == 0) {
                                            removeView(this.y);
                                            requestLayout();
                                        } else {
                                            addView(this.y);
                                            requestLayout();
                                        }
                                    }
                                }
                            } else if ((r8i & 8) == 0) {
                                removeView(this.r);
                                if (this.r == null || (r0i & 6) == 0) {
                                    if ((r0i & 16) == 0 || this.y == null) {
                                        requestLayout();
                                    } else if ((r8i & 16) == 0) {
                                        addView(this.y);
                                        requestLayout();
                                    } else {
                                        removeView(this.y);
                                        requestLayout();
                                    }
                                } else {
                                    if ((this.j & 4) == 0) {
                                    }
                                    r6_View = this.u;
                                    if (r5i != 0) {
                                        if (r1i == 0) {
                                        }
                                    }
                                    r6_View.setVisibility(r3i);
                                    r3_LinearLayout = this.r;
                                    if (r5i != 0 || r1i == 0) {
                                        r4z = false;
                                        r3_LinearLayout.setEnabled(r4z);
                                    } else {
                                        r3_LinearLayout.setEnabled(r4z);
                                    }
                                    if ((r0i & 16) == 0 || this.y == null) {
                                        requestLayout();
                                    } else if ((r8i & 16) == 0) {
                                        removeView(this.y);
                                        requestLayout();
                                    } else {
                                        addView(this.y);
                                        requestLayout();
                                    }
                                }
                            } else {
                                a();
                                if (this.r == null || (r0i & 6) == 0) {
                                    if ((r0i & 16) == 0 || this.y == null) {
                                        requestLayout();
                                    } else if ((r8i & 16) == 0) {
                                        addView(this.y);
                                        requestLayout();
                                    } else {
                                        removeView(this.y);
                                        requestLayout();
                                    }
                                } else {
                                    if ((this.j & 4) == 0) {
                                    }
                                    r6_View = this.u;
                                    if (r5i != 0) {
                                        r6_View.setVisibility(r3i);
                                        r3_LinearLayout = this.r;
                                        if (r5i != 0 || r1i == 0) {
                                            r4z = false;
                                            r3_LinearLayout.setEnabled(r4z);
                                        } else {
                                            r3_LinearLayout.setEnabled(r4z);
                                        }
                                        if ((r0i & 16) == 0 || this.y == null) {
                                            requestLayout();
                                        } else if ((r8i & 16) == 0) {
                                            removeView(this.y);
                                            requestLayout();
                                        } else {
                                            addView(this.y);
                                            requestLayout();
                                        }
                                    } else if (r1i == 0) {
                                        r6_View.setVisibility(r3i);
                                        r3_LinearLayout = this.r;
                                        if (r5i != 0 || r1i == 0) {
                                            r4z = false;
                                            r3_LinearLayout.setEnabled(r4z);
                                        } else {
                                            r3_LinearLayout.setEnabled(r4z);
                                        }
                                        if ((r0i & 16) == 0 || this.y == null) {
                                            requestLayout();
                                        } else if ((r8i & 16) == 0) {
                                            addView(this.y);
                                            requestLayout();
                                        } else {
                                            removeView(this.y);
                                            requestLayout();
                                        }
                                    } else {
                                        r6_View.setVisibility(r3i);
                                        r3_LinearLayout = this.r;
                                        if (r5i != 0 || r1i == 0) {
                                            r4z = false;
                                            r3_LinearLayout.setEnabled(r4z);
                                        } else {
                                            r3_LinearLayout.setEnabled(r4z);
                                        }
                                        if ((r0i & 16) == 0 || this.y == null) {
                                            requestLayout();
                                        } else if ((r8i & 16) == 0) {
                                            removeView(this.y);
                                            requestLayout();
                                        } else {
                                            addView(this.y);
                                            requestLayout();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (this.p.isEnabled()) {
                if ((r8i & 4) == 0) {
                    this.p.setContentDescription(this.o.getResources().getText(R.string.abc_action_bar_home_description));
                } else {
                    this.p.setContentDescription(this.o.getResources().getText(R.string.abc_action_bar_up_description));
                }
            } else {
                this.p.setContentDescription(null);
            }
        }
    }

    public void setDropdownAdapter(SpinnerAdapter r2_SpinnerAdapter) {
        this.O = r2_SpinnerAdapter;
        if (this.v != null) {
            this.v.setAdapter(r2_SpinnerAdapter);
        }
    }

    public void setDropdownSelectedPosition(int r2i) {
        this.v.setSelection(r2i);
    }

    public void setEmbeddedTabView(ScrollingTabContainerView r4_ScrollingTabContainerView) {
        if (this.x != null) {
            removeView(this.x);
        }
        this.x = r4_ScrollingTabContainerView;
        this.I = r4_ScrollingTabContainerView != null;
        if (this.I && this.i == 2) {
            addView(this.x);
            ViewGroup.LayoutParams r0_ViewGroup_LayoutParams = this.x.getLayoutParams();
            r0_ViewGroup_LayoutParams.width = -2;
            r0_ViewGroup_LayoutParams.height = -1;
            r4_ScrollingTabContainerView.setAllowCollapse(true);
        }
    }

    public void setHomeAsUpIndicator(int r2i) {
        this.p.setUpIndicator(r2i);
    }

    public void setHomeAsUpIndicator(Drawable r2_Drawable) {
        this.p.setUpIndicator(r2_Drawable);
    }

    public void setHomeButtonEnabled(boolean r4z) {
        this.p.setEnabled(r4z);
        this.p.setFocusable(r4z);
        if (r4z) {
            if ((this.j & 4) != 0) {
                this.p.setContentDescription(this.o.getResources().getText(R.string.abc_action_bar_up_description));
            } else {
                this.p.setContentDescription(this.o.getResources().getText(R.string.abc_action_bar_home_description));
            }
        } else {
            this.p.setContentDescription(null);
        }
    }

    public void setIcon(int r2i) {
        setIcon(this.o.getResources().getDrawable(r2i));
    }

    public void setIcon(Drawable r4_Drawable) {
        this.m = r4_Drawable;
        if (r4_Drawable != null) {
            if ((this.j & 1) == 0 || this.n == null) {
                this.p.setIcon(r4_Drawable);
            }
        }
        if (this.g != null) {
            this.q.setIcon(this.m.getConstantState().newDrawable(getResources()));
        }
    }

    public void setLogo(int r2i) {
        setLogo(this.o.getResources().getDrawable(r2i));
    }

    public void setLogo(Drawable r2_Drawable) {
        this.n = r2_Drawable;
        if (r2_Drawable == null || (this.j & 1) == 0) {
        } else {
            this.p.setIcon(r2_Drawable);
        }
    }

    public void setMenu(SupportMenu r6_SupportMenu, MenuPresenter.Callback r7_MenuPresenter_Callback) {
        if (r6_SupportMenu == this.L) {
        } else {
            ActionMenuView r0_ActionMenuView;
            if (this.L != null) {
                this.L.removeMenuPresenter(this.b);
                this.L.removeMenuPresenter(this.R);
            }
            MenuBuilder r6_MenuBuilder = (MenuBuilder) r6_SupportMenu;
            this.L = r6_MenuBuilder;
            if (this.a != null) {
                ViewGroup r0_ViewGroup = (ViewGroup) this.a.getParent();
                if (r0_ViewGroup != null) {
                    r0_ViewGroup.removeView(this.a);
                }
            }
            if (this.b == null) {
                this.b = new ActionMenuPresenter(this.o);
                this.b.setCallback(r7_MenuPresenter_Callback);
                this.b.setId(R.id.action_menu_presenter);
                this.R = new a(null);
            }
            ViewGroup.LayoutParams r2_ViewGroup_LayoutParams = new ViewGroup.LayoutParams(-2, -1);
            ViewGroup r1_ViewGroup;
            if (this.d) {
                this.b.setExpandedActionViewsExclusive(false);
                this.b.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
                this.b.setItemLimit(a.MAX_ACTIVITY_COUNT_UNLIMITED);
                r2_ViewGroup_LayoutParams.width = -1;
                a(r6_MenuBuilder);
                r0_ActionMenuView = (ActionMenuView) this.b.getMenuView(this);
                if (this.c != null) {
                    r1_ViewGroup = (ViewGroup) r0_ActionMenuView.getParent();
                    if (r1_ViewGroup == null || r1_ViewGroup == this.c) {
                        r0_ActionMenuView.setVisibility(getAnimatedVisibility());
                        this.c.addView(r0_ActionMenuView, r2_ViewGroup_LayoutParams);
                    } else {
                        r1_ViewGroup.removeView(r0_ActionMenuView);
                        r0_ActionMenuView.setVisibility(getAnimatedVisibility());
                        this.c.addView(r0_ActionMenuView, r2_ViewGroup_LayoutParams);
                    }
                } else {
                    r0_ActionMenuView.setLayoutParams(r2_ViewGroup_LayoutParams);
                }
            } else {
                this.b.setExpandedActionViewsExclusive(getResources().getBoolean(R.bool.abc_action_bar_expanded_action_views_exclusive));
                a(r6_MenuBuilder);
                r0_ActionMenuView = (ActionMenuView) this.b.getMenuView(this);
                r0_ActionMenuView.initialize(r6_MenuBuilder);
                r1_ViewGroup = (ViewGroup) r0_ActionMenuView.getParent();
                if (r1_ViewGroup == null || r1_ViewGroup == this) {
                    addView(r0_ActionMenuView, r2_ViewGroup_LayoutParams);
                } else {
                    r1_ViewGroup.removeView(r0_ActionMenuView);
                    addView(r0_ActionMenuView, r2_ViewGroup_LayoutParams);
                }
            }
            this.a = r0_ActionMenuView;
        }
    }

    public void setNavigationMode(int r5i) {
        int r0i = this.i;
        if (r5i != r0i) {
            ViewGroup.LayoutParams r0_ViewGroup_LayoutParams;
            switch (r0i) {
                case XListViewHeader.STATE_READY:
                    if (this.w != null) {
                        removeView(this.w);
                    }
                    break;
                case XListViewHeader.STATE_REFRESHING:
                    if (this.x == null || (!this.I)) {
                        switch (r5i) {
                            case XListViewHeader.STATE_READY:
                                if (this.v != null) {
                                    this.v = new p(this.o, null, R.attr.actionDropDownStyle);
                                    this.w = (LinearLayout) LayoutInflater.from(this.o).inflate(R.layout.abc_action_bar_view_list_nav_layout, null);
                                    r0_ViewGroup_LayoutParams = new LinearLayout.LayoutParams(-2, -1);
                                    r0_ViewGroup_LayoutParams.gravity = 17;
                                    this.w.addView(this.v, r0_ViewGroup_LayoutParams);
                                }
                                if (this.v.getAdapter() != this.O) {
                                    this.v.setAdapter(this.O);
                                }
                                this.v.setOnItemSelectedListener(this.S);
                                addView(this.w);
                                break;
                            case XListViewHeader.STATE_REFRESHING:
                                if (this.x == null || (!this.I)) {
                                    this.i = r5i;
                                    requestLayout();
                                } else {
                                    addView(this.x);
                                }
                                break;
                        }
                    } else {
                        removeView(this.x);
                    }
                    break;
            }
            switch (r5i) {
                case XListViewHeader.STATE_READY:
                    if (this.v != null) {
                        if (this.v.getAdapter() != this.O) {
                            this.v.setOnItemSelectedListener(this.S);
                            addView(this.w);
                        } else {
                            this.v.setAdapter(this.O);
                            this.v.setOnItemSelectedListener(this.S);
                            addView(this.w);
                        }
                    } else {
                        this.v = new p(this.o, null, R.attr.actionDropDownStyle);
                        this.w = (LinearLayout) LayoutInflater.from(this.o).inflate(R.layout.abc_action_bar_view_list_nav_layout, null);
                        r0_ViewGroup_LayoutParams = new LinearLayout.LayoutParams(-2, -1);
                        r0_ViewGroup_LayoutParams.gravity = 17;
                        this.w.addView(this.v, r0_ViewGroup_LayoutParams);
                        if (this.v.getAdapter() != this.O) {
                            this.v.setAdapter(this.O);
                        }
                        this.v.setOnItemSelectedListener(this.S);
                        addView(this.w);
                    }
                    break;
                case XListViewHeader.STATE_REFRESHING:
                    if (this.x == null || this.I) {
                        this.i = r5i;
                        requestLayout();
                    } else {
                        addView(this.x);
                    }
                    break;
            }
            this.i = r5i;
            requestLayout();
        }
    }

    public void setSplitActionBar(boolean r4z) {
        boolean r1z = false;
        if (this.d != r4z) {
            if (this.a != null) {
                ViewGroup r0_ViewGroup = (ViewGroup) this.a.getParent();
                if (r0_ViewGroup != null) {
                    r0_ViewGroup.removeView(this.a);
                }
                if (r4z) {
                    if (this.c != null) {
                        this.c.addView(this.a);
                    }
                    this.a.getLayoutParams().width = -1;
                } else {
                    addView(this.a);
                    this.a.getLayoutParams().width = -2;
                }
                this.a.requestLayout();
            }
            if (this.c != null) {
                this.c.setVisibility(r4z ? 0 : Base64.DONT_BREAK_LINES);
            }
            if (this.b != null) {
                if (r4z) {
                    this.b.setExpandedActionViewsExclusive(r1z);
                    this.b.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
                    this.b.setItemLimit(a.MAX_ACTIVITY_COUNT_UNLIMITED);
                } else {
                    this.b.setExpandedActionViewsExclusive(getResources().getBoolean(R.bool.abc_action_bar_expanded_action_views_exclusive));
                }
            }
            super.setSplitActionBar(r4z);
        }
    }

    public /* bridge */ /* synthetic */ void setSplitView(ActionBarContainer r1_ActionBarContainer) {
        super.setSplitView(r1_ActionBarContainer);
    }

    public /* bridge */ /* synthetic */ void setSplitWhenNarrow(boolean r1z) {
        super.setSplitWhenNarrow(r1z);
    }

    public void setSubtitle(CharSequence r5_CharSequence) {
        int r1i = 0;
        this.l = r5_CharSequence;
        if (this.t != null) {
            int r0i;
            this.t.setText(r5_CharSequence);
            this.t.setVisibility(r5_CharSequence != null ? 0 : 8);
            if (this.g != null || (this.j & 8) == 0) {
                r0i = 0;
            } else if (TextUtils.isEmpty(this.k) && TextUtils.isEmpty(this.l)) {
                r0i = 0;
            } else {
                r0i = 1;
            }
            LinearLayout r3_LinearLayout = this.r;
            if (r0i != 0) {
                r3_LinearLayout.setVisibility(r1i);
            } else {
                r1i = 8;
                r3_LinearLayout.setVisibility(r1i);
            }
        }
    }

    public void setTitle(CharSequence r2_CharSequence) {
        this.H = true;
        a(r2_CharSequence);
    }

    public /* bridge */ /* synthetic */ void setVisibility(int r1i) {
        super.setVisibility(r1i);
    }

    public void setWindowCallback(Callback r1_Callback) {
        this.h = r1_Callback;
    }

    public void setWindowTitle(CharSequence r2_CharSequence) {
        if (!this.H) {
            a(r2_CharSequence);
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public /* bridge */ /* synthetic */ boolean showOverflowMenu() {
        return super.showOverflowMenu();
    }
}