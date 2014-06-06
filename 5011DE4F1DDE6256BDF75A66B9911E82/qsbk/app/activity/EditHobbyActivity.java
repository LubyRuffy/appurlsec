package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;
import qsbk.app.activity.EditInfoEntranceActivity.EDIT_TYPE;

public class EditHobbyActivity extends SingleEditTextBaseActivity {
    public String getDefaultEditTextTips() {
        return getResources().getString(R.string.edit_hobby);
    }

    public String getDefaultInputTips() {
        return getResources().getString(R.string.edit_hobby);
    }

    public Map<String, Object> getPostParams() {
        Map<String, Object> r0_Map_String__Object = new HashMap();
        r0_Map_String__Object.put(EDIT_TYPE.TYPE_HOBBY, this.r.getText().toString());
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
        r0_Intent.putExtra(REQUEST_KEY.KEY_RETURN_VALUE, this.r.getText().toString());
        return r0_Intent;
    }

    public void init() {
        super.init();
    }

    public int maxLength() {
        return 140;
    }

    public void onCancel(View r2_View) {
        c("Edit hobby cancel.");
    }

    public boolean onSure(View r6_View) {
        boolean r0z;
        int r1i = 1;
        r0z = 140 >= this.r.getText().length();
        if (!r0z) {
            String r3_String = getResources().getString(R.string.edit_limit);
            Object[] r1_ObjectA = new Object[r1i];
            r1_ObjectA[0] = Integer.valueOf(this.r.getText().length() - 140);
            Toast.makeText(this, String.format(r3_String, r1_ObjectA), 0).show();
        }
        return r0z;
    }
}