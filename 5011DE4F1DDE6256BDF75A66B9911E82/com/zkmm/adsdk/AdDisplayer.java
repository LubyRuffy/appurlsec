package com.zkmm.adsdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;
import qsbk.app.bean.Base;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public final class AdDisplayer {
    public static final byte ADWO_FS_ENTRY = (byte) 1;
    public static final byte ADWO_FS_INTERCEPT = (byte) 0;
    public static final byte ADWO_FS_TRANSPOSITION = (byte) 2;
    public static final byte ADWO_FS_TYPE_ALL = (byte) 0;
    public static final byte ADWO_FS_TYPE_APP_FUN = (byte) 1;
    public static final byte ADWO_FS_TYPE_NO_APP_FUN = (byte) 2;
    protected static byte a;
    protected static byte b;
    private static AdDisplayer d;
    private static View f;
    private static Context m;
    String c;
    private PopupWindow e;
    private ae g;
    private WebView h;
    private int i;
    private RelativeLayout j;
    private RelativeLayout k;
    private ProgressBar l;
    private DisplayMetrics n;
    private int o;
    private int p;
    private int q;
    private int r;
    private Handler s;
    private boolean t;
    private FullScreenAdListener u;
    private g v;
    private int w;

    static {
        a = (byte) 0;
        b = (byte) 0;
    }

    private AdDisplayer(Context r5_Context) {
        this.e = null;
        this.i = 602;
        this.n = null;
        this.o = 0;
        Handler r0_Handler = new Handler();
        this.p = 1;
        this.q = 1;
        this.r = 50;
        this.s = new af(this);
        this.t = false;
        this.c = null;
        this.w = 0;
        this.n = r5_Context.getResources().getDisplayMetrics();
        dismissDisplayer();
        a(this.n.widthPixels, this.n.heightPixels, true);
        if (this.n.widthPixels > this.n.heightPixels) {
            m.z = this.n.heightPixels;
            m.y = this.n.widthPixels;
        } else {
            m.z = this.n.widthPixels;
            m.y = this.n.heightPixels;
        }
        m.L = (double) this.n.density;
    }

    private void a(int r5i, int r6i) {
        this.e = new AW(m);
        this.e.setOutsideTouchable(false);
        this.e.setClippingEnabled(false);
        this.e.setTouchable(true);
        this.e.setFocusable(true);
        this.e.setAnimationStyle(16973910);
        this.e.setBackgroundDrawable(new BitmapDrawable());
        this.e.setWidth(r5i);
        this.e.setHeight(r6i);
        if (this.k != null) {
            this.k.removeView(this.j);
        }
        this.k = new RelativeLayout(m);
        this.k.addView(this.j);
        this.e.setContentView(this.k);
        this.e.setOnDismissListener(new bw(this));
    }

    private void a(View r8_View) {
        f = r8_View;
        setAnimation(this.v.l);
        b(this.n.widthPixels, this.n.heightPixels);
        a(r8_View, 0, 0, this.n.widthPixels, this.n.heightPixels);
        if (m.x.containsKey(Integer.valueOf(this.v.a))) {
            m.x.put(Integer.valueOf(this.v.a), Integer.valueOf(((Integer) m.x.get(Integer.valueOf(this.v.a))).intValue() + 1));
        } else {
            m.x.put(Integer.valueOf(this.v.a), Integer.valueOf(1));
        }
        m.N = m.M.format(new Date());
        int r0i = s.a(m, m.N);
        if (r0i == 0) {
            s.a(m);
            s.a(m.N, 1, m);
        } else {
            r0i++;
            s.a(m.N, r0i, m);
        }
        Iterator r1_Iterator;
        if (m.T <= 0 || r0i < m.T) {
            if (this.v.j == null || this.v.j.size() <= 0) {
            } else {
                r1_Iterator = this.v.j.iterator();
                while (r1_Iterator.hasNext() && s.j(m, (String) r1_Iterator.next())) {
                    s.b = this.v.a;
                }
            }
        } else {
            m.U = (byte) 1;
            if (this.v.j == null || this.v.j.size() <= 0) {
            } else {
                r1_Iterator = this.v.j.iterator();
                while (r1_Iterator.hasNext() && s.j(m, (String) r1_Iterator.next())) {
                    s.b = this.v.a;
                }
            }
        }
    }

    private void a(View r9_View, int r10i, int r11i, int r12i, int r13i) {
        this.q = m.getResources().getConfiguration().orientation;
        try {
            if (m instanceof Activity) {
                Activity r0_Activity = (Activity) m;
                if (r0_Activity != null) {
                    if (r0_Activity.isFinishing() || r0_Activity.isRestricted()) {
                        Log.e("Adwo SDK", "The context of activity is not valid anymore.");
                    } else {
                        r0_Activity.runOnUiThread(new cc(this, r0_Activity));
                    }
                }
                r9_View.post(new cd(this, r9_View, r10i, r11i, r12i, r13i));
                if (this.q == 1) {
                    this.h.loadUrl("javascript:adwoFSAdOrientationChanged(0);");
                } else {
                    this.h.loadUrl("javascript:adwoFSAdOrientationChanged(1);");
                }
                int r0i = 0;
                try {
                    if (this.q == 2) {
                        r0i = 2;
                    }
                } catch (Exception e) {
                }
                this.h.loadUrl(new StringBuilder("javascript:adwoFSAdHasShown(").append(r0i).append(");").toString());
            } else {
                Log.e("Adwo SDK", "The context param must be an instance of Activity.");
            }
        } catch (Exception e_2) {
            e_2.printStackTrace();
            b(r9_View, r10i, r11i, r12i, r13i);
        }
    }

    static /* synthetic */ void a(AdDisplayer r0_AdDisplayer, StringTokenizer r1_StringTokenizer) {
    }

    private void a(g r5_g, String r6_String, String r7_String, boolean r8z) {
        cb r0_cb = new cb(this, r8z, r7_String);
        Object[] r1_ObjectA = new Object[4];
        r1_ObjectA[0] = r5_g.d;
        r1_ObjectA[1] = r6_String;
        r1_ObjectA[2] = r7_String;
        r1_ObjectA[3] = r5_g;
        r0_cb.execute(r1_ObjectA);
    }

    private void b(int r3i, int r4i) {
        this.h.setLayoutParams(new LayoutParams(r3i, r4i));
        this.h.requestLayout();
    }

    private void b(View r8_View, int r9i, int r10i, int r11i, int r12i) {
        try {
            a(this.n.widthPixels, this.n.heightPixels);
            r8_View.post(new ce(this, r8_View, r9i, r10i, r11i, r12i));
        } catch (Exception e) {
            e.printStackTrace();
            this.w++;
            if (this.w < 3) {
                b(r8_View, r9i, r10i, r11i, r12i);
            } else {
                this.w = 0;
            }
        }
    }

    static /* synthetic */ void b(AdDisplayer r8_AdDisplayer, g r9_g) {
        if (r9_g.d.endsWith(".zip")) {
            int r0i = r9_g.d.lastIndexOf("/");
            String r3_String = r9_g.d.substring(r0i + 1, r9_g.d.lastIndexOf(".zip"));
            File r4_File = new File(new StringBuilder(String.valueOf(m.P)).append(r9_g.a).append(File.separator).toString());
            if (r4_File.exists()) {
                if (r9_g.e != null) {
                    SimpleDateFormat r0_SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    try {
                        if (r8_AdDisplayer.v.e.length() == 10) {
                            r0_SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        }
                        if (r0_SimpleDateFormat.parse(r9_g.e).after(new Date(r4_File.lastModified()))) {
                            r0i = 1;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    r0i = 0;
                } else {
                    r0i = 0;
                }
                if (r0i != 0) {
                    s.a(r4_File);
                    r8_AdDisplayer.a(r9_g, new StringBuilder(String.valueOf(m.P)).append(r9_g.a).append(File.separator).toString(), r3_String, true);
                } else {
                    String r0_String = new StringBuilder(String.valueOf(m.P)).append(r9_g.a).append(File.separator).append(r3_String).append("/object.temp").toString();
                    File r1_File = new File(r0_String);
                    if (r1_File.exists() && r1_File.isFile()) {
                        r1_File.delete();
                        r9_g.d = new StringBuilder("file://").append(m.P).append(r9_g.a).append(File.separator).append(r3_String).append("/index.html?w=").append(((float) r8_AdDisplayer.n.widthPixels) / r8_AdDisplayer.n.density).append("&h=").append(((float) r8_AdDisplayer.n.heightPixels) / r8_AdDisplayer.n.density).toString();
                        s.a(r9_g, r0_String);
                    } else {
                        r9_g.d = new StringBuilder("file://").append(m.P).append(r9_g.a).append(File.separator).append(r3_String).append("/index.html?w=").append(((float) r8_AdDisplayer.n.widthPixels) / r8_AdDisplayer.n.density).append("&h=").append(((float) r8_AdDisplayer.n.heightPixels) / r8_AdDisplayer.n.density).toString();
                        s.a(r9_g, r0_String);
                    }
                }
            } else {
                r8_AdDisplayer.a(r9_g, new StringBuilder(String.valueOf(m.P)).append(r9_g.a).append(File.separator).toString(), r3_String, true);
            }
        } else {
            Log.e("Adwo SDK", "The ad file's url is not valid.");
        }
    }

    private void c() {
        new ca(this).execute(new String[0]);
    }

    private void d() {
        if (this.g != null) {
            this.g.b();
            this.g = null;
        }
    }

    public static AdDisplayer getInstance(Context r1_Context) {
        m = r1_Context;
        if (d == null) {
            d = new AdDisplayer(r1_Context);
        }
        return d;
    }

    static /* synthetic */ void i(AdDisplayer r0_AdDisplayer) {
    }

    protected final void a(int r5i, int r6i, boolean r7z) {
        dismissDisplayer();
        if (r7z) {
            this.j = new RelativeLayout(m);
            this.j.setGravity(Base64.URL_SAFE);
            this.h = new WebView(m);
            this.h.setId(this.i);
            this.h.setBackgroundColor(0);
            this.h.setWebViewClient(new q(this));
            this.h.setWebChromeClient(new k(this));
            this.h.setScrollBarStyle(0);
            this.h.setVerticalScrollBarEnabled(false);
            this.h.setHorizontalScrollBarEnabled(false);
            CookieManager.getInstance().setAcceptCookie(true);
            WebSettings r0_WebSettings = this.h.getSettings();
            r0_WebSettings.setDefaultTextEncodingName(Base.UTF8);
            r0_WebSettings.setGeolocationEnabled(true);
            r0_WebSettings.setSupportZoom(false);
            r0_WebSettings.setBuiltInZoomControls(false);
            r0_WebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            r0_WebSettings.setJavaScriptEnabled(true);
            r0_WebSettings.setPluginsEnabled(true);
            r0_WebSettings.setSaveFormData(true);
            r0_WebSettings.setLightTouchEnabled(true);
            if (m.q == 0) {
                r0_WebSettings.setAppCacheEnabled(true);
                r0_WebSettings.setDatabaseEnabled(true);
                r0_WebSettings.setDomStorageEnabled(true);
                r0_WebSettings.setCacheMode(-1);
                r0_WebSettings.setAppCacheMaxSize(8388608);
                if (m.a == null) {
                    m.a = new StringBuilder("/data/data/").append(m.getPackageName()).append("/cache").toString();
                }
                r0_WebSettings.setAppCachePath(m.a);
            }
            this.h.setOnTouchListener(new by(this));
            this.h.addJavascriptInterface(new bz(this), "adwo");
            this.j.addView(this.h);
        } else {
            b(r5i, r6i);
        }
        a(r5i, r6i);
    }

    public final void dismissDisplayer() {
        try {
            if (this.e != null) {
                this.e.dismiss();
            }
            d();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void displayFullScreenAd(View r4_View) {
        if (this.v == null) {
            Log.e("Adwo SDK", "The ad object is null.");
        } else {
            try {
                if (this.o == 0) {
                    Rect r1_Rect = new Rect();
                    ((Activity) m).getWindow().getDecorView().getWindowVisibleDisplayFrame(r1_Rect);
                    if (r1_Rect.top == 0) {
                        this.o = 0;
                    } else {
                        this.o = r1_Rect.top;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.e.setBackgroundDrawable(new ColorDrawable(0));
            a(r4_View);
        }
    }

    public final void initParems(String r3_String, boolean r4z, FullScreenAdListener r5_FullScreenAdListener) {
        m.b(r3_String);
        if (s.d(m)) {
            m.a(r4z);
            this.u = r5_FullScreenAdListener;
            m.C = ZKMMKey.a(m);
            m.D = bg.a(m);
            m.c(m);
            File r0_File = new File(m.P);
            if (!r0_File.exists()) {
                r0_File.mkdirs();
            }
        }
    }

    public final boolean isShowing() {
        return this.e != null ? this.e.isShowing() : false;
    }

    public final void preLoadFullScreenAd() {
        int r0i = 1;
        if (this.v == null) {
            Log.e("Adwo SDK", "The ad object is null.");
        } else {
            this.h.clearHistory();
            if (b == (byte) 1) {
                this.h.loadUrl(this.v.d);
            } else {
                String r2_String = this.v.d;
                if (m.q == 0 || (!r2_String.endsWith(".zip"))) {
                    this.h.clearHistory();
                    this.h.loadUrl(r2_String);
                } else {
                    r2_String = new StringBuilder(String.valueOf(m.O)).append(this.v.a).append(File.separator).append(r2_String.substring(r2_String.lastIndexOf("/") + 1, r2_String.lastIndexOf(".zip"))).toString();
                    File r3_File = new File(r2_String);
                    String r4_String = new StringBuilder("file://").append(r2_String).append(File.separator).append("index.html?w=").append(((float) this.n.widthPixels) / this.n.density).append("&h=").append(((float) this.n.heightPixels) / this.n.density).toString();
                    if (r3_File.exists()) {
                        if (this.v.e != null) {
                            SimpleDateFormat r2_SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            try {
                                if (this.v.e.length() == 10) {
                                    r2_SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if (r2_SimpleDateFormat.parse(this.v.e).after(new Date(r3_File.lastModified()))) {
                                if (r0i == 0) {
                                    s.a(r3_File);
                                    a(this.v, new StringBuilder(String.valueOf(m.O)).append(this.v.a).append(File.separator).toString(), r4_String, false);
                                } else {
                                    this.h.loadUrl(r4_String);
                                }
                            }
                            r0i = 0;
                        } else {
                            r0i = 0;
                        }
                        if (r0i == 0) {
                            this.h.loadUrl(r4_String);
                        } else {
                            s.a(r3_File);
                            a(this.v, new StringBuilder(String.valueOf(m.O)).append(this.v.a).append(File.separator).toString(), r4_String, false);
                        }
                    } else {
                        a(this.v, new StringBuilder(String.valueOf(m.O)).append(this.v.a).append(File.separator).toString(), r4_String, false);
                    }
                    this.v.d = r4_String;
                }
            }
        }
    }

    public final void requestFullScreenAd() {
        if (m.b()) {
            Log.e("Adwo SDK", "The interval that this request from the last one is shorter than 20 seconds.In other word, ad requesting has been too frequent.");
        } else if (b == 1) {
            if (m.q == 0) {
                Log.e("Adwo SDK", "No external storage found on the device, so could not make an ad request.");
                if (this.u != null) {
                    this.u.onFailedToReceiveAd(new ErrorCode(33, "OTHER_ERROR_NO_SDCARD"));
                }
            } else if (m.Q && s.c(m) == 0) {
                Log.e("Adwo SDK", "The network is not available.");
                if (this.u != null) {
                    this.u.onFailedToReceiveAd(new ErrorCode(30, "ERR_UNKNOWN"));
                }
            } else {
                File r2_File = new File(m.P);
                if (!r2_File.exists()) {
                    r2_File.mkdirs();
                }
                File[] r2_FileA = r2_File.listFiles();
                if (r2_FileA == null || r2_FileA.length == 0) {
                    Log.e("Adwo SDK", "This is the first entry full screen ad or there is no ad available in the sdcard.");
                    if (this.u != null) {
                        this.u.onFailedToReceiveAd(new ErrorCode(35, "OTHER_ERROR_NO_ENTRY_AD_ON_SDCARD"));
                    }
                    c();
                } else {
                    g r0_g;
                    File[] r0_FileA = r2_FileA[0].listFiles();
                    if (r0_FileA == null || r0_FileA.length <= 0) {
                        r0_g = null;
                        if (r0_g == null) {
                            this.v = r0_g;
                            r2_FileA[0].getAbsolutePath();
                        } else {
                            Log.e("Adwo SDK", "There is no entry full screen ad available in the sdcard.");
                            this.v = null;
                            if (this.u == null) {
                                this.u.onFailedToReceiveAd(new ErrorCode(35, "OTHER_ERROR_NO_ENTRY_AD_ON_SDCARD"));
                            }
                            return;
                        }
                    } else {
                        r0_g = s.c(new StringBuilder(String.valueOf(r0_FileA[0].getAbsolutePath())).append("/object.temp").toString());
                        if (r0_g == null) {
                            Log.e("Adwo SDK", "There is no entry full screen ad available in the sdcard.");
                            this.v = null;
                            if (this.u == null) {
                                return;
                            }
                            this.u.onFailedToReceiveAd(new ErrorCode(35, "OTHER_ERROR_NO_ENTRY_AD_ON_SDCARD"));
                            return;
                        }
                    }
                    this.v = r0_g;
                    r2_FileA[0].getAbsolutePath();
                    if (this.u != null) {
                        this.u.onReceiveAd();
                    }
                    c();
                }
            }
        } else {
            c();
        }
    }

    public final void setAnimation(int r3i) {
        switch (r3i) {
            case XListViewHeader.STATE_REFRESHING:
                this.e.setAnimationStyle(16973827);
                break;
            case XListViewFooter.STATE_NOMORE:
                this.e.setAnimationStyle(16973910);
                break;
            case ShareUtils.SHARE_SMS:
            case ShareUtils.SHARE_COPY:
                this.e.setAnimationStyle(16973826);
                break;
        }
    }

    public final void setDesireAdForm(byte r1b) {
        b = r1b;
    }

    public final void setDesireAdType(byte r1b) {
        a = r1b;
    }

    public final void setFSContext(Context r1_Context) {
        m = r1_Context;
    }

    public final void setKeyWords(String r1_String) {
        m.l = r1_String;
    }
}