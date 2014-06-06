package qsbk.app.nearby.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.Calendar;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;
import qsbk.app.activity.base.SecDefaultActivity;
import qsbk.app.api.UserHeaderHelper;
import qsbk.app.core.AsyncTask;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastUtil;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.listview.XListViewHeader;

public class InfoCompleteActivity extends SecDefaultActivity {
    public static final int REQ_CODE_BIRTH = 6;
    public static final int REQ_CODE_GENDER = 5;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private UserHeaderHelper r;
    private ImageView s;
    private TextView t;
    private ProgressBar u;
    private SizeChangeScrollView v;
    private EditText w;
    private String x;
    private long y;
    private ProgressDialog z;

    public InfoCompleteActivity() {
        this.x = null;
        this.y = 0;
    }

    private String a(int r2i) {
        return getResources().getString(r2i);
    }

    private void a(long r6j) {
        if (r6j > 0) {
            Calendar r0_Calendar = Calendar.getInstance();
            r0_Calendar.setTimeInMillis(r6j);
            Object[] r3_ObjectA = new Object[1];
            r3_ObjectA[0] = Integer.valueOf(getAge(r0_Calendar, Calendar.getInstance()));
            d(String.format("%d\u5c81", r3_ObjectA));
        } else {
            d(RContactStorage.PRIMARY_KEY);
        }
    }

    private void c(String r4_String) {
        if (TextUtils.isEmpty(r4_String)) {
            r4_String = RContactStorage.PRIMARY_KEY;
        }
        this.n.setText(b(a((int)R.string.gender) + " " + r4_String));
    }

    private void d(String r4_String) {
        if (TextUtils.isEmpty(r4_String)) {
            r4_String = RContactStorage.PRIMARY_KEY;
        }
        this.o.setText(b(a((int)R.string.label_age) + " " + r4_String));
    }

    private boolean d() {
        return QsbkApp.currentUser != null && !TextUtils.isEmpty(QsbkApp.currentUser.userIcon) && !"null".equals(QsbkApp.currentUser.userIcon.toString());
    }

    private void e() {
        if (d()) {
            this.t.setVisibility(Base64.DONT_BREAK_LINES);
            QsbkApp.getInstance().getAvatarWorker(this).loadImage(UserHeaderHelper.getIconUrl(QsbkApp.currentUser), this.s);
        } else {
            this.t.setVisibility(0);
        }
    }

    private void e(String r2_String) {
        LogUtil.d("show sex in UI");
        if (TextUtils.isEmpty(r2_String) || r2_String.equals(BaseUserInfo.GENDER_UNKONW)) {
            c(RContactStorage.PRIMARY_KEY);
        } else if (NearbySelectView.GENDER_FEMALE.equals(r2_String)) {
            c(a((int)R.string.female));
        } else {
            c(a((int)R.string.male));
        }
    }

    private void h() {
        e(this.x);
        a(this.y);
    }

    private void i() {
        if (TextUtils.isEmpty(this.x)) {
            ToastUtil.Short((int)R.string.toast_input_sex);
        } else if (this.y == 0) {
            ToastUtil.Short((int)R.string.toast_input_birth);
        } else if (this.w.getText().length() > 30) {
            ToastUtil.Short((int)R.string.toast_signature_too_long);
        } else if (HttpUtils.netIsAvailable()) {
            AsyncTask r0_AsyncTask = new d(this);
            this.z = ProgressDialog.show(this, null, getString(R.string.msg_info_saving), true, false);
            r0_AsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
        } else {
            ToastUtil.Short((int)R.string.network_not_connected);
        }
    }

    protected SpannableString b(String r6_String) {
        SpannableString r0_SpannableString = new SpannableString("*" + r6_String);
        r0_SpannableString.setSpan(new ForegroundColorSpan(-65536), 0, 1, AdViewUtil.NETWORK_TYPE_ADWO);
        return r0_SpannableString;
    }

    protected void c() {
        super.c();
        this.o = (TextView) findViewById(R.id.txt_birth);
        this.n = (TextView) findViewById(R.id.txt_gender);
        this.p = (TextView) findViewById(R.id.select_from_albumn);
        this.q = (TextView) findViewById(R.id.take_picture_now);
        this.u = (ProgressBar) findViewById(R.id.topLoading);
        this.s = (ImageView) findViewById(R.id.userhead);
        this.t = (TextView) findViewById(R.id.userhead_holder);
        this.n.setOnClickListener(new f(this));
        this.o.setOnClickListener(new g(this));
        this.p.setOnClickListener(new h(this));
        this.q.setOnClickListener(new i(this));
        this.Q.setVisibility(Base64.DONT_BREAK_LINES);
        this.P.setBackgroundResource(R.drawable.icon_back_enable);
        this.P.setOnClickListener(new j(this));
        this.w = (EditText) findViewById(R.id.edit_signature);
        this.w.setOnFocusChangeListener(new k(this));
        this.w.setText(QsbkApp.currentUser.signature);
        this.v = (SizeChangeScrollView) findViewById(R.id.id_scroll_view);
        this.v.setOnSizeChangeListner(new l(this));
        h();
        ((Button) findViewById(R.id.saveBtn)).setOnClickListener(new m(this));
    }

    public int getAge(Calendar r6_Calendar, Calendar r7_Calendar) {
        return (r7_Calendar.get(XListViewHeader.STATE_REFRESHING) * 100) + r7_Calendar.get(REQ_CODE_GENDER) >= (r6_Calendar.get(XListViewHeader.STATE_REFRESHING) * 100) + r6_Calendar.get(REQ_CODE_GENDER) ? r7_Calendar.get(1) - r6_Calendar.get(1) : r7_Calendar.get(1) - r6_Calendar.get(1) - 1;
    }

    public String getCustomerTitle() {
        return getString(R.string.info_complete);
    }

    public void hideSoftKeyboard() {
        UIHelper.hideKeyboard(this);
    }

    protected void onActivityResult(int r6i, int r7i, Intent r8_Intent) {
        if (r7i == 0) {
        } else {
            super.onActivityResult(r6i, r7i, r8_Intent);
            String r0_String;
            switch (r6i) {
                case XListViewHeader.STATE_NORMAL:
                    this.r.doCropPhotoWithCaptured();
                case XListViewHeader.STATE_READY:
                    r0_String = this.r.savePickedBitmap(r8_Intent);
                    if (!TextUtils.isEmpty(r0_String)) {
                        submitAvatar(r0_String);
                    }
                case REQ_CODE_GENDER:
                    if (r8_Intent != null) {
                        r0_String = r8_Intent.getStringExtra(REQUEST_KEY.KEY_RETURN_VALUE);
                        if (r0_String == null) {
                            LogUtil.d("on gender return null");
                        } else if (r0_String != null) {
                            this.x = NearbySelectView.GENDER_FEMALE.equals(r0_String) ? NearbySelectView.GENDER_FEMALE : NearbySelectView.GENDER_MALE;
                            e(this.x);
                        }
                    }
                case REQ_CODE_BIRTH:
                    if (r8_Intent != null) {
                        Long r0_Long = Long.valueOf(r8_Intent.getLongExtra(REQUEST_KEY.KEY_RETURN_VALUE, 0));
                        if (r0_Long == null) {
                            LogUtil.d("on birth reaturn null");
                        } else {
                            LogUtil.d("on birth return ");
                            if (r0_Long.longValue() != 0) {
                                this.y = r0_Long.longValue() * 1000;
                                a(this.y);
                            }
                        }
                    }
            }
        }
    }

    protected void onCreate(Bundle r5_Bundle) {
        super.onCreate(r5_Bundle);
        this.r = new UserHeaderHelper(this);
        this.y = QsbkApp.currentUser.birthday * 1000;
        this.x = QsbkApp.currentUser.gender;
        LogUtil.d("setted sex is:" + this.x);
        setContentView(R.layout.activity_infocomplete);
        c();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        e();
        super.onResume();
    }

    protected void onStop() {
        super.onStop();
    }

    public void scrollViewToBottom() {
        this.v.postDelayed(new e(this), 200);
    }

    public void submitAvatar(String r4_String) {
        int r2i = 0;
        if (HttpUtils.isNetworkConnected(this)) {
            this.u.setVisibility(r2i);
            new c(this, r4_String).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[r2i]);
        } else {
            Toast.makeText(this, R.string.network_not_connected, 1).show();
        }
    }
}