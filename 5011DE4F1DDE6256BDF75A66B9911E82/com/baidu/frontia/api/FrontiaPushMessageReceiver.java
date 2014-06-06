package com.baidu.frontia.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.baidu.android.pushservice.PushConstants;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Constants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.push.Utils;

public abstract class FrontiaPushMessageReceiver extends BroadcastReceiver {
    public static final String TAG;

    static {
        TAG = FrontiaPushMessageReceiver.class.getSimpleName();
    }

    public abstract void onBind(Context r1_Context, int r2i, String r3_String, String r4_String, String r5_String, String r6_String);

    public abstract void onDelTags(Context r1_Context, int r2i, List<String> r3_List_String, List<String> r4_List_String, String r5_String);

    public abstract void onListTags(Context r1_Context, int r2i, List<String> r3_List_String, String r4_String);

    public abstract void onMessage(Context r1_Context, String r2_String, String r3_String);

    public abstract void onNotificationClicked(Context r1_Context, String r2_String, String r3_String, String r4_String);

    public final void onReceive(Context r10_Context, Intent r11_Intent) {
        int r0i = 0;
        if (r11_Intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
            if (r11_Intent.getExtras() == null) {
            } else {
                onMessage(r10_Context, r11_Intent.getExtras().getString(PushConstants.EXTRA_PUSH_MESSAGE_STRING), r11_Intent.getStringExtra(PushConstants.EXTRA_EXTRA));
            }
        } else if (r11_Intent.getAction().equals(PushConstants.ACTION_RECEIVE)) {
            String r3_String = r11_Intent.getStringExtra(Utils.RESPONSE_METHOD);
            int r2i = r11_Intent.getIntExtra(PushConstants.EXTRA_ERROR_CODE, 0);
            CharSequence r1_CharSequence = RContactStorage.PRIMARY_KEY;
            if (r11_Intent.getByteArrayExtra(Utils.RESPONSE_CONTENT) != null) {
                r1_CharSequence = new String(r11_Intent.getByteArrayExtra(Utils.RESPONSE_CONTENT));
            }
            try {
                if (r3_String.equals(PushConstants.METHOD_BIND)) {
                    if (!TextUtils.isEmpty(r1_CharSequence)) {
                        JSONObject r0_JSONObject = new JSONObject(r1_CharSequence);
                        String r6_String = r0_JSONObject.getString("request_id");
                        r0_JSONObject = r0_JSONObject.getJSONObject("response_params");
                        onBind(r10_Context, r2i, r0_JSONObject.getString(Constants.PARAM_APP_ID), r0_JSONObject.getString(QsbkDatabase.USER_ID), r0_JSONObject.getString("channel_id"), r6_String);
                    }
                } else if (r3_String.equals(PushConstants.METHOD_UNBIND)) {
                    onUnbind(r10_Context, r2i, new JSONObject(r1_CharSequence).getString("request_id"));
                } else if (r3_String.equals(PushConstants.METHOD_SET_TAGS)) {
                    r3_JSONObject = new JSONObject(r1_CharSequence);
                    r5_String = r3_JSONObject.getString("request_id");
                    r1_JSONArray = r3_JSONObject.getJSONObject("response_params").getJSONArray("details");
                    r3_List = new ArrayList();
                    r4_List = new ArrayList();
                    while (r0i < r1_JSONArray.length()) {
                        r6_JSONObject = r1_JSONArray.getJSONObject(r0i);
                        r7_String = r6_JSONObject.getString("tag");
                        if (r6_JSONObject.getInt("result") == 0) {
                            r3_List.add(r7_String);
                        } else {
                            r4_List.add(r7_String);
                        }
                        r0i++;
                    }
                    onSetTags(r10_Context, r2i, r3_List, r4_List, r5_String);
                } else if (r3_String.equals(PushConstants.METHOD_DEL_TAGS)) {
                    r3_JSONObject = new JSONObject(r1_CharSequence);
                    r5_String = r3_JSONObject.getString("request_id");
                    r1_JSONArray = r3_JSONObject.getJSONObject("response_params").getJSONArray("details");
                    r3_List = new ArrayList();
                    r4_List = new ArrayList();
                    while (r0i < r1_JSONArray.length()) {
                        r6_JSONObject = r1_JSONArray.getJSONObject(r0i);
                        r7_String = r6_JSONObject.getString("tag");
                        if (r6_JSONObject.getInt("result") == 0) {
                            r3_List.add(r7_String);
                        } else {
                            r4_List.add(r7_String);
                        }
                        r0i++;
                    }
                    onDelTags(r10_Context, r2i, r3_List, r4_List, r5_String);
                } else {
                    if (r3_String.equals(PushConstants.METHOD_LISTTAGS)) {
                        onListTags(r10_Context, r2i, r11_Intent.getStringArrayListExtra(PushConstants.EXTRA_TAGS_LIST), new JSONObject(r1_CharSequence).getString("request_id"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            if (r11_Intent.getAction().equals(PushConstants.ACTION_RECEIVER_NOTIFICATION_CLICK)) {
                onNotificationClicked(r10_Context, r11_Intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_TITLE), r11_Intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT), r11_Intent.getStringExtra(PushConstants.EXTRA_EXTRA));
            }
        }
    }

    public abstract void onSetTags(Context r1_Context, int r2i, List<String> r3_List_String, List<String> r4_List_String, String r5_String);

    public abstract void onUnbind(Context r1_Context, int r2i, String r3_String);
}