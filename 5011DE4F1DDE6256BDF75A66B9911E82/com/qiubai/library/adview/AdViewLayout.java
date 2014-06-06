package com.qiubai.library.adview;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.widget.ExploreByTouchHelper;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.qiubai.library.adview.AdViewTargeting.SwitcherMode;
import com.qiubai.library.adview.adapters.AdViewAdapter;
import com.qiubai.library.adview.obj.Extra;
import com.qiubai.library.adview.obj.Ration;
import com.qiubai.library.adview.statistics.StatisticsBean;
import com.qiubai.library.adview.statistics.StatisticsInterface;
import com.qiubai.library.adview.util.AdViewReqManager;
import com.qiubai.library.adview.util.AdViewUtil;
import com.qiubai.library.adview.util.MD5;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.cordova.NetworkManager;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.bean.Base;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.audit.RequestListener;
import qsbk.app.widget.listview.XListViewFooter;

public class AdViewLayout extends RelativeLayout {
    public static final int CLICK = 0;
    public static final int FAIL = 2;
    public static final int IMPRESSION = 1;
    public static String appName;
    public static String appPackage;
    public static int appVersion;
    public static boolean isTest;
    public static boolean isadFill;
    public static ScheduledExecutorService scheduler;
    private int a;
    public Ration activeRation;
    public final WeakReference<Activity> activityReference;
    public String adKey;
    public AdViewInterface adViewInterface;
    public AdViewManager adViewManager;
    private int b;
    private boolean c;
    private boolean d;
    public String deviceId;
    public String deviceModel;
    private boolean e;
    public Extra extra;
    private boolean f;
    private int g;
    private int h;
    public final Handler handler;
    public boolean isTerminated;
    public double mDensity;
    public String netType;
    public Ration nextRation;
    public String osVer;
    public String platform;
    public String resolution;
    public String simType;
    public StatisticsInterface statisticsInterface;
    public WeakReference<RelativeLayout> superViewReference;
    public RelativeLayout umengView;

    public static class ViewAdRunnable implements Runnable {
        private WeakReference<AdViewLayout> a;
        private ViewGroup b;

        public ViewAdRunnable(AdViewLayout r2_AdViewLayout, ViewGroup r3_ViewGroup) {
            this.a = new WeakReference(r2_AdViewLayout);
            this.b = r3_ViewGroup;
        }

        public void run() {
            AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
            if (r0_AdViewLayout != null) {
                r0_AdViewLayout.pushSubView(this.b);
            }
        }
    }

    private static class a implements Runnable {
        private WeakReference<AdViewLayout> a;

        public a(AdViewLayout r2_AdViewLayout) {
            this.a = new WeakReference(r2_AdViewLayout);
        }

        public void run() {
            AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
            if (r0_AdViewLayout == null || (!r0_AdViewLayout.e)) {
            } else {
                if (r0_AdViewLayout.adViewManager != null) {
                    r0_AdViewLayout.adViewManager.fetchConfigFromServer(r0_AdViewLayout);
                }
                r0_AdViewLayout.fetchConfigThreadedDelayed(r0_AdViewLayout.adViewManager.getConfigExpiereTimeout());
            }
        }
    }

    private static class b implements Runnable {
        private WeakReference<AdViewLayout> a;

        public b(AdViewLayout r2_AdViewLayout) {
            this.a = new WeakReference(r2_AdViewLayout);
        }

        public void run() {
            AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
            if (r0_AdViewLayout != null) {
                r0_AdViewLayout.c();
            }
        }
    }

    private class c implements Runnable {
        private WeakReference<AdViewLayout> b;
        private String c;

        public c(AdViewLayout r3_AdViewLayout, String r4_String) {
            this.b = new WeakReference(r3_AdViewLayout);
            this.c = r4_String;
        }

        public void run() {
            if (AdViewLayout.this.c) {
            } else {
                AdViewLayout r0_AdViewLayout = (AdViewLayout) this.b.get();
                if (r0_AdViewLayout != null) {
                    Activity r1_Activity = (Activity) r0_AdViewLayout.activityReference.get();
                    if (r1_Activity != null) {
                        if (r0_AdViewLayout.adViewManager == null) {
                            r0_AdViewLayout.adViewManager = new AdViewManager(new WeakReference(r1_Activity.getApplicationContext()), this.c);
                        }
                        if (r0_AdViewLayout.e) {
                            r0_AdViewLayout.adViewManager.fetchConfig(r0_AdViewLayout);
                            r0_AdViewLayout.extra = r0_AdViewLayout.adViewManager.getExtra();
                            if (r0_AdViewLayout.extra == null) {
                                scheduler.schedule(this, 30, TimeUnit.SECONDS);
                            } else {
                                if (AdViewLayout.this.adViewManager == null || (!AdViewLayout.this.adViewManager.needUpdateConfig())) {
                                    r0_AdViewLayout.fetchConfigThreadedDelayed(r0_AdViewLayout.adViewManager.getConfigExpiereTimeout());
                                    r0_AdViewLayout.appReport();
                                } else {
                                    r0_AdViewLayout.fetchConfigThreadedDelayed(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO);
                                    r0_AdViewLayout.appReport();
                                }
                                if (!isTest) {
                                    r0_AdViewLayout.rotateAd();
                                }
                            }
                        } else {
                            r0_AdViewLayout.f = false;
                        }
                    }
                }
            }
        }
    }

    private static class d implements Runnable {
        private WeakReference<AdViewLayout> a;

        public d(AdViewLayout r2_AdViewLayout) {
            this.a = new WeakReference(r2_AdViewLayout);
        }

        public void run() {
            AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
            if (r0_AdViewLayout != null) {
                r0_AdViewLayout.rotateAd();
            }
        }
    }

    private static class e implements Runnable {
        private WeakReference<AdViewLayout> a;

        public e(AdViewLayout r2_AdViewLayout) {
            this.a = new WeakReference(r2_AdViewLayout);
        }

        public void run() {
            AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
            if (r0_AdViewLayout != null) {
                r0_AdViewLayout.rotatePriAd();
            }
        }
    }

    static {
        appPackage = RContactStorage.PRIMARY_KEY;
        isadFill = false;
    }

    public AdViewLayout(Activity r7_Activity, String r8_String) {
        super(r7_Activity);
        this.deviceId = new String("000000000000000");
        this.deviceModel = new String("SDK");
        this.osVer = new String("2.2");
        this.resolution = new String("320*480");
        this.simType = new String("46000");
        this.netType = new String("2G/3G");
        this.platform = new String("android");
        this.a = 96;
        this.b = 70;
        this.umengView = null;
        this.c = false;
        this.d = false;
        this.mDensity = 0.0d;
        this.activityReference = new WeakReference(r7_Activity);
        this.superViewReference = new WeakReference(this);
        this.adKey = r8_String;
        this.e = true;
        this.isTerminated = false;
        this.handler = new Handler();
        scheduler = Executors.newScheduledThreadPool(IMPRESSION);
        this.f = true;
        scheduler.schedule(new c(this, r8_String), 0, TimeUnit.SECONDS);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        a((Context)r7_Activity);
        this.g = 0;
        this.h = 0;
    }

    private int a(MotionEvent r4_MotionEvent) {
        float r0f = r4_MotionEvent.getX();
        float r1f = r4_MotionEvent.getY();
        return ((r0f > ((float) (getWidth() / 16)) ? 1 : (r0f == ((float) (getWidth() / 16))? 0 : -1)) < 0 || r0f > ((float) ((getWidth() * 15) / 16)) || r1f < ((float) (getHeight() / 6)) || r1f > ((float) ((getHeight() * 5) / 6))) ? IMPRESSION : CLICK;
    }

    private void a() {
        removeAllViews();
        this.isTerminated = true;
    }

    private void a(int r6i) {
        if (r6i == 0) {
            AdViewReqManager.getInstance(getContext()).loadPendingReqInfos(getContext());
            this.e = true;
            if (!this.f) {
                this.f = true;
                if (this.extra != null) {
                    rotateThreadedPri(CLICK);
                    if (this.adViewManager == null || (!this.adViewManager.needUpdateConfig())) {
                    } else {
                        fetchConfigThreadedDelayed(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO);
                    }
                } else {
                    scheduler.schedule(new c(this, this.adKey), 0, TimeUnit.SECONDS);
                }
            }
        } else {
            if (r6i == 8) {
                AdViewReqManager.getInstance(getContext()).savePendingReqInfos(getContext());
                this.e = false;
            }
        }
    }

    private void a(Context r6_Context) {
        if (r6_Context == null) {
        } else {
            TelephonyManager r0_TelephonyManager = (TelephonyManager) r6_Context.getSystemService("phone");
            if (r0_TelephonyManager != null) {
                String r0_String;
                NetworkInfo r0_NetworkInfo;
                String r1_String = r0_TelephonyManager.getDeviceId();
                DisplayMetrics r2_DisplayMetrics;
                if (r1_String == null || r1_String.length() <= 0) {
                    this.deviceModel = new String(Build.MODEL);
                    this.deviceModel = this.deviceModel.replaceAll(" ", RContactStorage.PRIMARY_KEY);
                    this.osVer = new String(VERSION.RELEASE);
                    this.osVer = this.osVer.replaceAll(" ", RContactStorage.PRIMARY_KEY);
                    r2_DisplayMetrics = new DisplayMetrics();
                    ((Activity) this.activityReference.get()).getWindowManager().getDefaultDisplay().getMetrics(r2_DisplayMetrics);
                    this.mDensity = (double) r2_DisplayMetrics.density;
                    this.resolution = new String(new StringBuilder(String.valueOf(Integer.toString(r2_DisplayMetrics.widthPixels))).append("*").append(Integer.toString(r2_DisplayMetrics.heightPixels)).toString());
                } else {
                    this.deviceId = new String(r1_String);
                    this.deviceModel = new String(Build.MODEL);
                    this.deviceModel = this.deviceModel.replaceAll(" ", RContactStorage.PRIMARY_KEY);
                    this.osVer = new String(VERSION.RELEASE);
                    this.osVer = this.osVer.replaceAll(" ", RContactStorage.PRIMARY_KEY);
                    r2_DisplayMetrics = new DisplayMetrics();
                    ((Activity) this.activityReference.get()).getWindowManager().getDefaultDisplay().getMetrics(r2_DisplayMetrics);
                    this.mDensity = (double) r2_DisplayMetrics.density;
                    this.resolution = new String(new StringBuilder(String.valueOf(Integer.toString(r2_DisplayMetrics.widthPixels))).append("*").append(Integer.toString(r2_DisplayMetrics.heightPixels)).toString());
                }
                if (r0_TelephonyManager.getSimState() == ShareUtils.SHARE_SMS) {
                    r0_String = r0_TelephonyManager.getSimOperator();
                    if (r0_String == null || r0_String.length() <= 0) {
                        r0_NetworkInfo = ((ConnectivityManager) r6_Context.getSystemService("connectivity")).getActiveNetworkInfo();
                    } else {
                        this.simType = new String(r0_String);
                    }
                    if (r0_NetworkInfo == null) {
                        r0_String = r0_NetworkInfo.getTypeName();
                        if (r0_String.equalsIgnoreCase(NetworkManager.MOBILE)) {
                            if (r0_String.equalsIgnoreCase(NetworkManager.WIFI)) {
                                this.netType = new String("Wi-Fi");
                            } else {
                                this.netType = new String("Wi-Fi");
                            }
                        } else {
                            this.netType = new String("2G/3G");
                        }
                    } else {
                        this.netType = new String("Wi-Fi");
                    }
                    appPackage = r6_Context.getPackageName();
                    appVersion = getAppVersion(r6_Context);
                    appName = b(r6_Context);
                }
                r0_NetworkInfo = ((ConnectivityManager) r6_Context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (r0_NetworkInfo == null) {
                    this.netType = new String("Wi-Fi");
                } else {
                    r0_String = r0_NetworkInfo.getTypeName();
                    if (r0_String.equalsIgnoreCase(NetworkManager.MOBILE)) {
                        if (r0_String.equalsIgnoreCase(NetworkManager.WIFI)) {
                            this.netType = new String("Wi-Fi");
                        } else {
                            this.netType = new String("Wi-Fi");
                        }
                    } else {
                        this.netType = new String("2G/3G");
                    }
                }
                appPackage = r6_Context.getPackageName();
                appVersion = getAppVersion(r6_Context);
                appName = b(r6_Context);
            }
        }
    }

    private String b(Context r5_Context) {
        String r0_String = null;
        try {
            r0_String = r5_Context.getPackageManager().getPackageInfo(r5_Context.getPackageName(), CLICK).applicationInfo.loadLabel(r5_Context.getPackageManager()).toString();
        } catch (Exception e) {
        }
        try {
            return URLEncoder.encode(r0_String, Base.UTF8);
        } catch (UnsupportedEncodingException e_2) {
            e_2.printStackTrace();
            return RContactStorage.PRIMARY_KEY;
        }
    }

    private boolean b() {
        return ((KeyguardManager) getContext().getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    private boolean b(MotionEvent r10_MotionEvent) {
        float r2f = r10_MotionEvent.getX();
        float r3f = r10_MotionEvent.getY();
        DisplayMetrics r4_DisplayMetrics = new DisplayMetrics();
        ((Activity) this.activityReference.get()).getWindowManager().getDefaultDisplay().getMetrics(r4_DisplayMetrics);
        this.mDensity = (double) r4_DisplayMetrics.density;
        int r0i = r4_DisplayMetrics.widthPixels;
        int r4i = getHeight();
        int r5i = this.b;
        int r6i = this.a;
        r2f = r2f + ((float) (r0i / 2 - this.adViewManager.width / 2)) - 2.0f;
        r0i = r0i / 2 + this.adViewManager.width / 2;
        if (AdViewTargeting.getSwitcherMode() == SwitcherMode.DEFAULT) {
            return true;
        }
        if (r2f < ((float) (r0i - r6i)) || r3f < ((float) ((r4i - r5i) / 2)) || r3f > ((float) (r4i - (r4i - r5i) / 2))) {
            return false;
        }
        return true;
    }

    private void c() {
        if (this.nextRation == null) {
            AdViewUtil.logInfo("nextRation is null!");
            rotateThreadedDelayed();
        } else if (b()) {
            AdViewUtil.logInfo("screen is locked");
            rotateThreadedPri(ShareUtils.SHARE_SMS);
        } else if (this.d) {
            AdViewUtil.logInfo("stop required");
            rotateThreadedPri(ShareUtils.SHARE_SMS);
        } else if (isConnectInternet(getContext())) {
            try {
                AdViewAdapter.handleOne(this, this.nextRation);
            } catch (Throwable th) {
                AdViewUtil.logWarn("Caught an exception in adapter:", th);
                rollover();
            }
        } else {
            AdViewUtil.logInfo("network is unavailable");
            rotateThreadedPri(ShareUtils.SHARE_SMS);
        }
    }

    public static int getAppVersion(Context r4_Context) {
        try {
            return r4_Context.getPackageManager().getPackageInfo(r4_Context.getPackageName(), CLICK).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return CLICK;
        }
    }

    public static boolean isConnectInternet(Context r1_Context) {
        NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r1_Context.getSystemService("connectivity")).getActiveNetworkInfo();
        return r0_NetworkInfo != null ? r0_NetworkInfo.isAvailable() : false;
    }

    public void AddSubView(ViewGroup r4_ViewGroup) {
        int r2i = RequestListener.DEFAULT_LOADED_SIZE;
        RelativeLayout r0_RelativeLayout = (RelativeLayout) this.superViewReference.get();
        if (r0_RelativeLayout == null) {
        } else {
            r0_RelativeLayout.removeAllViews();
            LayoutParams r1_LayoutParams = new RelativeLayout.LayoutParams(r2i, r2i);
            r1_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER);
            r0_RelativeLayout.addView(r4_ViewGroup, r1_LayoutParams);
            addCloseButton(this);
            this.activeRation = this.nextRation;
        }
    }

    public void addCloseButton(AdViewLayout r9_AdViewLayout) {
        if (AdViewTargeting.getSwitcherMode() != SwitcherMode.CANCLOSED) {
        } else {
            if (0.0d == this.mDensity) {
                DisplayMetrics r1_DisplayMetrics = new DisplayMetrics();
                ((Activity) this.activityReference.get()).getWindowManager().getDefaultDisplay().getMetrics(r1_DisplayMetrics);
                this.mDensity = (double) r1_DisplayMetrics.density;
            }
            this.a = (int) ((((double) this.adViewManager.width) / 6.4d) * 0.7d);
            this.b = (int) ((((double) this.adViewManager.width) / 6.4d) * 0.7d);
            View r1_View = new ImageView(r9_AdViewLayout.getContext());
            r1_View.setClickable(true);
            r1_View.setVisibility(XListViewFooter.STATE_NODATA);
            r1_View.setBackgroundDrawable(new BitmapDrawable(getClass().getResourceAsStream("/com/qiubai/library/adview/assets/ad_close.png")));
            LayoutParams r0_LayoutParams = new RelativeLayout.LayoutParams(this.a, this.b);
            r0_LayoutParams.leftMargin = this.a / 6;
            r0_LayoutParams.addRule(NearbySelectView.TIME_15MIN);
            r9_AdViewLayout.addView(r1_View, r0_LayoutParams);
            r1_View.setOnClickListener(new a(this));
            new Handler().postDelayed(new b(this, r1_View), 3000);
        }
    }

    public void appReport() {
    }

    public boolean cleanList() {
        if (AdViewUtil.statisticsList == null) {
            return false;
        }
        AdViewUtil.statisticsList.clear();
        return true;
    }

    public void fetchConfigThreadedDelayed(int r6i) {
        scheduler.schedule(new a(this), (long) r6i, TimeUnit.SECONDS);
    }

    public String getTokenMd5(long r3j) {
        return MD5.MD5Encode(new StringBuilder(String.valueOf(this.adKey)).append("0").append(this.deviceId).append(AdViewUtil.configVer).append(r3j).toString());
    }

    public boolean isClosed() {
        return this.c;
    }

    public synchronized void listStatistics(String r4_String, int r5i, int r6i) {
        if (AdViewUtil.statisticsList == null) {
            AdViewUtil.statisticsList = new ArrayList();
        }
        int r2i = 0;
        int r1i = 0;
        while (r2i < AdViewUtil.statisticsList.size()) {
            int r0i;
            if (((StatisticsBean) AdViewUtil.statisticsList.get(r2i)).getAdName().equals(r4_String)) {
                switch (r5i) {
                    case CLICK:
                        ((StatisticsBean) AdViewUtil.statisticsList.get(r2i)).setClick(((StatisticsBean) AdViewUtil.statisticsList.get(r2i)).getClick() + r6i);
                        break;
                    case IMPRESSION:
                        ((StatisticsBean) AdViewUtil.statisticsList.get(r2i)).setImpression(((StatisticsBean) AdViewUtil.statisticsList.get(r2i)).getImpression() + r6i);
                        break;
                    case FAIL:
                        ((StatisticsBean) AdViewUtil.statisticsList.get(r2i)).setFailed(((StatisticsBean) AdViewUtil.statisticsList.get(r2i)).getFailed() + r6i);
                        break;
                }
                r0i = IMPRESSION;
            } else {
                r0i = r1i;
            }
            r2i++;
            r1i = r0i;
        }
        if (r1i == 0) {
            StatisticsBean r0_StatisticsBean = new StatisticsBean();
            r0_StatisticsBean.setAdName(r4_String);
            switch (r5i) {
                case CLICK:
                    r0_StatisticsBean.setClick(r6i);
                    break;
                case IMPRESSION:
                    r0_StatisticsBean.setImpression(r6i);
                    break;
                case FAIL:
                    r0_StatisticsBean.setFailed(r6i);
                    break;
            }
            AdViewUtil.statisticsList.add(r0_StatisticsBean);
        }
        if (this.statisticsInterface != null) {
            this.statisticsInterface.onListChange(AdViewUtil.statisticsList);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent r5_MotionEvent) {
        switch (r5_MotionEvent.getAction()) {
            case CLICK:
                boolean r0z = b(r5_MotionEvent);
                AdViewUtil.logInfo(new StringBuilder("closeAble:").append(r0z).toString());
                listStatistics(this.activeRation.name, CLICK, IMPRESSION);
                if (this.activeRation == null || this.activeRation.type == 38 || this.activeRation.type == 30 || this.activeRation.type == 42) {
                    return false;
                }
                AdViewUtil.logInfo(new StringBuilder("Intercepted ACTION_DOWN event 2, activeRation.type=").append(this.activeRation.type).toString());
                if (this.activeRation.type == AdViewUtil.NETWORK_TYPE_ADFILL) {
                    try {
                        if (AdViewTargeting.getSwitcherMode() == SwitcherMode.DEFAULT || (!r0z)) {
                            AdViewAdapter.onClickAd(a(r5_MotionEvent));
                        }
                    } catch (Throwable th) {
                        AdViewUtil.logError("onClick", th);
                    }
                }
                break;
        }
        return false;
    }

    protected void onMeasure(int r5i, int r6i) {
        int r0i = MeasureSpec.getSize(r5i);
        int r1i = MeasureSpec.getSize(r6i);
        if (this.adViewManager != null) {
            r5i = MeasureSpec.makeMeasureSpec(this.adViewManager.width, ExploreByTouchHelper.INVALID_ID);
        }
        if (this.g <= 0 || r0i <= this.g) {
            if (this.h <= 0 || r1i <= this.h) {
                super.onMeasure(r5i, r6i);
            } else {
                r6i = MeasureSpec.makeMeasureSpec(this.h, ExploreByTouchHelper.INVALID_ID);
                super.onMeasure(r5i, r6i);
            }
        } else {
            r5i = MeasureSpec.makeMeasureSpec(this.g, ExploreByTouchHelper.INVALID_ID);
            if (this.h <= 0 || r1i <= this.h) {
                super.onMeasure(r5i, r6i);
            } else {
                r6i = MeasureSpec.makeMeasureSpec(this.h, ExploreByTouchHelper.INVALID_ID);
                super.onMeasure(r5i, r6i);
            }
        }
    }

    protected void onWindowVisibilityChanged(int r1i) {
        a(r1i);
    }

    public void pushSubView(ViewGroup r5_ViewGroup) {
        RelativeLayout r0_RelativeLayout = (RelativeLayout) this.superViewReference.get();
        if (r0_RelativeLayout == null) {
        } else {
            r0_RelativeLayout.removeAllViews();
            if (this.nextRation != null) {
                LayoutParams r1_LayoutParams;
                r1_LayoutParams = this.nextRation.type == 33 ? new RelativeLayout.LayoutParams(-2, -2) : new RelativeLayout.LayoutParams(-2, -2);
                r1_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER);
                r0_RelativeLayout.addView(r5_ViewGroup, r1_LayoutParams);
                if (AdViewTargeting.getSwitcherMode() == SwitcherMode.CANCLOSED) {
                    addCloseButton(this);
                }
                AdViewUtil.logInfo("Added subview");
                this.activeRation = this.nextRation;
            }
        }
    }

    public void reportClick() {
    }

    public void reportImpression() {
        this.activeRation = this.nextRation;
    }

    public void rollover() {
        this.nextRation = this.adViewManager.getRollover();
        this.handler.post(new b(this));
    }

    public void rotateAd() {
        if (this.isTerminated) {
        } else if (this.e) {
            AdViewUtil.logInfo("Rotating Ad");
            this.nextRation = this.adViewManager.getRation();
            this.handler.post(new b(this));
        } else {
            this.f = false;
        }
    }

    public void rotatePriAd() {
        if (this.isTerminated) {
        } else if (this.e) {
            AdViewUtil.logInfo("Rotating Pri Ad");
            this.nextRation = this.adViewManager.getRollover();
            this.handler.post(new b(this));
        } else {
            this.f = false;
        }
    }

    public void rotateThreadedDelayed() {
        AdViewUtil.logInfo(new StringBuilder("Will call rotateAd() in ").append(this.extra.cycleTime).append(" seconds").toString());
        scheduler.schedule(new d(this), (long) this.extra.cycleTime, TimeUnit.SECONDS);
    }

    public void rotateThreadedPri(int r6i) {
        scheduler.schedule(new e(this), (long) r6i, TimeUnit.SECONDS);
    }

    public void saveStatistics() {
        String r0_String = ";";
        Date r4_Date = new Date();
        SimpleDateFormat r5_SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm.ss ");
        if (AdViewUtil.statisticsList != null) {
            int r1i = 0;
            while (r1i < AdViewUtil.statisticsList.size()) {
                r1i++;
                r0_String = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(r0_String)).append(((StatisticsBean) AdViewUtil.statisticsList.get(r1i)).getAdName()).append("- ").toString())).append("\u5c55\u793a\uff1a").append(((StatisticsBean) AdViewUtil.statisticsList.get(r1i)).getImpression()).toString())).append(",\u70b9\u51fb\uff1a").append(((StatisticsBean) AdViewUtil.statisticsList.get(r1i)).getClick()).toString())).append(",\u5931\u8d25\uff1a").append(((StatisticsBean) AdViewUtil.statisticsList.get(r1i)).getFailed()).append(";\n").toString();
            }
        }
        if (r0_String.length() > 1) {
            AdViewUtil.writeLogtoFile(r5_SimpleDateFormat.format(r4_Date), false, r0_String.substring(IMPRESSION, r0_String.length()));
        }
    }

    public void setAdViewInterface(AdViewInterface r1_AdViewInterface) {
        this.adViewInterface = r1_AdViewInterface;
    }

    public void setClosed(boolean r2z) {
        this.c = r2z;
        this.d = false;
        if (r2z) {
            a();
        }
    }

    public void setHasWindow(boolean r1z) {
        this.e = r1z;
    }

    public void setStatisticsInterface(StatisticsInterface r1_StatisticsInterface) {
        this.statisticsInterface = r1_StatisticsInterface;
    }

    public void setVisibility(int r1i) {
        a(r1i);
        super.setVisibility(r1i);
    }
}