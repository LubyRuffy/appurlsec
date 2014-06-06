package com.flurry.android;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.view.ViewCompat;
import android.text.Html;
import android.text.SpannedString;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.android.pushservice.PushConstants;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
final class c extends RelativeLayout {
    private final SpannedString a;
    private final SpannedString b;

    public c(CatalogActivity r8_CatalogActivity, Context r9_Context) {
        super(r9_Context);
        this.a = new SpannedString(Html.fromHtml("<html><div='style:font-size:7px'>&lt; Previous</div></html>"));
        this.b = new SpannedString(Html.fromHtml("<html><div='style:font-size:7px;color:#ffA500'>&lt; Previous</div></html>"));
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        View r0_View = new TextView(r9_Context);
        r0_View.setTextColor(ColorStateList.valueOf(-1));
        r0_View.setId(PushConstants.ERROR_NETWORK_ERROR);
        r0_View.setText(this.a);
        r0_View.setPadding(ShareUtils.SHARE_SMS, XListViewHeader.STATE_REFRESHING, ShareUtils.SHARE_SMS, XListViewHeader.STATE_REFRESHING);
        r0_View.setFocusable(true);
        r0_View.setOnFocusChangeListener(new d(this, r0_View));
        r0_View.setOnClickListener(r8_CatalogActivity);
        r0_View.setEnabled(true);
        LayoutParams r1_LayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        r1_LayoutParams.setMargins(0, 0, 0, 0);
        setLayoutParams(r1_LayoutParams);
        r1_LayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        r1_LayoutParams.setMargins(XListViewHeader.STATE_REFRESHING, 0, 0, 0);
        r1_LayoutParams.addRule(XListViewFooter.STATE_NODATA);
        addView(r0_View, r1_LayoutParams);
    }
}