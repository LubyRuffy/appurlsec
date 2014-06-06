package com.baidu.frontia.api;

import com.baidu.frontia.module.push.FrontiaPushListenerImpl.CommonMessageListenerImpl;

class a implements CommonMessageListenerImpl {
    final /* synthetic */ a a;

    a(a r1_a) {
        this.a = r1_a;
    }

    public void onFailure(int r2i, String r3_String) {
        if (this.a.c != null) {
            this.a.c.onFailure(r2i, r3_String);
        }
    }

    public void onSuccess() {
        if (this.a.c != null) {
            this.a.c.onSuccess();
        }
    }
}