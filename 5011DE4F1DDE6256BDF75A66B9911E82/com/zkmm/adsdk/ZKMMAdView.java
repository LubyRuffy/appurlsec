package com.zkmm.adsdk;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import java.util.Random;

// compiled from: SourceFile
public final class ZKMMAdView extends RelativeLayout {
    public static int DESIRE_BANNER_SIZE_W320_H50;
    private static int f;
    private static int g;
    private static byte h;
    private static boolean k;
    private static int l;
    private cs a;
    private AdListener b;
    private AdStatusListener c;
    private volatile boolean d;
    private volatile boolean e;
    private int i;
    private Random j;
    private boolean m;
    private Handler n;

    static {
        h = (byte) 0;
        k = false;
        l = 1;
        DESIRE_BANNER_SIZE_W320_H50 = 1;
    }

    public ZKMMAdView(Context r2_Context, AttributeSet r3_AttributeSet) {
        this(r2_Context, r3_AttributeSet, 0);
    }

    public ZKMMAdView(Context r4_Context, AttributeSet r5_AttributeSet, int r6i) {
        super(r4_Context, r5_AttributeSet, r6i);
        this.e = false;
        this.i = 1;
        this.j = new Random();
        this.m = true;
        this.n = new as(this);
        if (s.d(r4_Context)) {
            this.d = false;
            setFocusable(true);
            setDescendantFocusability(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START);
            setClickable(true);
            if (r5_AttributeSet != null) {
                boolean r0z = r5_AttributeSet.getAttributeBooleanValue(new StringBuilder("http://schemas.android.com/apk/res/").append(r4_Context.getPackageName()).toString(), "testing", false);
                if (r0z) {
                    m.b(r0z);
                }
            }
            m.b(m.a(r4_Context));
            m.D = bg.a(r4_Context);
            a(r4_Context);
        }
    }

    public ZKMMAdView(Context r2_Context, String r3_String, boolean r4z) {
        this(r2_Context, r3_String, r4z, 30);
    }

    public ZKMMAdView(Context r4_Context, String r5_String, boolean r6z, int r7i) {
        super(r4_Context, null, 0);
        this.e = false;
        this.i = 1;
        this.j = new Random();
        this.m = true;
        this.n = new as(this);
        m.b(r5_String);
        if (s.d(r4_Context)) {
            this.d = false;
            setFocusable(true);
            setDescendantFocusability(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START);
            setClickable(true);
            if (r6z) {
                m.b(r6z);
            }
            setRequestInterval(r7i);
            m.D = bg.a(r4_Context);
            a(r4_Context);
        }
    }

    protected static int a() {
        return f;
    }

    private void a(Context r11_Context) {
        m.C = ZKMMKey.a(r11_Context);
        DisplayMetrics r0_DisplayMetrics = new DisplayMetrics();
        r0_DisplayMetrics = getResources().getDisplayMetrics();
        if (r0_DisplayMetrics.widthPixels > r0_DisplayMetrics.heightPixels) {
            m.z = r0_DisplayMetrics.heightPixels;
            m.y = r0_DisplayMetrics.widthPixels;
        } else {
            m.z = r0_DisplayMetrics.widthPixels;
            m.y = r0_DisplayMetrics.heightPixels;
        }
        m.L = (double) r0_DisplayMetrics.density;
        Log.i("Adwo SDK", new StringBuilder("Version 4.0 width: ").append(r0_DisplayMetrics.widthPixels).append(" height:").append(r0_DisplayMetrics.heightPixels).append("   density:").append(r0_DisplayMetrics.density).append("-dp:").append(r0_DisplayMetrics.xdpi).append("-").append(r0_DisplayMetrics.ydpi).append("- ").append(m.u).toString());
        m.c(r11_Context);
        int r1i;
        if (k) {
            r1i = (int) (((double) m.z) / m.L);
            m.A = r1i;
            m.B = (r1i * 50) / 320;
        } else {
            if (l != 0) {
                if (l != 1) {
                    if (l == 2) {
                        m.A = 360;
                        m.B = 56;
                    } else if (l == 3) {
                        m.A = 400;
                        m.B = 62;
                    } else if (l == 4) {
                        m.A = 320;
                        m.B = 250;
                    } else if (l == 5) {
                        m.A = 360;
                        m.B = 275;
                    } else if (l == 6) {
                        m.A = 400;
                        m.B = 300;
                    } else {
                        Log.e("Adwo SDK", "Disired banner size not defined. The default setting will be used.");
                        r1i = (int) (((double) m.z) / m.L);
                        if (r1i == 360) {
                            m.A = 360;
                            m.B = 56;
                        } else if (r1i == 400) {
                            m.A = 400;
                            m.B = 62;
                        } else {
                            m.A = 320;
                            m.B = 50;
                        }
                    }
                }
            } else {
                r1i = (int) (((double) m.z) / m.L);
                if (r1i == 360) {
                    m.A = 360;
                    m.B = 56;
                } else if (r1i == 400) {
                    m.A = 400;
                    m.B = 62;
                }
            }
            m.A = 320;
            m.B = 50;
        }
        f = (int) (((float) m.A) * r0_DisplayMetrics.density);
        g = (int) (r0_DisplayMetrics.density * ((float) m.B));
        Log.e("Adwo SDK", "ZKMMAdView initiating...");
    }

    private void a(boolean r5z) {
        if (r5z) {
            this.n.removeMessages(100);
            m.R.removeMessages(0);
            m.R = null;
        } else {
            this.n.removeMessages(100);
            f();
            if (!this.e) {
                this.n.sendEmptyMessageDelayed(100, (long) (bg.a * 1000));
            }
        }
    }

    protected static int b() {
        return g;
    }

    private void f() {
        if (this.d) {
            if (!this.m) {
                ViewGroup r0_ViewGroup = (ViewGroup) getParent();
                if (r0_ViewGroup != null) {
                    if (r0_ViewGroup.getWidth() < f || r0_ViewGroup.getHeight() < g || getWidth() < f || getHeight() < g) {
                        Log.e("Adwo SDK", "The Adview's size is not correctly defined. Please make sure that the width of adview or its parent view equals or greater than 320, and hight of adview or its parent view equals or greater than 50.");
                    }
                } else {
                    Log.e("Adwo SDK", "The Adview's parent view does not exist.");
                }
            }
            if (m.a()) {
                Log.e("Adwo SDK", "The interval that this request from the last one is shorter than 15 seconds.In other word, ad requesting has been too frequent.");
                if (this.b != null) {
                    this.b.onFailedToReceiveAd(this, new ErrorCode(30, "ERR_UNKNOWN"));
                }
            } else {
                new at(this).execute(new Void[0]);
            }
        }
    }

    public static void setAggChannelId(byte r1b) {
        if (r1b == 1) {
            m.s = (byte) 24;
        } else if (r1b == 2) {
            m.s = (byte) 40;
        } else if (r1b == 3) {
            m.s = (byte) 56;
        } else {
            m.s = (byte) 8;
        }
    }

    public static void setBannerMatchScreenWidth(boolean r0z) {
        k = r0z;
    }

    public static void setDesiredBannerSize(int r0i) {
        l = r0i;
    }

    public static void setMarketId(byte r0b) {
        m.u = r0b;
    }

    protected final AdListener c() {
        return this.b;
    }

    protected final AdStatusListener d() {
        return this.c;
    }

    public final boolean hasAd() {
        return this.a != null;
    }

    protected final void onAttachedToWindow() {
        this.d = true;
        a(false);
        super.onAttachedToWindow();
    }

    protected final void onDetachedFromWindow() {
        this.d = false;
        try {
            a(true);
        } catch (Exception e) {
        }
        super.onDetachedFromWindow();
    }

    public final void setAnimationType(int r1i) {
        this.i = r1i;
    }

    public final void setKeyWords(String r1_String) {
        m.l = r1_String;
    }

    public final void setListener(AdListener r2_AdListener) {
        synchronized (this) {
            this.b = r2_AdListener;
        }
    }

    public final void setRequestInterval(int r4i) {
        if (r4i == 0) {
            this.e = true;
        }
        if (bg.a <= r4i) {
            bg.a = r4i;
        } else {
            Log.e("Adwo SDK", new StringBuilder("The request interval you just set is too short, we have set the interval at ").append(bg.a).toString());
        }
    }

    public final void setStatusListener(AdStatusListener r2_AdStatusListener) {
        synchronized (this) {
            this.c = r2_AdStatusListener;
        }
    }

    public final void setVisibility(int r4i) {
        if (super.getVisibility() != r4i) {
            synchronized (this) {
                int r1i = getChildCount();
                int r0i = 0;
                while (r0i < r1i) {
                    getChildAt(r0i).setVisibility(r4i);
                    r0i++;
                }
                super.setVisibility(r4i);
                if (r4i != 4 || this.a == null) {
                } else {
                    removeView(this.a);
                    this.a.destroy();
                    this.a = null;
                    removeAllViews();
                }
            }
        }
    }
}