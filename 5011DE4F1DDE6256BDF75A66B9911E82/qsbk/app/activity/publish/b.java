package qsbk.app.activity.publish;

import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.QsbkApp;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: Publish.java
class b implements Runnable {
    final /* synthetic */ Publish a;

    b(Publish r1_Publish) {
        this.a = r1_Publish;
    }

    public void run() {
        String r0_String = this.a.n.getText().toString();
        String r1_String = SharePreferenceUtils.getSharePreferencesValue(Publish.a(this.a));
        if (r0_String.equals(RContactStorage.PRIMARY_KEY) || r0_String.equals(r1_String)) {
            this.a.v.postDelayed(this, 15000);
        } else {
            SharePreferenceUtils.setSharePreferencesValue(Publish.a(this.a), r0_String);
            Toast.makeText(QsbkApp.mContext, "\u5185\u5bb9\u5df2\u81ea\u52a8\u7f13\u5b58", 0).show();
            this.a.v.postDelayed(this, 15000);
        }
    }
}