package com.qq.e.ads;

import android.app.Activity;
import android.content.Context;
import com.qq.e.comm.a;
import com.qq.e.v2.managers.GDTADManager;
import com.qq.e.v2.plugininterfaces.InterstitialAdViewInterface;
import com.qq.e.v2.util.GDTLogger;
import com.qq.e.v2.util.StringUtil;

public class InterstitialAd {
    private InterstitialAdViewInterface a;

    public InterstitialAd(Activity r4_Activity, String r5_String, String r6_String) {
        if (a.a((Context)r4_Activity)) {
            if (StringUtil.isEmpty(r5_String) || StringUtil.isEmpty(r6_String) || r4_Activity == null) {
                Object[] r1_ObjectA = new Object[3];
                r1_ObjectA[0] = r5_String;
                r1_ObjectA[1] = r6_String;
                r1_ObjectA[2] = r4_Activity;
                GDTLogger.e(String.format("Intersitial Contructor paras error,appid=%s,posId=%s,context=%s", r1_ObjectA));
            } else if (GDTADManager.getInstance().initWith(r4_Activity, r5_String)) {
                try {
                    this.a = GDTADManager.getInstance().getPM().getInterstitialViewFactory().getInterstitialView(r4_Activity, r5_String, r6_String);
                } catch (com.qq.e.v2.managers.plugin.a e) {
                    GDTLogger.report("Fail to init Intersitial plugin", e);
                } catch (Throwable th) {
                    GDTLogger.report("Fail to init Intersitial Instance", th);
                }
            } else {
                GDTLogger.report("Fail to init GDTADManager");
            }
        } else {
            GDTLogger.e("Fail to init AdView, please check that all items are added correctly in AndroidManifest.xml");
        }
    }

    public void closePopupWindow() {
        if (this.a != null) {
            this.a.closePopupWindow();
        }
    }

    public void loadAd() {
        if (this.a != null) {
            this.a.loadAd();
        }
    }

    public void setAdListener(InterstitialAdListener r2_InterstitialAdListener) {
        if (this.a != null) {
            this.a.setAdListener(r2_InterstitialAdListener);
        }
    }

    public synchronized void show() {
        if (this.a != null) {
            this.a.show();
        }
    }

    public synchronized void show(Activity r2_Activity) {
        if (this.a != null) {
            this.a.show(r2_Activity);
        }
    }

    public synchronized void showAsPopupWindown() {
        if (this.a != null) {
            this.a.showAsPopupWindown();
        }
    }

    public synchronized void showAsPopupWindown(Activity r2_Activity) {
        if (this.a != null) {
            this.a.showAsPopupWindown(r2_Activity);
        }
    }
}