package qsbk.app.activity.base;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.LoginActivity;
import qsbk.app.adapter.DefaultAdapter;
import qsbk.app.adapter.MyCollectionsAdapter;
import qsbk.app.cache.FileCache;
import qsbk.app.cache.MemoryCache;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.loader.OnAsyncLoadListener;
import qsbk.app.model.Article;
import qsbk.app.push.Utils;
import qsbk.app.share.AuthorizeActivity;
import qsbk.app.share.QQAuthorizeActivity;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.share.ShareAble;
import qsbk.app.share.ShareUtils;
import qsbk.app.share.WXEntryActivity;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.Md5;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.listview.XListView;
import qsbk.app.widget.listview.XListView.IXListViewListener;
import qsbk.app.widget.listview.XListView.OnXScrollListener;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public abstract class MysBaseActivity extends BaseActionBarActivity implements IVotePoint, ShareAble, IXListViewListener, OnXScrollListener {
    private static final String B;
    public static final String BOTTOM_BUTTON_REFRESH = "bottom_button_refresh";
    public static final int DEFAULT_REFRESH_INTERVAL = 60000;
    private static final Handler E;
    public static final String LOAD = "load";
    public static final String PRE = "pre";
    public static final int REQ_CODE_SHARE = 20;
    public static final String TOP_REFRESH = "top_refresh";
    protected View A;
    private boolean C;
    private boolean D;
    private View F;
    public int _firstVisibleItem;
    public int _totalItemCount;
    public int _visibleItemCount;
    public Long lastRefreshFirstPageTime;
    protected String o;
    protected DefaultAdapter p;
    protected ArrayList<Object> q;
    protected ArrayList<Object> r;
    protected XListView s;
    protected int t;
    protected boolean u;
    protected int v;
    protected boolean w;
    protected boolean x;
    protected boolean y;
    protected Article z;

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

        public void onFinishListener(String r6_String) {
            if (TextUtils.isEmpty(r6_String) || this.d.equals(PRE)) {
                if (!this.d.equals(PRE)) {
                    this.c = null;
                }
            } else {
                if (MysBaseActivity.this.t == 1) {
                    FileCache.cacheTextToDisk(MysBaseActivity.this, MysBaseActivity.this.getCacheUniqueName(), r6_String);
                }
                MysBaseActivity.this.b(r6_String);
                MysBaseActivity r0_MysBaseActivity = MysBaseActivity.this;
                r0_MysBaseActivity.t++;
                MysBaseActivity.this.lastRefreshFirstPageTime = Long.valueOf(System.currentTimeMillis());
                ListViewHelper.saveLastUpdateTime(MysBaseActivity.this, MysBaseActivity.this.getCacheUniqueName());
                this.c = null;
                if (this.d.equals(PRE) || MysBaseActivity.this.q.size() <= 0) {
                    MysBaseActivity.this.b(0);
                    MysBaseActivity.this.y = false;
                } else {
                    new AsyncDataLoader(MysBaseActivity.this.getOnAsyncLoadListener(PRE), "qsbk-AT-BGA-pre1").execute(new Void[0]);
                    MysBaseActivity.this.b(0);
                    MysBaseActivity.this.y = false;
                }
                if (this.d.equals(PRE)) {
                } else {
                    this.c = null;
                }
            }
        }

        public void onPrepareListener() {
            StringBuffer r1_StringBuffer = new StringBuffer(MysBaseActivity.this.getTargetDataUrl(this.d)).append("?page=").append(MysBaseActivity.this.t);
            if (UIHelper.isNightTheme()) {
                r1_StringBuffer.append("&theme=night");
            }
            r1_StringBuffer.append("&count=").append(Constants.pageCount);
            if (MysBaseActivity.this.getTargetDataUrl(this.d).contains("nearby")) {
                try {
                    r1_StringBuffer.append("&n=" + new JSONObject(SharePreferenceUtils.getSharePreferencesValue("loc")).getInt("code"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            this.b = r1_StringBuffer.toString();
        }

        public String onStartListener() {
            try {
                this.c = MemoryCache.findOrCreateMemoryCache().get(Md5.MD5(this.b));
                if (TextUtils.isEmpty(this.c)) {
                    this.c = HttpClient.getIntentce().get(this.b);
                    return this.c;
                } else {
                    MemoryCache.findOrCreateMemoryCache().clear();
                    return this.c;
                }
            } catch (QiushibaikeException e) {
                MysBaseActivity.this.b(0);
            } catch (Exception e_2) {
                MysBaseActivity.this.b(0);
            }
        }
    }

    static {
        B = MysBaseActivity.class.getName();
        E = new Handler();
    }

    public MysBaseActivity() {
        this.o = RContactStorage.PRIMARY_KEY;
        this.lastRefreshFirstPageTime = null;
        this.q = new ArrayList();
        this.r = new ArrayList();
        this.t = 1;
        this.u = false;
        this.v = -1;
        this.w = false;
        this.x = false;
        this.C = false;
        this.y = false;
        this.z = null;
        this.A = null;
        this.D = false;
        this._firstVisibleItem = 0;
        this._visibleItemCount = 0;
        this._totalItemCount = 0;
    }

    private void b(int r5i) {
        new Handler(Looper.getMainLooper()).postDelayed(new ak(this), (long) r5i);
    }

    private void m() {
        String r0_String = FileCache.getContentFromDisk(this, getCacheUniqueName());
        if (TextUtils.isEmpty(r0_String)) {
            this.s.initData();
        } else {
            b(r0_String);
            this.q.clear();
            this.q.addAll(this.r);
            this.p.notifyDataSetChanged();
        }
        this.C = true;
    }

    private void n() {
        boolean r0z = false;
        if (this.F == null || (!this.F.getTag().equals("active"))) {
            ShareUtils.openShareDialog(this, 1, r0z);
        } else {
            r0z = true;
            ShareUtils.openShareDialog(this, 1, r0z);
        }
    }

    protected void b(String r7_String) {
        try {
            JSONObject r0_JSONObject = new JSONObject(r7_String);
            JSONArray r1_JSONArray = r0_JSONObject.getJSONArray("items");
            this.v = r0_JSONObject.getInt("total");
            int r2i = r1_JSONArray.length();
            if (this.v != 0) {
                if (r2i == 0 && this.t == 1) {
                    ToastAndDialog.makeText((Context)this, i()).show();
                } else if (r2i != 0) {
                    this.w = true;
                }
            } else {
                ToastAndDialog.makeText((Context)this, i()).show();
            }
            int r0i;
            Article r3_Article;
            if (r2i != 0) {
                if (this.o.equals(TOP_REFRESH)) {
                    if (this.t != 1 || ListViewHelper.canSelectionSaveable(this)) {
                        r0i = 0;
                    } else {
                        this.r.clear();
                    }
                } else {
                    this.r.clear();
                }
                r0i = 0;
                while (r0i < r2i) {
                    try {
                        if (r1_JSONArray.optJSONObject(r0i) == null) {
                            r3_Article = new Article(r1_JSONArray.optJSONObject(r0i));
                            if (this.r.contains(r3_Article)) {
                                this.r.add(r3_Article);
                            }
                        }
                    } catch (QiushibaikeException e) {
                    }
                    r0i++;
                }
                if (this.t == 1 && c()) {
                    sort(this.r);
                }
            } else {
                this.w = true;
                if (this.o.equals(TOP_REFRESH)) {
                    this.r.clear();
                } else if (this.t != 1 || ListViewHelper.canSelectionSaveable(this)) {
                    r0i = 0;
                } else {
                    this.r.clear();
                }
                r0i = 0;
                while (r0i < r2i) {
                    if (r1_JSONArray.optJSONObject(r0i) == null) {
                        r0i++;
                    } else {
                        r3_Article = new Article(r1_JSONArray.optJSONObject(r0i));
                        if (this.r.contains(r3_Article)) {
                            r0i++;
                        } else {
                            this.r.add(r3_Article);
                            r0i++;
                        }
                    }
                }
                if (this.t == 1 || c()) {
                } else {
                    sort(this.r);
                }
            }
        } catch (Exception e_2) {
            e_2.printStackTrace();
        }
    }

    protected boolean c() {
        return false;
    }

    protected void d() {
        this.s = (XListView) findViewById(R.id.xListView);
        this.s.setPullLoadEnable(true);
        this.p = getmAdapter();
        this.s.setAdapter(this.p);
        this.s.setXListViewListener(this);
        this.s.setOnScrollListener(this);
    }

    protected int e() {
        return R.layout.activity_manage_my_contents;
    }

    protected void f() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        d();
        g();
        h();
    }

    public void finish() {
        E.postDelayed(new aj(this), 5000);
        super.finish();
    }

    protected void g() {
        if (ListViewHelper.canSelectionSaveable(this)) {
            onInitHistoryData();
            if (!ListViewHelper.isOutSizeIntervalOfPage(this, getCacheUniqueName(), -1)) {
                this.t = Math.max(ListViewHelper.getSaveLastPage(this, getCacheUniqueName()) + 1, this.t);
            }
        } else {
            this.s.initData();
        }
    }

    public abstract String getCacheUniqueName();

    public OnAsyncLoadListener getOnAsyncLoadListener(String r2_String) {
        return new a(r2_String);
    }

    public int getPageNo() {
        return this.t > 1 ? this.t - 1 : this.t;
    }

    public int getShareRequestCode() {
        return REQ_CODE_SHARE;
    }

    public abstract String getTargetDataUrl(String r1_String);

    public String getVotePoint() {
        return "mycollection/";
    }

    public DefaultAdapter getmAdapter() {
        return new MyCollectionsAdapter(this, this.s, this.q);
    }

    public XListView getmListView() {
        return this.s;
    }

    protected void h() {
        this.s.setOnItemClickListener(new ah(this));
        l();
    }

    protected String i() {
        return "\u7a7a\u7a7a\u7684\uff0c\u5565\u4e5f\u6728\u6709\u5594";
    }

    protected void j() {
        this.s.stopRefresh();
        this.s.stopLoadMore();
        this.q.clear();
        this.q.addAll(this.r);
        if (this.q.size() <= 0 || this.w || this.q.size() == this.v) {
            this.s.loadNoMore();
            this.p.notifyDataSetChanged();
        } else {
            this.p.notifyDataSetChanged();
        }
    }

    protected void l() {
        this.s.setOnItemLongClickListener(new al(this));
    }

    protected void onActivityResult(int r8i, int r9i, Intent r10_Intent) {
        super.onActivityResult(r8i, r9i, r10_Intent);
        if (r9i == 0) {
        } else {
            switch (r8i) {
                case REQ_CODE_SHARE:
                    ShareUtils r0_ShareUtils = new ShareUtils();
                    if ((!ShareUtils.needNetwork(r9i)) || ShareUtils.checkAndAlertNetworkStatus(this)) {
                        if (r9i == 4) {
                            Intent r0_Intent = new Intent(this, WXEntryActivity.class);
                            r0_Intent.putExtra(Utils.RESPONSE_CONTENT, this.z.getContent());
                            r0_Intent.putExtra("articleId", this.z.id);
                            if (!TextUtils.isEmpty(this.z.image)) {
                                r0_Intent.putExtra("image", this.z.image);
                            }
                            startActivity(r0_Intent);
                        } else if (r9i == 7) {
                            r0_ShareUtils.tryCollection(this.F, this, this.z.id);
                        } else if (r9i == 8) {
                            r0_ShareUtils.getArticleReporter(this).chooseReportOption();
                        } else if (r9i > 4) {
                            r0_ShareUtils.Share(this, this.z.content, this.z.image, r9i, -1);
                        } else if (QsbkApp.currentUser == null) {
                            Toast.makeText(QsbkApp.mContext, "\u767b\u5f55\u540e\u624d\u80fd\u5206\u4eab\u54e6\uff01", 0).show();
                            startActivity(new Intent(this, LoginActivity.class));
                            overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
                        } else {
                            Intent r1_Intent;
                            Integer r2_Integer = r0_ShareUtils.checkAccessToken(r9i);
                            r1_Intent = (r9i == 2 || r9i == 3) ? new Intent(this, QQAuthorizeActivity.class) : new Intent(this, AuthorizeActivity.class);
                            if (r9i == 1) {
                                r1_Intent.putExtra(QQDialogAuthorizeActivity.TARGET, "sina");
                            }
                            r1_Intent.putExtra("resultCode", r9i);
                            switch (r2_Integer.intValue()) {
                                case XListViewHeader.STATE_READY:
                                    r0_ShareUtils.buidBindUrl(Integer.valueOf(r9i));
                                    startActivityForResult(r1_Intent, XListViewHeader.STATE_REFRESHING);
                                case XListViewHeader.STATE_REFRESHING:
                                    r0_ShareUtils.buidBindUrl(Integer.valueOf(r9i));
                                    Toast.makeText(this, "\u7ed1\u5b9a\u4fe1\u606f\u8fc7\u671f\uff0c\u8bf7\u91cd\u65b0\u7ed1\u5b9a", 0).show();
                                    startActivityForResult(r1_Intent, XListViewHeader.STATE_REFRESHING);
                                case XListViewFooter.STATE_NOMORE:
                                    r0_ShareUtils.Share(this, this.z.id, r9i);
                            }
                        }
                    }
            }
        }
    }

    protected void onDestroy() {
        if (this.p != null) {
            this.p.clearImageCache();
        }
        if (this.q != null) {
            this.q.clear();
        }
        if (this.r != null) {
            this.r.clear();
        }
        this.u = false;
        super.onDestroy();
    }

    public void onInitHistoryData() {
        this.o = TOP_REFRESH;
        m();
        this.C = true;
    }

    public boolean onKeyDown(int r3i, KeyEvent r4_KeyEvent) {
        if (r3i != 25 || this._firstVisibleItem + this._visibleItemCount < this._totalItemCount - 2) {
            return super.onKeyDown(r3i, r4_KeyEvent);
        }
        onLoadMore();
        return true;
    }

    public void onLoadMore() {
        if (this.q.size() > 0 || (!this.w)) {
            this.o = BOTTOM_BUTTON_REFRESH;
            new AsyncDataLoader(getOnAsyncLoadListener(LOAD), "qsbk-AT-BGA-more").execute(new Void[0]);
        }
    }

    public boolean onOptionsItemSelected(MenuItem r3_MenuItem) {
        if (r3_MenuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(r3_MenuItem);
    }

    protected void onPause() {
        ListViewHelper.onSaveListViewFirstVisibleItem(this, this.s, this.p, getCacheUniqueName(), true);
        super.onPause();
    }

    public void onRefresh() {
        this.w = false;
        this.o = TOP_REFRESH;
        new Handler().postDelayed(new ai(this), 180);
    }

    protected void onResume() {
        super.onResume();
        if (this.D) {
            this.p.notifyDataSetChanged();
            this.D = false;
        }
        if ((!QsbkApp.getInstance().hasContentTextSizeChange()) || this.p == null) {
        } else {
            this.p.notifyDataSetChanged();
        }
    }

    public void onScroll(AbsListView r2_AbsListView, int r3i, int r4i, int r5i) {
        this._visibleItemCount = r4i;
        this._totalItemCount = r5i;
        if (this._firstVisibleItem == r3i || this._firstVisibleItem >= r3i) {
        } else {
            this._firstVisibleItem = r3i;
        }
    }

    public void onScrollStateChanged(AbsListView r1_AbsListView, int r2i) {
    }

    public void onXScrolling(View r1_View) {
    }

    public void setClickItemTrue() {
        this.D = true;
    }

    public void setCollectionIcon(View r1_View) {
        this.F = r1_View;
    }

    public void setSelectedArticle(Article r1_Article) {
        this.z = r1_Article;
    }

    public void sort(ArrayList<Object> r6_ArrayList_Object) {
        int r1i = 0;
        while (r1i < r6_ArrayList_Object.size()) {
            int r2i = 1;
            while (r2i < r6_ArrayList_Object.size()) {
                if (((Article) r6_ArrayList_Object.get(r2i - 1)).random.compareTo(((Article) r6_ArrayList_Object.get(r2i)).random) > 0) {
                    r6_ArrayList_Object.set(r2i - 1, r6_ArrayList_Object.get(r2i));
                    r6_ArrayList_Object.set(r2i, (Article) r6_ArrayList_Object.get(r2i - 1));
                }
                r2i++;
            }
            r1i++;
        }
    }
}