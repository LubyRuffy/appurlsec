package qsbk.app.service;

import qsbk.app.Constants;

// compiled from: VersionCheckService.java
class m extends Thread {
    final /* synthetic */ l a;

    m(l r1_l, String r2_String) {
        this.a = r1_l;
        super(r2_String);
    }

    public void run() {
        this.a.a.loadLatestVersion(Constants.UPDATE_URL);
    }
}