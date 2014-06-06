package com.baidu.frontia.api;

import com.baidu.frontia.module.push.FrontiaPushListenerImpl.DescribeMessageListenerImpl;
import com.baidu.frontia.module.push.FrontiaPushListenerImpl.DescribeMessageResult;

class b implements DescribeMessageListenerImpl {
    final /* synthetic */ b a;

    b(b r1_b) {
        this.a = r1_b;
    }

    public void onFailure(int r2i, String r3_String) {
        if (this.a.c != null) {
            this.a.c.onFailure(r2i, r3_String);
        }
    }

    public void onSuccess(DescribeMessageResult r3_DescribeMessageResult) {
        if (this.a.c != null) {
            this.a.c.onSuccess(new FrontiaPushListener.DescribeMessageResult(r3_DescribeMessageResult));
        }
    }
}