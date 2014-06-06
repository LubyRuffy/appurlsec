package qsbk.app.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class LoaderLayout extends RelativeLayout implements Recyclable {
    private static int c;
    private static int d;
    private ProgressBar a;
    private TextView b;

    static {
        d = -1;
    }

    public LoaderLayout(Context r1_Context) {
        super(r1_Context);
        a();
    }

    public LoaderLayout(Context r1_Context, AttributeSet r2_AttributeSet) {
        super(r1_Context, r2_AttributeSet);
        a();
    }

    public LoaderLayout(Context r1_Context, AttributeSet r2_AttributeSet, int r3i) {
        super(r1_Context, r2_AttributeSet, r3i);
        a();
    }

    private void a() {
        if (d == -1) {
            Resources r0_Resources = getResources();
            d = r0_Resources.getDimensionPixelSize(R.dimen.g_txt_middle);
            c = r0_Resources.getColor(R.color.g_txt_middle);
        }
    }

    private void b() {
        setGravity(R.styleable.ActionBar_progressBarPadding);
        LayoutParams r0_LayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        r0_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY);
        this.a = new ProgressBar(getContext());
        this.a.setIndeterminate(true);
        this.a.setId(R.id.progressBar);
        this.a.setLayoutParams(r0_LayoutParams);
        addView(this.a);
        r0_LayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        r0_LayoutParams.addRule(NearbySelectView.TIME_15MIN);
        r0_LayoutParams.addRule(1, R.id.progressBar);
        r0_LayoutParams.leftMargin = 20;
        this.b = new TextView(getContext());
        this.b.setMaxLines(XListViewHeader.STATE_REFRESHING);
        this.b.setTextColor(c);
        this.b.setTextSize((float) d);
        this.b.setLayoutParams(r0_LayoutParams);
        this.b.setText("Loading~~~");
        addView(this.b);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        b();
    }

    public void recycle() {
        removeAllViews();
        this.b = null;
        this.a = null;
    }

    public void setLoadFailed(String r3_String) {
        this.b.setText(r3_String);
        this.a.setVisibility(0);
    }

    public void setLoaded(String r3_String) {
        this.b.setText(r3_String);
        this.a.setVisibility(XListViewFooter.STATE_NODATA);
    }

    public void setLoading(String r3_String) {
        this.b.setText(r3_String);
        this.a.setVisibility(0);
    }
}