package org.apache.cordova;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebHistoryItem;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.FrameLayout.LayoutParams;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.LVBuffer;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.tauth.Constants;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.LOG;
import org.apache.cordova.api.PluginManager;
import org.apache.cordova.api.PluginResult;
import qsbk.app.activity.EditInfoEntranceActivity.EDIT_TYPE;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;

public class CordovaWebView extends WebView {
    static final LayoutParams COVER_SCREEN_GRAVITY_CENTER;
    public static final String TAG = "CordovaWebView";
    String baseUrl;
    private boolean bound;
    private CordovaChromeClient chromeClient;
    private CordovaInterface cordova;
    ExposedJsApi exposedJsApi;
    private boolean handleButton;
    NativeToJsMessageQueue jsMessageQueue;
    private ArrayList<Integer> keyDownCodes;
    private ArrayList<Integer> keyUpCodes;
    private long lastMenuEventTime;
    int loadUrlTimeout;
    private View mCustomView;
    private CustomViewCallback mCustomViewCallback;
    private ActivityResult mResult;
    private boolean paused;
    public PluginManager pluginManager;
    private BroadcastReceiver receiver;
    private String url;
    private Stack<String> urls;
    boolean useBrowserHistory;
    CordovaWebViewClient viewClient;

    class AnonymousClass_2 implements Runnable {
        final /* synthetic */ String val$url;

        AnonymousClass_2(String r3_String) {
            this.val$url = r3_String;
        }

        public void run() {
            CordovaWebView.this.stopLoading();
            LOG.e(TAG, "CordovaWebView: TIMEOUT ERROR!");
            if (CordovaWebView.this.viewClient != null) {
                CordovaWebView.this.viewClient.onReceivedError(CordovaWebView.this, Constants.ERROR_UNKNOWN, "The connection to the server was unsuccessful.", this.val$url);
            }
        }
    }

    class AnonymousClass_3 implements Runnable {
        final /* synthetic */ int val$currentLoadUrlTimeout;
        final /* synthetic */ Runnable val$loadError;
        final /* synthetic */ int val$loadUrlTimeoutValue;
        final /* synthetic */ CordovaWebView val$me;

        AnonymousClass_3(int r2i, CordovaWebView r3_CordovaWebView, int r4i, Runnable r5_Runnable) {
            this.val$loadUrlTimeoutValue = r2i;
            this.val$me = r3_CordovaWebView;
            this.val$currentLoadUrlTimeout = r4i;
            this.val$loadError = r5_Runnable;
        }

        public void run() {
            try {
                synchronized (this) {
                    wait((long) this.val$loadUrlTimeoutValue);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.val$me.loadUrlTimeout == this.val$currentLoadUrlTimeout) {
                this.val$me.cordova.getActivity().runOnUiThread(this.val$loadError);
            }
        }
    }

    class AnonymousClass_4 implements Runnable {
        final /* synthetic */ CordovaWebView val$me;
        final /* synthetic */ Runnable val$timeoutCheck;
        final /* synthetic */ String val$url;

        AnonymousClass_4(Runnable r2_Runnable, CordovaWebView r3_CordovaWebView, String r4_String) {
            this.val$timeoutCheck = r2_Runnable;
            this.val$me = r3_CordovaWebView;
            this.val$url = r4_String;
        }

        public void run() {
            new Thread(this.val$timeoutCheck).start();
            this.val$me.loadUrlNow(this.val$url);
        }
    }

    class ActivityResult {
        Intent incoming;
        int request;
        int result;

        public ActivityResult(int r2i, int r3i, Intent r4_Intent) {
            this.request = r2i;
            this.result = r3i;
            this.incoming = r4_Intent;
        }
    }

    private static class Level16Apis {
        private Level16Apis() {
        }

        static void enableUniversalAccess(WebSettings r1_WebSettings) {
            r1_WebSettings.setAllowUniversalAccessFromFileURLs(true);
        }
    }

    static {
        COVER_SCREEN_GRAVITY_CENTER = new LayoutParams(-1, -1, 17);
    }

    public CordovaWebView(Context r3_Context) {
        super(r3_Context);
        this.keyDownCodes = new ArrayList();
        this.keyUpCodes = new ArrayList();
        this.urls = new Stack();
        this.useBrowserHistory = true;
        this.loadUrlTimeout = 0;
        this.handleButton = false;
        this.lastMenuEventTime = 0;
        this.mResult = null;
        if (CordovaInterface.class.isInstance(r3_Context)) {
            this.cordova = (CordovaInterface) r3_Context;
        } else {
            Log.d(TAG, "Your activity must implement CordovaInterface to work");
        }
        loadConfiguration();
        setup();
    }

    public CordovaWebView(Context r3_Context, AttributeSet r4_AttributeSet) {
        super(r3_Context, r4_AttributeSet);
        this.keyDownCodes = new ArrayList();
        this.keyUpCodes = new ArrayList();
        this.urls = new Stack();
        this.useBrowserHistory = true;
        this.loadUrlTimeout = 0;
        this.handleButton = false;
        this.lastMenuEventTime = 0;
        this.mResult = null;
        if (CordovaInterface.class.isInstance(r3_Context)) {
            this.cordova = (CordovaInterface) r3_Context;
        } else {
            Log.d(TAG, "Your activity must implement CordovaInterface to work");
        }
        setWebChromeClient(new CordovaChromeClient(this.cordova, this));
        initWebViewClient(this.cordova);
        loadConfiguration();
        setup();
    }

    public CordovaWebView(Context r3_Context, AttributeSet r4_AttributeSet, int r5i) {
        super(r3_Context, r4_AttributeSet, r5i);
        this.keyDownCodes = new ArrayList();
        this.keyUpCodes = new ArrayList();
        this.urls = new Stack();
        this.useBrowserHistory = true;
        this.loadUrlTimeout = 0;
        this.handleButton = false;
        this.lastMenuEventTime = 0;
        this.mResult = null;
        if (CordovaInterface.class.isInstance(r3_Context)) {
            this.cordova = (CordovaInterface) r3_Context;
        } else {
            Log.d(TAG, "Your activity must implement CordovaInterface to work");
        }
        setWebChromeClient(new CordovaChromeClient(this.cordova, this));
        loadConfiguration();
        setup();
    }

    public CordovaWebView(Context r3_Context, AttributeSet r4_AttributeSet, int r5i, boolean r6z) {
        super(r3_Context, r4_AttributeSet, r5i, r6z);
        this.keyDownCodes = new ArrayList();
        this.keyUpCodes = new ArrayList();
        this.urls = new Stack();
        this.useBrowserHistory = true;
        this.loadUrlTimeout = 0;
        this.handleButton = false;
        this.lastMenuEventTime = 0;
        this.mResult = null;
        if (CordovaInterface.class.isInstance(r3_Context)) {
            this.cordova = (CordovaInterface) r3_Context;
        } else {
            Log.d(TAG, "Your activity must implement CordovaInterface to work");
        }
        setWebChromeClient(new CordovaChromeClient(this.cordova));
        initWebViewClient(this.cordova);
        loadConfiguration();
        setup();
    }

    private void exposeJsInterface() {
        int r0i;
        int r1i = VERSION.SDK_INT;
        r0i = (r1i < 11 || r1i > 13) ? 0 : 1;
        if (r0i != 0 || r1i < 9) {
            Log.i(TAG, "Disabled addJavascriptInterface() bridge since Android version is old.");
        } else if (r1i >= 11 || (!Build.MANUFACTURER.equals(EDIT_TYPE.TYPE_UNKNOWN))) {
            addJavascriptInterface(this.exposedJsApi, "_cordovaNative");
        } else {
            Log.i(TAG, "Disabled addJavascriptInterface() bridge callback due to a bug on the 2.3 emulator");
        }
    }

    private void initWebViewClient(CordovaInterface r3_CordovaInterface) {
        if (VERSION.SDK_INT < 11) {
            setWebViewClient(new CordovaWebViewClient(this.cordova, this));
        } else {
            setWebViewClient(new IceCreamCordovaWebViewClient(this.cordova, this));
        }
    }

    private void loadConfiguration() {
        if ("false".equals(getProperty("useBrowserHistory", "true"))) {
            this.useBrowserHistory = false;
            Log.w(TAG, "useBrowserHistory=false is deprecated as of Cordova 2.2.0 and will be removed six months after the 2.2.0 release.  Please use the browser history and use history.back().");
        }
        if ("true".equals(getProperty("fullscreen", "false"))) {
            this.cordova.getActivity().getWindow().clearFlags(LVBuffer.MAX_STRING_LENGTH);
            this.cordova.getActivity().getWindow().setFlags(Util.BYTE_OF_KB, Util.BYTE_OF_KB);
        }
    }

    private void setup() {
        setInitialScale(0);
        setVerticalScrollBarEnabled(false);
        requestFocusFromTouch();
        WebSettings r0_WebSettings = getSettings();
        r0_WebSettings.setJavaScriptEnabled(true);
        r0_WebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        r0_WebSettings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
        try {
            Class[] r3_ClassA = new Class[1];
            r3_ClassA[0] = Boolean.TYPE;
            Method r1_Method = WebSettings.class.getMethod("setNavDump", r3_ClassA);
            if (VERSION.SDK_INT < REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE) {
                Object[] r2_ObjectA = new Object[1];
                r2_ObjectA[0] = Boolean.valueOf(true);
                r1_Method.invoke(r0_WebSettings, r2_ObjectA);
            }
        } catch (NoSuchMethodException e) {
            Log.d(TAG, "We are on a modern version of Android, we will deprecate HTC 2.3 devices in 2.8");
        } catch (IllegalArgumentException e_2) {
            Log.d(TAG, "Doing the NavDump failed with bad arguments");
        } catch (IllegalAccessException e_3) {
            Log.d(TAG, "This should never happen: IllegalAccessException means this isn't Android anymore");
        } catch (InvocationTargetException e_4) {
            Log.d(TAG, "This should never happen: InvocationTargetException means this isn't Android anymore.");
        }
        if (VERSION.SDK_INT > 15) {
            Level16Apis.enableUniversalAccess(r0_WebSettings);
        }
        r0_WebSettings.setDatabaseEnabled(true);
        String r1_String = this.cordova.getActivity().getApplicationContext().getDir("database", 0).getPath();
        r0_WebSettings.setDatabasePath(r1_String);
        r0_WebSettings.setGeolocationDatabasePath(r1_String);
        r0_WebSettings.setDomStorageEnabled(true);
        r0_WebSettings.setGeolocationEnabled(true);
        r0_WebSettings.setAppCacheMaxSize(5242880);
        r0_WebSettings.setAppCachePath(this.cordova.getActivity().getApplicationContext().getDir("database", 0).getPath());
        r0_WebSettings.setAppCacheEnabled(true);
        updateUserAgentString();
        IntentFilter r0_IntentFilter = new IntentFilter();
        r0_IntentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        if (this.receiver == null) {
            this.receiver = new BroadcastReceiver() {
                public void onReceive(Context r2_Context, Intent r3_Intent) {
                    CordovaWebView.this.updateUserAgentString();
                }
            };
            this.cordova.getActivity().registerReceiver(this.receiver, r0_IntentFilter);
        }
        this.pluginManager = new PluginManager(this, this.cordova);
        this.jsMessageQueue = new NativeToJsMessageQueue(this, this.cordova);
        this.exposedJsApi = new ExposedJsApi(this.pluginManager, this.jsMessageQueue);
        exposeJsInterface();
    }

    private void updateUserAgentString() {
        getSettings().getUserAgentString();
    }

    public boolean backHistory() {
        if (super.canGoBack() && this.useBrowserHistory) {
            printBackForwardList();
            super.goBack();
            return true;
        } else {
            if (this.urls.size() <= 1 || this.useBrowserHistory) {
                return false;
            }
            this.urls.pop();
            loadUrl((String) this.urls.pop());
            return true;
        }
    }

    public void bindButton(int r3i, boolean r4z, boolean r5z) {
        if (r4z) {
            this.keyDownCodes.add(Integer.valueOf(r3i));
        } else {
            this.keyUpCodes.add(Integer.valueOf(r3i));
        }
    }

    public void bindButton(String r3_String, boolean r4z) {
        if (r3_String.compareTo("volumeup") == 0) {
            this.keyDownCodes.add(Integer.valueOf(AdViewUtil.NETWORK_TYPE_CASEE));
        } else {
            if (r3_String.compareTo("volumedown") == 0) {
                this.keyDownCodes.add(Integer.valueOf(AdViewUtil.NETWORK_TYPE_WIYUN));
            }
        }
    }

    public void bindButton(boolean r1z) {
        this.bound = r1z;
    }

    public boolean canGoBack() {
        return (super.canGoBack() && this.useBrowserHistory) || this.urls.size() > 1;
    }

    public String getProperty(String r2_String, String r3_String) {
        Bundle r0_Bundle = this.cordova.getActivity().getIntent().getExtras();
        if (r0_Bundle == null) {
            return r3_String;
        }
        Object r0_Object = r0_Bundle.get(r2_String);
        return r0_Object != null ? r0_Object.toString() : r3_String;
    }

    public CordovaChromeClient getWebChromeClient() {
        return this.chromeClient;
    }

    public boolean hadKeyEvent() {
        return this.handleButton;
    }

    public void handleDestroy() {
        loadUrlIntoView("javascript:try{cordova.require('cordova/channel').onDestroy.fire();}catch(e){console.log('exception firing destroy event from native');};");
        loadUrl("about:blank");
        if (this.pluginManager != null) {
            this.pluginManager.onDestroy();
        }
        if (this.receiver != null) {
            try {
                this.cordova.getActivity().unregisterReceiver(this.receiver);
            } catch (Exception e) {
                Throwable r0_Throwable = e;
                Log.e(TAG, "Error unregistering configuration receiver: " + r0_Throwable.getMessage(), r0_Throwable);
            }
        }
    }

    public void handlePause(boolean r3z) {
        LOG.d(TAG, "Handle the pause");
        loadUrl("javascript:try{cordova.fireDocumentEvent('pause');}catch(e){console.log('exception firing pause event from native');};");
        if (this.pluginManager != null) {
            this.pluginManager.onPause(r3z);
        }
        if (!r3z) {
            pauseTimers();
        }
        this.paused = true;
    }

    public void handleResume(boolean r2z, boolean r3z) {
        loadUrl("javascript:try{cordova.fireDocumentEvent('resume');}catch(e){console.log('exception firing resume event from native');};");
        if (this.pluginManager != null) {
            this.pluginManager.onResume(r2z);
        }
        resumeTimers();
        this.paused = false;
    }

    public void hideCustomView() {
        Log.d(TAG, "Hidding Custom View");
        if (this.mCustomView == null) {
        } else {
            this.mCustomView.setVisibility(Base64.DONT_BREAK_LINES);
            ((ViewGroup) getParent()).removeView(this.mCustomView);
            this.mCustomView = null;
            this.mCustomViewCallback.onCustomViewHidden();
            setVisibility(0);
        }
    }

    public boolean isBackButtonBound() {
        return this.bound;
    }

    public boolean isCustomViewShowing() {
        return this.mCustomView != null;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void loadUrl(String r3_String) {
        if (r3_String.equals("about:blank") || r3_String.startsWith("javascript:")) {
            loadUrlNow(r3_String);
        } else {
            String r0_String = getProperty(Constants.PARAM_URL, null);
            if (r0_String == null || this.urls.size() > 0) {
                loadUrlIntoView(r3_String);
            } else {
                loadUrlIntoView(r0_String);
            }
        }
    }

    public void loadUrl(String r3_String, int r4i) {
        String r0_String = getProperty(Constants.PARAM_URL, null);
        if (r0_String == null || this.urls.size() > 0) {
            loadUrlIntoView(r3_String, r4i);
        } else {
            loadUrlIntoView(r0_String);
        }
    }

    public void loadUrlIntoView(String r7_String) {
        LOG.d(TAG, ">>> loadUrl(" + r7_String + ")");
        this.url = r7_String;
        if (this.baseUrl == null) {
            int r0i = r7_String.lastIndexOf(AdViewUtil.NETWORK_TYPE_SUIZONG);
            if (r0i > 0) {
                this.baseUrl = r7_String.substring(0, r0i + 1);
            } else {
                this.baseUrl = this.url + "/";
            }
            this.pluginManager.init();
            if (!this.useBrowserHistory) {
                this.urls.push(r7_String);
            }
        }
        this.cordova.getActivity().runOnUiThread(new AnonymousClass_4(new AnonymousClass_3(Integer.parseInt(getProperty("loadUrlTimeoutValue", "20000")), this, this.loadUrlTimeout, new AnonymousClass_2(this, r7_String)), this, r7_String));
    }

    public void loadUrlIntoView(String r6_String, int r7i) {
        if (r6_String.startsWith("javascript:") || this.urls.size() > 0 || canGoBack()) {
            loadUrlIntoView(r6_String);
        } else {
            String r0_String = TAG;
            Object[] r2_ObjectA = new Object[2];
            r2_ObjectA[0] = r6_String;
            r2_ObjectA[1] = Integer.valueOf(r7i);
            LOG.d(r0_String, "DroidGap.loadUrl(%s, %d)", r2_ObjectA);
            postMessage("splashscreen", AdViewUtil.COUNTSHOW);
            loadUrlIntoView(r6_String);
        }
    }

    void loadUrlNow(String r3_String) {
        if ((!LOG.isLoggable(XListViewFooter.STATE_NOMORE)) || r3_String.startsWith("javascript:")) {
            if (r3_String.startsWith("file://") || r3_String.indexOf(this.baseUrl) == 0 || r3_String.startsWith("javascript:") || Config.isUrlWhiteListed(r3_String)) {
                super.loadUrl(r3_String);
            }
        } else {
            LOG.d(TAG, ">>> loadUrlNow()");
            if (r3_String.startsWith("file://") || r3_String.indexOf(this.baseUrl) == 0 || r3_String.startsWith("javascript:") || Config.isUrlWhiteListed(r3_String)) {
                super.loadUrl(r3_String);
            }
        }
    }

    public boolean onKeyDown(int r5i, KeyEvent r6_KeyEvent) {
        boolean r0z = false;
        if (this.keyDownCodes.contains(Integer.valueOf(r5i))) {
            if (r5i == 25) {
                LOG.d(TAG, "Down Key Hit");
                loadUrl("javascript:cordova.fireDocumentEvent('volumedownbutton');");
                return true;
            } else {
                if (r5i != 24) {
                    return super.onKeyDown(r5i, r6_KeyEvent);
                }
                LOG.d(TAG, "Up Key Hit");
                loadUrl("javascript:cordova.fireDocumentEvent('volumeupbutton');");
                return true;
            }
        } else {
            if (r5i != 4) {
                return super.onKeyDown(r5i, r6_KeyEvent);
            }
            if (this.useBrowserHistory) {
                if (startOfHistory() && !(this.bound)) {
                    return r0z;
                }
                r0z = true;
                return r0z;
            } else {
                if (this.urls.size() <= 1 && !(this.bound)) {
                    return r0z;
                }
                r0z = true;
                return r0z;
            }
        }
    }

    public boolean onKeyUp(int r5i, KeyEvent r6_KeyEvent) {
        if (r5i == 4) {
            if (this.mCustomView != null) {
                hideCustomView();
            } else if (this.bound) {
                loadUrl("javascript:cordova.fireDocumentEvent('backbutton');");
                return true;
            } else {
                if (backHistory()) {
                    return true;
                }
                this.cordova.getActivity().finish();
            }
        } else if (r5i == 82) {
            if (this.lastMenuEventTime < r6_KeyEvent.getEventTime()) {
                loadUrl("javascript:cordova.fireDocumentEvent('menubutton');");
            }
            this.lastMenuEventTime = r6_KeyEvent.getEventTime();
            return super.onKeyUp(r5i, r6_KeyEvent);
        } else if (r5i == 84) {
            loadUrl("javascript:cordova.fireDocumentEvent('searchbutton');");
            return true;
        } else if (this.keyUpCodes.contains(Integer.valueOf(r5i))) {
            return super.onKeyUp(r5i, r6_KeyEvent);
        }
        return super.onKeyUp(r5i, r6_KeyEvent);
    }

    public void onNewIntent(Intent r2_Intent) {
        if (this.pluginManager != null) {
            this.pluginManager.onNewIntent(r2_Intent);
        }
    }

    public String peekAtUrlStack() {
        return this.urls.size() > 0 ? (String) this.urls.peek() : RContactStorage.PRIMARY_KEY;
    }

    public void postMessage(String r2_String, Object r3_Object) {
        if (this.pluginManager != null) {
            this.pluginManager.postMessage(r2_String, r3_Object);
        }
    }

    public void printBackForwardList() {
        WebBackForwardList r1_WebBackForwardList = copyBackForwardList();
        int r2i = r1_WebBackForwardList.getSize();
        int r0i = 0;
        while (r0i < r2i) {
            LOG.d(TAG, "The URL at index: " + Integer.toString(r0i) + "is " + r1_WebBackForwardList.getItemAtIndex(r0i).getUrl());
            r0i++;
        }
    }

    public void pushUrl(String r2_String) {
        this.urls.push(r2_String);
    }

    public WebBackForwardList restoreState(Bundle r4_Bundle) {
        WebBackForwardList r0_WebBackForwardList = super.restoreState(r4_Bundle);
        Log.d(TAG, "WebView restoration crew now restoring!");
        this.pluginManager.init();
        return r0_WebBackForwardList;
    }

    public void sendJavascript(String r2_String) {
        this.jsMessageQueue.addJavaScript(r2_String);
    }

    public void sendPluginResult(PluginResult r2_PluginResult, String r3_String) {
        this.jsMessageQueue.addPluginResult(r2_PluginResult, r3_String);
    }

    public void setWebChromeClient(CordovaChromeClient r1_CordovaChromeClient) {
        this.chromeClient = r1_CordovaChromeClient;
        super.setWebChromeClient(r1_CordovaChromeClient);
    }

    public void setWebViewClient(CordovaWebViewClient r1_CordovaWebViewClient) {
        this.viewClient = r1_CordovaWebViewClient;
        super.setWebViewClient(r1_CordovaWebViewClient);
    }

    public void showCustomView(View r3_View, CustomViewCallback r4_CustomViewCallback) {
        Log.d(TAG, "showing Custom View");
        if (this.mCustomView != null) {
            r4_CustomViewCallback.onCustomViewHidden();
        } else {
            this.mCustomView = r3_View;
            this.mCustomViewCallback = r4_CustomViewCallback;
            ViewGroup r0_ViewGroup = (ViewGroup) getParent();
            r0_ViewGroup.addView(r3_View, COVER_SCREEN_GRAVITY_CENTER);
            setVisibility(Base64.DONT_BREAK_LINES);
            r0_ViewGroup.setVisibility(0);
            r0_ViewGroup.bringToFront();
        }
    }

    public void showWebPage(String r6_String, boolean r7z, boolean r8z, HashMap<String, Object> r9_HashMap_String__Object) {
        String r0_String = TAG;
        Object[] r2_ObjectA = new Object[3];
        r2_ObjectA[0] = r6_String;
        r2_ObjectA[1] = Boolean.valueOf(r7z);
        r2_ObjectA[2] = Boolean.valueOf(r8z);
        LOG.d(r0_String, "showWebPage(%s, %b, %b, HashMap", r2_ObjectA);
        if (r8z) {
            clearHistory();
        }
        Intent r0_Intent;
        if (r7z) {
            try {
                r0_Intent = new Intent("android.intent.action.VIEW");
                r0_Intent.setData(Uri.parse(r6_String));
                this.cordova.getActivity().startActivity(r0_Intent);
            } catch (ActivityNotFoundException e) {
                LOG.e(TAG, "Error loading url " + r6_String, e);
            }
        } else if (r6_String.startsWith("file://") || r6_String.indexOf(this.baseUrl) == 0 || Config.isUrlWhiteListed(r6_String)) {
            if (r8z) {
                this.urls.clear();
            }
            loadUrl(r6_String);
        } else {
            LOG.w(TAG, "showWebPage: Cannot load URL into webview since it is not in white list.  Loading into browser instead. (URL=" + r6_String + ")");
            try {
                r0_Intent = new Intent("android.intent.action.VIEW");
                r0_Intent.setData(Uri.parse(r6_String));
                this.cordova.getActivity().startActivity(r0_Intent);
            } catch (ActivityNotFoundException e_2) {
                LOG.e(TAG, "Error loading url " + r6_String, e_2);
            }
        }
    }

    public boolean startOfHistory() {
        WebHistoryItem r1_WebHistoryItem = copyBackForwardList().getItemAtIndex(0);
        if (r1_WebHistoryItem == null) {
            return false;
        }
        String r0_String = r1_WebHistoryItem.getUrl();
        String r1_String = getUrl();
        LOG.d(TAG, "The current URL is: " + r1_String);
        LOG.d(TAG, "The URL at item 0 is:" + r0_String);
        return r1_String.equals(r0_String);
    }

    public void storeResult(int r2i, int r3i, Intent r4_Intent) {
        this.mResult = new ActivityResult(r2i, r3i, r4_Intent);
    }
}