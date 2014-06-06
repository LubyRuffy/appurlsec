package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import qsbk.app.R;
import qsbk.app.utils.DebugUtil;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class RatingView extends LinearLayout {
    private static final String a;
    private ImageView[] b;

    static {
        a = RatingView.class.getSimpleName();
    }

    public RatingView(Context r3_Context) {
        this(r3_Context, null, 0);
    }

    public RatingView(Context r2_Context, AttributeSet r3_AttributeSet) {
        this(r2_Context, r3_AttributeSet, 0);
    }

    public RatingView(Context r2_Context, AttributeSet r3_AttributeSet, int r4i) {
        super(r2_Context, r3_AttributeSet, r4i);
        this.b = new ImageView[5];
        a(r2_Context);
    }

    private void a(Context r5_Context) {
        DebugUtil.debug(a, "initView");
        View r1_View = LayoutInflater.from(r5_Context).inflate(R.layout.rating, this);
        this.b[0] = (ImageView) r1_View.findViewById(R.id.rating_1);
        this.b[1] = (ImageView) r1_View.findViewById(R.id.rating_2);
        this.b[2] = (ImageView) r1_View.findViewById(R.id.rating_3);
        this.b[3] = (ImageView) r1_View.findViewById(R.id.rating_4);
        this.b[4] = (ImageView) r1_View.findViewById(R.id.rating_5);
    }

    public void setRating(int r7i) {
        if (r7i > 10) {
            r7i = 10;
        }
        int r0i = 0;
        while (r0i < 5) {
            DebugUtil.debug(a, "setRating i:" + r0i + " " + this.b[r0i]);
            this.b[r0i].getDrawable().setLevel(XListViewFooter.STATE_NOMORE);
            r0i++;
        }
        r0i = 0;
        while (r7i > 1) {
            this.b[r0i].getDrawable().setLevel(1);
            r0i++;
            r7i -= 2;
            if (r7i <= 0) {
                return;
            }
        }
        if (r7i == 1) {
            this.b[r0i].getDrawable().setLevel(XListViewHeader.STATE_REFRESHING);
        }
    }
}