package com.baidu.frontia.api;

import com.baidu.frontia.module.push.FrontiaPushListenerImpl.PushMessageListenerImpl;

class d implements PushMessageListenerImpl {
    final /* synthetic */ d a;

    d(d r1_d) {
        this.a = r1_d;
    }

    public void onFailure(int r2i, String r3_String) {
        if (this.a.c != null) {
            this.a.c.onFailure(r2i, r3_String);
        }
    }

    public void onSuccess(String r2_String) {
        if (this.a.c != null) {
            this.a.c.onSuccess(r2_String);
        }
    }
}