package qsbk.app.activity.group;

import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.android.pushservice.PushManager;
import com.baidu.mobstat.StatService;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.GuideActivity;
import qsbk.app.activity.IndexActivity;
import qsbk.app.activity.LatestActivity;
import qsbk.app.activity.SuggestActivity;
import qsbk.app.activity.base.GroupBaseActivity;
import qsbk.app.manager.PushMessageManager;
import qsbk.app.service.ConfigService;
import qsbk.app.service.VerifyUserInfoService;
import qsbk.app.service.VersionCheckService;
import qsbk.app.utils.Base64;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.listview.XListViewHeader;

public class TopActivityGroup extends GroupBaseActivity implements OnClickListener {
    private static boolean x;
    private static boolean y;
    private String p;
    private Button q;
    private Button r;
    private Button s;
    private ImageView t;
    private LinearLayout u;
    private TextView v;
    private int w;

    static {
        x = false;
        y = true;
    }

    public TopActivityGroup() {
        this.p = "TopActivityGroup";
        this.w = 0;
    }

    private void a(int r4i) {
        String r0_String = SharePreferenceUtils.getSharePreferencesValue("loc");
        if (this.u == null || r0_String.equals("null") || TextUtils.isEmpty(r0_String)) {
        } else {
            try {
                this.u.setVisibility(r4i);
                this.v.setText(new JSONObject(r0_String).getString("name"));
            } catch (Exception e) {
                e.printStackTrace();
                this.u.setVisibility(Base64.DONT_BREAK_LINES);
            }
        }
    }

    private void a(View r12_View) {
        String r0_String = (String) r12_View.getTag();
        switch (r12_View.getId()) {
            case R.id.tab_left:
                if (r0_String.equals("normal")) {
                    n();
                    a((int)Base64.DONT_BREAK_LINES);
                    if (UIHelper.isNightTheme()) {
                        this.q.setTag("active");
                        this.q.setBackgroundResource(R.drawable.top_tab_active_night);
                        this.q.setTextColor(this.h);
                        this.s.setTag("normal");
                        this.s.setBackgroundResource(R.drawable.top_tab_normal);
                        this.s.setTextColor(this.j);
                        this.r.setTag("normal");
                        this.r.setBackgroundResource(R.drawable.top_tab_normal);
                        this.r.setTextColor(this.j);
                    } else {
                        this.q.setTag("active");
                        this.q.setBackgroundResource(R.drawable.top_tab_active);
                        this.q.setTextColor(this.g);
                        this.s.setTag("normal");
                        this.s.setBackgroundResource(R.drawable.top_tab_normal);
                        this.s.setTextColor(this.i);
                        this.r.setTag("normal");
                        this.r.setBackgroundResource(R.drawable.top_tab_normal);
                        this.r.setTextColor(this.i);
                    }
                    b("Suggest");
                } else {
                    h();
                }
                break;
            case R.id.tab_center:
                if (r0_String.equals("normal")) {
                    n();
                    a((int)Base64.DONT_BREAK_LINES);
                    if (UIHelper.isNightTheme()) {
                        this.s.setTag("active");
                        this.s.setBackgroundResource(R.drawable.top_tab_active_night);
                        this.s.setTextColor(this.h);
                        this.s.setSelected(true);
                        this.q.setTag("normal");
                        this.q.setBackgroundResource(R.drawable.top_tab_normal);
                        this.q.setTextColor(this.j);
                        this.r.setTag("normal");
                        this.r.setBackgroundResource(R.drawable.top_tab_normal);
                        this.r.setTextColor(this.j);
                        this.t.setVisibility(Base64.DONT_BREAK_LINES);
                    } else {
                        this.s.setTag("active");
                        this.s.setBackgroundResource(R.drawable.top_tab_active);
                        this.s.setTextColor(this.g);
                        this.s.setSelected(true);
                        this.q.setTag("normal");
                        this.q.setBackgroundResource(R.drawable.top_tab_normal);
                        this.q.setTextColor(this.i);
                        this.r.setTag("normal");
                        this.r.setBackgroundResource(R.drawable.top_tab_normal);
                        this.r.setTextColor(this.i);
                    }
                    b("Latest");
                } else {
                    h();
                }
                break;
            case R.id.tab_right:
                SharePreferenceUtils.setSharePreferencesValue("location_tip_time", String.valueOf(System.currentTimeMillis() / 1000));
                this.t.setVisibility(Base64.DONT_BREAK_LINES);
                a(0);
                if (r0_String.equals("normal")) {
                    if (UIHelper.isNightTheme()) {
                        this.r.setTag("active");
                        this.r.setBackgroundResource(R.drawable.top_tab_active_night);
                        this.r.setTextColor(this.h);
                        this.r.setSelected(true);
                        this.q.setTag("normal");
                        this.q.setBackgroundResource(R.drawable.top_tab_normal);
                        this.q.setTextColor(this.j);
                        this.s.setTag("normal");
                        this.s.setBackgroundResource(R.drawable.top_tab_normal);
                        this.s.setTextColor(this.j);
                    } else {
                        this.r.setTag("active");
                        this.r.setBackgroundResource(R.drawable.top_tab_active);
                        this.r.setTextColor(this.g);
                        this.r.setSelected(true);
                        this.q.setTag("normal");
                        this.q.setBackgroundResource(R.drawable.top_tab_normal);
                        this.q.setTextColor(this.i);
                        this.s.setTag("normal");
                        this.s.setBackgroundResource(R.drawable.top_tab_normal);
                        this.s.setTextColor(this.i);
                    }
                    b("Nearby");
                } else {
                    h();
                }
                break;
        }
    }

    private void b(View r8_View) {
        String r0_String = (String) r8_View.getTag();
        switch (r8_View.getId()) {
            case R.id.tab_left:
                if (r0_String.equals("normal")) {
                    n();
                    a((int)Base64.DONT_BREAK_LINES);
                    this.q.setTag("active");
                    if (UIHelper.isNightTheme()) {
                        this.q.setBackgroundResource(R.drawable.top_tab_active_night);
                        this.q.setTextColor(this.h);
                        this.r.setTag("normal");
                        this.r.setBackgroundResource(R.drawable.top_tab_normal);
                        this.r.setTextColor(this.j);
                    } else {
                        this.q.setBackgroundResource(R.drawable.top_tab_active);
                        this.q.setTextColor(this.g);
                        this.r.setTag("normal");
                        this.r.setBackgroundResource(R.drawable.top_tab_normal);
                        this.r.setTextColor(this.i);
                    }
                    b("Suggest");
                } else {
                    h();
                }
                break;
            case R.id.tab_right:
                this.t.setVisibility(Base64.DONT_BREAK_LINES);
                a(0);
                if (r0_String.equals("normal")) {
                    if (UIHelper.isNightTheme()) {
                        this.r.setTag("active");
                        this.r.setBackgroundResource(R.drawable.top_tab_active_night);
                        this.r.setTextColor(this.h);
                        this.r.setSelected(true);
                        this.q.setTag("normal");
                        this.q.setBackgroundResource(R.drawable.top_tab_normal);
                        this.q.setTextColor(this.j);
                    } else {
                        this.r.setTag("active");
                        this.r.setBackgroundResource(R.drawable.top_tab_active);
                        this.r.setTextColor(this.g);
                        this.r.setSelected(true);
                        this.q.setTag("normal");
                        this.q.setBackgroundResource(R.drawable.top_tab_normal);
                        this.q.setTextColor(this.i);
                    }
                    b("Latest");
                } else {
                    h();
                }
                break;
        }
    }

    private void b(String r7_String) {
        this.c.removeAllViews();
        o();
        Intent r1_Intent = new Intent(this, IndexActivity.class).addFlags(67108864);
        Window r0_Window;
        try {
            Window r0_Window_2;
            if (QsbkApp.indexConfig != null) {
                JSONArray r2_JSONArray = QsbkApp.indexConfig.getJSONArray("index");
                int r0i = 0;
                if ("Suggest".equals(r7_String)) {
                    r1_Intent.putExtra("cacheUniqueName", "suggest");
                } else if ("Latest".equals(r7_String)) {
                    r0i = 1;
                    r1_Intent.putExtra("cacheUniqueName", "latest");
                } else {
                    r0i = XListViewHeader.STATE_REFRESHING;
                    r1_Intent.putExtra("cacheUniqueName", "nearby");
                }
                r1_Intent.putExtra("shuffle", r2_JSONArray.optJSONObject(r0i).getBoolean("shuffle"));
                r1_Intent.putExtra(Constants.PARAM_TITLE, r2_JSONArray.optJSONObject(r0i).getString(Constants.PARAM_TITLE));
                r1_Intent.putExtra("targetDataUrl", r2_JSONArray.optJSONObject(r0i).getString(Constants.PARAM_URL));
                r0_Window_2 = getLocalActivityManager().startActivity(r7_String, r1_Intent);
            } else if ("Suggest".equals(r7_String)) {
                r0_Window_2 = getLocalActivityManager().startActivity("Suggest", new Intent(this, SuggestActivity.class).addFlags(67108864));
            } else {
                r0_Window_2 = getLocalActivityManager().startActivity("Latest", new Intent(this, LatestActivity.class).addFlags(67108864));
            }
            if (this.c == null || r0_Window_2 == null) {
            } else {
                this.c.addView(r0_Window_2.getDecorView());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void j() {
        startActivity(new Intent(this, GuideActivity.class));
    }

    private void k() {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=qsbk.app")));
            SharePreferenceUtils.setSharePreferencesValue("isRated", "true");
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "\u611f\u8c22\u60a8\u7684\u652f\u6301, \u6211\u4eec\u4f1a\u66f4\u52a0\u52aa\u529b.", 0).show();
        }
    }

    private boolean l() {
        String r0_String = SharePreferenceUtils.getSharePreferencesValue("location_tip_time");
        Long r1_Long = Long.valueOf(System.currentTimeMillis() / 1000);
        if (this.w > 0) {
            if (TextUtils.isEmpty(r0_String) || r1_Long.longValue() - Long.valueOf(r0_String).longValue() > ((long) this.w)) {
                return true;
            }
        }
        return false;
    }

    private void m() {
        startService(new Intent(this, ConfigService.class));
    }

    private void n() {
    }

    private void o() {
        if (QsbkApp.isChange) {
            if (getLocalActivityManager().getActivity("Suggest") != null) {
                getLocalActivityManager().destroyActivity("Suggest", true);
            }
            if (getLocalActivityManager().getActivity("Latest") != null) {
                getLocalActivityManager().destroyActivity("Latest", true);
            }
            QsbkApp.isChange = false;
        }
    }

    private void p() {
        new Handler(Looper.getMainLooper()).postDelayed(new i(this), 3000);
    }

    protected void a() {
        super.a();
        this.q = (Button) this.b.findViewById(R.id.tab_left);
        this.r = (Button) this.b.findViewById(R.id.tab_right);
        this.t = (ImageView) this.b.findViewById(R.id.location_tip);
        if (QsbkApp.indexConfig != null) {
            this.s = (Button) this.b.findViewById(R.id.tab_center);
            JSONArray r0_JSONArray = QsbkApp.indexConfig.getJSONArray("index");
            if (r0_JSONArray.length() > 2) {
                this.q.setText(r0_JSONArray.getJSONObject(0).getString(Constants.PARAM_TITLE));
                this.s.setText(r0_JSONArray.getJSONObject(1).getString(Constants.PARAM_TITLE));
                this.r.setText(r0_JSONArray.getJSONObject(XListViewHeader.STATE_REFRESHING).getString(Constants.PARAM_TITLE));
                this.w = r0_JSONArray.getJSONObject(XListViewHeader.STATE_REFRESHING).getInt("badge");
                if (l()) {
                    this.t.setVisibility(0);
                } else {
                    this.t.setVisibility(Base64.DONT_BREAK_LINES);
                }
                this.v = (TextView) this.b.findViewById(R.id.nearby);
                this.u = (LinearLayout) this.b.findViewById(R.id.nearbyLayout);
            } else {
                this.q.setText(r0_JSONArray.getJSONObject(0).getString(Constants.PARAM_TITLE));
                this.r.setText(r0_JSONArray.getJSONObject(1).getString(Constants.PARAM_TITLE));
                this.w = r0_JSONArray.getJSONObject(1).getInt("badge");
                if (l()) {
                    this.t.setVisibility(0);
                } else {
                    this.t.setVisibility(Base64.DONT_BREAK_LINES);
                }
            }
        }
    }

    protected void b() {
        super.b();
        this.q.setOnClickListener(this);
        this.r.setOnClickListener(this);
        if (this.s != null) {
            this.s.setOnClickListener(this);
        }
    }

    public void checkAndGotoMarketIfNecessary(Context r4_Context, boolean r5z) throws ActivityNotFoundException {
        if (r5z) {
            k();
        } else {
            if ((TextUtils.isEmpty(SharePreferenceUtils.getSharePreferencesValue("isRated")) ? 0 : 1) == 0) {
                int r0i = QsbkApp.getInstance().getLaunchedCount();
                if (r0i == 8 || r0i == 18 || r0i == 33) {
                    new Builder(this).setTitle("\u8bf7\u652f\u6301\u6211\u4eec").setMessage("\u60a8\u7684\u652f\u6301\u662f\u6211\u4eec\u7684\u52a8\u529b\uff0c\u7cd7\u53cb\u4eec\u6c42\u7ed9\u4e2a\u597d\u8bc4\u9f13\u52b1\u4e0b\uff01").setNegativeButton("\u679c\u65ad\u7ed9\u597d\u8bc4", new h(this)).setPositiveButton("\u6709\u610f\u89c1\u8981\u8bf4", new g(this)).setNeutralButton("\u7a0d\u540e", new f(this)).show();
                }
            }
        }
    }

    public boolean equals(Object r5_Object) {
        if (this == r5_Object) {
            return true;
        }
        if (r5_Object == null) {
            return false;
        }
        if (getClass() != r5_Object.getClass()) {
            return false;
        }
        TopActivityGroup r5_TopActivityGroup = (TopActivityGroup) r5_Object;
        if (this.p == null) {
            return r5_TopActivityGroup.p == null;
        } else {
            if (this.p.equals(r5_TopActivityGroup.p)) {
                return true;
            }
            return false;
        }
    }

    protected int f() {
        if (QsbkApp.indexConfig == null) {
            return R.layout.activity_group_two_tab;
        }
        try {
            return QsbkApp.indexConfig.getJSONArray("index").length() > XListViewHeader.STATE_REFRESHING ? R.layout.activity_group_three_tab : R.layout.activity_group_two_tab;
        } catch (JSONException e) {
            e.printStackTrace();
            return R.layout.activity_group_two_tab;
        }
    }

    public int hashCode() {
        return (this.p == null ? 0 : this.p.hashCode()) + 31;
    }

    public void initPushService() {
        PushMessageManager.startPushIfStopedOnce();
        if (PushMessageManager.getReceiveMsgFromLocal()) {
            PushMessageManager.startWork();
            StatService.onEvent(this, "push_started", RContactStorage.PRIMARY_KEY);
        } else {
            PushMessageManager.stopWork();
            if (PushMessageManager.needPromptOpenPush()) {
                p();
                PushMessageManager.setPushPrompted();
            }
            StatService.onEvent(this, "push_stoped", RContactStorage.PRIMARY_KEY);
        }
    }

    public void onClick(View r3_View) {
        if (QsbkApp.indexConfig != null) {
            if (QsbkApp.indexConfig.getJSONArray("index").length() > XListViewHeader.STATE_REFRESHING) {
                a(r3_View);
            } else {
                b(r3_View);
            }
        } else {
            b(r3_View);
        }
    }

    protected void onCreate(Bundle r5_Bundle) {
        super.onCreate(r5_Bundle);
        CharSequence r0_CharSequence = getIntent().getStringExtra("subActivity");
        if (TextUtils.isEmpty(r0_CharSequence) || "Suggest".equals(r0_CharSequence)) {
            b("Suggest");
        } else if ("Latest".equals(r0_CharSequence)) {
            b("Latest");
        } else {
            b("Nearby");
        }
        if (y) {
            y = false;
            j();
        }
        if (QsbkApp.hasVerify || UIHelper.isNightTheme()) {
            initPushService();
            m();
            if (x) {
                new Handler(Looper.getMainLooper()).postDelayed(new e(this), 5000);
            }
            if (QsbkApp.isPad) {
                StatService.onEvent(this, "pad", "yes");
            } else {
                StatService.onEvent(this, "pad", "no");
            }
        } else {
            startMyService();
            initPushService();
            m();
            if (x) {
                if (QsbkApp.isPad) {
                    StatService.onEvent(this, "pad", "no");
                } else {
                    StatService.onEvent(this, "pad", "yes");
                }
            } else {
                new Handler(Looper.getMainLooper()).postDelayed(new e(this), 5000);
                if (QsbkApp.isPad) {
                    StatService.onEvent(this, "pad", "yes");
                } else {
                    StatService.onEvent(this, "pad", "no");
                }
            }
        }
    }

    protected void onStart() {
        super.onStart();
        PushManager.activityStarted(this);
    }

    protected void onStop() {
        PushManager.activityStoped(this);
        super.onStop();
    }

    public void startMyService() {
        new l(this, "qbk-TopAct").start();
    }

    public void startVerifyService() {
        QsbkApp.tmpContext = this;
        startService(new Intent(this, VerifyUserInfoService.class));
        DebugUtil.debug("\u542f\u52a8\u7528\u6237\u9a8c\u8bc1\u670d\u52a1");
    }

    public void startVersionService() {
        startService(new Intent(this, VersionCheckService.class));
    }
}