package com.flurry.android;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.tencent.tauth.Constants;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: SourceFile
final class ae extends LinearLayout {
    public ae(CatalogActivity r7_CatalogActivity, Context r8_Context) {
        super(r8_Context);
        setBackgroundColor(-1);
        AdImage r0_AdImage = r7_CatalogActivity.e.m();
        if (r0_AdImage != null) {
            View r1_View = new ImageView(r8_Context);
            r1_View.setId(10000);
            byte[] r2_byteA = r0_AdImage.e;
            if (r2_byteA != null) {
                r1_View.setImageBitmap(BitmapFactory.decodeByteArray(r2_byteA, 0, r2_byteA.length));
            }
            ad.a(r8_Context, r1_View, ad.a(r8_Context, r0_AdImage.b), ad.a(r8_Context, r0_AdImage.c));
            LayoutParams r0_LayoutParams = new LinearLayout.LayoutParams(-2, -2);
            r0_LayoutParams.setMargins(0, 0, 0, Constants.ERROR_URL);
            setGravity(XListViewFooter.STATE_NOMORE);
            addView(r1_View, r0_LayoutParams);
        }
    }
}