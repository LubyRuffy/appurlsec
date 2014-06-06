package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.HashMap;
import java.util.regex.Pattern;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActivity;
import qsbk.app.activity.security.SecurityBindActivity;
import qsbk.app.api.UserHeaderHelper;
import qsbk.app.core.AsyncTask;
import qsbk.app.model.UserInfo;
import qsbk.app.share.AuthorizeActivity;
import qsbk.app.share.QQAuthorizeActivity;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.share.ShareUrl;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.listview.XListViewHeader;

public class UserSetting extends BaseActivity implements OnClickListener {
    private static int E;
    private UserHeaderHelper F;
    private Handler G;
    ShareUtils n;
    ImageView o;
    RelativeLayout p;
    ImageView q;
    RelativeLayout r;
    ImageView s;
    RelativeLayout t;
    ImageView u;
    TextView v;
    ProgressBar w;
    Handler x;

    static {
        E = 0;
    }

    public UserSetting() {
        this.n = new ShareUtils();
        this.G = new dc(this);
        this.x = new dn(this);
    }

    private void c(String r2_String) {
        if (checkingToken(r2_String)) {
            d(r2_String);
        } else {
            e(r2_String);
        }
    }

    private void d(String r4_String) {
        Intent r0_Intent = new Intent(this, AuthorizeActivity.class);
        if (r4_String.equals(this.n.QQ_ACCESS_TOKEN)) {
            r0_Intent = new Intent(this, QQAuthorizeActivity.class);
            ShareUrl.weiboUrl = ShareUrl.qqZoneUrl;
            startActivity(r0_Intent);
        }
        if (r4_String.equals(this.n.RENREN_ACCESS_TOKEN)) {
            ShareUrl.weiboUrl = ShareUrl.renrenUrl;
            r0_Intent.putExtra(QQDialogAuthorizeActivity.TARGET, "renren");
            startActivity(r0_Intent);
        }
        if (r4_String.equals(this.n.SINA_ACCESS_TOKEN)) {
            ShareUrl.weiboUrl = ShareUrl.sinaUrl;
            r0_Intent.putExtra(QQDialogAuthorizeActivity.TARGET, "sina");
            startActivity(r0_Intent);
        }
    }

    private void e(String r5_String) {
        Builder r0_Builder = new Builder(this).setTitle("\u64cd\u4f5c");
        CharSequence[] r1_CharSequenceA = new CharSequence[2];
        r1_CharSequenceA[0] = "\u89e3\u9664\u7ed1\u5b9a";
        r1_CharSequenceA[1] = "\u53d6\u6d88";
        r0_Builder.setItems(r1_CharSequenceA, new db(this, r5_String)).show();
    }

    static /* synthetic */ int f() {
        int r0i = E;
        E = r0i + 1;
        return r0i;
    }

    private void f(String r3_String) {
        if (r3_String.equals(this.n.QQ_ACCESS_TOKEN)) {
            this.s.setVisibility(Base64.DONT_BREAK_LINES);
        }
        if (r3_String.equals(this.n.RENREN_ACCESS_TOKEN)) {
            this.u.setVisibility(Base64.DONT_BREAK_LINES);
        }
        if (r3_String.equals(this.n.SINA_ACCESS_TOKEN)) {
            this.q.setVisibility(Base64.DONT_BREAK_LINES);
        }
    }

    private void h() {
        i();
        if (QsbkApp.currentUser == null || TextUtils.isEmpty(QsbkApp.currentUser.userIcon) || "null".equals(QsbkApp.currentUser.userIcon.toString())) {
            if (QsbkApp.currentUser == null) {
                if (!(TextUtils.isEmpty(QsbkApp.currentUser.wb) && TextUtils.isEmpty(QsbkApp.currentUser.qq))) {
                    this.v.setVisibility(0);
                    this.v.setOnClickListener(this);
                }
            }
            new da(this, "qbk-UserSet2").start();
        } else {
            ((TextView) this.y.findViewById(R.id.RightUserName)).setText(QsbkApp.currentUser.userName);
            QsbkApp.getInstance().getAvatarWorker(this).loadImage(UserHeaderHelper.getIconUrl(QsbkApp.currentUser), (ImageView) this.y.findViewById(R.id.RightUserIcon));
            if (QsbkApp.currentUser == null) {
                new da(this, "qbk-UserSet2").start();
            } else if (TextUtils.isEmpty(QsbkApp.currentUser.wb) || TextUtils.isEmpty(QsbkApp.currentUser.qq)) {
                this.v.setVisibility(0);
                this.v.setOnClickListener(this);
            } else {
                new da(this, "qbk-UserSet2").start();
            }
        }
    }

    private void i() {
        if (b(this.n.SINA_ACCESS_TOKEN)) {
            this.q.setVisibility(Base64.DONT_BREAK_LINES);
            SharePreferenceUtils.remove(this.n.SINA_ACCESS_TOKEN);
        } else {
            this.q.setVisibility(0);
        }
        if (b(this.n.RENREN_ACCESS_TOKEN)) {
            this.u.setVisibility(Base64.DONT_BREAK_LINES);
            SharePreferenceUtils.remove(this.n.RENREN_ACCESS_TOKEN);
        } else {
            this.u.setVisibility(0);
        }
        if (b(this.n.QQ_ACCESS_TOKEN)) {
            this.s.setVisibility(Base64.DONT_BREAK_LINES);
            SharePreferenceUtils.remove(this.n.QQ_ACCESS_TOKEN);
        } else {
            this.s.setVisibility(0);
        }
    }

    public static boolean isEmail(String r2_String) {
        return r2_String.trim().equals(RContactStorage.PRIMARY_KEY) ? false : Pattern.compile("^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$").matcher(r2_String).matches();
    }

    boolean b(String r6_String) {
        String r1_String = SharePreferenceUtils.getSharePreferencesValue(r6_String);
        return TextUtils.isEmpty(r1_String) || Long.valueOf(r1_String.split("&")[1].split("=")[1]).longValue() <= System.currentTimeMillis();
    }

    protected void c() {
        super.c();
        if (QsbkApp.currentUser == null) {
            finish();
        }
        this.w = (ProgressBar) this.y.findViewById(R.id.topLoading);
        this.A.setVisibility(Base64.DONT_BREAK_LINES);
        this.o = (ImageView) this.y.findViewById(R.id.RightUserIcon);
        this.v = (TextView) this.y.findViewById(R.id.security_auth);
        this.p = (RelativeLayout) this.y.findViewById(R.id.sinaLayout);
        this.q = (ImageView) this.y.findViewById(R.id.sina_check);
        this.r = (RelativeLayout) this.y.findViewById(R.id.qqLayout);
        this.s = (ImageView) this.y.findViewById(R.id.qq_check);
        this.t = (RelativeLayout) this.y.findViewById(R.id.renrenLayout);
        this.u = (ImageView) this.y.findViewById(R.id.renren_check);
        this.y.findViewById(R.id.exit).setBackgroundDrawable(UIHelper.getDrawable(R.drawable.input_exit_bg));
    }

    public boolean checkingToken(String r2_String) {
        return TextUtils.isEmpty(SharePreferenceUtils.getSharePreferencesValue(r2_String));
    }

    protected void d() {
        super.d();
        this.z.setOnClickListener(new dd(this));
        this.p.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.o.setOnClickListener(new de(this));
        this.y.findViewById(R.id.exit).setOnClickListener(new dh(this));
    }

    protected int e() {
        return R.layout.activity_user_setting;
    }

    public String getCustomerTitle() {
        return "\u4e2a\u4eba\u8d44\u6599";
    }

    public HashMap<String, Object> getPostParams() {
        return new HashMap();
    }

    public void logout() {
        new dk(this, "qbk-UserSet3").start();
    }

    protected void onActivityResult(int r3i, int r4i, Intent r5_Intent) {
        super.onActivityResult(r3i, r4i, r5_Intent);
        if (r4i != -1) {
        } else {
            switch (r3i) {
                case XListViewHeader.STATE_NORMAL:
                    this.F.doCropPhotoWithCaptured();
                case XListViewHeader.STATE_READY:
                    String r0_String = this.F.savePickedBitmap(r5_Intent);
                    if (!TextUtils.isEmpty(r0_String)) {
                        submitAvatar(r0_String);
                    }
            }
        }
    }

    public void onClick(View r3_View) {
        if (HttpUtils.isNetworkConnected(this)) {
            String r0_String = RContactStorage.PRIMARY_KEY;
            switch (r3_View.getId()) {
                case R.id.sinaLayout:
                    c(this.n.SINA_ACCESS_TOKEN);
                case R.id.qqLayout:
                    c(this.n.QQ_ACCESS_TOKEN);
                case R.id.security_auth:
                    startActivity(new Intent(this, SecurityBindActivity.class));
                case R.id.renrenLayout:
                    c(this.n.RENREN_ACCESS_TOKEN);
            }
        } else {
            Toast.makeText(this, R.string.network_not_connected, 0).show();
        }
    }

    protected void onCreate(Bundle r3_Bundle) {
        if (QsbkApp.currentUser == null) {
            QsbkApp.currentUser = new UserInfo(SharePreferenceUtils.getSharePreferencesValue("loginUser"));
        }
        super.onCreate(r3_Bundle);
        this.F = new UserHeaderHelper(this);
        this.C.trackView("\u4e2a\u4eba\u4e2d\u5fc3/");
    }

    protected void onResume() {
        super.onResume();
        h();
    }

    public void submitAvatar(String r4_String) {
        int r1i = 1;
        int r2i = 0;
        if (HttpUtils.isNetworkConnected(this)) {
            this.w.setVisibility(r2i);
            Toast.makeText(this, "\u6b63\u5728\u4e0a\u4f20\u5934\u50cf...", r1i).show();
            new dl(this, r4_String).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[r2i]);
        } else {
            Toast.makeText(this, R.string.network_not_connected, 1).show();
        }
    }

    public void submitContent() {
        this.w.setVisibility(0);
        new dm(this, "qbk-UserSet5").start();
    }
}