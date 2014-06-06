package qsbk.app.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.R;

public class HeaderTabBar extends HorizontalScrollView implements OnClickListener, Recyclable {
    private static int e;
    private LinearLayout a;
    private OnTabClickListener b;
    private int c;
    private int d;
    private HeaderTabBar f;
    private ArrayList<a> g;

    public static interface OnTabClickListener {
        public void onTabClick(int r1i);
    }


    private final class a {
        public int id;
        public Drawable select;
        public TextView textView;
        public Drawable unSelect;

        private a() {
        }
    }

    public HeaderTabBar(Context r3_Context) {
        super(r3_Context);
        this.g = new ArrayList(2);
        a();
    }

    public HeaderTabBar(Context r3_Context, AttributeSet r4_AttributeSet) {
        super(r3_Context, r4_AttributeSet);
        this.g = new ArrayList(2);
        a();
    }

    public HeaderTabBar(Context r3_Context, AttributeSet r4_AttributeSet, int r5i) {
        super(r3_Context, r4_AttributeSet, r5i);
        this.g = new ArrayList(2);
        a();
    }

    private void a() {
        if (e == 0) {
            e = getResources().getDimensionPixelSize(R.dimen.profile_header_tab_fading_edge_length);
        }
    }

    public final void addTab(int r5i, String r6_String, int r7i) {
        if (this.g.size() != 0) {
            View.inflate(getContext(), R.layout.divider_vertical_medium, this.a);
        }
        a r1_a = new a(null);
        r1_a.id = r7i;
        r1_a.textView = (TextView) inflate(getContext(), r5i, null);
        r1_a.textView.setText(r6_String);
        r1_a.textView.setTag(Integer.valueOf(r7i));
        r1_a.textView.setOnClickListener(this);
        if (this.c == 0) {
            this.c = r1_a.textView.getPaddingLeft();
            this.d = r1_a.textView.getPaddingRight();
        }
        Resources r0_Resources = getResources();
        r1_a.select = r0_Resources.getDrawable(R.drawable.profile_tab_active_item_selector);
        r1_a.unSelect = r0_Resources.getDrawable(R.drawable.profile_tab_inactive_item_selector);
        this.g.add(r1_a);
        LayoutParams r0_LayoutParams = new LinearLayout.LayoutParams(0, -1);
        r0_LayoutParams.weight = 1.0f;
        this.a.addView(r1_a.textView, r0_LayoutParams);
    }

    public final void initialFeature() {
        setHorizontalScrollBarEnabled(false);
        setHorizontalFadingEdgeEnabled(true);
        setFadingEdgeLength(e);
        setFillViewport(true);
        setBackgroundDrawable(getResources().getDrawable(R.drawable.profile_tab_bg));
        this.a = new LinearLayout(getContext());
        this.a.setOrientation(0);
        addView(this.a, generateDefaultLayoutParams());
    }

    public void onClick(View r3_View) {
        if (this.b == null) {
        } else {
            this.b.onTabClick(((Integer) r3_View.getTag()).intValue());
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        initialFeature();
    }

    protected void onScrollChanged(int r2i, int r3i, int r4i, int r5i) {
        super.onScrollChanged(r2i, r3i, r4i, r5i);
        if (this.f != null) {
            this.f.scrollTo(r2i, r3i);
        }
    }

    public void recycle() {
        this.a.removeAllViews();
        this.g.clear();
        this.b = null;
        this.c = 0;
        this.d = 0;
        if (this.f != null) {
            HeaderTabBar r0_HeaderTabBar = this.f;
            if (r0_HeaderTabBar.f == this) {
                r0_HeaderTabBar.f = null;
            }
            this.f = null;
        }
    }

    public final void setOnTabClickListener(OnTabClickListener r1_OnTabClickListener) {
        this.b = r1_OnTabClickListener;
    }

    public final void setRelativeHeaderTabBar(HeaderTabBar r3_HeaderTabBar) {
        this.f = r3_HeaderTabBar;
        if (r3_HeaderTabBar != null) {
            scrollTo(r3_HeaderTabBar.getScrollX(), r3_HeaderTabBar.getScrollY());
        }
    }

    public final void setSelectedTab(int r7i) {
        Iterator r1_Iterator = this.g.iterator();
        while (r1_Iterator.hasNext()) {
            a r0_a = (a) r1_Iterator.next();
            r0_a.textView.setPadding(this.c, 0, this.d, 0);
            if (r0_a.id == r7i) {
                r0_a.textView.setBackgroundDrawable(r0_a.select);
            } else {
                r0_a.textView.setBackgroundDrawable(r0_a.unSelect);
            }
        }
    }
}