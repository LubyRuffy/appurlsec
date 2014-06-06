package qsbk.app.push;

import qsbk.app.utils.HttpClient;

// compiled from: PushPingBack.java
class d extends Thread {
    final /* synthetic */ PushPingBack a;

    d(PushPingBack r1_PushPingBack, String r2_String) {
        this.a = r1_PushPingBack;
        super(r2_String);
    }

    public void run() {
        try {
            String r0_String = "http://push.qiushibaike.com/push?i=" + this.a.c;
            if (PushPingBack.messageState == 2) {
                r0_String = r0_String + "&s=launched";
                this.a.stopTimer();
            }
            if (PushPingBack.messageState == 1) {
                PushPingBack.messageState = 2;
            }
            HttpClient.getIntentce().get(r0_String);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}