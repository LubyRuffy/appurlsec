package com.baidu.mobads;

import android.content.Context;
import android.os.Handler.Callback;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.baidu.mobads.a.a;
import com.baidu.mobads.a.b;
import com.baidu.mobads.a.d;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import qsbk.app.widget.listview.XListViewHeader;

public final class AdView extends RelativeLayout {
    protected static final String P_VERSION = "3.42";
    private static Class<?> b;
    private ViewGroup a;

    public AdView(Context r4_Context) {
        this(r4_Context, true, AdSize.Banner, RContactStorage.PRIMARY_KEY);
    }

    public AdView(Context r7_Context, AttributeSet r8_AttributeSet) {
        this(r7_Context, r8_AttributeSet, true, AdSize.Banner, RContactStorage.PRIMARY_KEY);
    }

    private AdView(Context r7_Context, AttributeSet r8_AttributeSet, boolean r9z, AdSize r10_AdSize, String r11_String) {
        super(r7_Context, r8_AttributeSet);
        try {
            if (b == null) {
                b = b.b(r7_Context, "com.baidu.mobads.remote.AdView");
            }
            Class r0_Class = b;
            Class[] r1_ClassA = new Class[5];
            r1_ClassA[0] = Context.class;
            r1_ClassA[1] = AttributeSet.class;
            r1_ClassA[2] = Boolean.TYPE;
            r1_ClassA[3] = Integer.TYPE;
            r1_ClassA[4] = String.class;
            Constructor r0_Constructor = r0_Class.getConstructor(r1_ClassA);
            Object[] r1_ObjectA = new Object[5];
            r1_ObjectA[0] = r7_Context;
            r1_ObjectA[1] = r8_AttributeSet;
            r1_ObjectA[2] = Boolean.valueOf(r9z);
            r1_ObjectA[3] = Integer.valueOf(r10_AdSize.getValue());
            r1_ObjectA[4] = r11_String;
            this.a = (ViewGroup) r0_Constructor.newInstance(r1_ObjectA);
            addView(this.a, new LayoutParams(-1, -1));
            Object[] r0_ObjectA = new Object[2];
            r0_ObjectA[0] = b;
            r0_ObjectA[1] = this.a;
            d.a(r0_ObjectA);
            if (!(a.a) || a.b <= 2) {
            } else {
                b(r7_Context);
                a.a = false;
            }
        } catch (Exception e) {
            d.b(e);
        }
    }

    public AdView(Context r2_Context, AdSize r3_AdSize, String r4_String) {
        this(r2_Context, true, r3_AdSize, r4_String);
    }

    AdView(Context r7_Context, boolean r8z, AdSize r9_AdSize, String r10_String) {
        this(r7_Context, null, r8z, r9_AdSize, r10_String);
    }

    static Object a(Context r6_Context) {
        try {
            if (b == null) {
                b = b.b(r6_Context, "com.baidu.mobads.remote.AdView");
            }
            Class r1_Class = b;
            Class[] r3_ClassA = new Class[1];
            r3_ClassA[0] = Context.class;
            Method r1_Method = r1_Class.getDeclaredMethod("getInstance", r3_ClassA);
            Object[] r2_ObjectA = new Object[3];
            r2_ObjectA[0] = "AdView.getInstance";
            r2_ObjectA[1] = r6_Context;
            r2_ObjectA[2] = r1_Method;
            d.a(r2_ObjectA);
            Object[] r3_ObjectA = new Object[1];
            r3_ObjectA[0] = r6_Context;
            return r1_Method.invoke(null, r3_ObjectA);
        } catch (Exception e) {
            d.b(e);
            return null;
        }
    }

    static void a(Context r5_Context, Object r6_Object) {
        try {
            if (b == null) {
                b = b.b(r5_Context, "com.baidu.mobads.remote.AdView");
            }
            Class r0_Class = b;
            Class[] r2_ClassA = new Class[2];
            r2_ClassA[0] = Context.class;
            r2_ClassA[1] = Object.class;
            Method r0_Method = r0_Class.getDeclaredMethod("removeInstance", r2_ClassA);
            Object[] r1_ObjectA = new Object[4];
            r1_ObjectA[0] = "AdView.removeInstance";
            r1_ObjectA[1] = r5_Context;
            r1_ObjectA[2] = r6_Object;
            r1_ObjectA[3] = r0_Method;
            d.a(r1_ObjectA);
            Object[] r2_ObjectA = new Object[2];
            r2_ObjectA[0] = r5_Context;
            r2_ObjectA[1] = r6_Object;
            r0_Method.invoke(null, r2_ObjectA);
        } catch (Exception e) {
            d.b(e);
        }
    }

    private void b(Context r5_Context) {
        try {
            if (b == null) {
                b = b.b(r5_Context, "com.baidu.mobads.remote.AdView");
            }
            Method r0_Method = b.getDeclaredMethod("getLogable", new Class[0]);
            Object[] r1_ObjectA = new Object[2];
            r1_ObjectA[0] = "AdView.getDebugMode";
            r1_ObjectA[1] = r0_Method;
            d.a(r1_ObjectA);
            if (((Boolean) r0_Method.invoke(null, new Object[0])).booleanValue()) {
                a.b = 2;
            }
        } catch (Exception e) {
            d.b(e);
        }
    }

    public static void setAppSec(Context r5_Context, String r6_String) {
        try {
            if (b == null) {
                b = b.b(r5_Context, "com.baidu.mobads.remote.AdView");
            }
            Class r0_Class = b;
            Class[] r2_ClassA = new Class[1];
            r2_ClassA[0] = String.class;
            Method r0_Method = r0_Class.getDeclaredMethod("setAppSec", r2_ClassA);
            Object[] r1_ObjectA = new Object[3];
            r1_ObjectA[0] = "AdView.setAppSec";
            r1_ObjectA[1] = r6_String;
            r1_ObjectA[2] = r0_Method;
            d.a(r1_ObjectA);
            Object[] r2_ObjectA = new Object[1];
            r2_ObjectA[0] = r6_String;
            r0_Method.invoke(null, r2_ObjectA);
        } catch (Exception e) {
            d.b(e);
        }
    }

    public static void setAppSid(Context r5_Context, String r6_String) {
        try {
            if (b == null) {
                b = b.b(r5_Context, "com.baidu.mobads.remote.AdView");
            }
            Class r0_Class = b;
            Class[] r2_ClassA = new Class[1];
            r2_ClassA[0] = String.class;
            Method r0_Method = r0_Class.getDeclaredMethod("setAppSid", r2_ClassA);
            Object[] r1_ObjectA = new Object[3];
            r1_ObjectA[0] = "AdView.setAppSid";
            r1_ObjectA[1] = r6_String;
            r1_ObjectA[2] = r0_Method;
            d.a(r1_ObjectA);
            Object[] r2_ObjectA = new Object[1];
            r2_ObjectA[0] = r6_String;
            r0_Method.invoke(null, r2_ObjectA);
        } catch (Exception e) {
            d.b(e);
        }
    }

    protected void prepareForInterstitial(boolean r6z) {
        try {
            Object[] r0_ObjectA = new Object[XListViewHeader.STATE_REFRESHING];
            r0_ObjectA[0] = "AdView.prepareForInterstitial";
            r0_ObjectA[1] = Boolean.valueOf(r6z);
            d.a(r0_ObjectA);
            Class r0_Class = b;
            Class[] r2_ClassA = new Class[1];
            r2_ClassA[0] = Boolean.TYPE;
            Method r0_Method = r0_Class.getMethod("prepareForInterstitial", r2_ClassA);
            ViewGroup r1_ViewGroup = this.a;
            Object[] r2_ObjectA = new Object[1];
            r2_ObjectA[0] = Boolean.valueOf(r6z);
            r0_Method.invoke(r1_ViewGroup, r2_ObjectA);
        } catch (Exception e) {
            d.b(e);
        }
    }

    public void setAlpha(float r6f) {
        try {
            Class r0_Class = b;
            Class[] r2_ClassA = new Class[1];
            r2_ClassA[0] = Float.TYPE;
            Method r0_Method = r0_Class.getMethod("setAlpha", r2_ClassA);
            Object[] r1_ObjectA = new Object[3];
            r1_ObjectA[0] = "AdView.setAlpha";
            r1_ObjectA[1] = Float.valueOf(r6f);
            r1_ObjectA[2] = r0_Method;
            d.a(r1_ObjectA);
            ViewGroup r1_ViewGroup = this.a;
            Object[] r2_ObjectA = new Object[1];
            r2_ObjectA[0] = Float.valueOf(r6f);
            r0_Method.invoke(r1_ViewGroup, r2_ObjectA);
        } catch (Exception e) {
            d.b(e);
        }
    }

    public void setBackgroundColor(int r6i) {
        try {
            Class r0_Class = b;
            Class[] r2_ClassA = new Class[1];
            r2_ClassA[0] = Integer.TYPE;
            Method r0_Method = r0_Class.getMethod("setBackgroundColor", r2_ClassA);
            Object[] r1_ObjectA = new Object[3];
            r1_ObjectA[0] = "AdView.setBackgroundColor";
            r1_ObjectA[1] = Integer.valueOf(r6i);
            r1_ObjectA[2] = r0_Method;
            d.a(r1_ObjectA);
            ViewGroup r1_ViewGroup = this.a;
            Object[] r2_ObjectA = new Object[1];
            r2_ObjectA[0] = Integer.valueOf(r6i);
            r0_Method.invoke(r1_ViewGroup, r2_ObjectA);
        } catch (Exception e) {
            d.b(e);
        }
    }

    protected void setInterstialListener(InterstitialAdListener r6_InterstitialAdListener, InterstitialAd r7_InterstitialAd) {
        if (r6_InterstitialAdListener == null) {
            throw new IllegalArgumentException();
        } else {
            try {
                Object[] r0_ObjectA = new Object[XListViewHeader.STATE_REFRESHING];
                r0_ObjectA[0] = "AdView.setInterstialListener";
                r0_ObjectA[1] = r6_InterstitialAdListener;
                d.a(r0_ObjectA);
                Class r0_Class = b;
                Class[] r2_ClassA = new Class[1];
                r2_ClassA[0] = Callback.class;
                Method r0_Method = r0_Class.getMethod("setListener", r2_ClassA);
                ViewGroup r1_ViewGroup = this.a;
                Object[] r2_ObjectA = new Object[1];
                r2_ObjectA[0] = new b(r6_InterstitialAdListener, r7_InterstitialAd);
                r0_Method.invoke(r1_ViewGroup, r2_ObjectA);
            } catch (Exception e) {
                d.b(e);
            }
        }
    }

    public void setListener(AdViewListener r6_AdViewListener) {
        if (r6_AdViewListener == null) {
            throw new IllegalArgumentException();
        } else {
            try {
                Object[] r0_ObjectA = new Object[XListViewHeader.STATE_REFRESHING];
                r0_ObjectA[0] = "AdView.setListener";
                r0_ObjectA[1] = r6_AdViewListener;
                d.a(r0_ObjectA);
                Class r0_Class = b;
                Class[] r2_ClassA = new Class[1];
                r2_ClassA[0] = Callback.class;
                Method r0_Method = r0_Class.getMethod("setListener", r2_ClassA);
                ViewGroup r1_ViewGroup = this.a;
                Object[] r2_ObjectA = new Object[1];
                r2_ObjectA[0] = new a(r6_AdViewListener, this);
                r0_Method.invoke(r1_ViewGroup, r2_ObjectA);
            } catch (Exception e) {
                d.b(e);
            }
        }
    }
}