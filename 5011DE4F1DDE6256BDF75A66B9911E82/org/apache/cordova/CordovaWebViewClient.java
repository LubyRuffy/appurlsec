package org.apache.cordova;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.baidu.location.BDLocation;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Constants;
import java.util.Hashtable;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.LOG;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;

public class CordovaWebViewClient extends WebViewClient {
    private static final String CORDOVA_EXEC_URL_PREFIX = "http://cdv_exec/";
    private static final String TAG = "Cordova";
    CordovaWebView appView;
    private Hashtable<String, AuthenticationToken> authenticationTokens;
    CordovaInterface cordova;
    private boolean doClearHistory;

    public CordovaWebViewClient(CordovaInterface r2_CordovaInterface) {
        this.doClearHistory = false;
        this.authenticationTokens = new Hashtable();
        this.cordova = r2_CordovaInterface;
    }

    public CordovaWebViewClient(CordovaInterface r2_CordovaInterface, CordovaWebView r3_CordovaWebView) {
        this.doClearHistory = false;
        this.authenticationTokens = new Hashtable();
        this.cordova = r2_CordovaInterface;
        this.appView = r3_CordovaWebView;
    }

    private void handleExecUrl(String r7_String) {
        int r0i = CORDOVA_EXEC_URL_PREFIX.length();
        int r1i = r7_String.indexOf(AdViewUtil.NETWORK_TYPE_WQ, r0i + 1);
        int r2i = r7_String.indexOf(AdViewUtil.NETWORK_TYPE_WQ, r1i + 1);
        int r3i = r7_String.indexOf(AdViewUtil.NETWORK_TYPE_WQ, r2i + 1);
        if (r0i == -1 || r1i == -1 || r2i == -1 || r3i == -1) {
            Log.e(TAG, "Could not decode URL command: " + r7_String);
        } else {
            this.appView.pluginManager.exec(r7_String.substring(r0i, r1i), r7_String.substring(r1i + 1, r2i), r7_String.substring(r2i + 1, r3i), r7_String.substring(r3i + 1));
        }
    }

    public void clearAuthenticationTokens() {
        this.authenticationTokens.clear();
    }

    public void doUpdateVisitedHistory(WebView r2_WebView, String r3_String, boolean r4z) {
        if (!this.appView.peekAtUrlStack().equals(r3_String)) {
            this.appView.pushUrl(r3_String);
        }
    }

    public AuthenticationToken getAuthenticationToken(String r3_String, String r4_String) {
        AuthenticationToken r0_AuthenticationToken = (AuthenticationToken) this.authenticationTokens.get(r3_String.concat(r4_String));
        if (r0_AuthenticationToken != null) {
            return r0_AuthenticationToken;
        }
        r0_AuthenticationToken = (AuthenticationToken) this.authenticationTokens.get(r3_String);
        if (r0_AuthenticationToken == null) {
            r0_AuthenticationToken = (AuthenticationToken) this.authenticationTokens.get(r4_String);
        }
        return r0_AuthenticationToken == null ? (AuthenticationToken) this.authenticationTokens.get(RContactStorage.PRIMARY_KEY) : r0_AuthenticationToken;
    }

    public void onPageFinished(WebView r5_WebView, String r6_String) {
        super.onPageFinished(r5_WebView, r6_String);
        LOG.d(TAG, "onPageFinished(" + r6_String + ")");
        if (this.doClearHistory) {
            r5_WebView.clearHistory();
            this.doClearHistory = false;
        }
        CordovaWebView r0_CordovaWebView = this.appView;
        r0_CordovaWebView.loadUrlTimeout++;
        if (!r6_String.equals("about:blank")) {
            LOG.d(TAG, "Trying to fire onNativeReady");
            this.appView.loadUrl("javascript:try{ cordova.require('cordova/channel').onNativeReady.fire();}catch(e){_nativeReady = true;}");
            this.appView.postMessage("onNativeReady", null);
        }
        this.appView.postMessage("onPageFinished", r6_String);
        if (this.appView.getVisibility() == XListViewFooter.STATE_NODATA) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(2000);
                        CordovaWebViewClient.this.cordova.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                AnonymousClass_1.this.this$0.appView.postMessage("spinner", "stop");
                            }
                        });
                    } catch (InterruptedException e) {
                    }
                }
            }).start();
        }
        if (r6_String.equals("about:blank")) {
            this.appView.postMessage("exit", null);
        }
    }

    public void onPageStarted(WebView r3_WebView, String r4_String, Bitmap r5_Bitmap) {
        if (!this.appView.useBrowserHistory) {
            r3_WebView.clearHistory();
            this.doClearHistory = true;
        }
        this.appView.jsMessageQueue.reset();
        this.appView.postMessage("onPageStarted", r4_String);
        if (this.appView.pluginManager != null) {
            this.appView.pluginManager.onReset();
        }
    }

    public void onReceivedError(WebView r6_WebView, int r7i, String r8_String, String r9_String) {
        String r0_String = TAG;
        Object[] r2_ObjectA = new Object[3];
        r2_ObjectA[0] = Integer.valueOf(r7i);
        r2_ObjectA[1] = r8_String;
        r2_ObjectA[2] = r9_String;
        LOG.d(r0_String, "CordovaWebViewClient.onReceivedError: Error code=%s Description=%s URL=%s", r2_ObjectA);
        CordovaWebView r0_CordovaWebView = this.appView;
        r0_CordovaWebView.loadUrlTimeout++;
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put("errorCode", r7i);
            r1_JSONObject.put(Constants.PARAM_COMMENT, r8_String);
            r1_JSONObject.put(Constants.PARAM_URL, r9_String);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.appView.postMessage("onReceivedError", r1_JSONObject);
    }

    public void onReceivedHttpAuthRequest(WebView r3_WebView, HttpAuthHandler r4_HttpAuthHandler, String r5_String, String r6_String) {
        AuthenticationToken r0_AuthenticationToken = getAuthenticationToken(r5_String, r6_String);
        if (r0_AuthenticationToken != null) {
            r4_HttpAuthHandler.proceed(r0_AuthenticationToken.getUserName(), r0_AuthenticationToken.getPassword());
        } else {
            super.onReceivedHttpAuthRequest(r3_WebView, r4_HttpAuthHandler, r5_String, r6_String);
        }
    }

    public void onReceivedSslError(WebView r4_WebView, SslErrorHandler r5_SslErrorHandler, SslError r6_SslError) {
        try {
            if ((this.cordova.getActivity().getPackageManager().getApplicationInfo(this.cordova.getActivity().getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).flags & 2) != 0) {
                r5_SslErrorHandler.proceed();
            } else {
                super.onReceivedSslError(r4_WebView, r5_SslErrorHandler, r6_SslError);
            }
        } catch (NameNotFoundException e) {
            super.onReceivedSslError(r4_WebView, r5_SslErrorHandler, r6_SslError);
        }
    }

    public AuthenticationToken removeAuthenticationToken(String r3_String, String r4_String) {
        return (AuthenticationToken) this.authenticationTokens.remove(r3_String.concat(r4_String));
    }

    public void setAuthenticationToken(AuthenticationToken r3_AuthenticationToken, String r4_String, String r5_String) {
        if (r4_String == null) {
            r4_String = RContactStorage.PRIMARY_KEY;
        }
        if (r5_String == null) {
            r5_String = RContactStorage.PRIMARY_KEY;
        }
        this.authenticationTokens.put(r4_String.concat(r5_String), r3_AuthenticationToken);
    }

    public void setWebView(CordovaWebView r1_CordovaWebView) {
        this.appView = r1_CordovaWebView;
    }

    public boolean shouldOverrideUrlLoading(WebView r6_WebView, String r7_String) {
        if (this.appView.pluginManager != null && this.appView.pluginManager.onOverrideUrlLoading(r7_String)) {
            return true;
        }
        Intent r0_Intent;
        if (r7_String.startsWith("tel:")) {
            try {
                r0_Intent = new Intent("android.intent.action.DIAL");
                r0_Intent.setData(Uri.parse(r7_String));
                this.cordova.getActivity().startActivity(r0_Intent);
            } catch (ActivityNotFoundException e) {
                LOG.e(TAG, "Error dialing " + r7_String + ": " + e.toString());
            }
            return true;
        } else if (r7_String.startsWith("geo:")) {
            try {
                r0_Intent = new Intent("android.intent.action.VIEW");
                r0_Intent.setData(Uri.parse(r7_String));
                this.cordova.getActivity().startActivity(r0_Intent);
            } catch (ActivityNotFoundException e_2) {
                LOG.e(TAG, "Error showing map " + r7_String + ": " + e_2.toString());
            }
            return true;
        } else if (r7_String.startsWith("mailto:")) {
            try {
                r0_Intent = new Intent("android.intent.action.VIEW");
                r0_Intent.setData(Uri.parse(r7_String));
                this.cordova.getActivity().startActivity(r0_Intent);
            } catch (ActivityNotFoundException e_3) {
                LOG.e(TAG, "Error sending email " + r7_String + ": " + e_3.toString());
            }
            return true;
        } else if (r7_String.startsWith("sms:")) {
            String r0_String;
            try {
                String r0_String_2;
                Intent r1_Intent = new Intent("android.intent.action.VIEW");
                int r0i = r7_String.indexOf(BDLocation.TypeNetWorkException);
                if (r0i == -1) {
                    r0_String_2 = r7_String.substring(XListViewFooter.STATE_NODATA);
                } else {
                    r0_String_2 = r7_String.substring(XListViewFooter.STATE_NODATA, r0i);
                    String r2_String = Uri.parse(r7_String).getQuery();
                    if (r2_String == null || (!r2_String.startsWith("body="))) {
                        r1_Intent.setData(Uri.parse("sms:" + r0_String_2));
                        r1_Intent.putExtra("address", r0_String_2);
                        r1_Intent.setType("vnd.android-dir/mms-sms");
                        this.cordova.getActivity().startActivity(r1_Intent);
                    } else {
                        r1_Intent.putExtra("sms_body", r2_String.substring(ShareUtils.SHARE_SMS));
                    }
                }
                r1_Intent.setData(Uri.parse("sms:" + r0_String_2));
                r1_Intent.putExtra("address", r0_String_2);
                r1_Intent.setType("vnd.android-dir/mms-sms");
                this.cordova.getActivity().startActivity(r1_Intent);
            } catch (ActivityNotFoundException e_4) {
                LOG.e(TAG, "Error sending sms " + r7_String + ":" + e_4.toString());
            }
            return true;
        } else if (r7_String.startsWith("file://") || r7_String.startsWith("data:") || r7_String.indexOf(this.appView.baseUrl) == 0 || Config.isUrlWhiteListed(r7_String)) {
            if (this.appView.useBrowserHistory || r7_String.startsWith("data:")) {
                return false;
            }
            this.appView.loadUrl(r7_String);
            return true;
        } else {
            try {
                r0_Intent = new Intent("android.intent.action.VIEW");
                r0_Intent.setData(Uri.parse(r7_String));
                this.cordova.getActivity().startActivity(r0_Intent);
            } catch (ActivityNotFoundException e_5) {
                LOG.e(TAG, "Error loading url " + r7_String, e_5);
            }
            return true;
        }
    }
}