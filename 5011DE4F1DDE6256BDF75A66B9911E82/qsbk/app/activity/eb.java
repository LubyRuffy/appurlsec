package qsbk.app.activity;

import qsbk.app.Constants;

// compiled from: UserSettingNavi.java
class eb extends Thread {
    final /* synthetic */ ea a;

    eb(ea r1_ea, String r2_String) {
        this.a = r1_ea;
        super(r2_String);
    }

    public void run() {
        this.a.a.loadLatestVersion(Constants.UPDATE_URL);
    }
}