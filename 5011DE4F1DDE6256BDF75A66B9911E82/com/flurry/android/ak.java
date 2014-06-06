package com.flurry.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.MaskFilter;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.utils.audit.RequestListener;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: SourceFile
final class ak extends RelativeLayout {
    private ag a;
    private ab b;
    private String c;
    private int d;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ak(Context r5_Context, ag r6_ag, ab r7_ab, q r8_q, int r9i, boolean r10z) {
        /*
        r4_this = this;
        r3 = 0;
        r2 = 1;
        r4.<init>(r5);
        r4.a = r6;
        r4.b = r7;
        r0 = r7.b;
        r4.d = r9;
        r1 = r4.d;
        switch(r1) {
            case 1: goto L_0x001b;
            case 2: goto L_0x0016;
            default: goto L_0x0012;
        };
    L_0x0012:
        r4.setFocusable(r2);
        return;
    L_0x0016:
        if (r10 == 0) goto L_0x0021;
    L_0x0018:
        r4.a(r5, r8, r0, r3);
    L_0x001b:
        if (r10 == 0) goto L_0x0025;
    L_0x001d:
        r4.a(r5, r8, r0, r3);
        goto L_0x0012;
    L_0x0021:
        r4.a(r5, r8, r0, r2);
        goto L_0x001b;
    L_0x0025:
        r4.a(r5, r8, r0, r2);
        goto L_0x0012;
        */

    }

    private void a(Context r15_Context, q r16_q, ah r17_ah, boolean r18z) {
        Bitmap r2_Bitmap;
        setLayoutParams(new LayoutParams(-1, -1));
        o r3_o = r16_q.d;
        View r4_View = new ImageView(r15_Context);
        r4_View.setId(1);
        AdImage r1_AdImage = r17_ah.h;
        if (r1_AdImage != null) {
            Object r1_Object = r1_AdImage.e;
            Bitmap r5_Bitmap = BitmapFactory.decodeByteArray(r1_Object, 0, r1_Object.length);
            if (r5_Bitmap == null) {
                i.a("FlurryAgent", "Ad with bad image: " + r17_ah.d + ", data: " + r1_Object);
            }
            if (r5_Bitmap != null) {
                Bitmap r1_Bitmap;
                r2_Bitmap = Bitmap.createBitmap(r5_Bitmap.getWidth(), r5_Bitmap.getHeight(), Config.ARGB_8888);
                Canvas r1_Canvas = new Canvas(r2_Bitmap);
                Paint r6_Paint = new Paint();
                Rect r7_Rect = new Rect(0, 0, r5_Bitmap.getWidth(), r5_Bitmap.getHeight());
                RectF r8_RectF = new RectF(r7_Rect);
                float r9f = (float) ad.a(r15_Context, (int)Base64.DONT_BREAK_LINES);
                r6_Paint.setAntiAlias(true);
                r1_Canvas.drawARGB(0, 0, 0, 0);
                r6_Paint.setColor(ViewCompat.MEASURED_STATE_MASK);
                r1_Canvas.drawRoundRect(r8_RectF, r9f, r9f, r6_Paint);
                r6_Paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
                r1_Canvas.drawBitmap(r5_Bitmap, r7_Rect, r7_Rect, r6_Paint);
                if (Integer.parseInt(VERSION.SDK) > 4) {
                    MaskFilter r1_MaskFilter = new BlurMaskFilter(3.0f, Blur.OUTER);
                    Paint r5_Paint = new Paint();
                    r5_Paint.setMaskFilter(r1_MaskFilter);
                    int[] r6_intA = new int[2];
                    r1_Bitmap = r2_Bitmap.extractAlpha(r5_Paint, r6_intA).copy(Config.ARGB_8888, true);
                    new Canvas(r1_Bitmap).drawBitmap(r2_Bitmap, (float) (-r6_intA[0]), (float) (-r6_intA[1]), null);
                } else {
                    r1_Bitmap = r2_Bitmap;
                }
                r4_View.setImageBitmap(r1_Bitmap);
                ad.a(r15_Context, r4_View, ad.a(r15_Context, r3_o.m), ad.a(r15_Context, r3_o.n));
                r4_View.setScaleType(ScaleType.FIT_XY);
            }
        }
        r1_AdImage = this.a.a(r3_o.c);
        if (r1_AdImage != null) {
            byte[] r1_byteA = r1_AdImage.e;
            r2_Bitmap = BitmapFactory.decodeByteArray(r1_byteA, 0, r1_byteA.length);
            setBackgroundDrawable(NinePatch.isNinePatchChunk(r2_Bitmap.getNinePatchChunk()) ? new NinePatchDrawable(r2_Bitmap, r2_Bitmap.getNinePatchChunk(), new Rect(0, 0, 0, 0), null) : new BitmapDrawable(r2_Bitmap));
        }
        View r1_View = new TextView(r15_Context);
        r1_View.setId(ShareUtils.SHARE_SMS);
        r1_View.setPadding(0, 0, 0, 0);
        View r2_View = new TextView(r15_Context);
        r2_View.setId(XListViewFooter.STATE_NOMORE);
        r2_View.setPadding(0, 0, 0, 0);
        if (r18z) {
            r1_View.setTextColor(r3_o.f);
            r1_View.setTextSize((float) r3_o.e);
            r1_View.setText(new String("\u2022 " + r3_o.b));
            r1_View.setTypeface(Typeface.create(r3_o.d, 0));
            r2_View.setTextColor(r3_o.i);
            r2_View.setTextSize((float) r3_o.h);
            r2_View.setTypeface(Typeface.create(r3_o.g, 0));
            r2_View.setText(r17_ah.d);
        } else {
            r1_View.setId(XListViewFooter.STATE_NOMORE);
            r1_View.setText(r17_ah.d);
            r1_View.setTextColor(r3_o.i);
            r1_View.setTextSize((float) r3_o.h);
            r1_View.setTypeface(Typeface.create(r3_o.g, 0));
            r2_View.setId(XListViewFooter.STATE_NODATA);
            r2_View.setText(r17_ah.c);
            r2_View.setTextColor(r3_o.l);
            r2_View.setTextSize((float) r3_o.k);
            r2_View.setTypeface(Typeface.create(r3_o.j, 0));
        }
        setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        addView(new ImageView(r15_Context), new LayoutParams(-1, -2));
        ViewGroup.LayoutParams r6_ViewGroup_LayoutParams = new LayoutParams(-2, -2);
        r6_ViewGroup_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY);
        r6_ViewGroup_LayoutParams.setMargins(r3_o.o, r3_o.p, r3_o.q - r3_o.o << 1 - r3_o.m, 0);
        addView(r4_View, r6_ViewGroup_LayoutParams);
        ViewGroup.LayoutParams r3_ViewGroup_LayoutParams = new LayoutParams(-2, -2);
        r3_ViewGroup_LayoutParams.addRule(ShareUtils.SHARE_COPY, r4_View.getId());
        r3_ViewGroup_LayoutParams.addRule(1, r4_View.getId());
        r3_ViewGroup_LayoutParams.setMargins(0, 0, 0, 0);
        addView(r1_View, r3_ViewGroup_LayoutParams);
        r3_ViewGroup_LayoutParams = new LayoutParams(-2, -2);
        r3_ViewGroup_LayoutParams.addRule(1, r4_View.getId());
        r3_ViewGroup_LayoutParams.addRule(XListViewFooter.STATE_NOMORE, r1_View.getId());
        r3_ViewGroup_LayoutParams.setMargins(0, RequestListener.DEFAULT_LOADED_SIZE, 0, 0);
        addView(r2_View, r3_ViewGroup_LayoutParams);
    }

    final ab a() {
        return this.b;
    }

    final void a(String r1_String) {
        this.c = r1_String;
    }

    final String b(String r4_String) {
        return r4_String + this.c + System.currentTimeMillis();
    }
}