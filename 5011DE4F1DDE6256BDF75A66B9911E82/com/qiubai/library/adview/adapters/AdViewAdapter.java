package com.qiubai.library.adview.adapters;

import com.qiubai.library.adview.AdViewAdRegistry;
import com.qiubai.library.adview.AdViewLayout;
import com.qiubai.library.adview.obj.Ration;
import com.qiubai.library.adview.util.AdViewUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import qsbk.app.message.api.ChatEngine;
import qsbk.app.widget.listview.XListViewHeader;

public abstract class AdViewAdapter {
    public static int adffailCount;
    public static int adfreqCount;
    public static int adfsucCount;
    static AdViewAdapter c;
    static AdViewAdapter d;
    public static int failCount;
    public static int reqCount;
    public static int sucCount;
    protected WeakReference<AdViewLayout> a;
    protected Ration b;
    private Timer e;

    static {
        reqCount = 0;
        sucCount = 0;
        failCount = 0;
        adfreqCount = 0;
        adfsucCount = 0;
        adffailCount = 0;
    }

    public AdViewAdapter(AdViewLayout r1_AdViewLayout, Ration r2_Ration) {
        setParamters(r1_AdViewLayout, r2_Ration);
    }

    private static AdViewAdapter a(AdViewLayout r2_AdViewLayout, Ration r3_Ration) {
        Class r0_Class = AdViewAdRegistry.getInstance().adapterClassForAdType(Integer.valueOf(r3_Ration.type));
        return r0_Class != null ? a(r0_Class, r2_AdViewLayout, r3_Ration) : b(r2_AdViewLayout, r3_Ration);
    }

    private static AdViewAdapter a(Class<? extends AdViewAdapter> r3_Class__extends_AdViewAdapter, AdViewLayout r4_AdViewLayout, Ration r5_Ration) {
        AdViewAdapter r0_AdViewAdapter;
        try {
            r0_AdViewAdapter = (AdViewAdapter) r3_Class__extends_AdViewAdapter.getConstructor(new Class[0]).newInstance(new Object[0]);
            r0_AdViewAdapter.setParamters(r4_AdViewLayout, r5_Ration);
            r0_AdViewAdapter.initAdapter(r4_AdViewLayout, r5_Ration);
            return r0_AdViewAdapter;
        } catch (SecurityException e) {
            return null;
        } catch (NoSuchMethodException e_2) {
            return null;
        } catch (InvocationTargetException e_3) {
            return null;
        } catch (IllegalAccessException e_4) {
            return null;
        } catch (InstantiationException e_5) {
            return null;
        }
    }

    private static AdViewAdapter b(AdViewLayout r2_AdViewLayout, Ration r3_Ration) {
        AdViewUtil.logInfo(new StringBuilder("Unsupported ration type: ").append(r3_Ration.type).toString());
        return null;
    }

    public static void handleOne(AdViewLayout r1_AdViewLayout, Ration r2_Ration) {
        if (c != null) {
            d = c;
            if (d != null) {
                d.clean();
                d = null;
            }
        }
        c = a(r1_AdViewLayout, r2_Ration);
        if (c != null) {
            AdViewUtil.logInfo("Valid adapter, calling handle()");
            c.handle();
        } else {
            r1_AdViewLayout.adViewManager.resetRollover();
            r1_AdViewLayout.rotateThreadedPri(0);
        }
    }

    public static void onClickAd(int r2i) throws Throwable {
        if (c != null) {
            c.click(r2i);
        } else {
            throw new Exception("On Click failed");
        }
    }

    public static void onRelease() {
    }

    public void clean() {
    }

    public void click(int r1i) {
    }

    public abstract void handle();

    public abstract void initAdapter(AdViewLayout r1_AdViewLayout, Ration r2_Ration);

    public void onFailed(AdViewLayout r4_AdViewLayout, Ration r5_Ration) {
        r4_AdViewLayout.listStatistics(r5_Ration.name, XListViewHeader.STATE_REFRESHING, 1);
        r4_AdViewLayout.rotateThreadedPri(1);
    }

    public void onSuccessed(AdViewLayout r1_AdViewLayout, Ration r2_Ration) {
    }

    public void requestTimeOut() {
    }

    public void setParamters(AdViewLayout r2_AdViewLayout, Ration r3_Ration) {
        this.a = new WeakReference(r2_AdViewLayout);
        this.b = r3_Ration;
    }

    public void shoutdownTimer() {
        if (this.e != null) {
            this.e.cancel();
            this.e = null;
        }
    }

    public void startTimer() {
        if (this.e != null) {
            this.e.cancel();
            this.e = null;
        }
        this.e = new Timer();
        this.e.schedule(new a(this), ChatEngine.mQueryListInterval);
    }
}