package com.baidu.android.pushservice;

import android.content.Context;
import android.content.Intent;
import com.qiubai.library.adview.util.AdViewNetFetchThread;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

// compiled from: SourceFile
public class PushConstants {
    public static final String ACTION_MESSAGE = "com.baidu.android.pushservice.action.MESSAGE";
    public static final String ACTION_METHOD = "com.baidu.android.pushservice.action.METHOD";
    public static final String ACTION_RECEIVE = "com.baidu.android.pushservice.action.RECEIVE";
    public static final String ACTION_RECEIVER_NOTIFICATION_CLICK = "com.baidu.android.pushservice.action.notification.CLICK";
    public static final String ACTION_SDK_MESSAGE = "com.baidu.android.pushservice.action.SDK_MESSAGE";
    public static final String ACTION_SDK_RECEIVE = "com.baidu.android.pushservice.action.sdk.RECEIVE";
    public static final String ACTION_WEB_RECEIVE = "com.baidu.android.pushservice.action.web.RECEIVE";
    public static final int BIND_STATUS_OFFLINE = 1;
    public static final int BIND_STATUS_ONLINE = 0;
    public static final int ERROR_AUTHENTICATION_FAILED = 30603;
    public static final int ERROR_BIND_NOT_EXIST = 30608;
    public static final int ERROR_BIND_OVERLOAD = 30609;
    public static final int ERROR_CHANNEL_TOKEN_TIMEOUT = 30607;
    public static final int ERROR_DATA_NOT_FOUND = 30605;
    public static final int ERROR_METHOD_ERROR = 30601;
    public static final int ERROR_NETWORK_ERROR = 10001;
    public static final int ERROR_PARAMS_ERROR = 30602;
    public static final int ERROR_QUOTA_USE_UP = 30604;
    public static final int ERROR_SERVER_INTERNAL_ERROR = 30600;
    public static final int ERROR_SERVICE_NOT_AVAILABLE = 10002;
    public static final int ERROR_SERVICE_NOT_AVAILABLE_TEMP = 10003;
    public static final int ERROR_SUCCESS = 0;
    public static final int ERROR_TIME_EXPIRES = 30606;
    public static final int ERROR_UNKNOWN = 20001;
    public static final String EXTRA_ACCESS_TOKEN = "access_token";
    public static final String EXTRA_API_KEY = "secret_key";
    public static final String EXTRA_APP = "app";
    public static final String EXTRA_APP_ID = "app_id";
    public static final String EXTRA_BIND_NAME = "bind_name";
    public static final String EXTRA_BIND_STATUS = "bind_status";
    public static final String EXTRA_CB_URL = "cb_url";
    public static final String EXTRA_CONTENT = "content";
    public static final String EXTRA_ERROR_CODE = "error_msg";
    public static final String EXTRA_EXTRA = "extra_extra_custom_content";
    public static final String EXTRA_FETCH_NUM = "fetch_num";
    public static final String EXTRA_FETCH_TYPE = "fetch_type";
    public static final String EXTRA_GID = "gid";
    public static final String EXTRA_GROUP_FETCH_NUM = "group_fetch_num";
    public static final String EXTRA_GROUP_FETCH_TYPE = "group_fetch_type";
    public static final String EXTRA_HASHCODE = "hash_code";
    public static final String EXTRA_METHOD = "method";
    public static final String EXTRA_MSG = "push_ msg";
    public static final String EXTRA_MSGID = "msgid";
    public static final String EXTRA_MSG_IDS = "msg_ids";
    public static final String EXTRA_MSG_KEY = "push_ msg_key";
    public static final String EXTRA_NOTIFICATION_CONTENT = "notification_content";
    public static final String EXTRA_NOTIFICATION_TITLE = "notification_title";
    public static final String EXTRA_OPENTYPE = "open_type";
    public static final String EXTRA_PUSH_MESSAGE = "message";
    public static final String EXTRA_PUSH_MESSAGE_STRING = "message_string";
    public static final String EXTRA_PUSH_SDK_VERSION = "push_sdk_version";
    public static final String EXTRA_TAGS = "tags";
    public static final String EXTRA_TAGS_LIST = "tags_list";
    public static final String EXTRA_TIMESTAMP = "time_stamp";
    public static final String EXTRA_USER_ID = "user_id";
    public static final String EXTRA_WEB_BIND_API_KEY = "com.baidu.pushservice.webapp.apikey";
    public static final int LOGIN_TYPE_ACCESS_TOKEN = 1;
    public static final int LOGIN_TYPE_API_KEY = 0;
    public static final int LOGIN_TYPE_BDUSS = 2;
    public static final int LOGIN_TYPE_UNKNOWN = 9;
    public static final String METHOD_APPSTART = "method_appstart";
    public static final String METHOD_APPSTOP = "method_appstop";
    public static final String METHOD_BIND = "method_bind";
    public static final String METHOD_COUNT = "method_count";
    public static final String METHOD_COUNTGMSG = "method_countgmsg";
    public static final String METHOD_DELETE = "method_delete";
    public static final String METHOD_DEL_TAGS = "method_del_tags";
    public static final String METHOD_FETCH = "method_fetch";
    public static final String METHOD_FETCHGMSG = "method_fetchgmsg";
    public static final String METHOD_GBIND = "method_gbind";
    public static final String METHOD_GINFO = "method_ginfo";
    public static final String METHOD_GLIST = "method_glist";
    public static final String METHOD_GUNBIND = "method_gunbind";
    public static final String METHOD_LISTTAGS = "method_listtags";
    public static final String METHOD_ONLINE = "method_online";
    public static final String METHOD_SEND = "method_send";
    public static final String METHOD_SEND_MSG_TO_SERVER = "method_send_msg_to_server";
    public static final String METHOD_SEND_MSG_TO_USER = "method_send_msg_to_user";
    public static final String METHOD_SET_TAGS = "method_set_tags";
    public static final String METHOD_UNBIND = "method_unbind";
    public static final int OPEN_BY_NOTIFICATION = 1;
    public static final int OPEN_BY_USER = 0;
    public static final String PACKAGE_NAME = "pkg_name";

    private static byte[] a(byte[] r10_byteA, String r11_String, int r12i) throws Exception {
        Key r0_Key = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(a.a(r11_String.getBytes())));
        Cipher r5_Cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        r5_Cipher.init(OPEN_BY_NOTIFICATION, r0_Key);
        int r6i = r12i / 8;
        int r1i = r6i - 11;
        int r7i = r10_byteA.length;
        Object r8_Object = new Object[((((r7i + r1i) - 1) / r1i) * r6i)];
        int r2i = 0;
        int r4i = 0;
        while (r4i < r7i) {
            int r0i = r7i - r4i;
            if (r1i < r0i) {
                r0i = r1i;
            }
            Object r9_Object = new Object[r0i];
            System.arraycopy(r10_byteA, r4i, r9_Object, OPEN_BY_USER, r0i);
            r4i += r0i;
            System.arraycopy(r5_Cipher.doFinal(r9_Object), OPEN_BY_USER, r8_Object, r2i, r6i);
            r2i += r6i;
        }
        return r8_Object;
    }

    public static Intent createMethodIntent(Context r1_Context) {
        return LoadExecutor.loadPush(r1_Context) ? com.baidu.android.pushservice.apiproxy.PushConstants.createMethodIntent(r1_Context) : new Intent();
    }

    public static String getErrorMsg(int r1i) {
        String r0_String = "Unknown";
        switch (r1i) {
            case OPEN_BY_USER:
                return "Success";
            case ERROR_NETWORK_ERROR:
                return "Network Problem";
            case ERROR_SERVICE_NOT_AVAILABLE:
                return "Service not available";
            case ERROR_SERVICE_NOT_AVAILABLE_TEMP:
                return "Service not available temporary";
            case ERROR_SERVER_INTERNAL_ERROR:
                return "Internal Server Error";
            case ERROR_METHOD_ERROR:
                return "Method Not Allowed";
            case ERROR_PARAMS_ERROR:
                return "Request Params Not Valid";
            case ERROR_AUTHENTICATION_FAILED:
                return "Authentication Failed";
            case ERROR_QUOTA_USE_UP:
                return "Quota Use Up Payment Required";
            case ERROR_DATA_NOT_FOUND:
                return "Data Required Not Found";
            case ERROR_TIME_EXPIRES:
                return "Request Time Expires Timeout";
            case ERROR_CHANNEL_TOKEN_TIMEOUT:
                return "Channel Token Timeout";
            case ERROR_BIND_NOT_EXIST:
                return "Bind Relation Not Found";
            case ERROR_BIND_OVERLOAD:
                return "Bind Number Too Many";
        }
        return "Unknown";
    }

    public static void restartPushService(Context r1_Context) {
        if (LoadExecutor.loadPush(r1_Context)) {
            com.baidu.android.pushservice.apiproxy.PushConstants.restartPushService(r1_Context);
        }
    }

    public static String rsaEncrypt(String r4_String) {
        try {
            return a.a(a(r4_String.getBytes(), "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/7VlVn9LIrZ71PL2RZMbK/Yxc\r\ndb046w/cXVylxS7ouPY06namZUFVhdbUnNRJzmGUZlzs3jUbvMO3l+4c9cw/n9aQ\r\nrm/brgaRDeZbeSrQYRZv60xzJIimuFFxsRM+ku6/dAyYmXiQXlRbgvFQ0MsVng4j\r\nv+cXhtTis2Kbwb8mQwIDAQAB\r\n", Util.BYTE_OF_KB), AdViewNetFetchThread.NetEncoding);
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (Exception e_2) {
            return null;
        }
    }

    public static void startPushService(Context r1_Context) {
        if (LoadExecutor.loadPush(r1_Context)) {
            com.baidu.android.pushservice.apiproxy.PushConstants.startPushService(r1_Context);
        }
    }
}