package qsbk.app.adapter;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import qsbk.app.QsbkApp;

// compiled from: AdapterForLinearLayout.java
class a extends Handler {
    final /* synthetic */ AdapterForLinearLayout a;

    a(AdapterForLinearLayout r1_AdapterForLinearLayout) {
        this.a = r1_AdapterForLinearLayout;
    }

    public void handleMessage(Message r4_Message) {
        Toast.makeText(QsbkApp.mContext, (String) r4_Message.obj, 1).show();
    }
}