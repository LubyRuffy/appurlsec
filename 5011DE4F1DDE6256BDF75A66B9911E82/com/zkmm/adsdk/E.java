package com.zkmm.adsdk;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import android.widget.VideoView;
import com.tencent.mm.sdk.platformtools.Util;
import java.util.StringTokenizer;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.image.ImageFetcher;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public final class E extends WebViewClient {
    private /* synthetic */ cs a;

    protected E(cs r1_cs) {
        this.a = r1_cs;
    }

    public final void onPageFinished(WebView r3_WebView, String r4_String) {
        try {
            this.a.setVisibility(0);
            if (this.a.e == null || this.a.e.b() == null || (!this.a.e.b().equalsIgnoreCase(r4_String))) {
            } else {
                s.a(this.a, this.a.e.a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView r11_WebView, String r12_String) {
        double r8d = 1000.0d;
        boolean r1z = false;
        if (r12_String != null) {
            boolean r0z;
            if (r12_String.startsWith(ImageFetcher.HTTP_CACHE_DIR) || r12_String.startsWith("https")) {
                if (r12_String.endsWith(".mp3") || r12_String.endsWith(".wav") || r12_String.endsWith(".mp4") || r12_String.endsWith(".3gp") || r12_String.endsWith(".mpeg")) {
                    s.b(this.a.getContext(), r12_String);
                    return true;
                } else if (r12_String.endsWith(".apk")) {
                    r0z = ((byte) ((this.a.e.m & 240) >> 4)) > 0;
                    Context r1_Context = this.a.getContext();
                    boolean r2z = this.a.e.h;
                    s.a(r12_String, r1_Context, this.a.e.a, r0z, this.a.e.l);
                    return true;
                } else if (r12_String.endsWith(".jpeg") || r12_String.endsWith(".gif") || r12_String.endsWith(".png") || r12_String.endsWith(".bmp") || r12_String.endsWith(Util.PHOTO_DEFAULT_EXT)) {
                    s.a(r12_String, (Activity) this.a.getContext());
                    return true;
                } else {
                    this.a.loadUrl(r12_String);
                    return true;
                }
            } else if (r12_String.startsWith("tel")) {
                s.f(this.a.getContext(), r12_String);
                return true;
            } else if (r12_String.startsWith("sms")) {
                s.c(this.a.getContext(), r12_String);
                return true;
            } else if (r12_String.startsWith("market")) {
                s.d(this.a.getContext(), r12_String);
                return true;
            } else if (r12_String.startsWith("mailto")) {
                s.e(this.a.getContext(), r12_String);
                return true;
            } else if (r12_String.startsWith("adwo://")) {
                cs.n.setDuration(500);
                this.a.startAnimation(cs.n);
                try {
                    String r0_String = r12_String.substring(ShareUtils.SHARE_COLLECT);
                    StringTokenizer r3_StringTokenizer = new StringTokenizer(r0_String, "&");
                    String r2_String = r3_StringTokenizer.nextToken();
                    if ("adwoOpenURL".equalsIgnoreCase(r2_String)) {
                        if (m.Q && s.c(this.a.getContext()) == 0) {
                            Toast.makeText(this.a.getContext(), "\u7f51\u7edc\u4e0d\u7ed9\u529b...", 0).show();
                            return true;
                        } else {
                            r2_String = r3_StringTokenizer.nextToken();
                            int r4i = Integer.decode(r2_String.substring(r2_String.indexOf("_") + 1)).intValue();
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
                                    this.a.getContext().startActivity(r1_Intent);
                                    return true;
                                } catch (Exception e) {
                                    this.a.loadUrl(new StringBuilder("javascript:adwoURLOpenFailed(+").append(r0_String).append(");").toString());
                                    return true;
                                }
                            } else if (r4i == 0) {
                                byte[] r0_byteA = bz.a(r4i);
                                if (r0_byteA[1] != 0) {
                                    this.a.p.sendEmptyMessageDelayed(XListViewFooter.STATE_NODATA, (long) bz.a(r0_byteA[2], r0_byteA[3]));
                                }
                                bj.o = null;
                                bj.a(this.a.getContext()).a((View)r11_WebView, r2_String, this.a.e.a, this.a.e.m, this.a.e.l);
                                cs.b = 0;
                                cs.c = this.a.e.a;
                                cs.d = this.a.e.k;
                                cs.a = true;
                                return true;
                            } else {
                                if (r4i != 2 || !r2_String.endsWith(".apk")) {
                                    return true;
                                }
                                if (((byte) ((this.a.e.m & 240) >> 4)) > 0) {
                                    r1z = true;
                                }
                                Context r0_Context = this.a.getContext();
                                boolean r3z = this.a.e.h;
                                s.a(r2_String, r0_Context, this.a.e.a, r1z, this.a.e.l);
                                return true;
                            }
                        }
                    } else if ("adwoBannerExpand".equalsIgnoreCase(r2_String)) {
                        this.a.loadUrl("javascript:adwoDoExpand();");
                        r0_String = r3_StringTokenizer.nextToken();
                        r0i = Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                        r1_String = r3_StringTokenizer.nextToken();
                        r1i = Integer.decode(r1_String.substring(r1_String.indexOf("_") + 1)).intValue();
                        r2_String = r3_StringTokenizer.nextToken();
                        Animation r3_Animation = new TranslateAnimation(1.0f, 0.1f, 1.0f, 0.1f);
                        r3_Animation.setDuration((long) ((int) (Double.valueOf(r2_String.substring(r2_String.indexOf("_") + 1)).doubleValue() * r8d)));
                        this.a.setAnimation(r3_Animation);
                        this.a.o = System.currentTimeMillis();
                        this.a.setLayoutParams(new LayoutParams((int) (((double) r0i) * m.L), (int) (((double) r1i) * m.L)));
                        this.a.requestLayout();
                        this.a.loadUrl("javascript:adwoDoExpandComplete();");
                        this.a.e.j = true;
                        try {
                            AdStatusListener r0_AdStatusListener = ((ZKMMAdView) this.a.getParent()).d();
                            if (r0_AdStatusListener == null) {
                                return true;
                            }
                            r0_AdStatusListener.onAdStopRequesting();
                            return true;
                        } catch (Exception e_2) {
                            e_2.printStackTrace();
                            return true;
                        }
                    } else if ("adwoExpandRestore".equals(r2_String)) {
                        this.a.loadUrl("javascript:adwoDoBannerRestore();");
                        r0_String = r3_StringTokenizer.nextToken();
                        this.a.p.sendEmptyMessageDelayed(XListViewFooter.STATE_NOMORE, (long) ((int) (Double.valueOf(r0_String.substring(r0_String.indexOf("_") + 1)).doubleValue() * 1000.0d)));
                        return true;
                    } else if ("adwoVibrate".equalsIgnoreCase(r2_String)) {
                        if (cs.a) {
                            return true;
                        }
                        cs.a = true;
                        try {
                            this.a.g = (Vibrator) this.a.getContext().getSystemService("vibrator");
                            r0_String = r3_StringTokenizer.nextToken();
                            r0z = Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                            if (r0z > true) {
                                long[] r3_longA = new long[(r0z * 2)];
                                r2i = 0;
                                while (r1z < r0z) {
                                    r3_longA[r2i] = 100;
                                    r2i++;
                                    r3_longA[r2i] = 500;
                                    r2i++;
                                    r1z++;
                                }
                                this.a.g.vibrate(r3_longA, r0z);
                            } else {
                                long[] r0_longA = new long[2];
                                r0_longA[1] = 1000;
                                this.a.g.vibrate(r0_longA, 1);
                                r0z = true;
                            }
                            this.a.p.sendEmptyMessageDelayed(1, (long) (r0z * 1000));
                            return true;
                        } catch (Exception e_3) {
                            e_3.printStackTrace();
                            return true;
                        }
                    } else if ("adwoPlayAudio".equalsIgnoreCase(r2_String)) {
                        if (cs.a) {
                            return true;
                        }
                        r0_String = r3_StringTokenizer.nextToken();
                        this.a.k = Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                        if (this.a.k <= 0) {
                            this.a.k = 1;
                        }
                        r0_String = r3_StringTokenizer.nextToken();
                        r0_String = r0_String.substring(r0_String.indexOf("_") + 1);
                        Message r1_Message = this.a.p.obtainMessage(XListViewHeader.STATE_REFRESHING);
                        r1_Message.obj = r0_String;
                        this.a.p.dispatchMessage(r1_Message);
                        return true;
                    } else if ("adwoPlayVideo".equalsIgnoreCase(r2_String)) {
                        if (cs.a) {
                            return true;
                        }
                        cs.g(this.a);
                        cs.a = true;
                        this.a.h = new VideoView(this.a.getContext());
                        this.a.h.setOnCompletionListener(new f(this));
                        this.a.h.setMediaController(new MediaController(this.a.getContext()));
                        r0_String = r3_StringTokenizer.nextToken();
                        Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                        r0_String = r3_StringTokenizer.nextToken();
                        r0i = Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue();
                        r1_String = r3_StringTokenizer.nextToken();
                        r1i = Integer.decode(r1_String.substring(r1_String.indexOf("_") + 1)).intValue();
                        r2_String = r3_StringTokenizer.nextToken();
                        r2i = Integer.decode(r2_String.substring(r2_String.indexOf("_") + 1)).intValue();
                        String r3_String = r3_StringTokenizer.nextToken();
                        r3_String = r3_String.substring(r3_String.indexOf("_") + 1);
                        if (r3_String == null) {
                            return true;
                        }
                        this.a.h.setVideoURI(Uri.parse(r3_String));
                        ViewGroup.LayoutParams r3_ViewGroup_LayoutParams = new LayoutParams((int) (((double) r1i) * m.L), (int) (((double) r2i) * m.L));
                        r3_ViewGroup_LayoutParams.topMargin = (int) (((double) r0i) * m.L);
                        this.a.h.setLayoutParams(r3_ViewGroup_LayoutParams);
                        ((ZKMMAdView) this.a.getParent()).addView(this.a.h);
                        this.a.h.start();
                        return true;
                    } else if ("adwoCloseVideo".equalsIgnoreCase(r2_String)) {
                        this.a.p.sendEmptyMessage(ShareUtils.SHARE_SMS);
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
                        new Thread(new l(this.a.e)).start();
                        try {
                            r0_String = r3_StringTokenizer.nextToken();
                            this.a.loadUrl(new StringBuilder("javascript:").append(r0_String.substring(r0_String.indexOf("_") + 1)).append("();").toString());
                            return true;
                        } catch (Exception e_4) {
                            e_4.printStackTrace();
                            return true;
                        }
                    } else if ("adwoAddToAddressBook".equalsIgnoreCase(r2_String)) {
                        this.a.loadUrl("javascript:adwoDoGetAddressBookInfo();");
                        return true;
                    } else if ("adwoAddToCalendar".equalsIgnoreCase(r2_String)) {
                        this.a.loadUrl("javascript:adwoDoGetCalendarEventInfo();");
                        return true;
                    } else if ("adwoAddToReminder".equalsIgnoreCase(r2_String)) {
                        this.a.loadUrl("javascript:adwoDoGetReminderInfo();");
                        return true;
                    } else if ("adwoInitEssentialParams".equalsIgnoreCase(r2_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        r0_String = r0_String.substring(r0_String.indexOf("_") + 1);
                        s.a(r11_WebView, this.a.e.a);
                        if (this.a == null) {
                            return true;
                        }
                        this.a.loadUrl(new StringBuilder("javascript:").append(r0_String).append("+();").toString());
                        return true;
                    } else if ("adwoStartAudioRecorder".equalsIgnoreCase(r2_String)) {
                        this.a.j = new ae(600.0d, null, null, r11_WebView, this.a.e.a);
                        this.a.j.a();
                        this.a.p.sendEmptyMessageDelayed(ShareUtils.SHARE_COPY, (long) this.a.m);
                        return true;
                    } else if ("adwoCloseAudioRecorder".equalsIgnoreCase(r2_String)) {
                        this.a.c();
                        return true;
                    } else if ("adwoTakePhoto".equalsIgnoreCase(r2_String)) {
                        if (this.a.getContext().getPackageManager().hasSystemFeature("android.hardware.camera")) {
                            cs.a(this.a, r3_StringTokenizer);
                            this.a.loadUrl("javascript:adwoFetchCameraAssociatedImageInfo();");
                            return true;
                        } else {
                            Toast.makeText(this.a.getContext(), "\u8bbe\u5907\u6ca1\u6709\u6444\u50cf\u5934\u8c03\u7528\uff01", 0).show();
                            return true;
                        }
                    } else if ("adwoCloseCamera".equalsIgnoreCase(r2_String)) {
                        if (this.a.i == null) {
                            return true;
                        }
                        this.a.i.dismiss();
                        this.a.i = null;
                        return true;
                    } else if ("adwoShareToWeibo".equalsIgnoreCase(r2_String)) {
                        r11_WebView.loadUrl("javascript:adwoSinaWeiboInfo();");
                        return true;
                    } else if ("adwoSaveImg2Gallery".equalsIgnoreCase(r2_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        new Thread(new h(this, r0_String.substring(r0_String.indexOf("_") + 1))).start();
                        return true;
                    } else if ("adwoVoiceRecord".equalsIgnoreCase(r2_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        double r1d = Double.parseDouble(r0_String.substring(r0_String.indexOf("_") + 1));
                        r0_String = r3_StringTokenizer.nextToken();
                        this.a.j = new ae(r1d, r0_String.substring(r0_String.indexOf("_") + 1), new StringBuilder(String.valueOf(m.O)).append(System.currentTimeMillis()).append("ad").append("woRecord.mp4").toString(), r11_WebView, this.a.e.a);
                        this.a.j.a();
                        return true;
                    } else if ("adwoVoiceRecordStop".equalsIgnoreCase(r2_String)) {
                        r0_String = r3_StringTokenizer.nextToken();
                        if (Integer.decode(r0_String.substring(r0_String.indexOf("_") + 1)).intValue() == 0 || this.a.j == null) {
                            this.a.c();
                            return true;
                        } else {
                            this.a.j.a = null;
                            this.a.c();
                            return true;
                        }
                    } else if ("adwoBrowserDisableScroll".equalsIgnoreCase(r2_String)) {
                        r11_WebView.setVerticalScrollBarEnabled(false);
                        r11_WebView.setHorizontalScrollBarEnabled(false);
                        return true;
                    } else {
                        if (!"adwoIntent".equalsIgnoreCase(r2_String)) {
                            return true;
                        }
                        r0_String = r3_StringTokenizer.nextToken();
                        s.g(this.a.getContext(), r0_String.substring(r0_String.indexOf("_") + 1));
                        return true;
                    }
                } catch (Exception e_5) {
                    e_5.printStackTrace();
                    return true;
                }
            }
        }
        return false;
    }
}