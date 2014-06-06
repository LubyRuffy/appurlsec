package qsbk.app.gdtad;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.androidquery.AQuery;
import com.qq.e.feedsad.CustomFeedsViewInterface;
import com.qq.e.v2.util.GDTLogger;
import qsbk.app.R;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class AdView extends RelativeLayout implements CustomFeedsViewInterface {
    private String a;

    public AdView(Context r1_Context) {
        super(r1_Context);
    }

    public AdView(Context r1_Context, AttributeSet r2_AttributeSet) {
        super(r1_Context, r2_AttributeSet);
    }

    public AdView(Context r1_Context, AttributeSet r2_AttributeSet, int r3i) {
        super(r1_Context, r2_AttributeSet, r3i);
    }

    public void bindPkg(String r1_String) {
        this.a = r1_String;
    }

    public void updateAppStatus(String r3_String, int r4i) {
        GDTLogger.d("Status Updating" + r3_String + "\t" + r4i);
        if (r3_String == null || (!r3_String.equals(this.a))) {
        } else {
            updateButtonText(r4i);
        }
    }

    public void updateBackGround(int r1i) {
        setBackgroundColor(r1i);
    }

    public void updateButtonText(int r3i) {
        int r1i = R.id.downbt;
        AQuery r0_AQuery = new AQuery((View)this);
        switch (r3i) {
            case XListViewHeader.STATE_READY:
                ((AQuery) r0_AQuery.id(r1i)).text("\u70b9\u51fb\u542f\u52a8");
                return;
            case XListViewFooter.STATE_NODATA:
                ((AQuery) r0_AQuery.id(r1i)).text("\u4e0b\u8f7d\u4e2d");
                return;
            case Base64.DONT_BREAK_LINES:
                ((AQuery) r0_AQuery.id(r1i)).text("\u70b9\u51fb\u5b89\u88c5");
                return;
            case Base64.URL_SAFE:
                ((AQuery) r0_AQuery.id(r1i)).text("\u70b9\u51fb\u91cd\u8bd5");
                return;
        }
        ((AQuery) r0_AQuery.id(r1i)).text("\u4e0b\u8f7d");
    }

    public void updateStyle(String r3_String) {
        if (r3_String == null || (!r3_String.contains("0d6"))) {
        } else {
            new AQuery((View)this).textColor(Color.parseColor("#464652"));
        }
    }
}