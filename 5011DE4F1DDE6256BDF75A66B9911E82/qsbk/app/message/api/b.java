package qsbk.app.message.api;

import android.os.Handler;
import android.os.Message;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.message.ChatMsgSource;
import qsbk.app.message.api.ChatEngine.MessageBody;
import qsbk.app.message.util.ACache;
import qsbk.app.push.Utils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.HttpUtils;

// compiled from: ChatEngine.java
class b extends Thread {
    final /* synthetic */ Handler a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ ChatMsgSource d;
    final /* synthetic */ int e;
    final /* synthetic */ ChatEngine f;

    b(ChatEngine r1_ChatEngine, String r2_String, Handler r3_Handler, String r4_String, String r5_String, ChatMsgSource r6_ChatMsgSource, int r7i) {
        this.f = r1_ChatEngine;
        this.a = r3_Handler;
        this.b = r4_String;
        this.c = r5_String;
        this.d = r6_ChatMsgSource;
        this.e = r7i;
        super(r2_String);
    }

    public void run() {
        WeakReference r7_WeakReference = new WeakReference(this.a);
        Object[] r2_ObjectA = new Object[2];
        r2_ObjectA[0] = ChatEngine.CHAT_SERVER;
        r2_ObjectA[1] = this.b;
        String r1_String = String.format("%s/conv/%s?src=android", r2_ObjectA);
        try {
            if (HttpUtils.netIsAvailable()) {
                Map r2_Map = new HashMap();
                r2_Map.put(Utils.RESPONSE_CONTENT, this.c);
                if (this.d != null) {
                    r2_Map.put(QsbkDatabase.TYPE, Integer.valueOf(this.d.type));
                    r2_Map.put(SharedPref.VALUE, this.d.valueObj.encodeToJsonObject().toString());
                }
                JSONObject r8_JSONObject = new JSONObject(HttpClient.getIntentce().post(r1_String, r2_Map));
                if (r8_JSONObject.getInt("err") == 0) {
                    int r2i;
                    JSONObject r9_JSONObject = r8_JSONObject.getJSONObject(Utils.EXTRA_MESSAGE);
                    int r5i = r9_JSONObject.getInt(LocaleUtil.INDONESIAN);
                    synchronized (this.b) {
                        TreeSet r5_TreeSet;
                        ArrayList r6_ArrayList;
                        ArrayList r1_ArrayList = (ArrayList) ACache.get(QsbkApp.mContext).getAsObject(this.b);
                        TreeSet r2_TreeSet = null;
                        if (r1_ArrayList == null) {
                            r5_TreeSet = r2_TreeSet;
                            r6_ArrayList = new ArrayList();
                        } else if (r5i != 0) {
                            r5_TreeSet = new TreeSet();
                            Iterator r6_Iterator = r1_ArrayList.iterator();
                            while (r6_Iterator.hasNext()) {
                                r5_TreeSet.add(Integer.valueOf(((MessageBody) r6_Iterator.next()).mid));
                            }
                            r6_ArrayList = r1_ArrayList;
                        } else {
                            r5_TreeSet = r2_TreeSet;
                            r6_ArrayList = r1_ArrayList;
                        }
                        MessageBody r1_MessageBody = new MessageBody(r9_JSONObject.getInt(LocaleUtil.INDONESIAN), r9_JSONObject.getInt(QsbkDatabase.CREATE_AT), 1, r9_JSONObject.getString(Utils.RESPONSE_CONTENT));
                        if (r5_TreeSet == null || (!r5_TreeSet.contains(Integer.valueOf(r1_MessageBody.mid)))) {
                            r6_ArrayList.add(r1_MessageBody);
                            r2i = 1;
                        } else {
                            r2i = 0;
                        }
                        JSONArray r8_JSONArray = r8_JSONObject.getJSONArray("replied-messages");
                        int r9i = Integer.parseInt(this.b);
                        int r4i = 0;
                        int r1i = r2i;
                        r2i = 0;
                        while (r4i < r8_JSONArray.length()) {
                            JSONObject r11_JSONObject = r8_JSONArray.getJSONObject(r4i);
                            int r12i = r11_JSONObject.getInt(LocaleUtil.INDONESIAN);
                            if (r5_TreeSet == null || (!r5_TreeSet.contains(Integer.valueOf(r12i)))) {
                                r2i = 1;
                                r1i = 1;
                                r6_ArrayList.add(new MessageBody(r12i, r11_JSONObject.getInt(QsbkDatabase.CREATE_AT), r11_JSONObject.getInt("from") == r9i ? 0 : 1, r11_JSONObject.getString(Utils.RESPONSE_CONTENT)));
                            } else {
                                r2i = r1i;
                                r1i = r2i;
                            }
                            r4i++;
                            r1i = r2i;
                            r2i = r1i;
                        }
                        if (r1i != 0) {
                            ACache.get(QsbkApp.mContext).put(this.b, (Serializable)r6_ArrayList);
                        }
                    }
                    Handler r1_Handler = (Handler) r7_WeakReference.get();
                    if (r1_Handler != null) {
                        Message r3_Message = new Message();
                        r3_Message.arg1 = Integer.parseInt(this.b);
                        r3_Message.arg2 = this.e;
                        r3_Message.what = 125;
                        r1_Handler.sendMessage(r3_Message);
                        if (r2i != 0) {
                            Message r2_Message = new Message();
                            r2_Message.arg1 = Integer.parseInt(this.b);
                            r2_Message.what = 123;
                            r1_Handler.sendMessage(r2_Message);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}