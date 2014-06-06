package qsbk.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.flurry.android.FlurryAgent;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.base.MysBaseActivity;
import qsbk.app.adapter.ArticleAdapter;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.loader.OnAsyncLoadListener;
import qsbk.app.model.Article;
import qsbk.app.utils.Base64;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpUtils;

public class Qiushibaike extends Activity implements OnClickListener {
    ArticleAdapter a;
    ArrayList<Article> b;
    String c;
    protected int d;
    Tracker e;
    OnTouchListener f;
    Handler g;
    OnAsyncLoadListener h;
    private int i;
    private LinearLayout j;
    private ImageButton k;
    private boolean l;
    private ImageView m;
    public int maxLoadMorePage;
    private RelativeLayout n;
    private LinearLayout o;
    private Button p;
    private LinearLayout q;

    public Qiushibaike() {
        this.b = new ArrayList();
        this.c = RContactStorage.PRIMARY_KEY;
        this.maxLoadMorePage = 1;
        this.l = true;
        this.d = 1;
        this.f = new e(this);
        this.g = new f(this);
        this.h = new g(this);
    }

    private void a() {
        Animation r1_Animation;
        Animation r0_Animation;
        boolean r1z = false;
        if (this.l) {
            this.m.setVisibility(r1z);
            this.j.setVisibility(r1z);
            this.l = r1z;
            r1_Animation = AnimationUtils.loadAnimation(this, 17432576);
            r0_Animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_up);
        } else {
            this.m.setVisibility(Base64.DONT_BREAK_LINES);
            this.j.setVisibility(Base64.DONT_BREAK_LINES);
            this.k.setBackgroundDrawable(null);
            r1_Animation = AnimationUtils.loadAnimation(this, 17432577);
            r0_Animation = AnimationUtils.loadAnimation(this, R.anim.fade_out_down);
            this.l = true;
        }
        r1_Animation.setDuration(200);
        r0_Animation.setFillAfter(true);
        this.m.startAnimation(r1_Animation);
        this.j.startAnimation(r0_Animation);
    }

    private void a(String r6_String) {
        try {
            JSONArray r2_JSONArray = new JSONObject(r6_String).getJSONArray("items");
            int r3i = r2_JSONArray.length();
            if (this.c.equals(MysBaseActivity.TOP_REFRESH)) {
                this.b.clear();
            }
            int r1i = 0;
            while (r1i < r3i) {
                try {
                    JSONObject r0_JSONObject = r2_JSONArray.optJSONObject(r1i);
                    if (r0_JSONObject != null) {
                        this.b.add(new Article(r0_JSONObject));
                    }
                } catch (QiushibaikeException e) {
                    QiushibaikeException r0_QiushibaikeException = e;
                    DebugUtil.debug("\u4e0b\u9762\u7684\u6570\u636e\u89e3\u6790\u9519\u8bef");
                    DebugUtil.debug(r0_QiushibaikeException.getMessage());
                }
                r1i++;
            }
        } catch (JSONException e_2) {
            e_2.printStackTrace();
        }
    }

    public void initWidget() {
    }

    public void onClick(View r1_View) {
    }

    public void onCreate(Bundle r5_Bundle) {
        super.onCreate(r5_Bundle);
        EasyTracker.getInstance().setContext(this);
        this.e = EasyTracker.getTracker();
        this.e.setAppVersion(Constants.localVersionName);
        QsbkApp.getInstance().setSampleRate(this.e);
        this.i = getWindowManager().getDefaultDisplay().getHeight();
        initWidget();
        if (HttpUtils.netIsAvailable()) {
            new AsyncDataLoader(this.h, "qsbk-AT-main01").execute(new Void[0]);
        } else {
            this.n.setVisibility(Base64.DONT_BREAK_LINES);
            this.o.setVisibility(0);
            this.p.setVisibility(0);
            this.p.setText("\u91cd\u65b0\u52a0\u8f7d");
            this.q.setVisibility(Base64.DONT_BREAK_LINES);
        }
    }

    protected void onPause() {
        FlurryAgent.onEndSession(this);
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStart() {
        FlurryAgent.onStartSession(this, "LLLGV7Y72RGDIMUHII8Z");
        super.onStart();
    }
}