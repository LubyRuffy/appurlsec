package com.zkmm.adsdk;

import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout.LayoutParams;
import com.tencent.mm.sdk.platformtools.Util;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
final class a extends Handler {
    private /* synthetic */ cs a;

    a(cs r1_cs) {
        this.a = r1_cs;
    }

    public final void handleMessage(Message r10_Message) {
        super.handleMessage(r10_Message);
        switch (r10_Message.what) {
            case XListViewHeader.STATE_READY:
                if (cs.a(this.a) != null) {
                    cs.a(this.a).cancel();
                    cs.a(this.a, null);
                }
                cs.a = false;
                break;
            case XListViewHeader.STATE_REFRESHING:
                cs.a = true;
                new b(this, (String) r10_Message.obj).start();
                cs r0_cs = this.a;
                cs.b(r0_cs, cs.b(r0_cs) + 1);
                break;
            case XListViewFooter.STATE_NOMORE:
                try {
                    int r1i = ZKMMAdView.b();
                    int r2i = ZKMMAdView.a();
                    AdStatusListener r0_AdStatusListener = ((ZKMMAdView) this.a.getParent()).d();
                    if (r0_AdStatusListener != null) {
                        r0_AdStatusListener.onAdResumeRequesting();
                    }
                    this.a.setLayoutParams(new LayoutParams(r2i, r1i));
                    Animation r0_Animation = new TranslateAnimation(0.1f, 1.0f, 0.1f, 1.0f);
                    r0_Animation.setDuration(Util.MILLSECONDS_OF_SECOND);
                    this.a.setAnimation(r0_Animation);
                    this.a.requestLayout();
                    this.a.loadUrl("javascript:adwoDoBannerRestoreComplete();");
                    if (cs.e(this.a) != 0) {
                        s.a(new StringBuilder(String.valueOf(cs.f(this.a).a)).append("=").append(cs.f(this.a).k).append("=0").toString(), new StringBuilder(String.valueOf(System.currentTimeMillis() - cs.e(this.a))).toString(), this.a.getContext());
                        s.a(cs.f(this.a).k, new StringBuilder(String.valueOf(m.J)).append("=").append(m.K).append("=").append(cs.f(this.a).a).toString(), this.a.getContext());
                        cs.a(this.a, 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cs.f(this.a).j = false;
                break;
            case XListViewFooter.STATE_NODATA:
                AdDisplayer.getInstance(this.a.getContext()).dismissDisplayer();
                break;
            case ShareUtils.SHARE_SMS:
                cs.g(this.a);
                this.a.setVisibility(0);
                cs.a = false;
                break;
            case ShareUtils.SHARE_COPY:
                if (cs.h(this.a) != null) {
                    this.a.loadUrl(new StringBuilder("javascript:adwoFetchAudioPower(").append(((double) cs.h(this.a).c()) / 32767.0d).append(");").toString());
                    cs.d(this.a).sendEmptyMessageDelayed(ShareUtils.SHARE_COPY, (long) cs.i(this.a));
                }
                break;
        }
    }
}