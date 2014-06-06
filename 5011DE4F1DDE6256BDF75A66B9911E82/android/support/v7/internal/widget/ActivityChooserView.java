package android.support.v7.internal.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.ActivityChooserModel.ActivityChooserModelClient;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class ActivityChooserView extends ViewGroup implements ActivityChooserModelClient {
    ActionProvider a;
    private final a b;
    private final b c;
    private final LinearLayout d;
    private final Drawable e;
    private final FrameLayout f;
    private final ImageView g;
    private final FrameLayout h;
    private final ImageView i;
    private final int j;
    private final DataSetObserver k;
    private final OnGlobalLayoutListener l;
    private ListPopupWindow m;
    private OnDismissListener n;
    private boolean o;
    private int p;
    private boolean q;
    private int r;

    private class a extends BaseAdapter {
        public static final int MAX_ACTIVITY_COUNT_DEFAULT = 4;
        public static final int MAX_ACTIVITY_COUNT_UNLIMITED = 2147483647;
        private ActivityChooserModel b;
        private int c;
        private boolean d;
        private boolean e;
        private boolean f;

        private a() {
            this.c = 4;
        }

        public int getActivityCount() {
            return this.b.getActivityCount();
        }

        public int getCount() {
            int r0i = this.b.getActivityCount();
            if (this.d || this.b.getDefaultActivity() == null) {
                r0i = Math.min(r0i, this.c);
                return this.f ? r0i : r0i + 1;
            } else {
                r0i--;
                r0i = Math.min(r0i, this.c);
                if (this.f) {
                }
            }
        }

        public ActivityChooserModel getDataModel() {
            return this.b;
        }

        public ResolveInfo getDefaultActivity() {
            return this.b.getDefaultActivity();
        }

        public int getHistorySize() {
            return this.b.getHistorySize();
        }

        public Object getItem(int r2i) {
            switch (getItemViewType(r2i)) {
                case XListViewHeader.STATE_NORMAL:
                    if (this.d || this.b.getDefaultActivity() == null) {
                        return this.b.getActivity(r2i);
                    }
                    r2i++;
                    return this.b.getActivity(r2i);
                case XListViewHeader.STATE_READY:
                    return null;
            }
            throw new IllegalArgumentException();
        }

        public long getItemId(int r3i) {
            return (long) r3i;
        }

        public int getItemViewType(int r2i) {
            return (this.f && r2i == getCount() - 1) ? 1 : 0;
        }

        public int getMaxActivityCount() {
            return this.c;
        }

        public boolean getShowDefaultActivity() {
            return this.d;
        }

        public View getView(int r5i, View r6_View, ViewGroup r7_ViewGroup) {
            boolean r2z = false;
            switch (getItemViewType(r5i)) {
                case XListViewHeader.STATE_NORMAL:
                    PackageManager r2_PackageManager;
                    ResolveInfo r1_ResolveInfo;
                    if (r6_View == null || r6_View.getId() != R.id.list_item) {
                        r6_View = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(R.layout.abc_activity_chooser_view_list_item, r7_ViewGroup, false);
                        r2_PackageManager = ActivityChooserView.this.getContext().getPackageManager();
                        r1_ResolveInfo = (ResolveInfo) getItem(r5i);
                        ((ImageView) r6_View.findViewById(R.id.icon)).setImageDrawable(r1_ResolveInfo.loadIcon(r2_PackageManager));
                        ((TextView) r6_View.findViewById(R.id.title)).setText(r1_ResolveInfo.loadLabel(r2_PackageManager));
                    } else {
                        r2_PackageManager = ActivityChooserView.this.getContext().getPackageManager();
                        r1_ResolveInfo = (ResolveInfo) getItem(r5i);
                        ((ImageView) r6_View.findViewById(R.id.icon)).setImageDrawable(r1_ResolveInfo.loadIcon(r2_PackageManager));
                        ((TextView) r6_View.findViewById(R.id.title)).setText(r1_ResolveInfo.loadLabel(r2_PackageManager));
                    }
                    return (this.d && r5i == 0 && this.e) ? r6_View : r6_View;
                case XListViewHeader.STATE_READY:
                    if (r6_View != null && r6_View.getId() == 1) {
                        return r6_View;
                    }
                    r6_View = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(R.layout.abc_activity_chooser_view_list_item, r7_ViewGroup, r2z);
                    r6_View.setId(1);
                    ((TextView) r6_View.findViewById(R.id.title)).setText(ActivityChooserView.this.getContext().getString(R.string.abc_activity_chooser_view_see_all));
                    return r6_View;
            }
            throw new IllegalArgumentException();
        }

        public int getViewTypeCount() {
            return XListViewFooter.STATE_NOMORE;
        }

        public int measureContentWidth() {
            int r0i = 0;
            int r4i = this.c;
            this.c = 2147483647;
            int r5i = MeasureSpec.makeMeasureSpec(0, 0);
            int r6i = MeasureSpec.makeMeasureSpec(0, 0);
            int r7i = getCount();
            View r1_View = null;
            int r3i = 0;
            while (r0i < r7i) {
                r1_View = getView(r0i, r1_View, null);
                r1_View.measure(r5i, r6i);
                r3i = Math.max(r3i, r1_View.getMeasuredWidth());
                r0i++;
            }
            this.c = r4i;
            return r3i;
        }

        public void setDataModel(ActivityChooserModel r3_ActivityChooserModel) {
            ActivityChooserModel r0_ActivityChooserModel = ActivityChooserView.this.b.getDataModel();
            if (r0_ActivityChooserModel == null || (!ActivityChooserView.this.isShown())) {
                this.b = r3_ActivityChooserModel;
                if (r3_ActivityChooserModel == null || (!ActivityChooserView.this.isShown())) {
                    notifyDataSetChanged();
                } else {
                    r3_ActivityChooserModel.registerObserver(ActivityChooserView.this.k);
                    notifyDataSetChanged();
                }
            } else {
                r0_ActivityChooserModel.unregisterObserver(ActivityChooserView.this.k);
                this.b = r3_ActivityChooserModel;
                if (r3_ActivityChooserModel == null || ActivityChooserView.this.isShown()) {
                    notifyDataSetChanged();
                } else {
                    r3_ActivityChooserModel.registerObserver(ActivityChooserView.this.k);
                    notifyDataSetChanged();
                }
            }
        }

        public void setMaxActivityCount(int r2i) {
            if (this.c != r2i) {
                this.c = r2i;
                notifyDataSetChanged();
            }
        }

        public void setShowDefaultActivity(boolean r2z, boolean r3z) {
            if (!(this.d == r2z && this.e == r3z)) {
                this.d = r2z;
                this.e = r3z;
                notifyDataSetChanged();
            }
        }

        public void setShowFooterView(boolean r2z) {
            if (this.f != r2z) {
                this.f = r2z;
                notifyDataSetChanged();
            }
        }
    }

    private class b implements OnClickListener, OnLongClickListener, OnItemClickListener, OnDismissListener {
        private b() {
        }

        private void a() {
            if (ActivityChooserView.this.n != null) {
                ActivityChooserView.this.n.onDismiss();
            }
        }

        public void onClick(View r3_View) {
            if (r3_View == ActivityChooserView.this.h) {
                ActivityChooserView.this.dismissPopup();
                Intent r0_Intent = ActivityChooserView.this.b.getDataModel().chooseActivity(ActivityChooserView.this.b.getDataModel().getActivityIndex(ActivityChooserView.this.b.getDefaultActivity()));
                if (r0_Intent != null) {
                    r0_Intent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
                    ActivityChooserView.this.getContext().startActivity(r0_Intent);
                }
            } else if (r3_View == ActivityChooserView.this.f) {
                ActivityChooserView.this.o = false;
                ActivityChooserView.this.a(ActivityChooserView.this.p);
            } else {
                throw new IllegalArgumentException();
            }
        }

        public void onDismiss() {
            a();
            if (ActivityChooserView.this != null) {
                ActivityChooserView.this.subUiVisibilityChanged(false);
            }
        }

        public void onItemClick(AdapterView<?> r3_AdapterView_, View r4_View, int r5i, long r6j) {
            switch (((a) r3_AdapterView_.getAdapter()).getItemViewType(r5i)) {
                case XListViewHeader.STATE_NORMAL:
                    ActivityChooserView.this.dismissPopup();
                    if (ActivityChooserView.this.o) {
                        if (r5i > 0) {
                            ActivityChooserView.this.b.getDataModel().setDefaultActivity(r5i);
                        }
                        return;
                    } else if (ActivityChooserView.this.b.getShowDefaultActivity()) {
                        r0_Intent = ActivityChooserView.this.b.getDataModel().chooseActivity(r5i);
                        if (r0_Intent == null) {
                            r0_Intent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
                            ActivityChooserView.this.getContext().startActivity(r0_Intent);
                        }
                        return;
                    } else {
                        r5i++;
                        r0_Intent = ActivityChooserView.this.b.getDataModel().chooseActivity(r5i);
                        if (r0_Intent == null) {
                            return;
                        }
                        r0_Intent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
                        ActivityChooserView.this.getContext().startActivity(r0_Intent);
                        return;
                    }
                case XListViewHeader.STATE_READY:
                    ActivityChooserView.this.a((int)a.MAX_ACTIVITY_COUNT_UNLIMITED);
                    return;
            }
            throw new IllegalArgumentException();
        }

        public boolean onLongClick(View r4_View) {
            if (r4_View == ActivityChooserView.this.h) {
                if (ActivityChooserView.this.b.getCount() > 0) {
                    ActivityChooserView.this.o = true;
                    ActivityChooserView.this.a(ActivityChooserView.this.p);
                }
                return true;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public ActivityChooserView(Context r2_Context) {
        this(r2_Context, null);
    }

    public ActivityChooserView(Context r2_Context, AttributeSet r3_AttributeSet) {
        this(r2_Context, r3_AttributeSet, 0);
    }

    public ActivityChooserView(Context r6_Context, AttributeSet r7_AttributeSet, int r8i) {
        super(r6_Context, r7_AttributeSet, r8i);
        this.k = new i(this);
        this.l = new j(this);
        this.p = 4;
        TypedArray r0_TypedArray = r6_Context.obtainStyledAttributes(r7_AttributeSet, R.styleable.ActivityChooserView, r8i, 0);
        this.p = r0_TypedArray.getInt(0, XListViewFooter.STATE_NODATA);
        Drawable r1_Drawable = r0_TypedArray.getDrawable(1);
        r0_TypedArray.recycle();
        LayoutInflater.from(getContext()).inflate(R.layout.abc_activity_chooser_view, this, true);
        this.c = new b(null);
        this.d = (LinearLayout) findViewById(R.id.activity_chooser_view_content);
        this.e = this.d.getBackground();
        this.h = (FrameLayout) findViewById(R.id.default_activity_button);
        this.h.setOnClickListener(this.c);
        this.h.setOnLongClickListener(this.c);
        this.i = (ImageView) this.h.findViewById(R.id.image);
        this.f = (FrameLayout) findViewById(R.id.expand_activities_button);
        this.f.setOnClickListener(this.c);
        this.g = (ImageView) this.f.findViewById(R.id.image);
        this.g.setImageDrawable(r1_Drawable);
        this.b = new a(null);
        this.b.registerDataSetObserver(new k(this));
        Resources r0_Resources = r6_Context.getResources();
        this.j = Math.max(r0_Resources.getDisplayMetrics().widthPixels / 2, r0_Resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
    }

    private ListPopupWindow a() {
        if (this.m == null) {
            this.m = new ListPopupWindow(getContext());
            this.m.setAdapter(this.b);
            this.m.setAnchorView(this);
            this.m.setModal(true);
            this.m.setOnItemClickListener(this.c);
            this.m.setOnDismissListener(this.c);
        }
        return this.m;
    }

    private void a(int r7i) {
        if (this.b.getDataModel() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        } else {
            boolean r0z;
            getViewTreeObserver().addOnGlobalLayoutListener(this.l);
            r0z = this.h.getVisibility() == 0;
            int r4i = this.b.getActivityCount();
            int r3i = r0z ? 1 : 0;
            if (r7i == 2147483647 || r4i <= r3i + r7i) {
                this.b.setShowFooterView(false);
                this.b.setMaxActivityCount(r7i);
            } else {
                this.b.setShowFooterView(true);
                this.b.setMaxActivityCount(r7i - 1);
            }
            ListPopupWindow r3_ListPopupWindow = a();
            if (!r3_ListPopupWindow.isShowing()) {
                if (this.o || (!r0z)) {
                    this.b.setShowDefaultActivity(true, r0z);
                } else {
                    this.b.setShowDefaultActivity(false, false);
                }
                r3_ListPopupWindow.setContentWidth(Math.min(this.b.measureContentWidth(), this.j));
                r3_ListPopupWindow.show();
                if (this.a != null) {
                    this.a.subUiVisibilityChanged(true);
                }
                r3_ListPopupWindow.getListView().setContentDescription(getContext().getString(R.string.abc_activitychooserview_choose_application));
            }
        }
    }

    private void b() {
        if (this.b.getCount() > 0) {
            this.f.setEnabled(true);
        } else {
            this.f.setEnabled(false);
        }
        int r0i = this.b.getActivityCount();
        int r1i = this.b.getHistorySize();
        ResolveInfo r0_ResolveInfo;
        PackageManager r1_PackageManager;
        CharSequence r0_CharSequence;
        Context r1_Context;
        int r2i;
        Object[] r3_ObjectA;
        if (r0i != 1) {
            if (r0i <= 1 || r1i <= 0) {
                this.h.setVisibility(Base64.DONT_BREAK_LINES);
            } else {
                this.h.setVisibility(0);
                r0_ResolveInfo = this.b.getDefaultActivity();
                r1_PackageManager = getContext().getPackageManager();
                this.i.setImageDrawable(r0_ResolveInfo.loadIcon(r1_PackageManager));
                if (this.r == 0) {
                    r0_CharSequence = r0_ResolveInfo.loadLabel(r1_PackageManager);
                    r1_Context = getContext();
                    r2i = this.r;
                    r3_ObjectA = new Object[1];
                    r3_ObjectA[0] = r0_CharSequence;
                    this.h.setContentDescription(r1_Context.getString(r2i, r3_ObjectA));
                }
            }
        } else {
            this.h.setVisibility(0);
            r0_ResolveInfo = this.b.getDefaultActivity();
            r1_PackageManager = getContext().getPackageManager();
            this.i.setImageDrawable(r0_ResolveInfo.loadIcon(r1_PackageManager));
            if (this.r == 0) {
            } else {
                r0_CharSequence = r0_ResolveInfo.loadLabel(r1_PackageManager);
                r1_Context = getContext();
                r2i = this.r;
                r3_ObjectA = new Object[1];
                r3_ObjectA[0] = r0_CharSequence;
                this.h.setContentDescription(r1_Context.getString(r2i, r3_ObjectA));
            }
        }
        if (this.h.getVisibility() == 0) {
            this.d.setBackgroundDrawable(this.e);
        } else {
            this.d.setBackgroundDrawable(null);
        }
    }

    public boolean dismissPopup() {
        if (isShowingPopup()) {
            a().dismiss();
            ViewTreeObserver r0_ViewTreeObserver = getViewTreeObserver();
            if (r0_ViewTreeObserver.isAlive()) {
                r0_ViewTreeObserver.removeGlobalOnLayoutListener(this.l);
            }
        }
        return true;
    }

    public ActivityChooserModel getDataModel() {
        return this.b.getDataModel();
    }

    public boolean isShowingPopup() {
        return a().isShowing();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ActivityChooserModel r0_ActivityChooserModel = this.b.getDataModel();
        if (r0_ActivityChooserModel != null) {
            r0_ActivityChooserModel.registerObserver(this.k);
        }
        this.q = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActivityChooserModel r0_ActivityChooserModel = this.b.getDataModel();
        if (r0_ActivityChooserModel != null) {
            r0_ActivityChooserModel.unregisterObserver(this.k);
        }
        ViewTreeObserver r0_ViewTreeObserver = getViewTreeObserver();
        if (r0_ViewTreeObserver.isAlive()) {
            r0_ViewTreeObserver.removeGlobalOnLayoutListener(this.l);
        }
        if (isShowingPopup()) {
            dismissPopup();
        }
        this.q = false;
    }

    protected void onLayout(boolean r5z, int r6i, int r7i, int r8i, int r9i) {
        this.d.layout(0, 0, r8i - r6i, r9i - r7i);
        if (!isShowingPopup()) {
            dismissPopup();
        }
    }

    protected void onMeasure(int r4i, int r5i) {
        View r0_View = this.d;
        if (this.h.getVisibility() != 0) {
            r5i = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(r5i), 1073741824);
        }
        measureChild(r0_View, r4i, r5i);
        setMeasuredDimension(r0_View.getMeasuredWidth(), r0_View.getMeasuredHeight());
    }

    public void setActivityChooserModel(ActivityChooserModel r2_ActivityChooserModel) {
        this.b.setDataModel(r2_ActivityChooserModel);
        if (isShowingPopup()) {
            dismissPopup();
            showPopup();
        }
    }

    public void setDefaultActionButtonContentDescription(int r1i) {
        this.r = r1i;
    }

    public void setExpandActivityOverflowButtonContentDescription(int r3i) {
        this.g.setContentDescription(getContext().getString(r3i));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable r2_Drawable) {
        this.g.setImageDrawable(r2_Drawable);
    }

    public void setInitialActivityCount(int r1i) {
        this.p = r1i;
    }

    public void setOnDismissListener(OnDismissListener r1_OnDismissListener) {
        this.n = r1_OnDismissListener;
    }

    public void setProvider(ActionProvider r1_ActionProvider) {
        this.a = r1_ActionProvider;
    }

    public boolean showPopup() {
        boolean r0z = false;
        if (isShowingPopup() || (!this.q)) {
            return false;
        }
        this.o = r0z;
        a(this.p);
        return true;
    }
}