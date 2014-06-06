package qsbk.app.activity;

import android.os.Handler;
import android.os.Message;

// compiled from: UserSetting.java
class dc extends Handler {
    final /* synthetic */ UserSetting a;

    dc(UserSetting r1_UserSetting) {
        this.a = r1_UserSetting;
    }

    public void handleMessage(Message r3_Message) {
        this.a.v.setVisibility(0);
        super.handleMessage(r3_Message);
    }
}