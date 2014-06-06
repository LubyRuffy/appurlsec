package com.zkmm.adsdk;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import android.widget.VideoView;
import com.androidquery.util.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import java.util.StringTokenizer;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.image.ImageFetcher;

// compiled from: SourceFile
public final class q extends WebViewClient {
    private /* synthetic */ AdDisplayer a;

    protected q(AdDisplayer r1_AdDisplayer) {
        this.a = r1_AdDisplayer;
    }

    public final void onPageFinished(WebView r2_WebView, String r3_String) {
        super.onPageFinished(r2_WebView, r3_String);
        try {
            if (this.a.v != null) {
                if (this.a.v.d.equalsIgnoreCase(r3_String) || r3_String.contains(m.O)) {
                    s.a(r2_WebView, this.a.v.a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView r10_WebView, String r11_String) {
        boolean r1z = false;
        if (r11_String != null) {
            if (r11_String.startsWith(ImageFetcher.HTTP_CACHE_DIR) || r11_String.startsWith("https")) {
                if (r11_String.endsWith(".mp3") || r11_String.endsWith(".wav") || r11_String.endsWith(".mp4") || r11_String.endsWith(".3gp") || r11_String.endsWith(".mpeg")) {
                    s.b(AdDisplayer.m, r11_String);
                    return true;
                } else if (r11_String.endsWith(".apk")) {
                    s.a(r11_String, AdDisplayer.m, this.a.v.a, ((byte) ((this.a.v.l & 240) >> 4)) > 0, this.a.v.p);
                    return true;
                } else if (r11_String.endsWith(".jpeg") || r11_String.endsWith(".gif") || r11_String.endsWith(".png") || r11_String.endsWith(".bmp") || r11_String.endsWith(Util.PHOTO_DEFAULT_EXT)) {
                    s.a(r11_String, (Activity) AdDisplayer.m);
                    return true;
                } else {
                    r10_WebView.loadUrl(r11_String);
                    return true;
                }
            } else if (r11_String.startsWith("tel")) {
                s.f(AdDisplayer.m, r11_String);
                return true;
            } else if (r11_String.startsWith("sms")) {
                s.c(AdDisplayer.m, r11_String);
                return true;
            } else if (r11_String.startsWith("market")) {
                s.d(AdDisplayer.m, r11_String);
                return true;
            } else if (r11_String.startsWith("mailto")) {
                s.e(AdDisplayer.m, r11_String);
                return true;
            } else if (r11_String.startsWith("adwo://")) {
                try {
                    String r0_String = r11_String.substring(ShareUtils.SHARE_COLLECT);
                    StringTokenizer r3_StringTokenizer = new StringTokenizer(r0_String, "&");
                    String r2_String = r3_StringTokenizer.nextToken();
                    int r4i;
                    int r0i;
                    if ("adwoOpenURL".equalsIgnoreCase(r2_String)) {
                        r2_String = r3_StringTokenizer.nextToken();
                        r4i = Integer.decode(r2_String.substring(r2_String.indexOf("_") + 1)).intValue();
                        r2_String = r3_StringTokenizer.nextToken();
                        Integer.decode(r2_String.substring(r2_String.indexOf("_") + 1)).intValue();
                        r2_String = r3_StringTokenizer.nextToken();
                        Integer.decode(r2_String.substring(r2_String.indexOf("_") + 1)).intValue();
                        r2_String = r3_StringTokenizer.nextToken();
                        r2_String = r2_String.substring(r2_String.indexOf("_") + 1);
                        while (r3_StringTokenizer.hasMoreTokens()) {
                            r2_String = new StringBuilder(String.valueOf(r2_String)).append("&").append(r3_StringTokenizer.nextToken()).toString();
                        }
                        if (r4i == 1) {
                            Intent r1_Intent = new Intent();
                            try {
                                r1_Intent.setComponent(new ComponentName("com.android.browser", "com.android.browser.BrowserActivity"));
                                r1_Intent.setAction("android.intent.action.VIEW");
                                r1_Intent.setData(Uri.parse(r2_String));
                                r1_Intent.addFlags(268435456);
                                AdDisplayer.m.startActivity(r1_Intent);
                                return true;
                            } catch (Exception e) {
                                r10_WebView.loadUrl(new StringBuilder("javascript:adwoURLOpenFailed(+").append(r0_String).append(");").toString());
                                return true;
                            }
                        } else if (r4i == 0) {
                            bj.o = this.a.u;
                            this.a.dismissDisplayer();
                            bj.a(AdDisplayer.m).a(AdDisplayer.f, r2_String, this.a.v.a, this.a.v.l, this.a.v.p);
                            return true;
                        } else {
                            if (r4i != 2 || !r2_String.endsWith(".apk")) {
                                return true;
                            }
                            r0i = this.a.v.a;
                            if (((byte) ((this.a.v.l & 240) >> 4)) > 0) {
                                r1z = true;
                            }
                            s.a(r2_String, AdDisplayer.m, r0i, r1z, this.a.v.p);
                            return true;
                        }
                    } else if ("adwoVibrate".equalsIgnoreCase(r2_String)) {
                        if (cs.a) {
                            return true;
                        }
                        cs.a = true;
                        Vibrator r0_Vibrator = (Vibrator) AdDisplayer.m.getSystemService("vibrator");
                        r2_String = r3_StringTokenizer.nextToken();
                        r2i = Integer.decode(r2_String.substring(r2_String.indexOf("_") + 1)).intValue();
                        if (r2i > 1) {
                            long[] r4_longA = new long[(r2i * 2)];
                            r3i = 0;
                            while (r3i < r2i) {
                                r4_longA[r1z] = 100;
                                r1i = r1z + 1;
                                r4_longA[r1i] = 500;
                                r1z = r1i + 1;
                                r3i++;
                            }
                            r0_Vibrator.vibrate(r4_longA, r2i);
                            r1i = r2i;
                        } else {
                            long[] r1_longA = new long[2];
                            r1_longA[1] = 1000;
                            r0_Vibrator.vibrate(r1_longA, 1);
                            r1i = 1;
                        }
                        r10_WebView.postDelayed(new ck(this, r0_Vibrator), (long) (r1i * 1000));
                        return true;
                    } else if ("adwoCloseAd".equalsIgnoreCase(r2_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        this.a.setAnimation(Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue());
                        this.a.dismissDisplayer();
                        return true;
                    } else if ("adwoPlayAudio".equalsIgnoreCase(r2_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                        r0_String = r3_StringTokenizer.nextToken();
                        new Thread(new cl(this, r0_String.substring(r0_String.indexOf("_") + 1))).start();
                        return true;
                    } else if ("adwoPlayVideo".equalsIgnoreCase(r2_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                        r0_String = r3_StringTokenizer.nextToken();
                        Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                        r0_String = r3_StringTokenizer.nextToken();
                        r0i = Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                        String r1_String = r3_StringTokenizer.nextToken();
                        r1i = Integer.decode(r1_String.substring(r1_String.indexOf("_") + 1)).intValue();
                        r2_String = r3_StringTokenizer.nextToken();
                        r2_String = r2_String.substring(r2_String.indexOf("_") + 1);
                        Dialog r3_Dialog = new Dialog(AdDisplayer.m);
                        r3_Dialog.setCancelable(false);
                        r3_Dialog.setCanceledOnTouchOutside(false);
                        r3_Dialog.getWindow().setFlags(Constants.FLAG_HARDWARE_ACCELERATED, Constants.FLAG_HARDWARE_ACCELERATED);
                        r3_Dialog.requestWindowFeature(1);
                        View r4_View = new VideoView(AdDisplayer.m);
                        r3_Dialog.setOnDismissListener(new cn(this, r4_View));
                        r4_View.setMediaController(new MediaController(AdDisplayer.m));
                        r4_View.setOnCompletionListener(new co(this, r3_Dialog, r10_WebView));
                        r4_View.setOnErrorListener(new cp(this, r3_Dialog));
                        r4_View.setVideoURI(Uri.parse(r2_String));
                        r4_View.start();
                        r3_Dialog.setContentView(r4_View, new LayoutParams(r0i, r1i));
                        r3_Dialog.show();
                        return true;
                    } else if ("adwoCloseVideo".equalsIgnoreCase(r2_String)) {
                        AdDisplayer r0_AdDisplayer = this.a;
                        return true;
                    } else if ("adwoFetchLocation".equalsIgnoreCase(r2_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        m.J = Double.parseDouble(r0_String.substring(r0_String.indexOf("_") + 1));
                        r0_String = r3_StringTokenizer.nextToken();
                        m.K = Double.parseDouble(r0_String.substring(r0_String.indexOf("_") + 1));
                        m.F = true;
                        return true;
                    } else if ("adwoPageDidLoad".equalsIgnoreCase(r2_String)) {
                        cs.b = System.currentTimeMillis();
                        return true;
                    } else if ("adwoAdClickCount".equalsIgnoreCase(r2_String)) {
                        this.a.v.a();
                        r0_String = r3_StringTokenizer.nextToken();
                        r10_WebView.loadUrl(new StringBuilder("javascript:").append(r0_String.substring(r0_String.indexOf("_") + 1)).append("();").toString());
                        return true;
                    } else if ("adwoFSAdShowCount".equalsIgnoreCase(r2_String)) {
                        this.a.v.b();
                        return true;
                    } else if ("adwoChangeBrowserDoneStatus".equalsIgnoreCase(r2_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                        return true;
                    } else if ("adwoAddToAddressBook".equalsIgnoreCase(r2_String)) {
                        this.a.h.loadUrl("javascript:adwoDoGetAddressBookInfo();");
                        return true;
                    } else if ("adwoAddToCalendar".equalsIgnoreCase(r2_String)) {
                        this.a.h.loadUrl("javascript:adwoDoGetCalendarEventInfo();");
                        return true;
                    } else if ("adwoAddToReminder".equalsIgnoreCase(r2_String)) {
                        this.a.h.loadUrl("javascript:adwoDoGetReminderInfo();");
                        return true;
                    } else if ("adwoInitEssentialParams".equalsIgnoreCase(r2_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        r0_String = r0_String.substring(r0_String.indexOf("_") + 1);
                        s.a(r10_WebView, this.a.v.a);
                        if ("0".equals(r0_String) || r10_WebView == null) {
                            return true;
                        }
                        r10_WebView.loadUrl(new StringBuilder("javascript:").append(r0_String).append("+();").toString());
                        return true;
                    } else if ("adwoStartAudioRecorder".equalsIgnoreCase(r2_String)) {
                        this.a.g = new ae(600.0d, null, null, r10_WebView, this.a.v.a);
                        this.a.g.a();
                        this.a.s.sendEmptyMessageDelayed(ShareUtils.SHARE_COPY, (long) this.a.r);
                        return true;
                    } else if ("adwoCloseAudioRecorder".equalsIgnoreCase(r2_String)) {
                        this.a.d();
                        this.a.s.removeMessages(ShareUtils.SHARE_COPY);
                        return true;
                    } else if ("adwoTakePhoto".equalsIgnoreCase(r2_String)) {
                        AdDisplayer.m = r10_WebView.getContext();
                        if (AdDisplayer.m.getPackageManager().hasSystemFeature("android.hardware.camera")) {
                            AdDisplayer.a(this.a, r3_StringTokenizer);
                            r10_WebView.loadUrl("javascript:adwoFetchCameraAssociatedImageInfo();");
                            return true;
                        } else {
                            Toast.makeText(AdDisplayer.m, "\u8bbe\u5907\u6ca1\u6709\u6444\u50cf\u5934\u8c03\u7528\uff01", 0).show();
                            return true;
                        }
                    } else if ("adwoCloseCamera".equalsIgnoreCase(r2_String)) {
                        AdDisplayer.i(this.a);
                        return true;
                    } else if ("adwoSaveImg2Gallery".equalsIgnoreCase(r2_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        new Thread(new cq(this, r0_String.substring(r0_String.indexOf("_") + 1))).start();
                        return true;
                    } else if ("adwoVoiceRecord".equalsIgnoreCase(r2_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        double r1d = Double.parseDouble(r0_String.substring(r0_String.indexOf("_") + 1));
                        r0_String = r3_StringTokenizer.nextToken();
                        this.a.g = new ae(r1d, r0_String.substring(r0_String.indexOf("_") + 1), new StringBuilder(String.valueOf(m.O)).append(System.currentTimeMillis()).append("ad").append("woRecord.mp4").toString(), r10_WebView, this.a.v.a);
                        this.a.g.a();
                        return true;
                    } else if ("adwoVoiceRecordStop".equalsIgnoreCase(r2_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        if (Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue() == 0 || this.a.g == null) {
                            this.a.d();
                            return true;
                        } else {
                            this.a.g.a = null;
                            this.a.d();
                            return true;
                        }
                    } else if ("adwoFSShrink".equalsIgnoreCase(r2_String)) {
                        r10_WebView.loadUrl("javascript:adwoFSShrinkBegin();");
                        r0_String = r3_StringTokenizer.nextToken();
                        r0i = Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                        r2_String = r3_StringTokenizer.nextToken();
                        r4i = Integer.decode(r2_String.substring(r2_String.indexOf("_") + 1)).intValue();
                        r2_String = r3_StringTokenizer.nextToken();
                        int r5i = Integer.decode(r2_String.substring(r2_String.indexOf("_") + 1)).intValue();
                        r2_String = r3_StringTokenizer.nextToken();
                        Double.parseDouble(r2_String.substring(r2_String.indexOf("_") + 1));
                        if (this.a.e != null) {
                            this.a.a(r4i, r5i, false);
                            if (r0i == 1) {
                                r3i = this.a.n.heightPixels > r5i ? (this.a.n.heightPixels - r5i) / 2 : 0;
                                if (this.a.n.widthPixels > r4i) {
                                    r2i = (this.a.n.widthPixels - r4i) / 2;
                                }
                                r2i = 0;
                            } else if (r0i >= 2) {
                                r3i = this.a.n.heightPixels > r5i ? this.a.n.heightPixels - r5i : 0;
                                if (this.a.n.widthPixels > r4i) {
                                    r2i = (this.a.n.widthPixels - r4i) / 2;
                                }
                                r2i = 0;
                            } else {
                                r3i = 0;
                                r2i = 0;
                            }
                            this.a.a(AdDisplayer.f, r2i, r3i, r4i, r5i);
                        }
                        r10_WebView.loadUrl("javascript:adwoFSShrinkComplete();");
                        return true;
                    } else if ("adwoFSAdDidLoad".equalsIgnoreCase(r2_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        this.a.setAnimation(Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue());
                        if (this.a.u == null) {
                            return true;
                        }
                        this.a.u.onLoadAdComplete();
                        return true;
                    } else if ("adwoBrowserDisableScroll".equalsIgnoreCase(r2_String)) {
                        this.a.h.setVerticalScrollBarEnabled(false);
                        this.a.h.setHorizontalScrollBarEnabled(false);
                        return true;
                    } else if ("adwoBrowserShowIndicator".equalsIgnoreCase(r2_String)) {
                        this.a.l = new ProgressBar(AdDisplayer.m);
                        ViewGroup.LayoutParams r0_ViewGroup_LayoutParams = new LayoutParams(-2, -2);
                        r0_ViewGroup_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER);
                        this.a.l.setLayoutParams(r0_ViewGroup_LayoutParams);
                        this.a.j.addView(this.a.l);
                        return true;
                    } else if ("adwoBrowserHideIndicator".equalsIgnoreCase(r2_String)) {
                        if (this.a.j == null) {
                            return true;
                        }
                        this.a.j.removeView(this.a.l);
                        return true;
                    } else if ("adwoIntent".equalsIgnoreCase(r2_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        s.g(AdDisplayer.m, r0_String.substring(r0_String.indexOf("_") + 1));
                        return true;
                    } else {
                        if (!"adwoSetBackgroundColor".equalsIgnoreCase(r2_String)) {
                            return true;
                        }
                        r0_String = r3_StringTokenizer.nextToken();
                        this.a.e.setBackgroundDrawable(new ColorDrawable(Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue()));
                        return true;
                    }
                } catch (Exception e_2) {
                    e_2.printStackTrace();
                    return true;
                }
            }
        }
        return false;
    }
}