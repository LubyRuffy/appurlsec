package qsbk.app.manager;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.android.pushservice.PushManager;
import qsbk.app.QsbkApp;
import qsbk.app.push.Utils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;

public class PushMessageManager {
    public static final String FORCE_OPEN = "force_open";
    public static final String RECEIVE_MESSAGE = "receiveMessage";
    public static boolean isStarted;

    static {
        isStarted = false;
    }

    public static String getBaiduBindId() {
        return SharePreferenceUtils.getSharePreferencesValue("baidu_user_id");
    }

    public static String getLastBindedPushVersion() {
        return SharePreferenceUtils.getSharePreferencesValue("push_bind_suc_version");
    }

    public static boolean getReceiveMsgFromLocal() {
        String r0_String = SharePreferenceUtils.getSharePreferencesValue(RECEIVE_MESSAGE);
        LogUtil.d("lcoal:" + r0_String);
        return TextUtils.isEmpty(r0_String) || "start".equalsIgnoreCase(r0_String);
    }

    public static boolean needPromptOpenPush() {
        String r1_String = SharePreferenceUtils.getSharePreferencesValue("last_prompt_push");
        if (TextUtils.isEmpty(r1_String)) {
            return true;
        }
        try {
            long r1j = System.currentTimeMillis() - Long.parseLong(r1_String);
            return (r1j > 0 ? 1 : (r1j == 0? 0 : -1)) < 0 || r1j > 345600000;
        } catch (Exception e) {
            return true;
        }
    }

    public static void notifyPushBindServer(String r2_String) {
        new a("qbk-UserSetN1", r2_String).start();
    }

    public static void setBaiduBindId(String r2_String) {
        LogUtil.d("save baidu id:" + r2_String);
        SharePreferenceUtils.setSharePreferencesValue("baidu_user_id", r2_String);
    }

    public static void setLastBindedPushVersion(String r1_String) {
        SharePreferenceUtils.setSharePreferencesValue("push_bind_suc_version", r1_String);
    }

    public static void setPushPrompted() {
        SharePreferenceUtils.setSharePreferencesValue("last_prompt_push", String.valueOf(System.currentTimeMillis()));
    }

    public static void startPushIfStopedOnce() {
        if (TextUtils.isEmpty(SharePreferenceUtils.getSharePreferencesValue(FORCE_OPEN))) {
            SharePreferenceUtils.setSharePreferencesValue(RECEIVE_MESSAGE, "start");
            SharePreferenceUtils.setSharePreferencesValue(FORCE_OPEN, "true");
        }
    }

    public static void startWork() {
        if (isStarted) {
            PushManager.resumeWork(QsbkApp.mContext);
        } else {
            PushManager.startWork(QsbkApp.mContext, 0, Utils.getMetaValue(QsbkApp.mContext, "api_key"));
            isStarted = true;
        }
    }

    public static void stopWork() {
        LogUtil.d("stop work");
        PushManager.stopWork(QsbkApp.mContext);
    }

    public static void updateRecvMsg(boolean r2z, Context r3_Context) {
        if (getReceiveMsgFromLocal() == r2z) {
            LogUtil.d("is the same,and didn't change");
        } else {
            if (!r2z) {
                SharePreferenceUtils.setSharePreferencesValue(FORCE_OPEN, "true");
            }
            SharePreferenceUtils.setSharePreferencesValue(RECEIVE_MESSAGE, r2z ? "start" : "stop");
            LogUtil.d("set receive message:" + r2z);
            if (r2z) {
                startWork();
            } else {
                PushManager.stopWork(r3_Context);
            }
            notifyPushBindServer(r2z ? "start" : "stop");
        }
    }
}