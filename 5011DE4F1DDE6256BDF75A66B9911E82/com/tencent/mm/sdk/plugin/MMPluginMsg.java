package com.tencent.mm.sdk.plugin;

import android.content.Context;
import android.content.Intent;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX.Req;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXAppExtendObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.platformtools.Util;

public class MMPluginMsg {
    public static final String ACTION_AUTO_MSG = "ACTION_AUTO_MSG";
    public static final String RECV_MSG = "recv_msg";
    public static final String RECV_PKG = "recv_pkg";
    public static final String RECV_THUMB = "recv_thumb";
    public static final String SEND_ERR_CODE = "send_err_code";
    public static final String SEND_ERR_TYPE = "send_err_type";
    public static final String SEND_ID = "send_id";
    public static final String TYPE = "type";
    public static final int TYPE_RECV_MSG = 2;
    public static final int TYPE_SEND_RET = 1;
    public String content;
    public long msgClientId;

    public static class ReceiverHelper {
        int a;
        Intent b;

        public ReceiverHelper(Intent r3_Intent) {
            this.a = r3_Intent.getIntExtra(TYPE, 0);
            this.b = r3_Intent;
        }

        public String getMsgContent() {
            return isRecvNew() ? this.b.getStringExtra(RECV_MSG) : null;
        }

        public Integer getSendErrCode() {
            return isSendRet() ? Integer.valueOf(this.b.getIntExtra(SEND_ERR_CODE, 0)) : null;
        }

        public Integer getSendErrType() {
            return isSendRet() ? Integer.valueOf(this.b.getIntExtra(SEND_ERR_TYPE, 0)) : null;
        }

        public Long getSendMsgId() {
            return isSendRet() ? Long.valueOf(this.b.getLongExtra(SEND_ID, 0)) : null;
        }

        public boolean isRecvNew() {
            return this.a == 2;
        }

        public boolean isSendRet() {
            return this.a == 1;
        }
    }

    public static MMPluginMsg WXAppExtentObjectToPluginMsg(WXAppExtendObject r6_WXAppExtendObject) {
        if (r6_WXAppExtendObject == null) {
            return null;
        }
        MMPluginMsg r1_MMPluginMsg = new MMPluginMsg();
        r1_MMPluginMsg.msgClientId = Util.getLong(r6_WXAppExtendObject.extInfo, -1);
        if (r1_MMPluginMsg.msgClientId == -1 || Util.isNullOrNil(r6_WXAppExtendObject.fileData)) {
            return null;
        }
        r1_MMPluginMsg.content = new String(r6_WXAppExtendObject.fileData);
        return Util.isNullOrNil(r1_MMPluginMsg.content) ? null : r1_MMPluginMsg;
    }

    public static WXAppExtendObject pluginMsgToWXAppExtendObject(MMPluginMsg r4_MMPluginMsg) {
        if (r4_MMPluginMsg == null) {
            return null;
        }
        WXAppExtendObject r0_WXAppExtendObject = new WXAppExtendObject();
        r0_WXAppExtendObject.extInfo = r4_MMPluginMsg.msgClientId;
        r0_WXAppExtendObject.fileData = r4_MMPluginMsg.content.getBytes();
        return r0_WXAppExtendObject;
    }

    public static long sendMessage(Context r7_Context, String r8_String) {
        if (Util.isNullOrNil(r8_String)) {
            return -1;
        }
        MMPluginMsg r0_MMPluginMsg = new MMPluginMsg();
        r0_MMPluginMsg.msgClientId = Util.nowMilliSecond();
        r0_MMPluginMsg.content = r8_String;
        IMediaObject r1_IMediaObject = pluginMsgToWXAppExtendObject(r0_MMPluginMsg);
        WXMediaMessage r2_WXMediaMessage = new WXMediaMessage();
        r2_WXMediaMessage.mediaObject = r1_IMediaObject;
        r2_WXMediaMessage.description = RContactStorage.PRIMARY_KEY;
        IWXAPI r1_IWXAPI = WXAPIFactory.createWXAPI(r7_Context, null);
        if (r1_IWXAPI == null) {
            return -2;
        }
        BaseReq r3_BaseReq = new Req();
        r3_BaseReq.transaction = new StringBuilder("appdata").append(r0_MMPluginMsg.msgClientId).toString();
        r3_BaseReq.message = r2_WXMediaMessage;
        return r1_IWXAPI.sendReq(r3_BaseReq) ? r0_MMPluginMsg.msgClientId : -3;
    }
}