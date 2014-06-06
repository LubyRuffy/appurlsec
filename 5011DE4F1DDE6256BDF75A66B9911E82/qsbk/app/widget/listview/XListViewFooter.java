package qsbk.app.widget.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.Base64;

public class XListViewFooter extends LinearLayout {
    public static final int STATE_LOADING = 2;
    public static final int STATE_NODATA = 4;
    public static final int STATE_NOMORE = 3;
    public static final int STATE_NORMAL = 0;
    public static final int STATE_READY = 1;
    private View a;
    private View b;
    private TextView c;

    public XListViewFooter(Context r1_Context) {
        super(r1_Context);
        a(r1_Context);
    }

    public XListViewFooter(Context r1_Context, AttributeSet r2_AttributeSet) {
        super(r1_Context, r2_AttributeSet);
        a(r1_Context);
    }

    private void a(Context r5_Context) {
        LinearLayout r0_LinearLayout = (LinearLayout) LayoutInflater.from(r5_Context).inflate(R.layout.xlistview_footer, null);
        addView(r0_LinearLayout);
        r0_LinearLayout.setLayoutParams(new LayoutParams(-1, -2));
        this.a = r0_LinearLayout.findViewById(R.id.xlistview_footer_content);
        this.b = r0_LinearLayout.findViewById(R.id.xlistview_footer_progressbar);
        this.c = (TextView) r0_LinearLayout.findViewById(R.id.xlistview_footer_hint_textview);
    }

    public int getBottomMargin() {
        return ((LayoutParams) this.a.getLayoutParams()).bottomMargin;
    }

    public int getVisiableHeight() {
        return this.a.getHeight();
    }

    public void hide() {
        LayoutParams r0_LayoutParams = (LayoutParams) this.a.getLayoutParams();
        r0_LayoutParams.height = 0;
        this.a.setLayoutParams(r0_LayoutParams);
    }

    public void loading() {
        this.c.setVisibility(Base64.DONT_BREAK_LINES);
        this.b.setVisibility(STATE_NORMAL);
    }

    public void normal() {
        this.c.setVisibility(STATE_NORMAL);
        this.b.setVisibility(Base64.DONT_BREAK_LINES);
    }

    public void setBottomMargin(int r3i) {
        if (r3i < 0) {
        } else {
            LayoutParams r0_LayoutParams = (LayoutParams) this.a.getLayoutParams();
            r0_LayoutParams.bottomMargin = r3i;
            this.a.setLayoutParams(r0_LayoutParams);
        }
    }

    public void setState(int r4i) {
        int r1i = STATE_NORMAL;
        this.c.setVisibility(STATE_NODATA);
        this.b.setVisibility(STATE_NODATA);
        this.c.setVisibility(STATE_NODATA);
        if (r4i == STATE_READY) {
            this.c.setVisibility(r1i);
            this.c.setText(R.string.xlistview_footer_hint_ready);
        } else if (r4i == 2) {
            this.b.setVisibility(STATE_NORMAL);
        } else {
            this.c.setVisibility(r1i);
            this.c.setText(R.string.xlistview_footer_hint_normal);
        }
    }

    public void show() {
        LayoutParams r0_LayoutParams = (LayoutParams) this.a.getLayoutParams();
        r0_LayoutParams.height = -2;
        this.a.setLayoutParams(r0_LayoutParams);
    }
}