package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.mobstat.StatService;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.About;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.base.IVotePoint;
import qsbk.app.adapter.BaseInfoAdapter;
import qsbk.app.adapter.DefaultAdapter;
import qsbk.app.adapter.OneProfileArticleAdapter;
import qsbk.app.api.UserHeaderHelper;
import qsbk.app.cache.FileCache;
import qsbk.app.cache.MemoryCache;
import qsbk.app.cache.SecureFileCache;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.loader.OnAsyncLoadListener;
import qsbk.app.message.ui.MessageSendActivity;
import qsbk.app.model.Article;
import qsbk.app.model.UserInfo;
import qsbk.app.push.Utils;
import qsbk.app.share.AuthorizeActivity;
import qsbk.app.share.QQAuthorizeActivity;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.share.ShareAble;
import qsbk.app.share.ShareUtils;
import qsbk.app.share.WXEntryActivity;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.Md5;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.DefaultNoContentView;
import qsbk.app.widget.HeaderTabBar;
import qsbk.app.widget.HeaderTabBar.OnTabClickListener;
import qsbk.app.widget.ProfileHeaderListView;
import qsbk.app.widget.ProfileHeaderListView.OnScrollDirectionListener;
import qsbk.app.widget.RoundImageView;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class OneProfileActivity extends BaseActionBarActivity implements OnClickListener, IVotePoint, ShareAble, OnTabClickListener {
    private static SparseArray<Long> G = null;
    public static final String MSG_SOURCE = "chat_msg_source";
    private static final int[] P;
    public static final int REQ_CODE_SHARE = 20;
    public static final String SELECTED_TAB_ID = "selected_tab";
    public static final String SOURCE = "source";
    public static final int TAB_FIRST = 1;
    public static final int TAB_SECOND = 2;
    public static final String USER = "user";
    public static final String USER_ICON_URL = "user_icon_url";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    private static final String q;
    private UserInfo A;
    private UserHeaderHelper B;
    private ArrayList<Object> C;
    private ArrayList<Object> D;
    private ArrayList<UserInfo> E;
    private DefaultNoContentView F;
    private HashMap<Integer, Integer> H;
    private SparseIntArray I;
    private SparseBooleanArray J;
    private SparseBooleanArray K;
    private AsyncDataLoader L;
    private AsyncDataLoader M;
    private SecureFileCache N;
    private ImageView O;
    private String Q;
    private View R;
    private final Runnable S;
    private final Runnable T;
    private final Runnable U;
    private final Runnable V;
    private final Runnable W;
    private final Runnable X;
    protected Article o;
    private StringBuffer p;
    private final Handler r;
    private int s;
    private ProfileHeaderListView t;
    private DefaultAdapter u;
    private DefaultAdapter v;
    private DefaultAdapter w;
    private HeaderTabBar x;
    private RoundImageView y;
    private TextView z;

    abstract class d implements OnAsyncLoadListener {
        int e;
        String f;
        String g;
        boolean h;

        d() {
            this.h = false;
        }

        abstract String a();

        public void onFinishListener(String r4_String) {
            this.e = 3;
            OneProfileActivity.this.y();
            if (this.h) {
                MemoryCache.findOrCreateMemoryCache().put(Md5.MD5(this.f), this.g);
            }
        }

        public void onPrepareListener() {
            this.e = 1;
            this.f = a();
        }

        public String onStartListener() {
            this.e = 2;
            OneProfileActivity.this.x();
            try {
                this.g = MemoryCache.findOrCreateMemoryCache().get(Md5.MD5(this.f));
                if (TextUtils.isEmpty(this.g)) {
                    this.g = HttpClient.getIntentce().get(this.f);
                    this.h = true;
                    return this.g;
                } else {
                    this.h = false;
                    MemoryCache.findOrCreateMemoryCache().clear();
                    return this.g;
                }
            } catch (QiushibaikeException e) {
            } catch (Exception e_2) {
            }
        }
    }

    private class a extends d {
        int a;
        int b;
        BaseAdapter c;

        public a(int r3i, BaseAdapter r4_BaseAdapter) {
            super();
            this.b = 2;
            this.a = r3i;
            this.c = r4_BaseAdapter;
        }

        String a_() {
            StringBuffer r0_StringBuffer = new StringBuffer(OneProfileActivity.this.b(this.b));
            r0_StringBuffer.append("?page=").append(this.a).append("&count=").append(Constants.pageCount);
            if (UIHelper.isNightTheme()) {
                r0_StringBuffer.append("&theme=night");
            }
            return r0_StringBuffer.toString();
        }

        public void onFinishListener(String r5_String) {
            super.onFinishListener(r5_String);
            if (TextUtils.isEmpty(r5_String)) {
                ToastAndDialog.makeText(OneProfileActivity.this, "\u52a0\u8f7d\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\u3002").show();
            } else {
                if (this.a == 1) {
                    FileCache.cacheTextToDisk(OneProfileActivity.this, OneProfileActivity.this.c(this.b), r5_String);
                }
                OneProfileActivity.this.N.cacheArticlesToDisk(r5_String, OneProfileActivity.this.A.userId);
                OneProfileActivity.this.g(r5_String);
                OneProfileActivity.this.r.post(OneProfileActivity.this.V);
                HashMap r0_HashMap = OneProfileActivity.this.H;
                Integer r1_Integer = Integer.valueOf(this.b);
                int r2i = this.a + 1;
                this.a = r2i;
                r0_HashMap.put(r1_Integer, Integer.valueOf(r2i));
                G.put(this.b, Long.valueOf(System.currentTimeMillis()));
                ListViewHelper.saveLastUpdateTime(OneProfileActivity.this, OneProfileActivity.this.c(this.b));
                this.g = null;
            }
        }
    }

    private final class b implements Runnable {
        final String a;

        public b(String r2_String) {
            this.a = r2_String;
        }

        public void run() {
            OneProfileActivity.this.g(this.a);
            OneProfileActivity.this.v.notifyDataSetChanged();
            OneProfileActivity.this.y();
        }
    }

    private class c extends d {
        int a;
        final BaseAdapter b;

        public c(BaseAdapter r3_BaseAdapter) {
            super();
            this.a = 1;
            this.b = r3_BaseAdapter;
        }

        String a() {
            return new StringBuffer(OneProfileActivity.this.b(this.a)).toString();
        }

        public void onFinishListener(String r5_String) {
            super.onFinishListener(r5_String);
            if (TextUtils.isEmpty(r5_String)) {
                ToastAndDialog.makeText(OneProfileActivity.this, "\u52a0\u8f7d\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\u3002").show();
            } else {
                FileCache.cacheTextToDisk(OneProfileActivity.this, OneProfileActivity.this.c(this.a), r5_String);
                OneProfileActivity.this.N.cacheUserToDisk(r5_String, OneProfileActivity.this.A.userId);
                OneProfileActivity.this.h(r5_String);
                OneProfileActivity.this.r.post(OneProfileActivity.this.X);
                G.put(this.a, Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    private static class e implements OnScrollDirectionListener {
        private final View a;
        private Animation b;
        private Animation c;
        private Handler d;
        private Runnable e;
        private Runnable f;

        public e(View r10_View) {
            this.b = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
            this.c = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
            this.d = new Handler();
            this.e = new cg(this);
            this.f = new ch(this);
            this.a = r10_View;
            this.b.setDuration(200);
            this.c.setDuration(200);
        }

        public void onScrollDown() {
            if (this.a.getVisibility() != 8) {
                this.d.removeCallbacks(this.e);
                this.d.removeCallbacks(this.f);
                this.d.post(this.f);
            }
        }

        public void onScrollUp() {
            if (this.a.getVisibility() != 0) {
                this.d.removeCallbacks(this.e);
                this.d.removeCallbacks(this.f);
                this.d.post(this.e);
            }
        }
    }

    private static class f {
        int a;
        String b;
        int c;

        f(int r1i, String r2_String, int r3i) {
            this.a = r1i;
            this.b = r2_String;
            this.c = r3i;
        }
    }

    static {
        q = OneProfileActivity.class.getName();
        G = new SparseArray();
        P = new int[]{2130837860, 2130837861, 2130837862};
    }

    public OneProfileActivity() {
        this.r = new Handler();
        this.C = new ArrayList();
        this.D = new ArrayList();
        this.E = new ArrayList();
        this.H = new bp(this);
        this.I = new SparseIntArray();
        this.J = new SparseBooleanArray();
        this.K = new SparseBooleanArray();
        this.o = null;
        this.S = new br(this);
        this.T = new bs(this);
        this.U = new bt(this);
        this.V = new bu(this);
        this.W = new bv(this);
        this.X = new bw(this);
    }

    private void A() {
        this.t.setOnItemClickListener(new bx(this));
    }

    private int B() {
        return this.s;
    }

    private void a(String r5_String, String r6_String, String r7_String, Context r8_Context) {
        Intent r1_Intent = new Intent(r8_Context, MessageSendActivity.class);
        r1_Intent.setFlags(67108864);
        r1_Intent.putExtra("userName", r7_String);
        r1_Intent.putExtra("userId", r5_String);
        r1_Intent.putExtra("userIcon", r6_String);
        r1_Intent.putExtra(SOURCE, TextUtils.isEmpty(this.Q) ? "entry" : this.Q);
        String r0_String = getIntent().getStringExtra(MSG_SOURCE);
        if (!TextUtils.isEmpty(r0_String)) {
            r1_Intent.putExtra(MessageSendActivity.MSG_SOURCE, r0_String);
            LogUtil.d("msg_source :" + r0_String);
        }
        r8_Context.startActivity(r1_Intent);
        overridePendingTransition(R.anim.roll_right, R.anim.still_when_right);
    }

    private synchronized void b(String r4_String) {
        if (this.F == null) {
            this.F = (DefaultNoContentView) View.inflate(this, R.layout.layout_default_no_content, null);
        }
        d();
        this.F.setText(r4_String);
        LayoutParams r0_LayoutParams = new FrameLayout.LayoutParams(-2, -2);
        r0_LayoutParams.gravity = 17;
        r0_LayoutParams.topMargin = (int) (getResources().getDisplayMetrics().density * 70.0f);
        this.F.setLayoutParams(r0_LayoutParams);
        addContentView(this.F, r0_LayoutParams);
    }

    private void c(String r4_String) {
        if (!TextUtils.isEmpty(r4_String)) {
            StatService.onEvent(this, "edit_my_profile", r4_String);
        }
        Intent r0_Intent = new Intent(this, EditInfoEntranceActivity.class);
        r0_Intent.putExtra(SOURCE, OneProfileActivity.class.getSimpleName());
        startActivityForResult(r0_Intent, XListViewFooter.STATE_NOMORE);
    }

    private synchronized void d() {
        if (this.F == null) {
        } else if (this.F.getParent() != null) {
            ((ViewGroup) this.F.getParent()).removeView(this.F);
        }
    }

    private void d(String r3_String) {
        if (!TextUtils.isEmpty(r3_String)) {
            StatService.onEvent(this, "manage_articles", r3_String);
        }
        startActivity(new Intent(this, ManageMyContentsActivity.class));
    }

    private void e(String r4_String) {
        CharSequence[] r0_CharSequenceA = new CharSequence[2];
        r0_CharSequenceA[0] = "\u52a0\u5165\u9ed1\u540d\u5355";
        r0_CharSequenceA[1] = "\u4e3e\u62a5\u7528\u6237";
        new Builder(this).setTitle("\u8bf7\u9009\u62e9\u64cd\u4f5c").setItems(r0_CharSequenceA, new cb(this, r4_String)).show();
    }

    private void f(String r2_String) {
        new cc(this, r2_String).run();
    }

    private void g() {
        Intent r0_Intent = getIntent();
        if (!TextUtils.isEmpty(r0_Intent.getStringExtra(USER))) {
            this.A = new UserInfo(r0_Intent.getStringExtra(USER));
        }
        if (this.A == null) {
            this.A = new UserInfo();
            this.A.userId = r0_Intent.getStringExtra(USER_ID);
            this.A.userIcon = r0_Intent.getStringExtra(USER_ICON_URL);
            this.A.userName = r0_Intent.getStringExtra(USER_NAME);
        }
        this.s = r0_Intent.getIntExtra(SELECTED_TAB_ID, TAB_FIRST);
        this.Q = r0_Intent.getStringExtra(SOURCE);
        this.p = new StringBuffer(this.A.userId).append("_");
        m();
    }

    private synchronized void g(String r6_String) {
        try {
            JSONObject r0_JSONObject = new JSONObject(r6_String);
            JSONArray r1_JSONArray = r0_JSONObject.getJSONArray("items");
            int r0i = r0_JSONObject.getInt("total");
            this.I.put(TAB_SECOND, r0i);
            if (r0i == 0) {
                this.K.put(TAB_SECOND, Boolean.TRUE.booleanValue());
                if (B() == 2) {
                    this.r.post(this.S);
                }
            } else {
                int r2i = r1_JSONArray.length();
                if (r2i == 0) {
                    this.J.put(TAB_SECOND, Boolean.TRUE.booleanValue());
                }
                r0i = 0;
                while (r0i < r2i) {
                    try {
                        if (r1_JSONArray.optJSONObject(r0i) != null) {
                            Article r3_Article = new Article(r1_JSONArray.optJSONObject(r0i));
                            if (!this.C.contains(r3_Article)) {
                                this.C.add(r3_Article);
                            }
                        }
                    } catch (QiushibaikeException e) {
                    }
                    r0i++;
                }
            }
        } catch (Exception e_2) {
            e_2.printStackTrace();
        }
    }

    private void h() {
        f r1_f = new f(2130903155, "\u7b80\u4ecb", 1);
        f r2_f = new f(2130903155, "\u7cd7\u4e8b", 2);
        this.x = (HeaderTabBar) findViewById(R.id.dumplicate_header_tab_bar);
        this.x.addTab(r1_f.a, r1_f.b, r1_f.c);
        this.x.addTab(r2_f.a, r2_f.b, r2_f.c);
        this.x.setOnTabClickListener(this);
        this.x.setSelectedTab(this.s);
        this.t = (ProfileHeaderListView) findViewById(R.id.listview);
        this.t.addTab(r1_f.a, r1_f.b, r1_f.c);
        this.t.addTab(r2_f.a, r2_f.b, r2_f.c);
        this.t.setOnHeaderTabClickListener(this);
        this.t.addDuplicateHeaderTabBar(this.x);
        this.t.setSelectedTab(this.s);
        this.O = (ImageView) findViewById(R.id.main_image_bg);
        this.O.setImageResource(P[new Random().nextInt(P.length)]);
        i();
        n();
    }

    private void h(String r3_String) {
        try {
            JSONObject r0_JSONObject = new JSONObject(r3_String).optJSONObject("userdata");
            if (r0_JSONObject == null) {
                if (r0_JSONObject == null) {
                    UserInfo.updateServerJsonNearby(this.A, r0_JSONObject);
                    if (!q()) {
                        UserInfo.updateServerJsonNearby(QsbkApp.currentUser, r0_JSONObject);
                        SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
                    }
                    if (!TextUtils.isEmpty(this.A.signature)) {
                        this.z.setText(this.A.signature);
                    }
                }
            } else if (r0_JSONObject == null) {
            } else {
                UserInfo.updateServerJsonNearby(this.A, r0_JSONObject);
                if (q()) {
                    if (TextUtils.isEmpty(this.A.signature)) {
                    } else {
                        this.z.setText(this.A.signature);
                    }
                } else {
                    UserInfo.updateServerJsonNearby(QsbkApp.currentUser, r0_JSONObject);
                    SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
                    if (TextUtils.isEmpty(this.A.signature)) {
                        this.z.setText(this.A.signature);
                    }
                }
            }
        } catch (JSONException e) {
        }
    }

    private void i() {
        this.y = (RoundImageView) findViewById(R.id.head_icon);
        if (q()) {
            this.y.setOnClickListener(this);
            if (TextUtils.isEmpty(this.A.userIcon) || "null".equals(this.A.userIcon)) {
                this.y.setImageResource(R.drawable.default_avatar_lead);
            }
        }
        j();
    }

    private void j() {
        if (TextUtils.isEmpty(this.A.userIcon) || "null".equals(this.A.userIcon)) {
        } else {
            QsbkApp.getInstance().getAvatarWorker(this).loadImage(UserHeaderHelper.getIconUrl(this.A), this.y);
        }
    }

    private void l() {
        if (TextUtils.isEmpty(this.A.signature)) {
            this.z.setText(R.string.signature_default);
        } else {
            this.z.setText(this.A.signature);
        }
    }

    private void m() {
        if (!TextUtils.isEmpty(this.A.userName)) {
            setTitle(this.A.userName);
        }
    }

    private void n() {
        this.z = (TextView) findViewById(R.id.signature);
        if (q()) {
            this.z.setOnClickListener(this);
        }
        l();
    }

    private void o() {
        this.o = null;
    }

    private void p() {
        if (!q()) {
            View r0_View = ((ViewStub) findViewById(R.id.flow_bottom_layout)).inflate();
            r0_View.findViewById(R.id.send_msg).setOnClickListener(this);
            r0_View.findViewById(R.id.report).setOnClickListener(this);
            this.t.setOnScrollDirectionListener(new e(r0_View));
        }
    }

    private boolean q() {
        return QsbkApp.currentUser != null && QsbkApp.currentUser.userId.equals(this.A.userId);
    }

    private void r() {
        if (this.u == null) {
            x();
            this.E.add(this.A);
            this.u = new BaseInfoAdapter(this.E, this);
            this.N.getUserString(this.A.userId, new bz(this));
        }
        this.t.setAdapter(this.u);
    }

    private void s() {
        if (this.v == null) {
            x();
            if (q()) {
                this.C.add(0, RContactStorage.PRIMARY_KEY);
            }
            this.v = new OneProfileArticleAdapter(this, this.t, this.C);
            this.t.setAdapter(this.v);
            A();
            this.N.getArticlesString(this.A.userId, new ca(this));
        }
        if (this.K.get(TAB_SECOND)) {
            b(getString(R.string.no_contents));
        }
        this.t.setAdapter(this.v);
    }

    private void t() {
    }

    private void u() {
        if (q()) {
            new cd(this, b((int)TAB_FIRST)).run();
        }
    }

    private void v() {
        new Builder(this).setTitle("\u6e29\u99a8\u63d0\u793a").setMessage("\u786e\u5b9a\u8981\u9000\u51fa\u767b\u5f55\u5417\uff1f").setPositiveButton("\u786e\u5b9a", new cf(this)).setNegativeButton("\u53d6\u6d88", new ce(this)).show();
    }

    private void w() {
        new bq(this).run();
    }

    private void x() {
        this.r.removeCallbacks(this.U);
        this.r.removeCallbacks(this.T);
        this.r.post(this.T);
    }

    private void y() {
        this.r.removeCallbacks(this.U);
        this.r.removeCallbacks(this.T);
        this.r.post(this.U);
    }

    private void z() {
        Intent r0_Intent = new Intent(this, EditSignatureActivity.class);
        r0_Intent.putExtra(REQUEST_KEY.KEY_AUTO_SUBMIT, true);
        r0_Intent.putExtra(REQUEST_KEY.KEY_DEFAULT_VALUE, this.z.getText());
        startActivityForResult(r0_Intent, REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE);
    }

    protected String b(int r5i) {
        int r1i = TAB_FIRST;
        String r0_String;
        Object[] r1_ObjectA;
        switch (r5i) {
            case TAB_FIRST:
                r0_String = Constants.UPDATE_USERINFO_1;
                r1_ObjectA = new Object[r1i];
                r1_ObjectA[0] = this.A.userId;
                return String.format(r0_String, r1_ObjectA);
            case TAB_SECOND:
                r0_String = Constants.ONES_ARTICLES;
                r1_ObjectA = new Object[r1i];
                r1_ObjectA[0] = this.A.userId;
                return String.format(r0_String, r1_ObjectA);
        }
        return null;
    }

    protected String c(int r3i) {
        switch (r3i) {
            case TAB_FIRST:
                return this.p.append("myInfo").toString();
            case TAB_SECOND:
                return this.p.append("myPublishContent").toString();
        }
        return null;
    }

    protected int e() {
        return R.layout.one_profile_activity;
    }

    protected void f() {
        this.N = SecureFileCache.getInstance(this);
        g();
        this.B = new UserHeaderHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        h();
        p();
        u();
        switch (this.s) {
            case TAB_FIRST:
                r();
            case TAB_SECOND:
                s();
                break;
            case XListViewFooter.STATE_NOMORE:
                break;
            default:
                break;
        }
        t();
    }

    protected String getCustomTitle() {
        return getResources().getString(R.string.one_profile);
    }

    public int getShareRequestCode() {
        return REQ_CODE_SHARE;
    }

    public String getVotePoint() {
        return "oneprofile/";
    }

    protected void onActivityResult(int r9i, int r10i, Intent r11_Intent) {
        super.onActivityResult(r9i, r10i, r11_Intent);
        if (r10i == 0) {
        } else {
            String r0_String;
            switch (r9i) {
                case XListViewHeader.STATE_NORMAL:
                    if (r10i == -1) {
                        this.B.doCropPhotoWithCaptured();
                    }
                case TAB_FIRST:
                    if (r10i == -1) {
                        r0_String = this.B.savePickedBitmap(r11_Intent);
                        if (!TextUtils.isEmpty(r0_String)) {
                            submitAvatar(r0_String);
                        }
                    }
                case XListViewFooter.STATE_NOMORE:
                    if (r11_Intent.getBooleanExtra(EditInfoEntranceActivity.RESP_KEY_USER_INFO_CHANGED, false)) {
                        r0_String = SharePreferenceUtils.getSharePreferencesValue("loginUser");
                        if (!TextUtils.isEmpty(r0_String)) {
                            this.A = new UserInfo(r0_String);
                            this.E.remove(0);
                            this.E.add(this.A);
                            this.u.notifyDataSetChanged();
                        }
                    }
                case REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE:
                    if (r10i == -1) {
                        Serializable r0_Serializable = r11_Intent.getSerializableExtra(REQUEST_KEY.KEY_RETURN_VALUE);
                        if (r0_Serializable != null) {
                            this.z.setText(r0_Serializable.toString());
                            this.A.signature = r0_Serializable.toString();
                            QsbkApp.currentUser.signature = r0_Serializable.toString();
                        }
                    }
                case REQ_CODE_SHARE:
                    ShareUtils r0_ShareUtils = new ShareUtils();
                    LogUtil.d("request code:abc");
                    if ((!ShareUtils.needNetwork(r10i)) || ShareUtils.checkAndAlertNetworkStatus(this)) {
                        LogUtil.d("result code:" + r10i);
                        if (r10i == 4) {
                            Intent r0_Intent = new Intent(this, WXEntryActivity.class);
                            r0_Intent.putExtra(Utils.RESPONSE_CONTENT, this.o.getContent());
                            r0_Intent.putExtra("articleId", this.o.id);
                            if (!TextUtils.isEmpty(this.o.image)) {
                                r0_Intent.putExtra("image", this.o.image);
                            }
                            startActivity(r0_Intent);
                        } else if (r10i == 7) {
                            r0_ShareUtils.tryCollection(this.R, this, this.o.id);
                        } else if (r10i == 8) {
                            r0_ShareUtils.getArticleReporter(this).chooseReportOption();
                        } else if (r10i > 4) {
                            r0_ShareUtils.Share(this, this.o.content, this.o.image, r10i, -1);
                        } else if (QsbkApp.currentUser == null) {
                            Toast.makeText(QsbkApp.mContext, "\u767b\u5f55\u540e\u624d\u80fd\u5206\u4eab\u54e6\uff01", 0).show();
                            startActivity(new Intent(this, LoginActivity.class));
                            overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
                        } else {
                            Intent r1_Intent;
                            Integer r2_Integer = r0_ShareUtils.checkAccessToken(r10i);
                            r1_Intent = (r10i == 2 || r10i == 3) ? new Intent(this, QQAuthorizeActivity.class) : new Intent(this, AuthorizeActivity.class);
                            if (r10i == 1) {
                                r1_Intent.putExtra(QQDialogAuthorizeActivity.TARGET, "sina");
                            }
                            r1_Intent.putExtra("resultCode", r10i);
                            switch (r2_Integer.intValue()) {
                                case TAB_FIRST:
                                    r0_ShareUtils.buidBindUrl(Integer.valueOf(r10i));
                                    startActivityForResult(r1_Intent, TAB_SECOND);
                                case TAB_SECOND:
                                    r0_ShareUtils.buidBindUrl(Integer.valueOf(r10i));
                                    Toast.makeText(this, "\u7ed1\u5b9a\u4fe1\u606f\u8fc7\u671f\uff0c\u8bf7\u91cd\u65b0\u7ed1\u5b9a", 0).show();
                                    startActivityForResult(r1_Intent, TAB_SECOND);
                                case XListViewFooter.STATE_NOMORE:
                                    r0_ShareUtils.Share(this, this.o.id, r10i);
                                    o();
                            }
                        }
                    }
            }
        }
    }

    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    public void onClick(View r4_View) {
        switch (r4_View.getId()) {
            case R.id.signature:
                z();
                break;
            case R.id.send_msg:
                a(this.A.userId, this.A.userIcon, this.A.userName, this);
                break;
            case R.id.report:
                e(this.A.userId);
                break;
            case R.id.head_icon:
                c("head_ic");
                break;
        }
    }

    public boolean onCreateOptionsMenu(Menu r3_Menu) {
        if (q()) {
            getMenuInflater().inflate(R.menu.one_profile_self, r3_Menu);
        } else {
            getMenuInflater().inflate(R.menu.one_profile_other, r3_Menu);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem r4_MenuItem) {
        Intent r0_Intent;
        switch (r4_MenuItem.getItemId()) {
            case 16908332:
                finish();
                break;
            case R.id.action_feedback:
                startActivity(new Intent(this, About.class));
                break;
            case R.id.action_about:
                r0_Intent = new Intent(this, About.class);
                r0_Intent.putExtra("targetPage", "about");
                startActivity(r0_Intent);
                break;
            case R.id.action_edit:
                c("actionbar");
                break;
            case R.id.action_manager_mine:
                d("actionbar");
                break;
            case R.id.action_my_collection:
                r0_Intent = new Intent(this, MyCollectionsActivity.class);
                r0_Intent.putExtra(SOURCE, OneProfileActivity.class.getSimpleName());
                startActivity(r0_Intent);
                break;
            case R.id.action_my_participate:
                r0_Intent = new Intent(this, MyParticipatesActivity.class);
                r0_Intent.putExtra(SOURCE, OneProfileActivity.class.getSimpleName());
                startActivity(r0_Intent);
                break;
            case R.id.action_signout:
                v();
                break;
        }
        return super.onOptionsItemSelected(r4_MenuItem);
    }

    public void onTabClick(int r3i) {
        if (this.s != r3i) {
            this.s = r3i;
            this.x.setSelectedTab(this.s);
            this.t.setSelectedTab(this.s);
            d();
            switch (r3i) {
                case TAB_FIRST:
                    r();
                    break;
                case TAB_SECOND:
                    s();
                    break;
                case XListViewFooter.STATE_NOMORE:
                    t();
                    break;
            }
        }
    }

    public void setCollectionIcon(View r1_View) {
        this.R = r1_View;
    }

    public void setSelectedArticle(Article r1_Article) {
        this.o = r1_Article;
    }

    public void submitAvatar(String r4_String) {
        int r1i = TAB_FIRST;
        if (HttpUtils.isNetworkConnected(this)) {
            Toast.makeText(this, "\u6b63\u5728\u4e0a\u4f20\u5934\u50cf...", r1i).show();
            new by(this, r4_String).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
        } else {
            Toast.makeText(this, R.string.network_not_connected, TAB_FIRST).show();
        }
    }
}