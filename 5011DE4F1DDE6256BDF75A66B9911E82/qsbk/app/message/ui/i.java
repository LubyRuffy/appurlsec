package qsbk.app.message.ui;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import java.util.ArrayList;
import qsbk.app.message.api.ChatEngine;
import qsbk.app.message.util.ACache;

// compiled from: MessageSendActivity.java
class i extends Handler {
    final /* synthetic */ MessageSendActivity a;

    i(MessageSendActivity r1_MessageSendActivity) {
        this.a = r1_MessageSendActivity;
    }

    public void handleMessage(Message r5_Message) {
        int r2i = 0;
        if (r5_Message.arg1 == 1 && r5_Message.what == 0) {
            ChatEngine.deleteLocalConv(this.a.getApplication(), MessageSendActivity.e(this.a)[r2i]);
            ACache.get(this.a).put(MessageSendActivity.e(this.a)[r2i], new ArrayList());
            this.a.finish();
            Toast.makeText(MessageSendActivity.f(this.a), (String) r5_Message.obj, 1).show();
        } else {
            Toast.makeText(MessageSendActivity.f(this.a), (String) r5_Message.obj, 1).show();
        }
    }
}