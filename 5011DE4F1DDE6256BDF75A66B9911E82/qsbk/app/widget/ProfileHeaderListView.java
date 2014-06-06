package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import qsbk.app.R;
import qsbk.app.widget.HeaderTabBar.OnTabClickListener;
import qsbk.app.widget.listview.XListViewFooter;

public class ProfileHeaderListView extends ListView implements OnScrollListener, Recyclable {
    public static final int INVALID_TAB_ID = -1;
    private static final String o;
    private int a;
    private int b;
    private int c;
    private int d;
    private HeaderTabBar e;
    private LoadDataListener f;
    private OnScrollListener g;
    private OnScrollDirectionListener h;
    private OneProfileHeaderView i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;

    public static interface OnScrollDirectionListener {
        public void onScrollDown();

        public void onScrollUp();
    }

    public static interface LoadDataListener {
        public void onLoadMore();
    }

    static {
        o = ProfileHeaderListView.class.getName();
    }

    public ProfileHeaderListView(Context r3_Context) {
        super(r3_Context);
        this.a = -1;
        this.b = -1;
        this.c = -1;
        this.d = -1;
        this.j = -1;
        this.k = 0;
        this.l = -1;
        this.m = -1;
        this.n = 0;
        a();
    }

    public ProfileHeaderListView(Context r3_Context, AttributeSet r4_AttributeSet) {
        super(r3_Context, r4_AttributeSet);
        this.a = -1;
        this.b = -1;
        this.c = -1;
        this.d = -1;
        this.j = -1;
        this.k = 0;
        this.l = -1;
        this.m = -1;
        this.n = 0;
        a();
    }

    public ProfileHeaderListView(Context r3_Context, AttributeSet r4_AttributeSet, int r5i) {
        super(r3_Context, r4_AttributeSet, r5i);
        this.a = -1;
        this.b = -1;
        this.c = -1;
        this.d = -1;
        this.j = -1;
        this.k = 0;
        this.l = -1;
        this.m = -1;
        this.n = 0;
        a();
    }

    private void a() {
        super.setOnScrollListener(this);
        if (this.i == null) {
            this.i = (OneProfileHeaderView) inflate(getContext(), R.layout.one_profile_header, null);
            this.i.getViewTreeObserver().addOnGlobalLayoutListener(new b(this));
            addHeaderView(this.i);
        }
    }

    private void a(AbsListView r4_AbsListView, int r5i, int r6i, int r7i) {
        int r1i = 0;
        if (r5i == this.m) {
            int r0i = r4_AbsListView.getChildAt(r1i).getTop();
            if (r0i <= this.l || Math.abs(r0i - this.l) <= 20) {
                if (r0i >= this.l || Math.abs(r0i - this.l) <= 20) {
                } else {
                    if (this.h != null) {
                        this.h.onScrollDown();
                    }
                    this.l = r0i;
                }
            } else {
                if (this.h != null) {
                    this.h.onScrollUp();
                }
                this.l = r0i;
            }
        } else {
            View r0_View = r4_AbsListView.getChildAt(0);
            if (r0_View != null) {
                this.m = r5i;
                this.l = r0_View.getTop();
            }
        }
    }

    private void b() {
        if (this.i == null || this.e == null) {
        } else {
            if (this.a - this.i.getTop() >= this.n || getChildAt(0) != this.i) {
                this.e.setVisibility(0);
            } else {
                this.e.setVisibility(XListViewFooter.STATE_NODATA);
            }
        }
    }

    public void addDuplicateHeaderTabBar(HeaderTabBar r2_HeaderTabBar) {
        this.e = r2_HeaderTabBar;
        this.i.setRelativeHeaderTabBar(r2_HeaderTabBar);
    }

    public void addHeaderView(View r1_View) {
        super.addHeaderView(r1_View);
    }

    public void addTab(int r2i, String r3_String, int r4i) {
        this.i.addTab(r2i, r3_String, r4i);
    }

    public void onScroll(AbsListView r2_AbsListView, int r3i, int r4i, int r5i) {
        if (this.g != null) {
            this.g.onScroll(r2_AbsListView, r3i, r4i, r5i);
        }
        if (r4i == 0) {
        } else {
            this.k = r5i;
            b();
            a(r2_AbsListView, r3i, r4i, r5i);
            if (this.g != null) {
                this.g.onScroll(r2_AbsListView, r3i, r4i, r5i);
            }
        }
    }

    public void onScrollStateChanged(AbsListView r2_AbsListView, int r3i) {
        if (this.g != null) {
            this.g.onScrollStateChanged(r2_AbsListView, r3i);
        }
    }

    public void recycle() {
        if (this.e != null) {
            this.e.recycle();
        }
        if (this.i != null) {
            this.i.recycle();
            this.i = null;
        }
        this.f = null;
        this.h = null;
        this.g = null;
    }

    public void setAdapter(ListAdapter r3_ListAdapter) {
        if (getChildAt(1) != null) {
            this.d = this.i.getTop();
        }
        super.setAdapter(r3_ListAdapter);
        if (this.e.getVisibility() == 0) {
            setSelectionFromTop(1, this.c);
        } else {
            setSelectionFromTop(0, this.d);
        }
    }

    public void setOnHeaderTabClickListener(OnTabClickListener r2_OnTabClickListener) {
        this.i.setOnTabClickListener(r2_OnTabClickListener);
    }

    public void setOnScrollDirectionListener(OnScrollDirectionListener r1_OnScrollDirectionListener) {
        this.h = r1_OnScrollDirectionListener;
    }

    public void setOnScrollListener(OnScrollListener r1_OnScrollListener) {
        this.g = r1_OnScrollListener;
    }

    public void setSelectedTab(int r2i) {
        this.j = r2i;
        this.i.setSelectedTab(r2i);
        if (this.e != null) {
            this.e.setSelectedTab(r2i);
        }
    }
}