package qsbk.app.activity;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

// compiled from: EditInfoEntranceActivity.java
class ak extends Handler {
    final /* synthetic */ EditInfoEntranceActivity a;

    ak(EditInfoEntranceActivity r1_EditInfoEntranceActivity) {
        this.a = r1_EditInfoEntranceActivity;
    }

    public void handleMessage(Message r4_Message) {
        Toast.makeText(this.a, (String) r4_Message.obj, 1).show();
    }
}