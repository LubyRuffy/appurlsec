package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.openapi.WXMediaMessage.Builder;
import qsbk.app.widget.listview.XListViewFooter;

public class ShowMessageFromWX {

    public static class Req extends BaseReq {
        public WXMediaMessage message;

        public Req(Bundle r1_Bundle) {
            fromBundle(r1_Bundle);
        }

        final boolean a() {
            return this.message == null ? false : this.message.checkArgs();
        }

        public void fromBundle(Bundle r2_Bundle) {
            super.fromBundle(r2_Bundle);
            this.message = Builder.fromBundle(r2_Bundle);
        }

        public int getType() {
            return XListViewFooter.STATE_NODATA;
        }

        public void toBundle(Bundle r2_Bundle) {
            Bundle r0_Bundle = Builder.toBundle(this.message);
            super.toBundle(r0_Bundle);
            r2_Bundle.putAll(r0_Bundle);
        }
    }

    public static class Resp extends BaseResp {
        public Resp(Bundle r1_Bundle) {
            fromBundle(r1_Bundle);
        }

        final boolean a() {
            return true;
        }

        public int getType() {
            return XListViewFooter.STATE_NODATA;
        }
    }

    private ShowMessageFromWX() {
    }
}