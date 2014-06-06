package qsbk.app.widget.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class LinearLayoutForListView extends LinearLayout {
    private BaseAdapter a;
    private OnClickListener b;

    public LinearLayoutForListView(Context r2_Context) {
        super(r2_Context);
        this.b = null;
    }

    public LinearLayoutForListView(Context r2_Context, AttributeSet r3_AttributeSet) {
        super(r2_Context, r3_AttributeSet);
        this.b = null;
    }

    public void bindLinearLayout() {
        int r2i = this.a.getCount();
        int r1i = 0;
        while (r1i < r2i) {
            View r0_View = this.a.getView(r1i, null, null);
            r0_View.setOnClickListener(this.b);
            if (r1i == r2i) {
                ((RelativeLayout) r0_View).removeViewAt(XListViewHeader.STATE_REFRESHING);
            } else {
                addView(r0_View, getChildCount());
            }
            r1i++;
        }
    }

    public BaseAdapter getAdapter() {
        return this.a;
    }

    public OnClickListener getOnClickListener() {
        return this.b;
    }

    public void setAdapter(BaseAdapter r1_BaseAdapter) {
        this.a = r1_BaseAdapter;
        bindLinearLayout();
    }

    public void setOnClickListener(OnClickListener r1_OnClickListener) {
        this.b = r1_OnClickListener;
    }
}