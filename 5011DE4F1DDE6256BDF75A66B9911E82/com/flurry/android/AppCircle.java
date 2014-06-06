package com.flurry.android;

import android.content.Context;
import android.view.View;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.List;

// compiled from: SourceFile
public class AppCircle {
    public void acceptOffer(Context r1_Context, long r2j) {
        FlurryAgent.a(r1_Context, r2j);
    }

    public void addUserCookie(String r1_String, String r2_String) {
        FlurryAgent.addUserCookie(r1_String, r2_String);
    }

    public void clearUserCookies() {
        FlurryAgent.clearUserCookies();
    }

    public List getAllOffers() {
        return FlurryAgent.b(RContactStorage.PRIMARY_KEY);
    }

    public List getAllOffers(String r2_String) {
        return FlurryAgent.b(r2_String);
    }

    public View getHook(Context r2_Context, String r3_String, int r4i) {
        return FlurryAgent.a(r2_Context, r3_String, r4i);
    }

    public Offer getOffer() {
        return getOffer(RContactStorage.PRIMARY_KEY);
    }

    public Offer getOffer(String r2_String) {
        return FlurryAgent.a(r2_String);
    }

    public boolean hasAds() {
        return FlurryAgent.e();
    }

    public boolean isLaunchCanvasOnBannerClicked() {
        return FlurryAgent.a();
    }

    public boolean isLaunchCatalogOnBannerClicked() {
        return FlurryAgent.a();
    }

    public void launchCanvasOnBannerClicked(boolean r1z) {
        FlurryAgent.a(r1z);
    }

    public void launchCatalogOnBannerClicked(boolean r1z) {
        FlurryAgent.a(r1z);
    }

    public void openCatalog(Context r2_Context) {
        openCatalog(r2_Context, RContactStorage.PRIMARY_KEY);
    }

    public void openCatalog(Context r1_Context, String r2_String) {
        FlurryAgent.a(r1_Context, r2_String);
    }

    public void removeOffers(List r1_List) {
        FlurryAgent.a(r1_List);
    }

    public void setAppCircleCallback(AppCircleCallback r1_AppCircleCallback) {
        FlurryAgent.a(r1_AppCircleCallback);
    }

    public void setDefaultNoAdsMessage(String r1_String) {
        FlurryAgent.setDefaultNoAdsMessage(r1_String);
    }
}