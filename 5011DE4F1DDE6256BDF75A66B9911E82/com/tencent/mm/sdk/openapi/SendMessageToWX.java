package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.openapi.WXMediaMessage.Builder;
import com.tencent.mm.sdk.platformtools.Log;
import qsbk.app.widget.listview.XListViewHeader;

public class SendMessageToWX {

    public static class Req extends BaseReq {
        public static final int WXSceneSession = 0;
        public static final int WXSceneTimeline = 1;
        public WXMediaMessage message;
        public int scene;

        public Req(Bundle r1_Bundle) {
            fromBundle(r1_Bundle);
        }

        final boolean a() {
            if (this.message != null) {
                return this.message.checkArgs();
            }
            Log.e("MicroMsg.SDK.SendMessageToWX.Req", "checkArgs fail ,message is null");
            return false;
        }

        public void fromBundle(Bundle r2_Bundle) {
            super.fromBundle(r2_Bundle);
            this.message = Builder.fromBundle(r2_Bundle);
            this.scene = r2_Bundle.getInt("_wxapi_sendmessagetowx_req_scene");
        }

        public int getType() {
            return XListViewHeader.STATE_REFRESHING;
        }

        public void toBundle(Bundle r3_Bundle) {
            super.toBundle(r3_Bundle);
            r3_Bundle.putAll(Builder.toBundle(this.message));
            r3_Bundle.putInt("_wxapi_sendmessagetowx_req_scene", this.scene);
        }
    }

    public static class Resp extends BaseResp {
        public Resp(Bundle r1_Bundle) {
            fromBundle(r1_Bundle);
        }

        final boolean a() {
            return true;
        }

        public void fromBundle(Bundle r1_Bundle) {
            super.fromBundle(r1_Bundle);
        }

        public int getType() {
            return XListViewHeader.STATE_REFRESHING;
        }

        public void toBundle(Bundle r1_Bundle) {
            super.toBundle(r1_Bundle);
        }
    }

    private SendMessageToWX() {
    }
}