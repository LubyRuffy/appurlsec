package qsbk.app.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.security.SecurityBindActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: RegisterActivity.java
class cj extends Handler {
    final /* synthetic */ RegisterActivity a;

    cj(RegisterActivity r1_RegisterActivity) {
        this.a = r1_RegisterActivity;
    }

    public void handleMessage(Message r5_Message) {
        int r1i = XListViewFooter.STATE_NODATA;
        switch (r5_Message.what) {
            case XListViewHeader.STATE_NORMAL:
                this.a.t.setVisibility(r1i);
                this.a.r.setVisibility(Base64.DONT_BREAK_LINES);
                Intent r1_Intent = new Intent(this.a.p, SecurityBindActivity.class);
                JSONObject r2_JSONObject = new JSONObject();
                try {
                    r2_JSONObject.put(QsbkDatabase.LOGIN, this.a.w.getText().toString().trim());
                    r2_JSONObject.put("pass", this.a.v.getText().toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                r1_Intent.putExtra("response", r2_JSONObject.toString());
                this.a.p.startActivity(r1_Intent);
                return;
        }
        this.a.t.setVisibility(r1i);
        this.a.r.setVisibility(0);
        this.a.v.setText(RContactStorage.PRIMARY_KEY);
        Toast.makeText(QsbkApp.mContext, (String) r5_Message.obj, 1).show();
    }
}