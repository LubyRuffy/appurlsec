package com.qq.e.ads;

import android.app.Activity;
import android.content.Context;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.qq.e.comm.a;
import com.qq.e.v2.managers.GDTADManager;
import com.qq.e.v2.plugininterfaces.BannerViewInterface;
import com.qq.e.v2.util.GDTLogger;
import com.qq.e.v2.util.StringUtil;

public class AdView extends FrameLayout {
    private BannerViewInterface a;

    public AdView(Activity r7_Activity, AdSize r8_AdSize, String r9_String, String r10_String) {
        super(r7_Activity);
        if (a.a((Context)r7_Activity)) {
            if (StringUtil.isEmpty(r9_String) || StringUtil.isEmpty(r10_String) || r7_Activity == null) {
                Object[] r1_ObjectA = new Object[3];
                r1_ObjectA[0] = r9_String;
                r1_ObjectA[1] = r10_String;
                r1_ObjectA[2] = r7_Activity;
                GDTLogger.e(String.format("Banner ADView Contructor paras error,appid=%s,posId=%s,context=%s", r1_ObjectA));
            } else {
                setLayoutParams(new LayoutParams(-1, -2));
                if (GDTADManager.getInstance().initWith(r7_Activity, r9_String)) {
                    try {
                        this.a = GDTADManager.getInstance().getPM().getBannerViewFactory().getBannerView(r7_Activity, r8_AdSize, r9_String, r10_String, true);
                        addView(this.a.getView());
                    } catch (com.qq.e.v2.managers.plugin.a e) {
                        GDTLogger.report("Fail to init Banner plugin", e);
                    } catch (Throwable th) {
                        GDTLogger.report("Fail to init Banner Instance", th);
                    }
                } else {
                    GDTLogger.report("Fail to init GDTADManager");
                }
            }
        } else {
            GDTLogger.e("Fail to init AdView, please check that all items are added correctly in AndroidManifest.xml");
        }
    }

    public void destroy() {
        if (this.a != null) {
            this.a.destroy();
        }
    }

    public void fetchAd(AdRequest r2_AdRequest) {
        if (this.a != null) {
            this.a.fetchAd(r2_AdRequest);
        }
    }

    public void setAdListener(AdListener r2_AdListener) {
        if (this.a != null) {
            this.a.setAdListener(r2_AdListener);
        }
    }
}