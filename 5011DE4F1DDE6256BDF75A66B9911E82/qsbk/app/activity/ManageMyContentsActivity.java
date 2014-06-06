package qsbk.app.activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.base.IVotePoint;
import qsbk.app.adapter.DefaultAdapter;
import qsbk.app.adapter.ManageMyContentsAdapter;
import qsbk.app.cache.FileCache;
import qsbk.app.cache.MemoryCache;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.loader.OnAsyncLoadListener;
import qsbk.app.model.Article;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.Md5;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.listview.XListView;
import qsbk.app.widget.listview.XListView.IXListViewListener;
import qsbk.app.widget.listview.XListView.OnXScrollListener;
import qsbk.app.widget.listview.XListViewHeader;

public class ManageMyContentsActivity extends BaseActionBarActivity implements IVotePoint, IXListViewListener, OnXScrollListener {
    private static final String B;
    public static final String BOTTOM_BUTTON_REFRESH = "bottom_button_refresh";
    public static final int DEFAULT_REFRESH_INTERVAL = 60000;
    public static final String LOAD = "load";
    public static final String PRE = "pre";
    public static final String TOP_REFRESH = "top_refresh";
    protected View A;
    private boolean C;
    private boolean D;
    private final a E;
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

    private static class a extends Handler {
        private final WeakReference<ManageMyContentsActivity> a;

        public a(ManageMyContentsActivity r2_ManageMyContentsActivity) {
            this.a = new WeakReference(r2_ManageMyContentsActivity);
        }

        public void handleMessage(Message r4_Message) {
            ManageMyContentsActivity r0_ManageMyContentsActivity = (ManageMyContentsActivity) this.a.get();
            if (r0_ManageMyContentsActivity != null) {
                String r0_String;
                String r1_String = RContactStorage.PRIMARY_KEY;
                switch (r4_Message.what) {
                    case XListViewHeader.STATE_NORMAL:
                        r0_ManageMyContentsActivity.q.remove(r0_ManageMyContentsActivity.z);
                        r0_ManageMyContentsActivity.p.notifyDataSetChanged();
                        r0_String = "\u5220\u9664\u7cd7\u4e8b#" + r0_ManageMyContentsActivity.z.id + "\u6210\u529f";
                        Toast.makeText(QsbkApp.mContext, r0_String, 0).show();
                        break;
                    case AdViewUtil.NETWORK_TYPE_CUSTOMIZE:
                        r0_String = "\u5220\u9664\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5\uff01";
                        Toast.makeText(QsbkApp.mContext, r0_String, 0).show();
                        break;
                }
                r0_String = (String) r4_Message.obj;
                Toast.makeText(QsbkApp.mContext, r0_String, 0).show();
            }
        }
    }

    class b implements OnAsyncLoadListener {
        private String b;
        private String c;
        private String d;

        public b(String r3_String) {
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
                if (ManageMyContentsActivity.this.t == 1) {
                    FileCache.cacheTextToDisk(ManageMyContentsActivity.this, ManageMyContentsActivity.this.getCacheUniqueName(), r6_String);
                }
                ManageMyContentsActivity.this.b(r6_String);
                ManageMyContentsActivity r0_ManageMyContentsActivity = ManageMyContentsActivity.this;
                r0_ManageMyContentsActivity.t++;
                ManageMyContentsActivity.this.lastRefreshFirstPageTime = Long.valueOf(System.currentTimeMillis());
                ListViewHelper.saveLastUpdateTime(ManageMyContentsActivity.this, ManageMyContentsActivity.this.getCacheUniqueName());
                this.c = null;
                if (this.d.equals(PRE) || ManageMyContentsActivity.this.q.size() <= 0) {
                    ManageMyContentsActivity.this.b(0);
                    ManageMyContentsActivity.this.y = false;
                } else {
                    new AsyncDataLoader(ManageMyContentsActivity.this.getOnAsyncLoadListener(PRE), "qsbk-AT-BGA-pre1").execute(new Void[0]);
                    ManageMyContentsActivity.this.b(0);
                    ManageMyContentsActivity.this.y = false;
                }
                if (this.d.equals(PRE)) {
                } else {
                    this.c = null;
                }
            }
        }

        public void onPrepareListener() {
            StringBuffer r1_StringBuffer = new StringBuffer(ManageMyContentsActivity.this.getTargetDataUrl(this.d)).append("?page=").append(ManageMyContentsActivity.this.t);
            if (UIHelper.isNightTheme()) {
                r1_StringBuffer.append("&theme=night");
            }
            r1_StringBuffer.append("&count=").append(Constants.pageCount);
            if (ManageMyContentsActivity.this.getTargetDataUrl(this.d).contains("nearby")) {
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
                ManageMyContentsActivity.this.b(0);
            } catch (Exception e_2) {
                ManageMyContentsActivity.this.b(0);
            }
        }
    }

    static {
        B = ManageMyContentsActivity.class.getName();
    }

    public ManageMyContentsActivity() {
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
        this.E = new a(this);
    }

    private void b(int r5i) {
        new Handler(Looper.getMainLooper()).postDelayed(new bc(this), (long) r5i);
    }

    private void l() {
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

    private void m() {
        new bd(this, "qbk-MyCtnAct").start();
    }

    protected void b(String r7_String) {
        int r0i;
        try {
            int r0i_2;
            JSONObject r0_JSONObject = new JSONObject(r7_String);
            JSONArray r1_JSONArray = r0_JSONObject.getJSONArray("items");
            this.v = r0_JSONObject.getInt("total");
            int r2i = r1_JSONArray.length();
            if (r2i == 0) {
                this.w = true;
            }
            if (this.o.equals(TOP_REFRESH)) {
                this.r.clear();
            } else if (this.t != 1 || ListViewHelper.canSelectionSaveable(this)) {
                r0i_2 = 0;
            } else {
                this.r.clear();
            }
            r0i_2 = 0;
            while (r0i_2 < r2i) {
                try {
                    if (r1_JSONArray.optJSONObject(r0i_2) != null) {
                        Article r3_Article = new Article(r1_JSONArray.optJSONObject(r0i_2));
                        if (!this.r.contains(r3_Article)) {
                            this.r.add(r3_Article);
                        }
                    }
                } catch (QiushibaikeException e) {
                }
                r0i_2++;
            }
            if (this.t == 1 && h()) {
                sort(this.r);
            }
        } catch (Exception e_2) {
            e_2.printStackTrace();
        }
    }

    protected void c() {
        this.s = (XListView) findViewById(R.id.xListView);
        this.s.setPullLoadEnable(true);
        this.p = getmAdapter();
        this.s.setAdapter(this.p);
        this.s.setXListViewListener(this);
        this.s.setOnScrollListener(this);
    }

    protected void d() {
        if (ListViewHelper.canSelectionSaveable(this)) {
            onInitHistoryData();
            if (!ListViewHelper.isOutSizeIntervalOfPage(this, getCacheUniqueName(), -1)) {
                this.t = Math.max(ListViewHelper.getSaveLastPage(this, getCacheUniqueName()) + 1, this.t);
            }
        } else {
            this.s.initData();
        }
    }

    protected int e() {
        return R.layout.activity_manage_my_contents;
    }

    protected void f() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        c();
        d();
        g();
    }

    public void finish() {
        super.finish();
    }

    protected void g() {
        this.s.setOnItemClickListener(new ba(this));
        j();
    }

    public String getCacheUniqueName() {
        return "myContent";
    }

    protected String getCustomTitle() {
        return "\u7ba1\u7406\u6211\u7684\u7cd7\u4e8b";
    }

    public OnAsyncLoadListener getOnAsyncLoadListener(String r2_String) {
        return new b(r2_String);
    }

    public int getPageNo() {
        return this.t > 1 ? this.t - 1 : this.t;
    }

    public String getTargetDataUrl(String r4_String) {
        if (LOAD.equals(r4_String)) {
            k().trackView("\u7ba1\u7406\u6211\u7684\u7cd7\u4e8b/" + this.t);
        }
        return Constants.MYCONTENTS;
    }

    public String getVotePoint() {
        return "mycontent/";
    }

    public DefaultAdapter getmAdapter() {
        return new ManageMyContentsAdapter(this, this.s, this.q);
    }

    public XListView getmListView() {
        return this.s;
    }

    protected boolean h() {
        return false;
    }

    protected void i() {
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

    protected void j() {
        this.s.setOnItemLongClickListener(new be(this));
    }

    protected void onDestroy() {
        this.p.clearImageCache();
        this.q.clear();
        this.u = false;
        super.onDestroy();
    }

    public void onInitHistoryData() {
        this.o = TOP_REFRESH;
        l();
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
        new Handler().postDelayed(new bb(this), 180);
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