package com.baidu.mobads;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.baidu.mobads.a.d;
import com.tencent.mm.sdk.contact.RContactStorage;

public final class InterstitialAd {
    private AdView a;
    private boolean b;
    private Context c;
    private AdSize d;
    private String e;
    private InterstitialAdListener f;

    public InterstitialAd(Context r3_Context) {
        this(r3_Context, AdSize.InterstitialGame, RContactStorage.PRIMARY_KEY);
    }

    public InterstitialAd(Context r3_Context, AdSize r4_AdSize, String r5_String) {
        this.b = false;
        this.f = new c(this);
        this.c = r3_Context;
        if (r4_AdSize.getValue() < 6 || r4_AdSize.getValue() > 10) {
            d.b("Please use the right AdSize when new InterstitialAd");
            this.d = AdSize.InterstitialGame;
        } else {
            this.d = r4_AdSize;
        }
        this.e = r5_String;
    }

    public static void setAppSec(Context r0_Context, String r1_String) {
        AdView.setAppSec(r0_Context, r1_String);
    }

    public static void setAppSid(Context r0_Context, String r1_String) {
        AdView.setAppSid(r0_Context, r1_String);
    }

    protected AdView getAdView() {
        return this.a;
    }

    public boolean isAdReady() {
        return this.b;
    }

    public void loadAd() {
        try {
            this.b = false;
            if (this.a != null) {
                this.a.prepareForInterstitial(false);
            } else {
                this.a = new AdView(this.c, false, this.d, this.e);
                this.a.setInterstialListener(this.f, this);
            }
        } catch (Exception e) {
            d.b("Interstitial.loadAd", e);
        }
    }

    protected void removeAd() {
        try {
            ViewGroup r0_ViewGroup = (ViewGroup) this.a.getParent();
            r0_ViewGroup.removeView(this.a);
            ((ViewGroup) ((Activity) r0_ViewGroup.getContext()).getWindow().getDecorView()).removeView(r0_ViewGroup);
        } catch (Exception e) {
            d.b("Interstitial.removeAd", e);
        }
    }

    protected void setAdReady(boolean r1z) {
        this.b = r1z;
    }

    public void setListener(InterstitialAdListener r2_InterstitialAdListener) {
        if (r2_InterstitialAdListener == null) {
            throw new IllegalArgumentException();
        } else {
            this.f = r2_InterstitialAdListener;
        }
    }

    public void showAd(Activity r7_Activity) {
        try {
            if (this.a == null || (!this.b)) {
                Object[] r0_ObjectA = new Object[4];
                r0_ObjectA[0] = "InterstitialAd.showAd,but value not ready:adView=";
                r0_ObjectA[1] = this.a;
                r0_ObjectA[2] = ";isAdReady=";
                r0_ObjectA[3] = Boolean.valueOf(this.b);
                d.a(r0_ObjectA);
            } else {
                ViewGroup r0_ViewGroup = (ViewGroup) r7_Activity.getWindow().getDecorView();
                if (this.a.getParent() != null) {
                    int r2i = 0;
                    while (r2i < r0_ViewGroup.getChildCount()) {
                        if (r0_ViewGroup.getChildAt(r2i) == this.a.getParent()) {
                            r0_ViewGroup.removeView((View) this.a.getParent());
                        }
                        r2i++;
                    }
                }
                Rect r1_Rect = new Rect();
                r7_Activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r1_Rect);
                int r1i = r1_Rect.top;
                this.a.prepareForInterstitial(true);
                View r2_View = new RelativeLayout(r7_Activity);
                r2_View.setBackgroundColor(0);
                LayoutParams r3_LayoutParams = new RelativeLayout.LayoutParams(-1, -1);
                r3_LayoutParams.topMargin = r1i;
                r2_View.addView(this.a, r3_LayoutParams);
                r0_ViewGroup.addView(r2_View, new RelativeLayout.LayoutParams(-1, -1));
                this.b = false;
            }
        } catch (Exception e) {
            d.b("Interstitial.showAd", e);
        }
    }
}