package org.apache.cordova;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.LOG;
import org.json.JSONArray;
import org.json.JSONException;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.utils.audit.RequestListener;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class CordovaChromeClient extends WebChromeClient {
    public static final int FILECHOOSER_RESULTCODE = 5173;
    private static final String LOG_TAG = "CordovaChromeClient";
    private long MAX_QUOTA;
    private String TAG;
    private CordovaWebView appView;
    private CordovaInterface cordova;
    public ValueCallback<Uri> mUploadMessage;
    private View mVideoProgressView;

    class AnonymousClass_1 implements OnClickListener {
        final /* synthetic */ JsResult val$result;

        AnonymousClass_1(JsResult r2_JsResult) {
            this.val$result = r2_JsResult;
        }

        public void onClick(DialogInterface r2_DialogInterface, int r3i) {
            this.val$result.confirm();
        }
    }

    class AnonymousClass_2 implements OnCancelListener {
        final /* synthetic */ JsResult val$result;

        AnonymousClass_2(JsResult r2_JsResult) {
            this.val$result = r2_JsResult;
        }

        public void onCancel(DialogInterface r2_DialogInterface) {
            this.val$result.cancel();
        }
    }

    class AnonymousClass_3 implements OnKeyListener {
        final /* synthetic */ JsResult val$result;

        AnonymousClass_3(JsResult r2_JsResult) {
            this.val$result = r2_JsResult;
        }

        public boolean onKey(DialogInterface r2_DialogInterface, int r3i, KeyEvent r4_KeyEvent) {
            if (r3i != 4) {
                return true;
            }
            this.val$result.confirm();
            return false;
        }
    }

    class AnonymousClass_4 implements OnClickListener {
        final /* synthetic */ JsResult val$result;

        AnonymousClass_4(JsResult r2_JsResult) {
            this.val$result = r2_JsResult;
        }

        public void onClick(DialogInterface r2_DialogInterface, int r3i) {
            this.val$result.confirm();
        }
    }

    class AnonymousClass_5 implements OnClickListener {
        final /* synthetic */ JsResult val$result;

        AnonymousClass_5(JsResult r2_JsResult) {
            this.val$result = r2_JsResult;
        }

        public void onClick(DialogInterface r2_DialogInterface, int r3i) {
            this.val$result.cancel();
        }
    }

    class AnonymousClass_6 implements OnCancelListener {
        final /* synthetic */ JsResult val$result;

        AnonymousClass_6(JsResult r2_JsResult) {
            this.val$result = r2_JsResult;
        }

        public void onCancel(DialogInterface r2_DialogInterface) {
            this.val$result.cancel();
        }
    }

    class AnonymousClass_7 implements OnKeyListener {
        final /* synthetic */ JsResult val$result;

        AnonymousClass_7(JsResult r2_JsResult) {
            this.val$result = r2_JsResult;
        }

        public boolean onKey(DialogInterface r2_DialogInterface, int r3i, KeyEvent r4_KeyEvent) {
            if (r3i != 4) {
                return true;
            }
            this.val$result.cancel();
            return false;
        }
    }

    class AnonymousClass_8 implements OnClickListener {
        final /* synthetic */ EditText val$input;
        final /* synthetic */ JsPromptResult val$res;

        AnonymousClass_8(EditText r2_EditText, JsPromptResult r3_JsPromptResult) {
            this.val$input = r2_EditText;
            this.val$res = r3_JsPromptResult;
        }

        public void onClick(DialogInterface r3_DialogInterface, int r4i) {
            this.val$res.confirm(this.val$input.getText().toString());
        }
    }

    class AnonymousClass_9 implements OnClickListener {
        final /* synthetic */ JsPromptResult val$res;

        AnonymousClass_9(JsPromptResult r2_JsPromptResult) {
            this.val$res = r2_JsPromptResult;
        }

        public void onClick(DialogInterface r2_DialogInterface, int r3i) {
            this.val$res.cancel();
        }
    }

    public CordovaChromeClient(CordovaInterface r3_CordovaInterface) {
        this.TAG = "CordovaLog";
        this.MAX_QUOTA = 104857600;
        this.cordova = r3_CordovaInterface;
    }

    public CordovaChromeClient(CordovaInterface r3_CordovaInterface, CordovaWebView r4_CordovaWebView) {
        this.TAG = "CordovaLog";
        this.MAX_QUOTA = 104857600;
        this.cordova = r3_CordovaInterface;
        this.appView = r4_CordovaWebView;
    }

    public ValueCallback<Uri> getValueCallback() {
        return this.mUploadMessage;
    }

    public View getVideoLoadingProgressView() {
        int r3i = RequestListener.DEFAULT_LOADED_SIZE;
        if (this.mVideoProgressView == null) {
            View r0_View = new LinearLayout(this.appView.getContext());
            r0_View.setOrientation(1);
            LayoutParams r1_LayoutParams = new RelativeLayout.LayoutParams(r3i, r3i);
            r1_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER);
            r0_View.setLayoutParams(r1_LayoutParams);
            View r1_View = new ProgressBar(this.appView.getContext());
            LayoutParams r2_LayoutParams = new LinearLayout.LayoutParams(r3i, r3i);
            r2_LayoutParams.gravity = 17;
            r1_View.setLayoutParams(r2_LayoutParams);
            r0_View.addView(r1_View);
            this.mVideoProgressView = r0_View;
        }
        return this.mVideoProgressView;
    }

    public void onConsoleMessage(String r6_String, int r7i, String r8_String) {
        if (VERSION.SDK_INT == 7) {
            String r0_String = this.TAG;
            Object[] r2_ObjectA = new Object[3];
            r2_ObjectA[0] = r8_String;
            r2_ObjectA[1] = Integer.valueOf(r7i);
            r2_ObjectA[2] = r6_String;
            LOG.d(r0_String, "%s: Line %d : %s", r2_ObjectA);
            super.onConsoleMessage(r6_String, r7i, r8_String);
        }
    }

    public boolean onConsoleMessage(ConsoleMessage r3_ConsoleMessage) {
        if (r3_ConsoleMessage.message() != null) {
            LOG.d(this.TAG, r3_ConsoleMessage.message());
        }
        return super.onConsoleMessage(r3_ConsoleMessage);
    }

    public void onExceededDatabaseQuota(String r6_String, String r7_String, long r8j, long r10j, long r12j, QuotaUpdater r14_QuotaUpdater) {
        String r0_String = this.TAG;
        Object[] r2_ObjectA = new Object[3];
        r2_ObjectA[0] = Long.valueOf(r10j);
        r2_ObjectA[1] = Long.valueOf(r8j);
        r2_ObjectA[2] = Long.valueOf(r12j);
        LOG.d(r0_String, "DroidGap:  onExceededDatabaseQuota estimatedSize: %d  currentQuota: %d  totalUsedQuota: %d", r2_ObjectA);
        if (r10j < this.MAX_QUOTA) {
            r0_String = this.TAG;
            r2_ObjectA = new Object[1];
            r2_ObjectA[0] = Long.valueOf(r10j);
            LOG.d(r0_String, "calling quotaUpdater.updateQuota newQuota: %d", r2_ObjectA);
            r14_QuotaUpdater.updateQuota(r10j);
        } else {
            r14_QuotaUpdater.updateQuota(r8j);
        }
    }

    public void onGeolocationPermissionsShowPrompt(String r3_String, Callback r4_Callback) {
        super.onGeolocationPermissionsShowPrompt(r3_String, r4_Callback);
        r4_Callback.invoke(r3_String, true, false);
    }

    public void onHideCustomView() {
        this.appView.hideCustomView();
    }

    public boolean onJsAlert(WebView r5_WebView, String r6_String, String r7_String, JsResult r8_JsResult) {
        Builder r0_Builder = new Builder(this.cordova.getActivity());
        r0_Builder.setMessage(r7_String);
        r0_Builder.setTitle("Alert");
        r0_Builder.setCancelable(true);
        r0_Builder.setPositiveButton(17039370, new AnonymousClass_1(r8_JsResult));
        r0_Builder.setOnCancelListener(new AnonymousClass_2(r8_JsResult));
        r0_Builder.setOnKeyListener(new AnonymousClass_3(r8_JsResult));
        r0_Builder.create();
        r0_Builder.show();
        return true;
    }

    public boolean onJsConfirm(WebView r5_WebView, String r6_String, String r7_String, JsResult r8_JsResult) {
        Builder r0_Builder = new Builder(this.cordova.getActivity());
        r0_Builder.setMessage(r7_String);
        r0_Builder.setTitle("Confirm");
        r0_Builder.setCancelable(true);
        r0_Builder.setPositiveButton(17039370, new AnonymousClass_4(r8_JsResult));
        r0_Builder.setNegativeButton(17039360, new AnonymousClass_5(r8_JsResult));
        r0_Builder.setOnCancelListener(new AnonymousClass_6(r8_JsResult));
        r0_Builder.setOnKeyListener(new AnonymousClass_7(r8_JsResult));
        r0_Builder.create();
        r0_Builder.show();
        return true;
    }

    public boolean onJsPrompt(WebView r7_WebView, String r8_String, String r9_String, String r10_String, JsPromptResult r11_JsPromptResult) {
        int r0i;
        boolean r1z = false;
        r0i = (r8_String.startsWith("file://") || r8_String.indexOf(this.appView.baseUrl) == 0 || Config.isUrlWhiteListed(r8_String)) ? 1 : 0;
        String r0_String;
        if (r0i == 0 || r10_String == null || r10_String.length() <= 3 || (!r10_String.substring(0, XListViewFooter.STATE_NODATA).equals("gap:"))) {
            if (r0i == 0 || r10_String == null || (!r10_String.equals("gap_bridge_mode:"))) {
                if (r0i == 0 || r10_String == null || (!r10_String.equals("gap_poll:"))) {
                    if (r10_String == null || (!r10_String.equals("gap_init:"))) {
                        Builder r0_Builder = new Builder(this.cordova.getActivity());
                        r0_Builder.setMessage(r9_String);
                        View r3_View = new EditText(this.cordova.getActivity());
                        if (r10_String != null) {
                            r3_View.setText(r10_String);
                        }
                        r0_Builder.setView(r3_View);
                        r0_Builder.setCancelable(r1z);
                        r0_Builder.setPositiveButton(17039370, new AnonymousClass_8(r3_View, r11_JsPromptResult));
                        r0_Builder.setNegativeButton(17039360, new AnonymousClass_9(r11_JsPromptResult));
                        r0_Builder.create();
                        r0_Builder.show();
                    } else {
                        r11_JsPromptResult.confirm("OK");
                    }
                } else {
                    r0_String = this.appView.exposedJsApi.retrieveJsMessages();
                    if (r0_String == null) {
                        r0_String = RContactStorage.PRIMARY_KEY;
                    }
                    r11_JsPromptResult.confirm(r0_String);
                }
            } else {
                this.appView.exposedJsApi.setNativeToJsBridgeMode(Integer.parseInt(r9_String));
                r11_JsPromptResult.confirm(RContactStorage.PRIMARY_KEY);
            }
            return true;
        } else {
            try {
                JSONArray r0_JSONArray = new JSONArray(r10_String.substring(XListViewFooter.STATE_NODATA));
                r0_String = this.appView.exposedJsApi.exec(r0_JSONArray.getString(0), r0_JSONArray.getString(1), r0_JSONArray.getString(XListViewHeader.STATE_REFRESHING), r9_String);
                if (r0_String == null) {
                    r0_String = RContactStorage.PRIMARY_KEY;
                }
                r11_JsPromptResult.confirm(r0_String);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    public void onShowCustomView(View r2_View, CustomViewCallback r3_CustomViewCallback) {
        this.appView.showCustomView(r2_View, r3_CustomViewCallback);
    }

    public void openFileChooser(ValueCallback<Uri> r2_ValueCallback_Uri) {
        openFileChooser(r2_ValueCallback_Uri, "*/*");
    }

    public void openFileChooser(ValueCallback<Uri> r2_ValueCallback_Uri, String r3_String) {
        openFileChooser(r2_ValueCallback_Uri, r3_String, null);
    }

    public void openFileChooser(ValueCallback<Uri> r4_ValueCallback_Uri, String r5_String, String r6_String) {
        this.mUploadMessage = r4_ValueCallback_Uri;
        Intent r0_Intent = new Intent("android.intent.action.GET_CONTENT");
        r0_Intent.addCategory("android.intent.category.OPENABLE");
        r0_Intent.setType("*/*");
        this.cordova.getActivity().startActivityForResult(Intent.createChooser(r0_Intent, "File Browser"), FILECHOOSER_RESULTCODE);
    }

    public void setWebView(CordovaWebView r1_CordovaWebView) {
        this.appView = r1_CordovaWebView;
    }
}