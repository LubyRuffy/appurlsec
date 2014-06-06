package qsbk.app.service;

import qsbk.app.QsbkApp;

// compiled from: VoteHandler.java
class r implements Runnable {
    final /* synthetic */ VoteHandler a;

    r(VoteHandler r1_VoteHandler) {
        this.a = r1_VoteHandler;
    }

    public void run() {
        QsbkApp.voteHandler.obtainMessage().sendToTarget();
    }
}