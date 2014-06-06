package qsbk.app.adapter;

import qsbk.app.Constants;

// compiled from: UserMenuLayoutAdapter.java
class k extends Thread {
    final /* synthetic */ j a;

    k(j r1_j, String r2_String) {
        this.a = r1_j;
        super(r2_String);
    }

    public void run() {
        this.a.a.loadLatestVersion(Constants.UPDATE_URL);
    }
}