package qsbk.app.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.Iterator;
import java.util.Set;
import qsbk.app.QsbkApp;
import qsbk.app.model.Vote;

public class VoteHandler extends Handler {
    private int a;
    private int b;
    public long lastSendVoteTime;

    public VoteHandler(Looper r4_Looper) {
        super(r4_Looper);
        this.lastSendVoteTime = 0;
        this.a = 0;
        this.b = 0;
    }

    private void a() {
        if (sendVotes() != 0) {
            this.a++;
        } else {
            this.a = 0;
        }
    }

    private String b() {
        StringBuffer r1_StringBuffer = new StringBuffer("{\"votes\":[");
        Set r0_Set = QsbkApp.waitSendVotes.keySet();
        StringBuffer r2_StringBuffer = new StringBuffer();
        Iterator r3_Iterator = r0_Set.iterator();
        while (r3_Iterator.hasNext()) {
            r2_StringBuffer.append(((Vote) QsbkApp.waitSendVotes.get((String) r3_Iterator.next())).toString());
            r2_StringBuffer.append(",");
        }
        String r0_String = RContactStorage.PRIMARY_KEY;
        if (r2_StringBuffer.toString().length() > 0) {
            r0_String = r2_StringBuffer.toString().substring(0, r2_StringBuffer.toString().length() - 1);
        }
        r1_StringBuffer.append(r0_String);
        r1_StringBuffer.append("]}");
        return r1_StringBuffer.toString();
    }

    public void handleMessage(Message r7_Message) {
        int r0i = QsbkApp.waitSendVotes.size();
        if (r0i == 0) {
            this.b++;
        } else {
            this.b = 0;
        }
        if (this.lastSendVoteTime == 0) {
            this.lastSendVoteTime = System.currentTimeMillis();
            if (r0i > 0) {
                a();
            }
        } else if (r0i >= 2) {
            this.lastSendVoteTime = System.currentTimeMillis();
            a();
        } else if (System.currentTimeMillis() - this.lastSendVoteTime > 60000) {
            if (r0i > 0) {
                this.lastSendVoteTime = System.currentTimeMillis();
                a();
            }
        }
        if (this.a >= 2 || this.b >= 12) {
        } else {
            postDelayed(new r(this), 15000);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int sendVotes() {
        /*
        r5_this = this;
        r1 = 999; // 0x3e7 float:1.4E-42 double:4.936E-321;
        r0 = r5.b();	 //Catch:{ QiushibaikeException -> 0x002a, Exception -> 0x0032 }
        r2 = qsbk.app.utils.HttpClient.getIntentce();	 //Catch:{ QiushibaikeException -> 0x002a, Exception -> 0x0032 }
        r3 = "http://vote.qiushibaike.com/vote_queue";
        r0 = r2.post(r3, r0);	 //Catch:{ QiushibaikeException -> 0x002a, Exception -> 0x0032 }
        r2 = new org.json.JSONObject;	 //Catch:{ QiushibaikeException -> 0x002a, Exception -> 0x0032 }
        r2.<init>(r0);	 //Catch:{ QiushibaikeException -> 0x002a, Exception -> 0x0032 }
        r0 = "err";
        r0 = r2.getInt(r0);	 //Catch:{ QiushibaikeException -> 0x002a, Exception -> 0x0032 }
        if (r0 != 0) goto L_0x0029;
    L_0x001d:
        r1 = qsbk.app.database.QsbkDatabase.getInstance();	 //Catch:{ QiushibaikeException -> 0x003c, Exception -> 0x003a }
        r1.updateVote();	 //Catch:{ QiushibaikeException -> 0x003c, Exception -> 0x003a }
        r1 = qsbk.app.QsbkApp.waitSendVotes;	 //Catch:{ QiushibaikeException -> 0x003c, Exception -> 0x003a }
        r1.clear();	 //Catch:{ QiushibaikeException -> 0x003c, Exception -> 0x003a }
    L_0x0029:
        return r0;
    L_0x002a:
        r0 = move-exception;
        r4 = r0;
        r0 = r1;
        r1 = r4;
    L_0x002e:
        r1.printStackTrace();
        goto L_0x0029;
    L_0x0032:
        r0 = move-exception;
        r4 = r0;
        r0 = r1;
        r1 = r4;
    L_0x0036:
        r1.printStackTrace();
        goto L_0x0029;
    L_0x003a:
        r1 = move-exception;
        goto L_0x0036;
    L_0x003c:
        r1 = move-exception;
        goto L_0x002e;
        */

    }
}