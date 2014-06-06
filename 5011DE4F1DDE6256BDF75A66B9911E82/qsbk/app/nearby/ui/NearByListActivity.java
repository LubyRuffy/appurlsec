package qsbk.app.nearby.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import com.qiubai.library.adview.util.AdViewUtil;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.R;
import qsbk.app.activity.EditInfoEntranceActivity.EDIT_TYPE;
import qsbk.app.activity.StatFragmentActivity;
import qsbk.app.activity.base.GroupBaseActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.nearby.api.BDLocationManger;
import qsbk.app.nearby.api.GDLocationManager;
import qsbk.app.nearby.api.ILocationCallback;
import qsbk.app.nearby.api.ILocationManager;
import qsbk.app.nearby.api.NearbyEngine;
import qsbk.app.nearby.api.NearbyUser;
import qsbk.app.nearby.ui.AlertDialog.Builder;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.ActivityExitHelper;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastUtil;
import qsbk.app.widget.listview.XListView;
import qsbk.app.widget.listview.XListView.IXListViewListener;

public class NearByListActivity extends StatFragmentActivity implements ILocationCallback, IXListViewListener {
    public static final String DIALOG_KEY_CLEAR_POSITION = "clear_positon";
    public static final String DIALOG_KEY_INFOCOMPLETE = "infocomplete";
    public static final String DIALOG_KEY_NEARBYLIST = "nearbylist";
    public static final String DIALOG_KEY_REQ_LOCATION = "location";
    private static double I = 0.0d;
    private static double J = 0.0d;
    private static long K = 0;
    public static final int REQ_INFO_COMPLETE = 3;
    public static final int REQ_LOCATION_SERVICE = 2;
    private boolean A;
    private View B;
    private NearbySelectView C;
    private String D;
    private int E;
    private boolean F;
    private int G;
    private ActivityExitHelper H;
    private int L;
    protected XListView n;
    protected LinearLayout o;
    protected LinearLayout p;
    protected NearbyListAdapter q;
    protected ArrayList<NearbyUser> r;
    protected TextView s;
    Handler t;
    private ILocationManager u;
    private ProgressDialog v;
    private String w;
    private PopupWindow x;
    private boolean y;
    private Runnable z;

    static {
        I = 0.0d;
        J = 0.0d;
        K = 0;
    }

    public NearByListActivity() {
        this.r = new ArrayList();
        this.u = null;
        this.v = null;
        this.w = null;
        this.t = new Handler();
        this.y = false;
        this.z = null;
        this.A = true;
        this.B = null;
        this.D = null;
        this.F = false;
        this.G = 1;
        this.H = new x(this);
        this.L = 0;
    }

    private ArrayList<NearbyUser> a(JSONArray r6_JSONArray) {
        ArrayList<NearbyUser> r1_ArrayList_NearbyUser = new ArrayList();
        int r0i = 0;
        while (r0i < r6_JSONArray.length()) {
            JSONObject r2_JSONObject = r6_JSONArray.optJSONObject(r0i);
            NearbyUser r3_NearbyUser = new NearbyUser();
            r3_NearbyUser.age = r2_JSONObject.optInt("age");
            r3_NearbyUser.gender = r2_JSONObject.optString(EDIT_TYPE.TYPE_GENDER);
            r3_NearbyUser.signature = r2_JSONObject.optString(EDIT_TYPE.TYPE_SIGNATURE);
            r3_NearbyUser.mDistance = r2_JSONObject.optInt("dis");
            r3_NearbyUser.mLastVisitTime = r2_JSONObject.optInt("last_visit_time");
            r3_NearbyUser.userId = r2_JSONObject.optString("uid");
            r3_NearbyUser.userName = r2_JSONObject.optString("name");
            r3_NearbyUser.userIcon = r2_JSONObject.optString(QsbkDatabase.ICON);
            r1_ArrayList_NearbyUser.add(r3_NearbyUser);
            r0i++;
        }
        return r1_ArrayList_NearbyUser;
    }

    private void a(int r6i) {
        View[] r2_ViewA = new View[3];
        r2_ViewA[0] = this.n;
        r2_ViewA[1] = this.o;
        r2_ViewA[2] = this.p;
        int r0i = 0;
        while (r0i < r2_ViewA.length) {
            if (r2_ViewA[r0i] != null) {
                if (r0i == r6i) {
                    r2_ViewA[r0i].setVisibility(0);
                } else {
                    r2_ViewA[r0i].setVisibility(Base64.DONT_BREAK_LINES);
                }
            }
            r0i++;
        }
    }

    private void a(int r2i, String r3_String) {
        a(getString(r2i), r3_String);
    }

    private void a(String r3_String, int r4i) {
        if (!(r3_String.equals(this.D) && this.E == r4i)) {
            this.D = r3_String;
            this.E = r4i;
            NearbyEngine.instance().setLocalFilterSex(this.D);
            NearbyEngine.instance().setLocalFilterLastLogin(this.E);
            this.F = true;
            init();
        }
    }

    private void a(String r4_String, String r5_String) {
        if (this.v != null) {
            this.v.dismiss();
        }
        this.v = ProgressDialog.show(this, null, r4_String, true, false);
        this.w = r5_String;
    }

    private void b(String r2_String) {
        if (this.v == null || (!r2_String.equals(this.w))) {
        } else {
            this.v.dismiss();
            this.v = null;
        }
    }

    private void b(JSONArray r7_JSONArray) {
        int r1i;
        Iterator r4_Iterator = (r7_JSONArray != null ? a(r7_JSONArray) : new ArrayList()).iterator();
        if (this.F) {
            this.r.clear();
            r1i = 1;
        } else {
            r1i = 0;
        }
        while (r4_Iterator.hasNext()) {
            int r0i;
            NearbyUser r0_NearbyUser = (NearbyUser) r4_Iterator.next();
            if (this.r.contains(r0_NearbyUser)) {
                r0i = r1i;
            } else {
                this.r.add(r0_NearbyUser);
                r0i = 1;
            }
            r1i = r0i;
        }
        if (r1i != 0) {
            this.q.notifyDataSetChanged();
            if (this.F) {
                this.n.setSelection(0);
            }
        }
        this.F = false;
    }

    private void b(boolean r4z) {
        if (HttpUtils.netIsAvailable()) {
            if (this.D == null) {
                this.D = NearbyEngine.instance().getLocalFilterSex();
                this.E = NearbyEngine.instance().getLocalFilterTime();
            }
            HttpAsyncTask r0_HttpAsyncTask = new w(this, r4z);
            if (!r4z) {
                a((int)R.string.request_nearby_list, DIALOG_KEY_NEARBYLIST);
            }
            r0_HttpAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
        } else {
            ToastUtil.Short((int)R.string.network_not_connected);
        }
    }

    public static AlertDialog buildAlertDialog(Context r3_Context, int r4i, int r5i, int r6i, OnClickListener r7_OnClickListener) {
        Builder r0_Builder = new Builder(r3_Context);
        r0_Builder.setMessage(r5i).setTitle(r4i).setCancelable(false).setPositiveButton(r6i, r7_OnClickListener);
        return (AlertDialog) r0_Builder.create();
    }

    private void c(String r2_String) {
        StatService.onEvent(this, DIALOG_KEY_REQ_LOCATION, r2_String);
    }

    private void e() {
        this.n = (XListView) findViewById(R.id.listview);
        this.n.setPullRefreshEnable(false);
        this.n.setPullLoadEnable(true);
        this.n.loadNoMore();
        this.n.setXListViewListener(this);
        this.n.setOnItemClickListener(new n(this));
        this.o = (LinearLayout) findViewById(R.id.clear_layout);
        this.s = (TextView) findViewById(R.id.post_clear_msg);
        ((Button) this.o.findViewById(R.id.open_nearby)).setOnClickListener(new y(this));
        this.p = (LinearLayout) findViewById(R.id.open_location_services);
        ((Button) this.p.findViewById(R.id.id_btn_open_service)).setOnClickListener(new aa(this));
        this.q = new NearbyListAdapter(this, this.r);
        this.n.setAdapter(this.q);
        l();
        k();
        j();
    }

    private void f() {
        HttpAsyncTask r0_HttpAsyncTask = new ab(this);
        a((int)R.string.clear_position, DIALOG_KEY_CLEAR_POSITION);
        r0_HttpAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
    }

    private void h() {
        Builder r0_Builder = new Builder(this);
        r0_Builder.setMessage(R.string.msg_is_cleared).setCancelable(false).setPositiveButton((int)R.string.sure, new ac(this));
        ((AlertDialog) r0_Builder.create()).show();
    }

    private void i() {
        ((GroupBaseActivity) getParent()).resumeMenuLayout();
    }

    private void j() {
        View r1_View = getLayoutInflater().inflate(R.layout.nearby_pop_window, null);
        this.x = new PopupWindow(r1_View, -2, -2, true);
        this.x.setTouchable(true);
        this.x.setOutsideTouchable(true);
        this.x.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) false));
        TextView r0_TextView = (TextView) r1_View.findViewById(R.id.id_clear_position);
        if (r0_TextView != null) {
            r0_TextView.setOnClickListener(new ad(this));
        }
        ((TextView) r1_View.findViewById(R.id.id_setting)).setOnClickListener(new ae(this));
        ((TextView) r1_View.findViewById(R.id.id_feedback)).setOnClickListener(new af(this));
        ((TextView) r1_View.findViewById(R.id.id_refresh)).setOnClickListener(new ag(this));
    }

    private void k() {
        ((NearActivityGroup) getParent()).setMenuBtnListener(new o(this));
    }

    private void l() {
        ((NearActivityGroup) getParent()).setExtraBtnListener(new p(this));
    }

    private ILocationManager m() {
        String r0_String = SharePreferenceUtils.getSharePreferencesValue("nearby_location_manager");
        if (TextUtils.isEmpty(r0_String) || (!r0_String.equals("gaode"))) {
            LogUtil.d("use baidu location");
            c("use_bd_location");
            return BDLocationManger.instance();
        } else {
            LogUtil.d("use gaode location");
            c("use_gd_location");
            return GDLocationManager.instance();
        }
    }

    static /* synthetic */ int n(NearByListActivity r2_NearByListActivity) {
        int r0i = r2_NearByListActivity.G;
        r2_NearByListActivity.G = r0i + 1;
        return r0i;
    }

    private void n() {
        startActivityForResult(new Intent(this, InfoCompleteActivity.class), REQ_INFO_COMPLETE);
    }

    private void o() {
        if (NearbyEngine.instance().isLocationServiceEnabled(this)) {
            a(0);
            r();
        } else {
            s();
        }
    }

    private void p() {
        if (NearbyEngine.instance().isNearbyInfoComplete()) {
            o();
        } else {
            v();
        }
    }

    private void q() {
        a((int)R.string.get_user_location, DIALOG_KEY_REQ_LOCATION);
        this.y = true;
        int r0i = this.u.getLocation(this);
        LogUtil.d("ret:" + r0i);
        if (r0i == ShareUtils.SHARE_COPY) {
            this.t.postDelayed(new q(this), 2000);
        }
    }

    private void r() {
        if (System.currentTimeMillis() - K < 120000) {
            b(false);
        } else {
            q();
        }
    }

    private void s() {
        a((int)REQ_LOCATION_SERVICE);
    }

    private void t() {
        Builder r0_Builder = new Builder(this);
        r0_Builder.setTitle(R.string.nearby_pop_title).setPositiveButton((int)R.string.nearby_pop_btn_ok, new s(this)).setNegativeButton((int)R.string.nearby_pop_btn_deny, new r(this));
        AlertDialog r0_AlertDialog = (AlertDialog) r0_Builder.create();
        this.B = r0_AlertDialog.setCustomView((int)R.layout.layout_nearby_info_notify);
        r0_AlertDialog.setOnCancelListener(new t(this));
        r0_AlertDialog.show();
        r0_AlertDialog.setCanceledOnTouchOutside(true);
    }

    private void u() {
        Builder r0_Builder = new Builder(this);
        r0_Builder.setTitle(R.string.nearby_pop_title).setPositiveButton((int)R.string.dialog_btn_confirm, new v(this)).setNegativeButton((int)R.string.dialog_btn_cancel, new u(this));
        AlertDialog r0_AlertDialog = (AlertDialog) r0_Builder.create();
        r0_AlertDialog.hideTitleLayout();
        this.C = new NearbySelectView(r0_AlertDialog.setCustomView((int)R.layout.layout_nearby_user_select));
        LogUtil.d("getLocalFilterSex:" + NearbyEngine.instance().getLocalFilterSex());
        LogUtil.d("getLocalFilterTime:" + NearbyEngine.instance().getLocalFilterTime());
        this.C.setGender(NearbyEngine.instance().getLocalFilterSex());
        this.C.setTimeInMinute(NearbyEngine.instance().getLocalFilterTime());
        r0_AlertDialog.show();
        r0_AlertDialog.setCanceledOnTouchOutside(true);
    }

    private void v() {
        a((int)R.string.check_info_complete, DIALOG_KEY_INFOCOMPLETE);
        new z(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
    }

    private void w() {
        if (this.u instanceof BDLocationManger) {
            SharePreferenceUtils.setSharePreferencesValue("nearby_location_manager", "gaode");
        } else {
            SharePreferenceUtils.setSharePreferencesValue("nearby_location_manager", "baidu");
        }
        if (this.u != null) {
            this.u.stop();
        }
        this.u = m();
    }

    public void finish() {
        super.finish();
        if (this.u != null) {
            this.u.stop();
        }
    }

    protected void g() {
        this.u = m();
        init();
    }

    public Pair<Double, Double> getLastSavedPosition() {
        String r1_String = SharePreferenceUtils.getSharePreferencesValue("local_phone_lcoation");
        if (TextUtils.isEmpty(r1_String)) {
            return null;
        }
        String[] r2_StringA = r1_String.split(",");
        try {
            return new Pair(Double.valueOf(Double.parseDouble(r2_StringA[0])), Double.valueOf(Double.parseDouble(r2_StringA[1])));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void init() {
        a(0);
        this.G = 1;
        if (this.A) {
            if (NearbyEngine.instance().isNearbyNoMoreWarn()) {
                this.A = false;
                p();
            } else {
                t();
            }
        } else {
            p();
        }
    }

    protected void onActivityResult(int r2i, int r3i, Intent r4_Intent) {
        if (r2i == 2) {
            if (!NearbyEngine.instance().isLocationServiceEnabled(this)) {
                ToastUtil.Short("\u8bf7\u6253\u5f00\u5b9a\u4f4d\u670d\u52a1\u6765\u4f7f\u7528\u9644\u8fd1\u7684\u4eba\u7684\u529f\u80fd");
            }
            o();
        } else if (r2i == 3) {
            if (r3i == -1) {
                o();
            } else {
                ToastUtil.Short("\u60a8\u5fc5\u987b\u5148\u5b8c\u5584\u60a8\u7684\u4e2a\u4eba\u4fe1\u606f\u624d\u80fd\u4f7f\u7528\u9644\u8fd1\u529f\u80fd");
                show_restart();
            }
        }
        super.onActivityResult(r2i, r3i, r4_Intent);
    }

    protected void onCreate(Bundle r2_Bundle) {
        super.onCreate(r2_Bundle);
        setContentView(R.layout.activity_nearby_list);
        e();
    }

    protected void onDestroy() {
        K = 0;
        super.onDestroy();
    }

    public void onInitHistoryData() {
    }

    public boolean onKeyDown(int r2i, KeyEvent r3_KeyEvent) {
        if (r2i == 82) {
            ((GroupBaseActivity) getParent()).resumeMenuLayout();
            return true;
        } else {
            if (r2i == 4) {
                return this.H.handleBackPressed();
            }
            return super.onKeyDown(r2i, r3_KeyEvent);
        }
    }

    public void onLoadMore() {
        b(true);
        LogUtil.d("on load more");
    }

    public void onLocation(int r6i, double r7d, double r9d) {
        LogUtil.d("on location");
        if (this.y) {
            this.y = false;
            b(DIALOG_KEY_REQ_LOCATION);
            LogUtil.d("location type:" + r6i);
            LogUtil.d("location latitude:" + r7d);
            LogUtil.d("location longtitude:" + r9d);
            if (r6i == 161 || r6i == 0) {
                saveLastLocationToLocal(r7d, r9d);
            }
            if (r6i == 61 || r6i == 65 || r6i == 66 || r6i == 68 || r6i == 161 || r6i == 0) {
                I = r7d;
                J = r9d;
                K = System.currentTimeMillis();
                b(false);
            } else {
                this.L++;
                w();
                if (this.L >= 2) {
                    this.L = 0;
                    Pair r1_Pair = getLastSavedPosition();
                    if (r1_Pair != null) {
                        LogUtil.d("use last saved position");
                        I = ((Double) r1_Pair.first).doubleValue();
                        J = ((Double) r1_Pair.second).doubleValue();
                        b(false);
                    } else {
                        ToastUtil.Short((int)R.string.get_location_error);
                        show_restart();
                        c(AdViewUtil.COUNTFAIL);
                    }
                } else {
                    LogUtil.d("get location first error");
                    q();
                }
            }
        } else {
            LogUtil.d("is ruquesting location");
        }
    }

    protected void onPause() {
        super.onPause();
    }

    public void onRefresh() {
        LogUtil.d("on refresh");
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    public void openLocationSettingActivity(Activity r4_Activity) {
        try {
            r4_Activity.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), REQ_LOCATION_SERVICE);
        } catch (Exception e) {
            LogUtil.d("open location setting error:" + e.toString());
            c("open_gps_error");
            ToastUtil.Short((int)R.string.no_gps_enabled);
        }
    }

    public void saveLastLocationToLocal(double r4d, double r6d) {
        SharePreferenceUtils.setSharePreferencesValue("local_phone_lcoation", r4d + "," + r6d);
    }

    public void show_restart() {
        show_restart_with_msg(null);
    }

    public void show_restart_with_msg(String r3_String) {
        if (TextUtils.isEmpty(r3_String)) {
            this.s.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            this.s.setText(r3_String);
            this.s.setVisibility(0);
        }
        a(1);
    }
}