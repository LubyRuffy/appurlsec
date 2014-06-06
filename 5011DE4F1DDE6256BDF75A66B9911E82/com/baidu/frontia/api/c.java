package com.baidu.frontia.api;

import com.baidu.frontia.module.push.FrontiaPushListenerImpl.DescribeMessageResult;
import com.baidu.frontia.module.push.FrontiaPushListenerImpl.ListMessageListenerImpl;
import java.util.ArrayList;
import java.util.List;

class c implements ListMessageListenerImpl {
    final /* synthetic */ c a;

    c(c r1_c) {
        this.a = r1_c;
    }

    public void onFailure(int r2i, String r3_String) {
        if (this.a.c != null) {
            this.a.c.onFailure(r2i, r3_String);
        }
    }

    public void onSuccess(List<DescribeMessageResult> r5_List_DescribeMessageResult) {
        if (this.a.c != null) {
            List r2_List = new ArrayList();
            int r1i = 0;
            while (r1i < r5_List_DescribeMessageResult.size()) {
                r2_List.add(new FrontiaPushListener.DescribeMessageResult((DescribeMessageResult) r5_List_DescribeMessageResult.get(r1i)));
                r1i++;
            }
            this.a.c.onSuccess(r2_List);
        }
    }
}