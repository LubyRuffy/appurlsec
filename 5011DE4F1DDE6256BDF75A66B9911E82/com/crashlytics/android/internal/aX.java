package com.crashlytics.android.internal;

import qsbk.app.thirdparty.UsersAPI;
import qsbk.app.thirdparty.net.HttpManager;

// compiled from: SourceFile
public enum ax {
    GET,
    POST,
    PUT,
    DELETE;


    static {
        a = new ax(HttpManager.HTTPMETHOD_GET, 0);
        b = new ax(UsersAPI.HTTPMETHOD_POST, 1);
        c = new ax("PUT", 2);
        d = new ax("DELETE", 3);
        ax[] r0_axA = new ax[4];
        r0_axA[0] = a;
        r0_axA[1] = b;
        r0_axA[2] = c;
        r0_axA[3] = d;
        e = r0_axA;
    }
}