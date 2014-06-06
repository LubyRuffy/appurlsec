package qsbk.app.thirdparty.net;

import qsbk.app.thirdparty.ThirdPartyParameters;

public class AsyncWeiboRunner {
    public static void request(String r6_String, ThirdPartyParameters r7_ThirdPartyParameters, String r8_String, RequestListener r9_RequestListener) {
        new a("qbk-AsyscWeibo", r6_String, r8_String, r7_ThirdPartyParameters, r9_RequestListener).start();
    }
}