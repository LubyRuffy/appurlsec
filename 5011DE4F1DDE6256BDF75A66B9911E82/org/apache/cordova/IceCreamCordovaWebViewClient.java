package org.apache.cordova;

import android.net.Uri;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.IOException;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.LOG;
import qsbk.app.bean.Base;
import qsbk.app.widget.listview.XListViewHeader;

public class IceCreamCordovaWebViewClient extends CordovaWebViewClient {
    public IceCreamCordovaWebViewClient(CordovaInterface r1_CordovaInterface) {
        super(r1_CordovaInterface);
    }

    public IceCreamCordovaWebViewClient(CordovaInterface r1_CordovaInterface, CordovaWebView r2_CordovaWebView) {
        super(r1_CordovaInterface, r2_CordovaWebView);
    }

    private WebResourceResponse generateWebResourceResponse(String r6_String) {
        String r0_String = "file:///android_asset/";
        if (r6_String.startsWith("file:///android_asset/")) {
            r0_String = r6_String.replaceFirst("file:///android_asset/", RContactStorage.PRIMARY_KEY);
            if (r0_String.contains("?")) {
                r0_String = r0_String.split("\\?")[0];
            } else if (r0_String.contains("#")) {
                r0_String = r0_String.split("#")[0];
            }
            try {
                return new WebResourceResponse(r0_String.endsWith(".html") ? "text/html" : null, Base.UTF8, this.cordova.getActivity().getAssets().open(Uri.parse(r0_String).getPath(), XListViewHeader.STATE_REFRESHING));
            } catch (IOException e) {
                Throwable r0_Throwable = e;
                LOG.e("generateWebResourceResponse", r0_Throwable.getMessage(), r0_Throwable);
            }
        }
        return null;
    }

    public WebResourceResponse shouldInterceptRequest(WebView r2_WebView, String r3_String) {
        return (r3_String.contains("?") || r3_String.contains("#")) ? generateWebResourceResponse(r3_String) : super.shouldInterceptRequest(r2_WebView, r3_String);
    }
}