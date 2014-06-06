package org.apache.cordova;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.LVBuffer;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.tauth.Constants;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.LOG;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;

public class DroidGap extends Activity implements CordovaInterface {
    private static int ACTIVITY_EXITING;
    private static int ACTIVITY_RUNNING;
    private static int ACTIVITY_STARTING;
    public static String TAG;
    private Object LOG_TAG;
    protected CordovaPlugin activityResultCallback;
    protected boolean activityResultKeepRunning;
    private int activityState;
    protected CordovaWebView appView;
    private int backgroundColor;
    protected boolean cancelLoadUrl;
    private String initCallbackClass;
    protected boolean keepRunning;
    private Intent lastIntent;
    private int lastRequestCode;
    private Object lastResponseCode;
    protected int loadUrlTimeoutValue;
    private Object responseCode;
    protected LinearLayout root;
    protected ProgressDialog spinnerDialog;
    protected Dialog splashDialog;
    protected int splashscreen;
    protected int splashscreenTime;
    private final ExecutorService threadPool;
    protected CordovaWebViewClient webViewClient;

    class AnonymousClass_2 implements Runnable {
        final /* synthetic */ String val$errorUrl;

        AnonymousClass_2(String r3_String) {
            this.val$errorUrl = r3_String;
        }

        public void run() {
            DroidGap.this.spinnerStop();
            DroidGap.this.appView.showWebPage(this.val$errorUrl, false, true, null);
        }
    }

    class AnonymousClass_3 implements Runnable {
        final /* synthetic */ String val$description;
        final /* synthetic */ boolean val$exit;
        final /* synthetic */ String val$failingUrl;
        final /* synthetic */ DroidGap val$me;

        AnonymousClass_3(boolean r2z, DroidGap r3_DroidGap, String r4_String, String r5_String) {
            this.val$exit = r2z;
            this.val$me = r3_DroidGap;
            this.val$description = r4_String;
            this.val$failingUrl = r5_String;
        }

        public void run() {
            if (this.val$exit) {
                this.val$me.appView.setVisibility(Base64.DONT_BREAK_LINES);
                this.val$me.displayError("Application Error", this.val$description + " (" + this.val$failingUrl + ")", "OK", this.val$exit);
            }
        }
    }

    class AnonymousClass_4 implements Runnable {
        final /* synthetic */ String val$button;
        final /* synthetic */ boolean val$exit;
        final /* synthetic */ String val$message;
        final /* synthetic */ String val$title;

        AnonymousClass_4(String r3_String, String r4_String, String r5_String, boolean r6z) {
            this.val$message = r3_String;
            this.val$title = r4_String;
            this.val$button = r5_String;
            this.val$exit = r6z;
        }

        public void run() {
            try {
                Builder r0_Builder = new Builder(DroidGap.this);
                r0_Builder.setMessage(this.val$message);
                r0_Builder.setTitle(this.val$title);
                r0_Builder.setCancelable(false);
                r0_Builder.setPositiveButton(this.val$button, new OnClickListener() {
                    public void onClick(DialogInterface r2_DialogInterface, int r3i) {
                        r2_DialogInterface.dismiss();
                        if (AnonymousClass_4.this.val$exit) {
                            AnonymousClass_4.this.val$me.endActivity();
                        }
                    }
                });
                r0_Builder.create();
                r0_Builder.show();
            } catch (Exception e) {
                DroidGap.this.finish();
            }
        }
    }

    class AnonymousClass_5 implements Runnable {
        final /* synthetic */ int val$time;

        AnonymousClass_5(int r3i) {
            this.val$time = r3i;
        }

        public void run() {
            Display r0_Display = DroidGap.this.getWindowManager().getDefaultDisplay();
            View r1_View = new LinearLayout(DroidGap.this.getActivity());
            r1_View.setMinimumHeight(r0_Display.getHeight());
            r1_View.setMinimumWidth(r0_Display.getWidth());
            r1_View.setOrientation(1);
            r1_View.setBackgroundColor(DroidGap.this.getIntegerProperty("backgroundColor", ViewCompat.MEASURED_STATE_MASK));
            r1_View.setLayoutParams(new LayoutParams(-1, -1, 0.0f));
            r1_View.setBackgroundResource(DroidGap.this.splashscreen);
            DroidGap.this.splashDialog = new Dialog(DroidGap.this, 16973840);
            if ((DroidGap.this.getWindow().getAttributes().flags & 1024) == 1024) {
                DroidGap.this.splashDialog.getWindow().setFlags(Util.BYTE_OF_KB, Util.BYTE_OF_KB);
            }
            DroidGap.this.splashDialog.setContentView(r1_View);
            DroidGap.this.splashDialog.setCancelable(false);
            DroidGap.this.splashDialog.show();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    AnonymousClass_5.this.this$0.removeSplashScreen();
                }
            }, (long) this.val$time);
        }
    }

    static {
        TAG = "DroidGap";
        ACTIVITY_STARTING = 0;
        ACTIVITY_RUNNING = 1;
        ACTIVITY_EXITING = 2;
    }

    public DroidGap() {
        this.cancelLoadUrl = false;
        this.spinnerDialog = null;
        this.threadPool = Executors.newCachedThreadPool();
        this.activityState = 0;
        this.activityResultCallback = null;
        this.backgroundColor = -16777216;
        this.splashscreen = 0;
        this.splashscreenTime = 3000;
        this.loadUrlTimeoutValue = 20000;
        this.keepRunning = true;
    }

    public void addService(String r2_String, String r3_String) {
        if (this.appView == null || this.appView.pluginManager == null) {
        } else {
            this.appView.pluginManager.addService(r2_String, r3_String);
        }
    }

    public boolean backHistory() {
        return this.appView != null ? this.appView.backHistory() : false;
    }

    public void cancelLoadUrl() {
        this.cancelLoadUrl = true;
    }

    public void clearAuthenticationTokens() {
        if (this.appView == null || this.appView.viewClient == null) {
        } else {
            this.appView.viewClient.clearAuthenticationTokens();
        }
    }

    public void clearCache() {
        if (this.appView == null) {
            init();
        }
        this.appView.clearCache(true);
    }

    public void clearHistory() {
        this.appView.clearHistory();
    }

    public void displayError(String r8_String, String r9_String, String r10_String, boolean r11z) {
        runOnUiThread(new AnonymousClass_4(this, r9_String, r8_String, r10_String, r11z));
    }

    public void endActivity() {
        this.activityState = ACTIVITY_EXITING;
        super.finish();
    }

    public Activity getActivity() {
        return this;
    }

    public AuthenticationToken getAuthenticationToken(String r2_String, String r3_String) {
        return (this.appView == null || this.appView.viewClient == null) ? null : this.appView.viewClient.getAuthenticationToken(r2_String, r3_String);
    }

    public boolean getBooleanProperty(String r3_String, boolean r4z) {
        Bundle r1_Bundle = getIntent().getExtras();
        if (r1_Bundle == null) {
            return r4z;
        }
        Boolean r0_Boolean;
        try {
            r0_Boolean = (Boolean) r1_Bundle.get(r3_String);
        } catch (ClassCastException e) {
            r0_Boolean = "true".equals(r1_Bundle.get(r3_String).toString()) ? Boolean.valueOf(true) : Boolean.valueOf(false);
        }
        return r0_Boolean != null ? r0_Boolean.booleanValue() : r4z;
    }

    public Context getContext() {
        LOG.d(TAG, "This will be deprecated December 2012");
        return this;
    }

    public double getDoubleProperty(String r3_String, double r4d) {
        Bundle r1_Bundle = getIntent().getExtras();
        if (r1_Bundle == null) {
            return r4d;
        }
        Double r0_Double;
        try {
            r0_Double = (Double) r1_Bundle.get(r3_String);
        } catch (ClassCastException e) {
            r0_Double = Double.valueOf(Double.parseDouble(r1_Bundle.get(r3_String).toString()));
        }
        return r0_Double != null ? r0_Double.doubleValue() : r4d;
    }

    public int getIntegerProperty(String r3_String, int r4i) {
        Bundle r1_Bundle = getIntent().getExtras();
        if (r1_Bundle == null) {
            return r4i;
        }
        Integer r0_Integer;
        try {
            r0_Integer = (Integer) r1_Bundle.get(r3_String);
        } catch (ClassCastException e) {
            r0_Integer = Integer.valueOf(Integer.parseInt(r1_Bundle.get(r3_String).toString()));
        }
        return r0_Integer != null ? r0_Integer.intValue() : r4i;
    }

    public String getStringProperty(String r2_String, String r3_String) {
        Bundle r0_Bundle = getIntent().getExtras();
        if (r0_Bundle == null) {
            return r3_String;
        }
        String r0_String = r0_Bundle.getString(r2_String);
        return r0_String != null ? r0_String : r3_String;
    }

    public ExecutorService getThreadPool() {
        return this.threadPool;
    }

    public void init() {
        CordovaWebView r1_CordovaWebView = new CordovaWebView(this);
        init(r1_CordovaWebView, VERSION.SDK_INT < 11 ? new CordovaWebViewClient(this, r1_CordovaWebView) : new IceCreamCordovaWebViewClient(this, r1_CordovaWebView), new CordovaChromeClient(this, r1_CordovaWebView));
    }

    public void init(CordovaWebView r5_CordovaWebView, CordovaWebViewClient r6_CordovaWebViewClient, CordovaChromeClient r7_CordovaChromeClient) {
        LOG.d(TAG, "DroidGap.init()");
        this.appView = r5_CordovaWebView;
        this.appView.setId(100);
        this.appView.setWebViewClient(r6_CordovaWebViewClient);
        this.appView.setWebChromeClient(r7_CordovaChromeClient);
        r6_CordovaWebViewClient.setWebView(this.appView);
        r7_CordovaChromeClient.setWebView(this.appView);
        this.appView.setLayoutParams(new LayoutParams(-1, -1, 1.0f));
        this.appView.setVisibility(XListViewFooter.STATE_NODATA);
        this.root.addView(this.appView);
        setContentView(this.root);
        this.cancelLoadUrl = false;
    }

    public boolean isUrlWhiteListed(String r2_String) {
        return Config.isUrlWhiteListed(r2_String);
    }

    void loadSpinner() {
        String r0_String;
        r0_String = (this.appView == null || (!this.appView.canGoBack())) ? getStringProperty("loadingDialog", null) : getStringProperty("loadingPageDialog", null);
        if (r0_String != null) {
            String r2_String = RContactStorage.PRIMARY_KEY;
            String r1_String = "Loading Application...";
            if (r0_String.length() > 0) {
                int r2i = r0_String.indexOf(AdViewUtil.NETWORK_TYPE_FRACTAL);
                if (r2i > 0) {
                    r1_String = r0_String.substring(0, r2i);
                    r0_String = r0_String.substring(r2i + 1);
                } else {
                    r1_String = RContactStorage.PRIMARY_KEY;
                }
            } else {
                r0_String = r1_String;
                r1_String = r2_String;
            }
            spinnerStart(r1_String, r0_String);
        }
    }

    public void loadUrl(String r3_String) {
        if (this.appView == null) {
            init();
        }
        this.backgroundColor = getIntegerProperty("backgroundColor", ViewCompat.MEASURED_STATE_MASK);
        this.root.setBackgroundColor(this.backgroundColor);
        this.keepRunning = getBooleanProperty("keepRunning", true);
        loadSpinner();
        this.appView.loadUrl(r3_String);
    }

    public void loadUrl(String r3_String, int r4i) {
        if (this.appView == null) {
            init();
        }
        this.splashscreenTime = r4i;
        this.splashscreen = getIntegerProperty("splashscreen", 0);
        showSplashScreen(this.splashscreenTime);
        this.appView.loadUrl(r3_String, r4i);
    }

    protected void onActivityResult(int r6i, int r7i, Intent r8_Intent) {
        LOG.d(TAG, "Incoming Result");
        super.onActivityResult(r6i, r7i, r8_Intent);
        Log.d(TAG, "Request code = " + r6i);
        ValueCallback r1_ValueCallback = this.appView.getWebChromeClient().getValueCallback();
        if (r6i == CordovaChromeClient.FILECHOOSER_RESULTCODE) {
            Log.d(TAG, "did we get here?");
            if (r1_ValueCallback == null) {
                return;
            }
            Uri r0_Uri;
            if (r8_Intent == null || r7i != -1) {
                r0_Uri = null;
                Log.d(TAG, "result = " + r0_Uri);
                r1_ValueCallback.onReceiveValue(r0_Uri);
            } else {
                r0_Uri = r8_Intent.getData();
                Log.d(TAG, "result = " + r0_Uri);
                r1_ValueCallback.onReceiveValue(r0_Uri);
            }
        }
        CordovaPlugin r0_CordovaPlugin = this.activityResultCallback;
        if (r0_CordovaPlugin == null) {
            if (this.initCallbackClass != null) {
                this.activityResultCallback = this.appView.pluginManager.getPlugin(this.initCallbackClass);
                r0_CordovaPlugin = this.activityResultCallback;
                LOG.d(TAG, "We have a callback to send this result to");
                r0_CordovaPlugin.onActivityResult(r6i, r7i, r8_Intent);
            }
        } else {
            LOG.d(TAG, "We have a callback to send this result to");
            r0_CordovaPlugin.onActivityResult(r6i, r7i, r8_Intent);
        }
    }

    public void onConfigurationChanged(Configuration r1_Configuration) {
        super.onConfigurationChanged(r1_Configuration);
    }

    public void onCreate(Bundle r8_Bundle) {
        Config.init(this);
        LOG.d(TAG, "DroidGap.onCreate()");
        super.onCreate(r8_Bundle);
        if (r8_Bundle != null) {
            this.initCallbackClass = r8_Bundle.getString("callbackClass");
        }
        if (!getBooleanProperty("showTitle", false)) {
            getWindow().requestFeature(1);
        }
        if (getBooleanProperty("setFullscreen", false)) {
            getWindow().setFlags(Util.BYTE_OF_KB, Util.BYTE_OF_KB);
        } else {
            getWindow().setFlags(LVBuffer.MAX_STRING_LENGTH, LVBuffer.MAX_STRING_LENGTH);
        }
        Display r0_Display = getWindowManager().getDefaultDisplay();
        this.root = new LinearLayoutSoftKeyboardDetect(this, r0_Display.getWidth(), r0_Display.getHeight());
        this.root.setOrientation(1);
        this.root.setBackgroundColor(this.backgroundColor);
        this.root.setLayoutParams(new LayoutParams(-1, -1, 0.0f));
        setVolumeControlStream(XListViewFooter.STATE_NOMORE);
    }

    public boolean onCreateOptionsMenu(Menu r2_Menu) {
        postMessage("onCreateOptionsMenu", r2_Menu);
        return super.onCreateOptionsMenu(r2_Menu);
    }

    public void onDestroy() {
        LOG.d(TAG, "onDestroy()");
        super.onDestroy();
        removeSplashScreen();
        if (this.appView != null) {
            this.appView.handleDestroy();
        } else {
            endActivity();
        }
    }

    public boolean onKeyDown(int r2i, KeyEvent r3_KeyEvent) {
        return (this.appView.getFocusedChild() == null || r2i != 4) ? super.onKeyDown(r2i, r3_KeyEvent) : this.appView.onKeyDown(r2i, r3_KeyEvent);
    }

    public boolean onKeyUp(int r3i, KeyEvent r4_KeyEvent) {
        return ((this.appView.isCustomViewShowing() || this.appView.getFocusedChild() != null) && r3i == 4) ? this.appView.onKeyUp(r3i, r4_KeyEvent) : super.onKeyUp(r3i, r4_KeyEvent);
    }

    public Object onMessage(String r5_String, Object r6_Object) {
        LOG.d(TAG, "onMessage(" + r5_String + "," + r6_Object + ")");
        if ("splashscreen".equals(r5_String)) {
            if ("hide".equals(r6_Object.toString())) {
                removeSplashScreen();
            } else if (this.splashDialog == null || (!this.splashDialog.isShowing())) {
                this.splashscreen = getIntegerProperty("splashscreen", 0);
                showSplashScreen(this.splashscreenTime);
            }
        } else if ("spinner".equals(r5_String)) {
            if ("stop".equals(r6_Object.toString())) {
                spinnerStop();
                this.appView.setVisibility(0);
            }
        } else if ("onReceivedError".equals(r5_String)) {
            JSONObject r6_JSONObject = (JSONObject) r6_Object;
            try {
                onReceivedError(r6_JSONObject.getInt("errorCode"), r6_JSONObject.getString(Constants.PARAM_COMMENT), r6_JSONObject.getString(Constants.PARAM_URL));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if ("exit".equals(r5_String)) {
            endActivity();
        }
        return null;
    }

    protected void onNewIntent(Intent r2_Intent) {
        super.onNewIntent(r2_Intent);
        if (this.appView != null) {
            this.appView.onNewIntent(r2_Intent);
        }
    }

    public boolean onOptionsItemSelected(MenuItem r2_MenuItem) {
        postMessage("onOptionsItemSelected", r2_MenuItem);
        return true;
    }

    protected void onPause() {
        super.onPause();
        LOG.d(TAG, "Paused the application!");
        if (!(this.activityState == ACTIVITY_EXITING || this.appView == null)) {
            this.appView.handlePause(this.keepRunning);
            removeSplashScreen();
        }
    }

    public boolean onPrepareOptionsMenu(Menu r2_Menu) {
        postMessage("onPrepareOptionsMenu", r2_Menu);
        return true;
    }

    public void onReceivedError(int r7i, String r8_String, String r9_String) {
        String r0_String = getStringProperty("errorUrl", null);
        if (r0_String != null) {
            if ((r0_String.startsWith("file://") || Config.isUrlWhiteListed(r0_String)) && (!r9_String.equals(r0_String))) {
                runOnUiThread(new AnonymousClass_2(this, r0_String));
                return;
            }
        }
        runOnUiThread(new AnonymousClass_3(r7i != -2, this, r8_String, r9_String));
    }

    protected void onResume() {
        super.onResume();
        LOG.d(TAG, "Resuming the App");
        if (this.activityState == ACTIVITY_STARTING) {
            this.activityState = ACTIVITY_RUNNING;
        } else {
            if (this.appView != null) {
                this.appView.handleResume(this.keepRunning, this.activityResultKeepRunning);
                if (((!this.keepRunning) || this.activityResultKeepRunning) && this.activityResultKeepRunning) {
                    this.keepRunning = this.activityResultKeepRunning;
                    this.activityResultKeepRunning = false;
                }
            }
        }
    }

    protected void onSaveInstanceState(Bundle r3_Bundle) {
        super.onSaveInstanceState(r3_Bundle);
        if (this.activityResultCallback != null) {
            r3_Bundle.putString("callbackClass", this.activityResultCallback.getClass().getName());
        }
    }

    public void postMessage(String r2_String, Object r3_Object) {
        if (this.appView != null) {
            this.appView.postMessage(r2_String, r3_Object);
        }
    }

    public AuthenticationToken removeAuthenticationToken(String r2_String, String r3_String) {
        return (this.appView == null || this.appView.viewClient == null) ? null : this.appView.viewClient.removeAuthenticationToken(r2_String, r3_String);
    }

    public void removeSplashScreen() {
        if (this.splashDialog == null || (!this.splashDialog.isShowing())) {
        } else {
            this.splashDialog.dismiss();
            this.splashDialog = null;
        }
    }

    public void sendJavascript(String r2_String) {
        if (this.appView != null) {
            this.appView.jsMessageQueue.addJavaScript(r2_String);
        }
    }

    public void setActivityResultCallback(CordovaPlugin r1_CordovaPlugin) {
        this.activityResultCallback = r1_CordovaPlugin;
    }

    public void setAuthenticationToken(AuthenticationToken r2_AuthenticationToken, String r3_String, String r4_String) {
        if (this.appView == null || this.appView.viewClient == null) {
        } else {
            this.appView.viewClient.setAuthenticationToken(r2_AuthenticationToken, r3_String, r4_String);
        }
    }

    public void setBooleanProperty(String r3_String, boolean r4z) {
        Log.d(TAG, "Setting boolean properties in DroidGap will be deprecated in 3.0 on July 2013, please use config.xml");
        getIntent().putExtra(r3_String, r4z);
    }

    public void setDoubleProperty(String r3_String, double r4d) {
        Log.d(TAG, "Setting double properties in DroidGap will be deprecated in 3.0 on July 2013, please use config.xml");
        getIntent().putExtra(r3_String, r4d);
    }

    public void setIntegerProperty(String r3_String, int r4i) {
        Log.d(TAG, "Setting integer properties in DroidGap will be deprecated in 3.1 on August 2013, please use config.xml");
        getIntent().putExtra(r3_String, r4i);
    }

    public void setStringProperty(String r3_String, String r4_String) {
        Log.d(TAG, "Setting string properties in DroidGap will be deprecated in 3.0 on July 2013, please use config.xml");
        getIntent().putExtra(r3_String, r4_String);
    }

    protected void showSplashScreen(int r2i) {
        runOnUiThread(new AnonymousClass_5(this, r2i));
    }

    public void showWebPage(String r2_String, boolean r3z, boolean r4z, HashMap<String, Object> r5_HashMap_String__Object) {
        if (this.appView != null) {
            this.appView.showWebPage(r2_String, r3z, r4z, r5_HashMap_String__Object);
        }
    }

    public void spinnerStart(String r7_String, String r8_String) {
        if (this.spinnerDialog != null) {
            this.spinnerDialog.dismiss();
            this.spinnerDialog = null;
        }
        this.spinnerDialog = ProgressDialog.show(this, r7_String, r8_String, true, true, new OnCancelListener() {
            public void onCancel(DialogInterface r3_DialogInterface) {
                DroidGap.this.spinnerDialog = null;
            }
        });
    }

    public void spinnerStop() {
        if (this.spinnerDialog == null || (!this.spinnerDialog.isShowing())) {
        } else {
            this.spinnerDialog.dismiss();
            this.spinnerDialog = null;
        }
    }

    public void startActivityForResult(CordovaPlugin r2_CordovaPlugin, Intent r3_Intent, int r4i) {
        this.activityResultCallback = r2_CordovaPlugin;
        this.activityResultKeepRunning = this.keepRunning;
        if (r2_CordovaPlugin != null) {
            this.keepRunning = false;
        }
        super.startActivityForResult(r3_Intent, r4i);
    }
}