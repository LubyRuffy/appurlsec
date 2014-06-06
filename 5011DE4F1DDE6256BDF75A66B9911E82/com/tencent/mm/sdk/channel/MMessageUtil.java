package com.tencent.mm.sdk.channel;

import com.tencent.mm.algorithm.MD5;
import com.tencent.mm.sdk.Build;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;

public class MMessageUtil {
    private MMessageUtil() {
    }

    static byte[] a(String r3_String, String r4_String) {
        StringBuffer r0_StringBuffer = new StringBuffer();
        if (r3_String != null) {
            r0_StringBuffer.append(r3_String);
        }
        r0_StringBuffer.append(Build.SDK_INT);
        r0_StringBuffer.append(r4_String);
        r0_StringBuffer.append("mMcShCsTr");
        return MD5.getMessageDigest(r0_StringBuffer.toString().substring(1, REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY).getBytes()).getBytes();
    }
}