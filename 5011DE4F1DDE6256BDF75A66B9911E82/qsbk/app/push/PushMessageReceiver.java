package qsbk.app.push;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.baidu.android.pushservice.PushConstants;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.message.RMsgInfoStorage;
import com.tencent.tauth.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.LoginActivity;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.activity.SingleArticle;
import qsbk.app.activity.group.EssenceActivityGroup;
import qsbk.app.activity.group.HistoryActivityGroup;
import qsbk.app.activity.group.ImagesActivityGroup;
import qsbk.app.activity.group.MyCollectionActivityGroup;
import qsbk.app.activity.group.MyContentsActivityGroup;
import qsbk.app.activity.group.MyParticipateActivityGroup;
import qsbk.app.activity.group.TopActivityGroup;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.manager.PushMessageManager;
import qsbk.app.utils.LogUtil;
import qsbk.app.widget.listview.XListViewHeader;

public class PushMessageReceiver extends BroadcastReceiver {
    public static final String TAG;
    public static SparseArray<PushPingBack> sparseArray;
    PushPingBack a;

    static {
        TAG = PushMessageReceiver.class.getSimpleName();
        sparseArray = new SparseArray();
    }

    private void a(int r3i) {
        this.a = new PushPingBack();
        this.a.start(r3i);
        registerPushPingBack(Integer.valueOf(r3i), this.a);
    }

    private void a(Context r3_Context, Intent r4_Intent, int r5i) {
        if (r5i == 0 || r5i == 1) {
            r4_Intent.setClass(r3_Context, TopActivityGroup.class);
        } else if (r5i == 2 || r5i == 3 || r5i == 4) {
            r4_Intent.setClass(r3_Context, EssenceActivityGroup.class);
        } else if (r5i == 5 || r5i == 6) {
            r4_Intent.setClass(r3_Context, ImagesActivityGroup.class);
        } else if (r5i == 7) {
            r4_Intent.setClass(r3_Context, HistoryActivityGroup.class);
        } else if (r5i == 20) {
            if (QsbkApp.currentUser != null) {
                r4_Intent.setClass(r3_Context, MyContentsActivityGroup.class);
            } else {
                r4_Intent.putExtra("targetClass", MyContentsActivityGroup.class);
                r4_Intent.setClass(r3_Context, LoginActivity.class);
            }
        } else if (r5i == 21) {
            if (QsbkApp.currentUser != null) {
                r4_Intent.setClass(r3_Context, MyParticipateActivityGroup.class);
            } else {
                r4_Intent.putExtra("targetClass", MyParticipateActivityGroup.class);
                r4_Intent.setClass(r3_Context, LoginActivity.class);
            }
        } else if (r5i == 23) {
            if (QsbkApp.currentUser != null) {
                r4_Intent.setClass(r3_Context, MyCollectionActivityGroup.class);
            } else {
                r4_Intent.putExtra("targetClass", MyCollectionActivityGroup.class);
                r4_Intent.setClass(r3_Context, LoginActivity.class);
            }
        } else {
            r4_Intent.setClass(r3_Context, TopActivityGroup.class);
        }
    }

    private void a(Intent r6_Intent) {
        if (Utils.ACTION_RESPONSE.equals(r6_Intent.getAction())) {
            if (PushConstants.METHOD_BIND.equals(r6_Intent.getStringExtra(Utils.RESPONSE_METHOD))) {
                int r0i = r6_Intent.getIntExtra(Utils.RESPONSE_ERRCODE, 0);
                if (r0i == 0) {
                    String r1_String = r6_Intent.getStringExtra(Utils.RESPONSE_CONTENT);
                    String r0_String = RContactStorage.PRIMARY_KEY;
                    try {
                        r0_String = new JSONObject(r1_String).getJSONObject("response_params").getString(QsbkDatabase.USER_ID);
                    } catch (JSONException e) {
                        Log.e("PushMessageReceiver", "Parse bind json infos error: " + e);
                    }
                    LogUtil.d("receive baidu user_id:" + r0_String);
                    if (!TextUtils.isEmpty(r0_String)) {
                        PushMessageManager.setBaiduBindId(r0_String);
                        push_bind();
                    }
                } else if (r0i == 30607) {
                    Log.d("Bind Fail", "update channel token-----!");
                }
            }
        }
    }

    private boolean a() {
        int r3i = Integer.parseInt(new SimpleDateFormat("HH:mm:ss").format(new Date()).substring(0, XListViewHeader.STATE_REFRESHING));
        return r3i >= 22 || r3i < 10;
    }

    public static PushPingBack getPushPingBackForMessageId(Integer r2_Integer) {
        return (PushPingBack) sparseArray.get(r2_Integer.intValue());
    }

    public static void registerPushPingBack(Integer r2_Integer, PushPingBack r3_PushPingBack) {
        sparseArray.put(r2_Integer.intValue(), r3_PushPingBack);
    }

    public static void removePushPingBackForMessageId(Integer r2_Integer) {
        sparseArray.remove(r2_Integer.intValue());
    }

    public void onReceive(Context r11_Context, Intent r12_Intent) {
        int r1i = -1;
        int r2i = 0;
        String r0_String;
        if (r12_Intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
            String r6_String = r12_Intent.getExtras().getString(PushConstants.EXTRA_PUSH_MESSAGE_STRING);
            String r5_String = "\u7cd7\u4e8b\u767e\u79d1";
            r0_String = "\u7cd7\u767e\u6709\u65b0\u5185\u5bb9,\u8d76\u7d27\u62a2\u5148\u770b\u54e6";
            String r4_String = RContactStorage.PRIMARY_KEY;
            if (TextUtils.isEmpty(r6_String)) {
            } else {
                NotificationManager r0_NotificationManager;
                Intent r7_Intent;
                try {
                    JSONObject r7_JSONObject = new JSONObject(r6_String);
                    r6_String = r7_JSONObject.isNull(Constants.PARAM_TITLE) ? r5_String : r7_JSONObject.getString(Constants.PARAM_TITLE);
                    r5_String = r7_JSONObject.isNull("custom_content") ? r4_String : r7_JSONObject.getString("custom_content");
                    if (QsbkApp.messageCount > 1) {
                        r4_String = "\u60a8\u6709\u6765\u81ea\u7cd7\u4e8b\u767e\u79d1\u7684" + QsbkApp.messageCount + "\u6761\u6d88\u606f";
                    } else if (r7_JSONObject.isNull(Constants.PARAM_COMMENT)) {
                        r4_String = r0_String;
                    } else {
                        r4_String = r7_JSONObject.getString(Constants.PARAM_COMMENT);
                    }
                    r0_NotificationManager = (NotificationManager) r11_Context.getSystemService("notification");
                    r7_Intent = new Intent();
                    if (TextUtils.isEmpty(r5_String)) {
                        r7_Intent.setClass(r11_Context, TopActivityGroup.class);
                        r2i = -1;
                    } else {
                        JSONObject r8_JSONObject = new JSONObject(r5_String);
                        if (!r8_JSONObject.isNull("i")) {
                            r1i = r8_JSONObject.getInt("i");
                        }
                        r5_String = r8_JSONObject.getString(QsbkDatabase.T);
                        if ("list".equals(r5_String)) {
                            a(r11_Context, r7_Intent, r8_JSONObject.getInt("v"));
                        } else if (Constants.PARAM_URL.equals(r5_String)) {
                            r7_Intent.setClass(r11_Context, PushMessageShow.class);
                            PushMessageShow.title = r6_String;
                            PushMessageShow.url = r8_JSONObject.getString("v");
                            r2i = XListViewHeader.STATE_REFRESHING;
                        } else if ("art".equals(r5_String)) {
                            r7_Intent.setClass(r11_Context, SingleArticle.class);
                            r7_Intent.putExtra("article_id", r8_JSONObject.getString("v"));
                            r7_Intent.putExtra(OneProfileActivity.SOURCE, SingleArticle.KEY_ONLY_ARTICLE_ID);
                            r2i = 1;
                        } else {
                            r7_Intent.setClass(r11_Context, TopActivityGroup.class);
                        }
                    }
                    r7_Intent.putExtra(RMsgInfoStorage.PRIMARY_KEY, r1i);
                    a(r1i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (!(r7_Intent.getComponent() != null && SingleArticle.class.getName().equals(r7_Intent.getComponent().getClassName()) && a())) {
                    r0_NotificationManager.notify(r2i, new Builder(r11_Context).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(r11_Context, r1i, r7_Intent, 134217728)).setContentTitle(r6_String).setContentText(r4_String).setSmallIcon(R.drawable.ic_launcher).setTicker(r4_String).setDefaults(1).setStyle(new BigTextStyle().bigText(r4_String)).build());
                    QsbkApp.messageCount++;
                }
            }
        } else {
            if (r12_Intent.getAction().equals(PushConstants.ACTION_RECEIVE)) {
                r0_String = r12_Intent.getStringExtra(Utils.RESPONSE_METHOD);
                r1i = r12_Intent.getIntExtra(PushConstants.EXTRA_ERROR_CODE, r2i);
                String r2_String = new String(r12_Intent.getByteArrayExtra(Utils.RESPONSE_CONTENT));
                Intent r3_Intent = new Intent(Utils.ACTION_RESPONSE);
                r3_Intent.putExtra(Utils.RESPONSE_METHOD, r0_String);
                r3_Intent.putExtra(Utils.RESPONSE_ERRCODE, r1i);
                r3_Intent.putExtra(Utils.RESPONSE_CONTENT, r2_String);
                a(r3_Intent);
            }
        }
    }

    public void push_bind() {
        boolean r0z = PushMessageManager.getReceiveMsgFromLocal();
        String r1_String = PushMessageManager.getLastBindedPushVersion();
        if (r0z && (!qsbk.app.Constants.localVersionName.equals(r1_String))) {
            PushMessageManager.notifyPushBindServer("start");
        }
    }
}