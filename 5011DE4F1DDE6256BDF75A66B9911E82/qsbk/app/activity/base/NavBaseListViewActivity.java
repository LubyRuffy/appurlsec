package qsbk.app.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import com.qiubai.library.adview.AdViewLayout;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.Util;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.adapter.ArticleAdapter;
import qsbk.app.cache.FileCache;
import qsbk.app.cache.MemoryCache;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.loader.OnAsyncLoadListener;
import qsbk.app.model.Article;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.Md5;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.listview.XListView;
import qsbk.app.widget.listview.XListView.IXListViewListener;

public abstract class NavBaseListViewActivity extends BaseActivity implements IVotePoint, IXListViewListener {
    public String BOTTOM_BUTTON_REFRESH;
    public int DEFAULT_REFRESH_INTERVAL;
    public String TOP_REFRESH;
    public Long lastRefreshFirstPageTime;
    protected String n;
    protected ArticleAdapter o;
    protected ArrayList<Object> p;
    protected XListView q;
    protected int r;
    protected boolean s;
    protected boolean t;
    private String u;
    private boolean v;

    class a implements OnAsyncLoadListener {
        private String b;
        private String c;
        private String d;

        public a(String r3_String) {
            this.b = RContactStorage.PRIMARY_KEY;
            this.c = RContactStorage.PRIMARY_KEY;
            this.d = RContactStorage.PRIMARY_KEY;
            this.d = r3_String;
        }

        public void onFinishListener(String r5_String) {
            if (TextUtils.isEmpty(r5_String) || this.d.equals(MysBaseActivity.PRE)) {
                if (this.d.equals(MysBaseActivity.PRE)) {
                    MemoryCache.findOrCreateMemoryCache().put(Md5.MD5(this.b), r5_String);
                    r0_NavBaseListViewActivity = NavBaseListViewActivity.this;
                    r0_NavBaseListViewActivity.r++;
                    this.c = null;
                }
                NavBaseListViewActivity.this.h();
            } else {
                NavBaseListViewActivity.this.b(r5_String);
                if (NavBaseListViewActivity.this.r != 1 || NavBaseListViewActivity.this.p.size() <= 0) {
                    r0_NavBaseListViewActivity = NavBaseListViewActivity.this;
                    r0_NavBaseListViewActivity.r++;
                    this.c = null;
                    if (this.d.equals(MysBaseActivity.PRE) || NavBaseListViewActivity.this.p.size() <= 0) {
                        if (this.d.equals(MysBaseActivity.PRE)) {
                            NavBaseListViewActivity.this.h();
                        } else {
                            MemoryCache.findOrCreateMemoryCache().put(Md5.MD5(this.b), r5_String);
                            r0_NavBaseListViewActivity = NavBaseListViewActivity.this;
                            r0_NavBaseListViewActivity.r++;
                            this.c = null;
                            NavBaseListViewActivity.this.h();
                        }
                    } else {
                        new AsyncDataLoader(NavBaseListViewActivity.this.getOnAsyncLoadListener(MysBaseActivity.PRE), "qsbk-AT-NBLVA-pre").execute(new Void[0]);
                        if (this.d.equals(MysBaseActivity.PRE)) {
                            MemoryCache.findOrCreateMemoryCache().put(Md5.MD5(this.b), r5_String);
                            r0_NavBaseListViewActivity = NavBaseListViewActivity.this;
                            r0_NavBaseListViewActivity.r++;
                            this.c = null;
                        }
                        NavBaseListViewActivity.this.h();
                    }
                } else {
                    FileCache.cacheTextToDisk(NavBaseListViewActivity.this, NavBaseListViewActivity.this.getCacheUniqueName(), r5_String);
                    r0_NavBaseListViewActivity = NavBaseListViewActivity.this;
                    r0_NavBaseListViewActivity.r++;
                    this.c = null;
                    if (this.d.equals(MysBaseActivity.PRE) || NavBaseListViewActivity.this.p.size() <= 0) {
                        if (this.d.equals(MysBaseActivity.PRE)) {
                            NavBaseListViewActivity.this.h();
                        } else {
                            MemoryCache.findOrCreateMemoryCache().put(Md5.MD5(this.b), r5_String);
                            r0_NavBaseListViewActivity = NavBaseListViewActivity.this;
                            r0_NavBaseListViewActivity.r++;
                            this.c = null;
                            NavBaseListViewActivity.this.h();
                        }
                    } else {
                        new AsyncDataLoader(NavBaseListViewActivity.this.getOnAsyncLoadListener(MysBaseActivity.PRE), "qsbk-AT-NBLVA-pre").execute(new Void[0]);
                        if (this.d.equals(MysBaseActivity.PRE)) {
                            MemoryCache.findOrCreateMemoryCache().put(Md5.MD5(this.b), r5_String);
                            r0_NavBaseListViewActivity = NavBaseListViewActivity.this;
                            r0_NavBaseListViewActivity.r++;
                            this.c = null;
                        }
                        NavBaseListViewActivity.this.h();
                    }
                }
            }
        }

        public void onPrepareListener() {
            StringBuffer r0_StringBuffer = new StringBuffer(NavBaseListViewActivity.this.getTargetDataUrl(this.d)).append("?page=").append(NavBaseListViewActivity.this.r);
            if (UIHelper.isNightTheme()) {
                r0_StringBuffer.append("&theme=night");
            }
        }

        public String onStartListener() {
            try {
                this.c = MemoryCache.findOrCreateMemoryCache().get(Md5.MD5(this.b));
                if (TextUtils.isEmpty(this.c)) {
                    this.c = HttpClient.getIntentce().get(this.b);
                }
            } catch (QiushibaikeException e) {
                e.printStackTrace();
            }
            return this.c;
        }
    }

    public NavBaseListViewActivity() {
        this.u = "NavBaseListViewActivity";
        this.TOP_REFRESH = MysBaseActivity.TOP_REFRESH;
        this.BOTTOM_BUTTON_REFRESH = MysBaseActivity.BOTTOM_BUTTON_REFRESH;
        this.DEFAULT_REFRESH_INTERVAL = 60000;
        this.n = RContactStorage.PRIMARY_KEY;
        this.lastRefreshFirstPageTime = null;
        this.p = new ArrayList();
        this.r = 1;
        this.s = false;
        this.t = false;
        this.v = true;
    }

    private void i() {
        new AsyncDataLoader(getOnAsyncLoadListener(MysBaseActivity.LOAD), "qsbk-AT-NBLVA-01").execute(new Void[0]);
        this.lastRefreshFirstPageTime = Long.valueOf(System.currentTimeMillis());
    }

    private void j() {
        RelativeLayout r0_RelativeLayout = (RelativeLayout) findViewById(R.id.adLayout);
        if (r0_RelativeLayout != null) {
            r0_RelativeLayout.addView(new AdViewLayout(this, Constants.AD_KEY));
            r0_RelativeLayout.invalidate();
        }
    }

    protected void b(String r6_String) {
        try {
            JSONArray r2_JSONArray = new JSONObject(r6_String).getJSONArray("items");
            int r0i = r2_JSONArray.length();
            if (r0i == 0) {
                this.s = true;
            }
            int r1i;
            if (this.n.equals(this.TOP_REFRESH) || this.r == 1) {
                this.p.clear();
                if (HttpUtils.netIsAvailable()) {
                    r0i = 10;
                    r1i = 0;
                    while (r1i < r0i) {
                        if (r2_JSONArray.optJSONObject(r1i) != null) {
                            r1i++;
                        } else {
                            this.p.add(new Article(r2_JSONArray.optJSONObject(r1i)));
                            r1i++;
                        }
                    }
                } else {
                    r1i = 0;
                    while (r1i < r0i) {
                        try {
                            if (r2_JSONArray.optJSONObject(r1i) != null) {
                                this.p.add(new Article(r2_JSONArray.optJSONObject(r1i)));
                            }
                        } catch (QiushibaikeException e) {
                        }
                        r1i++;
                    }
                }
            } else {
                if (HttpUtils.netIsAvailable()) {
                    r0i = 10;
                }
                r1i = 0;
                while (r1i < r0i) {
                    if (r2_JSONArray.optJSONObject(r1i) != null) {
                        this.p.add(new Article(r2_JSONArray.optJSONObject(r1i)));
                    }
                    r1i++;
                }
            }
        } catch (Exception e_2) {
            e_2.printStackTrace();
        }
    }

    protected void f() {
        this.q.initData();
    }

    public abstract String getCacheUniqueName();

    public OnAsyncLoadListener getOnAsyncLoadListener(String r2_String) {
        return new a(r2_String);
    }

    public abstract String getTargetDataUrl(String r1_String);

    protected void h() {
        this.q.stopRefresh();
        this.q.stopLoadMore();
        this.q.setRefreshTime("\u521a\u521a");
        if (this.p.size() <= 0) {
            this.q.loadNoMore();
        }
        this.o.notifyDataSetChanged();
    }

    protected void onCreate(Bundle r1_Bundle) {
        super.onCreate(r1_Bundle);
        f();
        j();
    }

    public void onInitHistoryData() {
    }

    public void onLoadMore() {
        this.n = this.BOTTOM_BUTTON_REFRESH;
        new AsyncDataLoader(getOnAsyncLoadListener(MysBaseActivity.LOAD), "qsbk-AT-NBLVA-more").execute(new Void[0]);
    }

    public void onRefresh() {
        this.n = this.TOP_REFRESH;
        if (this.v) {
            String r0_String = FileCache.getContentFromDisk(this, getCacheUniqueName());
            if (!TextUtils.isEmpty(r0_String)) {
                b(r0_String);
                this.o.notifyDataSetChanged();
            }
        }
        if (HttpUtils.netIsAvailable()) {
            this.r = 1;
            if (this.lastRefreshFirstPageTime == null) {
                i();
            } else {
                Long r0_Long = Long.valueOf(System.currentTimeMillis());
                if (this.lastRefreshFirstPageTime == null || r0_Long.longValue() - this.lastRefreshFirstPageTime.longValue() > ((long) this.DEFAULT_REFRESH_INTERVAL)) {
                    i();
                } else {
                    new Handler().postDelayed(new am(this), Util.MILLSECONDS_OF_SECOND);
                }
            }
            this.v = false;
        } else {
            h();
            ToastAndDialog.makeText((Context)this, (int)R.string.network_not_connected).show();
        }
    }

    protected void onStop() {
        super.onStop();
    }
}