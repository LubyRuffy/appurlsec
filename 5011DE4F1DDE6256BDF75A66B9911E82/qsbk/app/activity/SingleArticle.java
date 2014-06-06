package qsbk.app.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.conversation.RConversation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.SecDefaultActivity;
import qsbk.app.activity.group.SplashGroup;
import qsbk.app.activity.publish.Publish;
import qsbk.app.adapter.AdapterForLinearLayout;
import qsbk.app.adapter.MyContentsAdapter;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.loader.OnAsyncLoadListener;
import qsbk.app.message.ChatMsgSource;
import qsbk.app.model.Article;
import qsbk.app.model.Vote;
import qsbk.app.push.Utils;
import qsbk.app.report.ArticleReporter;
import qsbk.app.share.AuthorizeActivity;
import qsbk.app.share.QQAuthorizeActivity;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.share.ShareUrl;
import qsbk.app.share.ShareUtils;
import qsbk.app.share.WXEntryActivity;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.MobileTransformationMethod;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.audit.RequestListener;
import qsbk.app.widget.listview.LinearLayoutForListView;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class SingleArticle extends SecDefaultActivity implements OnGestureListener, OnTouchListener {
    public static String KEY_ONLY_ARTICLE_ID;
    private static final String V;
    ImageView A;
    Button B;
    public final float[] BT_SELECTED;
    Button C;
    TextView D;
    ImageView E;
    LinearLayout F;
    View G;
    LinearLayout H;
    Button I;
    LinearLayout J;
    Handler K;
    GestureDetector L;
    int M;
    int N;
    private StringBuffer W;
    private Article X;
    private String Y;
    private int Z;
    private int aa;
    private boolean ab;
    private boolean ac;
    private boolean ad;
    private boolean ae;
    private String af;
    private ProgressBar ag;
    private TextView ah;
    private boolean ai;
    private OnAsyncLoadListener aj;
    private ArticleReporter ak;
    ColorStateList n;
    ColorStateList o;
    ColorStateList p;
    ColorStateList q;
    AdapterForLinearLayout r;
    LinkedList<JSONObject> s;
    LinkedList<JSONObject> t;
    HashMap<Integer, JSONObject> u;
    LinearLayoutForListView v;
    Button w;
    TextView x;
    TextView y;
    TextView z;

    class a implements OnClickListener {
        String a;
        String b;

        public a(String r3_String, String r4_String) {
            this.a = null;
            this.b = null;
            this.a = r3_String;
            this.b = r4_String;
        }

        public void onClick(View r4_View) {
            Intent r0_Intent = new Intent(SingleArticle.this.O, ImageViewer.class);
            r0_Intent.putExtra("contentId", this.a);
            r0_Intent.putExtra("imageName", this.b);
            SingleArticle.this.O.startActivity(r0_Intent);
        }
    }

    class b implements OnClickListener {
        String a;
        String b;
        String c;
        String d;

        public b(String r3_String, String r4_String, String r5_String) {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.a = r3_String;
            this.b = r4_String;
            this.c = r5_String;
        }

        public void onClick(View r6_View) {
            if (QsbkApp.currentUser != null) {
                Intent r0_Intent = new Intent(SingleArticle.this.O, OneProfileActivity.class);
                if (QsbkApp.currentUser.userId.equals(this.a)) {
                    r0_Intent.putExtra(OneProfileActivity.USER, QsbkApp.currentUser.toString());
                } else {
                    r0_Intent.putExtra(QsbkDatabase.USER_ID, this.a);
                    r0_Intent.putExtra(OneProfileActivity.USER_ICON_URL, this.c);
                    r0_Intent.putExtra(OneProfileActivity.USER_NAME, this.b);
                    ChatMsgSource r1_ChatMsgSource = new ChatMsgSource(2, this.a, this.d);
                    r0_Intent.putExtra(OneProfileActivity.SELECTED_TAB_ID, XListViewHeader.STATE_REFRESHING);
                    r0_Intent.putExtra(OneProfileActivity.MSG_SOURCE, r1_ChatMsgSource.encodeToJsonObject().toString());
                }
                SingleArticle.this.O.startActivity(r0_Intent);
            } else {
                Toast.makeText(SingleArticle.this.O, "\u767b\u9646\u540e\u624d\u80fd\u67e5\u770b\u522b\u4eba\u7684\u4fe1\u606f\u54e6", 1).show();
            }
        }

        public void setArticleId(String r1_String) {
            this.d = r1_String;
        }
    }

    static {
        V = SingleArticle.class.getName();
        KEY_ONLY_ARTICLE_ID = "only_article_id";
    }

    public SingleArticle() {
        this.W = new StringBuffer("\u7cd7\u4e8b");
        this.X = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.u = new HashMap();
        this.Z = 0;
        this.aa = 1;
        this.ab = false;
        this.ac = false;
        this.BT_SELECTED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, -35.0f, 0.0f, 1.0f, 0.0f, 0.0f, -35.0f, 0.0f, 0.0f, 1.0f, 0.0f, -35.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
        this.ad = false;
        this.ae = false;
        this.af = null;
        this.ah = null;
        this.ai = false;
        this.K = new cm(this);
        this.aj = new cp(this);
        this.M = 180;
        this.N = 0;
    }

    private void a(int r3i) {
        new Handler().post(new co(this, r3i));
    }

    private void a(Intent r6_Intent, Bundle r7_Bundle) {
        this.Y = r6_Intent.getStringExtra(OneProfileActivity.SOURCE);
        this.ae = KEY_ONLY_ARTICLE_ID.equals(this.Y);
        if (r6_Intent.getBooleanExtra("FROM_MSG", false)) {
            this.ad = true;
            try {
                this.X = new Article(new JSONObject(getIntent().getStringExtra("ARTICLEJSON")));
            } catch (Exception e) {
                Toast.makeText(this, "\u6d88\u606f\u643a\u5e26\u7684\u5e16\u5b50\u5185\u5bb9\u51fa\u9519\uff0c\u67e5\u770b\u5931\u8d25", 0).show();
                finish();
            }
        } else if (!this.ae) {
            if (p()) {
                this.X = (Article) QsbkApp.currentDataSource.get(QsbkApp.currentSelectItem);
            }
            if (r7_Bundle != null) {
                this.X = (Article) r7_Bundle.getSerializable("currentArticle");
            }
        }
        a(r6_Intent, this.X);
    }

    private void a(Intent r2_Intent, Article r3_Article) {
        this.af = r2_Intent.getStringExtra("article_id");
        if ((!TextUtils.isEmpty(this.af)) || r3_Article == null) {
            if (!TextUtils.isEmpty(this.af)) {
                finish();
            }
        } else {
            this.af = r3_Article.id;
            if (TextUtils.isEmpty(this.af)) {
            } else {
                finish();
            }
        }
    }

    private void a(View r4_View) {
        boolean r2z = false;
        if (QsbkApp.currentUser == null) {
            Toast.makeText(QsbkApp.mContext, "\u767b\u5f55\u540e\u624d\u80fd\u6536\u85cf\u54e6\uff01", r2z).show();
            startActivity(new Intent(this.O, LoginActivity.class));
            overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
        } else if (HttpUtils.isNetworkConnected(this.O)) {
            if (r4_View.getTag().equals("enable")) {
                r4_View.setTag("active");
                a(this.af, true);
            } else {
                r4_View.setTag("enable");
                a(this.af, false);
            }
        } else {
            Toast.makeText(QsbkApp.mContext, R.string.network_not_connected, 0).show();
        }
    }

    private void a(TextView r2_TextView, boolean r3z) {
        if (UIHelper.isNightTheme()) {
            if (r3z) {
                r2_TextView.setTextColor(this.o);
            } else {
                r2_TextView.setTextColor(this.q);
            }
        } else if (r3z) {
            r2_TextView.setTextColor(this.n);
        } else {
            r2_TextView.setTextColor(this.p);
        }
    }

    private void a(Integer r3_Integer) {
        if (r3_Integer.intValue() == 1) {
            ShareUrl.weiboUrl = ShareUrl.sinaUrl;
        } else if (r3_Integer.intValue() == 2 || r3_Integer.intValue() == 3) {
            ShareUrl.weiboUrl = ShareUrl.qqZoneUrl;
        } else {
            ShareUrl.weiboUrl = ShareUrl.renrenUrl;
        }
    }

    private void a(String r5_String, String r6_String, ImageView r7_ImageView) {
        if (TextUtils.isEmpty(r6_String) || "null".equals(r6_String)) {
            if (UIHelper.isNightTheme()) {
                r7_ImageView.setImageResource(R.drawable.default_users_avatar_night);
            } else {
                r7_ImageView.setImageResource(R.drawable.default_users_avatar);
            }
        } else {
            String r0_String = Constants.ARATAR_URL;
            Object[] r1_ObjectA = new Object[4];
            r1_ObjectA[0] = Integer.valueOf(Integer.valueOf(r5_String).intValue() / 10000);
            r1_ObjectA[1] = r5_String;
            r1_ObjectA[2] = "thumb";
            r1_ObjectA[3] = r6_String;
            r0_String = String.format(r0_String, r1_ObjectA);
            if (UIHelper.isNightTheme()) {
                r7_ImageView.setColorFilter(new ColorMatrixColorFilter(this.BT_SELECTED));
            }
            QsbkApp.getInstance().getAvatarWorker(this.O).loadImage(r0_String, r7_ImageView);
        }
    }

    private void a(String r7_String, boolean r8z) {
        new cn(this, "qbk-SingleArt", r7_String, new JSONObject(), r8z).start();
    }

    private void a(Article r8_Article) {
        if (TextUtils.isEmpty(r8_Article.login)) {
            findViewById(R.id.userInfo).setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            findViewById(R.id.userInfo).setVisibility(0);
            this.x.setText(r8_Article.login);
            a(r8_Article.user_id, r8_Article.user_icon, (ImageView) findViewById(R.id.userIcon));
            OnClickListener r0_OnClickListener = new b(r8_Article.user_id, r8_Article.login, r8_Article.user_icon);
            r0_OnClickListener.setArticleId(r8_Article.id);
            findViewById(R.id.userInfo).setOnClickListener(r0_OnClickListener);
        }
        if (TextUtils.isEmpty(r8_Article.content) || r8_Article.content.equals("null")) {
            this.z.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            this.z.setVisibility(0);
            this.z.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
            this.z.setTransformationMethod(new MobileTransformationMethod());
            this.z.setText(r8_Article.content);
        }
        if (TextUtils.isEmpty(r8_Article.image)) {
            this.A.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            this.A.setVisibility(0);
            if (MyContentsAdapter.PUBLISH.equals(r8_Article.state)) {
                a(r8_Article, r8_Article.image, this.A);
            } else if (MyContentsAdapter.PENDING.equals(r8_Article.state)) {
                this.A.setBackgroundResource(R.drawable.thumb_pic);
            } else {
                this.A.setBackgroundResource(R.drawable.default_content_pic);
            }
        }
        String r0_String = r8_Article.tag;
        if (TextUtils.isEmpty(r0_String) || r0_String.equals("null")) {
            this.y.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            this.y.setVisibility(0);
            this.y.setTransformationMethod(new MobileTransformationMethod());
            this.y.setText(r0_String.trim());
        }
        m();
        if (r8_Article.allow_comment) {
            this.w.setText("\u6dfb\u52a0\u8bc4\u8bba");
        } else {
            this.w.setClickable(false);
            this.Q.setClickable(false);
            this.w.setText("\u8be5\u6587\u7ae0\u5173\u95ed\u8bc4\u8bba");
        }
        this.r.setArticleId(r8_Article.id);
    }

    private void a(Article r5_Article, String r6_String, ImageView r7_ImageView) {
        int r3i = 0;
        if (TextUtils.isEmpty(r6_String) || r7_ImageView == null) {
            r7_ImageView.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            r7_ImageView.setVisibility(r3i);
            String r0_String = Constants.CONTENT_IMAGE_URL;
            Object[] r1_ObjectA = new Object[4];
            r1_ObjectA[r3i] = Integer.valueOf(Integer.valueOf(r5_Article.id).intValue() / 10000);
            r1_ObjectA[1] = r5_Article.id;
            r1_ObjectA[2] = "small";
            r1_ObjectA[3] = r6_String;
            QsbkApp.getInstance().getImageWorker(this.O).loadImage(String.format(r0_String, r1_ObjectA), r7_ImageView);
            if (UIHelper.isNightTheme()) {
                r7_ImageView.setColorFilter(new ColorMatrixColorFilter(this.BT_SELECTED));
            }
        }
    }

    private boolean a(String r6_String, String r7_String, String r8_String) {
        String r0_String;
        Vote r0_Vote = new Vote(this.Y, r6_String, r7_String, "1");
        QsbkDatabase.getInstance().insertVote(r0_Vote);
        QsbkApp.waitSendVotes.put(String.valueOf(r7_String + "_" + r6_String), r0_Vote);
        QsbkApp.AllVotes.put(String.valueOf(r7_String + "_" + r6_String), r0_Vote);
        r0_String = r6_String.equals("up") ? "dn" : "up";
        Integer r1_Integer = QsbkDatabase.getInstance().queryVote(r7_String, r0_String);
        if (r1_Integer != null) {
            QsbkDatabase.getInstance().deleteVote(r1_Integer);
            QsbkApp.waitSendVotes.remove(String.valueOf(r7_String + "_" + r0_String));
            QsbkApp.AllVotes.remove(String.valueOf(r7_String + "_" + r0_String));
        }
        QsbkApp.voteHandler.obtainMessage().sendToTarget();
        return r1_Integer != null;
    }

    private void b(String r9_String) {
        int r0i = 0;
        try {
            if (!RContactStorage.PRIMARY_KEY.equals(r9_String)) {
                JSONObject r2_JSONObject = new JSONObject(r9_String);
                JSONArray r3_JSONArray = r2_JSONObject.getJSONArray("items");
                int r4i = r3_JSONArray.length();
                this.s.clear();
                int r1i = 0;
                while (r1i <= r4i) {
                    JSONObject r5_JSONObject = r3_JSONArray.optJSONObject(r1i);
                    if (r5_JSONObject != null) {
                        this.s.addLast(r5_JSONObject);
                        this.u.put(Integer.valueOf(r5_JSONObject.optInt("floor")), r5_JSONObject);
                    }
                    r1i++;
                }
                this.t.addAll(this.s);
                r1i = r2_JSONObject.getInt("total");
                this.Z = r1i - this.aa * 50;
                if (this.Z > 0) {
                    r0i = this.Z;
                }
                this.Z = r0i;
                if (r1i > 0) {
                    a(r1i);
                }
                if (this.ae) {
                    String r0_String;
                    r0_String = r2_JSONObject.isNull("article") ? null : r2_JSONObject.getString("article");
                    if (r0_String != null) {
                        this.X = new Article(new JSONObject(r0_String));
                        l();
                        i();
                        a(this.X);
                        this.ag.setVisibility(Base64.DONT_BREAK_LINES);
                        this.G.setVisibility(0);
                        b(this.X);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b(Article r3_Article) {
        ArrayList r0_ArrayList = new ArrayList(1);
        r0_ArrayList.add(r3_Article);
        if (QsbkApp.currentDataSource != null) {
            QsbkApp.currentDataSource.clear();
        }
        QsbkApp.currentDataSource = r0_ArrayList;
    }

    private void d() {
        int r1i = RequestListener.DEFAULT_LOADED_SIZE;
        if (this.ag == null) {
            this.ag = new ProgressBar(this);
            LayoutParams r0_LayoutParams = new FrameLayout.LayoutParams(r1i, r1i);
            r0_LayoutParams.gravity = 17;
            addContentView(this.ag, r0_LayoutParams);
        }
        this.ag.setVisibility(0);
        new AsyncDataLoader(this.aj, "qsbk-AT-SA-01").execute(new Void[0]);
        this.G.setVisibility(XListViewFooter.STATE_NODATA);
    }

    private void e() {
        this.ag.setVisibility(Base64.DONT_BREAK_LINES);
        if (this.ah == null) {
            this.ah = new TextView(this);
            this.ah.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
            this.ah.setText(Html.fromHtml("\u52a0\u8f7d\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e<font color=#000000>\u70b9\u6211</font>\u91cd\u8bd5..."));
            this.ah.setOnClickListener(new cl(this));
            LayoutParams r0_LayoutParams = new FrameLayout.LayoutParams(-2, -2);
            r0_LayoutParams.gravity = 17;
            addContentView(this.ah, r0_LayoutParams);
        } else {
            this.ah.setVisibility(0);
        }
    }

    private void h() {
        int r1i = Base64.DONT_BREAK_LINES;
        if (isShowCommentLayout()) {
            if (this.X != null) {
                if (this.ae) {
                    b(this.X);
                }
                if (this.X.comment_count == 0) {
                    if (HttpUtils.netIsAvailable()) {
                        this.w.setVisibility(0);
                        this.H.setVisibility(Base64.DONT_BREAK_LINES);
                    } else {
                        this.w.setVisibility(r1i);
                        this.I.setVisibility(0);
                        this.J.setVisibility(r1i);
                        this.I.setText("\u6682\u65e0\u8bc4\u8bba");
                    }
                } else if (this.ab) {
                    if (this.ai) {
                        n();
                    }
                    this.ab = false;
                } else {
                    this.w.setVisibility(Base64.DONT_BREAK_LINES);
                    if (HttpUtils.netIsAvailable()) {
                        this.I.setVisibility(r1i);
                        this.J.setVisibility(0);
                        new AsyncDataLoader(this.aj, "qsbk-AT-SA-01").execute(new Void[0]);
                    } else {
                        this.I.setVisibility(0);
                        this.J.setVisibility(r1i);
                        this.I.setText("\u8fd8\u6709" + this.X.comment_count + "\u6761\u8bc4\u8bba");
                    }
                }
            }
        } else {
            if (!this.ae) {
                this.F.setVisibility(Base64.DONT_BREAK_LINES);
            }
        }
    }

    private void i() {
        this.A.setOnClickListener(new a(this.af, this.X.image));
        findViewById(R.id.connectLayout).setOnLongClickListener(new cr(this));
        this.B.setOnClickListener(new cs(this));
        this.C.setOnClickListener(new ct(this));
        this.E.setOnClickListener(new cu(this));
        this.P.setOnClickListener(new cv(this));
        this.Q.setOnClickListener(new cw(this));
        this.w.setOnClickListener(new cx(this));
        this.I.setOnClickListener(new cy(this));
    }

    private void j() {
        boolean r0z = false;
        if (this.E == null || (!this.E.getTag().equals("active"))) {
            ShareUtils.openShareDialog(this, 1, r0z);
        } else {
            r0z = true;
            ShareUtils.openShareDialog(this, 1, r0z);
        }
    }

    private void k() {
        if (QsbkApp.currentUser == null) {
            Toast.makeText(QsbkApp.mContext, "\u767b\u5f55\u540e\u624d\u80fd\u8bc4\u8bba\u54e6\uff01", 0).show();
            this.O.startActivity(new Intent(this.O, LoginActivity.class));
            overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
        } else if (this.X.allow_comment) {
            if (HttpUtils.netIsAvailable()) {
                this.ab = true;
                QsbkApp.currentComment = null;
                Intent r0_Intent = new Intent(this, Publish.class);
                r0_Intent.putExtra(RConversation.COL_FLAG, "comment");
                r0_Intent.putExtra("currentQsId", this.af);
                startActivity(r0_Intent);
            } else {
                Toast.makeText(QsbkApp.mContext, QsbkApp.mContext.getString(R.string.network_not_connected), 1).show();
            }
        } else {
            Toast.makeText(QsbkApp.mContext, "\u8be5\u6587\u7ae0\u5173\u95ed\u8bc4\u8bba", 1).show();
        }
    }

    private void l() {
        boolean r0z = false;
        if (QsbkApp.AllVotes != null ? QsbkApp.AllVotes.containsKey(this.af + "_up") : false) {
            this.B.setTag("active");
            this.B.getBackground().setLevel(1);
            a(this.B, true);
            this.B.getCompoundDrawables()[0].setLevel(1);
        } else {
            this.B.setTag("enable");
            this.B.getBackground().setLevel(0);
            a(this.B, false);
            this.B.getCompoundDrawables()[0].setLevel(0);
        }
        if (QsbkApp.AllVotes != null ? QsbkApp.AllVotes.containsKey(this.af + "_dn") : false) {
            this.C.getBackground().setLevel(1);
            this.C.setTag("active");
            a(this.C, true);
            this.C.getCompoundDrawables()[0].setLevel(1);
        } else {
            this.C.setTag("enable");
            this.C.getBackground().setLevel(0);
            a(this.C, false);
            this.C.getCompoundDrawables()[0].setLevel(0);
        }
        if (QsbkApp.allCollection != null) {
            r0z = QsbkApp.allCollection.contains(this.af);
        }
        if (r0z) {
            this.E.setTag("active");
        } else {
            this.E.setTag("enable");
        }
    }

    private void m() {
        if (isShowCommentLayout()) {
            CharSequence r0_CharSequence;
            this.B.setText(String.valueOf(this.X.vote_up));
            this.C.setText(String.valueOf(this.X.vote_down));
            int r0i = this.X.comment_count;
            if (r0i == 0) {
                this.D.setTextSize(14.0f);
            } else {
                this.D.setTextSize(16.0f);
            }
            TextView r1_TextView = this.D;
            if (r0i == 0) {
                r0_CharSequence = "\u8bc4\u8bba";
            } else if (r0i > 1000) {
                r0_CharSequence = "1K+";
            } else {
                r0_CharSequence = String.valueOf(r0i);
            }
            r1_TextView.setText(r0_CharSequence);
        } else {
            this.B.setVisibility(Base64.DONT_BREAK_LINES);
            this.C.setVisibility(Base64.DONT_BREAK_LINES);
            this.D.setVisibility(Base64.DONT_BREAK_LINES);
            TextView r0_TextView = (TextView) findViewById(R.id.sendStateContent);
            r0_TextView.setVisibility(0);
            String r1_String = this.X.state;
            Drawable r1_Drawable;
            if (r1_String.equals(MyContentsAdapter.PRIVATE) || r1_String.equals(MyContentsAdapter.SPAM)) {
                r0_TextView.setText("\u672a\u901a\u8fc7\u5ba1\u6838");
                r1_Drawable = getResources().getDrawable(R.drawable.icon_color2);
                r0_TextView.setCompoundDrawablesWithIntrinsicBounds(r1_Drawable, null, null, null);
                findViewById(R.id.collection_icon).setVisibility(Base64.DONT_BREAK_LINES);
            } else if (r1_String.equals(MyContentsAdapter.PENDING)) {
                r0_TextView.setText("\u5f85\u5ba1\u6838");
                r1_Drawable = getResources().getDrawable(R.drawable.icon_color1);
                r0_TextView.setCompoundDrawablesWithIntrinsicBounds(r1_Drawable, null, null, null);
                findViewById(R.id.collection_icon).setVisibility(Base64.DONT_BREAK_LINES);
            } else if (r1_String.equals(MyContentsAdapter.PUBLISH)) {
                r0_TextView.setText("\u5df2\u53d1\u8868");
                r1_Drawable = getResources().getDrawable(R.drawable.icon_color4);
                r0_TextView.setCompoundDrawablesWithIntrinsicBounds(r1_Drawable, null, null, null);
                findViewById(R.id.collection_icon).setVisibility(Base64.DONT_BREAK_LINES);
            } else if (r1_String.equals(MyContentsAdapter.REPORTED)) {
                r0_TextView.setText("\u88ab\u4e3e\u62a5");
                r1_Drawable = getResources().getDrawable(R.drawable.icon_color3);
                r0_TextView.setCompoundDrawablesWithIntrinsicBounds(r1_Drawable, null, null, null);
                findViewById(R.id.collection_icon).setVisibility(Base64.DONT_BREAK_LINES);
            } else {
                r0_TextView.setText("\u706b\u661f\u8d44\u6599\u5907\u4efd\u4e2d");
                r1_Drawable = getResources().getDrawable(R.drawable.icon_color3);
                r0_TextView.setCompoundDrawablesWithIntrinsicBounds(r1_Drawable, null, null, null);
                findViewById(R.id.collection_icon).setVisibility(Base64.DONT_BREAK_LINES);
            }
        }
    }

    static /* synthetic */ int n(SingleArticle r2_SingleArticle) {
        int r0i = r2_SingleArticle.aa;
        r2_SingleArticle.aa = r0i + 1;
        return r0i;
    }

    private void n() {
        this.s.clear();
        try {
            if (QsbkApp.currentComment != null) {
                int r0i = 1;
                if (!this.t.isEmpty()) {
                    r0i = ((JSONObject) this.t.getLast()).getInt("floor") + 1;
                }
                QsbkApp.currentComment.put("floor", r0i);
                if (QsbkApp.currentComment.getBoolean("anonymous")) {
                    QsbkApp.currentComment.put(OneProfileActivity.USER, RContactStorage.PRIMARY_KEY);
                }
                this.s.addLast(QsbkApp.currentComment);
            }
        } catch (Exception e) {
            this.s.addAll(this.t);
            e.printStackTrace();
        }
        this.v.setAdapter(this.r);
        this.r.notifyDataSetChanged();
        this.H.setVisibility(Base64.DONT_BREAK_LINES);
        this.w.setVisibility(0);
    }

    private void o() {
        if (this.aa == 2) {
            new Handler().postDelayed(new cq(this), 2000);
        }
    }

    private boolean p() {
        return QsbkApp.currentDataSource != null && QsbkApp.currentDataSource.size() > QsbkApp.currentSelectItem && QsbkApp.currentSelectItem > -1;
    }

    private ArticleReporter q() {
        if (this.ak == null) {
            this.ak = new ArticleReporter(this);
        }
        return this.ak;
    }

    private void r() {
        this.L = new GestureDetector(this);
        ((LinearLayout) findViewById(R.id.singleArticleLayout)).setOnTouchListener(this);
    }

    protected void c() {
        super.c();
        this.v = (LinearLayoutForListView) findViewById(R.id.commentList);
        this.s = new LinkedList();
        this.t = new LinkedList();
        this.r = new AdapterForLinearLayout(this, this.s, this.u);
        this.x = (TextView) findViewById(R.id.userName);
        this.z = (TextView) findViewById(R.id.content);
        this.y = (TextView) findViewById(R.id.tagContent);
        this.A = (ImageView) findViewById(R.id.image);
        this.B = (Button) findViewById(R.id.support_text);
        this.C = (Button) findViewById(R.id.down_text);
        this.D = (TextView) findViewById(R.id.comment_text);
        this.E = (ImageView) findViewById(R.id.collection_icon);
        this.F = (LinearLayout) findViewById(R.id.commentLayout);
        this.w = (Button) findViewById(R.id.enter);
        this.I = (Button) findViewById(R.id.loadmore);
        this.J = (LinearLayout) findViewById(R.id.loading);
        this.H = (LinearLayout) findViewById(R.id.loadMoreLayout);
        this.G = findViewById(R.id.myScrollView);
        if (UIHelper.isNightTheme()) {
            if (this.ad) {
                this.P.setBackgroundResource(R.drawable.icon_close_large_night);
            } else {
                this.P.setBackgroundResource(R.drawable.icon_back_enable_night);
            }
            this.Q.setBackgroundResource(R.drawable.icon_comment_night);
            this.F.setBackgroundDrawable(null);
            findViewById(R.id.row_line).setVisibility(0);
            ((ProgressBar) findViewById(R.id.loadmore_progressbar)).setIndeterminateDrawable(getResources().getDrawable(R.drawable.progressbar_night));
            findViewById(R.id.adLayerMask).setVisibility(0);
        } else {
            if (this.ad) {
                this.P.setBackgroundResource(R.drawable.icon_close_large);
            } else {
                this.P.setBackgroundResource(R.drawable.icon_back_enable);
            }
            this.Q.setBackgroundResource(R.drawable.icon_comment);
            this.F.setBackgroundResource(R.drawable.listview_item_background);
            findViewById(R.id.row_line).setVisibility(Base64.DONT_BREAK_LINES);
            ((ProgressBar) findViewById(R.id.loadmore_progressbar)).setIndeterminateDrawable(getResources().getDrawable(R.drawable.progressbar));
            findViewById(R.id.adLayerMask).setVisibility(Base64.DONT_BREAK_LINES);
        }
        if (this.X != null) {
            l();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent r2_MotionEvent) {
        this.L.onTouchEvent(r2_MotionEvent);
        return super.dispatchTouchEvent(r2_MotionEvent);
    }

    public void finish() {
        if (isTaskRoot()) {
            startActivity(new Intent(this, SplashGroup.class));
        }
        if (this.ad) {
            overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
        }
        super.finish();
    }

    public String getCustomerTitle() {
        return this.W.append(this.af != null ? this.af : RContactStorage.PRIMARY_KEY).toString();
    }

    public boolean isShowCommentLayout() {
        if (this.X != null) {
            if (MyContentsAdapter.PENDING.equals(this.X.state) || MyContentsAdapter.PRIVATE.equals(this.X.state)) {
                return false;
            }
        }
        return true;
    }

    protected void onActivityResult(int r8i, int r9i, Intent r10_Intent) {
        ShareUtils r0_ShareUtils = new ShareUtils();
        if (r9i < 1) {
        } else {
            if (r8i == 1) {
                if ((!ShareUtils.needNetwork(r9i)) || ShareUtils.checkAndAlertNetworkStatus(this)) {
                    if (r9i == 4) {
                        Intent r0_Intent = new Intent(this, WXEntryActivity.class);
                        r0_Intent.putExtra(Utils.RESPONSE_CONTENT, this.X.getContent());
                        r0_Intent.putExtra("articleId", this.af);
                        if (!TextUtils.isEmpty(this.X.image)) {
                            r0_Intent.putExtra("image", this.X.image);
                        }
                        startActivity(r0_Intent);
                    } else if (r9i == 7) {
                        a(this.E);
                    } else if (r9i == 8) {
                        q().chooseReportOption();
                    } else if (r9i > 4) {
                        r0_ShareUtils.Share(this, this.X.content, this.X.image, r9i, -1);
                    } else if (QsbkApp.currentUser == null) {
                        Toast.makeText(this.O, "\u767b\u5f55\u540e\u624d\u80fd\u5206\u4eab\u54e6\uff01", 0).show();
                        this.O.startActivity(new Intent(this.O, LoginActivity.class));
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
                                    a(Integer.valueOf(r9i));
                                    startActivityForResult(r1_Intent, XListViewHeader.STATE_REFRESHING);
                                    break;
                                case XListViewHeader.STATE_REFRESHING:
                                    a(Integer.valueOf(r9i));
                                    ToastAndDialog.makeText(QsbkApp.mContext, "\u7ed1\u5b9a\u4fe1\u606f\u8fc7\u671f\uff0c\u8bf7\u91cd\u65b0\u7ed1\u5b9a", Integer.valueOf(0)).show();
                                    startActivityForResult(r1_Intent, XListViewHeader.STATE_REFRESHING);
                                    break;
                                case XListViewFooter.STATE_NOMORE:
                                    r0_ShareUtils.Share(this.O, this.af, r9i);
                                    break;
                            }
                        } else {
                            r1_Intent = new Intent(this, AuthorizeActivity.class);
                            if (r9i != 1) {
                                r1_Intent.putExtra("resultCode", r9i);
                                switch (r2_Integer.intValue()) {
                                    case XListViewHeader.STATE_READY:
                                        a(Integer.valueOf(r9i));
                                        startActivityForResult(r1_Intent, XListViewHeader.STATE_REFRESHING);
                                        break;
                                    case XListViewHeader.STATE_REFRESHING:
                                        a(Integer.valueOf(r9i));
                                        ToastAndDialog.makeText(QsbkApp.mContext, "\u7ed1\u5b9a\u4fe1\u606f\u8fc7\u671f\uff0c\u8bf7\u91cd\u65b0\u7ed1\u5b9a", Integer.valueOf(0)).show();
                                        startActivityForResult(r1_Intent, XListViewHeader.STATE_REFRESHING);
                                        break;
                                    case XListViewFooter.STATE_NOMORE:
                                        r0_ShareUtils.Share(this.O, this.af, r9i);
                                        break;
                                }
                            }
                        }
                        r1_Intent.putExtra(QQDialogAuthorizeActivity.TARGET, "sina");
                        r1_Intent.putExtra("resultCode", r9i);
                        switch (r2_Integer.intValue()) {
                            case XListViewHeader.STATE_READY:
                                a(Integer.valueOf(r9i));
                                startActivityForResult(r1_Intent, XListViewHeader.STATE_REFRESHING);
                                break;
                            case XListViewHeader.STATE_REFRESHING:
                                a(Integer.valueOf(r9i));
                                ToastAndDialog.makeText(QsbkApp.mContext, "\u7ed1\u5b9a\u4fe1\u606f\u8fc7\u671f\uff0c\u8bf7\u91cd\u65b0\u7ed1\u5b9a", Integer.valueOf(0)).show();
                                startActivityForResult(r1_Intent, XListViewHeader.STATE_REFRESHING);
                                break;
                            case XListViewFooter.STATE_NOMORE:
                                r0_ShareUtils.Share(this.O, this.af, r9i);
                                break;
                        }
                    }
                }
            } else if (r8i == 2) {
                r0_ShareUtils.Share(this.O, this.af, r9i);
            } else if (r8i == 3) {
                q().reportArticle(this.af, r9i);
            }
            super.onActivityResult(r8i, r9i, r10_Intent);
        }
    }

    protected void onCreate(Bundle r2_Bundle) {
        super.onCreate(r2_Bundle);
        setContentView(R.layout.activity_singlearticle);
        a(getIntent(), r2_Bundle);
        this.n = UIHelper.getColorStateList(R.color.vote_active);
        this.o = UIHelper.getColorStateList(R.color.vote_active_night);
        this.p = UIHelper.getColorStateList(R.color.vote);
        this.q = UIHelper.getColorStateList(R.color.vote_night);
        r();
        c();
        if (this.ae) {
            d();
        } else {
            i();
            a(this.X);
        }
    }

    protected void onDestroy() {
        this.s.clear();
        this.t.clear();
        this.u.clear();
        super.onDestroy();
    }

    public boolean onDown(MotionEvent r2_MotionEvent) {
        return false;
    }

    public boolean onFling(MotionEvent r3_MotionEvent, MotionEvent r4_MotionEvent, float r5f, float r6f) {
        if (r3_MotionEvent == null || r4_MotionEvent == null || r3_MotionEvent.getX() >= 70.0f || r4_MotionEvent.getX() - r3_MotionEvent.getX() <= ((float) this.M) || Math.abs(r5f) <= ((float) this.N)) {
            return false;
        }
        finish();
        return false;
    }

    public void onLongPress(MotionEvent r1_MotionEvent) {
    }

    public void onRestoreInstanceState(Bundle r2_Bundle) {
        super.onRestoreInstanceState(r2_Bundle);
        this.X = (Article) r2_Bundle.getSerializable("currentArticle");
    }

    protected void onSaveInstanceState(Bundle r3_Bundle) {
        r3_Bundle.putSerializable("currentArticle", this.X);
        super.onSaveInstanceState(r3_Bundle);
    }

    public boolean onScroll(MotionEvent r2_MotionEvent, MotionEvent r3_MotionEvent, float r4f, float r5f) {
        return false;
    }

    public void onShowPress(MotionEvent r1_MotionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent r2_MotionEvent) {
        return false;
    }

    protected void onStart() {
        h();
        super.onStart();
    }

    public boolean onTouch(View r2_View, MotionEvent r3_MotionEvent) {
        return this.L.onTouchEvent(r3_MotionEvent);
    }
}