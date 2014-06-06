package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.openapi.WXMediaMessage.Builder;
import com.tencent.mm.sdk.platformtools.Log;
import qsbk.app.widget.listview.XListViewFooter;

public final class GetMessageFromWX {

    public static class Req extends BaseReq {
        public String username;

        public Req(Bundle r1_Bundle) {
            fromBundle(r1_Bundle);
        }

        final boolean a() {
            return true;
        }

        public void fromBundle(Bundle r1_Bundle) {
            super.fromBundle(r1_Bundle);
        }

        public int getType() {
            return XListViewFooter.STATE_NOMORE;
        }

        public void toBundle(Bundle r1_Bundle) {
            super.toBundle(r1_Bundle);
        }
    }

    public static class Resp extends BaseResp {
        public WXMediaMessage message;

        public Resp(Bundle r1_Bundle) {
            fromBundle(r1_Bundle);
        }

        final boolean a() {
            if (this.message != null) {
                return this.message.checkArgs();
            }
            Log.e("MicroMsg.SDK.GetMessageFromWX.Resp", "checkArgs fail, message is null");
            return false;
        }

        public void fromBundle(Bundle r2_Bundle) {
            super.fromBundle(r2_Bundle);
            this.message = Builder.fromBundle(r2_Bundle);
        }

        public int getType() {
            return XListViewFooter.STATE_NOMORE;
        }

        public void toBundle(Bundle r2_Bundle) {
            super.toBundle(r2_Bundle);
            r2_Bundle.putAll(Builder.toBundle(this.message));
        }
    }

    private GetMessageFromWX() {
    }
}