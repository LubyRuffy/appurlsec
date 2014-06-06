package qsbk.app.activity;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;
import qsbk.app.utils.AstrologyUtils;
import qsbk.app.utils.ToastUtil;
import qsbk.app.widget.DatePickerDialogFragment;

public class EditBirthActivity extends EditInfoBaseActivity implements OnDateSetListener {
    private static final String p;
    private DatePickerDialogFragment q;
    private long r;
    private ListView s;
    private ArrayAdapter<String> t;
    private String[] u;

    static {
        p = EditBirthActivity.class.getSimpleName();
    }

    public EditBirthActivity() {
        this.u = new String[2];
    }

    private String a(int r5i) {
        int r0i = Math.max(r5i, 0);
        String r1_String = getResources().getString(R.string.age);
        Object[] r2_ObjectA = new Object[1];
        r2_ObjectA[0] = Integer.valueOf(r0i);
        return String.format(r1_String, r2_ObjectA);
    }

    private void c() {
        if (this.q == null) {
            this.q = new DatePickerDialogFragment();
            this.q.setMaxDate(new Date().getTime());
            this.q.setDateSetListener(this);
            this.q.setInitialTime(this.r);
        }
        this.q.show(getSupportFragmentManager(), p);
    }

    public int getLayout() {
        return R.layout.layout_edit_birth;
    }

    public Map<String, Object> getPostParams() {
        Map<String, Object> r0_Map_String__Object = new HashMap();
        r0_Map_String__Object.put("birthday", Long.valueOf(this.r / 1000));
        return r0_Map_String__Object;
    }

    public String getPostUrl() {
        String r0_String = Constants.UPDATE_USERINFO_1;
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = QsbkApp.currentUser.userId;
        return String.format(r0_String, r1_ObjectA);
    }

    public Intent getResultData() {
        Intent r0_Intent = new Intent();
        r0_Intent.putExtra(REQUEST_KEY.KEY_RETURN_VALUE, this.r / 1000);
        return r0_Intent;
    }

    public void handleIntent(Intent r7_Intent) {
        Serializable r0_Serializable = r7_Intent.getSerializableExtra(REQUEST_KEY.KEY_DEFAULT_VALUE);
        this.r = r0_Serializable != null ? ((Long) r0_Serializable).longValue() : 0;
        if (this.r == 0) {
            this.r = -1;
        }
        Calendar r0_Calendar = Calendar.getInstance();
        if (this.r != -1) {
            this.r *= 1000;
            r0_Calendar.setTime(new Date(this.r));
        }
        this.u[1] = "\u661f\u5ea7\uff1a" + AstrologyUtils.date2Astrology(r0_Calendar);
        this.u[0] = "\u5e74\u9f84\uff1a" + a(AstrologyUtils.getAge(r0_Calendar));
        this.t = new ArrayAdapter(this, 2130903159, this.u);
        this.s.setAdapter(this.t);
    }

    public void init() {
        this.s = (ListView) findViewById(R.id.listview);
        this.s.setOnItemClickListener(new s(this));
    }

    public void onCancel(View r2_View) {
        c("Edit birth cancel.");
    }

    public void onDateSet(DatePicker r6_DatePicker, int r7i, int r8i, int r9i) {
        Calendar r0_Calendar = Calendar.getInstance();
        r0_Calendar.set(r7i, r8i, r9i);
        int r1i = AstrologyUtils.getAge(r0_Calendar);
        if (r1i < 12 || r1i > 99) {
            ToastUtil.Short((int)R.string.age_invalid);
        } else {
            this.r = r0_Calendar.getTimeInMillis();
            this.q.setInitialTime(this.r);
            this.u[0] = "\u5e74\u9f84\uff1a" + a(AstrologyUtils.getAge(r0_Calendar));
            this.u[1] = "\u661f\u5ea7\uff1a" + AstrologyUtils.date2Astrology(r0_Calendar);
            this.t.notifyDataSetChanged();
        }
    }

    public boolean onSure(View r2_View) {
        return true;
    }
}