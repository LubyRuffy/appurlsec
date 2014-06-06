package org.apache.cordova;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Constants;
import java.util.HashMap;
import java.util.StringTokenizer;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.LOG;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.image.ImageFetcher;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class InAppBrowser extends CordovaPlugin {
    private static final String EXIT_EVENT = "exit";
    private static final String LOAD_START_EVENT = "loadstart";
    private static final String LOAD_STOP_EVENT = "loadstop";
    private static final String LOCATION = "location";
    protected static final String LOG_TAG = "InAppBrowser";
    private static final String NULL = "null";
    private static final String SELF = "_self";
    private static final String SYSTEM = "_system";
    private long MAX_QUOTA;
    private CallbackContext callbackContext;
    private Dialog dialog;
    private EditText edittext;
    private WebView inAppWebView;
    private boolean showLocationBar;

    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ CordovaWebView val$thatWebView;
        final /* synthetic */ String val$url;

        AnonymousClass_1(String r2_String, CordovaWebView r3_CordovaWebView) {
            this.val$url = r2_String;
            this.val$thatWebView = r3_CordovaWebView;
        }

        private int dpToPixels(int r4i) {
            return (int) TypedValue.applyDimension(1, (float) r4i, InAppBrowser.this.cordova.getActivity().getResources().getDisplayMetrics());
        }

        public void run() {
            InAppBrowser.this.dialog = new Dialog(InAppBrowser.this.cordova.getActivity(), 16973830);
            InAppBrowser.this.dialog.getWindow().getAttributes().windowAnimations = 16973826;
            InAppBrowser.this.dialog.requestWindowFeature(1);
            InAppBrowser.this.dialog.setCancelable(true);
            InAppBrowser.this.dialog.setOnDismissListener(new OnDismissListener() {
                public void onDismiss(DialogInterface r4_DialogInterface) {
                    try {
                        JSONObject r0_JSONObject = new JSONObject();
                        r0_JSONObject.put(QsbkDatabase.TYPE, EXIT_EVENT);
                        AnonymousClass_1.this.this$0.sendUpdate(r0_JSONObject, false);
                    } catch (JSONException e) {
                        Log.d(LOG_TAG, "Should never happen");
                    }
                }
            });
            View r0_View = new LinearLayout(InAppBrowser.this.cordova.getActivity());
            r0_View.setOrientation(1);
            View r1_View = new RelativeLayout(InAppBrowser.this.cordova.getActivity());
            r1_View.setLayoutParams(new LayoutParams(-1, dpToPixels(AdViewUtil.NETWORK_TYPE_FRACTAL)));
            r1_View.setPadding(dpToPixels(XListViewHeader.STATE_REFRESHING), dpToPixels(XListViewHeader.STATE_REFRESHING), dpToPixels(XListViewHeader.STATE_REFRESHING), dpToPixels(XListViewHeader.STATE_REFRESHING));
            r1_View.setHorizontalGravity(XListViewFooter.STATE_NOMORE);
            r1_View.setVerticalGravity(AdViewUtil.NETWORK_TYPE_ADUU);
            View r2_View = new RelativeLayout(InAppBrowser.this.cordova.getActivity());
            r2_View.setLayoutParams(new LayoutParams(-2, -2));
            r2_View.setHorizontalGravity(XListViewFooter.STATE_NOMORE);
            r2_View.setVerticalGravity(Base64.URL_SAFE);
            r2_View.setId(1);
            View r3_View = new Button(InAppBrowser.this.cordova.getActivity());
            ViewGroup.LayoutParams r4_ViewGroup_LayoutParams = new LayoutParams(-2, -1);
            r4_ViewGroup_LayoutParams.addRule(ShareUtils.SHARE_SMS);
            r3_View.setLayoutParams(r4_ViewGroup_LayoutParams);
            r3_View.setContentDescription("Back Button");
            r3_View.setId(XListViewHeader.STATE_REFRESHING);
            r3_View.setText("<");
            r3_View.setOnClickListener(new OnClickListener() {
                public void onClick(View r2_View) {
                    AnonymousClass_1.this.this$0.goBack();
                }
            });
            View r4_View = new Button(InAppBrowser.this.cordova.getActivity());
            ViewGroup.LayoutParams r5_ViewGroup_LayoutParams = new LayoutParams(-2, -1);
            r5_ViewGroup_LayoutParams.addRule(1, XListViewHeader.STATE_REFRESHING);
            r4_View.setLayoutParams(r5_ViewGroup_LayoutParams);
            r4_View.setContentDescription("Forward Button");
            r4_View.setId(XListViewFooter.STATE_NOMORE);
            r4_View.setText(">");
            r4_View.setOnClickListener(new OnClickListener() {
                public void onClick(View r2_View) {
                    AnonymousClass_1.this.this$0.goForward();
                }
            });
            InAppBrowser.this.edittext = new EditText(InAppBrowser.this.cordova.getActivity());
            r5_ViewGroup_LayoutParams = new LayoutParams(-1, -1);
            r5_ViewGroup_LayoutParams.addRule(1, 1);
            r5_ViewGroup_LayoutParams.addRule(0, ShareUtils.SHARE_SMS);
            InAppBrowser.this.edittext.setLayoutParams(r5_ViewGroup_LayoutParams);
            InAppBrowser.this.edittext.setId(XListViewFooter.STATE_NODATA);
            InAppBrowser.this.edittext.setSingleLine(true);
            InAppBrowser.this.edittext.setText(this.val$url);
            InAppBrowser.this.edittext.setInputType(Base64.URL_SAFE);
            InAppBrowser.this.edittext.setImeOptions(XListViewHeader.STATE_REFRESHING);
            InAppBrowser.this.edittext.setInputType(0);
            InAppBrowser.this.edittext.setOnKeyListener(new OnKeyListener() {
                public boolean onKey(View r3_View, int r4i, KeyEvent r5_KeyEvent) {
                    if (r5_KeyEvent.getAction() != 0 || r4i != 66) {
                        return false;
                    }
                    AnonymousClass_1.this.this$0.navigate(AnonymousClass_1.this.this$0.edittext.getText().toString());
                    return true;
                }
            });
            View r5_View = new Button(InAppBrowser.this.cordova.getActivity());
            ViewGroup.LayoutParams r6_ViewGroup_LayoutParams = new LayoutParams(-2, -1);
            r6_ViewGroup_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE);
            r5_View.setLayoutParams(r6_ViewGroup_LayoutParams);
            r4_View.setContentDescription("Close Button");
            r5_View.setId(ShareUtils.SHARE_SMS);
            r5_View.setText("Done");
            r5_View.setOnClickListener(new OnClickListener() {
                public void onClick(View r2_View) {
                    AnonymousClass_1.this.this$0.closeDialog();
                }
            });
            InAppBrowser.this.inAppWebView = new WebView(InAppBrowser.this.cordova.getActivity());
            InAppBrowser.this.inAppWebView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            InAppBrowser.this.inAppWebView.setWebChromeClient(new org.apache.cordova.InAppBrowser.InAppChromeClient());
            InAppBrowser.this.inAppWebView.setWebViewClient(new org.apache.cordova.InAppBrowser.InAppBrowserClient(this.val$thatWebView, InAppBrowser.this.edittext));
            WebSettings r6_WebSettings = InAppBrowser.this.inAppWebView.getSettings();
            r6_WebSettings.setJavaScriptEnabled(true);
            r6_WebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            r6_WebSettings.setBuiltInZoomControls(true);
            r6_WebSettings.setPluginsEnabled(true);
            r6_WebSettings.setDatabaseEnabled(true);
            r6_WebSettings.setDatabasePath(InAppBrowser.this.cordova.getActivity().getApplicationContext().getDir("inAppBrowserDB", 0).getPath());
            r6_WebSettings.setDomStorageEnabled(true);
            InAppBrowser.this.inAppWebView.loadUrl(this.val$url);
            InAppBrowser.this.inAppWebView.setId(ShareUtils.SHARE_COPY);
            InAppBrowser.this.inAppWebView.getSettings().setLoadWithOverviewMode(true);
            InAppBrowser.this.inAppWebView.getSettings().setUseWideViewPort(true);
            InAppBrowser.this.inAppWebView.requestFocus();
            InAppBrowser.this.inAppWebView.requestFocusFromTouch();
            r2_View.addView(r3_View);
            r2_View.addView(r4_View);
            r1_View.addView(r2_View);
            r1_View.addView(InAppBrowser.this.edittext);
            r1_View.addView(r5_View);
            if (InAppBrowser.this.getShowLocationBar()) {
                r0_View.addView(r1_View);
            }
            r0_View.addView(InAppBrowser.this.inAppWebView);
            WindowManager.LayoutParams r1_WindowManager_LayoutParams = new WindowManager.LayoutParams();
            r1_WindowManager_LayoutParams.copyFrom(InAppBrowser.this.dialog.getWindow().getAttributes());
            r1_WindowManager_LayoutParams.width = -1;
            r1_WindowManager_LayoutParams.height = -1;
            InAppBrowser.this.dialog.setContentView(r0_View);
            InAppBrowser.this.dialog.show();
            InAppBrowser.this.dialog.getWindow().setAttributes(r1_WindowManager_LayoutParams);
        }
    }

    public class InAppBrowserClient extends WebViewClient {
        EditText edittext;
        CordovaWebView webView;

        public InAppBrowserClient(CordovaWebView r2_CordovaWebView, EditText r3_EditText) {
            this.webView = r2_CordovaWebView;
            this.edittext = r3_EditText;
        }

        public void onPageFinished(WebView r4_WebView, String r5_String) {
            super.onPageFinished(r4_WebView, r5_String);
            try {
                JSONObject r0_JSONObject = new JSONObject();
                r0_JSONObject.put(QsbkDatabase.TYPE, LOAD_STOP_EVENT);
                r0_JSONObject.put(Constants.PARAM_URL, r5_String);
                InAppBrowser.this.sendUpdate(r0_JSONObject, true);
            } catch (JSONException e) {
                Log.d(LOG_TAG, "Should never happen");
            }
        }

        public void onPageStarted(WebView r4_WebView, String r5_String, Bitmap r6_Bitmap) {
            super.onPageStarted(r4_WebView, r5_String, r6_Bitmap);
            JSONObject r0_JSONObject;
            if (r5_String.startsWith("http:") || r5_String.startsWith("https:") || r5_String.startsWith("file:")) {
                if (r5_String.equals(this.edittext.getText().toString())) {
                    this.edittext.setText(r5_String);
                }
                try {
                    r0_JSONObject = new JSONObject();
                    r0_JSONObject.put(QsbkDatabase.TYPE, LOAD_START_EVENT);
                    r0_JSONObject.put(Constants.PARAM_URL, r5_String);
                    InAppBrowser.this.sendUpdate(r0_JSONObject, true);
                } catch (JSONException e) {
                    Log.d(LOG_TAG, "Should never happen");
                }
            } else {
                r5_String = HttpUtils.http + r5_String;
                if (r5_String.equals(this.edittext.getText().toString())) {
                    r0_JSONObject = new JSONObject();
                    r0_JSONObject.put(QsbkDatabase.TYPE, LOAD_START_EVENT);
                    r0_JSONObject.put(Constants.PARAM_URL, r5_String);
                    InAppBrowser.this.sendUpdate(r0_JSONObject, true);
                } else {
                    this.edittext.setText(r5_String);
                    r0_JSONObject = new JSONObject();
                    r0_JSONObject.put(QsbkDatabase.TYPE, LOAD_START_EVENT);
                    r0_JSONObject.put(Constants.PARAM_URL, r5_String);
                    InAppBrowser.this.sendUpdate(r0_JSONObject, true);
                }
            }
        }
    }

    public class InAppChromeClient extends WebChromeClient {
        public void onExceededDatabaseQuota(String r6_String, String r7_String, long r8j, long r10j, long r12j, QuotaUpdater r14_QuotaUpdater) {
            String r0_String = LOG_TAG;
            Object[] r2_ObjectA = new Object[3];
            r2_ObjectA[0] = Long.valueOf(r10j);
            r2_ObjectA[1] = Long.valueOf(r8j);
            r2_ObjectA[2] = Long.valueOf(r12j);
            LOG.d(r0_String, "onExceededDatabaseQuota estimatedSize: %d  currentQuota: %d  totalUsedQuota: %d", r2_ObjectA);
            if (r10j < InAppBrowser.this.MAX_QUOTA) {
                r0_String = LOG_TAG;
                r2_ObjectA = new Object[1];
                r2_ObjectA[0] = Long.valueOf(r10j);
                LOG.d(r0_String, "calling quotaUpdater.updateQuota newQuota: %d", r2_ObjectA);
                r14_QuotaUpdater.updateQuota(r10j);
            } else {
                r14_QuotaUpdater.updateQuota(r8j);
            }
        }
    }

    public InAppBrowser() {
        this.MAX_QUOTA = 104857600;
        this.showLocationBar = true;
    }

    private void closeDialog() {
        try {
            JSONObject r0_JSONObject = new JSONObject();
            r0_JSONObject.put(QsbkDatabase.TYPE, EXIT_EVENT);
            sendUpdate(r0_JSONObject, false);
        } catch (JSONException e) {
            Log.d(LOG_TAG, "Should never happen");
        }
        if (this.dialog != null) {
            this.dialog.dismiss();
        }
    }

    private boolean getShowLocationBar() {
        return this.showLocationBar;
    }

    private void goBack() {
        if (this.inAppWebView.canGoBack()) {
            this.inAppWebView.goBack();
        }
    }

    private void goForward() {
        if (this.inAppWebView.canGoForward()) {
            this.inAppWebView.goForward();
        }
    }

    private void navigate(String r4_String) {
        ((InputMethodManager) this.cordova.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.edittext.getWindowToken(), 0);
        if (r4_String.startsWith(ImageFetcher.HTTP_CACHE_DIR) || r4_String.startsWith("file:")) {
            this.inAppWebView.loadUrl(r4_String);
        } else {
            this.inAppWebView.loadUrl(HttpUtils.http + r4_String);
        }
        this.inAppWebView.requestFocus();
    }

    private HashMap<String, Boolean> parseFeature(String r6_String) {
        if (r6_String.equals(NULL)) {
            return null;
        }
        HashMap<String, Boolean> r1_HashMap_String__Boolean = new HashMap();
        StringTokenizer r2_StringTokenizer = new StringTokenizer(r6_String, ",");
        while (r2_StringTokenizer.hasMoreElements()) {
            StringTokenizer r0_StringTokenizer = new StringTokenizer(r2_StringTokenizer.nextToken(), "=");
            if (r0_StringTokenizer.hasMoreElements()) {
                r1_HashMap_String__Boolean.put(r0_StringTokenizer.nextToken(), r0_StringTokenizer.nextToken().equals("no") ? Boolean.FALSE : Boolean.TRUE);
            }
        }
        return r1_HashMap_String__Boolean;
    }

    private void sendUpdate(JSONObject r3_JSONObject, boolean r4z) {
        PluginResult r0_PluginResult = new PluginResult(Status.OK, r3_JSONObject);
        r0_PluginResult.setKeepCallback(r4z);
        this.callbackContext.sendPluginResult(r0_PluginResult);
    }

    private String updateUrl(String r6_String) {
        return Uri.parse(r6_String).isRelative() ? this.webView.getUrl().substring(0, this.webView.getUrl().lastIndexOf("/") + 1) + r6_String : r6_String;
    }

    public boolean execute(String r10_String, JSONArray r11_JSONArray, CallbackContext r12_CallbackContext) throws JSONException {
        Status r2_Status = Status.OK;
        String r0_String = RContactStorage.PRIMARY_KEY;
        this.callbackContext = r12_CallbackContext;
        Status r1_Status;
        try {
            Status r1_Status_2;
            if (r10_String.equals("open")) {
                String r3_String = r11_JSONArray.getString(0);
                String r1_String = r11_JSONArray.optString(1);
                HashMap r4_HashMap;
                Intent r1_Intent;
                if (r1_String == null || r1_String.equals(RContactStorage.PRIMARY_KEY) || r1_String.equals(NULL)) {
                    r1_String = SELF;
                    r4_HashMap = parseFeature(r11_JSONArray.optString(XListViewHeader.STATE_REFRESHING));
                    Log.d(LOG_TAG, "target = " + r1_String);
                    r3_String = updateUrl(r3_String);
                    if (SELF.equals(r1_String)) {
                        if (SYSTEM.equals(r1_String)) {
                            Log.d(LOG_TAG, "in blank");
                            r0_String = showWebPage(r3_String, r4_HashMap);
                        } else {
                            Log.d(LOG_TAG, "in system");
                            r0_String = openExternal(r3_String);
                        }
                    } else {
                        Log.d(LOG_TAG, "in self");
                        if (r3_String.startsWith("file://") || r3_String.startsWith("javascript:") || Config.isUrlWhiteListed(r3_String)) {
                            this.webView.loadUrl(r3_String);
                        } else if (r3_String.startsWith("tel:")) {
                            r0_String = showWebPage(r3_String, r4_HashMap);
                        } else {
                            try {
                                r1_Intent = new Intent("android.intent.action.DIAL");
                                r1_Intent.setData(Uri.parse(r3_String));
                                this.cordova.getActivity().startActivity(r1_Intent);
                            } catch (ActivityNotFoundException e) {
                                LOG.e(LOG_TAG, "Error dialing " + r3_String + ": " + e.toString());
                            }
                        }
                    }
                    r1_Status_2 = r2_Status;
                } else {
                    r4_HashMap = parseFeature(r11_JSONArray.optString(XListViewHeader.STATE_REFRESHING));
                    Log.d(LOG_TAG, "target = " + r1_String);
                    r3_String = updateUrl(r3_String);
                    if (SELF.equals(r1_String)) {
                        if (SYSTEM.equals(r1_String)) {
                            Log.d(LOG_TAG, "in blank");
                            r0_String = showWebPage(r3_String, r4_HashMap);
                        } else {
                            Log.d(LOG_TAG, "in system");
                            r0_String = openExternal(r3_String);
                        }
                    } else {
                        Log.d(LOG_TAG, "in self");
                        if (r3_String.startsWith("file://") || r3_String.startsWith("javascript:") || Config.isUrlWhiteListed(r3_String)) {
                            this.webView.loadUrl(r3_String);
                        } else if (r3_String.startsWith("tel:")) {
                            r0_String = showWebPage(r3_String, r4_HashMap);
                        } else {
                            r1_Intent = new Intent("android.intent.action.DIAL");
                            r1_Intent.setData(Uri.parse(r3_String));
                            this.cordova.getActivity().startActivity(r1_Intent);
                        }
                    }
                    r1_Status_2 = r2_Status;
                }
            } else if (r10_String.equals("close")) {
                closeDialog();
                PluginResult r1_PluginResult = new PluginResult(Status.OK);
                r1_PluginResult.setKeepCallback(false);
                this.callbackContext.sendPluginResult(r1_PluginResult);
                r1_Status_2 = r2_Status;
            } else {
                r1_Status_2 = Status.INVALID_ACTION;
            }
            PluginResult r2_PluginResult = new PluginResult(r1_Status_2, r0_String);
            r2_PluginResult.setKeepCallback(true);
            this.callbackContext.sendPluginResult(r2_PluginResult);
        } catch (JSONException e_2) {
            this.callbackContext.sendPluginResult(new PluginResult(Status.JSON_EXCEPTION));
        }
        return true;
    }

    public String openExternal(String r5_String) {
        try {
            Intent r0_Intent = new Intent("android.intent.action.VIEW");
            r0_Intent.setData(Uri.parse(r5_String));
            this.cordova.getActivity().startActivity(r0_Intent);
            return RContactStorage.PRIMARY_KEY;
        } catch (ActivityNotFoundException e) {
            ActivityNotFoundException r0_ActivityNotFoundException = e;
            Log.d(LOG_TAG, "InAppBrowser: Error loading url " + r5_String + ":" + r0_ActivityNotFoundException.toString());
            return r0_ActivityNotFoundException.toString();
        }
    }

    public String showWebPage(String r3_String, HashMap<String, Boolean> r4_HashMap_String__Boolean) {
        this.showLocationBar = true;
        if (r4_HashMap_String__Boolean != null) {
            this.showLocationBar = ((Boolean) r4_HashMap_String__Boolean.get(LOCATION)).booleanValue();
        }
        this.cordova.getActivity().runOnUiThread(new AnonymousClass_1(r3_String, this.webView));
        return RContactStorage.PRIMARY_KEY;
    }
}