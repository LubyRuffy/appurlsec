package qsbk.app.push;

import qsbk.app.utils.HttpClient;

// compiled from: PushPingBack.java
class e extends Thread {
    final /* synthetic */ int a;
    final /* synthetic */ PushPingBack b;

    e(PushPingBack r1_PushPingBack, String r2_String, int r3i) {
        this.b = r1_PushPingBack;
        this.a = r3i;
        super(r2_String);
    }

    public void run() {
        try {
            HttpClient.getIntentce().get("http://push.qiushibaike.com/push?i=" + this.a + "&s=running");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}