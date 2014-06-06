package qsbk.app.message.api;

import android.text.TextUtils;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.HttpUtils;

// compiled from: ChatEngine.java
class a extends Thread {
    final /* synthetic */ ChatEngine a;

    a(ChatEngine r1_ChatEngine, String r2_String) {
        this.a = r1_ChatEngine;
        super(r2_String);
    }

    public void run() {
        String r0_String;
        try {
            String r0_String_2;
            r0_String_2 = QsbkApp.currentUser == null ? "0" : QsbkApp.currentUser.userId;
            if (HttpUtils.netIsAvailable()) {
                r0_String = HttpClient.getIntentce().get("http://msg.qiushibaike.com/messages/unread?src=android&uid=" + r0_String_2);
                if (!TextUtils.isEmpty(r0_String)) {
                    ChatEngine.unreadCount = new JSONObject(r0_String).getInt("unread");
                }
                if (ChatEngine.unreadCount > 0) {
                    this.a.unReadCountChangerListener.changeUi();
                }
            }
        } catch (QiushibaikeException e) {
            e.printStackTrace();
        } catch (Exception e_2) {
            e_2.printStackTrace();
        }
    }
}