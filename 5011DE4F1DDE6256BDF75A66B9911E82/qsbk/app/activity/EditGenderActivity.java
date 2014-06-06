package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.view.View;
import android.widget.RadioGroup;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;
import qsbk.app.activity.EditInfoEntranceActivity.EDIT_TYPE;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.utils.LogUtil;

public class EditGenderActivity extends EditInfoBaseActivity {
    private RadioGroup p;
    private String q;
    private int r;

    public EditGenderActivity() {
        this.r = -1;
    }

    private void c() {
        new Builder(this).setCancelable(true).setMessage(R.string.edit_gender).setPositiveButton(R.string.edit_gender_dialog_ok, new u(this)).setTitle(R.string.nearby_pop_title).show();
    }

    public int getLayout() {
        return R.layout.layout_edit_gender;
    }

    public Map<String, Object> getPostParams() {
        Map<String, Object> r0_Map_String__Object = new HashMap();
        r0_Map_String__Object.put(EDIT_TYPE.TYPE_GENDER, this.q);
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
        r0_Intent.putExtra(REQUEST_KEY.KEY_RETURN_VALUE, this.q);
        return r0_Intent;
    }

    public void handleIntent(Intent r5_Intent) {
        Serializable r0_Serializable = r5_Intent.getSerializableExtra(REQUEST_KEY.KEY_DEFAULT_VALUE);
        this.q = r0_Serializable != null ? (String) r0_Serializable : RContactStorage.PRIMARY_KEY;
        if (NearbySelectView.GENDER_FEMALE.equalsIgnoreCase(this.q)) {
            this.r = 2131099920;
            this.p.check(R.id.gender_female);
        } else if (NearbySelectView.GENDER_MALE.equalsIgnoreCase(this.q)) {
            this.r = 2131099919;
            this.p.check(R.id.gender_male);
        } else {
            this.q = BaseUserInfo.GENDER_UNKONW;
        }
    }

    public void init() {
        this.p = (RadioGroup) findViewById(R.id.gender_group);
        this.p.setOnCheckedChangeListener(new t(this));
    }

    public void onCancel(View r2_View) {
        c("Edit gender cancel.");
    }

    public boolean onSure(View r3_View) {
        LogUtil.d("gender:" + this.q);
        return !BaseUserInfo.GENDER_UNKONW.equalsIgnoreCase(this.q);
    }
}