package qsbk.app.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.QsbkApp;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: LoginActivity.java
class az extends Handler {
    final /* synthetic */ LoginActivity a;

    az(LoginActivity r1_LoginActivity) {
        this.a = r1_LoginActivity;
    }

    public void handleMessage(Message r5_Message) {
        int r2i = 0;
        switch (r5_Message.what) {
            case XListViewHeader.STATE_NORMAL:
                this.a.handleToken(this.a.x);
                this.a.f();
                this.a.t.setVisibility(XListViewFooter.STATE_NODATA);
                this.a.r.setVisibility(0);
                this.a.finish();
                if (this.a.y != null) {
                    this.a.p.startActivity(new Intent(this.a.p, this.a.y));
                }
                this.a.onLoginSuccess();
                return;
        }
        this.a.t.setVisibility(XListViewFooter.STATE_NODATA);
        this.a.r.setVisibility(r2i);
        this.a.v.setText(RContactStorage.PRIMARY_KEY);
        Toast.makeText(QsbkApp.mContext, (String) r5_Message.obj, 1).show();
    }
}