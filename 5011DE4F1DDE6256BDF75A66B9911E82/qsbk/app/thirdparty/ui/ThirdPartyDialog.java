package qsbk.app.thirdparty.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import qsbk.app.R;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyAuthListener;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.thirdparty.ThirdPartyDialogError;
import qsbk.app.thirdparty.ThirdPartyException;
import qsbk.app.thirdparty.Utility.Utility;
import qsbk.app.widget.listview.XListViewFooter;

public class ThirdPartyDialog extends Dialog {
    static LayoutParams a;
    private static int h;
    private static int i;
    private static int j;
    private static int k;
    private static int l;
    protected ThirdPartyAuthListener b;
    protected ProgressDialog c;
    protected WebView d;
    private String e;
    private RelativeLayout f;
    private RelativeLayout g;

    private class a implements OnCancelListener {
        private a() {
        }

        public void onCancel(DialogInterface r2_DialogInterface) {
            ThirdPartyDialog.this.b.onCancel();
        }
    }

    private class b extends WebViewClient {
        private b() {
        }

        public void onPageFinished(WebView r3_WebView, String r4_String) {
            super.onPageFinished(r3_WebView, r4_String);
            if (ThirdPartyDialog.this.c.isShowing()) {
                ThirdPartyDialog.this.c.dismiss();
            }
            ThirdPartyDialog.this.d.setVisibility(0);
        }

        public void onPageStarted(WebView r2_WebView, String r3_String, Bitmap r4_Bitmap) {
            if (r3_String.startsWith(ThirdParty.redirecturl)) {
                ThirdPartyDialog.this.b(r2_WebView, r3_String);
                r2_WebView.stopLoading();
                ThirdPartyDialog.this.dismiss();
            } else {
                super.onPageStarted(r2_WebView, r3_String, r4_Bitmap);
                ThirdPartyDialog.this.c.show();
            }
        }

        public void onReceivedError(WebView r3_WebView, int r4i, String r5_String, String r6_String) {
            super.onReceivedError(r3_WebView, r4i, r5_String, r6_String);
            ThirdPartyDialog.this.b.onError(new ThirdPartyDialogError(r5_String, r4i, r6_String));
            ThirdPartyDialog.this.dismiss();
        }

        public void onReceivedSslError(WebView r1_WebView, SslErrorHandler r2_SslErrorHandler, SslError r3_SslError) {
            r2_SslErrorHandler.proceed();
        }

        public boolean shouldOverrideUrlLoading(WebView r5_WebView, String r6_String) {
            if (!r6_String.startsWith("sms:")) {
                return super.shouldOverrideUrlLoading(r5_WebView, r6_String);
            }
            Intent r0_Intent = new Intent("android.intent.action.VIEW");
            r0_Intent.putExtra("address", r6_String.replace("sms:", RContactStorage.PRIMARY_KEY));
            r0_Intent.setType("vnd.android-dir/mms-sms");
            ThirdPartyDialog.this.getContext().startActivity(r0_Intent);
            return true;
        }
    }

    private class c extends WebViewClient {
        private c() {
        }

        public void onPageFinished(WebView r3_WebView, String r4_String) {
            super.onPageFinished(r3_WebView, r4_String);
            if (ThirdPartyDialog.this.c.isShowing()) {
                ThirdPartyDialog.this.c.dismiss();
            }
            ThirdPartyDialog.this.d.setVisibility(0);
        }

        public void onPageStarted(WebView r2_WebView, String r3_String, Bitmap r4_Bitmap) {
            if (r3_String.startsWith(ThirdParty.redirecturl)) {
                ThirdPartyDialog.this.a(r2_WebView, r3_String);
                r2_WebView.stopLoading();
                ThirdPartyDialog.this.dismiss();
            } else {
                super.onPageStarted(r2_WebView, r3_String, r4_Bitmap);
                ThirdPartyDialog.this.c.show();
            }
        }

        public void onReceivedError(WebView r3_WebView, int r4i, String r5_String, String r6_String) {
            super.onReceivedError(r3_WebView, r4i, r5_String, r6_String);
            ThirdPartyDialog.this.b.onError(new ThirdPartyDialogError(r5_String, r4i, r6_String));
            ThirdPartyDialog.this.dismiss();
        }

        public void onReceivedSslError(WebView r1_WebView, SslErrorHandler r2_SslErrorHandler, SslError r3_SslError) {
            r2_SslErrorHandler.proceed();
        }

        public boolean shouldOverrideUrlLoading(WebView r5_WebView, String r6_String) {
            Log.d("Weibo-WebView", "Redirect URL: " + r6_String);
            if (!r6_String.startsWith("sms:")) {
                return super.shouldOverrideUrlLoading(r5_WebView, r6_String);
            }
            Intent r0_Intent = new Intent("android.intent.action.VIEW");
            r0_Intent.putExtra("address", r6_String.replace("sms:", RContactStorage.PRIMARY_KEY));
            r0_Intent.setType("vnd.android-dir/mms-sms");
            ThirdPartyDialog.this.getContext().startActivity(r0_Intent);
            return true;
        }
    }

    static {
        a = new LayoutParams(-1, -1);
        h = 16973840;
        i = 0;
        j = 0;
        k = 0;
        l = 0;
    }

    public ThirdPartyDialog(Context r2_Context, String r3_String, ThirdPartyAuthListener r4_ThirdPartyAuthListener) {
        super(r2_Context, h);
        this.e = r3_String;
        this.b = r4_ThirdPartyAuthListener;
    }

    private void a() {
        try {
            this.c.dismiss();
            if (this.d != null) {
                this.d.stopLoading();
                this.d.destroy();
            }
        } catch (Exception e) {
        }
        cancel();
    }

    private void a(WebView r5_WebView, String r6_String) {
        Bundle r0_Bundle = Utility.parseUrl(r6_String);
        String r1_String = r0_Bundle.getString(QQDialogAuthorizeActivity.ERROR_RET);
        String r2_String = r0_Bundle.getString("error_code");
        if (r1_String == null && r2_String == null) {
            this.b.onComplete(r0_Bundle);
        } else if (r1_String.equals("access_denied")) {
            this.b.onCancel();
        } else if (r2_String == null) {
            this.b.onThirdPartyException(new ThirdPartyException(r1_String, 0));
        } else {
            this.b.onThirdPartyException(new ThirdPartyException(r1_String, Integer.parseInt(r2_String)));
        }
    }

    private void b() {
        InputStream r1_InputStream;
        r1_InputStream = null;
        this.f = new RelativeLayout(getContext());
        this.d = new WebView(getContext());
        this.d.setVerticalScrollBarEnabled(false);
        this.d.setHorizontalScrollBarEnabled(false);
        this.d.getSettings().setJavaScriptEnabled(true);
        this.d.setWebViewClient(getWebViewClient());
        this.d.loadUrl(this.e);
        this.d.setLayoutParams(a);
        this.d.setVisibility(XListViewFooter.STATE_NODATA);
        ViewGroup.LayoutParams r2_ViewGroup_LayoutParams = new RelativeLayout.LayoutParams(-1, -1);
        ViewGroup.LayoutParams r3_ViewGroup_LayoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.g.setBackgroundColor(0);
        try {
            r1_InputStream = getContext().getAssets().open("weibosdk_dialog_bg.9.png");
            float r0f = getContext().getResources().getDisplayMetrics().density;
            r3_ViewGroup_LayoutParams.leftMargin = (int) (10.0f * r0f);
            r3_ViewGroup_LayoutParams.topMargin = (int) (10.0f * r0f);
            r3_ViewGroup_LayoutParams.rightMargin = (int) (10.0f * r0f);
            r3_ViewGroup_LayoutParams.bottomMargin = (int) (r0f * 10.0f);
            if (r1_InputStream == null) {
                this.f.setBackgroundResource(R.drawable.weibosdk_dialog_bg);
            } else {
                Bitmap r0_Bitmap = BitmapFactory.decodeStream(r1_InputStream);
                this.f.setBackgroundDrawable(new NinePatchDrawable(r0_Bitmap, r0_Bitmap.getNinePatchChunk(), new Rect(0, 0, 0, 0), null));
            }
            if (r1_InputStream != null) {
                try {
                    r1_InputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e_2) {
            e_2.printStackTrace();
            if (r1_InputStream != null) {
                r1_InputStream.close();
            }
        }
        this.f.addView(this.d, r3_ViewGroup_LayoutParams);
        this.f.setGravity(R.styleable.ActionBar_progressBarPadding);
        if (c()) {
            r2_ViewGroup_LayoutParams.leftMargin = i;
            r2_ViewGroup_LayoutParams.topMargin = j;
            r2_ViewGroup_LayoutParams.rightMargin = k;
            r2_ViewGroup_LayoutParams.bottomMargin = l;
        } else {
            Resources r0_Resources = getContext().getResources();
            r2_ViewGroup_LayoutParams.leftMargin = r0_Resources.getDimensionPixelSize(R.dimen.thirdparty_dialog_left_margin);
            r2_ViewGroup_LayoutParams.rightMargin = r0_Resources.getDimensionPixelSize(R.dimen.thirdparty_dialog_right_margin);
            r2_ViewGroup_LayoutParams.topMargin = r0_Resources.getDimensionPixelSize(R.dimen.thirdparty_dialog_top_margin);
            r2_ViewGroup_LayoutParams.bottomMargin = r0_Resources.getDimensionPixelSize(R.dimen.thirdparty_dialog_bottom_margin);
        }
        this.g.addView(this.f, r2_ViewGroup_LayoutParams);
    }

    private void b(WebView r5_WebView, String r6_String) {
        Bundle r0_Bundle = new Bundle();
        Matcher r1_Matcher = Pattern.compile("access_token=(.+?)(?:&|$)").matcher(r6_String);
        if (r1_Matcher.find()) {
            r0_Bundle.putString(ThirdParty.KEY_TOKEN, r1_Matcher.group(1));
        }
        r1_Matcher = Pattern.compile("expires_in=(.+?)(?:&|$)").matcher(r6_String);
        if (r1_Matcher.find()) {
            r0_Bundle.putString(ThirdParty.KEY_EXPIRES, r1_Matcher.group(1));
        }
        this.b.onComplete(r0_Bundle);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean c() {
        /*
        r7_this = this;
        r3 = 0;
        r1 = 1;
        r2 = 0;
        r0 = r7.getContext();
        r0 = r0.getAssets();
        r4 = r7.getContext();
        r4 = r4.getResources();
        r4 = r4.getDisplayMetrics();
        r4 = r4.density;
        r5 = "values/dimens.xml";
        r3 = r0.open(r5);	 //Catch:{ IOException -> 0x00d6, all -> 0x00a9 }
        r5 = android.util.Xml.newPullParser();	 //Catch:{ IOException -> 0x00db, all -> 0x00a9 }
        r0 = "utf-8";
        r5.setInput(r3, r0);	 //Catch:{ XmlPullParserException -> 0x00e0 }
        r0 = r5.getEventType();	 //Catch:{ XmlPullParserException -> 0x00e0 }
    L_0x002c:
        if (r0 == r1) goto L_0x00c7;
    L_0x002e:
        switch(r0) {
            case 2: goto L_0x0036;
            default: goto L_0x0031;
        };
    L_0x0031:
        r0 = r5.next();	 //Catch:{ XmlPullParserException -> 0x005f }
        goto L_0x002c;
    L_0x0036:
        r0 = r5.getName();	 //Catch:{ XmlPullParserException -> 0x005f }
        r2 = "dimen";
        r0 = r0.equals(r2);	 //Catch:{ XmlPullParserException -> 0x005f }
        if (r0 == 0) goto L_0x0031;
    L_0x0042:
        r0 = 0;
        r2 = "name";
        r0 = r5.getAttributeValue(r0, r2);	 //Catch:{ XmlPullParserException -> 0x005f }
        r2 = "weibosdk_dialog_left_margin";
        r2 = r2.equals(r0);	 //Catch:{ XmlPullParserException -> 0x005f }
        if (r2 == 0) goto L_0x006a;
    L_0x0051:
        r0 = r5.nextText();	 //Catch:{ XmlPullParserException -> 0x005f }
        r0 = java.lang.Integer.parseInt(r0);	 //Catch:{ XmlPullParserException -> 0x005f }
        r0 = (float) r0;	 //Catch:{ XmlPullParserException -> 0x005f }
        r0 = r0 * r4;
        r0 = (int) r0;	 //Catch:{ XmlPullParserException -> 0x005f }
        i = r0;	 //Catch:{ XmlPullParserException -> 0x005f }
        goto L_0x0031;
    L_0x005f:
        r0 = move-exception;
    L_0x0060:
        r0.printStackTrace();	 //Catch:{ IOException -> 0x0080, all -> 0x00a9 }
        r0 = r1;
    L_0x0064:
        if (r3 == 0) goto L_0x0069;
    L_0x0066:
        r3.close();	 //Catch:{ IOException -> 0x00c9 }
    L_0x0069:
        return r0;
    L_0x006a:
        r2 = "weibosdk_dialog_top_margin";
        r2 = r2.equals(r0);	 //Catch:{ XmlPullParserException -> 0x005f }
        if (r2 == 0) goto L_0x0093;
    L_0x0072:
        r0 = r5.nextText();	 //Catch:{ XmlPullParserException -> 0x005f }
        r0 = java.lang.Integer.parseInt(r0);	 //Catch:{ XmlPullParserException -> 0x005f }
        r0 = (float) r0;	 //Catch:{ XmlPullParserException -> 0x005f }
        r0 = r0 * r4;
        r0 = (int) r0;	 //Catch:{ XmlPullParserException -> 0x005f }
        j = r0;	 //Catch:{ XmlPullParserException -> 0x005f }
        goto L_0x0031;
    L_0x0080:
        r0 = move-exception;
        r2 = r3;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x0085:
        r1.printStackTrace();	 //Catch:{ all -> 0x00d3 }
        if (r2 == 0) goto L_0x0069;
    L_0x008a:
        r2.close();	 //Catch:{ IOException -> 0x008e }
        goto L_0x0069;
    L_0x008e:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0069;
    L_0x0093:
        r2 = "weibosdk_dialog_right_margin";
        r2 = r2.equals(r0);	 //Catch:{ XmlPullParserException -> 0x005f }
        if (r2 == 0) goto L_0x00b0;
    L_0x009b:
        r0 = r5.nextText();	 //Catch:{ XmlPullParserException -> 0x005f }
        r0 = java.lang.Integer.parseInt(r0);	 //Catch:{ XmlPullParserException -> 0x005f }
        r0 = (float) r0;	 //Catch:{ XmlPullParserException -> 0x005f }
        r0 = r0 * r4;
        r0 = (int) r0;	 //Catch:{ XmlPullParserException -> 0x005f }
        k = r0;	 //Catch:{ XmlPullParserException -> 0x005f }
        goto L_0x0031;
    L_0x00a9:
        r0 = move-exception;
    L_0x00aa:
        if (r3 == 0) goto L_0x00af;
    L_0x00ac:
        r3.close();	 //Catch:{ IOException -> 0x00ce }
    L_0x00af:
        throw r0;
    L_0x00b0:
        r2 = "weibosdk_dialog_bottom_margin";
        r0 = r2.equals(r0);	 //Catch:{ XmlPullParserException -> 0x005f }
        if (r0 == 0) goto L_0x0031;
    L_0x00b8:
        r0 = r5.nextText();	 //Catch:{ XmlPullParserException -> 0x005f }
        r0 = java.lang.Integer.parseInt(r0);	 //Catch:{ XmlPullParserException -> 0x005f }
        r0 = (float) r0;	 //Catch:{ XmlPullParserException -> 0x005f }
        r0 = r0 * r4;
        r0 = (int) r0;	 //Catch:{ XmlPullParserException -> 0x005f }
        l = r0;	 //Catch:{ XmlPullParserException -> 0x005f }
        goto L_0x0031;
    L_0x00c7:
        r0 = r1;
        goto L_0x0064;
    L_0x00c9:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0069;
    L_0x00ce:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00af;
    L_0x00d3:
        r0 = move-exception;
        r3 = r2;
        goto L_0x00aa;
    L_0x00d6:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
        r2 = r3;
        goto L_0x0085;
    L_0x00db:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
        r2 = r3;
        goto L_0x0085;
    L_0x00e0:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0060;
        */

    }

    public WebViewClient getWebViewClient() {
        return ThirdPartyConstants.THIRDPARTY_TYLE_QQ.equals(ThirdParty.thirdparty_type) ? new b(null) : new c(null);
    }

    protected void onCreate(Bundle r6_Bundle) {
        super.onCreate(r6_Bundle);
        this.c = new ProgressDialog(getContext());
        this.c.requestWindowFeature(1);
        this.c.setMessage("\u6b63\u5728\u52a0\u8f7d...");
        this.c.setOnKeyListener(new a(this));
        requestWindowFeature(1);
        getWindow().setFeatureDrawableAlpha(0, 0);
        this.g = new RelativeLayout(getContext());
        b();
        addContentView(this.g, new ViewGroup.LayoutParams(-1, -1));
        if (this.b != null) {
            setOnCancelListener(new a(null));
        }
    }
}