package qsbk.app.widget.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import qsbk.app.R;

public class XListView extends ListView implements OnScrollListener {
    private float a;
    private Scroller b;
    private OnScrollListener c;
    private IXListViewListener d;
    private XListViewHeader e;
    private RelativeLayout f;
    private int g;
    private boolean h;
    private boolean i;
    private XListViewFooter j;
    private boolean k;
    private boolean l;
    private boolean m;
    private int n;
    private int o;

    public static interface IXListViewListener {
        public void onInitHistoryData();

        public void onLoadMore();

        public void onRefresh();
    }

    public static interface OnXScrollListener extends OnScrollListener {
        public void onXScrolling(View r1_View);
    }

    public XListView(Context r3_Context) {
        super(r3_Context);
        this.a = -1.0f;
        this.h = true;
        this.i = false;
        this.m = false;
        a(r3_Context);
    }

    public XListView(Context r3_Context, AttributeSet r4_AttributeSet) {
        super(r3_Context, r4_AttributeSet);
        this.a = -1.0f;
        this.h = true;
        this.i = false;
        this.m = false;
        a(r3_Context);
    }

    public XListView(Context r3_Context, AttributeSet r4_AttributeSet, int r5i) {
        super(r3_Context, r4_AttributeSet, r5i);
        this.a = -1.0f;
        this.h = true;
        this.i = false;
        this.m = false;
        a(r3_Context);
    }

    private void a() {
        if (this.c instanceof OnXScrollListener) {
            ((OnXScrollListener) this.c).onXScrolling(this);
        }
    }

    private void a(float r5f) {
        this.e.setVisiableHeight(((int) r5f) + this.e.getVisiableHeight());
        if ((!this.h) || this.i) {
            setSelection(0);
        } else if (this.e.getVisiableHeight() > this.g) {
            this.e.setState(1);
            setSelection(0);
        } else {
            this.e.setState(0);
            setSelection(0);
        }
    }

    private void a(Context r3_Context) {
        this.b = new Scroller(r3_Context, new DecelerateInterpolator());
        super.setOnScrollListener(this);
        this.e = new XListViewHeader(r3_Context);
        this.f = (RelativeLayout) this.e.findViewById(R.id.xlistview_header_content);
        addHeaderView(this.e);
        this.j = new XListViewFooter(r3_Context);
        this.e.getViewTreeObserver().addOnGlobalLayoutListener(new a(this));
    }

    private void b() {
        int r2i = this.e.getVisiableHeight();
        if (r2i == 0) {
        } else if ((!this.i) || r2i > this.g) {
            int r3i;
            if ((!this.i) || r2i <= this.g) {
                r3i = 0;
                this.o = 0;
                this.b.startScroll(0, r2i, 0, r3i - r2i, 400);
                invalidate();
            } else {
                r3i = this.g;
                this.o = 0;
                this.b.startScroll(0, r2i, 0, r3i - r2i, 400);
                invalidate();
            }
        }
    }

    private void b(float r4f) {
        int r0i = this.j.getBottomMargin() + ((int) r4f);
        if ((!this.k) || this.l) {
            this.j.setState(0);
        } else if (r0i > 50) {
            this.j.setState(1);
        } else {
            this.j.setState(0);
        }
        this.j.setBottomMargin(r0i);
    }

    private void c() {
        int r2i = this.j.getBottomMargin();
        if (r2i > 0) {
            this.o = 1;
            this.b.startScroll(0, r2i, 0, -r2i, 400);
            invalidate();
        }
    }

    private void d() {
        this.l = true;
        this.j.setState(XListViewHeader.STATE_REFRESHING);
        if (this.d != null) {
            this.d.onLoadMore();
        }
    }

    public void computeScroll() {
        if (this.b.computeScrollOffset()) {
            if (this.o == 0) {
                this.e.setVisiableHeight(this.b.getCurrY());
            } else {
                this.j.setBottomMargin(this.b.getCurrY());
            }
            postInvalidate();
            a();
        }
        super.computeScroll();
    }

    public void initData() {
        this.e.setVisiableHeight(this.e.measureHeight);
        this.i = true;
        this.e.setState(XListViewHeader.STATE_REFRESHING);
        this.j.hide();
        if (this.d != null) {
            this.d.onRefresh();
        }
    }

    public void loadNoMore() {
        this.j.hide();
    }

    public void onScroll(AbsListView r2_AbsListView, int r3i, int r4i, int r5i) {
        this.n = r5i;
        if (this.c != null) {
            this.c.onScroll(r2_AbsListView, r3i, r4i, r5i);
        }
    }

    public void onScrollStateChanged(AbsListView r2_AbsListView, int r3i) {
        if (this.c != null) {
            this.c.onScrollStateChanged(r2_AbsListView, r3i);
        }
    }

    public boolean onTouchEvent(MotionEvent r6_MotionEvent) {
        if (this.a == -1.0f) {
            this.a = r6_MotionEvent.getRawY();
        }
        switch (r6_MotionEvent.getAction()) {
            case XListViewHeader.STATE_NORMAL:
                this.a = r6_MotionEvent.getRawY();
                return super.onTouchEvent(r6_MotionEvent);
            case XListViewHeader.STATE_REFRESHING:
                float r0f = r6_MotionEvent.getRawY() - this.a;
                this.a = r6_MotionEvent.getRawY();
                if (getFirstVisiblePosition() == 0) {
                    if (this.e.getVisiableHeight() > 0 || r0f > 0.0f) {
                        a(r0f / 1.8f);
                        a();
                    }
                    return super.onTouchEvent(r6_MotionEvent);
                }
                if (getLastVisiblePosition() == this.n - 1) {
                    if ((this.j.getBottomMargin() > 0 || r0f < 0.0f) && (!this.l)) {
                        b((-r0f) / 1.8f);
                    }
                }
                return super.onTouchEvent(r6_MotionEvent);
        }
        this.a = -1.0f;
        if (getFirstVisiblePosition() == 0) {
            if ((!this.h) || this.e.getVisiableHeight() <= this.g) {
                b();
            } else {
                this.i = true;
                this.e.setState(XListViewHeader.STATE_REFRESHING);
                if (this.d != null) {
                    this.d.onRefresh();
                }
                b();
            }
            return super.onTouchEvent(r6_MotionEvent);
        } else {
            if (getLastVisiblePosition() == this.n - 1) {
                if ((!this.k) || this.j.getBottomMargin() <= 50) {
                    c();
                } else {
                    d();
                    c();
                }
            }
            return super.onTouchEvent(r6_MotionEvent);
        }
    }

    public void setAdapter(ListAdapter r2_ListAdapter) {
        if (!this.m) {
            this.m = true;
            addFooterView(this.j);
        }
        super.setAdapter(r2_ListAdapter);
    }

    public void setOnScrollListener(OnScrollListener r1_OnScrollListener) {
        this.c = r1_OnScrollListener;
    }

    public void setPullLoadEnable(boolean r3z) {
        this.k = r3z;
        this.l = false;
        this.j.show();
        this.j.setState(0);
        this.j.setOnClickListener(new b(this));
    }

    public void setPullRefreshEnable(boolean r3z) {
        this.h = r3z;
        if (this.h) {
            this.f.setVisibility(0);
        } else {
            this.f.setVisibility(XListViewFooter.STATE_NODATA);
        }
    }

    public void setRefreshTime(String r1_String) {
    }

    public void setXListViewListener(IXListViewListener r1_IXListViewListener) {
        this.d = r1_IXListViewListener;
    }

    public void stopLoadMore() {
        this.j.show();
        if (this.l) {
            this.l = false;
            this.j.setState(0);
        }
    }

    public void stopRefresh() {
        if (this.i) {
            this.i = false;
            b();
        }
    }
}