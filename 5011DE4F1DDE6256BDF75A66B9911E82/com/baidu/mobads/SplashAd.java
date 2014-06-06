package com.baidu.mobads;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.ViewGroup;
import com.baidu.mobads.a.b;
import com.baidu.mobads.a.d;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.lang.reflect.Constructor;
import qsbk.app.push.Utils;

public final class SplashAd {
    private static Class<?> a;
    private Object b;
    private SplashAdListener c;

    class a implements Callback {
        private SplashAdListener b;

        public a(SplashAdListener r2_SplashAdListener) {
            this.b = r2_SplashAdListener;
        }

        public boolean handleMessage(Message r7_Message) {
            try {
                Bundle r0_Bundle = r7_Message.getData();
                String r1_String = r0_Bundle.getString(Utils.RESPONSE_METHOD);
                Object[] r2_ObjectA = new Object[2];
                r2_ObjectA[0] = "SplashAd.setSplashListener handleMessage";
                r2_ObjectA[1] = r0_Bundle;
                d.a(r2_ObjectA);
                if ("onAdDismissed".equals(r1_String)) {
                    this.b.onAdDismissed();
                    return false;
                } else if ("onAdFailed".equals(r1_String)) {
                    this.b.onAdFailed(r0_Bundle.getString("p_reason"));
                    this.b.onAdDismissed();
                    return false;
                } else {
                    if ("onSplashAdPresent".equals(r1_String)) {
                        this.b.onAdPresent();
                    }
                    return false;
                }
            } catch (Exception e) {
                d.b(e);
            }
        }
    }

    public SplashAd(Context r2_Context, ViewGroup r3_ViewGroup, SplashAdListener r4_SplashAdListener) {
        this(r2_Context, r3_ViewGroup, r4_SplashAdListener, RContactStorage.PRIMARY_KEY);
    }

    public SplashAd(Context r6_Context, ViewGroup r7_ViewGroup, SplashAdListener r8_SplashAdListener, String r9_String) {
        this.c = new d(this);
        try {
            if (a == null) {
                a = b.b(r6_Context, "com.baidu.mobads.remote.SplashAd");
            }
            if (r8_SplashAdListener != null) {
                this.c = r8_SplashAdListener;
            }
            a r0_a = new a(this.c);
            Class r1_Class = a;
            Class[] r2_ClassA = new Class[4];
            r2_ClassA[0] = Context.class;
            r2_ClassA[1] = ViewGroup.class;
            r2_ClassA[2] = Callback.class;
            r2_ClassA[3] = String.class;
            Constructor r1_Constructor = r1_Class.getConstructor(r2_ClassA);
            Object[] r2_ObjectA = new Object[4];
            r2_ObjectA[0] = r6_Context;
            r2_ObjectA[1] = r7_ViewGroup;
            r2_ObjectA[2] = r0_a;
            r2_ObjectA[3] = r9_String;
            this.b = r1_Constructor.newInstance(r2_ObjectA);
        } catch (Exception e) {
            d.b(e);
        }
    }

    public static void setAppSec(Context r0_Context, String r1_String) {
        AdView.setAppSec(r0_Context, r1_String);
    }

    public static void setAppSid(Context r0_Context, String r1_String) {
        AdView.setAppSid(r0_Context, r1_String);
    }
}