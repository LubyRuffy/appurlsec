package com.baidu.frontia.api;

import android.content.Context;
import com.baidu.frontia.framework.IModule;
import com.baidu.frontia.module.deeplink.FrontiaDeepLinkImpl;

public class FrontiaDeepLink implements IModule {
    private static FrontiaDeepLink b;
    private FrontiaDeepLinkImpl a;

    private FrontiaDeepLink(Context r2_Context) {
        this.a = new FrontiaDeepLinkImpl(r2_Context);
    }

    public static FrontiaDeepLink newInstance(Context r2_Context) {
        if (r2_Context == null) {
            return null;
        }
        if (b == null) {
            synchronized ("FrontiaDeepLink") {
                if (b == null) {
                    b = new FrontiaDeepLink(r2_Context);
                }
            }
        }
        return b;
    }

    public void init(String r2_String) {
        this.a.init(r2_String);
    }

    public void launch(String r2_String) {
        this.a.launch(r2_String);
    }
}