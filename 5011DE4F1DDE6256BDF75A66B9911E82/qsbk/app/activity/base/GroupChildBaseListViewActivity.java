package qsbk.app.activity.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.LoginActivity;
import qsbk.app.activity.MyLikeActivity;
import qsbk.app.activity.StatFragmentActivity;
import qsbk.app.adapter.ArticleAdapter;
import qsbk.app.adapter.DefaultAdapter;
import qsbk.app.bean.AdBean;
import qsbk.app.cache.FileCache;
import qsbk.app.cache.MemoryCache;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.gdtad.GdtAd;
import qsbk.app.gdtad.IFeedsAdLoaded;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.loader.OnAsyncLoadListener;
import qsbk.app.model.Article;
import qsbk.app.model.Impression;
import qsbk.app.push.Utils;
import qsbk.app.share.AuthorizeActivity;
import qsbk.app.share.QQAuthorizeActivity;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.share.ShareUtils;
import qsbk.app.share.WXEntryActivity;
import qsbk.app.utils.Base64;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.Md5;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.audit.RequestListener;
import qsbk.app.widget.listview.XListView;
import qsbk.app.widget.listview.XListView.IXListViewListener;
import qsbk.app.widget.listview.XListView.OnXScrollListener;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;
import qsbk.app.widget.slidingmenu.ui.MenuHorizontalScrollView;

public abstract class GroupChildBaseListViewActivity extends StatFragmentActivity implements IVotePoint, IFeedsAdLoaded, IXListViewListener, OnXScrollListener {
    public static final String BOTTOM_BUTTON_REFRESH = "bottom_button_refresh";
    public static final int DEFAULT_REFRESH_INTERVAL = 60000;
    public static final int FEEDSAD_MIN_POSITION = 5;
    private static Boolean L = null;
    public static final String LOAD = "load";
    private static Boolean M = null;
    public static final String PRE = "pre";
    public static final String TOP_REFRESH = "top_refresh";
    protected Tracker A;
    protected View B;
    Handler C;
    Handler D;
    Runnable E;
    LinkedList<Impression> F;
    private boolean G;
    private boolean H;
    private boolean I;
    private int J;
    private View K;
    public int _firstVisibleItem;
    public int _totalItemCount;
    public int _visibleItemCount;
    public Long lastRefreshFirstPageTime;
    private boolean n;
    protected Context o;
    protected String p;
    protected DefaultAdapter q;
    protected ArrayList<Object> r;
    protected XListView s;
    protected int t;
    protected int u;
    protected boolean v;
    protected boolean w;
    protected boolean x;
    protected Article y;
    protected View z;

    class a implements OnAsyncLoadListener {
        private String b;
        private String c;
        private String d;
        private long e;
        private long f;

        public a(String r3_String) {
            this.b = RContactStorage.PRIMARY_KEY;
            this.c = RContactStorage.PRIMARY_KEY;
            this.d = RContactStorage.PRIMARY_KEY;
            this.d = r3_String;
        }

        public void onFinishListener(String r9_String) {
            this.f = System.currentTimeMillis();
            DebugUtil.debug("TIMECOUNTER", "loading data time:" + (this.f - this.e));
            if (TextUtils.isEmpty(r9_String) || this.d.equals(PRE)) {
                if (TextUtils.isEmpty(r9_String)) {
                    ToastAndDialog.makeText(GroupChildBaseListViewActivity.this, "\u52a0\u8f7d\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\u3002").show();
                    GroupChildBaseListViewActivity.this.a(0);
                }
                if (!this.d.equals(PRE)) {
                    this.c = null;
                }
            } else {
                if (GroupChildBaseListViewActivity.this.t == 1) {
                    FileCache.cacheTextToDisk(GroupChildBaseListViewActivity.this, GroupChildBaseListViewActivity.this.getCacheUniqueName(), r9_String);
                }
                GroupChildBaseListViewActivity.this.b(r9_String);
                GroupChildBaseListViewActivity r0_GroupChildBaseListViewActivity = GroupChildBaseListViewActivity.this;
                r0_GroupChildBaseListViewActivity.t++;
                GroupChildBaseListViewActivity.this.lastRefreshFirstPageTime = Long.valueOf(System.currentTimeMillis());
                ListViewHelper.saveLastUpdateTime(GroupChildBaseListViewActivity.this, GroupChildBaseListViewActivity.this.getCacheUniqueName());
                this.c = null;
                GroupChildBaseListViewActivity.this.x = false;
                GroupChildBaseListViewActivity.this.I = false;
                GroupChildBaseListViewActivity.this.q();
                if (this.d.equals(PRE)) {
                } else {
                    this.c = null;
                }
            }
        }

        public void onPrepareListener() {
            this.e = System.currentTimeMillis();
            StringBuffer r1_StringBuffer = new StringBuffer(GroupChildBaseListViewActivity.this.getTargetDataUrl(this.d)).append("?page=").append(GroupChildBaseListViewActivity.this.t);
            if (UIHelper.isNightTheme()) {
                r1_StringBuffer.append("&theme=night");
            }
            r1_StringBuffer.append("&count=").append(Constants.pageCount);
            if (GroupChildBaseListViewActivity.this.getTargetDataUrl(this.d).contains("nearby")) {
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
                GroupChildBaseListViewActivity.this.a(0);
            } catch (Exception e_2) {
                GroupChildBaseListViewActivity.this.a(0);
            }
        }
    }

    static {
        L = Boolean.valueOf(false);
        M = Boolean.valueOf(false);
    }

    public GroupChildBaseListViewActivity() {
        this.o = this;
        this.p = RContactStorage.PRIMARY_KEY;
        this.lastRefreshFirstPageTime = null;
        this.r = new ArrayList();
        this.t = 1;
        this.u = -1;
        this.v = false;
        this.w = false;
        this.n = false;
        this.x = false;
        this.G = true;
        this.y = null;
        this.z = null;
        this.H = false;
        this.I = false;
        this.J = 0;
        this.B = null;
        this.C = new q(this);
        this.D = new Handler();
        this.E = new r(this);
        this._firstVisibleItem = 0;
        this._visibleItemCount = 0;
        this._totalItemCount = 0;
        this.F = new LinkedList();
    }

    private void a(int r5i) {
        new Handler(Looper.getMainLooper()).postDelayed(new z(this), (long) r5i);
    }

    private void k() {
        new Handler().postDelayed(new s(this), 5000);
    }

    private void l() {
        EasyTracker.getInstance().setContext(this);
        this.A = EasyTracker.getTracker();
        this.A.setAppVersion(Constants.localVersionName);
        QsbkApp.getInstance().setSampleRate(this.A);
    }

    private void m() {
        String r0_String = FileCache.getContentFromDisk(this, getCacheUniqueName());
        if ((!TextUtils.isEmpty(r0_String)) || this instanceof MyLikeActivity) {
            Log.i("Test", "\u6709\u4fdd\u7559\u4f4d\u7f6e\u80fd\u529b\uff0c\u5e76\u4e14\u7f13\u5b58\u5185\u5bb9\u66f4\u4e0d\u662f\u7a7a");
            b(r0_String);
            if (ListViewHelper.canSelectionSaveable(this)) {
                ListViewHelper.onRestoreListViewSelection(this, getCacheUniqueName(), this.r, this.s);
            }
            a(0);
        } else {
            this.s.initData();
        }
        this.n = true;
        showAd();
    }

    private void n() {
        boolean r0z = false;
        if (this.K == null || (!this.K.getTag().equals("active"))) {
            ShareUtils.openShareDialog(this, 1, r0z);
        } else {
            r0z = true;
            ShareUtils.openShareDialog(this, 1, r0z);
        }
    }

    private void o() {
        this.y = null;
        this.z = null;
    }

    private void p() {
        int r0i = this.s.getLastVisiblePosition();
        DebugUtil.debug("GroupChildBaseListViewActivity", "insertFeedAd curPositin:" + r0i);
        if ((!getCacheUniqueName().equalsIgnoreCase("Nearby")) || HttpUtils.isWifi(this)) {
            GdtAd.getInstance().insertFeedAd(r0i, this.r);
        }
    }

    private void q() {
        DebugUtil.debug("GroupChildBaseListViewActivity", "refreshAdList");
        p();
        a(0);
    }

    protected void b(String r9_String) {
        int r1i;
        try {
            int r1i_2;
            JSONObject r2_JSONObject = new JSONObject(r9_String);
            JSONArray r3_JSONArray = r2_JSONObject.getJSONArray("items");
            this.u = r2_JSONObject.getInt("total");
            int r4i = r3_JSONArray.length();
            if (r4i == 0) {
                this.v = true;
            }
            if (this.p.equals(TOP_REFRESH)) {
                this.r.clear();
            } else if (this.t != 1 || ListViewHelper.canSelectionSaveable(this)) {
                r1i_2 = 0;
            } else {
                this.r.clear();
            }
            r1i_2 = 0;
            while (r1i_2 < r4i) {
                try {
                    if (r3_JSONArray.optJSONObject(r1i_2) != null) {
                        Article r0_Article = new Article(r3_JSONArray.optJSONObject(r1i_2));
                        if (!this.r.contains(r0_Article)) {
                            if (r0_Article.image_size == null || r0_Article.image_size.mediumSize() == null || (!QsbkApp.isPad)) {
                                this.r.add(r0_Article);
                            } else if (r0_Article.image_size.mediumSize().getWidth() < 400 || r0_Article.image_size.mediumSize().getHeight() < 400) {
                                r1i_2++;
                            } else {
                                this.r.add(r0_Article);
                            }
                        }
                    }
                } catch (QiushibaikeException e) {
                    e.printStackTrace();
                }
                r1i_2++;
            }
            if (this.t == 1 && b_()) {
                sort(this.r);
            }
            if (!r2_JSONObject.isNull("ads")) {
                initAdData(r2_JSONObject.getJSONArray("ads"));
            }
        } catch (Exception e_2) {
            e_2.printStackTrace();
        }
    }

    protected boolean b_() {
        return false;
    }

    protected void c() {
        DebugUtil.debug("GroupChildBaseListViewActivity", "resume");
        this.s.stopRefresh();
        this.s.stopLoadMore();
        if (this.r.size() <= 0 || this.v || this.r.size() == this.u) {
            this.s.loadNoMore();
            this.q.notifyDataSetChanged();
            if (this.p == BOTTOM_BUTTON_REFRESH) {
                showAd();
            }
        } else {
            this.q.notifyDataSetChanged();
            if (this.p == BOTTOM_BUTTON_REFRESH) {
            } else {
                showAd();
            }
        }
    }

    public void closeAd() {
        ((GroupBaseActivity) getParent()).onClosedAd();
    }

    protected void d() {
        if (ListViewHelper.canSelectionSaveable(this)) {
            Log.i("Test", "\u6709\u4fdd\u7559\u4f4d\u7f6e\u7684\u80fd\u529b");
            onInitHistoryData();
            if (!ListViewHelper.isOutSizeIntervalOfPage(this, getCacheUniqueName(), -1)) {
                this.t = Math.max(ListViewHelper.getSaveLastPage(this.o, getCacheUniqueName()) + 1, this.t);
            }
        } else {
            this.s.initData();
        }
    }

    protected void f() {
        this.s.setOnItemLongClickListener(new y(this));
    }

    public abstract String getCacheUniqueName();

    public OnAsyncLoadListener getOnAsyncLoadListener(String r2_String) {
        return new a(r2_String);
    }

    public int getPageNo() {
        return this.t > 1 ? this.t - 1 : this.t;
    }

    public abstract String getTargetDataUrl(String r1_String);

    public String getVotePoint() {
        return null;
    }

    public DefaultAdapter getmAdapter() {
        return new ArticleAdapter(this, this.s, this.r);
    }

    public XListView getmListView() {
        return this.s;
    }

    protected void h() {
        this.s = (XListView) findViewById(R.id.xListView);
        this.s.setPullLoadEnable(true);
        this.q = getmAdapter();
        this.s.setAdapter(this.q);
        this.s.setXListViewListener(this);
        this.s.setOnScrollListener(this);
    }

    protected void i() {
        int r2i = RequestListener.DEFAULT_LOADED_SIZE;
        if (this.B == null) {
            this.B = View.inflate(this, R.layout.layout_tap_to_refresh, null);
            this.B.setOnClickListener(new t(this));
        }
        if (this.B.getParent() != null) {
            ((ViewGroup) this.B.getParent()).removeView(this.B);
        }
        LayoutParams r0_LayoutParams = new FrameLayout.LayoutParams(r2i, r2i);
        r0_LayoutParams.gravity = 1;
        r0_LayoutParams.topMargin = 20;
        addContentView(this.B, r0_LayoutParams);
        this.B.requestFocus();
        this.B.setVisibility(0);
        new Handler().postDelayed(new u(this), 15000);
    }

    public void iFeedsAdLoaded() {
        if (!this.I) {
            q();
        }
    }

    public void initAdData(JSONArray r7_JSONArray) {
        String r1_String = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        int r2i = r7_JSONArray.length();
        int r0i = 0;
        while (r0i < r2i) {
            try {
                JSONObject r3_JSONObject = r7_JSONArray.optJSONObject(r0i);
                if (r3_JSONObject != null) {
                    AdBean r4_AdBean = new AdBean(r3_JSONObject);
                    if (SharePreferenceUtils.getSharePreferencesIntValue(r4_AdBean.id + "_" + r1_String) < r4_AdBean.count) {
                        this.r.add(r4_AdBean.pos, r4_AdBean);
                    }
                }
            } catch (Exception e) {
            }
            r0i++;
        }
    }

    protected void j() {
        this.s.setOnItemClickListener(new v(this));
        f();
    }

    protected void onActivityResult(int r8i, int r9i, Intent r10_Intent) {
        if (this.z != null) {
            this.z.findViewById(R.id.layerMask).setVisibility(Base64.DONT_BREAK_LINES);
        }
        ShareUtils r0_ShareUtils = new ShareUtils();
        if (r9i < 1) {
        } else {
            if (r8i == 1) {
                if ((!ShareUtils.needNetwork(r9i)) || ShareUtils.checkAndAlertNetworkStatus(this)) {
                    if (r9i == 4) {
                        Intent r0_Intent = new Intent(this, WXEntryActivity.class);
                        r0_Intent.putExtra(Utils.RESPONSE_CONTENT, this.y.getContent());
                        r0_Intent.putExtra("articleId", this.y.id);
                        if (!TextUtils.isEmpty(this.y.image)) {
                            r0_Intent.putExtra("image", this.y.image);
                        }
                        startActivity(r0_Intent);
                    } else if (r9i == 7) {
                        r0_ShareUtils.tryCollection(this.K, this, this.y.id);
                    } else if (r9i == 8) {
                        r0_ShareUtils.getArticleReporter(this).chooseReportOption();
                    } else if (r9i > 4) {
                        r0_ShareUtils.Share(this.o, this.y.content, this.y.image, r9i, -1);
                    } else if (QsbkApp.currentUser == null) {
                        Toast.makeText(QsbkApp.mContext, "\u767b\u5f55\u540e\u624d\u80fd\u5206\u4eab\u54e6\uff01", 0).show();
                        startActivity(new Intent(this, LoginActivity.class));
                        overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
                    } else {
                        Intent r1_Intent;
                        Integer r2_Integer = r0_ShareUtils.checkAccessToken(r9i);
                        if (r9i == 2 || r9i == 3) {
                            r1_Intent = new Intent(this, QQAuthorizeActivity.class);
                            if (r9i != 1) {
                                r1_Intent.putExtra(QQDialogAuthorizeActivity.TARGET, "sina");
                            }
                            r1_Intent.putExtra("resultCode", r9i);
                            switch (r2_Integer.intValue()) {
                                case XListViewHeader.STATE_READY:
                                    r0_ShareUtils.buidBindUrl(Integer.valueOf(r9i));
                                    startActivityForResult(r1_Intent, XListViewHeader.STATE_REFRESHING);
                                    break;
                                case XListViewHeader.STATE_REFRESHING:
                                    r0_ShareUtils.buidBindUrl(Integer.valueOf(r9i));
                                    Toast.makeText(this, "\u7ed1\u5b9a\u4fe1\u606f\u8fc7\u671f\uff0c\u8bf7\u91cd\u65b0\u7ed1\u5b9a", 0).show();
                                    startActivityForResult(r1_Intent, XListViewHeader.STATE_REFRESHING);
                                    break;
                                case XListViewFooter.STATE_NOMORE:
                                    r0_ShareUtils.Share(this.o, this.y.id, r9i);
                                    o();
                                    break;
                            }
                        } else {
                            r1_Intent = new Intent(this, AuthorizeActivity.class);
                            if (r9i != 1) {
                                r1_Intent.putExtra("resultCode", r9i);
                                switch (r2_Integer.intValue()) {
                                    case XListViewHeader.STATE_READY:
                                        r0_ShareUtils.buidBindUrl(Integer.valueOf(r9i));
                                        startActivityForResult(r1_Intent, XListViewHeader.STATE_REFRESHING);
                                        break;
                                    case XListViewHeader.STATE_REFRESHING:
                                        r0_ShareUtils.buidBindUrl(Integer.valueOf(r9i));
                                        Toast.makeText(this, "\u7ed1\u5b9a\u4fe1\u606f\u8fc7\u671f\uff0c\u8bf7\u91cd\u65b0\u7ed1\u5b9a", 0).show();
                                        startActivityForResult(r1_Intent, XListViewHeader.STATE_REFRESHING);
                                        break;
                                    case XListViewFooter.STATE_NOMORE:
                                        r0_ShareUtils.Share(this.o, this.y.id, r9i);
                                        o();
                                        break;
                                }
                            }
                        }
                        r1_Intent.putExtra(QQDialogAuthorizeActivity.TARGET, "sina");
                        r1_Intent.putExtra("resultCode", r9i);
                        switch (r2_Integer.intValue()) {
                            case XListViewHeader.STATE_READY:
                                r0_ShareUtils.buidBindUrl(Integer.valueOf(r9i));
                                startActivityForResult(r1_Intent, XListViewHeader.STATE_REFRESHING);
                                break;
                            case XListViewHeader.STATE_REFRESHING:
                                r0_ShareUtils.buidBindUrl(Integer.valueOf(r9i));
                                Toast.makeText(this, "\u7ed1\u5b9a\u4fe1\u606f\u8fc7\u671f\uff0c\u8bf7\u91cd\u65b0\u7ed1\u5b9a", 0).show();
                                startActivityForResult(r1_Intent, XListViewHeader.STATE_REFRESHING);
                                break;
                            case XListViewFooter.STATE_NOMORE:
                                r0_ShareUtils.Share(this.o, this.y.id, r9i);
                                o();
                                break;
                        }
                    }
                }
            } else if (r8i == 2) {
                r0_ShareUtils.Share(this.o, this.y.id, r9i);
            } else if (r8i == 3) {
                r0_ShareUtils.getArticleReporter(this).reportArticle(this.y.id, r9i);
            }
            super.onActivityResult(r8i, r9i, r10_Intent);
        }
    }

    protected void onCreate(Bundle r3_Bundle) {
        super.onCreate(r3_Bundle);
        setContentView(View.inflate(this, R.layout.layout_xlistview, null));
        l();
        h();
        d();
        j();
        GdtAd.getInstance().registerListener(this);
    }

    protected void onDestroy() {
        this.q.clearImageCache();
        this.r.clear();
        this.q.notifyDataSetChanged();
        GdtAd.getInstance().unRegisterListener(this);
        super.onDestroy();
    }

    public void onInitHistoryData() {
        this.p = TOP_REFRESH;
        m();
        this.n = true;
    }

    public boolean onKeyDown(int r6i, KeyEvent r7_KeyEvent) {
        if (r6i != 25 || this._firstVisibleItem + this._visibleItemCount < this._totalItemCount - 2) {
            if (r6i == 82) {
                ((GroupBaseActivity) getParent()).resumeMenuLayout();
                return true;
            } else {
                if (r6i != 4) {
                    return super.onKeyDown(r6i, r7_KeyEvent);
                }
                if (!(this.G)) {
                    return true;
                }
                ((GroupBaseActivity) getParent()).getScrollView();
                if (MenuHorizontalScrollView.menuOut) {
                    ((GroupBaseActivity) getParent()).resumeMenuLayout();
                    return true;
                } else {
                    if (L.booleanValue()) {
                        QsbkApp.getInstance().exit();
                    } else {
                        L = Boolean.valueOf(true);
                        Toast.makeText(QsbkApp.mContext, "\u518d\u6309\u4e00\u6b21\u8fd4\u56de\u952e\u9000\u51fa", 0).show();
                        ListViewHelper.onSaveListViewFirstVisibleItem(this, this.s, this.q, getCacheUniqueName(), true);
                        if (!M.booleanValue()) {
                            M = Boolean.valueOf(true);
                            this.D.postDelayed(this.E, 3000);
                        }
                    }
                    return true;
                }
            }
        } else {
            onLoadMore();
            return true;
        }
    }

    public void onLoadMore() {
        this.J = this.r.size();
        if (this.J > 0 || (!this.v)) {
            this.I = true;
            this.p = BOTTOM_BUTTON_REFRESH;
            new AsyncDataLoader(getOnAsyncLoadListener(LOAD), "qsbk-AT-BGA-more").execute(new Void[0]);
        }
    }

    protected void onPause() {
        ListViewHelper.onSaveListViewFirstVisibleItem(this, this.s, this.q, getCacheUniqueName(), true);
        super.onPause();
    }

    public void onRefresh() {
        this.v = false;
        this.p = TOP_REFRESH;
        if (this.B == null || this.B.getParent() == null) {
            new Handler().postDelayed(new x(this), 180);
        } else {
            ((ViewGroup) this.B.getParent()).removeView(this.B);
            new Handler().postDelayed(new x(this), 180);
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.H) {
            this.q.notifyDataSetChanged();
            this.H = false;
        }
        new Handler().postDelayed(new p(this), 800);
        if ((!QsbkApp.getInstance().hasContentTextSizeChange()) || this.q == null) {
            k();
        } else {
            this.q.notifyDataSetChanged();
            k();
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

    public void setCanBack(boolean r1z) {
        this.G = r1z;
    }

    public void setClickItemTrue() {
        this.H = true;
    }

    public void showAd() {
        if (!GdtAd.getInstance().getFeedsAdSwitcherState()) {
            new Handler().postDelayed(new w(this), 3000);
        }
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