package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import qsbk.app.R;
import qsbk.app.widget.HeaderTabBar.OnTabClickListener;

public class OneProfileHeaderView extends RelativeLayout implements Recyclable {
    private HeaderTabBar a;

    public OneProfileHeaderView(Context r1_Context) {
        super(r1_Context);
    }

    public OneProfileHeaderView(Context r1_Context, AttributeSet r2_AttributeSet) {
        super(r1_Context, r2_AttributeSet);
    }

    public OneProfileHeaderView(Context r1_Context, AttributeSet r2_AttributeSet, int r3i) {
        super(r1_Context, r2_AttributeSet, r3i);
    }

    public void addTab(int r2i, String r3_String, int r4i) {
        this.a.addTab(r2i, r3_String, r4i);
    }

    public int getTabBarHeight() {
        return this.a.getHeight();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (HeaderTabBar) findViewById(R.id.header_tab_bar);
    }

    public void recycle() {
        if (this.a != null) {
            this.a.recycle();
        }
    }

    public void setOnTabClickListener(OnTabClickListener r2_OnTabClickListener) {
        this.a.setOnTabClickListener(r2_OnTabClickListener);
    }

    public void setRelativeHeaderTabBar(HeaderTabBar r2_HeaderTabBar) {
        this.a.setRelativeHeaderTabBar(r2_HeaderTabBar);
        r2_HeaderTabBar.setRelativeHeaderTabBar(this.a);
    }

    public void setSelectedTab(int r2i) {
        this.a.setSelectedTab(r2i);
    }
}