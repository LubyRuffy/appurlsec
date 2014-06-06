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
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public final class aD extends WebViewClient {
    private /* synthetic */ bj a;

    protected aD(bj r1_bj) {
        this.a = r1_bj;
    }

    public final void onPageFinished(WebView r3_WebView, String r4_String) {
        super.onPageFinished(r3_WebView, r4_String);
        this.a.c.getSettings().setBlockNetworkImage(false);
        try {
            if (this.a.c.canGoBack()) {
                this.a.h.setVisibility(0);
                if (this.a.f != null) {
                    this.a.f.setVisibility(0);
                }
            } else if (this.a.f != null) {
                this.a.f.setVisibility(XListViewFooter.STATE_NODATA);
            }
            if (this.a.c.canGoForward()) {
                this.a.h.setVisibility(0);
                if (this.a.e != null) {
                    this.a.e.setVisibility(0);
                }
            } else if (this.a.e != null) {
                this.a.e.setVisibility(XListViewFooter.STATE_NODATA);
            }
            if (r4_String == null || (!r4_String.equalsIgnoreCase(r4_String))) {
            } else {
                s.a(r3_WebView, this.a.p);
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
                    s.b(bj.j, r11_String);
                    return true;
                } else if (r11_String.endsWith(".apk")) {
                    s.a(r11_String, bj.j, this.a.p, ((byte) ((this.a.q & 240) >> 4)) > 0, this.a.r);
                    return true;
                } else if (r11_String.endsWith(".jpeg") || r11_String.endsWith(".gif") || r11_String.endsWith(".png") || r11_String.endsWith(".bmp") || r11_String.endsWith(Util.PHOTO_DEFAULT_EXT)) {
                    s.a(r11_String, (Activity) bj.j);
                    return true;
                } else {
                    r10_WebView.loadUrl(r11_String);
                    return true;
                }
            } else if (r11_String.startsWith("tel")) {
                s.f(bj.j, r11_String);
                return true;
            } else if (r11_String.startsWith("sms")) {
                s.c(bj.j, r11_String);
                return true;
            } else if (r11_String.startsWith("market")) {
                s.d(bj.j, r11_String);
                return true;
            } else if (r11_String.startsWith("mailto")) {
                s.e(bj.j, r11_String);
                return true;
            } else if (r11_String.startsWith("adwo://")) {
                try {
                    String r2_String = r11_String.substring(ShareUtils.SHARE_COLLECT);
                    StringTokenizer r3_StringTokenizer = new StringTokenizer(r2_String, "&");
                    String r0_String = r3_StringTokenizer.nextToken();
                    if ("adwoOpenURL".equalsIgnoreCase(r0_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        int r4i = Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                        r0_String = r3_StringTokenizer.nextToken();
                        Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                        r0_String = r3_StringTokenizer.nextToken();
                        Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                        r0_String = r3_StringTokenizer.nextToken();
                        r0_String = r0_String.substring(r0_String.indexOf("_") + 1);
                        while (r3_StringTokenizer.hasMoreTokens()) {
                            r0_String = new StringBuilder(String.valueOf(r0_String)).append("&").append(r3_StringTokenizer.nextToken()).toString();
                        }
                        if (r4i == 1) {
                            Intent r1_Intent = new Intent();
                            try {
                                r1_Intent.setComponent(new ComponentName("com.android.browser", "com.android.browser.BrowserActivity"));
                                r1_Intent.setAction("android.intent.action.VIEW");
                                r1_Intent.setData(Uri.parse(r0_String));
                                r1_Intent.addFlags(268435456);
                                bj.j.startActivity(r1_Intent);
                                return true;
                            } catch (Exception e) {
                                r10_WebView.loadUrl(new StringBuilder("javascript:adwoURLOpenFailed(+").append(r2_String).append(");").toString());
                                return true;
                            }
                        } else {
                            if (r4i != 2 || !r0_String.endsWith(".apk")) {
                                return true;
                            }
                            if (((byte) ((this.a.q & 240) >> 4)) > 0) {
                                r1z = true;
                            }
                            s.a(r0_String, bj.j, this.a.p, r1z, this.a.r);
                            return true;
                        }
                    } else if ("adwoVibrate".equalsIgnoreCase(r0_String)) {
                        if (cs.a) {
                            return true;
                        }
                        cs.a = true;
                        Vibrator r0_Vibrator = (Vibrator) bj.j.getSystemService("vibrator");
                        r2_String = r3_StringTokenizer.nextToken();
                        int r2i = Integer.decode(r2_String.substring(r2_String.indexOf("_") + 1)).intValue();
                        if (r2i > 1) {
                            long[] r4_longA = new long[(r2i * 2)];
                            int r3i = 0;
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
                        r10_WebView.postDelayed(new aj(this, r0_Vibrator), (long) (r1i * 1000));
                        return true;
                    } else if ("adwoCloseAd".equalsIgnoreCase(r0_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        r0i = Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                        bj r1_bj = this.a;
                        switch (r0i) {
                            case XListViewHeader.STATE_REFRESHING:
                                r1_bj.a.setAnimationStyle(16973827);
                                break;
                            case XListViewFooter.STATE_NOMORE:
                                r1_bj.a.setAnimationStyle(16973910);
                                break;
                            case ShareUtils.SHARE_SMS:
                            case ShareUtils.SHARE_COPY:
                                r1_bj.a.setAnimationStyle(16973826);
                                break;
                        }
                        this.a.a();
                        return true;
                    } else if ("adwoPlayAudio".equalsIgnoreCase(r0_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                        r0_String = r3_StringTokenizer.nextToken();
                        new Thread(new ak(this, r0_String.substring(r0_String.indexOf("_") + 1))).start();
                        return true;
                    } else if ("adwoPlayVideo".equalsIgnoreCase(r0_String)) {
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
                        Dialog r3_Dialog = new Dialog(bj.j);
                        r3_Dialog.setCancelable(false);
                        r3_Dialog.setCanceledOnTouchOutside(false);
                        r3_Dialog.getWindow().setFlags(Constants.FLAG_HARDWARE_ACCELERATED, Constants.FLAG_HARDWARE_ACCELERATED);
                        r3_Dialog.requestWindowFeature(1);
                        View r4_View = new VideoView(bj.j);
                        r3_Dialog.setOnDismissListener(new am(this, r4_View));
                        r4_View.setMediaController(new MediaController(bj.j));
                        r4_View.setOnCompletionListener(new an(this, r3_Dialog, r10_WebView));
                        r4_View.setOnErrorListener(new ao(this, r3_Dialog));
                        r4_View.setVideoURI(Uri.parse(r2_String));
                        r4_View.start();
                        r3_Dialog.setContentView(r4_View, new LayoutParams(r0i, r1i));
                        r3_Dialog.show();
                        return true;
                    } else if ("adwoCloseVideo".equalsIgnoreCase(r0_String)) {
                        r0_bj = this.a;
                        return true;
                    } else if ("adwoFetchLocation".equalsIgnoreCase(r0_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        m.J = Double.parseDouble(r0_String.substring(r0_String.indexOf("_") + 1));
                        r0_String = r3_StringTokenizer.nextToken();
                        m.K = Double.parseDouble(r0_String.substring(r0_String.indexOf("_") + 1));
                        m.F = true;
                        return true;
                    } else if ("adwoPageDidLoad".equalsIgnoreCase(r0_String)) {
                        cs.b = System.currentTimeMillis();
                        return true;
                    } else if ("adwoAdClickCount".equalsIgnoreCase(r0_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        r10_WebView.loadUrl(new StringBuilder("javascript:").append(r0_String.substring(r0_String.indexOf("_") + 1)).append("();").toString());
                        return true;
                    } else if ("adwoChangeBrowserDoneStatus".equalsIgnoreCase(r0_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        r0i = Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                        if (this.a.g == null) {
                            return true;
                        }
                        if (r0i == 0) {
                            this.a.g.setEnabled(false);
                            return true;
                        } else {
                            this.a.g.setEnabled(true);
                            return true;
                        }
                    } else if ("adwoAddToAddressBook".equalsIgnoreCase(r0_String)) {
                        this.a.c.loadUrl("javascript:adwoDoGetAddressBookInfo();");
                        return true;
                    } else if ("adwoAddToCalendar".equalsIgnoreCase(r0_String)) {
                        this.a.c.loadUrl("javascript:adwoDoGetCalendarEventInfo();");
                        return true;
                    } else if ("adwoAddToReminder".equalsIgnoreCase(r0_String)) {
                        this.a.c.loadUrl("javascript:adwoDoGetReminderInfo();");
                        return true;
                    } else if ("adwoInitEssentialParams".equalsIgnoreCase(r0_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        r0_String = r0_String.substring(r0_String.indexOf("_") + 1);
                        s.a(r10_WebView, this.a.p);
                        if (r10_WebView == null) {
                            return true;
                        }
                        r10_WebView.loadUrl(new StringBuilder("javascript:").append(r0_String).append("+();").toString());
                        return true;
                    } else if ("adwoStartAudioRecorder".equalsIgnoreCase(r0_String)) {
                        this.a.b = new ae(600.0d, null, null, r10_WebView, this.a.p);
                        this.a.b.a();
                        this.a.m.sendEmptyMessageDelayed(ShareUtils.SHARE_COPY, (long) this.a.l);
                        return true;
                    } else if ("adwoCloseAudioRecorder".equalsIgnoreCase(r0_String)) {
                        this.a.b();
                        this.a.m.removeMessages(ShareUtils.SHARE_COPY);
                        return true;
                    } else if ("adwoTakePhoto".equalsIgnoreCase(r0_String)) {
                        if (bj.j.getPackageManager().hasSystemFeature("android.hardware.camera")) {
                            r0_bj = this.a;
                            r10_WebView.loadUrl("javascript:adwoFetchCameraAssociatedImageInfo();");
                            return true;
                        } else {
                            Toast.makeText(bj.j, "\u8bbe\u5907\u6ca1\u6709\u6444\u50cf\u5934\u8c03\u7528\uff01", 0).show();
                            return true;
                        }
                    } else if ("adwoCloseCamera".equalsIgnoreCase(r0_String)) {
                        r0_bj = this.a;
                        return true;
                    } else if ("adwoSaveImg2Gallery".equalsIgnoreCase(r0_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        new Thread(new ap(this, r0_String.substring(r0_String.indexOf("_") + 1))).start();
                        return true;
                    } else if ("adwoVoiceRecord".equalsIgnoreCase(r0_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        double r1d = Double.parseDouble(r0_String.substring(r0_String.indexOf("_") + 1));
                        r0_String = r3_StringTokenizer.nextToken();
                        this.a.b = new ae(r1d, r0_String.substring(r0_String.indexOf("_") + 1), new StringBuilder(String.valueOf(m.O)).append(System.currentTimeMillis()).append("ad").append("woRecord.mp4").toString(), r10_WebView, this.a.p);
                        this.a.b.a();
                        return true;
                    } else if ("adwoVoiceRecordStop".equalsIgnoreCase(r0_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        if (Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue() == 0 || this.a.b == null) {
                            this.a.b();
                            return true;
                        } else {
                            this.a.b.a = null;
                            this.a.b();
                            return true;
                        }
                    } else if ("adwoBrowserDisableScroll".equalsIgnoreCase(r0_String)) {
                        this.a.c.setVerticalScrollBarEnabled(false);
                        this.a.c.setHorizontalScrollBarEnabled(false);
                        return true;
                    } else if ("adwoBrowserShowIndicator".equalsIgnoreCase(r0_String)) {
                        this.a.i = new ProgressBar(bj.j);
                        ViewGroup.LayoutParams r0_ViewGroup_LayoutParams = new LayoutParams(-2, -2);
                        r0_ViewGroup_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER);
                        this.a.i.setLayoutParams(r0_ViewGroup_LayoutParams);
                        this.a.d.addView(this.a.i);
                        return true;
                    } else if ("adwoBrowserHideIndicator".equalsIgnoreCase(r0_String)) {
                        if (this.a.d == null) {
                            return true;
                        }
                        this.a.d.removeView(this.a.i);
                        return true;
                    } else if ("adwoIntent".equalsIgnoreCase(r0_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        s.g(bj.j, r0_String.substring(r0_String.indexOf("_") + 1));
                        return true;
                    } else {
                        if (!"adwoSetBackgroundColor".equalsIgnoreCase(r0_String)) {
                            return true;
                        }
                        r0_String = r3_StringTokenizer.nextToken();
                        this.a.a.setBackgroundDrawable(new ColorDrawable(Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue()));
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