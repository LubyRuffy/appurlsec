package com.flurry.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.qiubai.library.adview.util.AdViewNetFetchThread;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class CatalogActivity extends Activity implements OnClickListener {
    private static volatile String a;
    private WebView b;
    private ai c;
    private List d;
    private ag e;
    private ab f;

    static {
        a = "<html><body><table height='100%' width='100%' border='0'><tr><td style='vertical-align:middle;text-align:center'>No recommendations available<p><button type='input' onClick='activity.finish()'>Back</button></td></tr></table></body></html>";
    }

    public CatalogActivity() {
        this.d = new ArrayList();
    }

    private void a(aj r4_aj) {
        try {
            this.b.loadUrl(r4_aj.b);
            this.c.a(r4_aj.c);
        } catch (Exception e) {
            i.a("FlurryAgent", "Error loading url: " + r4_aj.b);
        }
    }

    public void finish() {
        super.finish();
    }

    public void onClick(View r8_View) {
        if (r8_View instanceof ak) {
            aj r0_aj = new aj();
            r0_aj.a = this.f;
            r0_aj.b = this.b.getUrl();
            r0_aj.c = new ArrayList(this.c.b());
            this.d.add(r0_aj);
            if (this.d.size() > ShareUtils.SHARE_SMS) {
                this.d.remove(0);
            }
            aj r1_aj = new aj();
            ak r0_ak = (ak) r8_View;
            String r2_String = r0_ak.b(this.e.j());
            this.f = r0_ak.a();
            r1_aj.a = r0_ak.a();
            r1_aj.a.a(new r((byte) 4, this.e.k()));
            r1_aj.b = r2_String;
            r1_aj.c = this.c.a(r8_View.getContext());
            a(r1_aj);
        } else if (r8_View.getId() == 10000) {
            finish();
        } else if (r8_View.getId() == 10002) {
            this.c.a();
        } else if (this.d.isEmpty()) {
            finish();
        } else {
            a((aj) this.d.remove(this.d.size() - 1));
        }
    }

    protected void onCreate(Bundle r10_Bundle) {
        String r0_String;
        boolean r6z = true;
        setTheme(16973839);
        super.onCreate(r10_Bundle);
        this.e = FlurryAgent.c();
        Intent r0_Intent = getIntent();
        if (r0_Intent.getExtras() != null) {
            Long r0_Long = Long.valueOf(r0_Intent.getExtras().getLong("o"));
            if (r0_Long != null) {
                this.f = this.e.b(r0_Long.longValue());
            }
        }
        View r0_View = new c(this, this);
        r0_View.setId(1);
        r0_View.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.b = new WebView(this);
        this.b.setId(XListViewHeader.STATE_REFRESHING);
        this.b.setScrollBarStyle(0);
        this.b.setBackgroundColor(-1);
        if (this.f != null) {
            this.b.setWebViewClient(new ac(this));
        }
        this.b.getSettings().setJavaScriptEnabled(r6z);
        this.b.addJavascriptInterface(this, "activity");
        this.c = new ai(this, this);
        this.c.setId(XListViewFooter.STATE_NOMORE);
        View r6_View = new RelativeLayout(this);
        r6_View.setLayoutParams(new LayoutParams(-1, -1));
        LayoutParams r2_LayoutParams = new RelativeLayout.LayoutParams(-1, -2);
        r2_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO, r0_View.getId());
        r6_View.addView(r0_View, r2_LayoutParams);
        r2_LayoutParams = new RelativeLayout.LayoutParams(-1, -2);
        r2_LayoutParams.addRule(XListViewFooter.STATE_NOMORE, r0_View.getId());
        r2_LayoutParams.addRule(XListViewHeader.STATE_REFRESHING, this.c.getId());
        r6_View.addView(this.b, r2_LayoutParams);
        r2_LayoutParams = new RelativeLayout.LayoutParams(-1, -2);
        r2_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH, r0_View.getId());
        r6_View.addView(this.c, r2_LayoutParams);
        Bundle r0_Bundle = getIntent().getExtras();
        r0_String = r0_Bundle == null ? null : r0_Bundle.getString("u");
        if (r0_String == null) {
            this.b.loadDataWithBaseURL(null, a, "text/html", AdViewNetFetchThread.NetEncoding, null);
        } else {
            this.b.loadUrl(r0_String);
        }
        setContentView(r6_View);
    }

    protected void onDestroy() {
        this.e.h();
        super.onDestroy();
    }
}