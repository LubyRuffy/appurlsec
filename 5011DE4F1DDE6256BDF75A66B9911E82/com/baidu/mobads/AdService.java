package com.baidu.mobads;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.baidu.mobads.a.a;
import com.baidu.mobads.a.d;
import com.tencent.mm.sdk.contact.RContactStorage;

public class AdService {
    protected static String channelId;
    protected static int instanceCount;
    private final AdView a;

    static {
        channelId = RContactStorage.PRIMARY_KEY;
        instanceCount = -1;
    }

    public AdService(Context r8_Context, ViewGroup r9_ViewGroup, LayoutParams r10_LayoutParams, AdViewListener r11_AdViewListener) {
        this(r8_Context, r9_ViewGroup, r10_LayoutParams, r11_AdViewListener, AdSize.Banner, RContactStorage.PRIMARY_KEY);
    }

    public AdService(Context r3_Context, ViewGroup r4_ViewGroup, LayoutParams r5_LayoutParams, AdViewListener r6_AdViewListener, AdSize r7_AdSize, String r8_String) {
        a.g = true;
        if (r3_Context == null || r4_ViewGroup == null || r5_LayoutParams == null || r6_AdViewListener == null || r7_AdSize == null) {
            throw new IllegalArgumentException("One of arguments is null");
        } else {
            Object r0_Object = AdView.a(r3_Context);
            if (r0_Object != null) {
                AdView.a(r3_Context, r0_Object);
            }
            this.a = new AdView(r3_Context, false, r7_AdSize, r8_String);
            this.a.setListener(r6_AdViewListener);
            a(r4_ViewGroup, r5_LayoutParams);
            instanceCount++;
        }
    }

    private void a(ViewGroup r3_ViewGroup, LayoutParams r4_LayoutParams) {
        try {
            if (this.a.getParent() != r3_ViewGroup) {
                if (this.a.getParent() != null) {
                    ((ViewGroup) this.a.getParent()).removeView(this.a);
                }
                r3_ViewGroup.addView(this.a, r4_LayoutParams);
            }
        } catch (Exception e) {
            d.b(e);
        }
    }

    public static void setChannelId(String r0_String) {
        channelId = r0_String;
    }
}