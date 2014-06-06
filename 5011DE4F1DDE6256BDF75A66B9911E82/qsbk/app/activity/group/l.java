package qsbk.app.activity.group;

import qsbk.app.QsbkApp;
import qsbk.app.message.api.ChatEngine;
import qsbk.app.utils.HttpUtils;

// compiled from: TopActivityGroup.java
class l extends Thread {
    final /* synthetic */ TopActivityGroup a;

    l(TopActivityGroup r1_TopActivityGroup, String r2_String) {
        this.a = r1_TopActivityGroup;
        super(r2_String);
    }

    public void run() {
        if ((!HttpUtils.netIsAvailable()) || QsbkApp.hasVerify || QsbkApp.currentUser == null) {
            this.a.startVersionService();
        } else {
            try {
                Thread.sleep(ChatEngine.mQueryConvInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            QsbkApp.hasVerify = true;
            this.a.startVerifyService();
        }
    }
}