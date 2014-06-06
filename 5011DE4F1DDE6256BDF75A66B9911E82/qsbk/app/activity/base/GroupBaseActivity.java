package qsbk.app.activity.base;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qiubai.library.adview.AdViewInterface;
import com.qiubai.library.adview.AdViewLayout;
import com.qiubai.library.adview.AdViewTargeting;
import com.qiubai.library.adview.AdViewTargeting.SwitcherMode;
import com.qq.e.comm.DownloadService;
import com.tencent.mm.sdk.message.RMsgInfoStorage;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.LoginActivity;
import qsbk.app.adapter.ContentMenuLayoutAdapter;
import qsbk.app.adapter.MenuLayoutAdapter;
import qsbk.app.message.api.ChatEngine;
import qsbk.app.message.api.ChatEngine.UnReadCountChanger;
import qsbk.app.nearby.ui.AlertDialog.Builder;
import qsbk.app.provider.QBMenu;
import qsbk.app.push.PushMessageReceiver;
import qsbk.app.push.PushPingBack;
import qsbk.app.utils.ActivityExitHelper;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.MyViewFlipper;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.slidingmenu.callback.SizeCallBackForMenu;
import qsbk.app.widget.slidingmenu.ui.MenuHorizontalScrollView;

public abstract class GroupBaseActivity extends ActivityGroup implements AdViewInterface, IScrollView, UnReadCountChanger {
    public static ScheduledExecutorService scheduler;
    private OnClickListener A;
    private SwitcherMode B;
    private ActivityExitHelper C;
    protected Context a;
    protected View b;
    protected MyViewFlipper c;
    protected ImageButton d;
    protected ImageButton e;
    protected ImageButton f;
    protected ColorStateList g;
    protected ColorStateList h;
    protected ColorStateList i;
    protected ColorStateList j;
    protected TextView k;
    protected ImageView l;
    protected ImageView m;
    protected OnClickListener n;
    protected AdViewLayout o;
    private MenuHorizontalScrollView p;
    private ListView q;
    private LinearLayout r;
    private MenuLayoutAdapter s;
    private View[] t;
    private LayoutInflater u;
    private Handler v;
    private ImageButton w;
    private String x;
    private Intent y;
    private boolean z;

    private class a implements Runnable {
        private a() {
        }

        public void run() {
            if (GroupBaseActivity.this.z) {
                ChatEngine.getInstance().updateTick_unread();
            }
        }
    }

    public GroupBaseActivity() {
        this.a = this;
        this.c = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.v = new e(this);
        this.x = "contentMenuLayout";
        this.z = true;
        this.n = new m(this);
        this.A = new n(this);
        this.B = SwitcherMode.DEFAULT;
        this.C = new o(this);
    }

    private void b(String r6_String) {
        this.q = (ListView) findViewById(R.id.menuList);
        if ("contentMenuLayout".equals(r6_String)) {
            this.s = new ContentMenuLayoutAdapter((Activity) this.a, 2130968598, getClass(), QBMenu.getContentMenu());
        }
        this.q.setAdapter(this.s);
    }

    private void j() {
        int r4i = Integer.valueOf(TextUtils.isEmpty(SharePreferenceUtils.getSharePreferencesValue("pollTime")) ? "30" : SharePreferenceUtils.getSharePreferencesValue("pollTime")).intValue();
        if (QsbkApp.currentUser != null) {
            scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(new a(null), 0, (long) r4i, TimeUnit.SECONDS);
        }
    }

    private void k() {
        String r0_String = SharePreferenceUtils.getSharePreferencesValue("image-reportable");
        if (TextUtils.isEmpty(r0_String) || (!r0_String.equals("1"))) {
        } else {
            QsbkApp.reportable = true;
        }
    }

    private void l() {
        int r0i = getIntent().getIntExtra(RMsgInfoStorage.PRIMARY_KEY, -1);
        if (r0i != -1) {
            PushPingBack r1_PushPingBack = PushMessageReceiver.getPushPingBackForMessageId(Integer.valueOf(r0i));
            if (r1_PushPingBack != null) {
                r1_PushPingBack.receiveMessage();
                PushMessageReceiver.removePushPingBackForMessageId(Integer.valueOf(r0i));
            }
        }
    }

    protected void a() {
        this.k = (TextView) findViewById(R.id.userName);
        this.l = (ImageView) findViewById(R.id.userIcon);
        this.w = (ImageButton) findViewById(R.id.setting);
        this.r = (LinearLayout) findViewById(R.id.menuLayout);
        this.r.setVisibility(Base64.DONT_BREAK_LINES);
        Resources r0_Resources = getResources();
        this.g = r0_Resources.getColorStateList(R.color.title_nav);
        this.h = r0_Resources.getColorStateList(R.color.title_nav_active_night);
        this.i = r0_Resources.getColorStateList(R.color.title_post);
        this.j = r0_Resources.getColorStateList(R.color.title_nav_night);
        this.b = this.u.inflate(f(), null);
        this.c = (MyViewFlipper) this.b.findViewById(R.id.containerBody);
        this.m = (ImageView) this.b.findViewById(R.id.message_tip);
        this.d = (ImageButton) this.b.findViewById(R.id.leftBtn);
        this.f = (ImageButton) this.b.findViewById(R.id.rightBtn);
        this.e = (ImageButton) this.b.findViewById(R.id.left_indicator);
        if (UIHelper.isNightTheme()) {
            this.b.findViewById(R.id.adLayerMask).setVisibility(0);
        } else {
            this.b.findViewById(R.id.adLayerMask).setVisibility(Base64.DONT_BREAK_LINES);
        }
    }

    protected void a(Class<?> r3_Class_) {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
    }

    protected void a(String r3_String) {
        TextView r0_TextView = (TextView) this.b.findViewById(R.id.title);
        if (r0_TextView != null) {
            r0_TextView.setText(r3_String);
        }
    }

    protected void b() {
        findViewById(R.id.userLayout).setOnClickListener(new h(this));
        this.w.setOnClickListener(new i(this));
        this.w.setOnLongClickListener(new j(this));
        if (this.d != null) {
            this.d.setOnClickListener(this.n);
            this.d.setOnTouchListener(QsbkApp.TouchDark);
            FrameLayout r0_FrameLayout = (FrameLayout) this.d.getParent();
            r0_FrameLayout.post(new k(this, r0_FrameLayout));
        }
        if (this.f != null) {
            this.f.setOnClickListener(e());
            this.f.setOnTouchListener(QsbkApp.TouchDark);
            LinearLayout r0_LinearLayout = (LinearLayout) this.f.getParent();
            r0_LinearLayout.post(new l(this, r0_LinearLayout));
        }
    }

    protected void c() {
        if (this.m != null) {
            this.m.setVisibility(Base64.DONT_BREAK_LINES);
        }
    }

    public void changeUi() {
        this.v.obtainMessage(1).sendToTarget();
    }

    public void cleanAdViewLayout() {
        this.o = null;
    }

    public void createCloseDialog(Context r4_Context, AdViewLayout r5_AdViewLayout) {
        new Builder(r4_Context).setTitle("\u63d0\u793a").setMessage((CharSequence)"\u786e\u8ba4\u8981\u5173\u95ed\u5e7f\u544a\u5417?").setNegativeButton((CharSequence)"\u53d6\u6d88", new g(this, r5_AdViewLayout)).setPositiveButton((CharSequence)"\u786e\u5b9a", new f(this, r5_AdViewLayout)).show().setCanceledOnTouchOutside(false);
    }

    protected void d() {
        if (this.m != null) {
            this.m.setVisibility(0);
        }
    }

    protected OnClickListener e() {
        return this.A;
    }

    protected abstract int f();

    protected void g() {
        if (this.o != null) {
        } else {
            String r0_String = HttpUtils.getNetworkType(QsbkApp.mContext);
            String r1_String = SharePreferenceUtils.getSharePreferencesValue("adbanner-close");
            if (!TextUtils.isEmpty(r1_String)) {
                if (r1_String.equals("0")) {
                    this.B = SwitcherMode.DEFAULT;
                } else {
                    if ((!r1_String.equals("1")) || r0_String.toUpperCase().equals("WIFI")) {
                        if (r1_String.equals(DownloadService.V2)) {
                            this.B = SwitcherMode.CANCLOSED;
                        }
                    } else {
                        this.B = SwitcherMode.CANCLOSED;
                    }
                    if (r0_RelativeLayout == null) {
                        this.o = new AdViewLayout(this, Constants.AD_KEY);
                        this.o.setAdViewInterface(this);
                        AdViewTargeting.setSwitcherMode(this.B);
                        r0_RelativeLayout.addView(this.o);
                        r0_RelativeLayout.invalidate();
                    }
                }
            }
            RelativeLayout r0_RelativeLayout = (RelativeLayout) findViewById(R.id.adLayout);
            if (r0_RelativeLayout == null) {
            } else {
                this.o = new AdViewLayout(this, Constants.AD_KEY);
                this.o.setAdViewInterface(this);
                AdViewTargeting.setSwitcherMode(this.B);
                r0_RelativeLayout.addView(this.o);
                r0_RelativeLayout.invalidate();
            }
        }
    }

    public LinearLayout getMenuLayout() {
        return this.r;
    }

    public MenuHorizontalScrollView getScrollView() {
        return this.p;
    }

    protected void h() {
        if (getCurrentActivity() != null) {
            ((GroupChildBaseListViewActivity) getCurrentActivity()).getmListView().setSelection(0);
        }
    }

    protected void i() {
        if (this.f != null) {
            this.f.setVisibility(Base64.DONT_BREAK_LINES);
        }
    }

    public boolean isAppOnForeground() {
        String r2_String = getApplicationContext().getPackageName();
        List r0_List = ((ActivityManager) getApplicationContext().getSystemService("activity")).getRunningAppProcesses();
        if (r0_List == null) {
            return false;
        }
        Iterator r3_Iterator = r0_List.iterator();
        while (r3_Iterator.hasNext()) {
            RunningAppProcessInfo r0_RunningAppProcessInfo = (RunningAppProcessInfo) r3_Iterator.next();
            if (r0_RunningAppProcessInfo.processName.equals(r2_String) && r0_RunningAppProcessInfo.importance == 100) {
                return true;
            }
        }
        return false;
    }

    public void onClickAd() {
    }

    public void onClosedAd() {
        if (this.o != null) {
            this.o.setClosed(true);
            cleanAdViewLayout();
        }
    }

    protected void onCreate(Bundle r7_Bundle) {
        int r1i = XListViewFooter.STATE_NODATA;
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
        super.onCreate(r7_Bundle);
        getWindow().setFormat(r1i);
        this.u = LayoutInflater.from(this);
        setContentView(R.layout.layout_menu_scroll_view);
        this.p = (MenuHorizontalScrollView) findViewById(R.id.mScrollView);
        b(this.x);
        a();
        View r0_View = new View(this);
        r0_View.setBackgroundResource(R.drawable.slide_left_shadow);
        View[] r1_ViewA = new View[2];
        r1_ViewA[0] = r0_View;
        r1_ViewA[1] = this.b;
        this.t = r1_ViewA;
        if (this.d != null) {
            this.p.initViews(this.t, new SizeCallBackForMenu(this.d), this.q, this.r);
            this.p.setMenuBtn(this.d);
        } else {
            this.p.initViews(this.t, new SizeCallBackForMenu(this.e), this.q, this.r);
            this.p.setMenuBtn(this.e);
        }
        b();
        QsbkApp.getInstance().addActivity(this);
        if (QsbkApp.allCollection.size() == 0) {
            SharePreferenceUtils.getCollections();
        }
        if (QsbkApp.currentUser != null) {
            this.k.setText(QsbkApp.currentUser.userName);
        }
        try {
            if (QsbkApp.currentUser == null || TextUtils.isEmpty(QsbkApp.currentUser.userId) || TextUtils.isEmpty(QsbkApp.currentUser.userIcon) || "null".equals(QsbkApp.currentUser.userIcon.trim())) {
                l();
                QsbkApp.messageCount = 1;
                ChatEngine.getInstance().unReadCountChangerListener = this;
                j();
                k();
            } else {
                String r0_String = Constants.ARATAR_URL;
                Object[] r1_ObjectA = new Object[4];
                r1_ObjectA[0] = Integer.valueOf(Integer.valueOf(QsbkApp.currentUser.userId).intValue() / 10000);
                r1_ObjectA[1] = QsbkApp.currentUser.userId;
                r1_ObjectA[2] = "thumb";
                r1_ObjectA[3] = QsbkApp.currentUser.userIcon;
                QsbkApp.getInstance().getAvatarWorker(this.a).loadImage(String.format(r0_String, r1_ObjectA), (ImageView) findViewById(R.id.userIcon));
                l();
                QsbkApp.messageCount = 1;
                ChatEngine.getInstance().unReadCountChangerListener = this;
                j();
                k();
            }
        } catch (Exception e) {
        }
    }

    public void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        } catch (Exception e) {
            this.c.stopFlipping();
        }
    }

    public void onDisplayAd() {
    }

    public boolean onKeyDown(int r4i, KeyEvent r5_KeyEvent) {
        if (r4i != 25 || ((GroupChildBaseListViewActivity) getCurrentActivity())._firstVisibleItem + ((GroupChildBaseListViewActivity) getCurrentActivity())._visibleItemCount < ((GroupChildBaseListViewActivity) getCurrentActivity())._totalItemCount - 2) {
            if (r4i == 82) {
                resumeMenuLayout();
                return true;
            } else {
                if (r4i == 4) {
                    return this.C.handleBackPressed();
                }
                return super.onKeyDown(r4i, r5_KeyEvent);
            }
        } else {
            ((GroupChildBaseListViewActivity) getCurrentActivity()).onLoadMore();
            return true;
        }
    }

    protected void onPause() {
        super.onPause();
        this.z = false;
    }

    protected void onResume() {
        super.onResume();
        if (QsbkApp.currentUser != null) {
            this.k.setText(QsbkApp.currentUser.userName);
        } else {
            this.k.setText(R.string.default_user_name);
        }
        if (QsbkApp.currentUser == null || TextUtils.isEmpty(QsbkApp.currentUser.userId) || TextUtils.isEmpty(QsbkApp.currentUser.userIcon) || "null".equals(QsbkApp.currentUser.userIcon.trim())) {
            if (UIHelper.isNightTheme()) {
                this.l.setImageResource(R.drawable.side_user_avatar_night);
                this.z = true;
            } else {
                this.l.setImageResource(R.drawable.side_user_avatar);
                this.z = true;
            }
        } else {
            try {
                String r0_String = Constants.ARATAR_URL;
                Object[] r1_ObjectA = new Object[4];
                r1_ObjectA[0] = Integer.valueOf(Integer.valueOf(QsbkApp.currentUser.userId).intValue() / 10000);
                r1_ObjectA[1] = QsbkApp.currentUser.userId;
                r1_ObjectA[2] = "thumb";
                r1_ObjectA[3] = QsbkApp.currentUser.userIcon;
                QsbkApp.getInstance().getAvatarWorker(this).loadImage(String.format(r0_String, r1_ObjectA), (ImageView) findViewById(R.id.userIcon));
            } catch (Exception e) {
            }
            this.z = true;
        }
    }

    protected void onStop() {
        QsbkApp.getInstance().removeActivity(this);
        super.onStop();
    }

    public void recreate() {
        this.c.removeAllViews();
        onCreate(null);
        QsbkApp.getInstance().destoryImageWorker();
    }

    public void resumeMenuLayout() {
        this.p.clickMenuBtn();
        this.s.resume();
        c();
    }

    public void setScrollView(MenuHorizontalScrollView r1_MenuHorizontalScrollView) {
        this.p = r1_MenuHorizontalScrollView;
    }

    public void stopVoteService() {
        if (this.y != null) {
            stopService(this.y);
        }
    }
}