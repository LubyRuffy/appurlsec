package qsbk.app.push;

import android.os.Handler;
import android.os.Message;

// compiled from: PushPingBack.java
class b extends Handler {
    final /* synthetic */ PushPingBack a;

    b(PushPingBack r1_PushPingBack) {
        this.a = r1_PushPingBack;
    }

    public void handleMessage(Message r3_Message) {
        if (PushPingBack.messageState == 0) {
            PushPingBack.messageState = 1;
            this.a.receiveMessage();
        }
        if (PushPingBack.messageState == 2) {
            this.a.stopTimer();
        }
    }
}