package qsbk.app.push;

import android.os.Message;
import java.util.TimerTask;

// compiled from: PushPingBack.java
class c extends TimerTask {
    final /* synthetic */ PushPingBack a;

    c(PushPingBack r1_PushPingBack) {
        this.a = r1_PushPingBack;
    }

    public void run() {
        Message r0_Message = new Message();
        r0_Message.what = 1;
        this.a.a.sendMessage(r0_Message);
    }
}