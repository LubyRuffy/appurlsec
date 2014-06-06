package qsbk.app.activity.publish;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.QsbkApp;
import qsbk.app.utils.Base64;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: Publish.java
class a extends Handler {
    final /* synthetic */ Publish a;

    a(Publish r1_Publish) {
        this.a = r1_Publish;
    }

    public void handleMessage(Message r4_Message) {
        Toast.makeText(QsbkApp.mContext, (String) r4_Message.obj, 1).show();
        this.a.r.setVisibility(Base64.DONT_BREAK_LINES);
        this.a.t.setVisibility(0);
        if (r4_Message.what == 0) {
            this.a.n.setText(RContactStorage.PRIMARY_KEY);
            SharePreferenceUtils.remove(Publish.a(this.a));
        } else {
            String r0_String = this.a.n.getText().toString();
            String r1_String = SharePreferenceUtils.getSharePreferencesValue(Publish.a(this.a));
            if ((!Publish.b(this.a)) || r0_String.equals(RContactStorage.PRIMARY_KEY) || r0_String.equals(r1_String)) {
                this.a.n.setText(RContactStorage.PRIMARY_KEY);
            } else {
                SharePreferenceUtils.setSharePreferencesValue(Publish.a(this.a), r0_String);
                this.a.n.setText(RContactStorage.PRIMARY_KEY);
            }
        }
        this.a.finish();
    }
}